package org.roblr;


import com.google.gson.Gson;
import org.roblr.builder.ObjectSpecRegistry;
import org.roblr.classalias.ClassRegistry;
import org.roblr.classalias.DefaultClassRegistryImpl;
import org.roblr.classalias.DefaultZeroObjects;

public class Roblr {
    private Config config;
    private ClassRegistry classRegistry;
    private DefaultZeroObjects zeroObjRegistry;
    private ObjectSpecRegistry objectRegistry;

    public Roblr() {
        this.config = new Config();
        this.classRegistry = new DefaultClassRegistryImpl();
        this.zeroObjRegistry = new DefaultZeroObjects();
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

    public ObjectSpecRegistry getObjectRegistry() {
        return objectRegistry;
    }

    public void setObjectRegistry(ObjectSpecRegistry objectRegistry) {
        this.objectRegistry = objectRegistry;
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
