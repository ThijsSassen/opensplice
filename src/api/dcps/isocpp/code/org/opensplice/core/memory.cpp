/*
*                         OpenSplice DDS
*
*   This software and documentation are Copyright 2006 to 2012 PrismTech
*   Limited and its licensees. All rights reserved. See file:
*
*                     $OSPL_HOME/LICENSE
*
*   for full copyright notice and license terms.
*
*/


/**
 * @file
 */

#include <org/opensplice/core/memory.hpp>
#include <sstream>
#include <org/opensplice/core/exception_helper.hpp>

void
org::opensplice::core::DPDeleter::operator()(DDS::DomainParticipant* dp)
{
  DDS::DomainParticipantFactory_var dpf = DDS::DomainParticipantFactory::get_instance();
  DDS::ReturnCode_t result = dpf->delete_participant(dp);
  org::opensplice::core::check_and_throw(result, OSPL_CONTEXT_LITERAL("Calling ::delete_participant"));
  DDS::release(dp);

  if (dds::core::detail::maplog("MM") >= os_reportVerbosity)
  {
    std::ostringstream oss;
    oss << "Deleted Participant at: " << std::hex << dp << std::dec;
    OMG_DDS_LOG("MM", oss.str().c_str());
  }
}

org::opensplice::core::PubDeleter::PubDeleter(const org::opensplice::core::DDS_DP_REF& dp) : dp_(dp) { }

org::opensplice::core::PubDeleter::~PubDeleter()  { }

void
org::opensplice::core::PubDeleter::operator()(DDS::Publisher* p)
{
  DDS::ReturnCode_t result = dp_->delete_publisher(p);
  org::opensplice::core::check_and_throw(result, OSPL_CONTEXT_LITERAL("Calling ::delete_publisher"));
  DDS::release(p);

  if (dds::core::detail::maplog("MM") >= os_reportVerbosity)
  {
    std::ostringstream oss;
    oss << "Deleted Publisher at: " << std::hex << p << std::dec;
    OMG_DDS_LOG("MM", oss.str().c_str());
  }
}

org::opensplice::core::DWDeleter::DWDeleter(const DDS_PUB_REF& pub) : pub_(pub) { }

org::opensplice::core::DWDeleter::~DWDeleter() { }
void
org::opensplice::core::DWDeleter::operator()(DDS::DataWriter* w)
{
  DDS::ReturnCode_t result = pub_->delete_datawriter(w);
  org::opensplice::core::check_and_throw(result, OSPL_CONTEXT_LITERAL("Calling ::delete_datawriter"));
  DDS::release(w);

  if (dds::core::detail::maplog("MM") >= os_reportVerbosity)
  {
    std::ostringstream oss;
    oss << "Deleted DataWriter at: " << std::hex << w << std::dec;
    OMG_DDS_LOG("MM", oss.str().c_str());
  }
}



org::opensplice::core::SubDeleter::SubDeleter(const DDS_DP_REF& dp) : dp_(dp) { }

org::opensplice::core::SubDeleter::~SubDeleter() { }

void
org::opensplice::core::SubDeleter::operator()(DDS::Subscriber* s)
{
  DDS::ReturnCode_t result = dp_->delete_subscriber(s);
  org::opensplice::core::check_and_throw(result, OSPL_CONTEXT_LITERAL("Calling ::delete_subscriber"));
  DDS::release(s);

  if (dds::core::detail::maplog("MM") >= os_reportVerbosity)
  {
    std::ostringstream oss;
    oss << "Deleted Subscriber at: " << std::hex << s << std::dec;
    OMG_DDS_LOG("MM", oss.str().c_str());
  }
}


org::opensplice::core::DRDeleter::DRDeleter(const DDS_SUB_REF& sub) : sub_(sub) { }
org::opensplice::core::DRDeleter::~DRDeleter() { }
void
org::opensplice::core::DRDeleter::operator()(DDS::DataReader* r)
{
  DDS::ReturnCode_t result = sub_->delete_datareader(r);
  org::opensplice::core::check_and_throw(result, OSPL_CONTEXT_LITERAL("Calling ::delete_datareader"));
  DDS::release(r);

  if (dds::core::detail::maplog("MM") >= os_reportVerbosity)
  {
    std::ostringstream oss;
    oss << "Deleted DataReader at: " << std::hex << r << std::dec;
    OMG_DDS_LOG("MM", oss.str().c_str());
  }
}

org::opensplice::core::TopicDeleter::TopicDeleter(const DDS_DP_REF& dp) : dp_(dp) { }
org::opensplice::core::TopicDeleter::~TopicDeleter() { }
void
org::opensplice::core::TopicDeleter::operator()(DDS::Topic* t)
{
  DDS::ReturnCode_t result = dp_->delete_topic(t);
  org::opensplice::core::check_and_throw(result, OSPL_CONTEXT_LITERAL("Calling ::delete_topic"));
  DDS::release(t);

  if (dds::core::detail::maplog("MM") >= os_reportVerbosity)
  {
    std::ostringstream oss;
    oss << "Deleted Topic at: " << std::hex << t << std::dec;
    OMG_DDS_LOG("MM", oss.str().c_str());
  }
}
