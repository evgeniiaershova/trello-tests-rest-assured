package dataloaders;

import com.epam.commons.DataClass;

public class MethodData extends DataClass {
    private String resource;

    public String getResource() {
        return resource;
    }

    public MethodData(String resource) {
        this.resource = resource;
    }
}
