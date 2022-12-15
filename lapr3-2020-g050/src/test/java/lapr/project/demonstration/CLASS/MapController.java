package lapr.project.demonstration.CLASS;

import lapr.project.model.DomainClasses.Address;
import lapr.project.model.DomainClasses.LocationNet;

public class MapController {

    protected GeoLocationRegister geoLocationRegister;
    protected AddressRegister addressRegister;
    protected final LocationNet locationNet;

    public MapController(AddressRegister addressRegister, GeoLocationRegister geoLocationRegister) {
        this.geoLocationRegister = geoLocationRegister;
        this.addressRegister = addressRegister;
        locationNet = new LocationNet();
    }

    public boolean addAddress(Address address){
        return locationNet.addAddress(address);
    }

    public String mapToString(){
        return locationNet.mapToString();
    }

}
