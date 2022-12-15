package lapr.project.demonstration;


import lapr.project.controller.ApplicationEM;
import lapr.project.demonstration.CLASS.*;
import lapr.project.model.DomainClasses.*;
import lapr.project.model.Registers.ClientRegister;
import lapr.project.model.Registers.AddressRegister;
import lapr.project.model.Registers.PharmacyRegister;
import lapr.project.utils.EnergyConsumptionAlgorithms;
import lapr.project.utils.PrintFile;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

public class ComparationScenariosAllLocal {

    static PharmacyRegister pharmacyRegister;
    static AddressRegister addressRegister;
    static ClientRegister clientRegister;
    static DeliverySimulationsController deliverySimulationsController;
    static DistanceAerialMapController distanceAerialMapController;
    static EnergyAerialMapController energyAerialMapController;
    static DistanceLandMapController distanceLandMapController;
    static EnergyLandMapController energyLandMapController;
    static ReadFile readFile;
    static Scooter scooter = new Scooter();
    static Courier courier = new Courier();
    static Drone drone = new Drone();



    @BeforeAll
    static void setUp(){
        ApplicationEM applicationEM = ApplicationEM.getInstance();
        pharmacyRegister = applicationEM.getPlatform().getPharmacyRegister();
        addressRegister = applicationEM.getPlatform().getAddressRegister();
        clientRegister = applicationEM.getPlatform().getClientRegister();

        readFile = new ReadFile();
        readFile.readAddressFile();
        readFile.readGeoLocationFile();
        readFile.readClientsFile();
        readFile.readPharmaciesFile();
        readFile.readAirPathFile();
        readFile.readLandPathFile();

        deliverySimulationsController = new DeliverySimulationsController(readFile.addressRegister, readFile.geoLocationRegister);

        distanceLandMapController = deliverySimulationsController.getDistanceLandMap(readFile.landPaths);
        energyLandMapController = (EnergyLandMapController) deliverySimulationsController.getEnergyConsumptionLandMap(readFile.landPaths,scooter,courier);
        distanceAerialMapController = deliverySimulationsController.getDistanceAerialMap(readFile.aerialPaths);
        energyAerialMapController = deliverySimulationsController.getEnergyConsumptionAerialMap(readFile.aerialPaths,drone,courier);

    }

    @Test
    void cenarioC1_2(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("Air Drag Coefficient: %f \n", Constants.S_AIR_DRAG_COEFFICIENT));
        stringBuilder.append(String.format("Vehicle medium speed: %f kmh | %f mps\n",Constants.D_VELOCITY, EnergyConsumptionAlgorithms.kmhToMps(Constants.D_VELOCITY)));
        stringBuilder.append("------------------------------- \n\n");
        int pharmacyAddressID = pharmacyRegister.getPharmacyByNif("211231411").getAddressId(); //FARMACIA SA NIF = 211231411
        Address startAddress = addressRegister.getAddressByID(pharmacyAddressID); //ID 4 - FARMACIA SA
        int client1ID = clientRegister.getClientByEmail("centrocomercialcarvalhido@lapr.pt").getAddressId(); //centro comercia carvalhido
        int client2ID = clientRegister.getClientByEmail("ctt@lapr.pt").getAddressId();

        Address client1 = addressRegister.getAddressByID(client1ID);
        Address client2 = addressRegister.getAddressByID(client2ID);
        LinkedList<Address> stops = new LinkedList<>();
        stops.add(client1);
        stops.add(client2);

        LinkedList<Address> landGoingPath = new LinkedList<>();
        double distanceGoing = distanceLandMapController.getShortestRoute(startAddress, stops, landGoingPath);

        stringBuilder.append(String.format("Land Going Distance: %.3f m \n\nPath: %s\n\n",distanceGoing,landGoingPath));

        LinkedList<Address> landReturningPath = new LinkedList<>();
        double distanceReturning = distanceLandMapController.getShortestRoute(landGoingPath.getLast(), startAddress, landReturningPath);

        stringBuilder.append(String.format("Land Returning Distance: %.3f m \n\nPath: %s\n\n",distanceReturning,landReturningPath));

        double landDistance = distanceReturning + distanceGoing;
        stringBuilder.append("------------------------------- \n\n");
        stringBuilder.append(String.format("Total Land Distance: %.3f m\n\n",landDistance));
        stringBuilder.append("------------------------------- \n\n");

