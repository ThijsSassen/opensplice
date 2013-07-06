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
package DDS;

/**
 * <P>For each application defined class there is an ObjectHome instance,
 * which exists to globally represent the related set of instances and to
 * perform actions on it. Actually, ObjectHome is the abstract root class for
 * specialized implementation classes that are generated by the DLRL code
 * generator. The name for such a derived class is FooHome, assuming it
 * corresponds to the application-defined class Foo. Each of these specialized
 * homes is dedicated to one application-defined class, so that it completely
 * embeds the related specificity. </P>
 *  <P>ObjectHomes and their typed specializations have no corresponding factory,
 * they must be instantiated directly by the application, using a default
 * constructor without any parameters. Instantiating an ObjectHome like that
 * initializes all attributes to their default settings, (which will be
 * mentioned below). </P>
 *  <P>When an ObjectHome has been created, it must be registered to a Cache
 * before it can be used to actually manage DLRL Objects of its related type.
 * The only operations that may be invoked on an unregistered home are the
 * accessors to obtain the name of the class that it is supposed to manage
 * (name), the value of the auto_deref attribute (auto_deref) and the SQL
 * expression for the filter (content_filter). It is also allowed to change
 * this filter expression (set_content_filter) and to change the value of the
 * auto_deref attribute (set_auto_deref). </P>
 * <P>After all homes are registered to a Cache, the Cache needs to register_all_for_pub_sub,
 * thus connecting each home to the underlying DCPS infrastructure. During this
 * step, the Cache will also resolve all relationships between homes. In the case
 * that one DLRL class inherits from another one, both their corresponding homes
 * will be connected. It is possible to navigate from the home of the child class
 * to the home of its parent class (parent), or to navigate from the home of a
 * parent class to the homes of all its possible child-classes (children).</P>
 * <P>A home manages the state of all DLRL Objects of its corresponding type. With
 * respect to incoming data, it can choose to always copy that data into the
 * corresponding DLRL objects (auto_deref = <code>true</code>), or it can delay
 * that step until the user actually tries to access the state of a DLRL object
 * (auto_deref = <code>false</code>). This behaviour is not only determined by
 * the value of the auto_deref attribute, but it can also be performed on application
 * request for all objects managed by this home: deref_all loads all DCPS data into
 * the DLRL objects of the corresponding type and underef_all unloads this data from all
 * DLRL objects of the corresponding type.</P>
 * <P>To find out how information is distributed through the DCPS, it is necessary to
 * know which attribute is mapped onto which topic. The operation get_all_topic_names
 * returns the names of all topics that are mapped onto this object, the operation
 * get_topic_name returns the name of the topic that holds the value for the specified
 * attribute.</P>
 * <P>Finally, when a Cache is deleted, all ObjectHomes attached to it will also be
 * considered deleted. Invoking any operation on such a home will result in an
 * AlreadyDeleted exception being thrown.</P>
 */
public abstract class ObjectHome {

    private long admin = 0;
    //only used to load DLRL libraries via static initializer.
    private static org.opensplice.dds.dlrl.Initializer initialiser = new org.opensplice.dds.dlrl.Initializer();

    protected ObjectHome(){}

    /**
     * Returns the fully-qualified name (in IDL notation) of the class managed by this ObjectHome.
     * For a class named "Foo" in a package named "demo" this name will be "demo::Foo".
     *
     * @return the fully-qualified name of the class managed by this ObjectHome.
     * @throws DDS.AlreadyDeleted if the ObjectHome is considered to be deleted.
     */
    public final String name () throws DDS.AlreadyDeleted {
        return jniName();
    }

    /**
     * Returns the SQL expression of the content_filter.
     *
     * @return the SQL expression of the content_filter.
     * @throws DDS.AlreadyDeleted if the ObjectHome is considered to be deleted.
     */
    public final String content_filter () throws DDS.AlreadyDeleted {
        return jniFilter();
    }

