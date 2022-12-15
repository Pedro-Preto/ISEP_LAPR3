package lapr.project.model.Registers;

import lapr.project.data.DeliveryDB;
import lapr.project.model.DomainClasses.Delivery;

public class DeliveryRegister {

    private final DeliveryDB deliveryDB;


    public DeliveryRegister() {
        this.deliveryDB = new DeliveryDB();
    }

    public Boolean addDelivery(int orderId, String status, Double shippingPrice) {
        Delivery dev = new Delivery(orderId, status, shippingPrice);
        return deliveryDB.addDelivery(dev);
    }

    public Boolean removeDelivery(int orderId) {
        return deliveryDB.removeDelivery(orderId);
    }

    public Delivery getDelivery(int id){
        return deliveryDB.getDelivery(id);
    }
}
