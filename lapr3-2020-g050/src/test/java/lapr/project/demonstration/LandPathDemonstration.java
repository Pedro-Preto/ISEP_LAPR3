package lapr.project.demonstration;

import lapr.project.controller.*;
import lapr.project.model.DomainClasses.*;
import lapr.project.model.Registers.AddressRegister;
import lapr.project.model.Registers.GeoLocationRegister;

import java.util.ArrayList;
import java.util.List;
import lapr.project.utils.EnergyConsumptionAlgorithms;
import lapr.project.utils.PrintFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;


public class LandPathDemonstration {

    DeliverySimulationsController deliverySimulationsController;

    @BeforeEach
    void setUp(){
        deliverySimulationsController = new DeliverySimulationsController();
    }

    @Test
    void cenarioT1_0(){
        StringBuilder stringBuilder = new StringBuilder();
        ApplicationEM applicationEM = ApplicationEM.getInstance();
        Platform platform = applicationEM.getPlatform();
        AddressRegister addressRegister = platform.getAddressRegister();
        GeoLocationRegister geoLocationRegister = platform.getGeoLocationRegister();

        addressRegister.addAddress(75,"street1", "doornr1", "city1", "country1");
        addressRegister.addAddress(76,"street2", "doornr2", "city2", "country2");

        geoLocationRegister.addGeoLocation(75, 1.0,1.0,1,75);
        geoLocationRegister.addGeoLocation(76, 2.0,2.0,2,76);

        List<LandPath> landPathList = new ArrayList<>();
        LandPath landPath = new LandPath(75,76,15000.0, 0.0, 0.0, 0.5, 0.0);
        LandPath landPathReverse = new LandPath(76,75,15000.0, 0.0, 0.0, 0.5, 0.0);

        landPathList.add(landPath);
        landPathList.add(landPathReverse);

        stringBuilder.append(String.format("Air Drag Coefficient: %f \n", Constants.S_AIR_DRAG_COEFFICIENT));
        stringBuilder.append(String.format("Vehicle medium speed: %f kmh | %f mps\n",Constants.D_VELOCITY, EnergyConsumptionAlgorithms.kmhToMps(Constants.D_VELOCITY)));

        Scooter scooter = new Scooter();
        Courier courier = new Courier();

        stringBuilder.append(String.format("Courier mass: %f kg\n",courier.getWeight()));
        stringBuilder.append(scooter.toString());

        for(LandPath pa : landPathList)
            stringBuilder.append(pa.toString());

        EnergyLandMapController mapController = deliverySimulationsController.getEnergyOnAShortLandPath(landPathList, scooter, courier);

        LinkedList<Address> shortestPathGoing = new LinkedList<>();

        Address start = addressRegister.getAddressByID(75);
        Address end = addressRegister.getAddressByID(76);
        double energyConsumptionGoing = mapController.getMostEfficientRoute(start,end,shortestPathGoing) / 1000;

        stringBuilder.append(((MapController) mapController).mapToString());

        stringBuilder.append(String.format("Energy Consumption Going: %.3f kJ\n\nPath: %s\n\n", energyConsumptionGoing,shortestPathGoing));

        LinkedList<Address> shortestPathReturning = new LinkedList<>();

        double energyConsumptionReturning = mapController.getMostEfficientRoute(end,start,shortestPathReturning) / 1000;
        stringBuilder.append(String.format("Energy Consumption Returning: %.3f kJ\n\nPath: %s\n\n",energyConsumptionReturning,shortestPathReturning));

        double energyConsumption = energyConsumptionGoing + energyConsumptionReturning;

        stringBuilder.append(String.format("Energy Consumption Total: %.3f kJ\n\n",energyConsumption));

        double scooterTotalEnergy = EnergyConsumptionAlgorithms.batteryEnergy(scooter.getBatterySize(),scooter.getBatteryEfficiency());
        stringBuilder.append(String.format("Scooter battery energy: " + scooterTotalEnergy + "kJ\n"));
        if(energyConsumption < scooterTotalEnergy)
            stringBuilder.append("The scooter can do the path\n\n");
        else
            stringBuilder.append("The scooter can't do the path\n\n");
        PrintFile.printFile("cenarioT1_0.txt", stringBuilder.toString());
    }

