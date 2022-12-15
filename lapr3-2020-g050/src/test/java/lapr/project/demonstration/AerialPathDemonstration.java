package lapr.project.demonstration;

import lapr.project.controller.ApplicationEM;
import lapr.project.controller.DeliverySimulationsController;
import lapr.project.controller.EnergyAerialMapController;
import lapr.project.model.DomainClasses.*;
import lapr.project.model.Registers.AddressRegister;
import lapr.project.model.Registers.GeoLocationRegister;
import lapr.project.utils.EnergyConsumptionAlgorithms;
import lapr.project.utils.PrintFile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AerialPathDemonstration {

    DeliverySimulationsController deliverySimulationsController;
    AddressRegister addressRegister;
    GeoLocationRegister geoLocationRegister;

    @BeforeEach
    void setUp(){
        deliverySimulationsController = new DeliverySimulationsController();
        ApplicationEM applicationEM = ApplicationEM.getInstance();
        Platform platform = applicationEM.getPlatform();
        addressRegister = platform.getAddressRegister();
        geoLocationRegister = platform.getGeoLocationRegister();
        addressRegister.addAddress(1295,"street1", "doornr1", "city1", "country1");
        addressRegister.addAddress(1296,"street2", "doornr2", "city2", "country2");

        geoLocationRegister.addGeoLocation(1295, 1.0,1.0,1,1295);
        geoLocationRegister.addGeoLocation(1296, 2.0,2.0,2,1296);
    }

    @AfterEach
    void tearDown(){
        geoLocationRegister.removeGeoLocation(1295);
        geoLocationRegister.removeGeoLocation(1296);

        addressRegister.removeAddress(1295);
        addressRegister.removeAddress(1296);
    }

    @Test
    void cenarioA3_0(){
        StringBuilder stringBuilder = new StringBuilder();

        List<AerialPath> aerialPathList = new ArrayList<>();
        AerialPath aerialPath = new AerialPath(1295,1296,2500.0, 180.0, 10.0, Constants.D_AIR_DENSITY, Constants.D_AIR_DRAG_COEFFICIENT);
        AerialPath aerialPathReverse = new AerialPath(1296,1295,2500.0, 0.0, 10.0, Constants.D_AIR_DENSITY, Constants.D_AIR_DRAG_COEFFICIENT);

        aerialPathList.add(aerialPath);
        aerialPathList.add(aerialPathReverse);

        stringBuilder.append(String.format("Air Drag Coefficient: %f \n", Constants.S_AIR_DRAG_COEFFICIENT));
        stringBuilder.append(String.format("Vehicle medium speed: %f kmh | %f mps\n",Constants.D_VELOCITY, EnergyConsumptionAlgorithms.kmhToMps(Constants.D_VELOCITY)));

        Drone drone = new Drone(Constants.DEFAULT_INT_VALUES, Constants.DRONE_BATTERY_SIZE, Constants.DRONE_BATTERY_SIZE,
                Constants.DRONE_BATTERY_EFFICIENCY,Constants.DRONE_BATTERY_SIZE, Constants.DRONE_WEIGHT,
                Constants.DRONE_FRONTAL_AREA,Constants.DRONE_MOTOR_POWER,
                Constants.DEFAULT_STRING_VALUES,7.0);

        Courier courier = new Courier();

        stringBuilder.append(drone.toString());

        LinkedList<Address> aerialGoingPathE = new LinkedList<>();

        EnergyAerialMapController energyAerialMapController = deliverySimulationsController.getEnergyConsumptionAerialMap(aerialPathList,drone,courier);

        Address start = addressRegister.getAddressByID(1295);
        Address end = addressRegister.getAddressByID(1296);

        double aerialEnergyConsumptionGoing = energyAerialMapController.getMostEfficientRoute(start,end,aerialGoingPathE);


        stringBuilder.append(String.format("Aerial Going Energy Consumption: %.3f J \n\nPath: %s\n\n",aerialEnergyConsumptionGoing,aerialGoingPathE));

        LinkedList<Address> aerialReturningPathE = new LinkedList<>();
        double aerialReturingEnergyConsumption = 0;

        if(aerialGoingPathE.size() != 0) {
            aerialReturingEnergyConsumption = energyAerialMapController.getMostEfficientRoute(end, start, aerialReturningPathE);
            stringBuilder.append(String.format("Aerial Returning Energy Consumption: %.3f J \n\nPath: %s\n\n", aerialReturingEnergyConsumption, aerialReturningPathE));
        }
        else
            stringBuilder.append(String.format("Aerial Returning Path: %s\n\n", "not possible !"));
        double aerialEnergyConsumption = aerialReturingEnergyConsumption + aerialEnergyConsumptionGoing;
        stringBuilder.append("------------------------------- \n\n");
        stringBuilder.append(String.format("Total Aerial Enery Consumption: %.3f J \n\n",aerialEnergyConsumption));
        stringBuilder.append("------------------------------- \n\n");

        PrintFile.printFile("cenarioA3_0.txt", stringBuilder.toString());
    }

    @Test
    void cenarioA2_0(){
        StringBuilder stringBuilder = new StringBuilder();

        List<AerialPath> aerialPathList = new ArrayList<>();
        AerialPath aerialPath = new AerialPath(1295,1296,10000.0, 180.0, 10.0, Constants.D_AIR_DENSITY, Constants.D_AIR_DRAG_COEFFICIENT);
        AerialPath aerialPathReverse = new AerialPath(1296,1295,10000.0, 180.0, 10.0, Constants.D_AIR_DENSITY, Constants.D_AIR_DRAG_COEFFICIENT);

        aerialPathList.add(aerialPath);
        aerialPathList.add(aerialPathReverse);

        stringBuilder.append(String.format("Air Drag Coefficient: %f \n", Constants.S_AIR_DRAG_COEFFICIENT));
        stringBuilder.append(String.format("Vehicle medium speed: %f kmh | %f mps\n",Constants.D_VELOCITY, EnergyConsumptionAlgorithms.kmhToMps(Constants.D_VELOCITY)));

        Drone drone = new Drone(Constants.DEFAULT_INT_VALUES, Constants.DRONE_BATTERY_SIZE, Constants.DRONE_BATTERY_SIZE,
                Constants.DRONE_BATTERY_EFFICIENCY,Constants.DRONE_BATTERY_SIZE, Constants.DRONE_WEIGHT,
                Constants.DRONE_FRONTAL_AREA,Constants.DRONE_MOTOR_POWER,
                Constants.DEFAULT_STRING_VALUES,7.0);

        Courier courier = new Courier();

        stringBuilder.append(drone.toString());

        LinkedList<Address> aerialGoingPathE = new LinkedList<>();

        EnergyAerialMapController energyAerialMapController = deliverySimulationsController.getEnergyConsumptionAerialMap(aerialPathList,drone,courier);

        Address start = addressRegister.getAddressByID(1295);
        Address end = addressRegister.getAddressByID(1296);

        double aerialEnergyConsumptionGoing = energyAerialMapController.getMostEfficientRoute(start,end,aerialGoingPathE);


        stringBuilder.append(String.format("Aerial Going Energy Consumption: %.3f J \n\nPath: %s\n\n",aerialEnergyConsumptionGoing,aerialGoingPathE));

        LinkedList<Address> aerialReturningPathE = new LinkedList<>();
        double aerialReturingEnergyConsumption = 0;

        if(aerialGoingPathE.size() != 0) {
            aerialReturingEnergyConsumption = energyAerialMapController.getMostEfficientRoute(end, start, aerialReturningPathE);
            stringBuilder.append(String.format("Aerial Returning Energy Consumption: %.3f J \n\nPath: %s\n\n", aerialReturingEnergyConsumption, aerialReturningPathE));
        }
        else
            stringBuilder.append(String.format("Aerial Returning Path: %s\n\n", "not possible !"));
        double aerialEnergyConsumption = aerialReturingEnergyConsumption + aerialEnergyConsumptionGoing;
        stringBuilder.append("------------------------------- \n\n");
        stringBuilder.append(String.format("Total Aerial Enery Consumption: %.3f J \n\n",aerialEnergyConsumption));
        stringBuilder.append("------------------------------- \n\n");

        PrintFile.printFile("cenarioA2_0.txt", stringBuilder.toString());
    }

    @Test
    void cenarioA1_0(){
        StringBuilder stringBuilder = new StringBuilder();

        List<AerialPath> aerialPathList = new ArrayList<>();
        AerialPath aerialPath = new AerialPath(1295,1296,15000.0, 0.0, 0.0, Constants.D_AIR_DENSITY, Constants.D_AIR_DRAG_COEFFICIENT);
        AerialPath aerialPathReverse = new AerialPath(1296,1295,15000.0, 0.0, 0.0, Constants.D_AIR_DENSITY, Constants.D_AIR_DRAG_COEFFICIENT);

        aerialPathList.add(aerialPath);
        aerialPathList.add(aerialPathReverse);

        stringBuilder.append(String.format("Air Drag Coefficient: %f \n", Constants.S_AIR_DRAG_COEFFICIENT));
        stringBuilder.append(String.format("Vehicle medium speed: %f kmh | %f mps\n",Constants.D_VELOCITY, EnergyConsumptionAlgorithms.kmhToMps(Constants.D_VELOCITY)));

        Drone drone = new Drone(Constants.DEFAULT_INT_VALUES, Constants.DRONE_BATTERY_SIZE, Constants.DRONE_BATTERY_SIZE,
                Constants.DRONE_BATTERY_EFFICIENCY,Constants.DRONE_BATTERY_SIZE, Constants.DRONE_WEIGHT,
                Constants.DRONE_FRONTAL_AREA,Constants.DRONE_MOTOR_POWER,
                Constants.DEFAULT_STRING_VALUES,7.0);

        Courier courier = new Courier();

        stringBuilder.append(drone.toString());

        LinkedList<Address> aerialGoingPathE = new LinkedList<>();

        EnergyAerialMapController energyAerialMapController = deliverySimulationsController.getEnergyConsumptionAerialMap(aerialPathList,drone,courier);

        Address start = addressRegister.getAddressByID(1295);
        Address end = addressRegister.getAddressByID(1296);

        double aerialEnergyConsumptionGoing = energyAerialMapController.getMostEfficientRoute(start,end,aerialGoingPathE);


        stringBuilder.append(String.format("Aerial Going Energy Consumption: %.3f J \n\nPath: %s\n\n",aerialEnergyConsumptionGoing,aerialGoingPathE));

        LinkedList<Address> aerialReturningPathE = new LinkedList<>();
        double aerialReturingEnergyConsumption = 0;

        if(aerialGoingPathE.size() != 0) {
            aerialReturingEnergyConsumption = energyAerialMapController.getMostEfficientRoute(end, start, aerialReturningPathE);
            stringBuilder.append(String.format("Aerial Returning Energy Consumption: %.3f J \n\nPath: %s\n\n", aerialReturingEnergyConsumption, aerialReturningPathE));
        }
        else
            stringBuilder.append(String.format("Aerial Returning Path: %s\n\n", "not possible !"));
        double aerialEnergyConsumption = aerialReturingEnergyConsumption + aerialEnergyConsumptionGoing;
        stringBuilder.append("------------------------------- \n\n");
        stringBuilder.append(String.format("Total Aerial Enery Consumption: %.3f J \n\n",aerialEnergyConsumption));
        stringBuilder.append("------------------------------- \n\n");

        PrintFile.printFile("cenarioA1_0.txt", stringBuilder.toString());
    }
}
