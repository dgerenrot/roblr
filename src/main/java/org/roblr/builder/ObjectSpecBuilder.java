package org.roblr.builder;

import org.roblr.Roblr;

public class ObjectSpecBuilder {

    private Roblr roblr;
    private ObjectSpec objectSpecBuilt;

    public ObjectSpecBuilder(Roblr roblr) {
        this.roblr = roblr;
    }

    public ObjectSpecBuilder make(String classAlias) {
        return make(null, classAlias);
    }
    public ObjectSpecBuilder make(String id, String classAlias) {
        Class clazz = roblr.getClassRegistry().get(classAlias);
        if (clazz == null) {
            throw new IllegalArgumentException("No class for alias " + classAlias);
        }

        if (id == null) {
            id = roblr.generateId();
        }


        ObjectSpec objSpec = new DefaultObjectSpec(roblr);
        objSpec.setId(id);
        objSpec.setObjectClassName(clazz.getName());
        roblr.getObjectRegistry().add(id, objSpec);
        return this;
    }
    // TODO: add relator??
}
