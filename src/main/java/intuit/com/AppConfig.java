package intuit.com;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
    private static final String CONFIG_FILE_NAME = "config.properties";
    private AppConfig(){}
    private static Properties properties;

    private static Properties initialize() throws IOException {
        try(InputStream fileInput = IntuitApp.class.getClassLoader().getResourceAsStream(CONFIG_FILE_NAME)) {
            Properties properties = new Properties();
            properties.load(fileInput);
            return properties;
        }
    }

    public static Properties getProperties() throws IOException {
        if(properties == null){
            properties = initialize();
        }
        return properties;
    }
}
