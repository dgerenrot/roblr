package org.roblr.builder;

public interface ObjectRegistry {
    ObjectSpec getById(String id);
    void add(String id, ObjectSpec objectSpec);
}
