
/*
 *                         OpenSplice DDS
 *
 *   This software and documentation are Copyright 2006 to 2013 PrismTech
 *   Limited and its licensees. All rights reserved. See file:
 *
 *                     $OSPL_HOME/LICENSE
 *
 *   for full copyright notice and license terms.
 *
 */

#include "common/example_export.h"

namespace examples {
namespace dcps {
namespace ContentFilteredTopic {
namespace isocpp  {
    OS_EXAMPLE_IMPL_EXPORT int publisher(int argc, char *argv[]);
    OS_EXAMPLE_IMPL_EXPORT int subscriber(int argc, char *argv[]);
}
}
}
}

ISOCPP_EXAMPLE_ENTRYPOINT(DCPS_ISOCPP_ContentFilteredTopic_publisher, examples::dcps::ContentFilteredTopic::isocpp::publisher)
ISOCPP_EXAMPLE_ENTRYPOINT(DCPS_ISOCPP_ContentFilteredTopic_subscriber, examples::dcps::ContentFilteredTopic::isocpp::subscriber)
