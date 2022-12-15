package lapr.project.model.DomainClasses;

import java.util.Objects;

public class Receipt {

    private final int id;
    private double totalPrice;
    private final int orderId;
    private final String clientNif;
    private final String pharmacyNif;

    public Receipt(int id, double totalPrice, int orderId, String clientNif, String pharmacyNif) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.orderId = orderId;
        this.clientNif = clientNif;
        this.pharmacyNif = pharmacyNif;
    }

    public int getId() {
        return id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getClientNif() {
        return clientNif;
    }

    public String getPharmacyNif() {
        return pharmacyNif;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Receipt)) return false;
        Receipt receipt = (Receipt) o;
        return id == receipt.id && Double.compare(receipt.totalPrice, totalPrice) == 0 && orderId == receipt.orderId && Objects.equals(clientNif, receipt.clientNif) && Objects.equals(pharmacyNif, receipt.pharmacyNif);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalPrice, orderId, clientNif, pharmacyNif);
    }

    @Override
    public String toString() {
        return String.format("Receipt->Id:%s,TotalPrice:%s,OrderId:%s,ClientNIF:%s,PharmacyNIF:%s\n",id,totalPrice,orderId,clientNif,pharmacyNif);
    }
}
