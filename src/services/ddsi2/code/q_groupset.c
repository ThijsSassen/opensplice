#include <ctype.h>
#include <stddef.h>

#include "os_defs.h"
#include "os_heap.h"
#include "os_mutex.h"
#include "os_if.h"

#include "v_groupSet.h"
#include "kernelModule.h"

#include "q_avl.h"
#include "q_log.h"
#include "q_config.h"
#include "q_xqos.h"
#include "q_groupset.h"
#include "q_error.h"
#include "q_globals.h"
#include "q_osplser.h"

/* See q_addrset.c for the rationale for these: */
#define LOCK(gs) (os_mutexLock (&((struct nn_groupset *) (gs))->lock))
#define UNLOCK(gs) (os_mutexUnlock (&((struct nn_groupset *) (gs))->lock))

struct nn_groupset_entry {
  STRUCT_AVLNODE (nn_groupset_entry_avlnode, struct nn_groupset_entry *) avlnode;
  v_group group;
};

struct nn_groupset {
  os_mutex lock;
  STRUCT_AVLTREE (nn_groupset_avltree, struct nn_groupset_entry *) grouptree;
};

static int compare_group_ptr (const void *va, const void *vb)
{
  C_STRUCT (v_group) const * const *a = va;
  C_STRUCT (v_group) const * const *b = vb;
  return (*a == *b) ? 0 : (*a < *b) ? -1 : 1;
}

static int add_group (struct nn_groupset *gs, v_group g)
{
  avlparent_t parent;
  if (avl_lookup (&gs->grouptree, &g, &parent) == NULL)
  {
    struct nn_groupset_entry *e;
    if ((e = os_malloc (sizeof (*e))) == NULL)
      return ERR_OUT_OF_MEMORY;
    avl_init_node (&e->avlnode, parent);
    e->group = g;
    avl_insert (&gs->grouptree, e);
  }
  return 0;
}

static int add_group_by_name (struct nn_groupset *gs, v_kernel kernel, const char *topic_name, const char *partition)
{
  v_group g;
  if (strchr (partition, '?') || strchr (partition, '*'))
    return 0;
  else if ((g = v_groupSetGet (kernel->groupSet, partition, topic_name)) != NULL)
  {
    int res;
    res = add_group (gs, g);
    c_free (g);
    return res;
  }
  else
  {
    TRACE (("add_group_by_name: no kernel group for %s.%s?\n", partition, topic_name));
    return 0;
  }
}

struct nn_groupset *nn_groupset_new (void)
{
  struct nn_groupset *gs;
  if ((gs = os_malloc (sizeof (*gs))) == NULL)
    return NULL;
  os_mutexInit (&gs->lock, &gv.mattr);
  avl_init (&gs->grouptree, offsetof (struct nn_groupset_entry, avlnode), offsetof (struct nn_groupset_entry, group), compare_group_ptr, 0);
  return gs;
}

int nn_groupset_fromqos (struct nn_groupset *gs, v_kernel kernel, const nn_xqos_t *qos)
{
  int res = 0;
  assert (qos->present & QP_TOPIC_NAME);
  LOCK (gs);
  if (!(qos->present & QP_PARTITION) || qos->partition.n == 0)
  {
    if ((res = add_group_by_name (gs, kernel, qos->topic_name, "")) < 0)
      goto out;
  }
  else
  {
    int i;
    for (i = 0; i < qos->partition.n; i++)
    {
      if ((res = add_group_by_name (gs, kernel, qos->topic_name, qos->partition.strs[i])) < 0)
        goto out;
    }
  }
 out:
  UNLOCK (gs);
  return res;
}

void nn_groupset_free (struct nn_groupset *gs)
{
  avl_free (&gs->grouptree, os_free);
  os_mutexDestroy (&gs->lock);
  os_free (gs);
}

int nn_groupset_foreach (const struct nn_groupset *gs, nn_groupset_foreach_t f, void *arg)
{
  struct nn_groupset_entry *e;
  int result = 0;
  LOCK (gs);
  for (e = avl_findmin (&gs->grouptree); e && result >= 0; e = avl_findsucc (&gs->grouptree, e))
  {
    int r;
    if ((r = f (e->group, arg)) < 0)
      result = r; /* which will abort the walk */
    else
      result += r;
  }
  UNLOCK (gs);
  return result;
}

static int nn_groupset_add_helper (v_group g, void *varg)
{
  struct nn_groupset *gs = varg;
  int res;
  LOCK (gs);
  res = add_group (gs, g);
  UNLOCK (gs);
  return res;
}

int nn_groupset_add (struct nn_groupset *gs, const struct nn_groupset *add)
{
  return nn_groupset_foreach (add, nn_groupset_add_helper, gs);
}

int nn_groupset_add_group (struct nn_groupset *gs, v_group g)
{
  int res;
  LOCK (gs);
  res = add_group (gs, g);
  UNLOCK (gs);
  return res;
}

int nn_groupset_empty (const struct nn_groupset *gs)
{
  int res;
  LOCK (gs);
  res = avl_empty (&gs->grouptree);
  UNLOCK (gs);
  return res;
}

/* SHA1 not available (unoffical build.) */