        LinkedList<Address> aerialGoingPath = new LinkedList<>();
        stops.add(client1);
        stops.add(client2);

        double aerialGoingDistance = distanceAerialMapController.getShortestRoute(startAddress, stops, aerialGoingPath);

        stringBuilder.append(String.format("Aerial Going Distance: %.3f m \n\nPath: %s\n\n",aerialGoingDistance,aerialGoingPath));

        LinkedList<Address> aerialReturningPath = new LinkedList<>();

        double aerialReturingDistance = 0;
        if(aerialGoingPath.size() != 0) {
            aerialReturingDistance = distanceAerialMapController.getShortestRoute(aerialGoingPath.getLast(), startAddress, aerialReturningPath);
            stringBuilder.append(String.format("Aerial Returning Distance: %.3f m \n\nPath: %s\n\n", aerialReturingDistance, aerialReturningPath));
        }
        else
            stringBuilder.append(String.format("Aerial Returning Path: %s\n\n", "not possible!"));

        double aerialDistance = aerialReturingDistance + aerialGoingDistance;
        stringBuilder.append("------------------------------- \n\n");
        stringBuilder.append(String.format("Total Aerial Distance: %.3f m\n\n",aerialDistance));
        stringBuilder.append("------------------------------- \n\n");

        LinkedList<Address> landGoingPathE = new LinkedList<>();
        stops.add(client1);
        stops.add(client2);
        double energyConsumptionGoingLand = energyLandMapController.getMostEfficientRoute(startAddress, stops, landGoingPathE);

        stringBuilder.append(String.format("Land Going Energy Consumption: %.3f J \n\nPath: %s\n\n",energyConsumptionGoingLand,landGoingPathE));

        LinkedList<Address> landReturningPathE = new LinkedList<>();
        double energyConsumptionReturningLand = 0;
        if(landGoingPathE.size() != 0){
            energyConsumptionReturningLand = energyLandMapController.getMostEfficientRoute(landGoingPathE.getLast(), startAddress, landReturningPathE);
            stringBuilder.append(String.format("Land Returning Energy Consumption:: %.3f J \n\nPath: %s\n\n",energyConsumptionReturningLand,landReturningPathE));
        }
        else
            stringBuilder.append(String.format("Land Returning Path: %s\n\n", "not possible!"));


        double landEnergyConsumption = energyConsumptionReturningLand + energyConsumptionGoingLand;
        stringBuilder.append("------------------------------- \n\n");
        stringBuilder.append(String.format("Total Land Energy Consumption:: %.3f J\n\n",landEnergyConsumption));
        stringBuilder.append("------------------------------- \n\n");

        LinkedList<Address> aerialGoingPathE = new LinkedList<>();
        stops.add(client1);
        stops.add(client2);
        double aerialEnergyConsumptionGoing = energyAerialMapController.getMostEfficientRoute(startAddress, stops, aerialGoingPathE);

        stringBuilder.append(String.format("Aerial Going Energy Consumption: %.3f J \n\nPath: %s\n\n",aerialEnergyConsumptionGoing,aerialGoingPathE));

        LinkedList<Address> aerialReturningPathE = new LinkedList<>();
        double aerialReturingEnergyConsumption = 0;

        if(aerialGoingPath.size() != 0) {
            aerialReturingEnergyConsumption = energyAerialMapController.getMostEfficientRoute(aerialGoingPathE.getLast(), startAddress, aerialReturningPathE);
            stringBuilder.append(String.format("Aerial Returning Energy Consumption: %.3f J \n\nPath: %s\n\n", aerialReturingEnergyConsumption, aerialReturningPathE));
        }
        else
            stringBuilder.append(String.format("Aerial Returning Path: %s\n\n", "not possible !"));
        double aerialEnergyConsumption = aerialReturingEnergyConsumption + aerialEnergyConsumptionGoing;
        stringBuilder.append("------------------------------- \n\n");
        stringBuilder.append(String.format("Total Aerial Enery Consumption: %.3f J \n\n",aerialEnergyConsumption));
        stringBuilder.append("------------------------------- \n\n");

