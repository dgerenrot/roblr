package org.roblr;


import com.google.gson.Gson;
import org.roblr.builder.ObjectRegistry;
import org.roblr.classalias.ClassRegistry;
import org.roblr.classalias.DefaultClassRegistry;
import org.roblr.classalias.DefaultZeroObjRegistry;

public class Roblr {
    private Config config;
    private ClassRegistry classRegistry;
    private DefaultZeroObjRegistry zeroObjRegistry;
    private ObjectRegistry objectRegistry;

    public Roblr() {
        this.config = new Config();
        this.classRegistry = new DefaultClassRegistry();
        this.zeroObjRegistry = new DefaultZeroObjRegistry();
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public void setClassAlias(String alias, Class<?> clazz) {
        if (classRegistry.get(alias) != null) {
            throw new IllegalArgumentException("Duplicate entry for class " + clazz);
        }

        classRegistry.register(alias, clazz);
    }

    public void setClassAlias(String alias, String clazz) throws ClassNotFoundException {
        if (classRegistry.get(alias) != null) {
            throw new IllegalArgumentException("Duplicate entry for class " + clazz);
        }

        classRegistry.register(alias, clazz);
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

    public ClassRegistry getClassRegistry() {
        return classRegistry;
    }

    public void setClassRegistry(ClassRegistry classRegistry) {
        this.classRegistry = classRegistry;
    }

    public DefaultZeroObjRegistry getZeroObjRegistry() {
        return zeroObjRegistry;
    }

    public void setZeroObjRegistry(DefaultZeroObjRegistry zeroObjRegistry) {
        this.zeroObjRegistry = zeroObjRegistry;
    }

    public ObjectRegistry getObjectRegistry() {
        return objectRegistry;
    }

    public void setObjectRegistry(ObjectRegistry objectRegistry) {
        this.objectRegistry = objectRegistry;
    }
}
