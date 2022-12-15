package lapr.project.demonstration.CLASS;

import lapr.project.data.GeoLocationDB;
import lapr.project.model.DomainClasses.Address;
import lapr.project.model.DomainClasses.GeoLocation;

import java.util.LinkedList;

public class GeoLocationRegister {

    private final LinkedList<GeoLocation> geoLocations;

    public GeoLocationRegister() {
        this.geoLocations = new LinkedList<>();
    }

    public boolean addGeoLocation(int id, Double longitude, Double latitude, Integer altitude, int addressId) {
        GeoLocation geoLocation = new GeoLocation(id, longitude, latitude, altitude, addressId);
        return geoLocations.add(geoLocation);
    }

    public GeoLocation getGeolocationByID(int id){
        for(GeoLocation geoLocation : geoLocations)
            if(geoLocation.getId() == id)
                return geoLocation;

        return null;
    }

    public GeoLocation getGeolocationByAddressID(int id){
        for(GeoLocation geoLocation : geoLocations)
            if(geoLocation.getAddressId() == id)
                return geoLocation;

        return null;
    }




}
