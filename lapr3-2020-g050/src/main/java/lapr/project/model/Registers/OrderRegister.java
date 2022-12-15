package lapr.project.model.Registers;


import lapr.project.data.OrderDB;
import lapr.project.model.DomainClasses.*;

import java.util.ArrayList;
import java.util.List;

public class OrderRegister {

    private final OrderDB orderDB;
    private final ReceiptRegister receiptRegister;

    public OrderRegister(ReceiptRegister receiptRegister) {
        this.orderDB = new OrderDB();
        this.receiptRegister = receiptRegister;
    }

    public boolean addOrder(int id, String status, Double itemsPrice, String clientNif) {
        Order order = new Order(id, status, itemsPrice, clientNif);
        return orderDB.addOrder(order);
    }

    public boolean removeOrder(int orderId) {
        return orderDB.removeOrder(orderId);
    }

    public Order getOrder(int id) {
        return orderDB.getOrder(id);
    }

    public List<Order> checkOrders(String pharNIF) {
        List<Order> o = new ArrayList<>();
        List<Receipt> rList = receiptRegister.getReceiptByPharmacyNif(pharNIF);
        for (Receipt receipt : rList) {
            int orderId = receipt.getOrderId();
            Order pharOrders = orderDB.getOrder(orderId);
            o.add(pharOrders);
        }
        return o;
    }

    public boolean updateOrder(int id, String status, Double itemsPrice, String clientNif){
        return orderDB.updateOrder(id, status, itemsPrice, clientNif);
    }

    public List<Order> checkClientOrders(String clientNIF){
        List<Order> o= new ArrayList<>();
        List<Receipt> rList=receiptRegister.getReceiptByClientNif(clientNIF);
        for (Receipt receipt:rList){
            int orderId=receipt.getOrderId();
            Order clientOrder=orderDB.getOrder(orderId);
            o.add(clientOrder);
        }
        return o;
    }
    public List<String> checkClientOrderStatus(int orderId,String clientNIF){
        List<String> status=new ArrayList<>();
        List<Order> clientOrdedr=checkClientOrders(clientNIF);
        for (Order order:clientOrdedr){
            int oId =order.getId();
            if(orderId==oId) {
                String flag = order.getStatus();
                status.add(flag);
            }
        }
        return status;
    }
}


