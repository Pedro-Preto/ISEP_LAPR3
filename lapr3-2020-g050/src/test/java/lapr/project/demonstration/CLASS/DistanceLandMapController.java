package lapr.project.demonstration.CLASS;

import lapr.project.model.DomainClasses.Address;
import lapr.project.model.DomainClasses.GeoLocation;
import lapr.project.model.DomainClasses.LandPath;
import lapr.project.utils.EnergyConsumptionAlgorithms;

import java.util.LinkedList;

public class DistanceLandMapController extends MapController {


    public DistanceLandMapController(AddressRegister addressRegister, GeoLocationRegister geoLocationRegister) {
        super(addressRegister, geoLocationRegister);
    }

    public boolean addConnection(LandPath path){
        if(path == null)
            return false;

        GeoLocation startLocation = geoLocationRegister.getGeolocationByID(path.getGeoLocationStart());
        GeoLocation endLocation = geoLocationRegister.getGeolocationByID(path.getGeoLocationEnd());

        Address startAddress = addressRegister.getAddressByID(startLocation.getAddressId());
        Address endAddress = addressRegister.getAddressByID(endLocation.getAddressId());

        double distance = EnergyConsumptionAlgorithms.calculateDistanceBetweenLocations(startLocation,endLocation);

        return locationNet.addConnection(startAddress,endAddress,distance);
    }

    public double getShortestRoute(Address startAddress, Address endAndress, LinkedList<Address> path){
        return locationNet.getRoute(startAddress,endAndress,path);
    }

    public double getShortestRoute(Address startAddres, LinkedList<Address> locals, LinkedList<Address> path){
        return locationNet.getRouteWithIntermediatePoints(startAddres,locals,path);
    }

}
