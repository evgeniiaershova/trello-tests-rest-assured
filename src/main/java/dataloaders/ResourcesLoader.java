package dataloaders;

import dataloaders.PropertyLoader;

public class ResourcesLoader {

    PropertyLoader propertyLoader;

    public ResourcesLoader() {
        propertyLoader = new PropertyLoader("recources-list.properties");
    }


    public String boards() {
        return propertyLoader.load("boards");
    }


}
