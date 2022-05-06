package device_topoology;

import org.json.JSONException;
import org.json.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.*;
import java.io.*;

public class Api {
    HashMap<String, topology> topologies = new HashMap<String, topology>();

    public void saveTopology(topology topology) throws JSONException {
        JSONObject topologyJsonObject = new JSONObject();
        JSONArray componentsArray = new JSONArray();

        for (component component : topology.getComponents().values()) {
            JSONObject deviceObject = new JSONObject();
            deviceObject.put("type", component.getType());
            deviceObject.put("id", component.getId());
            JSONObject propertyValuesObject = new JSONObject();
            for (String property : component.getPropertyValues().keySet()) {
                propertyValuesObject.put(property, component.getPropertyValues().get(property));
            }
            deviceObject.put(component.getProperty(), propertyValuesObject);
            JSONObject netListObject = new JSONObject();
            for (String net : component.getNetList().keySet()) {
                netListObject.put(net, component.getNetList().get(net));
            }
            deviceObject.put("netlist", netListObject);
            componentsArray.put(deviceObject);
        }

        try (FileWriter file = new FileWriter(topology.getId() + ".json")) {
            topologyJsonObject.put("id", topology.getId());
            topologyJsonObject.put("components", componentsArray);
            file.write(topologyJsonObject.toString());
            file.flush();
        } catch (IOException e) {
            System.out.println("An error occurred, check the file name");
            e.printStackTrace();
        }

    }

    public topology loadTopology(String fileName) {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(fileName)) {
            Object obj = parser.parse(reader);
            org.json.simple.JSONObject topologyJsonObject = (org.json.simple.JSONObject) obj;
            topology topology = new topology(topologyJsonObject.get("id").toString(), new HashMap<String, component>());
            org.json.simple.JSONArray componentsArray = (org.json.simple.JSONArray) topologyJsonObject
                    .get("components");
            componentsArray.forEach(comp -> {
                org.json.simple.JSONObject componentObject = (org.json.simple.JSONObject) comp;
                component component = new component((String) componentObject.get("type"),
                        (String) componentObject.get("id"), "", new HashMap<String, Number>(),
                        new HashMap<String, String>());
                org.json.simple.JSONObject netListObject = (org.json.simple.JSONObject) componentObject.get("netlist");
                netListObject.forEach((k, v) -> {
                    component.getNetList().put((String) k, (String) v);
                });
                Set keys = componentObject.keySet();
                keys.remove("netlist");
                keys.remove("type");
                keys.remove("id");
                String property = (String) keys.iterator().next();
                component.setProperty(property);
                org.json.simple.JSONObject propertyValuesObject = (org.json.simple.JSONObject) componentObject
                        .get(property);
                propertyValuesObject.forEach((k, v) -> {
                    component.getPropertyValues().put((String) k, (Number) v);
                });
                topology.getComponents().put(component.getId(), component);
            });
            topologies.put(topology.getId(), topology);
            return topology;
        } catch (IOException | ParseException e) {
            System.err.println("An error occurred while parsing the file");
            e.printStackTrace();
        }
        return null;
    }

    public Set<String> getTopologies() {
        return topologies.keySet();
    }

    public boolean deleteTopology(String topologyID) {
        if (topologies.containsKey(topologyID)) {
            topologies.remove(topologyID);
            return true;
        } else {
            return false;
        }
    }

    public HashMap<String, component> getComponents(String topologyID) {
        return topologies.get(topologyID).getComponents();
    }

    public HashMap<String, component> getComponentsWithNetlistNode(String topologyID, String node) {
        HashMap<String, component> components = new HashMap<String, component>();
        topologies.get(topologyID).getComponents().forEach((k, v) -> {
            if (v.getNetList().containsValue(node)) {
                components.put(k, v);
            }
        });
        return components;
    }
}