    @Test
    void cenarioT2_1() {
        StringBuilder stringBuilder = new StringBuilder();
        ApplicationEM applicationEM = ApplicationEM.getInstance();
        Platform platform = applicationEM.getPlatform();
        AddressRegister addressRegister = platform.getAddressRegister();
        GeoLocationRegister geoLocationRegister = platform.getGeoLocationRegister();

        Address address = new Address(1300, "Street A", "1", "Porto", "Portugal");
        Pharmacy pharmacy = new Pharmacy("1300DemonstrationPharmacy","Pharmacy SP",1300);

        List<LandPath> paths = new LinkedList<>();

        addressRegister.addAddress(201, "Rua A","01","Porto","Portugal");
        addressRegister.addAddress(202, "Rua B","02","Porto","Portugal");
        addressRegister.addAddress(203, "Rua 3","03","Porto","Portugal");

        geoLocationRegister.addGeoLocation(201, 10.0 ,10.0, 10, 201);
        geoLocationRegister.addGeoLocation(202, 10.0 ,10.0, 10, 202);
        geoLocationRegister.addGeoLocation(203, 10.0 ,10.0, 10, 203);

        LandPath first = new LandPath(201,202,10000.0,0.0,0.0,0.5,3.0);
        LandPath second = new LandPath(202,203,5000.0,0.0,0.0,0.5,3.0);
        LandPath firstReverse = new LandPath(202,201,10000.0,0.0,0.0,0.5,-3.0);
        LandPath secondReverse = new LandPath(203,202,5000.0,0.0,0.0,0.5,-3.0);

        paths.add(first);
        paths.add(second);
        paths.add(firstReverse);
        paths.add(secondReverse);

        Scooter scooter = new Scooter(201,2800,0,0.6,3000,50,0.3,3000,"1300DemonstrationPharmacy","140QRCODE");

        Courier courier = new Courier("201","courier","couriermodelo",
                "12321314","protw",80.0,0.0,"1300DemonstrationPharmacy");

        stringBuilder.append(String.format("Air Drag Coefficient: %f \n", Constants.S_AIR_DRAG_COEFFICIENT));
        stringBuilder.append(String.format("Vehicle medium speed: %f kmh | %f mps\n",Constants.D_VELOCITY, EnergyConsumptionAlgorithms.kmhToMps(Constants.D_VELOCITY)));

        EnergyLandMapController mapController = deliverySimulationsController.getEnergyOnAShortLandPath(paths, scooter,courier);

        stringBuilder.append(((MapController) mapController).mapToString());

        LinkedList<Address> shortestPathGoing = new LinkedList<>();

        Address start = addressRegister.getAddressByID(201);
        Address end = addressRegister.getAddressByID(203);
        double energyConsumptionGoing = mapController.getMostEfficientRoute(start,end,shortestPathGoing) / 1000;

        stringBuilder.append(String.format("Energy Consumption: %.3f kJ \n\nPath: %s\n\n",energyConsumptionGoing,shortestPathGoing));

        LinkedList<Address> shortestPathReturning = new LinkedList<>();

        double energyConsumptionReturning = mapController.getMostEfficientRoute(end,start,shortestPathReturning) / 1000;

        stringBuilder.append(String.format("Energy Consumption: %.3f kJ \n\nPath: %s\n\n",energyConsumptionReturning,shortestPathReturning));

        double energyConsumption = energyConsumptionGoing + energyConsumptionReturning;

        stringBuilder.append(String.format("Energy Consumption Total: %.3f kJ\n\n",energyConsumption));

//        double scooterTotalEnergy = EnergyConsumptionAlgorithms.batteryEnergy(scooter.getBatterySize(),scooter.getBatteryEfficiency());
//        stringBuilder.append(String.format("Scooter battery energy: " + scooterTotalEnergy + "kJ\n"));
//        if(energyConsumption < scooterTotalEnergy)
//            stringBuilder.append("The scooter can do the path\n\n");
//        else
//            stringBuilder.append("The scooter can't do the path\n\n");
        PrintFile.printFile("cenarioT2_1.txt", stringBuilder.toString());


    }

