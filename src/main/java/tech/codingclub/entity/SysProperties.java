package tech.codingclub.entity;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;

public class SysProperties {
    private static Properties properties = null;

    private SysProperties() {
    }

    public static Properties getInstance() {
        if (properties == null) {
            ClassLoader loader = SysProperties.class.getClassLoader();
            if (loader == null) {
                loader = ClassLoader.getSystemClassLoader();
            }
            String propFile = "application.properties";
            URL url = loader.getResource(propFile);
            properties = new Properties();
            try {
                properties.load(Objects.requireNonNull(url).openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties;
    }

    public static String getPropertyValue(String key) {
        return SysProperties.getInstance().getProperty(key);
    }

    public static void main(String[] args) {
        System.out.println(getPropertyValue("DB_USERNAME"));
        System.out.println(getPropertyValue("DB_PASSWORD"));

    }
}
