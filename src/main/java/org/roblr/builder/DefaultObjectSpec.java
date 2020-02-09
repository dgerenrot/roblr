package org.roblr.builder;

import org.roblr.Roblr;

import java.util.HashMap;
import java.util.Map;

public class DefaultObjectSpec implements ObjectSpec {

    private String id;
    private String className;
    private Class clazz;
    private Map<String, String> relAliases;
    private Map<String, String> fieldsSpecs;
    private Object value;
    private Roblr roblr;

    public DefaultObjectSpec(Roblr roblr) {
        this();

        this.roblr = roblr;
    }

    public DefaultObjectSpec() {
        relAliases = new HashMap<>();
        fieldsSpecs = new HashMap<>();
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getObjectClassName() {
        return className;
    }

    public void setObjectClassName(String className) {
        this.className = className;
    }

    @Override
    public String getRelatedObjId(String name) {
        return relAliases.get(name);
    }

    @Override
    public ObjectSpec getRelatedObj(String name) {
        String id = relAliases.get(name);
        return roblr.getObjectRegistry().getById(id);
    }

    public void setRelation(String name, String id) {
        relAliases.put(name, id);
    }

    public void setRelation(String name, ObjectSpec objectSpec) {
        relAliases.put(name, objectSpec.getId());
    }

    @Override
    public String getFieldSpec(String name) {
        return fieldsSpecs.get(name);
    }

    @Override
    public void setFieldSpec(String name, String spec) {
        fieldsSpecs.put(name, spec);
    }

    @Override
    public Object getValue() {
        return value;
    }

    public Roblr getRoblr() {
        return roblr;
    }

    public void setRoblr(Roblr roblr) {
        this.roblr = roblr;
    }

}
