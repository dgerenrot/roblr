package org.roblr.builder;

public interface ObjectSpec {
    String getId();

    void setId(String id);

    String getObjectClassName();

    void setObjectClassName(String name);

    String getRelatedObjId(String name);

    void setRelatedObjId(String name, String id);

    ObjectSpec getRelatedObjSpec(String name);

    void setRelatedObjSpec(String name, ObjectSpec objectSpec);

    Iterable<String> getRelNames();

    // no deletes?
    String getFieldSpec(String name);

    void setFieldSpec(String name, String spec);

    Object getValue();
}
