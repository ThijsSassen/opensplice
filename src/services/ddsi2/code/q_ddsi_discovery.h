#ifndef NN_DDSI_DISCOVERY_H
#define NN_DDSI_DISCOVERY_H

#include "q_unused.h"

struct participant;
struct writer;
struct reader;
struct nn_rsample_info;
struct nn_rdata;

int spdp_write (struct participant *pp);
int spdp_dispose_unregister (struct participant *pp);

int sedp_write_writer (struct writer *wr);
int sedp_write_reader (struct reader *rd);
int sedp_dispose_unregister_writer (struct writer *wr);
int sedp_dispose_unregister_reader (struct reader *rd);

int nn_spdp_dqueue_handler (const struct nn_rsample_info *sampleinfo, const struct nn_rdata *fragchain, void *qarg);
int builtins_dqueue_handler (const struct nn_rsample_info *sampleinfo, const struct nn_rdata *fragchain, void *qarg);

int nn_loc_to_address (os_sockaddr_storage *dst, const nn_locator_t *src);
void nn_address_to_loc (nn_locator_t *dst, const os_sockaddr_storage *src);

#endif /* NN_DDSI_DISCOVERY_H */

/* SHA1 not available (unoffical build.) */
