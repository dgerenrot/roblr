package org.roblr.builder;

public interface ObjectSpec {
    String getId();
    void setId(String id);

    String getObjectClassName();
    void setObjectClassName(String name);

    String getRelatedObjId(String name);
    ObjectSpec getRelatedObjSpec(String name);
    void setRelatedObjId(String name, String id);
    void setRelatedObjSpec(String name, ObjectSpec objectSpec);
    // no deletes?
    String getFieldSpec(String name);
    void setFieldSpec(String name, String spec);

    Object getValue();
}
