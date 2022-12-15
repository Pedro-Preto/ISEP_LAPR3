package lapr.project.model.DomainClasses;

import java.util.Objects;

public class Delivery {
    private static final double SHIPPING_COSTS = 3.00;
    private int orderId;
    private String status;
    private Double shippingPrice;
    private String courierNIF;
    private int droneId;


    public Delivery(int orderId, String status, Double shippingPrice, String courierNIF, int droneId) {
        this.orderId = orderId;
        this.status = status;
        this.shippingPrice = shippingPrice;
        this.courierNIF = courierNIF;
        this.droneId = droneId;
    }

    public Delivery(int orderId, String status, Double shippingPrice) {
        this.orderId = orderId;
        this.status = status;
        this.shippingPrice = shippingPrice;
    }

    public static double getShippingCosts(){
        return SHIPPING_COSTS;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getStatus() {
        return status;
    }

    public Double getShippingPrice() {
        return shippingPrice;
    }

    public String getCourierNIF() {
        return courierNIF;
    }

    public int getDroneId() {
        return droneId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Delivery)) return false;
        Delivery delivery = (Delivery) o;
        return orderId == delivery.orderId && droneId == delivery.droneId && Objects.equals(status, delivery.status) && Objects.equals(shippingPrice, delivery.shippingPrice) && Objects.equals(courierNIF, delivery.courierNIF);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, status, shippingPrice, courierNIF, droneId);
    }

    @Override
    public String toString() {
        return String.format("Delivery-> orderId:%s,status:%s,shippingPrice:%s,courierNIF:%s,DroneId:%s",orderId,status,shippingPrice,courierNIF,droneId);


    }
}