    /**
     * Returns the ObjectHome that manages the parent-class of the class that is managed by
     * this ObjectHome. (I.e. when class Bar extends from class Foo, then FooHome is the
     * parent for BarHome.) When this ObjectHome has no parent, it returns <code>null</code>.
     * Also when this operation is called before the ObjectHome is registered to a Cache and
     * before the register_all_for_pubsub operation has been successfully called on the cache
     * then this operation will return a <code>null</code>.
     *
     * @return the ObjectHome of the parent-class of the class that is managed by this ObjectHome.
     * @throws DDS.AlreadyDeleted if the ObjectHome is considered to be deleted.
     */
    public final DDS.ObjectHome parent () throws DDS.AlreadyDeleted {
        return jniParent();
    }

    /**
     * Returns the ObjectHomes that manage the sub-classes of the class that is managed
     * by this ObjectHome. (I.e. when class Bar and class Rod both extend from class Foo,
     * then BarHome and RodHome are children of FooHome.) When this ObjectHome has no
     * children, it returns an empty array.
     * Also when this operation is called before the ObjectHome is registered to a Cache and
     * before the register_all_for_pubsub operation has been successfully called on the cache
     * then this operation will return an empty array.
     *
     * @return the ObjectHomes of the sub-classes of the class that is managed by this ObjectHome.
     * @throws DDS.AlreadyDeleted if the ObjectHome is considered to be deleted.
     */
    public final DDS.ObjectHome[] children () throws DDS.AlreadyDeleted {
        return jniChildren();
    }

    /**
     * Returns the index under which the ObjectHome is registered by the Cache. If the
     * ObjectHome has not yet been registered to the Cache, it returns -1.
     *
     * @return the index under which the ObjectHome is registered by the Cache.
     * @throws DDS.AlreadyDeleted if the ObjectHome is considered to be deleted.
     */
    public final int registration_index () throws DDS.AlreadyDeleted {
        return jniRegistrationIndex();
    }

    /**
     * Returns the current setting of the auto_deref attribute. When set to <code>true</code>,
     * the state of each DLRL object is always copied into it. When set to <code>false</code>,
     * the state is only copied into DLRL objects when they are explicitly accessed by the
     * application.
     *
     * @return the current setting of the auto_deref attribute.
     * @throws DDS.AlreadyDeleted if the ObjectHome is considered to be deleted.
     */
    public final boolean auto_deref () throws DDS.AlreadyDeleted {
        return jniAutoDeref();
    }

    /**
     * <P>Specifies an SQL expression that is to be used as a contentfilter.  The settings for the
     * contentfilter may only be changed when the Cache is still in the INITIAL pubsub state.
     * A PreconditionNotMet exception is thrown otherwise. When using an invalid SQL expression,
     * an SQLError will be thrown.</P>
     * <P>The current implementation does not yet support the notion of a content filter.
     * Therefore invoking this method has no effect.</P>
     *
     * @param expression the SQL expression that is to be used as a contentfilter.
     * @throws DDS.AlreadyDeleted if the ObjectHome is considered to be deleted.
     * @throws PreconditionNotMet if the Cache is not in the INITIAL pubsub state.
     * @throws DDS.SQLError if the SQL expression is invalid.
     */
    public final void set_content_filter (String expression) throws DDS.AlreadyDeleted, DDS.PreconditionNotMet, DDS.SQLError {
        jniSetFilter(expression);
    }

    /**
     * <P>Specifies the value for the auto_deref attribute. When set to <code>true</code>,
     * the state of each DLRL object is always copied into it. When set to <code>false</code>,
     * the state is only copied into DLRL objects when they are explicitly accessed by the
     * application.</P>
     * <P>The current implementation only supports dereferenced objects. Therefore an
     * auto_deref setting of <code>false</code> is not supported, meaning that invoking
     * this method has no effect. </P>
     *
     * @param value the new value for the auto_deref attribute.
     * @throws DDS.AlreadyDeleted if the ObjectHome is considered to be deleted.
     */
    public final void set_auto_deref (boolean value) throws DDS.AlreadyDeleted {
        jniSetAutoDeref(value);
    }

