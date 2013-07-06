#ifndef OMG_DDS_TOPIC_TOPIC_LISTENER_HPP_
#define OMG_DDS_TOPIC_TOPIC_LISTENER_HPP_

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

#include "dds/topic/Topic.hpp"

namespace dds { namespace topic {

  template <typename T>
  class TopicListener {
  public:
    virtual ~TopicListener();

  public:
    virtual void on_inconsistent_topic(
        Topic<T>& topic,
        const dds::core::status::InconsistentTopicStatus& status) = 0;
  };

  template <typename T>
  class NoOpTopicListener : public virtual TopicListener<T> {
  public:
    virtual ~NoOpTopicListener();

  public:
    virtual void on_inconsistent_topic(
        Topic<T>& topic,
        const dds::core::status::InconsistentTopicStatus& status);
  };

} }

#endif /* OMG_DDS_TOPIC_TOPIC_LISTENER_HPP_ */