    @Test
    void cenarioT3_0() {
        StringBuilder stringBuilder = new StringBuilder();
        ApplicationEM applicationEM = ApplicationEM.getInstance();
        Platform platform = applicationEM.getPlatform();
        AddressRegister addressRegister = platform.getAddressRegister();
        GeoLocationRegister geoLocationRegister = platform.getGeoLocationRegister();

        Address address = new Address(1400, "Street A", "1", "Porto", "Portugal");
        Pharmacy pharmacy = new Pharmacy("1400DemonstrationPharmacy","Pharmacy SP",1400);

        List<LandPath> paths = new LinkedList<>();

        addressRegister.addAddress(301, "Rua A","01","Porto","Portugal");
        addressRegister.addAddress(302, "Rua B","02","Porto","Portugal");
        addressRegister.addAddress(303, "Rua C", "03", "Porto", "Portugal");

        geoLocationRegister.addGeoLocation(301, 10.0 ,10.0, 10, 301);
        geoLocationRegister.addGeoLocation(302, 10.0 ,10.0, 10, 302);
        geoLocationRegister.addGeoLocation(303, 10.0 ,10.0, 10, 303);


        LandPath intermediate = new LandPath(301,302,10000.0,0.0,0.0,0.5,3.0);
        LandPath second = new LandPath(302,303,5000.0,0.0,0.0,0.5,0.0);
        LandPath intermediateReverse = new LandPath(302,301,10000.0,0.0,0.0,0.5,0.0);
        LandPath secondReverse = new LandPath(303,302,5000.0,0.0,0.0,0.5,-3.0);

        paths.add(intermediate);
        paths.add(second);
        paths.add(intermediateReverse);
        paths.add(secondReverse);

        Scooter scooter = new Scooter(301,2800,0,0.6,3000,50,0.3,3000,"1400DemonstrationPharmacy","140QRCODE");

        Courier courier = new Courier("301","courier","couriermodelo",
                "12321314","protw",80.0,0.0,"1400DemonstrationPharmacy");

        stringBuilder.append(String.format("Air Drag Coefficient: %f \n", Constants.S_AIR_DRAG_COEFFICIENT));
        stringBuilder.append(String.format("Vehicle medium speed: %f kmh | %f mps\n",Constants.D_VELOCITY, EnergyConsumptionAlgorithms.kmhToMps(Constants.D_VELOCITY)));

        EnergyLandMapController mapController = deliverySimulationsController.getEnergyOnAShortLandPath(paths, scooter,courier);

        stringBuilder.append(((MapController) mapController).mapToString());

        LinkedList<Address> shortestPathGoing = new LinkedList<>();

        Address start = addressRegister.getAddressByID(301);
        Address end = addressRegister.getAddressByID(303);
        double energyConsumptionGoing = mapController.getMostEfficientRoute(start,end,shortestPathGoing) / 1000;

        stringBuilder.append(String.format("Energy Consumption: %.3f kJ \n\nPath: %s\n\n",energyConsumptionGoing,shortestPathGoing));

        LinkedList<Address> shortestPathReturning = new LinkedList<>();

        double energyConsumptionReturning = mapController.getMostEfficientRoute(end,start,shortestPathReturning) / 1000;

        stringBuilder.append(String.format("Energy Consumption: %.3f kJ \n\nPath: %s\n\n",energyConsumptionReturning,shortestPathReturning));

        double energyConsumption = energyConsumptionGoing + energyConsumptionReturning;

        stringBuilder.append(String.format("Energy Consumption Total: %.3f kJ\n\n",energyConsumption));

//        double scooterTotalEnergy = EnergyConsumptionAlgorithms.batteryEnergy(scooter.getBatterySize(),scooter.getBatteryEfficiency());
//        stringBuilder.append(String.format("Scooter battery energy: " + scooterTotalEnergy + "kJ\n"));
//        if(energyConsumption < scooterTotalEnergy)
//            stringBuilder.append("The scooter can do the path\n\n");
//        else
//            stringBuilder.append("The scooter can't do the path\n\n");
        PrintFile.printFile("cenarioT3_0.txt", stringBuilder.toString());


    }

