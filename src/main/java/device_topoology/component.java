package device_topoology;

import java.util.HashMap;

public class component {
    private String type;
    private String id;
    private String property;
    HashMap<String, Number> propertyValues = new HashMap<String, Number>();
    HashMap<String, String> netList = new HashMap<String, String>();

    public component(String type, String id, String property,HashMap<String, Number> propertyValues,HashMap<String, String> netList) {
        this.type = type;
        this.id = id;
        this.property = property;
        this.propertyValues = propertyValues;
        this.netList = netList;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getProperty() {
        return property;
    }

    public HashMap<String, Number> getPropertyValues() {
        return propertyValues;
    }

    public HashMap<String, String> getNetList() {
        return netList;
    }

    public void setPropertyValues(HashMap<String, Number> propertyValues) {
        this.propertyValues = propertyValues;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNetList(HashMap<String, String> netList) {
        this.netList = netList;
    }

    @Override
    public String toString() {
        return "component{" + "type= " + type + ", id= " + id + ", property= " + property + ", propertyValues= " + propertyValues + ", netList= " + netList + '}';
    }
}
