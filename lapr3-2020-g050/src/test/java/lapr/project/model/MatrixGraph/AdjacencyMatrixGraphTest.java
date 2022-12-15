
package lapr.project.model.MatrixGraph;

import java.util.Iterator;

import lapr.project.model.MatrixGraph.AdjacencyMatrixGraph;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Unit tests class
 * for AdjacencyMatrixGraph
 *
 * @author DEI_ESINF
 * 
 */
public class AdjacencyMatrixGraphTest {

    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }	
	
    @Test
    public void testNumVertices() {

		System.out.println("Test of numVertices");
		AdjacencyMatrixGraph<String, Integer> instance;
		instance = new AdjacencyMatrixGraph<>();
		assertTrue((instance.numVertices()==0), "result should be zero");
		instance.insertVertex("Vert 1");
		assertTrue((instance.numVertices()==1), "result should be one");
		instance.insertVertex("Vert 2");
		assertTrue((instance.numVertices()==2), "result should be two");
		instance.removeVertex("Vert 1");
		assertTrue((instance.numVertices()==1), "result should be one");
		instance.removeVertex("Vert 2");
		assertTrue((instance.numVertices()==0), "result should be zero");
    }

    @Test
    public void testNumEdges() {
		System.out.println("Test of numEdges");
		AdjacencyMatrixGraph<String, Integer> instance = new AdjacencyMatrixGraph<>();
		
		assertTrue((instance.numEdges()==0), "result should be zero");
	
		for(int i = 1 ; i < 5; i++)
			instance.insertVertex("Vert "+i);
		
		instance.insertEdge("Vert 3",  "Vert 2", 12);
		assertTrue((instance.numEdges()==1), "result should be one");
		
		instance.insertEdge("Vert 1",  "Vert 4", 11);
		assertTrue((instance.numEdges()==2), "result should be two");
		
		instance.removeEdge("Vert 2", "Vert 3");
		assertTrue((instance.numEdges()==1), "result should be one");
		
		instance.removeEdge("Vert 4", "Vert 1");
		assertTrue((instance.numEdges()==0), "result should be zero");
    }

    @Test
    public void testVertices() {
		System.out.println("Test of vertices getter");

		AdjacencyMatrixGraph<String, Integer> instance = new AdjacencyMatrixGraph<>();
		
		Iterator<String> itVert = instance.vertices().iterator();
		
		assertTrue((!itVert.hasNext()), "vertices should be empty");

		instance.insertVertex("Vert 1");
		instance.insertVertex("Vert 2");
		
		itVert = instance.vertices().iterator();
				
		assertTrue((itVert.next().compareTo("Vert 1")==0), "first vertice should be \"Vert 1\"");
		assertTrue((itVert.next().compareTo("Vert 2")==0), "second vertice should be \"Vert 2\"");

		instance.removeVertex("Vert 1");
		
		itVert = instance.vertices().iterator();
		assertTrue((itVert.next().compareTo("Vert 2")==0), "first vertice should now be \"Vert 2\"");
		
		instance.removeVertex("Vert 2");
		
		itVert = instance.vertices().iterator();
		assertTrue((!itVert.hasNext()), "vertices should now be empty");

		instance.insertVertex("Vert 1");
	
		itVert = instance.vertices().iterator();

		instance.removeVertex("Vert 1");

		assertTrue((itVert.next().compareTo("Vert 1")==0), "iterator should still give \"Vert 1\" (no change in cloned obj)");
		assertTrue((!instance.vertices().iterator().hasNext()), "instance vertices should be empty");
	
    }

    @Test
    public void testEdges() {
		System.out.println("Test of Edges getter");

		AdjacencyMatrixGraph<String, String> instance = new AdjacencyMatrixGraph<>();
		
		Iterator<String> itEdge = instance.edges().iterator();
		
		assertTrue((!itEdge.hasNext()), "edges should be empty");

		for(int i = 1 ; i < 5; i++)
			instance.insertVertex("Vert "+i);

		instance.insertEdge("Vert 1", "Vert 2", "Edge 1");
		instance.insertEdge("Vert 2", "Vert 4", "Edge 2");
		instance.insertEdge("Vert 1", "Vert 3", "Edge 3");
		
		itEdge = instance.edges().iterator();
				
		assertTrue((itEdge.next().compareTo("Edge 1")==0), "first edge should be \"Edge 1\"");
		assertTrue((itEdge.next().compareTo("Edge 3")==0), "second edge should be \"Edge 3\"");
		assertTrue((itEdge.next().compareTo("Edge 2")==0), "third edge should be \"Edge 2\"");

		instance.removeEdge("Vert 1", "Vert 2");
		
		itEdge = instance.edges().iterator();
		assertTrue((itEdge.next().compareTo("Edge 3")==0), "first edge should now be \"Edge 3\"");
		
		instance.removeEdge("Vert 1", "Vert 3");
		instance.removeEdge("Vert 2", "Vert 4");
		
		itEdge = instance.edges().iterator();
		assertTrue((!itEdge.hasNext()), "vertices should now be empty");
    }

    @Test   
    public void testOutDegree() {
		System.out.println("Test of Out degree");

		AdjacencyMatrixGraph<String, String> instance = new AdjacencyMatrixGraph<>();
		
		for(int i = 1 ; i <= 5; i++)
			instance.insertVertex("Vert "+i);
		
		assertTrue((instance.outDegree("Vert 2")==0), "out degree should be zero");

		instance.insertEdge("Vert 1", "Vert 2", "Edge 1");
		instance.insertEdge("Vert 2", "Vert 4", "Edge 2");
		instance.insertEdge("Vert 1", "Vert 3", "Edge 3");
		instance.insertEdge("Vert 2", "Vert 3", "Edge 4");
		instance.insertEdge("Vert 1", "Vert 5", "Edge 5");
		
		assertTrue((instance.outDegree("Vert 2")==2), "out degree should be 2");

		instance.removeEdge("Vert 1", "Vert 2");
		
		assertTrue((instance.outDegree("Vert 2")==2), "out degree should be 2");

		instance.removeEdge("Vert 2", "Vert 4");
		instance.removeEdge("Vert 2", "Vert 3");
		assertTrue((instance.outDegree("Vert 2")==0), "out degree should be zero");
    }

    @Test
    public void testInDegree() {
		System.out.println("Test of In degree");

		AdjacencyMatrixGraph<String, String> instance = new AdjacencyMatrixGraph<>();
		
		for(int i = 1 ; i <= 5; i++)
			instance.insertVertex("Vert "+i);
		
		assertTrue((instance.inDegree("Vert 3")==0), "in degree should be zero");

		instance.insertEdge("Vert 1", "Vert 2", "Edge 1");
		instance.insertEdge("Vert 2", "Vert 4", "Edge 2");
		instance.insertEdge("Vert 1", "Vert 3", "Edge 3");
		instance.insertEdge("Vert 2", "Vert 3", "Edge 4");
		instance.insertEdge("Vert 3", "Vert 5", "Edge 5");
		
		assertTrue((instance.inDegree("Vert 3")==1), "in degree should be 1");

		instance.removeEdge("Vert 1", "Vert 3");
		
		assertTrue((instance.inDegree("Vert 3")==1), "in degree should be 1");

		instance.removeEdge("Vert 3", "Vert 5");
		instance.removeEdge("Vert 2", "Vert 3");
		assertTrue((instance.outDegree("Vert 3")==0), "in degree should be zero");
		
    }

    @Test
	public void testDirectConnections() {
            System.out.println("Test of Direct Connections");

            AdjacencyMatrixGraph<String, String> instance = new AdjacencyMatrixGraph<>();
		
            for (int i = 1 ; i <= 5; i++)
		instance.insertVertex("Vert "+i);
		
            Iterator<String> itVertex = instance.directConnections("Vert 2").iterator();
		
            assertTrue((!itVertex.hasNext()), "vertices should be empty");

            instance.insertEdge("Vert 1", "Vert 2", "Edge 1");
            instance.insertEdge("Vert 2", "Vert 4", "Edge 2");
            instance.insertEdge("Vert 1", "Vert 3", "Edge 3");
            instance.insertEdge("Vert 2", "Vert 3", "Edge 4");
            instance.insertEdge("Vert 1", "Vert 5", "Edge 5");
		
            itVertex = instance.directConnections("Vert 1").iterator();
				
            assertTrue((itVertex.next().compareTo("Vert 2")==0), "first vertex should be \"Vert 2\"");
            assertTrue((itVertex.next().compareTo("Vert 3")==0), "second vertex should be \"Vert 3\"");
            assertTrue((itVertex.next().compareTo("Vert 5")==0), "third vertex should be \"Vert 5\"");

            instance.removeEdge("Vert 1", "Vert 2");
		
            itVertex = instance.directConnections("Vert 1").iterator();
            assertTrue((itVertex.next().compareTo("Vert 3")==0), "first vertex should now be \"Vert 3\"");
		
            instance.removeEdge("Vert 1", "Vert 3");
            instance.removeEdge("Vert 1", "Vert 5");
		
            itVertex = instance.directConnections("Vert 1").iterator();
            assertTrue((!itVertex.hasNext()), "vertices should now be empty");

    }

	
    @Test
	public void testOutgoingEdges() {
		System.out.println("Test of Outgoing Edges");

		AdjacencyMatrixGraph<String, String> instance = new AdjacencyMatrixGraph<>();
		
		for(int i = 1 ; i <= 5; i++)
			instance.insertVertex("Vert "+i);
		
		Iterator<String> itEdge = instance.outgoingEdges("Vert 2").iterator();
		
		assertTrue((!itEdge.hasNext()), "edges should be empty");

		instance.insertEdge("Vert 1", "Vert 2", "Edge 1");
		instance.insertEdge("Vert 2", "Vert 4", "Edge 2");
		instance.insertEdge("Vert 1", "Vert 3", "Edge 3");
		instance.insertEdge("Vert 2", "Vert 3", "Edge 4");
		instance.insertEdge("Vert 1", "Vert 5", "Edge 5");
		
		itEdge = instance.outgoingEdges("Vert 1").iterator();
				
		assertTrue((itEdge.next().compareTo("Edge 1")==0), "first edge should be \"Edge 1\"");
		assertTrue((itEdge.next().compareTo("Edge 3")==0), "second edge should be \"Edge 3\"");
		assertTrue((itEdge.next().compareTo("Edge 5")==0), "third edge should be \"Edge 5\"");

		instance.removeEdge("Vert 1", "Vert 2");
		
		itEdge = instance.outgoingEdges("Vert 1").iterator();
		assertTrue((itEdge.next().compareTo("Edge 3")==0), "first edge should now be \"Edge 3\"");
		
		instance.removeEdge("Vert 1", "Vert 3");
		instance.removeEdge("Vert 1", "Vert 5");
		
		itEdge = instance.outgoingEdges("Vert 1").iterator();
		assertTrue((!itEdge.hasNext()), "edges should now be empty");
    }

    @Test
	public void testIncomingEdges() {
            System.out.println("Test of Incoming Edges");

            AdjacencyMatrixGraph<String, String> instance = new AdjacencyMatrixGraph<>();

            for(int i = 1 ; i <= 5; i++)
                    instance.insertVertex("Vert "+i);

            Iterator<String> itEdge = instance.incomingEdges("Vert 2").iterator();

            assertTrue((!itEdge.hasNext()), "edges should be empty");

            instance.insertEdge("Vert 1", "Vert 2", "Edge 1");
            instance.insertEdge("Vert 2", "Vert 4", "Edge 2");
            instance.insertEdge("Vert 1", "Vert 5", "Edge 3");
            instance.insertEdge("Vert 2", "Vert 3", "Edge 4");
            instance.insertEdge("Vert 1", "Vert 3", "Edge 5");

            itEdge = instance.incomingEdges("Vert 1").iterator();

            assertTrue((itEdge.next().compareTo("Edge 1")==0), "first edge should be \"Edge 1\"");
            assertTrue((itEdge.next().compareTo("Edge 5")==0), "second edge should be \"Edge 5\"");
            assertTrue((itEdge.next().compareTo("Edge 3")==0), "third edge should be \"Edge 3\"");

            instance.removeEdge("Vert 1", "Vert 2");

            itEdge = instance.incomingEdges("Vert 1").iterator();
            assertTrue((itEdge.next().compareTo("Edge 5")==0), "first edge should now be \"Edge 5\"");

            instance.removeEdge("Vert 1", "Vert 3");
            instance.removeEdge("Vert 1", "Vert 5");

            itEdge = instance.incomingEdges("Vert 1").iterator();
            assertTrue((!itEdge.hasNext()), "vertices should now be empty");
	}

    @Test
	public void testGetEdge() {
		System.out.println("Test of Get Edge");

		AdjacencyMatrixGraph<String, String> instance = new AdjacencyMatrixGraph<>();
		
		for(int i = 1 ; i <= 5; i++)
			instance.insertVertex("Vert "+i);
		
		instance.insertEdge("Vert 1", "Vert 2", "Edge 1");
		instance.insertEdge("Vert 2", "Vert 4", "Edge 2");
		instance.insertEdge("Vert 1", "Vert 5", "Edge 3");
		instance.insertEdge("Vert 2", "Vert 3", "Edge 4");
		instance.insertEdge("Vert 1", "Vert 3", "Edge 5");

		assertNull( instance.getEdge("Vert 2", "Vert 5"), "edge should be null");

		assertEquals(0, instance.getEdge("Vert 2", "Vert 4").compareTo("Edge 2"), "edge should be \"Edge 2\"");

		instance.removeEdge("Vert 2", "Vert 4");

		assertNull( instance.getEdge("Vert 2", "Vert 5"), "edge should be null");
		instance.insertEdge("Vert 2", "Vert 4", "Edge 6");
		assertEquals( 0, instance.getEdge("Vert 2", "Vert 4").compareTo("Edge 6"), "edge should be \"Edge 6\"");
    }

	@Test
    public void testEndVertices() {
		System.out.println("Test of end vertices");

		AdjacencyMatrixGraph<String, String> instance = new AdjacencyMatrixGraph<>();
		
		for(int i = 1 ; i <= 5; i++)
			instance.insertVertex("Vert "+i);
		
		instance.insertEdge("Vert 1", "Vert 2", "Edge 1");
		instance.insertEdge("Vert 2", "Vert 1", "Edge 1");

		instance.insertEdge("Vert 2", "Vert 4", "Edge 2");
		instance.insertEdge("Vert 4", "Vert 2", "Edge 2");

		instance.insertEdge("Vert 1", "Vert 5", "Edge 3");
		instance.insertEdge("Vert 5", "Vert 1", "Edge 3");

		instance.insertEdge("Vert 2", "Vert 3", "Edge 4");
		instance.insertEdge("Vert 3", "Vert 2", "Edge 4");

		instance.insertEdge("Vert 3", "Vert 1", "Edge 5");
		instance.insertEdge("Vert 1", "Vert 3", "Edge 5");

		Object [] endVertices = instance.endVertices("Edge 6");

		assertNull(endVertices, "endVertices should be null");
		
		endVertices = instance.endVertices("Edge 5");
				
		String v1 = (String) endVertices[0];
		String v2 = (String) endVertices[1];

		assertEquals(0, v1.compareTo("Vert 1"), "first vertex should be \"Vert 1\"");
		assertEquals( 0, v2.compareTo("Vert 3"),"second vertex should be \"Vert 3\"");
    }

    @Test
    public void testInsertEdge() {
		System.out.println("Test of insert edge");

		AdjacencyMatrixGraph<String, String> instance = new AdjacencyMatrixGraph<>();
		
		for(int i = 1 ; i < 5; i++)
			instance.insertVertex("Vert "+i);
		
		assertTrue((instance.numEdges()==0), "result should be zero");
		
		instance.insertEdge("Vert 1", "Vert 2", "Edge 1");
		assertTrue((instance.numEdges()==1), "result should be one");
		
		instance.insertEdge("Vert 1", "Vert 3", "Edge 2");
		assertTrue((instance.numEdges()==2), "result should be two");

		instance.removeEdge("Vert 1",  "Vert 3");
		assertTrue((instance.numEdges()==1), "result should be one");
		
		instance.insertEdge("Vert 2", "Vert 4", "Edge 3");
		assertTrue((instance.numEdges()==2), "result should be two");
		
		Iterator <String> itEdge = instance.edges().iterator();
				
		assertTrue((itEdge.next().compareTo("Edge 1")==0), "first edge should be \"Edge 1\"");
		assertTrue((itEdge.next().compareTo("Edge 3")==0), "second edge should be \"Edge 3\"");

    }

    @Test
    public void testInsertVertex() {
		System.out.println("Test of insert vertex");

		AdjacencyMatrixGraph<String, Integer> instance = new AdjacencyMatrixGraph<>();
		assertTrue((instance.numVertices()==0), "result should be zero");
		instance.insertVertex("Vert 1");
		assertTrue((instance.numVertices()==1), "result should be one");
		instance.insertVertex("Vert 2");
		assertTrue((instance.numVertices()==2), "result should be two");

		assertFalse(instance.insertVertex("Vert 2"), "insert should fail on existing vertex");
		
		instance.removeVertex("Vert 1");
		assertTrue((instance.numVertices()==1), "result should be one");

		instance.insertVertex("Vert 3");
		assertTrue((instance.numVertices()==2), "result should be two");
		
		instance.insertVertex("Vert 4");

		Iterator <String> itVert = instance.vertices().iterator();
		
		assertTrue((itVert.next().compareTo("Vert 2")==0), "first vertex should be \"Vert 2\"");
		assertTrue((itVert.next().compareTo("Vert 3")==0), "second vertex should be \"Vert 3\"");
		assertTrue((itVert.next().compareTo("Vert 4")==0), "third vertex should be \"Vert 4\"");
		
		// Force resize of matrix
		
		for (int i = 0; i< 100 ; i++)
			instance.insertVertex("Vert "+ i);
		
		instance.insertEdge("Vert 1", "Vert 80", 80);
		
		Iterator<Integer> itEdge = instance.edges().iterator();

		assertEquals( 80, (int) itEdge.next(), "edge should be 80");
    }

    @Test
    public void testRemoveVertex() {
		System.out.println("Test of remove vertex");

		AdjacencyMatrixGraph<String, String> instance = new AdjacencyMatrixGraph<>();
		
		for(int i = 1 ; i <= 5; i++)
			instance.insertVertex("Vert "+i);
		
		instance.insertEdge("Vert 1", "Vert 2", "Edge 1");
		instance.insertEdge("Vert 2", "Vert 4", "Edge 2");
		instance.insertEdge("Vert 1", "Vert 5", "Edge 3");
		instance.insertEdge("Vert 2", "Vert 3", "Edge 4");
		instance.insertEdge("Vert 3", "Vert 1", "Edge 5");
		instance.insertEdge("Vert 4", "Vert 1", "Edge 6");
		

		assertTrue((instance.numVertices()==5), "result should be 5");
		assertTrue((instance.numEdges()==6), "result should be 6");
		
		instance.removeVertex("Vert 3");

		assertTrue((instance.numVertices()==4), "result should be 4");
		assertTrue((instance.numEdges()==5), "result should be 5");
		
		// Vertex 2 was removed - The vertices should now be 1, 2, 4, 5
		// Edge matrix should collapse - line and column 2 should refer to Vert 4
		// Requesting the edge between first and third vertices should give "Edge 6"
		// Requesting the edge between second and fourth vertices should give "Edge 2"
		
		Iterator <String> itVert = instance.vertices().iterator();

		String v1 = itVert.next();
		assertEquals(0, v1.compareTo("Vert 1"),"Vertex should be \"Vert 1\"");
		String v2 = itVert.next();
		assertEquals(0, v2.compareTo("Vert 2"), "Vertex should be \"Vert 2\"");
		String v3 = itVert.next();
		assertEquals( 0, v3.compareTo("Vert 4"), "Vertex should be \"Vert 4\"");
		String v4 = itVert.next();
		assertEquals( 0, v4.compareTo("Vert 5"), "Vertex should be \"Vert 5\"");

		assertEquals( 0, instance.getEdge(v3, v1).compareTo("Edge 6"), "edge should be \"Edge 6\"");
		assertEquals( 0, instance.getEdge(v2, v3).compareTo("Edge 2"), "edge should be \"Edge 2\"");
		assertNull( instance.getEdge(v2, v4), "edge should be null");
    }

    @Test
    public void testRemoveEdge() {
		System.out.println("Test of remove edge");

		AdjacencyMatrixGraph<String, String> instance = new AdjacencyMatrixGraph<>();
		
		for(int i = 1 ; i < 5; i++)
			instance.insertVertex("Vert "+i);
		
		assertTrue((instance.numEdges()==0), "result should be zero");
		
		instance.insertEdge("Vert 1", "Vert 2", "Edge 1");
		assertTrue((instance.numEdges()==1), "result should be one");
		
		instance.insertEdge("Vert 1", "Vert 3", "Edge 2");
		assertTrue((instance.numEdges()==2), "result should be two");

		instance.removeEdge("Vert 1",  "Vert 3");
		assertTrue((instance.numEdges()==1), "result should be one");
		
		instance.insertEdge("Vert 2", "Vert 4", "Edge 3");
		assertTrue((instance.numEdges()==2), "result should be two");
		
		Iterator <String> itEdge = instance.edges().iterator();
				
		assertTrue((itEdge.next().compareTo("Edge 1")==0), "first edge should be \"Edge 1\"");
		assertTrue((itEdge.next().compareTo("Edge 3")==0), "second edge should be \"Edge 3\"");

		instance.removeEdge("Vert 4",  "Vert 2");
		instance.removeEdge("Vert 2",  "Vert 1");
		assertTrue((instance.numEdges()==0), "result should be zero");
    }
	
    @Test
    public void testClone() {
		System.out.println("Test of Clone");

		AdjacencyMatrixGraph<String, String> instance = new AdjacencyMatrixGraph<>();
		
		for(int i = 1; i<= 5; i++)
			instance.insertVertex("Vert "+i);
		
		instance.insertEdge("Vert 1", "Vert 2", "Edge 1");
		instance.insertEdge("Vert 2", "Vert 4", "Edge 2");
		instance.insertEdge("Vert 1", "Vert 3", "Edge 3");
		instance.insertEdge("Vert 2", "Vert 3", "Edge 4");
		instance.insertEdge("Vert 1", "Vert 5", "Edge 5");
		
		@SuppressWarnings("unchecked")
		AdjacencyMatrixGraph<String, String> instance2 = (AdjacencyMatrixGraph<String, String>) instance.clone();

		assertEquals( instance.numVertices(), instance2.numVertices(), "number of vertices should be equal");
		assertEquals( instance.numEdges(), instance2.numEdges(), "number of edges should be equal");
		
		Iterator <String> itVert = instance2.vertices().iterator();

		for(int j = 1; j<=5; j++)
			assertTrue((itVert.next().compareTo("Vert "+j)==0), "vertex should be \"Vert \""+j);

		String edge = instance2.getEdge("Vert 1", "Vert 2");
		assertEquals( 0, edge.compareTo("Edge 1"), "edge should be \"Edge 1\"");
		edge = instance2.getEdge("Vert 2", "Vert 4");
		assertEquals( 0, edge.compareTo("Edge 2"), "edge should be \"Edge 2\"");
		edge = instance2.getEdge("Vert 1", "Vert 3");
		assertEquals( 0, edge.compareTo("Edge 3"), "edge should be \"Edge 3\"");
		edge = instance2.getEdge("Vert 2", "Vert 3");
		assertEquals( 0, edge.compareTo("Edge 4"), "edge should be \"Edge 4\"");
		edge = instance2.getEdge("Vert 1", "Vert 5");
		assertEquals( 0, edge.compareTo("Edge 5"), "edge should be \"Edge 5\"");
		
		
		instance.removeVertex("Vert 2");

		
		// instance should be different
		assertEquals( 4, instance.numVertices(),"should be now 4 vertices");
		assertEquals( 3, instance.numEdges(), "should be only 3 edges");

		// instance 2 should maintain the same as before

		assertEquals(5, instance2.numVertices(), "number of vertices should as before");
		assertEquals( 5, instance2.numEdges(), "number of edges should as before");

		
		itVert = instance2.vertices().iterator();
		
		itVert.next();

		assertEquals( 0, itVert.next().compareTo("Vert 2"), "Vertex should be \"Vert 2\"");
		
		edge = instance2.getEdge("Vert 2", "Vert 4");
		assertEquals( 0, edge.compareTo("Edge 2"), "edge should be \"Edge 2\"");
		
    }

    @Test
    public void testEqualsObject() {
		System.out.println("Test Equals");
		
		AdjacencyMatrixGraph<String, String> instance = new AdjacencyMatrixGraph<>();

		for(int i = 1; i<= 5; i++)
			instance.insertVertex("Vert "+i);
		
		instance.insertEdge("Vert 1", "Vert 2", "Edge 1");
		instance.insertEdge("Vert 2", "Vert 4", "Edge 2");
		instance.insertEdge("Vert 1", "Vert 3", "Edge 3");
		instance.insertEdge("Vert 2", "Vert 3", "Edge 4");
		instance.insertEdge("Vert 1", "Vert 5", "Edge 5");

		assertNotNull(instance, "should not be equal to null");

		assertEquals( instance, instance, "should be equal to itself");

		assertEquals( instance, instance.clone(), "should be equal to a clone");
		
		AdjacencyMatrixGraph<String, String> instance3 = new AdjacencyMatrixGraph<>();

		for(int i = 1; i<= 5; i++)
			instance3.insertVertex("Vert "+i);
		
		instance3.insertEdge("Vert 1", "Vert 2", "Edge 1");
		instance3.insertEdge("Vert 2", "Vert 4", "Edge 2");
		instance3.insertEdge("Vert 1", "Vert 3", "Edge 3");
		instance3.insertEdge("Vert 2", "Vert 3", "Edge 4");

		assertNotEquals( instance, instance3, "should not be equal");

		instance3.insertEdge("Vert 1", "Vert 5", "Edge 5");

		assertEquals( instance, instance3, "should be equal");
    }

    @Test
    public void testToString() {
		System.out.println("Test of To String");
		
		AdjacencyMatrixGraph<String, String> instance = new AdjacencyMatrixGraph<>();

		for(int i = 1 ; i <= 5; i++)
			instance.insertVertex("Vert "+i);
		
		instance.insertEdge("Vert 1", "Vert 2", "Edge 1");
		instance.insertEdge("Vert 2", "Vert 4", "Edge 2");
		instance.insertEdge("Vert 1", "Vert 3", "Edge 3");
		instance.insertEdge("Vert 2", "Vert 3", "Edge 4");
		instance.insertEdge("Vert 1", "Vert 5", "Edge 5");

		System.out.println(instance);
    }

	
}