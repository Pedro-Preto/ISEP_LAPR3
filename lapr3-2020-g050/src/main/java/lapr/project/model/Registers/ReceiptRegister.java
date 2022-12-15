package lapr.project.model.Registers;

import lapr.project.data.OrderDB;
import lapr.project.data.ReceiptDB;
import lapr.project.model.DomainClasses.Order;
import lapr.project.model.DomainClasses.Receipt;

import java.util.List;

public class ReceiptRegister {

    private final ReceiptDB receiptDB;

    public ReceiptRegister() {
        this.receiptDB = new ReceiptDB();
    }

    public boolean addReceipt(int id, double totalPrice, int orderId, String clientNif, String pharmacyNif) {
        Receipt receipt = new Receipt(id, totalPrice, orderId, clientNif, pharmacyNif);
        return receiptDB.addReceipt(receipt);
    }

    public boolean removeReceipt(int receiptId) {
        return receiptDB.removeReceipt(receiptId);
    }

    public Receipt getReceipt(int id) {
        return receiptDB.getReceipt(id);
    }


    public List<Receipt> getReceiptByPharmacyNif(String pharNIF) {
        return receiptDB.getReceiptByPharmacyNif(pharNIF);
    }

    public List<Receipt> getReceiptByClientNif(String clientNIF) {
        return receiptDB.getReceiptByClientNif(clientNIF);
    }
}
