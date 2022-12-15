///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package lapr.project.model.DomainClasses;
//
//import lapr.project.model.MatrixGraph.GraphAlgorithms;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.LinkedList;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class MapTest {
//
//    LocationNet mapatest = new LocationNet();
//
//
//    @BeforeEach
//    void setUp() {
//        GeoLocation geo1 = new GeoLocation(1.3, 2.2);
//        GeoLocation geo2 = new GeoLocation(1.4, 2.5);
//        Address a1 = new Address("Rua1", "5", "Porto", "Portugal", geo1);
//        Address a2 = new Address("Rua2", "6", "Madrid", "Espanha", geo2);
//        GeoLocation geo3 = new GeoLocation(1.3, 2.2);
//        GeoLocation geo4 = new GeoLocation(1.4, 2.5);
//        GeoLocation geo5 = new GeoLocation(1.4, 2.5);
//        GeoLocation geo6 = new GeoLocation(1.4, 2.5);
//        Address a3 = new Address("Rua3", "5", "Miranda", "Portugal", geo3);
//        Address a4 = new Address("Rua4", "6", "Barcelona", "Espanha", geo4);
//        Address a5 = new Address("Rua5", "6", "Barcelona", "Espanha", geo5);
//        Address a6 = new Address("Rua6", "6", "Barcelona", "Espanha", geo6);
//
//        mapatest.addAddress(a1);
//        mapatest.addAddress(a2);
//        mapatest.addAddress(a3);
//        mapatest.addAddress(a4);
//        mapatest.addAddress(a5);
//        mapatest.addAddress(a6);
//
//
//        mapatest.addRode(a1, a2);
//        mapatest.addRode(a2, a3);
//        mapatest.addRode(a3, a4);
//        mapatest.addRode(a4, a1);
//        mapatest.addRode(a4, a2);
//        mapatest.addRode(a4, a5);
//        mapatest.addRode(a5, a6);
//
//
//    }
//
//    /**
//     * Test of addAddress method, of class LocationNet.
//     */
//    @Test
//    void addAddress() {
//        System.out.println("addAddress");
//        GeoLocation geo1 = new GeoLocation(1.3, 2.2);
//        GeoLocation geo2 = new GeoLocation(1.4, 2.5);
//        Address a1 = new Address("Rua1", "5", "Porto", "Portugal", geo1);
//        Address a2 = new Address("Rua2", "6", "Madrid", "Espanha", geo2);
//
//        LocationNet instance = new LocationNet();
//        assertTrue(instance.addAddress(a1), "result should be true");
//        assertTrue(instance.addAddress(a2), "result should be true");
//    }
//
//    /**
//     * Test of addRode method, of class LocationNet.
//     */
//    @Test
//    void addRode() {
//        System.out.println("addRode");
//
//        LocationNet instance = new LocationNet();
//
//        GeoLocation geo1 = new GeoLocation(1.3, 2.2);
//        GeoLocation geo2 = new GeoLocation(1.4, 2.5);
//        Address a1 = new Address("Rua1", "5", "Porto", "Portugal", geo1);
//        Address a2 = new Address("Rua2", "6", "Madrid", "Espanha", geo2);
//        GeoLocation geo3 = new GeoLocation(1.3, 2.2);
//        Address a3 = new Address("Rua3", "5", "Miranda", "Portugal", geo3);
//
//        instance.addAddress(a1);
//        instance.addAddress(a2);
//        instance.addAddress(a3);
//
//
//        assertTrue(instance.addRode(a1, a2), "result should be true");
//        assertTrue(instance.addRode(a2, a3), "result should be true");
//
//
//    }
//
//
//    /**
//     * Test of getRoute method, of class LocationNet.
//     */
//    @Test
//    public void testGetRoute() {
//
//        System.out.println("getRoute");
//        System.out.println("getRoute");
//        LocationNet instance = new LocationNet();
//
//        GeoLocation geo1 = new GeoLocation(1.3, 2.2);
//        GeoLocation geo2 = new GeoLocation(1.4, 2.5);
//        GeoLocation geo3 = new GeoLocation(1.3, 2.2);
//        GeoLocation geo4 = new GeoLocation(1.4, 2.5);
//        GeoLocation geo5 = new GeoLocation(1.4, 2.5);
//        GeoLocation geo6 = new GeoLocation(1.4, 2.5);
//        Address a1 = new Address("Rua1", "5", "Porto", "Portugal", geo1);
//        Address a2 = new Address("Rua2", "6", "Madrid", "Espanha", geo2);
//        Address a3 = new Address("Rua3", "5", "Miranda", "Portugal", geo3);
//        Address a4 = new Address("Rua4", "6", "Barcelona", "Espanha", geo4);
//        Address a5 = new Address("Rua5", "6", "Barcelona", "Espanha", geo5);
//        Address a6 = new Address("Rua6", "6", "Barcelona", "Espanha", geo6);
//
//
//        instance.addAddress(a1);
//        instance.addAddress(a2);
//        instance.addAddress(a3);
//        instance.addAddress(a4);
//        instance.addAddress(a5);
//        instance.addAddress(a6);
//
//
//        instance.addRode(a1, a2);
//        instance.addRode(a2, a3);
//        instance.addRode(a1, a5);
//        instance.addRode(a5, a6);
//        instance.addRode(a6, a4);
//        instance.addRode(a4, a3);
//        LinkedList<Address> path=new LinkedList<>();
//        double expResult = 0.0;
//        double result = instance.getRoute(a1, a2, path);
//        assertEquals(expResult, result, 0.0);
//
//    }
//
//    @Test
//    public void testGetShortRoute() {
//        System.out.println("getRoute");
//        System.out.println("getRoute");
//        LocationNet instance = new LocationNet();
//
//        GeoLocation geo1 = new GeoLocation(1.3, 2.2);
//        GeoLocation geo2 = new GeoLocation(1.4, 2.5);
//        GeoLocation geo3 = new GeoLocation(1.3, 2.2);
//        GeoLocation geo4 = new GeoLocation(1.4, 2.5);
//        GeoLocation geo5 = new GeoLocation(1.4, 2.5);
//        GeoLocation geo6 = new GeoLocation(1.4, 2.5);
//        Address a1 = new Address("Rua1", "5", "Porto", "Portugal", geo1);
//        Address a2 = new Address("Rua2", "6", "Madrid", "Espanha", geo2);
//        Address a3 = new Address("Rua3", "5", "Miranda", "Portugal", geo3);
//        Address a4 = new Address("Rua4", "6", "Barcelona", "Espanha", geo4);
//        Address a5 = new Address("Rua5", "6", "Barcelona", "Espanha", geo5);
//        Address a6 = new Address("Rua6", "6", "Barcelona", "Espanha", geo6);
//
//
//        instance.addAddress(a1);
//        instance.addAddress(a2);
//        instance.addAddress(a3);
//        instance.addAddress(a4);
//        instance.addAddress(a5);
//        instance.addAddress(a6);
//
//
//        instance.addRode(a1, a2);
//        instance.addRode(a2, a3);
//        instance.addRode(a1, a5);
//        instance.addRode(a5, a6);
//        instance.addRode(a6, a4);
//        instance.addRode(a4, a3);
//
//
//        double expResult = 0.0;
//        double result = instance.getShortRoute();
//        assertEquals(expResult, result, 0.0);
//
//    }
//
//    /**
//     * Test of isConnected method, of class LocationNet.
//     */
//    @Test
//    public void testIsConnected() {
//        System.out.println("isConnected");
//        LocationNet instance = new LocationNet();
//        GeoLocation geo1 = new GeoLocation(1.3, 2.2);
//        GeoLocation geo2 = new GeoLocation(1.4, 2.5);
//        GeoLocation geo3 = new GeoLocation(1.3, 2.2);
//        GeoLocation geo4 = new GeoLocation(1.4, 2.5);
//        GeoLocation geo5 = new GeoLocation(1.4, 2.5);
//        GeoLocation geo6 = new GeoLocation(1.4, 2.5);
//        Address a1 = new Address("Rua1", "5", "Porto", "Portugal", geo1);
//        Address a2 = new Address("Rua2", "6", "Madrid", "Espanha", geo2);
//        Address a3 = new Address("Rua3", "5", "Miranda", "Portugal", geo3);
//        Address a4 = new Address("Rua4", "6", "Barcelona", "Espanha", geo4);
//        Address a5 = new Address("Rua5", "6", "Barcelona", "Espanha", geo5);
//        Address a6 = new Address("Rua6", "6", "Barcelona", "Espanha", geo6);
//
//
//        instance.addAddress(a1);
//        instance.addAddress(a2);
//        instance.addRode(a1, a2);
//        instance.addRode(a2, a3);
//        instance.addRode(a1, a5);
//        instance.addRode(a5, a6);
//        instance.addRode(a6, a4);
//        instance.addRode(a4, a3);
//
//
//        for (Address origin : instance.map.vertices()) {
//            LinkedList<Address> list = GraphAlgorithms.DFS(instance.map, origin);
//            assertFalse(list.size() != instance.map.numVertices(), "Must be false");
//            assertTrue(list.size() == instance.map.numVertices());
//
//
//        }
//    }
//
//
//}
