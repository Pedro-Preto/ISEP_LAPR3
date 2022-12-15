package lapr.project.demonstration;

import lapr.project.controller.*;
import lapr.project.model.DomainClasses.*;
import lapr.project.model.Registers.AddressRegister;
import lapr.project.model.Registers.ClientRegister;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class ClientDemonstration {

    /*
    Steps:
        run register
        run log in
        run make Order
        run log out
        run tearDown
    */

    private String clientEmail;
    private String clientPassword;
    private String clientName;
    private String clientNif;

    private String clientCountry;
    private String clientCity;
    private String clientStreet;
    private String clientDoor;

    @Test
    void tearDown(){
        ApplicationEM app = ApplicationEM.getInstance();
        Platform platform = app.getPlatform();
        AddressRegister addressRegister = platform.getAddressRegister();
        ClientRegister clientRegister = platform.getClientRegister();

        clientEmail = "joao254@gmail.com";
        clientNif = "203912987";

        clientRegister.removeClient(clientNif);
        addressRegister.removeAddress(Integer.parseInt(clientNif));
        platform.getAutorization().deleteUser(clientEmail);


        platform.getReceiptRegister().removeReceipt(42);
        platform.getOrderRegister().removeOrder(42);
        platform.getStockItemsRegister().removeStockItems(222,34);
        platform.getStockItemsRegister().removeStockItems(222,35);
        platform.getItemRegister().removeItem(222);
        platform.getStockRegister().removeStock(34);
        platform.getStockRegister().removeStock(35);
        platform.getPharmacyRegister().removePharmacy("299755970");
        platform.getPharmacyRegister().removePharmacy("383246853");
        platform.getAddressRegister().removeAddress(315);
        platform.getAddressRegister().removeAddress(316);
    }

    @Test
    void registerClientDemonstration(){
        ApplicationEM app = ApplicationEM.getInstance();
        Platform platform = app.getPlatform();
        RegisterClientController registerClientController = new RegisterClientController();
        LoginController loginController = new LoginController();
        AddressRegister addressRegister = platform.getAddressRegister();
        ClientRegister clientRegister = platform.getClientRegister();

        //create your account
        clientEmail = "joao254@gmail.com";
        clientPassword = "qwerty123456";
        clientName = "Joao Bispo";
        clientNif = "203912987";
        //insert your address
        clientCountry = "Portugal";
        clientCity = "Lisboa";
        clientStreet = "Rua Andrade";
        clientDoor = "74";

        if (!registerClientController.addAddress(Integer.parseInt(clientNif), clientStreet, clientDoor, clientCity, clientCountry)){
            System.out.println("There was an error verifying your address!");
        } else if (!registerClientController.addClient(clientNif, clientEmail, clientName, Integer.parseInt(clientNif))){
            addressRegister.removeAddress(Integer.parseInt(clientNif));
            System.out.println(("There was an error registering your nif and email!"));
        } else if (!registerClientController.registerClient(clientEmail, clientPassword)) {
            clientRegister.removeClient(clientNif);
            addressRegister.removeAddress(Integer.parseInt(clientNif));
            System.out.println("There was an error verifying your email!");
        } else {
            System.out.println("Registration Successful");
        }
    }

    @Test
    void clientLogInDemonstration(){
        ApplicationEM app = ApplicationEM.getInstance();
        Platform platform = app.getPlatform();
        RegisterClientController registerClientController = new RegisterClientController();
        LoginController loginController = new LoginController();
        AddressRegister addressRegister = platform.getAddressRegister();
        ClientRegister clientRegister = platform.getClientRegister();

        clientEmail = "joao254@gmail.com";
        clientPassword = "qwerty123456";

        if (!loginController.doLogin(clientEmail, clientPassword)){
            System.out.println("The email or the password are incorrect!");
        } else {
            System.out.println("Log In successful");
        }
    }

    @Test
    void clientMakeOrderDemonstration(){
        ApplicationEM app = ApplicationEM.getInstance();
        Platform platform = app.getPlatform();

        LoginController loginController = new LoginController();
        clientEmail = "joao254@gmail.com";
        clientPassword = "qwerty123456";

        loginController.doLogin(clientEmail, clientPassword);

        Item it111 = new Item(222, "paracetamol", 2.75, 0.25);

        platform.getAddressRegister().addAddress(315, "Rua Tome", "135", "Porto", "Portugal");
        platform.getAddressRegister().addAddress(316, "Rua Virada", "023", "Lisboa", "Portugal");


        platform.getPharmacyRegister().addPharmacy("299755970", "Farmacia Trindade", 215);
        platform.getPharmacyRegister().addPharmacy("383246853", "Farmacia Sistelo", 216);

        platform.getStockRegister().addStock(34, "299755970");
        platform.getStockRegister().addStock(35, "383246853");

        platform.getItemRegister().addItem(222, "paracetamol", 2.75, 0.25);

        platform.getStockItemsRegister().addStockItems(222, 34, 24);
        platform.getStockItemsRegister().addStockItems(222, 35, 2);


        MakeOrderController makeOrderController = new MakeOrderController();
        String item1 = "paracetamol";
        Map<String,Integer> items = new HashMap<>();
        items.put(item1,1);
        Map<Item,Integer> orderItems = makeOrderController.pickItemsOfOrder(items);

        int orderid= 42;

        if(orderItems.isEmpty()){
            System.out.println("The items were not found!");

        } else {
            Pharmacy pharmacy = makeOrderController.getNearestPharmacy();

            if (makeOrderController.makeOrder(orderid,orderItems, pharmacy.getNif())){
                Receipt receipt = makeOrderController.makeReceipt(42);

                if(receipt != null){
                    System.out.println(receipt);

                    if (makeOrderController.makeDelivery(Delivery.getShippingCosts())){
                        System.out.println("Your order was registed sucessfully. Please check your email to more informations!");

                    }
                } else {
                    System.out.println("The payment were not received or problems during receipt emission.");

                }

            } else {
                System.out.println("The items are not available");

            }
        }

    }

    @Test
    void clientLogOutDemonstration(){
        LoginController loginController = new LoginController();

        loginController.doLogout();
        System.out.println("Logged Out");
    }
}
