package lapr.project.model.DomainClasses;


import java.util.ArrayList;
import java.util.LinkedList;


import lapr.project.model.MatrixGraph.AdjacencyMatrixGraph;
import lapr.project.model.MatrixGraph.EdgeAsDoubleGraphAlgorithms;
import lapr.project.model.MatrixGraph.GraphAlgorithms;


public class LocationNet {

    /**
     * Graph representing a map with the edges being streets and the address vertices
     */
    AdjacencyMatrixGraph<Address, Double> map;

    /**
     * Empty constructor, builds a LocationNet instance with an empty graph.
     */
    public LocationNet() {
        this.map = new AdjacencyMatrixGraph<>();
    }


    /**
     * Add the vertices to the graph and the vertices are the address of the clients and the pharmacy
     *
     * @param address of the clients and the pharmacy
     * @return true if there are addresses
     */
    public boolean addAddress(Address address) {
        if (address != null) {
            return this.map.insertVertex(address);
        }
        return false;
    }

    /**
     * Add the edges of the graph, that are the routes between de address
     *
     * @param a1 address of the client or pharmacy
     * @param a2 address of the client or pharmacy
     * @return true if both address exist
     */
    public boolean addConnection(Address a1, Address a2, Double edgeValue) {
        if (a1 != null && a2 != null) {
            return map.insertEdge(a1, a2, edgeValue);
        } else {
            return false;
        }
    }


    public boolean isConnected() {
        //An undirected graph is connected if every pair of vertices is connected by a path.
        for (Address origin : this.map.vertices()) {
            LinkedList<Address> list = GraphAlgorithms.DFS(this.map, origin);
            assert list != null;
            if (list.size() != this.map.numVertices()) {

                return false;
            }
        }
        return true;

    }

    /**
     * @param aOrigin address of origin point
     * @param aDest   address of destiny point
     * @param path     list where the path between origin point and destiny point is saved
     * @return path
     */

    public double getRoute(Address aOrigin, Address aDest, LinkedList<Address> path) {
        return EdgeAsDoubleGraphAlgorithms.shortestPath(map, aOrigin, aDest, path);
    }

    private void getShortestPathsBetweenAddress(LinkedList<Address> intermediatePoints, Address aOrigin,
                                                ArrayList<LinkedList<Address>> paths, LinkedList<Double> dists){

        for(Address aDest : intermediatePoints) {
            if(!aDest.equals(aOrigin)){
                LinkedList<Address> path = new LinkedList<>();
                double dist = getRoute(aOrigin,aDest,path);
                paths.add(path);
                dists.add(dist);
            }
        }

    }


    public double getRouteWithIntermediatePoints(Address aOrigin, LinkedList<Address> intermediateAddress, LinkedList<Address> finalPath){

        //Get shortest path between the cities with greater number
        double dist = 0.0;
        //Finds all the shortest path between starter vertex and the cities intermediate
        Address vOrig = aOrigin;

        while(intermediateAddress.size()!=0){
            ArrayList<LinkedList<Address>> paths = new ArrayList<>();
            LinkedList<Double> dists = new LinkedList<>();

            getShortestPathsBetweenAddress(intermediateAddress,vOrig,paths,dists);

            double shorterPathDist = Double.MAX_VALUE;
            LinkedList<Address> shorterPath = new LinkedList<>();
            for(int i = 0; i < paths.size(); i++){
                double pathDist = dists.get(i);
                if(shorterPathDist > pathDist){
                    shorterPathDist = pathDist;
                    shorterPath = paths.get(i);
                }
            }

            finalPath.addAll(shorterPath);
            dist += shorterPathDist;

            intermediateAddress.removeIf(finalPath::contains);

            if(intermediateAddress.size()!=0)
                vOrig = finalPath.removeLast();

        }


        return dist;

    }

    public String mapToString(){
        return map.toString();
    }


}


