package device_topoology;

import java.util.HashMap;

public final class topology {
    private final String id;
    private final HashMap<String, component> components;

    public topology(String id, HashMap<String, component> components) {
        this.id = id;
        this.components = components;
    }
    
    public String getId() {
        return id;
    }

    public HashMap<String, component> getComponents() {
        return components;
    }

    @Override
    public String toString() {
        return "topology [id= " + id + ", \nComponents=\n" + components + "]";
    }

}