    /**
     * Dereferences all DLRL objects of the class that is managed by this home. This means
     * that the state of each of DLRL object is directly copied into it.
     * <P>The current implementation only supports dereferenced objects. Therefore invoking
     * this method has no effect (objects are already dereferenced).</P>
     *
     * @throws DDS.AlreadyDeleted if the ObjectHome is considered to be deleted.
     */
    public final void deref_all () throws DDS.AlreadyDeleted {
        jniDerefAll();
    }

    /**
     * <P>Undereferences all DLRL objects of the class that is managed by this home. This means
     * that the state of each DLRL object is no longer instantly available, but will only be copied
     * into it when explicitly accessed by  the application.</P>
     * <P>The current implementation only supports dereferenced objects. Therefore invoking
     * this method has no effect.</P>
     *
     * @throws DDS.AlreadyDeleted if the ObjectHome is considered to be deleted.
     */
    public final void underef_all () throws DDS.AlreadyDeleted {
        jniUnderefAll();
    }

    /**
     * Returns the name of the topic that holds the value for the specified DLRL attribute.
     * If the specified attribute does not exist, a null pointer is returned. If the
     * pubsub-state of the Cache is still set to INITIAL, a PreconditionNotMet exception
     * is thrown.
     *
     * @param attribute_name the name of the DLRL attribute whose corresponding topic needs to be found.
     * @return the name of the topic that holds the specified DLRL attribute.
     * @throws DDS.AlreadyDeleted if the ObjectHome is considered to be deleted.
     * @throws DDS.PreconditionNotMet is the pubsub_state of the Cache is still set to INITIAL.
     */
    public final String get_topic_name (String attribute_name) throws DDS.AlreadyDeleted, DDS.PreconditionNotMet {
        return jniGetTopicName(attribute_name);
    }

    /**
     * Returns the DCPS DataReader associated with the provided topic name, or
     * NULL if no reader was found. If the pubsub-state of the Cache is still
     * set to INITIAL, a PreconditionNotMet exception is thrown.
     *
     * @param topic_name the name of the topic whose corresponding data reader needs to be found.
     * @return The DCPS DataReader associated with the provided topic_name, or NULL if no reader was found.
     * @throws DDS.AlreadyDeleted if the ObjectHome is considered to be deleted.
     * @throws DDS.PreconditionNotMet is the pubsub_state of the Cache is still set to INITIAL.
     */
    public final DDS.DataReader get_datareader(String topic_name) throws DDS.AlreadyDeleted, DDS.PreconditionNotMet {
        return jniGetDataReader(topic_name);
    }

    /**
     * Returns the DCPS DataWriter associated with the provided topic name, or
     * NULL if no writer was found. If the pubsub-state of the Cache is still
     * set to INITIAL, a PreconditionNotMet exception is thrown.
     *
     * @param topic_name the name of the topic whose corresponding data writer needs to be found.
     * @return The DCPS DataWriter associated with the provided topic_name, or NULL if no writer was found.
     * @throws DDS.AlreadyDeleted if the ObjectHome is considered to be deleted.
     * @throws DDS.PreconditionNotMet is the pubsub_state of the Cache is still set to INITIAL.
     */
    public final DDS.DataWriter get_datawriter(String topic_name) throws DDS.AlreadyDeleted, DDS.PreconditionNotMet {
        return jniGetDataWriter(topic_name);
    }

    /**
     * Returns the DCPS Topic object associated with the provided topic name, or
     * NULL if no topic object was found. If the pubsub-state of the Cache is still
     * set to INITIAL, a PreconditionNotMet exception is thrown.
     *
     * @param topic_name the name of the topic whose corresponding topic object needs to be found.
     * @return The DCPS Topic object  associated with the provided topic_name, or NULL if no topic object was found.
     * @throws DDS.AlreadyDeleted if the ObjectHome is considered to be deleted.
     * @throws DDS.PreconditionNotMet is the pubsub_state of the Cache is still set to INITIAL.
     */
    public final DDS.Topic get_topic(String topic_name) throws DDS.AlreadyDeleted, DDS.PreconditionNotMet {
        return jniGetTopic(topic_name);
    }

