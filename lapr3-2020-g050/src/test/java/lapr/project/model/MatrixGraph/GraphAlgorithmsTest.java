
package lapr.project.model.MatrixGraph;


import java.util.Iterator;
import java.util.LinkedList;

import lapr.project.model.MatrixGraph.AdjacencyMatrixGraph;
import lapr.project.model.MatrixGraph.GraphAlgorithms;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author DEI-ESINF
 */
public class GraphAlgorithmsTest {

    AdjacencyMatrixGraph<String, String> completeMap = new AdjacencyMatrixGraph<>();
    AdjacencyMatrixGraph <String, String> incompleteMap;

    @SuppressWarnings("unchecked")
    @BeforeEach
    public void setUp() throws Exception {
		
		completeMap.insertVertex("Porto");
		completeMap.insertVertex("Braga");
		completeMap.insertVertex("Vila Real");
		completeMap.insertVertex("Aveiro");
		completeMap.insertVertex("Coimbra");
		completeMap.insertVertex("Leiria");

		completeMap.insertVertex("Viseu");
		completeMap.insertVertex("Guarda");
		completeMap.insertVertex("Castelo Branco");
		completeMap.insertVertex("Lisboa");
		completeMap.insertVertex("Faro");

		completeMap.insertEdge("Porto", "Aveiro", "A1");
		completeMap.insertEdge("Aveiro","Porto",  "A1");

		completeMap.insertEdge("Porto", "Braga", "A3");
		completeMap.insertEdge("Braga", "Porto", "A3");

		completeMap.insertEdge("Porto", "Vila Real", "A4");
		completeMap.insertEdge("Vila Real","Porto",  "A4");

		completeMap.insertEdge("Viseu", "Guarda", "A25");
		completeMap.insertEdge("Guarda","Viseu",  "A25");

		completeMap.insertEdge("Guarda",  "Castelo Branco", "A23");
		completeMap.insertEdge("Castelo Branco","Guarda",   "A23");

		completeMap.insertEdge("Aveiro", "Coimbra", "A1");
		completeMap.insertEdge("Coimbra", "Aveiro", "A1");

		completeMap.insertEdge("Coimbra", "Lisboa", "A1");
		completeMap.insertEdge("Lisboa","Coimbra",  "A1");

		completeMap.insertEdge("Coimbra",  "Leiria",  "A34");
		completeMap.insertEdge("Leiria","Coimbra",    "A34");

		completeMap.insertEdge("Aveiro", "Leiria", "A17");
		completeMap.insertEdge("Leiria","Aveiro",  "A17");

		completeMap.insertEdge("Leiria", "Lisboa", "A8");
		completeMap.insertEdge("Lisboa", "Leiria", "A8");
		
		incompleteMap = (AdjacencyMatrixGraph<String, String>) completeMap.clone();
		
		completeMap.insertEdge("Aveiro", "Viseu", "A25");
		completeMap.insertEdge("Viseu", "Aveiro", "A25");

		completeMap.insertEdge("Leiria", "Castelo Branco", "A23");
		completeMap.insertEdge("Castelo Branco", "Leiria", "A23");

		completeMap.insertEdge("Lisboa", "Faro", "A2");
		completeMap.insertEdge("Faro","Lisboa",  "A2");

	}

	
	@Test
	public void testDFS() {
		System.out.println("Test of DFS");

		LinkedList<String> path;

		assertNull( GraphAlgorithms.DFS(completeMap, "LX"), "Should be null if vertex does not exist");

		path = GraphAlgorithms.DFS(incompleteMap, "Faro");

		assert path != null;
		assertEquals( 1, path.size(), "Should be just one");

		Iterator<String> it = path.iterator();

		assertEquals( 0, it.next().compareTo("Faro"), "it should be Faro");
		
		path = GraphAlgorithms.DFS(completeMap, "Porto");

		assert path != null;
		assertEquals( 11, path.size(), "Should give all vertices ");

		it = path.iterator();

		assertEquals(0, it.next().compareTo("Porto"),"First in visit should be Porto" );
		assertEquals( 0, it.next().compareTo("Braga"), "then Braga");
		assertEquals( 0, it.next().compareTo("Vila Real"), "then Vila Real");
		assertEquals( 0, it.next().compareTo("Aveiro"), "then Aveiro");

		assertEquals(0, it.next().compareTo("Coimbra"), "then Coimbra");
		assertEquals( 0, it.next().compareTo("Leiria"), "then Leiria");
		assertEquals( 0, it.next().compareTo("Castelo Branco"), "then Castelo Branco");
		assertEquals(0, it.next().compareTo("Guarda"), "then Guarda");
		assertEquals( 0, it.next().compareTo("Viseu"), "then Viseu");
		assertEquals( 0, it.next().compareTo("Lisboa"), "then Lisboa");
		assertEquals( 0, it.next().compareTo("Faro"), "then Faro");

		
		path = GraphAlgorithms.DFS(incompleteMap, "Viseu");

		assert path != null;
		assertEquals( 3, path.size(), "Should give 3 vertices");

		it = path.iterator();

		assertEquals( 0, it.next().compareTo("Viseu"), "First in visit should be Viseu");
		assertEquals( 0, it.next().compareTo("Guarda"), "then Guarda");
		assertEquals( 0, it.next().compareTo("Castelo Branco"), "then Castelo Branco");
	}

	@Test
	public void testBFS() {
		System.out.println("Test of BFS");

		LinkedList<String> path;

		assertNull(GraphAlgorithms.BFS(completeMap, "LX"), "Should be null if vertex does not exist");

		path = GraphAlgorithms.BFS(incompleteMap, "Faro");

		assert path != null;
		assertEquals( 1, path.size(), "Should be just one");

		Iterator<String> it = path.iterator();

		assertEquals( 0, it.next().compareTo("Faro"), "it should be Faro");

		
		path = GraphAlgorithms.BFS(completeMap, "Porto");

		assert path != null;
		assertEquals( 11, path.size(), "Should give all vertices ");

		it = path.iterator();

		assertEquals( 0, it.next().compareTo("Porto"), "First in visit should be Porto");
		assertEquals( 0, it.next().compareTo("Braga"), "then Braga");
		assertEquals( 0, it.next().compareTo("Vila Real"), "then Vila Real");
		assertEquals( 0, it.next().compareTo("Aveiro"), "then Aveiro");

		assertEquals( 0, it.next().compareTo("Coimbra"), "then Coimbra");
		assertEquals( 0, it.next().compareTo("Leiria"), "then Leiria");
		assertEquals( 0, it.next().compareTo("Viseu"), "then Viseu");
		assertEquals( 0, it.next().compareTo("Lisboa"), "then Lisboa");
		assertEquals( 0, it.next().compareTo("Castelo Branco"), "then Castelo Branco");
		assertEquals( 0, it.next().compareTo("Guarda"), "then Guarda");
		assertEquals( 0, it.next().compareTo("Faro"), "then Faro");

		
		path = GraphAlgorithms.BFS(incompleteMap, "Viseu");

		assert path != null;
		assertEquals( 3, path.size(), "Should give 3 vertices");

		it = path.iterator();

		assertEquals( 0, it.next().compareTo("Viseu"), "First in visit should be Viseu");
		assertEquals( 0, it.next().compareTo("Guarda"), "then Guarda");
		assertEquals( 0, it.next().compareTo("Castelo Branco"), "then Castelo Branco");
		
	}


}
