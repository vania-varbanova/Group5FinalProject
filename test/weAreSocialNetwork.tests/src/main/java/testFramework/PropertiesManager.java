package testFramework;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
public class PropertiesManager {

    public enum PropertiesManagerEnum {

        INSTANCE;
        private static final String UI_MAP = "src/main/resources/mappings/ui_map.properties";
        private static final String CONFIG_PROPERTIES = "src/main/resources/config.properties";

        public Properties getConfigProperties() {
            return loadProperties(CONFIG_PROPERTIES);
        }

        public Properties getUiMappings() {
            return loadProperties(UI_MAP);
        }

        private static Properties loadProperties(String url) {
            Properties properties = new Properties();

            try {
                properties.load(Files.newInputStream(Paths.get(url)));
            } catch (IOException ex) {
                Utils.LOGGER.error("Loading properties failed!");
            }

            return properties;
        }
    }
}
