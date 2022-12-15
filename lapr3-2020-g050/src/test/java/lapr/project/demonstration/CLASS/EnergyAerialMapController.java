package lapr.project.demonstration.CLASS;

import lapr.project.model.DomainClasses.*;
import lapr.project.utils.EnergyConsumptionAlgorithms;

import java.util.LinkedList;

public class EnergyAerialMapController extends MapController {

    public EnergyAerialMapController(AddressRegister addressRegister, GeoLocationRegister geoLocationRegister) {
        super(addressRegister, geoLocationRegister);
    }

    public boolean addConnection(Address start, Address end, double value){
        return locationNet.addConnection(start,end,value);
    }

    public boolean addConnection(AerialPath path, Drone drone){
        if(path == null)
            return false;

        GeoLocation startLocation = geoLocationRegister.getGeolocationByID(path.getGeoLocationStart());
        GeoLocation endLocation = geoLocationRegister.getGeolocationByID(path.getGeoLocationEnd());

        Address startAddress = addressRegister.getAddressByID(startLocation.getAddressId());
        Address endAddress = addressRegister.getAddressByID(endLocation.getAddressId());

        double horizontalDistance = EnergyConsumptionAlgorithms.calculateDistanceBetweenLocations(startLocation,endLocation);

        path.setDistance(horizontalDistance);

        double payload = drone.getPayload();
        double vehicleWeight = drone.getWeight();
        double totalWeight = vehicleWeight + payload;

        double frontalArea = drone.getFrontalArea();
        double width = Constants.DRONE_WIDTH;

        double airDensity = path.getAirDensity();
        double airDragCoefficient = path.getAirDragCoefficient();

        double horizontalSpeed = Constants.HORIZONTAL_SPEED;
        double horizontalRelativeSpeed = EnergyConsumptionAlgorithms.calculateRelativeSpeed(horizontalSpeed,path.getWindVelocity(),path.getWindDirection());

        double flightPower = EnergyConsumptionAlgorithms.calculateFlightPower(totalWeight, frontalArea, width, airDragCoefficient,airDensity, horizontalRelativeSpeed);

        double flightTime = EnergyConsumptionAlgorithms.getTimeFromVelocityDistance(horizontalRelativeSpeed, horizontalDistance);

        double flightEnergy = flightPower * flightTime;


        double verticalSpeed = Constants.ASCENT_DESCENT_SPEED;

        double takeoffOrLandingPower = EnergyConsumptionAlgorithms.calculateTakeoffOrLandingPower(totalWeight, airDensity, frontalArea);

        //TAKEOFF ENERGY
        double takeoffAltitude = startLocation.getAltitude();

        double takeoffVerticalDistance = Constants.FLIGHT_ALTITUDE - takeoffAltitude + Constants.DRONE_PLATFORM_ALTITUDE;

        double takeoffTime = EnergyConsumptionAlgorithms.getTimeFromVelocityDistance(verticalSpeed, takeoffVerticalDistance);

        double takeoffEnergy = takeoffOrLandingPower * takeoffTime;

        //LANDING ENERGY
        double landingAltitude = endLocation.getAltitude();

        double landingVerticalDistance = Constants.FLIGHT_ALTITUDE - landingAltitude + Constants.DRONE_PLATFORM_ALTITUDE;

        double landingTime = EnergyConsumptionAlgorithms.getTimeFromVelocityDistance(verticalSpeed, landingVerticalDistance);

        double landingEnergy = takeoffOrLandingPower * landingTime;


        //TAKEOFF AND LANDING ENERGY
        double takeoffAndLandingEnergy = takeoffEnergy + landingEnergy;

        double energy = takeoffAndLandingEnergy + flightEnergy;

        return locationNet.addConnection(startAddress,endAddress,energy);
    }

    public double getMostEfficientRoute(Address startAddress, Address endAndress, LinkedList<Address> path) {
        double flyingEnergy = locationNet.getRoute(startAddress, endAndress, path);
        return locationNet.getRoute(startAddress, endAndress, path);
    }

    public double getMostEfficientRoute(Address startAddres, LinkedList<Address> locals, LinkedList<Address> path){
        return locationNet.getRouteWithIntermediatePoints(startAddres,locals,path);
    }

}
