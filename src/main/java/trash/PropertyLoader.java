package trash;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {

    Properties properties = new Properties();
    InputStream input = null;
    String propertiesFileName;

    public PropertyLoader() {
        this.propertiesFileName = "common-service.properties";
    }

    public String getBaseUrl(){
        return load("baseUrl");
    }

    public String getApiKey(){
        return load("key");
    }

    public String getToken(){
        return load("token");
    }

    public String load(String key){
        String propertyValue = null;
        input = getClass().getClassLoader().getResourceAsStream(propertiesFileName);
        try {
            properties.load(input);
            propertyValue = properties.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            propertyValue.toString();
        } catch (NullPointerException npe) {
            System.out.println(("Could not find property by key \"" + key + "\" in file " + propertiesFileName));
            npe.getMessage();
        }
        return propertyValue;
    }
}