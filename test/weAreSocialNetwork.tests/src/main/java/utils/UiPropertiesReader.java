package utils;

import testFramework.PropertiesManager;

import java.util.Properties;

public class UiPropertiesReader {
    private static final Properties configProperties = PropertiesManager.PropertiesManagerEnum.INSTANCE.getUiMappings();
    public static String getValueByKey(String key) {
        String value = configProperties.getProperty(key);
        return value != null ? value : key;
    }
}
