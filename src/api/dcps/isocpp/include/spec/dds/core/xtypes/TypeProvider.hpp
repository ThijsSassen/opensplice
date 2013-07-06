/* Copyright 2010, Object Management Group, Inc.
* Copyright 2010, PrismTech, Corp.
* Copyright 2010, Real-Time Innovations, Inc.
* All rights reserved.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
#ifndef OMG_DDS_CORE_XTYPES_TYPE_PROVIDER_HPP_
#define OMG_DDS_CORE_XTYPES_TYPE_PROVIDER_HPP_

#include <dds/core/xtypes/TTypeProvider.hpp>
#include <dds/core/xtypes/detail/TypeProvider.hpp>

namespace dds {
  namespace core {
    namespace xtypes {
      typedef TTypeProvider<detail::TypeProvider> TypeProvider;
    }
  }
}

#endif /* OMG_DDS_CORE_XTYPES_TYPE_PROVIDER_HPP_ */
