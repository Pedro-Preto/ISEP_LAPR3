package lapr.project.utils;

import lapr.project.model.DomainClasses.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnergyConsumptionAlgorithmsTest {

    GeoLocation startLocation, endLocation;
    Vehicle vehicle;
    LandPath path;

    @BeforeEach
    void setUp() {
        Address startAddress = new Address(1, "RUA", "123", "Porto", "Portugal");
        Address endAddress = new Address(2, "RUA1", "127", "Porto", "Portugal");
        startLocation = new GeoLocation(41.15227, -8.60929, 104, startAddress.getId());
        endLocation = new GeoLocation(41.14582, -8.61398, 87, endAddress.getId());

        double distance = 800;
        double windDirection = 35.0;
        double windVelocity = 6.0;
        double roadResistanceCoefficient = 0.7;
        double elevation = -1.93;

        path = new LandPath(startLocation.getId(), endLocation.getId(), distance, windDirection, windVelocity, roadResistanceCoefficient, elevation);

        int id = 1;
        double batterySize = 2400.0;
        double batteryConsumption = 242.5;
        double batteryEfficiency = 70;
        double currentBattery = 1200.0;
        double weight = 118.0;
        double frontalArea = .875;
        double motorPower = 6000.0;
        String pharmacyNif = "123124141";
        String QRCode = "1dqni32ntf1";

        vehicle = new Scooter(id, batterySize, batteryConsumption, batteryEfficiency, currentBattery, weight, frontalArea, motorPower, pharmacyNif, QRCode);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void calculateDistanceBetweenLocations() {
        double expected = 880.239;
        double actual = EnergyConsumptionAlgorithms.calculateDistanceBetweenLocations(startLocation, endLocation);

        assertEquals(expected, actual, 0.001);

        expected = 880.239;
        actual = EnergyConsumptionAlgorithms.calculateDistanceBetweenLocations(endLocation, startLocation);

        assertEquals(expected, actual, 0.001);
    }

    @Test
    void calculateAcceleration() {
        double startSpeed = 25.0;
        double endSpeed = 45.0;
        double startTime = 0.0;
        double endTime = 30.0;

        double expected = 0.667;

        double actual = EnergyConsumptionAlgorithms.calculateAcceleration(startSpeed, endSpeed, startTime, endTime);

        assertEquals(expected, actual, 0.001);
    }

    @Test
    void calculateInertialForce() {
        double courierMass = 75.0;
        double payload = 10.0;
        double totalMass = vehicle.getWeight() + courierMass + payload;
        double acceleration = 0.0;

        double expected = 0.0;
        double actual = EnergyConsumptionAlgorithms.calculateInertialForce(totalMass, acceleration);

        assertEquals(expected, actual, 0.001);
    }

    @Test
    void calculateRoadSlopeAngle() {
        double expected = -0.019310546;
        double actual = EnergyConsumptionAlgorithms.calculateRoadSlopeAngle(startLocation, endLocation);

        assertEquals(expected, actual, 0.001);

        expected = 0.019310546;
        actual = EnergyConsumptionAlgorithms.calculateRoadSlopeAngle(endLocation, startLocation);

        assertEquals(expected, actual, 0.001);

        expected = -0.019310546;
        actual = EnergyConsumptionAlgorithms.calculateRoadSlopeAngle(path.getInclination());

        assertEquals(expected,actual,0.001);
    }

    @Test
    void calculateRoadSlopeForce() {
        double courierMass = 75.0;
        double payload = 10.0;
        double totalMass = vehicle.getWeight() + courierMass + payload;

        double expected = -38.45321;
        double roadSlopeAngle = EnergyConsumptionAlgorithms.calculateRoadSlopeAngle(startLocation,endLocation);
        double actual = EnergyConsumptionAlgorithms.calculateRoadSlopeForce(totalMass, roadSlopeAngle);

        assertEquals(expected, actual, 0.1);

        roadSlopeAngle = EnergyConsumptionAlgorithms.calculateRoadSlopeAngle(endLocation,startLocation);

        expected = 38.45321;
        actual = EnergyConsumptionAlgorithms.calculateRoadSlopeForce(totalMass, roadSlopeAngle);

        assertEquals(expected, actual, 0.1);
    }

    @Test
    void calculateRoadLoadFrictionForce() {
        double courierMass = 75.0;
        double payload = 10.0;
        double totalMass = vehicle.getWeight() + courierMass + payload;

        double roadSlopeAngle = EnergyConsumptionAlgorithms.calculateRoadSlopeAngle(startLocation,endLocation);

        double expected = 1393.741099;
        double actual = EnergyConsumptionAlgorithms.calculateRoadLoadFrictionForce(totalMass, roadSlopeAngle,
                path.getRoadResistanceCoefficient());

        assertEquals(expected, actual, 0.005);

        roadSlopeAngle = EnergyConsumptionAlgorithms.calculateRoadSlopeAngle(endLocation,startLocation);
        expected = 1393.741099;
        actual = EnergyConsumptionAlgorithms.calculateRoadLoadFrictionForce(totalMass, roadSlopeAngle,
                path.getRoadResistanceCoefficient());

        assertEquals(expected, actual, 0.005);
    }

    @Test
    void calculateRelativeSpeed() {
        double vehicleSpeed = 22.22222;

        double expected = 17.3073078;
        double actual = EnergyConsumptionAlgorithms.calculateRelativeSpeed(vehicleSpeed, path.getWindVelocity(), path.getWindDirection());

        assertEquals(expected, actual, 0.005);
    }

    @Test
    void calculateAerodynamicDragForce() {
        double airDensity = 1.324;
        double airDragCoefficient = 1.5;
        double vehicleSpeed = 22.22222;
        double relativeSpeed = EnergyConsumptionAlgorithms.calculateRelativeSpeed(vehicleSpeed, path.getWindVelocity(), path.getWindDirection());

        double expected = 260.256081;
        double actual = EnergyConsumptionAlgorithms.calculateAerodynamicDragForce(airDensity, airDragCoefficient,
                vehicle.getFrontalArea(), relativeSpeed);

        assertEquals(expected, actual, 0.01);
    }

    @Test
    void calculateTotalForces() {
        double courierMass = 75.0;
        double payload = 10.0;
        double totalMass = vehicle.getWeight() + courierMass + payload;

        double vehicleAcceleration = 0.0;

        double airDensity = 1.324;
        double airDragCoefficient = 1.5;
        double vehicleSpeed = 22.22222;
        double relativeSpeed = EnergyConsumptionAlgorithms.calculateRelativeSpeed(vehicleSpeed, path.getWindVelocity(), path.getWindDirection());
        double roadSlopeAngle = EnergyConsumptionAlgorithms.calculateRoadSlopeAngle(startLocation, endLocation);


        double expected = 1615.553;
        double actual = EnergyConsumptionAlgorithms.calculateTotalForces(totalMass, vehicleAcceleration, roadSlopeAngle,
                path.getRoadResistanceCoefficient(), airDensity, airDragCoefficient, vehicle.getFrontalArea(), relativeSpeed);

        assertEquals(expected, actual, 0.005);

        roadSlopeAngle = EnergyConsumptionAlgorithms.calculateRoadSlopeAngle(endLocation, startLocation);

        expected = 1692.459;
        actual = EnergyConsumptionAlgorithms.calculateTotalForces(totalMass, vehicleAcceleration, roadSlopeAngle
                , path.getRoadResistanceCoefficient(), airDensity, airDragCoefficient, vehicle.getFrontalArea(), relativeSpeed);

        assertEquals(expected, actual, 0.005);
    }

    @Test
    void calculateTotalPower() {
        double courierMass = 75.0;
        double payload = 10.0;
        double totalMass = vehicle.getWeight() + courierMass + payload; //Kg

        double vehicleAcceleration = 0.0; //m s^-2

        double airDensity = 1.324;
        double airDragCoefficient = 1.5;
        double vehicleSpeed = 22.22222; //m s^-1
        double relativeSpeed = EnergyConsumptionAlgorithms.calculateRelativeSpeed(vehicleSpeed, path.getWindVelocity(), path.getWindDirection()); //m s^-1
        double roadSlopeAngle = EnergyConsumptionAlgorithms.calculateRoadSlopeAngle(startLocation, endLocation);

        double totalForces = EnergyConsumptionAlgorithms.calculateTotalForces(totalMass, vehicleAcceleration, roadSlopeAngle,
                path.getRoadResistanceCoefficient(), airDensity, airDragCoefficient, vehicle.getFrontalArea(), relativeSpeed); //N => Kg m s^-2

        double expected = 27960.87685;
        double actual = EnergyConsumptionAlgorithms.calculateTotalPower(totalForces, relativeSpeed); //W => Kg m^2 s^-3

        assertEquals(expected, actual, 0.005);

        roadSlopeAngle = EnergyConsumptionAlgorithms.calculateRoadSlopeAngle(endLocation, startLocation);

        totalForces = EnergyConsumptionAlgorithms.calculateTotalForces(totalMass, vehicleAcceleration, roadSlopeAngle
                , path.getRoadResistanceCoefficient(), airDensity, airDragCoefficient, vehicle.getFrontalArea(), relativeSpeed); //N => Kg m s^-2

        expected = 29291.919937;
        actual = EnergyConsumptionAlgorithms.calculateTotalPower(totalForces, relativeSpeed); //W => Kg m^2 s^-3

        assertEquals(expected, actual, 0.005);
    }

    @Test
    void calculateDistanceTravelled() {
        double expected = 880.403;
        double actual = EnergyConsumptionAlgorithms.calculateDistanceTravelled(startLocation, endLocation);

        assertEquals(expected, actual, 0.001);
    }

    @Test
    void getTimeFromVelocityDistance() {
        double vehicleSpeed = 22.22222;
        double relativeSpeed = EnergyConsumptionAlgorithms.calculateRelativeSpeed(vehicleSpeed, path.getWindVelocity(), path.getWindDirection());

        double distance = EnergyConsumptionAlgorithms.calculateDistanceTravelled(startLocation, endLocation);

        double expected = 50.869;
        double actual = EnergyConsumptionAlgorithms.getTimeFromVelocityDistance(relativeSpeed, distance);

        assertEquals(expected, actual, 0.001);

    }

    @Test
    void calculateEnergy() {
        double courierMass = 75.0;
        double payload = 10.0;
        double totalMass = vehicle.getWeight() + courierMass + payload;

        double vehicleAcceleration = 0.0;

        double airDensity = 1.324;
        double airDragCoefficient = 1.5;
        double vehicleSpeed = 22.22222;
        double relativeSpeed = EnergyConsumptionAlgorithms.calculateRelativeSpeed(vehicleSpeed, path.getWindVelocity(), path.getWindDirection());

        double roadSlopeAngle = EnergyConsumptionAlgorithms.calculateRoadSlopeAngle(startLocation, endLocation);

        double totalForces = EnergyConsumptionAlgorithms.calculateTotalForces(totalMass, vehicleAcceleration, roadSlopeAngle,
                path.getRoadResistanceCoefficient(), airDensity, airDragCoefficient, vehicle.getFrontalArea(), relativeSpeed);

        double distance = EnergyConsumptionAlgorithms.calculateDistanceTravelled(startLocation, endLocation);

        double totalPower = EnergyConsumptionAlgorithms.calculateTotalPower(totalForces, relativeSpeed);

        double time = EnergyConsumptionAlgorithms.getTimeFromVelocityDistance(relativeSpeed, distance);

        //double expected = 554424.400;
        double expected = 1422337.416;
        double actual = EnergyConsumptionAlgorithms.calculateEnergy(totalPower, time);

        assertEquals(expected, actual, 0.005);

        roadSlopeAngle = EnergyConsumptionAlgorithms.calculateRoadSlopeAngle(endLocation, startLocation);

        totalForces = EnergyConsumptionAlgorithms.calculateTotalForces(totalMass, vehicleAcceleration, roadSlopeAngle,
                path.getRoadResistanceCoefficient(), airDensity, airDragCoefficient, vehicle.getFrontalArea(), relativeSpeed);

        distance = EnergyConsumptionAlgorithms.calculateDistanceBetweenLocations(endLocation, startLocation);

        totalPower = EnergyConsumptionAlgorithms.calculateTotalPower(totalForces, relativeSpeed);

        time = EnergyConsumptionAlgorithms.getTimeFromVelocityDistance(relativeSpeed, distance);

        expected = 1489768.228;
        actual = EnergyConsumptionAlgorithms.calculateEnergy(totalPower, time);

        assertEquals(expected, actual, 0.005);
    }
}