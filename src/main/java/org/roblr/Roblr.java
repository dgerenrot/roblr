package org.roblr;


import com.google.gson.Gson;
import org.roblr.builder.DefaultObjectRegistryImpl;
import org.roblr.builder.ObjectRegistry;
import org.roblr.builder.ObjectSpec;
import org.roblr.builder.ObjectSpecRegistry;
import org.roblr.classalias.ClassRegistry;
import org.roblr.classalias.DefaultClassRegistryImpl;
import org.roblr.classalias.DefaultZeroObjects;

import java.util.*;

public class Roblr {
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
    }

    public Object buildFromSpec(String id) {
        ObjectSpec root = objectSpecRegistry.get(id);
        if (root == null) {
            throw new IllegalArgumentException("No object spec exists for id " + id);
        }
        int dir = CALL;
        Deque<ObjectSpec> objSpecStack = new LinkedList<>();
        Deque<Iterator<String>> relIterStack = new LinkedList<>();
        objSpecStack.push(root);

        relIterStack.push(root.getRelNames().iterator());

        String currRel;
        Set<String> done = new HashSet<>();

        while (!objSpecStack.isEmpty()) {
            if (dir == CALL) {

                ObjectSpec curr = objSpecStack.peekFirst();
                done.add(curr.getId());

                if (relIterStack.peek().hasNext()) {
                    String relName = relIterStack.peek().next();
                    String newId = curr.getRelatedObjId(relName);
                    if (done.contains(newId))
                        continue;
                }
            }
        }

        return null; // TODO
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

    public ObjectSpecRegistry getObjectSpecRegistry() {
        return objectSpecRegistry;
    }

    public void setObjectSpecRegistry(ObjectSpecRegistry objectSpecRegistry) {
        this.objectSpecRegistry = objectSpecRegistry;
    }

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
