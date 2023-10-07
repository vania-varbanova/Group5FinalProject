package utils;

import testFramework.PropertiesManager;

import java.util.Properties;

public class ConfigPropertiesReader {
    private static final Properties configProperties = PropertiesManager.PropertiesManagerEnum.INSTANCE.getConfigProperties();
    public static String getValueByKey(String key) {
        String value = configProperties.getProperty(key);
        return value != null ? value : key;
    }
}
