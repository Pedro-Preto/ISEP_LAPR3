package lapr.project.controller;

import lapr.project.model.DomainClasses.Courier;
import lapr.project.model.DomainClasses.Platform;
import lapr.project.model.Registers.CourierRegister;

public class RegisterCourierController {

    private final CourierRegister courierRegister;
    private final RegistrationController registrationController;

    public RegisterCourierController() {
        ApplicationEM app = ApplicationEM.getInstance();
        Platform platform = app.getPlatform();
        this.courierRegister = platform.getCourierRegister();
        this.registrationController = new RegistrationController();
    }

    public Boolean addCourier(String nif, String name, String emailAddress, String cellPhone, String iban, double weight, double payload, String pharmacyNif) {
        return courierRegister.addCourier(nif, name, emailAddress, cellPhone, iban, weight, payload, pharmacyNif);
    }

    public Boolean registerCourier(String nif){
        Courier courier = courierRegister.getCourier(nif);
        return registrationController.registerCourier(courier);
    }

    public boolean removeCourier(String nif){
        return courierRegister.removeCourier(nif);
    }
}