    @Test
    void cenarioT4_1() {
        StringBuilder stringBuilder = new StringBuilder();
        ApplicationEM applicationEM = ApplicationEM.getInstance();
        Platform platform = applicationEM.getPlatform();
        AddressRegister addressRegister = platform.getAddressRegister();
        GeoLocationRegister geoLocationRegister = platform.getGeoLocationRegister();

        Address address = new Address(1500, "Street A", "1", "Porto", "Portugal");
        Pharmacy pharmacy = new Pharmacy("1500DemonstrationPharmacy","Pharmacy SP",1500);

        List<LandPath> paths = new LinkedList<>();

        addressRegister.addAddress(401, "Rua A","01","Porto","Portugal");
        addressRegister.addAddress(402, "Rua B","02","Porto","Portugal");
        addressRegister.addAddress(403, "Rua C", "03", "Porto", "Portugal");

        geoLocationRegister.addGeoLocation(401, 10.0 ,10.0, 10, 401);
        geoLocationRegister.addGeoLocation(402, 10.0 ,10.0, 10, 402);
        geoLocationRegister.addGeoLocation(403, 10.0 ,10.0, 10, 403);


        LandPath intermediate = new LandPath(401,402,10000.0,0.0,0.0,0.5,3.0);
        LandPath second = new LandPath(402,403,5000.0,0.0,0.0,0.5,0.0);
        LandPath intermediateReverse = new LandPath(402,401,10000.0,0.0,0.0,0.5,0.0);
        LandPath secondReverse = new LandPath(403,402,5000.0,0.0,0.0,0.5,-3.0);

        paths.add(intermediate);
        paths.add(second);
        paths.add(intermediateReverse);
        paths.add(secondReverse);

        Scooter scooter = new Scooter(401,2800,0,0.6,3000,50,0.3,3000,"1500DemonstrationPharmacy","140QRCODE");

        Courier courier = new Courier("401","courier","couriermodelo",
                "12321314","protw",80.0,50.0,"1500DemonstrationPharmacy");

        stringBuilder.append(String.format("Air Drag Coefficient: %f \n", Constants.S_AIR_DRAG_COEFFICIENT));
        stringBuilder.append(String.format("Vehicle medium speed: %f kmh | %f mps\n",Constants.D_VELOCITY, EnergyConsumptionAlgorithms.kmhToMps(Constants.D_VELOCITY)));

        EnergyLandMapController mapController = deliverySimulationsController.getEnergyOnAShortLandPath(paths, scooter,courier);

        stringBuilder.append(((MapController) mapController).mapToString());

        LinkedList<Address> shortestPathGoing = new LinkedList<>();

        Address start = addressRegister.getAddressByID(401);
        Address end = addressRegister.getAddressByID(403);
        double energyConsumptionGoing = mapController.getMostEfficientRoute(start,end,shortestPathGoing) / 1000;

        stringBuilder.append(String.format("Energy Consumption: %.3f kJ \n\nPath: %s\n\n",energyConsumptionGoing,shortestPathGoing));

        LinkedList<Address> shortestPathReturning = new LinkedList<>();

        double energyConsumptionReturning = mapController.getMostEfficientRoute(end,start,shortestPathReturning) / 1000;

        stringBuilder.append(String.format("Energy Consumption: %.3f kJ \n\nPath: %s\n\n",energyConsumptionReturning,shortestPathReturning));

        double energyConsumption = energyConsumptionGoing + energyConsumptionReturning;

        stringBuilder.append(String.format("Energy Consumption Total: %.3f kJ\n\n",energyConsumption));

//        double scooterTotalEnergy = EnergyConsumptionAlgorithms.batteryEnergy(scooter.getBatterySize(),scooter.getBatteryEfficiency());
//        stringBuilder.append(String.format("Scooter battery energy: " + scooterTotalEnergy + "kJ\n"));
//        if(energyConsumption < scooterTotalEnergy)
//            stringBuilder.append("The scooter can do the path\n\n");
//        else
//            stringBuilder.append("The scooter can't do the path\n\n");
        PrintFile.printFile("cenarioT4_1.txt", stringBuilder.toString());

    }

