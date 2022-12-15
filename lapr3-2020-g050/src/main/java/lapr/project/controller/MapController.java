package lapr.project.controller;

import lapr.project.model.DomainClasses.*;
import lapr.project.model.Registers.AddressRegister;
import lapr.project.model.Registers.GeoLocationRegister;

public class MapController {

    protected final ApplicationEM applicationEM;
    protected final GeoLocationRegister geoLocationRegister;
    protected final AddressRegister addressRegister;
    protected final LocationNet locationNet;

    public MapController() {
        this.applicationEM = ApplicationEM.getInstance();
        geoLocationRegister = applicationEM.getPlatform().getGeoLocationRegister();
        addressRegister = applicationEM.getPlatform().getAddressRegister();
        locationNet = new LocationNet();
    }

    public boolean addAddress(Address address){
        return locationNet.addAddress(address);
    }

    public String mapToString(){
        return locationNet.mapToString();
    }

}