        PrintFile.printFile("cenarioC1_2.txt", stringBuilder.toString());
    }

    @Test
    void cenarioC2_0(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("Air Drag Coefficient: %f \n", Constants.S_AIR_DRAG_COEFFICIENT));
        stringBuilder.append(String.format("Vehicle medium speed: %f kmh | %f mps\n",Constants.D_VELOCITY, EnergyConsumptionAlgorithms.kmhToMps(Constants.D_VELOCITY)));
        stringBuilder.append("------------------------------- \n\n");
        int pharmacyAddressID = pharmacyRegister.getPharmacyByNif("321414211").getAddressId(); //Farmácia Sousa Torres NIF = 321414211
        Address startAddress = addressRegister.getAddressByID(pharmacyAddressID);

        int client1ID = clientRegister.getClientByEmail("postocorreioforno@lapr.pt").getAddressId(); //posto correios forno
        int client2ID = clientRegister.getClientByEmail("centrodistribuicaocorreiosermesinde@lapr.pt").getAddressId(); //centro de distribuição de ermensinde

        Address client1 = addressRegister.getAddressByID(client1ID);
        Address client2 = addressRegister.getAddressByID(client2ID);
        LinkedList<Address> stops = new LinkedList<>();
        stops.add(client1);
        stops.add(client2);

        LinkedList<Address> landGoingPath = new LinkedList<>();
        double distanceGoing = distanceLandMapController.getShortestRoute(startAddress, stops, landGoingPath);

        stringBuilder.append(String.format("Land Going Distance: %.3f m \n\nPath: %s\n\n",distanceGoing,landGoingPath));

        LinkedList<Address> landReturningPath = new LinkedList<>();
        double distanceReturning = distanceLandMapController.getShortestRoute(landGoingPath.getLast(), startAddress, landReturningPath);

        stringBuilder.append(String.format("Land Returning Distance: %.3f m \n\nPath: %s\n\n",distanceReturning,landReturningPath));

        double landDistance = distanceReturning + distanceGoing;
        stringBuilder.append("------------------------------- \n\n");
        stringBuilder.append(String.format("Total Land Distance: %.3f m\n\n",landDistance));
        stringBuilder.append("------------------------------- \n\n");

        LinkedList<Address> aerialGoingPath = new LinkedList<>();
        stops.add(client1);
        stops.add(client2);

        double aerialGoingDistance = distanceAerialMapController.getShortestRoute(startAddress, stops, aerialGoingPath);

        stringBuilder.append(String.format("Aerial Going Distance: %.3f m \n\nPath: %s\n\n",aerialGoingDistance,aerialGoingPath));

        LinkedList<Address> aerialReturningPath = new LinkedList<>();

        double aerialReturingDistance = 0;
        if(aerialGoingPath.size() != 0) {
            aerialReturingDistance = distanceAerialMapController.getShortestRoute(aerialGoingPath.getLast(), startAddress, aerialReturningPath);
            stringBuilder.append(String.format("Aerial Returning Distance: %.3f m \n\nPath: %s\n\n", aerialReturingDistance, aerialReturningPath));
        }
        else
            stringBuilder.append(String.format("Aerial Returning Path: %s\n\n", "not possible!"));

        stringBuilder.append(String.format("Aerial Returning Distance: %.3f m \n\nPath: %s\n\n",aerialReturingDistance,aerialReturningPath));

        double aerialDistance = aerialReturingDistance + aerialGoingDistance;
        stringBuilder.append("------------------------------- \n\n");
        stringBuilder.append(String.format("Total Aerial Distance: %.3f m\n\n",aerialDistance));
        stringBuilder.append("------------------------------- \n\n");

        LinkedList<Address> landGoingPathE = new LinkedList<>();
        stops.add(client1);
        stops.add(client2);
        double energyConsumptionGoingLand = energyLandMapController.getMostEfficientRoute(startAddress, stops, landGoingPathE);

        stringBuilder.append(String.format("Land Going Energy Consumption: %.3f J \n\nPath: %s\n\n",energyConsumptionGoingLand,landGoingPathE));

        LinkedList<Address> landReturningPathE = new LinkedList<>();
        double energyConsumptionReturningLand = 0;
        if(landGoingPathE.size() != 0){
            energyConsumptionReturningLand = energyLandMapController.getMostEfficientRoute(landGoingPathE.getLast(), startAddress, landReturningPathE);
            stringBuilder.append(String.format("Land Returning Energy Consumption:: %.3f J \n\nPath: %s\n\n",energyConsumptionReturningLand,landReturningPathE));
        }
        else
            stringBuilder.append(String.format("Land Returning Path: %s\n\n", "not possible!"));


        double landEnergyConsumption = energyConsumptionReturningLand + energyConsumptionGoingLand;
        stringBuilder.append("------------------------------- \n\n");
        stringBuilder.append(String.format("Total Land Energy Consumption:: %.3f J\n\n",landEnergyConsumption));
        stringBuilder.append("------------------------------- \n\n");
        LinkedList<Address> aerialGoingPathE = new LinkedList<>();
        stops.add(client1);
        stops.add(client2);
        double aerialEnergyConsumptionGoing = energyAerialMapController.getMostEfficientRoute(startAddress, stops, aerialGoingPathE);

        stringBuilder.append(String.format("Aerial Going Energy Consumption: %.3f J \n\nPath: %s\n\n",aerialEnergyConsumptionGoing,aerialGoingPathE));

        LinkedList<Address> aerialReturningPathE = new LinkedList<>();
        double aerialReturingEnergyConsumption = 0;

        if(aerialGoingPath.size() != 0) {
            aerialReturingEnergyConsumption = energyAerialMapController.getMostEfficientRoute(aerialGoingPathE.getLast(), startAddress, aerialReturningPathE);
            stringBuilder.append(String.format("Aerial Returning Energy Consumption: %.3f J \n\nPath: %s\n\n", aerialReturingEnergyConsumption, aerialReturningPathE));
        }
        else
            stringBuilder.append(String.format("Aerial Returning Path: %s\n\n", "not possible !"));
        double aerialEnergyConsumption = aerialReturingEnergyConsumption + aerialEnergyConsumptionGoing;
        stringBuilder.append("------------------------------- \n\n");
        stringBuilder.append(String.format("Total Aerial Enery Consumption: %.3f J \n\n",aerialEnergyConsumption));
        stringBuilder.append("------------------------------- \n\n");

        PrintFile.printFile("cenarioC2_0.txt", stringBuilder.toString());
    }


    @Test
    void cenarioC3_0(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("Air Drag Coefficient: %f \n", Constants.S_AIR_DRAG_COEFFICIENT));
        stringBuilder.append(String.format("Vehicle medium speed: %f kmh | %f mps\n",Constants.D_VELOCITY, EnergyConsumptionAlgorithms.kmhToMps(Constants.D_VELOCITY)));
        stringBuilder.append("------------------------------- \n\n");
        int pharmacyAddressID = pharmacyRegister.getPharmacyByNif("213141411").getAddressId(); //“Farmácia Avenida NIF = 213141411
        Address startAddress = addressRegister.getAddressByID(pharmacyAddressID);

        int client1ID = clientRegister.getClientByEmail("correioscttcampoalegre@lapr.pt").getAddressId(); //correios ctt campo alegre
        int client2ID = clientRegister.getClientByEmail("pereiropostoffice@lapr.pt").getAddressId(); //pereiro posto ffice
        int client3ID = clientRegister.getClientByEmail("centrocomercialcarvalhido@lapr.pt").getAddressId(); //centro comercial carvalhido


        Address client1 = addressRegister.getAddressByID(client1ID);
        Address client2 = addressRegister.getAddressByID(client2ID);
        Address client3 = addressRegister.getAddressByID(client3ID);
        LinkedList<Address> stops = new LinkedList<>();
        stops.add(client1);
        stops.add(client2);
        stops.add(client3);

        LinkedList<Address> landGoingPath = new LinkedList<>();
        double distanceGoing = distanceLandMapController.getShortestRoute(startAddress, stops, landGoingPath);

        stringBuilder.append(String.format("Land Going Distance: %.3f m \n\nPath: %s\n\n",distanceGoing,landGoingPath));

        LinkedList<Address> landReturningPath = new LinkedList<>();
        double distanceReturning = distanceLandMapController.getShortestRoute(landGoingPath.getLast(), startAddress, landReturningPath);

        stringBuilder.append(String.format("Land Returning Distance: %.3f m \n\nPath: %s\n\n",distanceReturning,landReturningPath));

        double landDistance = distanceReturning + distanceGoing;
        stringBuilder.append("------------------------------- \n\n");
        stringBuilder.append(String.format("Total Land Distance: %.3f m\n\n",landDistance));
        stringBuilder.append("------------------------------- \n\n");

        LinkedList<Address> aerialGoingPath = new LinkedList<>();
        stops.add(client1);
        stops.add(client2);
        stops.add(client3);

        double aerialGoingDistance = distanceAerialMapController.getShortestRoute(startAddress, stops, aerialGoingPath);

        stringBuilder.append(String.format("Aerial Going Distance: %.3f m \n\nPath: %s\n\n",aerialGoingDistance,aerialGoingPath));

        LinkedList<Address> aerialReturningPath = new LinkedList<>();

        double aerialReturingDistance = 0;
        if(aerialGoingPath.size() != 0) {
            aerialReturingDistance = distanceAerialMapController.getShortestRoute(aerialGoingPath.getLast(), startAddress, aerialReturningPath);
            stringBuilder.append(String.format("Aerial Returning Distance: %.3f m \n\nPath: %s\n\n", aerialReturingDistance, aerialReturningPath));
        }
        else
            stringBuilder.append(String.format("Aerial Returning Path: %s\n\n", "not possible!"));

        stringBuilder.append(String.format("Aerial Returning Distance: %.3f m \n\nPath: %s\n\n",aerialReturingDistance,aerialReturningPath));

        double aerialDistance = aerialReturingDistance + aerialGoingDistance;
        stringBuilder.append("------------------------------- \n\n");
        stringBuilder.append(String.format("Total Aerial Distance: %.3f m\n\n",aerialDistance));
        stringBuilder.append("------------------------------- \n\n");

        LinkedList<Address> landGoingPathE = new LinkedList<>();
        stops.add(client1);
        stops.add(client2);
        stops.add(client3);

        double energyConsumptionGoingLand = energyLandMapController.getMostEfficientRoute(startAddress, stops, landGoingPathE);

        stringBuilder.append(String.format("Land Going Energy Consumption: %.3f J \n\nPath: %s\n\n",energyConsumptionGoingLand,landGoingPathE));

        LinkedList<Address> landReturningPathE = new LinkedList<>();
        double energyConsumptionReturningLand = 0;
        if(landGoingPathE.size() != 0){
            energyConsumptionReturningLand = energyLandMapController.getMostEfficientRoute(landGoingPathE.getLast(), startAddress, landReturningPathE);
            stringBuilder.append(String.format("Land Returning Energy Consumption:: %.3f J \n\nPath: %s\n\n",energyConsumptionReturningLand,landReturningPathE));
        }
        else
            stringBuilder.append(String.format("Land Returning Path: %s\n\n", "not possible!"));


        double landEnergyConsumption = energyConsumptionReturningLand + energyConsumptionGoingLand;
        stringBuilder.append("------------------------------- \n\n");
        stringBuilder.append(String.format("Total Land Energy Consumption:: %.3f J\n\n",landEnergyConsumption));
        stringBuilder.append("------------------------------- \n\n");
        LinkedList<Address> aerialGoingPathE = new LinkedList<>();
        stops.add(client1);
        stops.add(client2);
        stops.add(client3);

        double aerialEnergyConsumptionGoing = energyAerialMapController.getMostEfficientRoute(startAddress, stops, aerialGoingPathE);


        stringBuilder.append(String.format("Aerial Going Energy Consumption: %.3f J \n\nPath: %s\n\n",aerialEnergyConsumptionGoing,aerialGoingPathE));

        LinkedList<Address> aerialReturningPathE = new LinkedList<>();
        double aerialReturingEnergyConsumption = 0;

        if(aerialGoingPath.size() != 0) {
            aerialReturingEnergyConsumption = energyAerialMapController.getMostEfficientRoute(aerialGoingPathE.getLast(), startAddress, aerialReturningPathE);
            stringBuilder.append(String.format("Aerial Returning Energy Consumption: %.3f J \n\nPath: %s\n\n", aerialReturingEnergyConsumption, aerialReturningPathE));
        }
        else
            stringBuilder.append(String.format("Aerial Returning Path: %s\n\n", "not possible !"));
        double aerialEnergyConsumption = aerialReturingEnergyConsumption + aerialEnergyConsumptionGoing;
        stringBuilder.append("------------------------------- \n\n");
        stringBuilder.append(String.format("Total Aerial Enery Consumption: %.3f J \n\n",aerialEnergyConsumption));
        stringBuilder.append("------------------------------- \n\n");

        PrintFile.printFile("cenarioC3_0.txt", stringBuilder.toString());
    }

    @Test
    void cenarioC4_0(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("Air Drag Coefficient: %f \n", Constants.S_AIR_DRAG_COEFFICIENT));
        stringBuilder.append(String.format("Vehicle medium speed: %f kmh | %f mps\n",Constants.D_VELOCITY, EnergyConsumptionAlgorithms.kmhToMps(Constants.D_VELOCITY)));
        stringBuilder.append("------------------------------- \n\n");
        int pharmacyAddressID = pharmacyRegister.getPharmacyByNif("231241511").getAddressId(); //Farmácia Guifões NIF = 231241511
        Address startAddress = addressRegister.getAddressByID(pharmacyAddressID);

        int client1ID = clientRegister.getClientByEmail("marshoppingmatosinhos@lapr.pt").getAddressId(); //mar shopping matosinhos
        int client2ID = clientRegister.getClientByEmail("centrocomercialnewcity@lapr.pt").getAddressId(); //centro comercial newcity
        int client3ID = clientRegister.getClientByEmail("centrocomercialparque@lapr.pt").getAddressId(); //centro comercial parque


        Address client1 = addressRegister.getAddressByID(client1ID);
        Address client2 = addressRegister.getAddressByID(client2ID);
        Address client3 = addressRegister.getAddressByID(client3ID);
        LinkedList<Address> stops = new LinkedList<>();
        stops.add(client1);
        stops.add(client2);
        stops.add(client3);

        LinkedList<Address> landGoingPath = new LinkedList<>();
        double distanceGoing = distanceLandMapController.getShortestRoute(startAddress, stops, landGoingPath);

        stringBuilder.append(String.format("Land Going Distance: %.3f m \n\nPath: %s\n\n",distanceGoing,landGoingPath));

        LinkedList<Address> landReturningPath = new LinkedList<>();
        double distanceReturning = distanceLandMapController.getShortestRoute(landGoingPath.getLast(), startAddress, landReturningPath);

        stringBuilder.append(String.format("Land Returning Distance: %.3f m \n\nPath: %s\n\n",distanceReturning,landReturningPath));

        double landDistance = distanceReturning + distanceGoing;
        stringBuilder.append("------------------------------- \n\n");
        stringBuilder.append(String.format("Total Land Distance: %.3f m\n\n",landDistance));
        stringBuilder.append("------------------------------- \n\n");

        LinkedList<Address> aerialGoingPath = new LinkedList<>();
        stops.add(client1);
        stops.add(client2);
        stops.add(client3);

        double aerialGoingDistance = distanceAerialMapController.getShortestRoute(startAddress, stops, aerialGoingPath);

        stringBuilder.append(String.format("Aerial Going Distance: %.3f m \n\nPath: %s\n\n",aerialGoingDistance,aerialGoingPath));

        LinkedList<Address> aerialReturningPath = new LinkedList<>();

        double aerialReturingDistance = 0;
        if(aerialGoingPath.size() != 0) {
            aerialReturingDistance = distanceAerialMapController.getShortestRoute(aerialGoingPath.getLast(), startAddress, aerialReturningPath);
            stringBuilder.append(String.format("Aerial Returning Distance: %.3f m \n\nPath: %s\n\n", aerialReturingDistance, aerialReturningPath));
        }
        else
            stringBuilder.append(String.format("Aerial Returning Path: %s\n\n", "not possible!"));

        stringBuilder.append(String.format("Aerial Returning Distance: %.3f m \n\nPath: %s\n\n",aerialReturingDistance,aerialReturningPath));

        double aerialDistance = aerialReturingDistance + aerialGoingDistance;
        stringBuilder.append("------------------------------- \n\n");
        stringBuilder.append(String.format("Total Aerial Distance: %.3f m\n\n",aerialDistance));
        stringBuilder.append("------------------------------- \n\n");

        LinkedList<Address> landGoingPathE = new LinkedList<>();
        stops.add(client1);
        stops.add(client2);
        stops.add(client3);

        double energyConsumptionGoingLand = energyLandMapController.getMostEfficientRoute(startAddress, stops, landGoingPathE);

        stringBuilder.append(String.format("Land Going Energy Consumption: %.3f J \n\nPath: %s\n\n",energyConsumptionGoingLand,landGoingPathE));

        LinkedList<Address> landReturningPathE = new LinkedList<>();
        double energyConsumptionReturningLand = 0;
        if(landGoingPathE.size() != 0){
            energyConsumptionReturningLand = energyLandMapController.getMostEfficientRoute(landGoingPathE.getLast(), startAddress, landReturningPathE);
            stringBuilder.append(String.format("Land Returning Energy Consumption:: %.3f J \n\nPath: %s\n\n",energyConsumptionReturningLand,landReturningPathE));
        }
        else
            stringBuilder.append(String.format("Land Returning Path: %s\n\n", "not possible!"));


        double landEnergyConsumption = energyConsumptionReturningLand + energyConsumptionGoingLand;
        stringBuilder.append("------------------------------- \n\n");
        stringBuilder.append(String.format("Total Land Energy Consumption:: %.3f J\n\n",landEnergyConsumption));
        stringBuilder.append("------------------------------- \n\n");
        LinkedList<Address> aerialGoingPathE = new LinkedList<>();
        stops.add(client1);
        stops.add(client2);
        stops.add(client3);

        double aerialEnergyConsumptionGoing = energyAerialMapController.getMostEfficientRoute(startAddress, stops, aerialGoingPathE);


        stringBuilder.append(String.format("Aerial Going Energy Consumption: %.3f J \n\nPath: %s\n\n",aerialEnergyConsumptionGoing,aerialGoingPathE));

        LinkedList<Address> aerialReturningPathE = new LinkedList<>();
        double aerialReturingEnergyConsumption = 0;

        if(aerialGoingPath.size() != 0) {
            aerialReturingEnergyConsumption = energyAerialMapController.getMostEfficientRoute(aerialGoingPathE.getLast(), startAddress, aerialReturningPathE);
            stringBuilder.append(String.format("Aerial Returning Energy Consumption: %.3f J \n\nPath: %s\n\n", aerialReturingEnergyConsumption, aerialReturningPathE));
        }
        else
            stringBuilder.append(String.format("Aerial Returning Path: %s\n\n", "not possible !"));
        double aerialEnergyConsumption = aerialReturingEnergyConsumption + aerialEnergyConsumptionGoing;
        stringBuilder.append("------------------------------- \n\n");
        stringBuilder.append(String.format("Total Aerial Enery Consumption: %.3f J \n\n",aerialEnergyConsumption));
        stringBuilder.append("------------------------------- \n\n");

        PrintFile.printFile("cenarioC4_0.txt", stringBuilder.toString());
    }

    @Test
    void cenarioC5_0(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("Air Drag Coefficient: %f \n", Constants.S_AIR_DRAG_COEFFICIENT));
        stringBuilder.append(String.format("Vehicle medium speed: %f kmh | %f mps\n",Constants.D_VELOCITY, EnergyConsumptionAlgorithms.kmhToMps(Constants.D_VELOCITY)));
        stringBuilder.append("------------------------------- \n\n");
        int pharmacyAddressID = pharmacyRegister.getPharmacyByNif("231313124").getAddressId(); //Farmácia Sá da Bandeira NIF = 231313124
        Address startAddress = addressRegister.getAddressByID(pharmacyAddressID);

        int client1ID = clientRegister.getClientByEmail("espiracolossal@lapr.pt").getAddressId(); //espira colossal
        int client2ID = clientRegister.getClientByEmail("centrocomercialstop@lapr.pt").getAddressId(); //centro comercial stop

        Address client1 = addressRegister.getAddressByID(client1ID);
        Address client2 = addressRegister.getAddressByID(client2ID);
        LinkedList<Address> stops = new LinkedList<>();
        stops.add(client1);
        stops.add(client2);

        LinkedList<Address> landGoingPath = new LinkedList<>();
        double distanceGoing = distanceLandMapController.getShortestRoute(startAddress, stops, landGoingPath);

        stringBuilder.append(String.format("Land Going Distance: %.3f m \n\nPath: %s\n\n",distanceGoing,landGoingPath));

        LinkedList<Address> landReturningPath = new LinkedList<>();
        double distanceReturning = distanceLandMapController.getShortestRoute(landGoingPath.getLast(), startAddress, landReturningPath);

        stringBuilder.append(String.format("Land Returning Distance: %.3f m \n\nPath: %s\n\n",distanceReturning,landReturningPath));

        double landDistance = distanceReturning + distanceGoing;
        stringBuilder.append("------------------------------- \n\n");
        stringBuilder.append(String.format("Total Land Distance: %.3f m\n\n",landDistance));
        stringBuilder.append("------------------------------- \n\n");

        LinkedList<Address> aerialGoingPath = new LinkedList<>();
        stops.add(client1);
        stops.add(client2);

        double aerialGoingDistance = distanceAerialMapController.getShortestRoute(startAddress, stops, aerialGoingPath);

        stringBuilder.append(String.format("Aerial Going Distance: %.3f m \n\nPath: %s\n\n",aerialGoingDistance,aerialGoingPath));

        LinkedList<Address> aerialReturningPath = new LinkedList<>();

        double aerialReturingDistance = 0;
        if(aerialGoingPath.size() != 0) {
            aerialReturingDistance = distanceAerialMapController.getShortestRoute(aerialGoingPath.getLast(), startAddress, aerialReturningPath);
            stringBuilder.append(String.format("Aerial Returning Distance: %.3f m \n\nPath: %s\n\n", aerialReturingDistance, aerialReturningPath));
        }
        else
            stringBuilder.append(String.format("Aerial Returning Path: %s\n\n", "not possible!"));

        stringBuilder.append(String.format("Aerial Returning Distance: %.3f m \n\nPath: %s\n\n",aerialReturingDistance,aerialReturningPath));

        double aerialDistance = aerialReturingDistance + aerialGoingDistance;
        stringBuilder.append("------------------------------- \n\n");
        stringBuilder.append(String.format("Total Aerial Distance: %.3f m\n\n",aerialDistance));
        stringBuilder.append("------------------------------- \n\n");

        LinkedList<Address> landGoingPathE = new LinkedList<>();
        stops.add(client1);
        stops.add(client2);
        double energyConsumptionGoingLand = energyLandMapController.getMostEfficientRoute(startAddress, stops, landGoingPathE);

        stringBuilder.append(String.format("Land Going Energy Consumption: %.3f J \n\nPath: %s\n\n",energyConsumptionGoingLand,landGoingPathE));

        LinkedList<Address> landReturningPathE = new LinkedList<>();
        double energyConsumptionReturningLand = 0;
        if(landGoingPathE.size() != 0){
            energyConsumptionReturningLand = energyLandMapController.getMostEfficientRoute(landGoingPathE.getLast(), startAddress, landReturningPathE);
            stringBuilder.append(String.format("Land Returning Energy Consumption:: %.3f J \n\nPath: %s\n\n",energyConsumptionReturningLand,landReturningPathE));
        }
        else
            stringBuilder.append(String.format("Land Returning Path: %s\n\n", "not possible!"));


        double landEnergyConsumption = energyConsumptionReturningLand + energyConsumptionGoingLand;
        stringBuilder.append("------------------------------- \n\n");
        stringBuilder.append(String.format("Total Land Energy Consumption:: %.3f J\n\n",landEnergyConsumption));
        stringBuilder.append("------------------------------- \n\n");
        LinkedList<Address> aerialGoingPathE = new LinkedList<>();
        stops.add(client1);
        stops.add(client2);
        double aerialEnergyConsumptionGoing = energyAerialMapController.getMostEfficientRoute(startAddress, stops, aerialGoingPathE);

        stringBuilder.append(String.format("Aerial Going Energy Consumption: %.3f J \n\nPath: %s\n\n",aerialEnergyConsumptionGoing,aerialGoingPathE));

        LinkedList<Address> aerialReturningPathE = new LinkedList<>();
        double aerialReturingEnergyConsumption = 0;

        if(aerialGoingPath.size() != 0) {
            aerialReturingEnergyConsumption = energyAerialMapController.getMostEfficientRoute(aerialGoingPathE.getLast(), startAddress, aerialReturningPathE);
            stringBuilder.append(String.format("Aerial Returning Energy Consumption: %.3f J \n\nPath: %s\n\n", aerialReturingEnergyConsumption, aerialReturningPathE));
        }
        else
            stringBuilder.append(String.format("Aerial Returning Path: %s\n\n", "not possible !"));
        double aerialEnergyConsumption = aerialReturingEnergyConsumption + aerialEnergyConsumptionGoing;
        stringBuilder.append("------------------------------- \n\n");
        stringBuilder.append(String.format("Total Aerial Enery Consumption: %.3f J \n\n",aerialEnergyConsumption));
        stringBuilder.append("------------------------------- \n\n");

        PrintFile.printFile("cenarioC5_0.txt", stringBuilder.toString());
    }
}
