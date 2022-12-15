package lapr.project.model.DomainClasses;

import java.util.Objects;

public class Order {

    private final int id;
    private final String status;
    private final double itemsPrice;
    private final String clientNif;

    public Order(int id, String status, double itemsPrice, String clientNif) {
        this.id = id;
        this.status = status;
        this.itemsPrice = itemsPrice;
        this.clientNif = clientNif;
    }

    
    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public double getItemsPrice() {
        return itemsPrice;
    }

    public String getClientNif() {
        return clientNif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return id == order.id && Double.compare(order.itemsPrice, itemsPrice) == 0 && Objects.equals(status, order.status) && Objects.equals(clientNif, order.clientNif);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, itemsPrice, clientNif);
    }

    @Override
    public String toString() {
        return String.format("Order->Id:%s,Status:%s,ItemsPrice:%s,ClientNif:%s",id,status,itemsPrice,clientNif);
    }
}
