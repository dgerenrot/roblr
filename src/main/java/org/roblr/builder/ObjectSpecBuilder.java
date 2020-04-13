package org.roblr.builder;

import org.roblr.Roblr;

public class ObjectSpecBuilder {

    private Roblr roblr;
    private ObjectSpec objectSpecBuilt;

    public ObjectSpecBuilder(Roblr roblr) {
        this.roblr = roblr;
    }

    public ObjectSpecBuilder set(String classAlias) {
        return set(null, classAlias);
    }

    public ObjectSpecBuilder set(String id, String classAlias) {
        Class clazz = roblr.getClassRegistry().get(classAlias);
        if (clazz == null) {
            throw new IllegalArgumentException("No class for alias " + classAlias);
        }

        objectSpecBuilt = roblr.getObjectSpecRegistry().get(id);

        if (objectSpecBuilt == null) {
            if (id == null) {
                id = roblr.generateId();
            }

            objectSpecBuilt = new DefaultObjectSpecImpl(roblr);
            objectSpecBuilt.setId(id);
            objectSpecBuilt.setObjectClassName(clazz.getName());
            roblr.getObjectSpecRegistry().put(id, objectSpecBuilt);
        }

        return this;
    }

    public ObjectSpec getObjectSpec() {
        return objectSpecBuilt;
    }
    // TODO: add relator??

    // TODO: no type checking at all?
    // TODO: no collections (as in myObj.arrName[0])?
    public RelBuilder rel(String name) {
        RelBuilder retVal = new RelBuilder(roblr);
        retVal.setRelName(name);
        retVal.setFrom(this);
        return retVal;
    }

    public RelBuilder backRel(String name) {
        RelBuilder retVal = new RelBuilder(roblr);
        retVal.setRelName(name);
        retVal.setTo(this);
        retVal.setBackward(true);
        return retVal;
    }
}
