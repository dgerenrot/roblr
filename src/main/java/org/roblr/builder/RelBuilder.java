package org.roblr.builder;

import org.roblr.classalias.ClassRegistry;

public class RelBuilder {
    private ObjectSpecBuilder from;
    private ObjectSpecBuilder to;
    private ObjectRegistry objectRegistry;
    private ClassRegistry classRegistry;
    private String relName;
    private boolean isReverse;

    public ObjectSpecBuilder set(String id, String classAlias) {
        return isReverse ? from : to;
    }

    public ObjectSpecBuilder getFrom() {
        return from;
    }

    public void setFrom(ObjectSpecBuilder from) {
        this.from = from;
    }

    public ObjectSpecBuilder getTo() {
        return to;
    }

    public void setTo(ObjectSpecBuilder to) {
        this.to = to;
    }

    public String getRelName() {
        return relName;
    }

    public void setRelName(String relName) {
        this.relName = relName;
    }

    public ObjectRegistry getObjectRegistry() {
        return objectRegistry;
    }

    public void setObjectRegistry(ObjectRegistry objectRegistry) {
        this.objectRegistry = objectRegistry;
    }

    public ClassRegistry getClassRegistry() {
        return classRegistry;
    }

    public void setClassRegistry(ClassRegistry classRegistry) {
        this.classRegistry = classRegistry;
    }

    public boolean isReverse() {
        return isReverse;
    }

    public void setReverse(boolean reverse) {
        isReverse = reverse;
    }
}
