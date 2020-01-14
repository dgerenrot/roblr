package org.roblr;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class Roblr {
    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    private Config config;

    private String generateId() {
        byte[] bytes = new byte[config.getDefaultIdLengthBytes()];
        Rng.instance().nextBytes(bytes);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(String.format("%02x", bytes[i]));
        };

        return sb.toString();
    }

    public static void main(String[] args) {
        Roblr r = new Roblr();
        r.config = new Config();
        for (int i = 0; i < 10; i++) {
            System.out.println(r.generateId());
        }

    }
}