    /**
     * Returns the names of all topics that hold the values for all attributes contained in the
     * DLRL class that is managed by this ObjectHome. If the pubsub-state of the Cache is still
     * set to INITIAL, a PreconditionNotMet exception is thrown.
     *
     * @return the names of all topics that hold the values for the attributes of the DLRL class
     * that is managed by this ObjectHome.
     * @throws DDS.AlreadyDeleted if the ObjectHome is considered to be deleted.
     * @throws DDS.PreconditionNotMet is the pubsub_state of the Cache is still set to INITIAL.
     */
    public final String[] get_all_topic_names () throws DDS.AlreadyDeleted, DDS.PreconditionNotMet{
        return jniGetAllTopicNames();
    }

    protected native void jniConstructObjectHome(
        String name,
        String pathPrefixed,
        String pathPrefixedExceptLast,
        String topicTypePrefixed,
        String targetImpleClassName);

    private native DDS.DataReader jniGetDataReader(String topic_name) throws DDS.AlreadyDeleted, DDS.PreconditionNotMet;
    private native DDS.DataWriter jniGetDataWriter(String topic_name) throws DDS.AlreadyDeleted, DDS.PreconditionNotMet;
    private native DDS.Topic jniGetTopic(String topic_name) throws DDS.AlreadyDeleted, DDS.PreconditionNotMet;
    private native String jniName() throws DDS.AlreadyDeleted;
    private native String jniFilter() throws DDS.AlreadyDeleted;
    private native DDS.ObjectHome jniParent() throws DDS.AlreadyDeleted;
    private native DDS.ObjectHome[] jniChildren() throws DDS.AlreadyDeleted;
    private native int jniRegistrationIndex() throws DDS.AlreadyDeleted;
    private native boolean jniAutoDeref() throws DDS.AlreadyDeleted;
    private native void jniSetFilter(String expression)  throws DDS.AlreadyDeleted, DDS.PreconditionNotMet, DDS.SQLError;
    private native void jniSetAutoDeref(boolean value) throws DDS.AlreadyDeleted;
    private native void jniDerefAll() throws DDS.AlreadyDeleted;
    private native void jniUnderefAll() throws DDS.AlreadyDeleted;
    private native String jniGetTopicName(String attribute_name)  throws DDS.AlreadyDeleted;
    private native String[] jniGetAllTopicNames() throws DDS.AlreadyDeleted;
    protected native DDS.ObjectListener[] jniListeners() throws DDS.AlreadyDeleted;
    private native void jniDeleteObjectHome();
    protected native boolean jniAttachListener(DDS.ObjectListener listener, boolean concernsContainedObjects) throws DDS.AlreadyDeleted;
    protected native boolean jniDetachListener(DDS.ObjectListener listener) throws DDS.AlreadyDeleted;

	protected abstract void buildMetaModel() throws DDS.BadHomeDefinition;

	protected abstract int registerType(DDS.DomainParticipant participant, String typeName, String topicName);

    /* topic creation & build up operations */

    protected native void jniCreateMainTopic(String name, String typeName) throws DDS.BadHomeDefinition;

	protected native void jniCreateExtensionTopic(String name, String typeName) throws DDS.BadHomeDefinition;

	protected native void jniCreatePlaceTopic(String name, String typeName) throws DDS.BadHomeDefinition;

	protected native void jniCreateMultiPlaceTopic(String name, String typeName) throws DDS.BadHomeDefinition;

	protected native void jniCreateDCPSField(String name, int fieldType, int type, String owningTopic) throws DDS.BadHomeDefinition;

    /* Meta DLRL class creation operation */
	protected native void jniCreateDLRLClass(String parentName, int mapping) throws DDS.BadHomeDefinition;

    /* Adding (and creation of) DLRL attributes to DLRL class operations */
	protected native void jniCreateAttribute(String name, boolean isImmutable, int type) throws DDS.BadHomeDefinition;

	protected native void jniMapAttributeToDCPSField(String attributeName, String dcpsFieldName) throws DDS.BadHomeDefinition;

