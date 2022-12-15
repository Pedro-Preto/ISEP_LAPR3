package lapr.project.model.Registers;

import lapr.project.data.GeoLocationDB;
import lapr.project.model.DomainClasses.GeoLocation;

public class GeoLocationRegister {

    private final GeoLocationDB geoLocationDB;

    public GeoLocationRegister() {
        this.geoLocationDB = new GeoLocationDB();
    }

    public boolean addGeoLocation(int id, Double longitude, Double latitude, Integer altitude, int addressId) {
        GeoLocation geoLocation = new GeoLocation(id, longitude, latitude, altitude, addressId);
        return geoLocationDB.addGeoLocation(geoLocation);
    }

    public boolean removeGeoLocation(int geoLocationId) {
        return geoLocationDB.removeGeoLocation(geoLocationId);
    }

    public GeoLocation getGeolocationByAddressID(int id){
        return geoLocationDB.getGeoLocationByAddresID(id);
    }

    public GeoLocation getGeolocationByID(int id){
        return geoLocationDB.getGeoLocation(id);}


}
