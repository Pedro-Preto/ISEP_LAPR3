package lapr.project.model.Registers;

import lapr.project.data.CourierDB;
import lapr.project.model.DomainClasses.Courier;

import java.util.List;

public class CourierRegister {

    private final CourierDB courierDB;

    public CourierRegister() {
        this.courierDB = new CourierDB();
    }

    public boolean addCourier(String nif, String name, String emailAddress, String cellPhone, String iban, double weight, double payload, String pharmacyNif) {
        Courier courier = new Courier(nif, name, emailAddress, cellPhone, iban, weight, payload, pharmacyNif);
        return courierDB.addCourier(courier);
    }

    public boolean removeCourier(String courierNif) {
        return courierDB.removeCourier(courierNif);
    }

    public Courier getCourier(String courierNif) { return courierDB.getCourier(courierNif); }

    public List<Courier> getPharmacyCouriers(String pharmacyNif){
        return courierDB.getPharmacyCouriers(pharmacyNif);
    }

}