    @Test
    void cenarioT5_1() {
        StringBuilder stringBuilder = new StringBuilder();
        ApplicationEM applicationEM = ApplicationEM.getInstance();
        Platform platform = applicationEM.getPlatform();
        AddressRegister addressRegister = platform.getAddressRegister();
        GeoLocationRegister geoLocationRegister = platform.getGeoLocationRegister();

        Address address = new Address(1600, "Street A", "1", "Porto", "Portugal");
        Pharmacy pharmacy = new Pharmacy("1600DemonstrationPharmacy","Pharmacy SP",1600);

        List<LandPath> paths = new LinkedList<>();

        addressRegister.addAddress(501, "Rua A","01","Porto","Portugal");
        addressRegister.addAddress(502, "Rua B","02","Porto","Portugal");
        addressRegister.addAddress(503, "Rua C", "03", "Porto", "Portugal");

        geoLocationRegister.addGeoLocation(501, 10.0 ,10.0, 10, 501);
        geoLocationRegister.addGeoLocation(502, 10.0 ,10.0, 10, 502);
        geoLocationRegister.addGeoLocation(503, 10.0 ,10.0, 10, 503);


        LandPath intermediate = new LandPath(501,502,10000.0,0.0,0.0,0.5,3.0);
        LandPath second = new LandPath(502,503,5000.0,0.0,0.0,0.5,-5.0);
        LandPath intermediateReverse = new LandPath(502,501,10000.0,0.0,0.0,0.5,5.0);
        LandPath secondReverse = new LandPath(503,502,5000.0,0.0,0.0,0.5,-3.0);

        paths.add(intermediate);
        paths.add(second);
        paths.add(intermediateReverse);
        paths.add(secondReverse);

        Scooter scooter = new Scooter(501,2800,0,0.6,3000,50,0.3,3000,"1600DemonstrationPharmacy","140QRCODE");

        Courier courier = new Courier("501","courier","couriermodelo",
                "12321314","protw",80.0,50.0,"1600DemonstrationPharmacy");

        stringBuilder.append(String.format("Air Drag Coefficient: %f \n", Constants.S_AIR_DRAG_COEFFICIENT));
        stringBuilder.append(String.format("Vehicle medium speed: %f kmh | %f mps\n",Constants.D_VELOCITY, EnergyConsumptionAlgorithms.kmhToMps(Constants.D_VELOCITY)));

        EnergyLandMapController mapController = deliverySimulationsController.getEnergyOnAShortLandPath(paths, scooter,courier);

        stringBuilder.append(((MapController) mapController).mapToString());

        LinkedList<Address> shortestPathGoing = new LinkedList<>();

        Address start = addressRegister.getAddressByID(501);
        Address end = addressRegister.getAddressByID(503);
        double energyConsumptionGoing = mapController.getMostEfficientRoute(start,end,shortestPathGoing) / 1000;

        stringBuilder.append(String.format("Energy Consumption: %.3f kJ \n\nPath: %s\n\n",energyConsumptionGoing,shortestPathGoing));

        LinkedList<Address> shortestPathReturning = new LinkedList<>();

        double energyConsumptionReturning = mapController.getMostEfficientRoute(end,start,shortestPathReturning) / 1000;

        stringBuilder.append(String.format("Energy Consumption: %.3f kJ \n\nPath: %s\n\n",energyConsumptionReturning,shortestPathReturning));

        double energyConsumption = energyConsumptionGoing + energyConsumptionReturning;

        stringBuilder.append(String.format("Energy Consumption Total: %.3f kJ\n\n",energyConsumption));

//        double scooterTotalEnergy = EnergyConsumptionAlgorithms.batteryEnergy(scooter.getBatterySize(),scooter.getBatteryEfficiency());
//        stringBuilder.append(String.format("Scooter battery energy: " + scooterTotalEnergy + "kJ\n"));
//        if(energyConsumption < scooterTotalEnergy)
//            stringBuilder.append("The scooter can do the path\n\n");
//        else
//            stringBuilder.append("The scooter can't do the path\n\n");
        PrintFile.printFile("cenarioT5_1.txt", stringBuilder.toString());

    }

