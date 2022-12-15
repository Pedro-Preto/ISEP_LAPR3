 
package lapr.project.model.MatrixGraph;

import java.util.Iterator;
import java.util.LinkedList;

import lapr.project.model.MatrixGraph.AdjacencyMatrixGraph;
import lapr.project.model.MatrixGraph.EdgeAsDoubleGraphAlgorithms;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author DEI-ESINF
 */
public class EdgeAsDoubleGraphAlgorithmsTest {
    
    AdjacencyMatrixGraph<String, Double> distanceMap = new AdjacencyMatrixGraph<>();

    public EdgeAsDoubleGraphAlgorithmsTest() {
    }
    
    @BeforeEach
    public void setUp() throws Exception {
		distanceMap.insertVertex("Porto");
		distanceMap.insertVertex("Braga");
		distanceMap.insertVertex("Vila Real");
		distanceMap.insertVertex("Aveiro");
		distanceMap.insertVertex("Coimbra");
		distanceMap.insertVertex("Leiria");

		distanceMap.insertVertex("Viseu");
		distanceMap.insertVertex("Guarda");
		distanceMap.insertVertex("Castelo Branco");
		distanceMap.insertVertex("Lisboa");
		distanceMap.insertVertex("Faro");
		distanceMap.insertVertex("Évora");
		

		distanceMap.insertEdge("Porto", "Aveiro", 75.0);
		distanceMap.insertEdge("Aveiro", "Porto", 75.0);

		distanceMap.insertEdge("Porto", "Braga", 60.0);
		distanceMap.insertEdge("Braga","Porto",  60.0);

		distanceMap.insertEdge("Porto", "Vila Real", 100.0);
		distanceMap.insertEdge("Vila Real","Porto",  100.0);

		distanceMap.insertEdge("Viseu", "Guarda", 75.0);
		distanceMap.insertEdge("Guarda","Viseu",  75.0);

		distanceMap.insertEdge("Castelo Branco","Guarda", 100.0);
		distanceMap.insertEdge("Guarda","Castelo Branco", 100.0);

		distanceMap.insertEdge("Aveiro", "Coimbra", 60.0);
		distanceMap.insertEdge("Coimbra", "Aveiro", 60.0);

		distanceMap.insertEdge("Coimbra", "Lisboa", 200.0);
		distanceMap.insertEdge( "Lisboa","Coimbra", 200.0);

		distanceMap.insertEdge("Coimbra",  "Leiria",  80.0);
		distanceMap.insertEdge("Leiria","Coimbra",    80.0);

		distanceMap.insertEdge("Aveiro", "Leiria", 120.0);
		distanceMap.insertEdge( "Leiria","Aveiro", 120.0);

		distanceMap.insertEdge("Leiria", "Lisboa", 150.0);
		distanceMap.insertEdge("Lisboa","Leiria",  150.0);
		
		distanceMap.insertEdge("Aveiro", "Viseu", 85.0);
		distanceMap.insertEdge("Viseu","Aveiro",  85.0);

		distanceMap.insertEdge("Leiria", "Castelo Branco", 170.0);
		distanceMap.insertEdge("Castelo Branco","Leiria",  170.0);

		distanceMap.insertEdge("Lisboa", "Faro", 280.0);
		distanceMap.insertEdge("Faro","Lisboa",  280.0);

	}

	@Test
	public void testShortestPath() {
		System.out.println("Test of shortest path");
		
		LinkedList<String> path = new LinkedList<>();
		
		assertTrue(EdgeAsDoubleGraphAlgorithms.shortestPath(distanceMap,  "Porto",  "LX",  path) == -1, "Should be -1 if vertex does not exist");
		 
		assertTrue(EdgeAsDoubleGraphAlgorithms.shortestPath(distanceMap, "Porto", "Évora", path)==-1, "Should be -1 if there is no path");
		
		assertTrue(EdgeAsDoubleGraphAlgorithms.shortestPath(distanceMap,  "Porto",  "Porto",  path) == 0, "Should be 0 if source and vertex are the same");
		
		assertTrue(path.size() == 1, "Path should be single vertex if source and vertex are the same");
		
		assertTrue(EdgeAsDoubleGraphAlgorithms.shortestPath(distanceMap,  "Porto",  "Lisboa",  path) == 335, "Path between Porto and Lisboa should be 335 Km");
		
		Iterator<String> it = path.iterator();

		assertTrue(it.next().compareTo("Porto")==0, "First in path should be Porto");
		assertTrue(it.next().compareTo("Aveiro")==0, "then Aveiro");
		assertTrue(it.next().compareTo("Coimbra")==0, "then Coimbra");
		assertTrue(it.next().compareTo("Lisboa")==0, "then Lisboa");

		assertTrue(EdgeAsDoubleGraphAlgorithms.shortestPath(distanceMap,  "Braga",  "Leiria",  path) == 255, "Path between Braga and Leiria should be 255 Km");
		
		it = path.iterator();

		assertTrue(it.next().compareTo("Braga")==0, "First in path should be Braga");
		assertTrue(it.next().compareTo("Porto")==0, "then Porto");
		assertTrue(it.next().compareTo("Aveiro")==0, "then Aveiro");
		assertTrue(it.next().compareTo("Leiria")==0, "then Leiria");
		
		assertTrue(EdgeAsDoubleGraphAlgorithms.shortestPath(distanceMap,  "Porto",  "Castelo Branco",  path) == 335, "Path between Porto and Castelo Branco should be 335 Km");
		assertTrue(path.size() == 5, "Path between Porto and Castelo Branco should be 5 cities");
		
		it = path.iterator();

		assertTrue(it.next().compareTo("Porto")==0, "First in path should be Porto");
		assertTrue(it.next().compareTo("Aveiro")==0, "then Aveiro");
		assertTrue(it.next().compareTo("Viseu")==0, "then Viseu");
		assertTrue(it.next().compareTo("Guarda")==0, "then Guarda");
		assertTrue(it.next().compareTo("Castelo Branco")==0, "then Castelo Branco");
		
		// Changing Viseu to Guarda should change shortest path between Porto and Castelo Branco

		distanceMap.removeEdge("Viseu", "Guarda");
		distanceMap.insertEdge("Viseu", "Guarda", 125.0);
		
		assertTrue(EdgeAsDoubleGraphAlgorithms.shortestPath(distanceMap,  "Porto",  "Castelo Branco",  path) == 365, "Path between Porto and Castelo Branco should now be 365 Km");
		assertTrue(path.size() == 4, "Path between Porto and Castelo Branco should be 4 cities");
		
		it = path.iterator();
	
		assertTrue(it.next().compareTo("Porto")==0, "First in path should be Porto");
		assertTrue(it.next().compareTo("Aveiro")==0, "then Aveiro");
		assertTrue(it.next().compareTo("Leiria")==0, "then Leiria");
		assertTrue(it.next().compareTo("Castelo Branco")==0, "then Castelo Branco");
		
		
	}

}
