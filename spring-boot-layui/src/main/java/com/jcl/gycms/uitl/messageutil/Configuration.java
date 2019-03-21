package com.jcl.gycms.uitl.messageutil;

import java.util.Hashtable;
import java.util.ResourceBundle;

public class Configuration {

    private static Hashtable<String, Configuration> table = new Hashtable<String, Configuration>();
    private ResourceBundle rb = null;
    private volatile static Configuration instance = null;

    private Configuration(String configFile) {
        rb = ResourceBundle.getBundle(configFile);
    }

    public static Configuration getInstance(String configFile) {
        if (!table.containsKey(configFile)) {
            instance = new Configuration(configFile);
            table.put(configFile, instance);
            return instance;
        } else {
            return table.get(configFile);
        }
    }

    public String getValue(String key) {
        return (rb.getString(key));
    }
}

