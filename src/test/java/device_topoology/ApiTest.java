package device_topoology;

import java.util.HashMap;
import java.util.Set;

import org.json.JSONException;

import static org.junit.jupiter.api.Assertions.*;

public class ApiTest {
    Api api = new Api();
    String testPath = "src\\test\\java\\device_topoology\\";
    @org.junit.jupiter.api.Test
    void testLoadTopology() {
        topology topology = null;
        topology = api.loadTopology(testPath+"topology.json");
        assertNotNull(topology);
        System.out.println(topology);
        assertEquals("topology", topology.getId());
        assertEquals(2, topology.getComponents().size());
        assertEquals("res1", topology.getComponents().get("res1").getId());
        assertEquals("m1", topology.getComponents().get("m1").getId());
        assertEquals(3, topology.getComponents().get("res1").getPropertyValues().size());
        assertEquals(3, topology.getComponents().get("m1").getPropertyValues().size());
        assertEquals(2, topology.getComponents().get("res1").getNetList().size());
        assertEquals(3, topology.getComponents().get("m1").getNetList().size());
        assertEquals(100L, topology.getComponents().get("res1").getPropertyValues().get("default"));
        assertEquals(10L, topology.getComponents().get("res1").getPropertyValues().get("min"));
        assertEquals(1000L, topology.getComponents().get("res1").getPropertyValues().get("max"));
        assertEquals(1.5d, topology.getComponents().get("m1").getPropertyValues().get("default"));
        assertEquals(1L, topology.getComponents().get("m1").getPropertyValues().get("min"));
        assertEquals(2L, topology.getComponents().get("m1").getPropertyValues().get("max"));
        assertEquals("vdd", topology.getComponents().get("res1").getNetList().get("t1"));
        assertEquals("n1", topology.getComponents().get("res1").getNetList().get("t2"));
        assertEquals("n1", topology.getComponents().get("m1").getNetList().get("drain"));
        assertEquals("vin", topology.getComponents().get("m1").getNetList().get("gate"));
        assertEquals("vss", topology.getComponents().get("m1").getNetList().get("source"));
        assertEquals("resistor", topology.getComponents().get("res1").getType());
        assertEquals("nmos", topology.getComponents().get("m1").getType());
    }



    @org.junit.jupiter.api.Test
    void testSaveTopology() throws JSONException {
        topology topology = api.loadTopology(testPath+"topology.json");
        assertNotNull(topology);
        api.saveTopology(topology);
        topology topology_replica = api.loadTopology("topology.json");
        assertNotNull(topology_replica);
        assertEquals(topology.toString(), topology_replica.toString());
    }

    @org.junit.jupiter.api.Test
    void testgetTopologies()
    {
        topology topology = api.loadTopology(testPath+"topology.json");
       assertNotNull(topology);
        Set<String> topologiesIDs = api.getTopologies();
        assertEquals(1, topologiesIDs.size());
        assertTrue(topologiesIDs.contains("topology"));
    }

    @org.junit.jupiter.api.Test
    void testdeleteTopology()
    {
        topology topology = api.loadTopology(testPath+"topology.json");
        topology = api.loadTopology(testPath+"top1.json");
        assertNotNull(topology);
        api.deleteTopology("top1");
        Set<String> topologiesIDs = api.getTopologies();
        assertEquals(1, topologiesIDs.size());
        assertFalse(topologiesIDs.contains("top1"));
        assertTrue(topologiesIDs.contains("topology"));
    }

    @org.junit.jupiter.api.Test
    void testgetComponents()
    {
        topology topology = api.loadTopology(testPath+"topology.json");
        assertNotNull(topology);
        HashMap<String,component> componentsIDs = api.getComponents("topology");
        assertEquals(2, componentsIDs.size());
        assertTrue(componentsIDs.containsKey("res1"));
        assertTrue(componentsIDs.containsKey("m1"));
    }

    @org.junit.jupiter.api.Test
    void testgetComponentsWithNetlistNode()
    {
        topology topology = api.loadTopology(testPath+"topology.json");
        assertNotNull(topology);
        HashMap<String,component> componentsIDs = api.getComponentsWithNetlistNode("topology","n1");
        assertEquals(2, componentsIDs.size());
        assertTrue(componentsIDs.containsKey("res1"));
        assertTrue(componentsIDs.containsKey("m1"));
        componentsIDs = api.getComponentsWithNetlistNode("topology","vss");
        assertEquals(1, componentsIDs.size());
        assertTrue(componentsIDs.containsKey("m1"));
        componentsIDs = api.getComponentsWithNetlistNode("topology","vdd");
        assertEquals(1, componentsIDs.size());
        assertTrue(componentsIDs.containsKey("res1"));
    }

}
