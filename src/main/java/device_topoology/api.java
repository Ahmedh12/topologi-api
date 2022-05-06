package device_topoology;

import org.json.*;
import java.util.*;
import java.io.*;

public class api {
    List<topology> topologies = new ArrayList<topology>();

    public void saveTopology(topology topology, String fileName) throws JSONException {
        JSONObject topologyJsonObject = new JSONObject();
        JSONArray  devicesArray = new JSONArray();
        JSONObject devicesObject = new JSONObject();

        topologyJsonObject.put("id", topology.getId());

    }

    
}
