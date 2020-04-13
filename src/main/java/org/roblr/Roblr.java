package org.roblr;


import com.google.gson.Gson;
import org.roblr.builder.*;
import org.roblr.classalias.ClassRegistry;
import org.roblr.classalias.DefaultClassRegistryImpl;
import org.roblr.classalias.DefaultZeroObjects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Predicate;

public class Roblr {

    private static final Logger logger = LoggerFactory.getLogger(Roblr.class);

    public static final int CALL = 0;
    public static final int RET = 1;

    private Config config;
    private ClassRegistry classRegistry;
    private DefaultZeroObjects zeroObjRegistry;
    private ObjectSpecRegistry objectSpecRegistry;

    private ObjectRegistry objectRegistry;

    public Roblr() {
        this.config = new Config();
        this.classRegistry = new DefaultClassRegistryImpl();
        this.zeroObjRegistry = new DefaultZeroObjects();
        this.objectRegistry = new DefaultObjectRegistryImpl();
        this.objectSpecRegistry = new DefaultObjectSpecRegistryImpl();
    }

    public void buildAll() {
        // TODO
    }

    public Object buildFromSpec(String id) throws ReflectiveOperationException {
        ObjectSpec root = objectSpecRegistry.get(id);
        if (root == null) {
            throw new IllegalArgumentException("No object spec exists for id " + id);
        }
        int dir = CALL;
        Deque<ObjectSpec> objSpecStack = new LinkedList<>();
        Deque<Iterator<String>> relIterStack = new LinkedList<>();

        objSpecStack.push(root);
        relIterStack.push(root.getRelNames().iterator());

        Set<String> done = new HashSet<>();

        while (!objSpecStack.isEmpty()) {
            if (dir == CALL) {
                ObjectSpec curr = objSpecStack.peek();
                done.add(curr.getId());

                if (relIterStack.peek().hasNext()) {
                    String relName = relIterStack.peek().next();
                    String newId = curr.getRelatedObjId(relName);
                    //TODO: if newId null -- IllegalStateException!

                    if (done.contains(newId))
                        continue; // TODO: add todo instr

                    objSpecStack.push(objectSpecRegistry.get(newId));
                    relIterStack.push(objectSpecRegistry.get(newId).getRelNames().iterator());
                } else {
                    buildSingleConnectRels(curr);
                    dir = RET;
                }
            } else {
                objSpecStack.pop();
                relIterStack.pop();
                dir = CALL;
            }
        }

        return objectRegistry.get(id); // TODO
    }

    private void buildSingleConnectRels(ObjectSpec curr) throws ReflectiveOperationException {

        Object currObj = buildSingle(curr);
        Class clazz = currObj.getClass();

        objectRegistry.put(curr.getId(), currObj);

        for (String relName : curr.getRelNames()) {
            String relId = curr.getRelatedObjId(relName);
            Object relObj = objectRegistry.get(relId);
            if (relObj == null)
                continue;

            String setterName = "set" + Character.toUpperCase(relName.charAt(0)) + relName.substring(1);

            Method setter = clazz.getDeclaredMethod(setterName, relObj.getClass());
            if (setter == null) {
                Arrays.stream(clazz.getDeclaredMethods()).filter(Predicate.isEqual(setterName))
                        .findFirst().orElse(null);
            }

            if (setter != null) {
                setter.invoke(currObj, relObj);
            } else {
                clazz.getField(relName).set(currObj, relObj);
                logger.debug("Setter not found for class {}: {}", clazz.getName(), setterName);
            }
        }
    }

    private Object buildSingle(ObjectSpec curr) throws ReflectiveOperationException {
        Class clazz = Class.forName(curr.getObjectClassName());
        return clazz.getConstructor().newInstance();
    }

    public void setClassAlias(String alias, Class<?> clazz) {
        if (classRegistry.get(alias) != null) {
            throw new IllegalArgumentException("Duplicate entry for class " + clazz);
        }

        classRegistry.put(alias, clazz);
    }

    public void setClassAlias(String alias, String clazz) throws ClassNotFoundException {
        if (classRegistry.get(alias) != null) {
            throw new IllegalArgumentException("Duplicate entry for class " + clazz);
        }

        classRegistry.putClassName(alias, clazz);
    }

    public String generateId() {
        byte[] bytes = new byte[config.getDefaultIdLengthBytes()];
        Rng.instance().nextBytes(bytes);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(String.format("%02x", bytes[i]));
        }

        return sb.toString();
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public ClassRegistry getClassRegistry() {
        return classRegistry;
    }

    public void setClassRegistry(ClassRegistry classRegistry) {
        this.classRegistry = classRegistry;
    }

    public DefaultZeroObjects getZeroObjRegistry() {
        return zeroObjRegistry;
    }

    public void setZeroObjRegistry(DefaultZeroObjects zeroObjRegistry) {
        this.zeroObjRegistry = zeroObjRegistry;
    }

    public ObjectRegistry getObjectRegistry() {
        return objectRegistry;
    }

    public void setObjectRegistry(ObjectRegistry objectRegistry) {
        this.objectRegistry = objectRegistry;
    }

    public ObjectSpecRegistry getObjectSpecRegistry() {
        return objectSpecRegistry;
    }

    public void setObjectSpecRegistry(ObjectSpecRegistry objectSpecRegistry) {
        this.objectSpecRegistry = objectSpecRegistry;
    }

    // TODO: just for testing. Remove.
    public static void main(String[] args) {
        Gson g = new Gson();
        Roblr r = new Roblr();
        System.out.println(g.toJson(r));
        Config config = g.fromJson("{  'fieldAlwaysBeatsClass': 'FIELD_WINS' }", Config.class);
        r.config = config;
        for (int i = 0; i < 10; i++) {
            System.out.println(r.generateId());
        }
    }
}
