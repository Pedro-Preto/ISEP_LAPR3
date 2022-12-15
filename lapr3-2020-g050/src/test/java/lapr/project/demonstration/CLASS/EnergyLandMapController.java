package lapr.project.demonstration.CLASS;

import lapr.project.model.DomainClasses.*;
import lapr.project.utils.EnergyConsumptionAlgorithms;

import java.util.LinkedList;

public class EnergyLandMapController extends MapController {

    public EnergyLandMapController(AddressRegister addressRegister, GeoLocationRegister geoLocationRegister) {
        super(addressRegister, geoLocationRegister);
    }

    public boolean addConnection(Address start, Address end, double value){
        return locationNet.addConnection(start,end,value);
    }

    public boolean addConnection(LandPath path, Scooter scooter, Courier courier){
        if(path == null)
            return false;

        GeoLocation startLocation = geoLocationRegister.getGeolocationByID(path.getGeoLocationStart());
        GeoLocation endLocation = geoLocationRegister.getGeolocationByID(path.getGeoLocationEnd());

        Address startAddress = addressRegister.getAddressByID(startLocation.getAddressId());
        Address endAddress = addressRegister.getAddressByID(endLocation.getAddressId());

        double courierMass = courier.getWeight();
        double payload = courier.getPayload();
        double vehicleWeight = scooter.getWeight();
        double totalMass = vehicleWeight + courierMass + payload;

        double vehicleAcceleration = 0.0;
        double frontalArea = scooter.getFrontalArea();

        double airDensity = Constants.AIR_DENSITY;
        double airDragCoefficient = Constants.AIR_DRAG_COEFFICIENT;
        double vehicleSpeed = Constants.SCOOTER_MAX_SPEED;

        double relativeSpeed = EnergyConsumptionAlgorithms.calculateRelativeSpeed(vehicleSpeed,path.getWindVelocity(),path.getWindDirection());

        double roadSlopeAngle = EnergyConsumptionAlgorithms.calculateRoadSlopeAngle(startLocation, endLocation);

        double totalForces =  EnergyConsumptionAlgorithms.calculateTotalForces(totalMass,vehicleAcceleration,roadSlopeAngle,
                path.getRoadResistanceCoefficient(), airDensity, airDragCoefficient, frontalArea, relativeSpeed);

        double distance = EnergyConsumptionAlgorithms.calculateDistanceTravelled(startLocation,endLocation);

        double totalPower = EnergyConsumptionAlgorithms.calculateTotalPower(totalForces, relativeSpeed);

        double time = EnergyConsumptionAlgorithms.getTimeFromVelocityDistance(relativeSpeed, distance);

        double energy = EnergyConsumptionAlgorithms.calculateEnergy(totalPower, time);

        path.setDistance(distance);
        path.setInclination(EnergyConsumptionAlgorithms.calculateInclination(startLocation,endLocation));

        return locationNet.addConnection(startAddress,endAddress,energy);
    }

    public double getMostEfficientRoute(Address startAddress, Address endAndress, LinkedList<Address> path) {
        return locationNet.getRoute(startAddress, endAndress, path);
    }

    public double getMostEfficientRoute(Address startAddres, LinkedList<Address> locals, LinkedList<Address> path){
        return locationNet.getRouteWithIntermediatePoints(startAddres,locals,path);
    }

}
