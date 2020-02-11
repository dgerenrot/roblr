package org.roblr.builder;

import org.roblr.Roblr;
import org.roblr.classalias.ClassRegistry;

public class RelBuilder {
    private ObjectSpecBuilder from;
    private ObjectSpecBuilder to;
    private ObjectSpecRegistry objectRegistry;
    private ClassRegistry classRegistry;
    private String relName;
    private boolean isBackward;

    public Roblr roblr;

    public RelBuilder(Roblr roblr) {
        this.roblr = roblr;
    }

    public ObjectSpecBuilder set(String id, String classAlias) {
        ObjectSpecBuilder objectSpecBuilder = new ObjectSpecBuilder(roblr);
        objectSpecBuilder.set(id, classAlias);
        if (!isBackward) {
            to = objectSpecBuilder;
        } else {
            from = objectSpecBuilder;
        }

        // TODO : maybe instead check for existing rel-d object and return that
        if (from.getObjectSpec().getRelatedObjId(relName) != null)
            throw new IllegalArgumentException("Relation " + relName +
                            " already exists for " + from.getObjectSpec().getObjectClassName());

        from.getObjectSpec().setRelatedObjId(relName, to.getObjectSpec().getId());

        return objectSpecBuilder;
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

    public ObjectSpecRegistry getObjectRegistry() {
        return objectRegistry;
    }

    public void setObjectRegistry(ObjectSpecRegistry objectRegistry) {
        this.objectRegistry = objectRegistry;
    }

    public ClassRegistry getClassRegistry() {
        return classRegistry;
    }

    public void setClassRegistry(ClassRegistry classRegistry) {
        this.classRegistry = classRegistry;
    }

    public boolean isBackward() {
        return isBackward;
    }

    public void setBackward(boolean reverse) {
        isBackward = reverse;
    }
}