    @Test
    void cenarioT6_1() {
        StringBuilder stringBuilder = new StringBuilder();
        ApplicationEM applicationEM = ApplicationEM.getInstance();
        Platform platform = applicationEM.getPlatform();
        AddressRegister addressRegister = platform.getAddressRegister();
        GeoLocationRegister geoLocationRegister = platform.getGeoLocationRegister();

        Address address = new Address(1700, "Street A", "1", "Porto", "Portugal");
        Pharmacy pharmacy = new Pharmacy("1700DemonstrationPharmacy","Pharmacy SP",1700);

        List<LandPath> paths = new LinkedList<>();

        addressRegister.addAddress(601, "Rua A","01","Porto","Portugal");
        addressRegister.addAddress(602, "Rua B","02","Porto","Portugal");
        addressRegister.addAddress(603, "Rua C", "03", "Porto", "Portugal");

        geoLocationRegister.addGeoLocation(601, 10.0 ,10.0, 10, 601);
        geoLocationRegister.addGeoLocation(602, 10.0 ,10.0, 10, 602);
        geoLocationRegister.addGeoLocation(603, 10.0 ,10.0, 10, 603);


        LandPath intermediate = new LandPath(601,602,10000.0,180.0,5.0,0.5,3.0);
        LandPath second = new LandPath(602,603,5000.0,180.0,5.0,0.5,-5.0);
        LandPath intermediateReverse = new LandPath(602,601,10000.0,0.0,5.0,0.5,5.0);
        LandPath secondReverse = new LandPath(603,602,5000.0,0.0,5.0,0.5,-3.0);

        paths.add(intermediate);
        paths.add(second);
        paths.add(intermediateReverse);
        paths.add(secondReverse);

        Scooter scooter = new Scooter(601,2800,0,0.6,3000,50,0.3,3000,"1700DemonstrationPharmacy","140QRCODE");

        Courier courier = new Courier("601","courier","couriermodelo",
                "12321314","protw",80.0,50.0,"1700DemonstrationPharmacy");

        stringBuilder.append(String.format("Air Drag Coefficient: %f \n", Constants.S_AIR_DRAG_COEFFICIENT));
        stringBuilder.append(String.format("Vehicle medium speed: %f kmh | %f mps\n",Constants.D_VELOCITY, EnergyConsumptionAlgorithms.kmhToMps(Constants.D_VELOCITY)));

        EnergyLandMapController mapController = deliverySimulationsController.getEnergyOnAShortLandPath(paths, scooter,courier);

        stringBuilder.append(((MapController) mapController).mapToString());

        LinkedList<Address> shortestPathGoing = new LinkedList<>();

        Address start = addressRegister.getAddressByID(601);
        Address end = addressRegister.getAddressByID(603);
        double energyConsumptionGoing = mapController.getMostEfficientRoute(start,end,shortestPathGoing) / 1000;

        stringBuilder.append(String.format("Energy Consumption: %.3f kJ \n\nPath: %s\n\n",energyConsumptionGoing,shortestPathGoing));

        LinkedList<Address> shortestPathReturning = new LinkedList<>();

        double energyConsumptionReturning = mapController.getMostEfficientRoute(end,start,shortestPathReturning) / 1000;

        stringBuilder.append(String.format("Energy Consumption: %.3f kJ \n\nPath: %s\n\n",energyConsumptionReturning,shortestPathReturning));

        double energyConsumption = energyConsumptionGoing + energyConsumptionReturning;

        stringBuilder.append(String.format("Energy Consumption Total: %.3f kJ\n\n",energyConsumption));

//        double scooterTotalEnergy = EnergyConsumptionAlgorithms.batteryEnergy(scooter.getBatterySize(),scooter.getBatteryEfficiency());
//        stringBuilder.append(String.format("Scooter battery energy: " + scooterTotalEnergy + "kJ\n"));
//        if(energyConsumption < scooterTotalEnergy)
//            stringBuilder.append("The scooter can do the path\n\n");
//        else
//            stringBuilder.append("The scooter can't do the path\n\n");
        PrintFile.printFile("cenarioT6_1.txt", stringBuilder.toString());

    }





