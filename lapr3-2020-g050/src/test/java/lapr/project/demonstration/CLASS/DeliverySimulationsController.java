package lapr.project.demonstration.CLASS;

import lapr.project.model.DomainClasses.*;
import lapr.project.utils.EnergyConsumptionAlgorithms;

import java.util.List;

public class DeliverySimulationsController {

    private final AddressRegister addressRegister;
    private final GeoLocationRegister geoLocationRegister;

    public DeliverySimulationsController(AddressRegister addressRegister, GeoLocationRegister geoLocationRegister) {
        this.addressRegister = addressRegister;
        this.geoLocationRegister = geoLocationRegister;
    }

    private Address getAddressByGeolocationID(int geolocationID){
        GeoLocation geoLocation = geoLocationRegister.getGeolocationByAddressID(geolocationID);
        return addressRegister.getAddressByID(geoLocation.getAddressId());
    }

    public MapController getEnergyConsumptionLandMap(List<LandPath> paths, Scooter scooter, Courier courier){
        EnergyLandMapController energyLandMapController = new EnergyLandMapController(addressRegister,geoLocationRegister);

        for(LandPath path: paths){
            Address startAddress = getAddressByGeolocationID(path.getGeoLocationStart());
            Address endAddress = getAddressByGeolocationID(path.getGeoLocationEnd());

            energyLandMapController.addAddress(startAddress);
            energyLandMapController.addAddress(endAddress);

            energyLandMapController.addConnection(path,scooter,courier);
        }

        return energyLandMapController;
    }

    public DistanceLandMapController getDistanceLandMap(List<LandPath> paths){
        DistanceLandMapController energyLandMapController = new DistanceLandMapController(addressRegister,geoLocationRegister);

        for(LandPath path: paths){
            Address startAddress = getAddressByGeolocationID(path.getGeoLocationStart());
            Address endAddress = getAddressByGeolocationID(path.getGeoLocationEnd());

            energyLandMapController.addAddress(startAddress);
            energyLandMapController.addAddress(endAddress);

            energyLandMapController.addConnection(path);
        }

        return energyLandMapController;
    }

    public EnergyLandMapController getEnergyOnAShortLandPath(List<LandPath> paths, Scooter scooter, Courier courier){
        EnergyLandMapController map = new EnergyLandMapController(addressRegister,geoLocationRegister);

        for(LandPath path: paths){

            GeoLocation startLocation = geoLocationRegister.getGeolocationByID(path.getGeoLocationStart());
            GeoLocation endLocation = geoLocationRegister.getGeolocationByID(path.getGeoLocationEnd());

            Address startAddress = addressRegister.getAddressByID(startLocation.getAddressId());
            Address endAddress = addressRegister.getAddressByID(endLocation.getAddressId());

            map.addAddress(startAddress);
            map.addAddress(endAddress);

            double courierMass = courier.getWeight();
            double payload = courier.getPayload();
            double vehicleWeight = scooter.getWeight();
            double totalMass = vehicleWeight + courierMass + payload;

            double vehicleAcceleration = 0.0;
            double frontalArea = scooter.getFrontalArea();

            double airDensity = Constants.AIR_DENSITY;
            double airDragCoefficient = Constants.S_AIR_DRAG_COEFFICIENT;
            double vehicleSpeed = Constants.D_VELOCITY;

            double relativeSpeed = EnergyConsumptionAlgorithms.calculateRelativeSpeed(vehicleSpeed,path.getWindVelocity(),path.getWindDirection());

            double roadSlopeAngle = EnergyConsumptionAlgorithms.calculateRoadSlopeAngle(path.getInclination());

            double totalForces =  EnergyConsumptionAlgorithms.calculateTotalForces(totalMass,vehicleAcceleration,roadSlopeAngle,
                    path.getRoadResistanceCoefficient(), airDensity, airDragCoefficient, frontalArea, relativeSpeed);

            double distance = path.getDistance();

            double totalPower = EnergyConsumptionAlgorithms.calculateTotalPower(totalForces, relativeSpeed);

            double time = EnergyConsumptionAlgorithms.getTimeFromVelocityDistance(relativeSpeed, distance);

            double energy = EnergyConsumptionAlgorithms.calculateEnergy(totalPower, time);

            map.addConnection(startAddress, endAddress, energy);
        }

        return map;
    }

    public EnergyAerialMapController getEnergyConsumptionAerialMap(List<AerialPath> paths, Drone drone, Courier courier){
        EnergyAerialMapController energyenergyAerialMapController = new EnergyAerialMapController(addressRegister,geoLocationRegister);

        for(AerialPath path: paths){
            Address startAddress = getAddressByGeolocationID(path.getGeoLocationStart());
            Address endAddress = getAddressByGeolocationID(path.getGeoLocationEnd());

            energyenergyAerialMapController.addAddress(startAddress);
            energyenergyAerialMapController.addAddress(endAddress);

            energyenergyAerialMapController.addConnection(path,drone);
        }

        return energyenergyAerialMapController;
    }

    public DistanceAerialMapController getDistanceAerialMap(List<AerialPath> paths){
        DistanceAerialMapController distanceAerialMapController = new DistanceAerialMapController(addressRegister,geoLocationRegister);

        for(AerialPath path: paths){
            Address startAddress = getAddressByGeolocationID(path.getGeoLocationStart());
            Address endAddress = getAddressByGeolocationID(path.getGeoLocationEnd());

            distanceAerialMapController.addAddress(startAddress);
            distanceAerialMapController.addAddress(endAddress);

            distanceAerialMapController.addConnection(path);
        }

        return distanceAerialMapController;
    }

}

//