	protected native void jniMapAttributeToDCPSTopic(String attributeName, String topicName) throws DDS.BadHomeDefinition;

    /* Adding (and creation of) DLRL multi attributes to DLRL class operations */
	protected native void jniCreateMultiAttribute(String name, boolean isImmutable, int type, int basis) throws DDS.BadHomeDefinition;

	protected native void jniMapMultiAttributeToIndexDCPSField(String attributeName, String indexFieldName) throws DDS.BadHomeDefinition;

    /* single relation operations */
	protected native void jniCreateRelation(boolean isComposition, String name, String typeName,
                                            String associatedRelationName, boolean isOptional) throws DDS.BadHomeDefinition;

	protected native void jniSetRelationTopicPair(String relationName, String ownerTopicName, String targetTopicName) throws DDS.BadHomeDefinition;

    //convenience operation for the jniAddOwnerField & jniAddTargetField operations, as single relation must always have
    //the same number of owner and target fields, unlike multi relations which do not have this requirement
	protected native void jniAddRelationKeyFieldPair(String relationName, String ownerKeyName, String targetKeyName) throws DDS.BadHomeDefinition;

	protected native void jniSetRelationValidityField(String relationName, String validityFieldName) throws DDS.BadHomeDefinition;

    /* multi relation operations */
	protected native void jniCreateMultiRelation( boolean isComposition, String name, String typeName,
                                                String associatedRelationName, int basis) throws DDS.BadHomeDefinition;

    protected native void jniAddOwnerField(String relationName, String fieldName) throws DDS.BadHomeDefinition;
    protected native void jniAddTargetField(String relationName, String fieldName) throws DDS.BadHomeDefinition;
    protected native void jniSetMultiRelationRelationTopic(String relationName, String relationTopicName) throws DDS.BadHomeDefinition;

    protected native void jniAddRelationTopicOwnerField(String relationName, String relationTopicFieldName) throws DDS.BadHomeDefinition;
    protected native void jniAddRelationTopicTargetField(String relationName, String relationTopicFieldName) throws DDS.BadHomeDefinition;
	protected native void jniSetRelationTopicIndexField(String relationName, String relationTopicFieldName) throws DDS.BadHomeDefinition;

	protected native DDS.ObjectRoot[] jniDeletedObjects(DDS.CacheBase source, int kind) throws DDS.AlreadyDeleted;
	protected native DDS.ObjectRoot[] jniModifiedObjects(DDS.CacheBase source, int kind) throws DDS.AlreadyDeleted;
	protected native DDS.ObjectRoot[] jniNewObjects(DDS.CacheBase source, int kind) throws DDS.AlreadyDeleted;
	protected native DDS.ObjectRoot[] jniObjects(DDS.CacheBase source, int kind) throws DDS.AlreadyDeleted;
    protected native DDS.Selection[] jniSelections() throws DDS.AlreadyDeleted;
    protected native void jniCreateSelection(DDS.Selection a_selection, DDS.SelectionCriterion criterion, int kind, boolean auto_refresh, boolean concerns_contained_objects) throws DDS.AlreadyDeleted, DDS.PreconditionNotMet;
    protected native void jniDeleteSelection(DDS.Selection a_selection) throws DDS.AlreadyDeleted, DDS.PreconditionNotMet;
	protected native void jniRegisterObject(DDS.ObjectRoot unregisteredObject) throws DDS.PreconditionNotMet, DDS.AlreadyExisting,
																							DDS.AlreadyDeleted;
	protected native DDS.ObjectRoot jniCreateUnregisteredObject(DDS.CacheAccess access) throws DDS.PreconditionNotMet,
																								DDS.AlreadyDeleted;
	protected native DDS.ObjectRoot jniCreateObject(DDS.CacheAccess access) throws DDS.PreconditionNotMet,
																					DDS.AlreadyDeleted;
    protected native DDS.ObjectRoot jniFindObject(DDS.DLRLOid oid, DDS.CacheBase source) throws DDS.AlreadyDeleted;

    protected final void finalize(){
	    jniDeleteObjectHome();
    }
} // class ObjectHome
