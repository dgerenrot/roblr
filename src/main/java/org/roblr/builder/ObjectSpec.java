package org.roblr.builder;

public interface ObjectSpec {
    String getId();
    void setId(String id);

    String getObjectClassName();
    void setObjectClassName(String name);

    String getRelatedObjId(String name);
    ObjectSpec getRelatedObj(String name);
    void setRelation(String name, String id);
    void setRelation(String name, ObjectSpec objectSpec);

    String getFieldSpec(String name);
    void setFieldSpec(String name, String spec);

    Object getValue();
}
