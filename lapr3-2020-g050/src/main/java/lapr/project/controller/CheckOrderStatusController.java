package lapr.project.controller;

import lapr.project.model.DomainClasses.Platform;
import lapr.project.model.Registers.OrderRegister;
import java.util.List;

public class CheckOrderStatusController {

    private final OrderRegister orderRegister;


    public CheckOrderStatusController() {
        ApplicationEM app = ApplicationEM.getInstance();
        Platform platform = app.getPlatform();
        this.orderRegister= platform.getOrderRegister();
    }

    public List<String> checkClientOrderStatus(int orderId,String clientNIF){
        return orderRegister.checkClientOrderStatus(orderId,clientNIF);
    }

}
