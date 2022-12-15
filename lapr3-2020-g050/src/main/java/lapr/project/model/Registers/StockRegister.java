package lapr.project.model.Registers;

import lapr.project.data.StockDB;
import lapr.project.model.DomainClasses.Stock;

import java.util.List;

public class StockRegister {

    private final StockDB stockDB;

    public StockRegister(){
        this.stockDB = new StockDB();
    }

    public boolean addStock(int id, String pharmacyNif){
        Stock stock = new Stock(id, pharmacyNif);
        return stockDB.addStock(stock);
    }

    public boolean removeStock(int stockId) {
        return stockDB.removeStock(stockId);
    }

    public Stock getStockByPharmacyNif(String pharmacyNif) { return stockDB.getStockByPharmacyNif(pharmacyNif);}

    public List<Stock> getAllStocks() { return stockDB.getAllStocks(); }

}

