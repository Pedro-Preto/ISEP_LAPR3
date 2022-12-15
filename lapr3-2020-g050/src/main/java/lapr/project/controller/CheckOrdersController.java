package lapr.project.controller;

import lapr.project.model.DomainClasses.Order;
import lapr.project.model.DomainClasses.Platform;
import lapr.project.model.Registers.OrderRegister;

import java.util.List;

public class CheckOrdersController {

    private final OrderRegister orderRegister;


    public CheckOrdersController() {
        ApplicationEM app = ApplicationEM.getInstance();
        Platform platform = app.getPlatform();
        this.orderRegister= platform.getOrderRegister();
    }

    public List<Order> checkOrders(String pharNIF) {

        return orderRegister.checkOrders(pharNIF);
    }
}
