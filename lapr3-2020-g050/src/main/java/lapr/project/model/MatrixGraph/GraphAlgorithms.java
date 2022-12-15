
package lapr.project.model.MatrixGraph;

import java.util.LinkedList;

/**
 * Implementation of graph algorithms for a (undirected) graph structure
 * Considering generic vertex V and edge E types
 * <p>
 * Works on AdjancyMatrixGraph objects
 *
 * @author DEI-ESINF
 */
public class GraphAlgorithms {


    /**
     * Performs depth-first search of the graph starting at vertex.
     * Calls package recursive version of the method.
     *
     * @param graph  Graph object
     * @param vertex Vertex of graph that will be the source of the search
     * @return queue of vertices found by search (including vertex), null if vertex does not exist
     */
    public static <V, E> LinkedList<V> BFS(AdjacencyMatrixGraph<V, E> graph, V vertex) {

        int index = graph.toIndex(vertex);
        if (index == -1)
            return null;

        LinkedList<V> resultQueue = new LinkedList<V>();
        LinkedList<Integer> auxQueue = new LinkedList<Integer>();

        resultQueue.add(graph.vertices.get(index));
        auxQueue.add(index);

        while (!auxQueue.isEmpty()) {
            index = auxQueue.remove();
            for (int i = 0; i < graph.numVertices; i++) {
                if (graph.edgeMatrix[index][i] != null) {
                    if (!resultQueue.contains(graph.vertices.get(i))) {
                        resultQueue.add(graph.vertices.get(i));
                        auxQueue.add(i);
                    }
                }
            }
        }
        return resultQueue;
    }

    /**
     * Performs depth-first search of the graph starting at vertex.
     * Calls package recursive version of the method.
     *
     * @param graph  Graph object
     * @param vertex Vertex of graph that will be the source of the search
     * @return queue of vertices found by search (empty if none), null if vertex does not exist
     */
    public static <V, E> LinkedList<V> DFS(AdjacencyMatrixGraph<V, E> graph, V vertex) {
        if (!graph.checkVertex(vertex)) {
            return null;
        }
        LinkedList<V> list = new LinkedList<>();
        DFS(graph, vertex, list);
        return list;
    }

    /**
     * Actual depth-first search of the graph starting at vertex.
     * The method adds discovered vertices (including vertex) to the queue of vertices
     *
     * @param graph         Graph object
     * @param vertex        The vertex of graph that will be the source of the search
     * @param verticesQueue queue of vertices found by search
     */
    static <V, E> void DFS(AdjacencyMatrixGraph<V, E> graph, V vertex, LinkedList<V> verticesQueue) {
        verticesQueue.add(vertex);
        for (V adj : graph.directConnections(vertex)) {
            if (!verticesQueue.contains(adj)) {
                DFS(graph, adj, verticesQueue);
            }
        }

    }


}
