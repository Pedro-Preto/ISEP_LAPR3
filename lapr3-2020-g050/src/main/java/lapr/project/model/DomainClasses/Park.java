package lapr.project.model.DomainClasses;

public abstract class Park {

    private final int id;
    private final String pharmacyNIF;
    private final double power;


    public Park(int id, String pharmacyNIF, double power) {
        this.id = id;
        this.pharmacyNIF = pharmacyNIF;
        this.power = power;
    }

    public int getId() {
        return id;
    }

    public String getPharmacyNIF() {
        return pharmacyNIF;
    }

    public double getPower() {
        return power;
    }

    @Override
    public String toString() {
        return String.format("Park->Id:%s,PharmacyNIF:%s",id,pharmacyNIF);
    }
}
