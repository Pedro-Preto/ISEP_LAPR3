package lapr.project.utils;

import lapr.project.model.DomainClasses.Constants;
import lapr.project.model.DomainClasses.GeoLocation;

public class EnergyConsumptionAlgorithms {

    private static final Double GRAVITATIONAL_ACCELERATION = 9.81;
    private static final double KMP_TO_MPS = 0.27777777777778;

    /**
     * Hide implicit constructor
     */
    private EnergyConsumptionAlgorithms() {
        //
    }

    public static double calculateDistanceBetweenLocations(GeoLocation startPosition, GeoLocation endPosition){
        double R = 6371000;
        double modLat1 = startPosition.getLatitude() * Math.PI / 180;
        double modLat2 = endPosition.getLatitude() * Math.PI / 180;
        double latDiff = (endPosition.getLatitude() - startPosition.getLatitude()) * Math.PI / 180;
        double longDiff = (endPosition.getLongitude() - startPosition.getLongitude()) * Math.PI / 180;
        double a = Math.sin(latDiff / 2) * Math.sin(latDiff / 2) +
                Math.cos(modLat1) * Math.cos(modLat2) *
                        Math.sin(longDiff / 2) * Math.sin(longDiff / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; //m
    }

    public static double calculateAcceleration(double startSpeed, double endSpeed, double startTime, double endTime){
        double speedDifference = endSpeed - startSpeed; //m s^-1
        double timeDifference = endTime - startTime; //s
        return speedDifference / timeDifference; //m s^-2
    }

    public static double calculateInertialForce(double totalMass, double vehicleAcceleration){
        return totalMass * vehicleAcceleration; //Kg m s^-2 => N
    }

    public static double calculateRoadSlopeAngle(GeoLocation startPosition, GeoLocation endPosition){
        double horizontalDistance = calculateDistanceBetweenLocations(startPosition,endPosition); //m
        double verticalDistance = endPosition.getAltitude()-startPosition.getAltitude(); //m
        return Math.atan(verticalDistance/horizontalDistance); //rad
    }

    public static double calculateInclination(GeoLocation startPosition, GeoLocation endPosition){
        double horizontalDistance = calculateDistanceBetweenLocations(startPosition,endPosition); //m
        double verticalDistance = endPosition.getAltitude()-startPosition.getAltitude(); //m
        return (verticalDistance / horizontalDistance) * 100;
    }

    public static double calculateRoadSlopeAngle(double inclination){
        return Math.atan(inclination/100);
    }

    public static double calculateRoadSlopeForce(double totalMass, double roadSlopeAngle){
//        double roadSlopeAngle = calculateRoadSlopeAngle(startPosition,endPosition); //rad
        return totalMass * GRAVITATIONAL_ACCELERATION * Math.sin(roadSlopeAngle); //Kg m s^-2 => N
    }

    public static double calculateRoadLoadFrictionForce(double totalMass, double roadSlopeAngle,
                                                        double roadRollingResistanceCoefficient){
//        double roadSlopeAngle = calculateRoadSlopeAngle(startPosition,endPosition); //rad
        return totalMass * GRAVITATIONAL_ACCELERATION * roadRollingResistanceCoefficient * Math.cos(roadSlopeAngle); //Kg m s^-2 => N
    }

    public static double calculateAerodynamicDragForce(double airDensity, double airDragCoefficient, double frontalArea, double speed){
        //airDensity = kg m^-3 | frotalArea = m^2 | speed = m s^-1
        // kg m^-1 || m^2 s^-2
        return 0.5 * airDensity * airDragCoefficient * frontalArea * Math.pow(speed,2); //Kg m s^-2 => N
    }

    public static double calculateTotalForces(double totalMass, double vehicleAcceleration, double roadSlopeAngle, double roadRollingResistanceCoefficient,
                                              double airDensity, double airDragCoefficient, double frontalArea, double speed){
        double inertialForce = calculateInertialForce(totalMass, vehicleAcceleration); //N
        double roadSlopeForce = calculateRoadSlopeForce(totalMass,roadSlopeAngle); //N
        double roadLoadFrictionForce = calculateRoadLoadFrictionForce(totalMass,roadSlopeAngle, roadRollingResistanceCoefficient);//N
        double aerodynamicDragForce = calculateAerodynamicDragForce(airDensity,airDragCoefficient,frontalArea,speed);//N
        return inertialForce + roadSlopeForce + roadLoadFrictionForce + aerodynamicDragForce; //N => Kg m s^-2
    }

    public static double calculateTotalPower(double totalForces, double speed){
        //totalForces = N => Kg m s^-2 | speed = m s^-1
        return totalForces * speed;  // Kg m^2 s^-3 => W
    }

    public static double calculateRelativeSpeed(double vehicleSpeed, double windSpeed, double windDirection){
        return vehicleSpeed - windSpeed * Math.cos(Math.toRadians(windDirection)); //m s^-1
    }

    public static double calculateDistanceTravelled(GeoLocation startPosition, GeoLocation endPosition){
        double horizontalDistance = calculateDistanceBetweenLocations(startPosition,endPosition); //m
        double verticalDistance = endPosition.getAltitude()-startPosition.getAltitude(); //m

        double sideA = Math.pow(horizontalDistance,2);
        double sideB = Math.pow(verticalDistance,2);

        return Math.sqrt(sideA + sideB);
    }


    public static double getTimeFromVelocityDistance(double speed, double distance){
        //speed = m s^-1 || distance = m
        return distance / speed; //s
    }

    public static double calculateEnergy(double totalPower, double time){
        //totalPower = W => Kg m^2 s^-3 | time = s
        return totalPower * time; // Kg m^2 s^-2 => J
    }

    public static double kmhToMps(double kmh){
        return kmh*KMP_TO_MPS;
    }

    public static double batteryEnergy(double batteryCapacity, double batteryEfficiency){
        double usableBattery = batteryCapacity * batteryEfficiency;
        return usableBattery * Constants.D_VELOCITY;
    }

    private static double calculateThrust(double totalWeight){
        return totalWeight * GRAVITATIONAL_ACCELERATION;
    }

    public static double calculateTakeoffOrLandingPower(double totalWeight, double airDensity, double frontalArea){
        double thrust = calculateThrust(totalWeight);
        final double exponent = 3.0/2.0;
        double aux1 = Math.pow(thrust,exponent);
        double aux2 = Math.sqrt(2*airDensity*frontalArea);
        return aux1 / aux2;
    }

    private static double calculateParasiticDragForce(double aerodynamicDragCoefficient, double frontalArea, double airDensity, double relativeSpeed){
        double aux = Math.pow(relativeSpeed,2);
        return 0.5 * aerodynamicDragCoefficient * frontalArea * airDensity * aux;
    }

    public static double calculateParasiticDragPower(double aerodynamicDragCoefficient, double frontalArea, double airDensity, double relativeSpeed){
        double parasiticDragForce = calculateParasiticDragForce(aerodynamicDragCoefficient,frontalArea,airDensity,relativeSpeed);
        return parasiticDragForce * relativeSpeed;
    }

    public static double calculateLiftPower(double totalWeight, double airDensity, double width, double relativeSpeed){
        double aux1 = Math.pow(totalWeight,2);
        double aux2 = Math.pow(width,2);
        double aux3 = airDensity * aux2 * relativeSpeed;
        return aux1 / aux3;
    }

    public static double calculateFlightPower(double totalWeight, double frontalArea, double width, double aerodynamicDragCoefficient,  double airDensity, double relativeSpeed){
        double parasiticDragPower = calculateParasiticDragPower(aerodynamicDragCoefficient, frontalArea, airDensity, relativeSpeed);
        double liftPower = calculateLiftPower(totalWeight, airDensity, width, relativeSpeed);
        return parasiticDragPower + liftPower;
    }

}