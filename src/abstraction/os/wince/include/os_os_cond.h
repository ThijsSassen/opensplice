/*
 *                         Vortex OpenSplice
 *
 *   This software and documentation are Copyright 2006 to TO_YEAR ADLINK
 *   Technology Limited, its affiliated companies and licensors. All rights
 *   reserved.
 *
 *   Licensed under the ADLINK Software License Agreement Rev 2.7 2nd October
 *   2014 (the "License"); you may not use this file except in compliance with
 *   the License.
 *   You may obtain a copy of the License at:
 *                      $OSPL_HOME/LICENSE
 *
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */
#ifndef OS_WIN32_COND_H
#define OS_WIN32_COND_H

#if defined (__cplusplus)
extern "C" {
#endif

typedef struct cond {
    os_scopeAttr scope;
    long         qId;
    long         lifecycleId;
    long         state;
} os_os_cond;

#if defined (__cplusplus)
}
#endif

#endif /* OS_WIN32_COND_H */