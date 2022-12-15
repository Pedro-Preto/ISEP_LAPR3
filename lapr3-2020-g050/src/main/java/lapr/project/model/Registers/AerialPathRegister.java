package lapr.project.model.Registers;

import lapr.project.data.AerialPathDB;
import lapr.project.model.DomainClasses.AerialPath;

public class AerialPathRegister {

    AerialPathDB aerialPathDB;

    public AerialPathRegister(){
        this.aerialPathDB = new AerialPathDB();
    }

    public boolean addAerialPath(int geoLocationStart, int geoLocationEnd, Double distance, Double windDirection, Double windVelocity, Double airDensity, Double airDragCoefficient){
        AerialPath aerialPath = new AerialPath(geoLocationStart, geoLocationEnd, distance, windDirection, windVelocity, airDensity, airDragCoefficient);
        return aerialPathDB.addAerialPath(aerialPath);
    }

    public boolean removeAerialPath(int startId, int endId) {
        return aerialPathDB.removeAerialPath(startId, endId);
    }

    public AerialPath getPathByID(int startId, int endId){ return aerialPathDB.getAerialPath(startId, endId); }
}
