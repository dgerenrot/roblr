package org.roblr.builder;

import org.roblr.DefaultRegistryImpl;

public class DefaultObjectSpecRegistryImpl extends DefaultRegistryImpl<ObjectSpec>
        implements ObjectSpecRegistry {
    @Override
    public ObjectSpec get(String  id) {
        if (id == null)
            throw new IllegalArgumentException("Null ids are not allowed");
        return super.get(id);
    }
}
