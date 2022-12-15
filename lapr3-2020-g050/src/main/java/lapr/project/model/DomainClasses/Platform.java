package lapr.project.model.DomainClasses;

import lapr.project.model.Registers.*;
import lapr.project.model.autorization.AutorizationFacade;

public class Platform {

    private final AutorizationFacade autorization;
    private Catalogue catalogue;
    private AddressRegister addressRegister;
    private AerialPathRegister aerialPathRegister;
    private ChargingStationRegister chargingStationRegister;
    private ClientRegister clientRegister;
    private CourierRegister courierRegister;
    private CreditsRegister creditsRegister;
    private DeliveryRegister deliveryRegister;
    private DroneParkRegister droneParkRegister;
    private DroneRegister droneRegister;
    private GeoLocationRegister geoLocationRegister;
    private ItemRegister itemRegister;
    private ItemsOfOrderRegister itemsOfOrderRegister;
    private LandPathRegister landPathRegister;
    private OrderRegister orderRegister;
    private ParkSlotRegister parkSlotRegister;
    private PharmacyAdministratorRegister pharmacyAdministratorRegister;
    private PharmacyRegister pharmacyRegister;
    private ReceiptRegister receiptRegister;
    private ScooterParkRegister scooterParkRegister;
    private ScooterRegister scooterRegister;
    private StockItemsRegister stockItemsRegister;
    private StockRegister stockRegister;
    private LocationNet locationNet;

    public Platform() {
        this.autorization = new AutorizationFacade();
        resetData();
    }

    public AutorizationFacade getAutorization() {
        return this.autorization;
    }


    public Catalogue getCatalogue() {
        return this.catalogue;
    }

    //------------------------Registers------------------------------------


    public AddressRegister getAddressRegister() {
        return addressRegister;
    }

    public AerialPathRegister getAerialPathRegister() {
        return aerialPathRegister;
    }

    public ChargingStationRegister getChargingStationRegister() {
        return chargingStationRegister;
    }

    public ClientRegister getClientRegister() {
        return this.clientRegister;
    }

    public CourierRegister getCourierRegister() {
        return courierRegister;
    }

    public CreditsRegister getCreditsRegister() {
        return creditsRegister;
    }


    public DeliveryRegister getDeliveryRegister() {
        return deliveryRegister;
    }

    public DroneParkRegister getDroneParkRegister() {
        return droneParkRegister;
    }

    public DroneRegister getDroneRegister() {
        return droneRegister;
    }

    public GeoLocationRegister getGeoLocationRegister() {
        return geoLocationRegister;
    }

    public ItemRegister getItemRegister() {
        return itemRegister;
    }

    public ItemsOfOrderRegister getItemsOfOrderRegister() {
        return itemsOfOrderRegister;
    }

    public LandPathRegister getLandPathRegister() {
        return landPathRegister;
    }

    public OrderRegister getOrderRegister() {
        return orderRegister;
    }

    public ParkSlotRegister getParkSlotRegister() {
        return parkSlotRegister;
    }

    public PharmacyAdministratorRegister getPharmacyAdministratorRegister() {
        return pharmacyAdministratorRegister;
    }

    public PharmacyRegister getPharmacyRegister() {
        return pharmacyRegister;
    }

    public ReceiptRegister getReceiptRegister() {
        return receiptRegister;
    }

    public ScooterParkRegister getScooterParkRegister() {
        return scooterParkRegister;
    }

    public ScooterRegister getScooterRegister() {
        return scooterRegister;
    }

    public StockItemsRegister getStockItemsRegister() {
        return stockItemsRegister;
    }

    public StockRegister getStockRegister(){
        return stockRegister;
    }

    public LocationNet getMap() {
        return this.locationNet;
    }

    public void resetData() {

        this.catalogue = new Catalogue();
        this.addressRegister = new AddressRegister();
        this.aerialPathRegister = new AerialPathRegister();
        this.chargingStationRegister = new ChargingStationRegister();
        this.clientRegister = new ClientRegister();
        this.courierRegister = new CourierRegister();
        this.creditsRegister = new CreditsRegister();
        this.deliveryRegister = new DeliveryRegister();
        this.droneParkRegister = new DroneParkRegister();
        this.droneRegister = new DroneRegister();
        this.geoLocationRegister = new GeoLocationRegister();
        this.itemRegister = new ItemRegister();
        this.itemsOfOrderRegister = new ItemsOfOrderRegister();
        this.landPathRegister = new LandPathRegister();
        this.parkSlotRegister = new ParkSlotRegister();
        this.pharmacyAdministratorRegister = new PharmacyAdministratorRegister();
        this.pharmacyRegister = new PharmacyRegister();
        this.receiptRegister = new ReceiptRegister();
        this.orderRegister = new OrderRegister(receiptRegister);
        this.scooterParkRegister = new ScooterParkRegister();
        this.scooterRegister = new ScooterRegister();
        this.stockItemsRegister = new StockItemsRegister();
        this.stockRegister=new StockRegister();
        this.locationNet = new LocationNet();
    }



}