    @Test
    void cenarioT7_0(){
        StringBuilder stringBuilder = new StringBuilder();
        ApplicationEM applicationEM = ApplicationEM.getInstance();
        Platform platform = applicationEM.getPlatform();
        AddressRegister addressRegister = platform.getAddressRegister();
        GeoLocationRegister geoLocationRegister = platform.getGeoLocationRegister();

        Address address = new Address(1212, "DemonstrationStreet", "DemonstrationDoorNumber", "DemonstrationCity", "DemonstrationCountry");
        Pharmacy pharmacy = new Pharmacy("1212DemonstrationPharmacy","211DemonstrationPharmacy",1212);

        List<LandPath> paths = new LinkedList<>();
        double windSpeed = EnergyConsumptionAlgorithms.kmhToMps(11);

        addressRegister.addAddress(101, "Rua 1","01","Porto","Portugal");
        addressRegister.addAddress(102, "Rua 2","02","Porto","Portugal");
        addressRegister.addAddress(103, "Rua 3","03","Porto","Portugal");

        geoLocationRegister.addGeoLocation(101, 10.0 ,10.0, 10, 101);
        geoLocationRegister.addGeoLocation(102, 10.0 ,10.0, 10, 102);
        geoLocationRegister.addGeoLocation(103, 10.0 ,10.0, 10, 103);

        LandPath first = new LandPath(101,102,10000.0,20.0,windSpeed,0.5,3.0);
        LandPath second = new LandPath(102,103,5000.0,20.0,windSpeed,0.5,0.0);
        LandPath firstReverse = new LandPath(102,101,10000.0,-20.0,windSpeed,0.5,-3.0);
        LandPath secondReverse = new LandPath(103,102,5000.0,-20.0,windSpeed,0.5,0.0);

        paths.add(first);
        paths.add(second);
        paths.add(firstReverse);
        paths.add(secondReverse);

        Scooter scooter = new Scooter(101,2800,0,0.6,3000,50,0.3,3000,"211DemonstrationPharmacy","211QRCODE");

        Courier courier = new Courier("101","courier","couriermodelo",
                "12321314","protw",80.0,0.0,"1212DemonstrationPharmacy");

        stringBuilder.append(String.format("Air Drag Coefficient: %f \n", Constants.S_AIR_DRAG_COEFFICIENT));
        stringBuilder.append(String.format("Vehicle medium speed: %f kmh | %f mps\n",Constants.D_VELOCITY, EnergyConsumptionAlgorithms.kmhToMps(Constants.D_VELOCITY)));

        EnergyLandMapController mapController = deliverySimulationsController.getEnergyOnAShortLandPath(paths, scooter,courier);

        stringBuilder.append(((MapController) mapController).mapToString());

        LinkedList<Address> shortestPathGoing = new LinkedList<>();

        Address start = addressRegister.getAddressByID(101);
        Address end = addressRegister.getAddressByID(103);
        double energyConsumptionGoing = mapController.getMostEfficientRoute(start,end,shortestPathGoing) / 1000;

        stringBuilder.append(String.format("Energy Consumption: %.3f kJ \n\nPath: %s\n\n",energyConsumptionGoing,shortestPathGoing));

        LinkedList<Address> shortestPathReturning = new LinkedList<>();

        double energyConsumptionReturning = mapController.getMostEfficientRoute(end,start,shortestPathReturning) / 1000;

        stringBuilder.append(String.format("Energy Consumption: %.3f kJ \n\nPath: %s\n\n",energyConsumptionReturning,shortestPathReturning));

        double energyConsumption = energyConsumptionGoing + energyConsumptionReturning;

        stringBuilder.append(String.format("Energy Consumption Total: %.3f kJ\n\n",energyConsumption));

//        double scooterTotalEnergy = EnergyConsumptionAlgorithms.batteryEnergy(scooter.getBatterySize(),scooter.getBatteryEfficiency());
//        stringBuilder.append(String.format("Scooter battery energy: " + scooterTotalEnergy + "kJ\n"));
//        if(energyConsumption < scooterTotalEnergy)
//            stringBuilder.append("The scooter can do the path\n\n");
//        else
//            stringBuilder.append("The scooter can't do the path\n\n");
        PrintFile.printFile("cenarioT7_0.txt", stringBuilder.toString());
    }
}
