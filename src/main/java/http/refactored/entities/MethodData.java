package http.refactored.entities;

import com.epam.commons.DataClass;

public class MethodData extends DataClass {
    private String resourceUrl = "";
    public String getResourceUrl() {
        return resourceUrl;
    }
    public MethodData(String resourceUrl){
        this.resourceUrl = resourceUrl;
    }
}
