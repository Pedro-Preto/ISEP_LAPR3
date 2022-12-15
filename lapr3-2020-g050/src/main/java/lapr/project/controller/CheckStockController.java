package lapr.project.controller;

import lapr.project.model.DomainClasses.*;
import lapr.project.model.Registers.PharmacyAdministratorRegister;
import lapr.project.model.Registers.StockItemsRegister;
import lapr.project.model.Registers.StockRegister;

import java.util.List;

public class CheckStockController {

    private final StockRegister stockRegister;
    private final StockItemsRegister stockItemRegister;
    private final PharmacyAdministratorRegister pharmacyAdministratorRegister;
    private final Platform platform;

    public CheckStockController() {
        ApplicationEM app = ApplicationEM.getInstance();
        this.platform = app.getPlatform();
        this.stockRegister = platform.getStockRegister();
        this.stockItemRegister = platform.getStockItemsRegister();
        this.pharmacyAdministratorRegister = platform.getPharmacyAdministratorRegister();
    }

    public List<StockItems> getStock(){
        String email = platform.getAutorization().getUserSession().getUserEmail();
        PharmacyAdministrator pharmacyAdministrator = pharmacyAdministratorRegister.getPharmacyAdministrator(email);
        Stock stock = this.stockRegister.getStockByPharmacyNif(pharmacyAdministrator.getPharmacyNif());
        return this.stockItemRegister.getAllItemsOfStock(stock.getId());
    }
}
