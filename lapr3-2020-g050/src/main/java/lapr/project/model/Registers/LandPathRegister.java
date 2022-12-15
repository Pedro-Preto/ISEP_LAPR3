package lapr.project.model.Registers;

import lapr.project.data.LandPathDB;
import lapr.project.model.DomainClasses.LandPath;

import java.util.List;

public class LandPathRegister {

    private final LandPathDB landPathDB;

    public LandPathRegister(){
        this.landPathDB = new LandPathDB();
    }

    public boolean addLandPath(int geoLocationStart, int geoLocationEnd, Double distance, Double windDirection, Double windVelocity, Double roadResistanceCoefficient, Double elevation){
        LandPath landPath = new LandPath(geoLocationStart, geoLocationEnd, distance, windDirection, windVelocity, roadResistanceCoefficient, elevation);
        return landPathDB.addLandPath(landPath);
    }

    public boolean removeLandPath(int startId, int endId) {
        return landPathDB.removeLandPath(startId, endId);
    }

    public LandPath getPathByID(int startId, int endId){
        return landPathDB.getLandPath(startId,endId);
    }

    public List<LandPath> allPathsBetweenPharmacies(){
        return landPathDB.allPathsBetweenPharmacies();
    }
    public List<LandPath> getAllPathsBetweenClientsAndPharmacies(){
        return landPathDB.getAllPathsBetweenClientsAndPharmacies();
    }

}
