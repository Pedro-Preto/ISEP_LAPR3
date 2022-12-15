package lapr.project.model.DomainClasses;

import java.util.Objects;

public class Stock {

    private final int id;
    private final String pharmacyNif;

    public Stock(int id, String pharmacyNif) {
        this.id = id;
        this.pharmacyNif = pharmacyNif;
    }

    public int getId() {
        return id;
    }

    public String getPharmacyNif() {
        return pharmacyNif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stock)) return false;
        Stock stock = (Stock) o;
        return id == stock.id && Objects.equals(pharmacyNif, stock.pharmacyNif);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pharmacyNif);
    }

    @Override
    public String toString() {
        return String.format("Stock->Id:%s,PharmacyNif:%s",id,pharmacyNif);
    }


}
