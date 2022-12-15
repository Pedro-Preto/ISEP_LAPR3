package lapr.project.model.Registers;

import lapr.project.controller.ApplicationEM;
import lapr.project.model.DomainClasses.Address;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressRegisterTest {

    AddressRegister addressRegister;

    @BeforeEach
    void setUp() {
        addressRegister = ApplicationEM.getInstance().getPlatform().getAddressRegister();
        ApplicationEM.getInstance().getPlatform().resetData();
    }

    @AfterEach
    void tearDown() {
        addressRegister.removeAddress(88);
        addressRegister.removeAddress(99);
    }

    @Test
    void addAddress() {
        boolean actual = addressRegister.addAddress(88, "Rua 88", "88","City88", "Country88");
        assertTrue(actual, "Should be true because there isn't any address with this id on the db.");

        actual = addressRegister.addAddress(88, "Rua 88", "88","City88", "Country88");
        assertFalse(actual, "Should be false because there is already an address with this id on the db.");

        actual = addressRegister.addAddress(99,"Rua 99", "99", "City99", "Coutry99");
        assertTrue(actual, "Should be true because there isn't any address with this id on the db.");
    }

    @Test
    void removeAddress() {
        addressRegister.addAddress(88, "Rua 88", "88","City88", "Country88");
        addressRegister.addAddress(99,"Rua 99", "99", "City99", "Coutry99");

        boolean actual = addressRegister.removeAddress(88);
        assertTrue(actual, "Should be true because there is an address with this id on the db.");
        actual = addressRegister.removeAddress(88);
        assertFalse(actual, "Should be false because there isn't any address with this id on the db.");
        actual = addressRegister.removeAddress(99);
        assertTrue(actual, "Should be true because there is an address with this id on the db.");
        actual = addressRegister.removeAddress(8888);
        assertFalse(actual, "Should be false because there isn't any address with this id on the db.");
    }

    @Test
    void getAddressByID() {
        addressRegister.addAddress(88, "Rua 88", "88","City88", "Country88");
        addressRegister.addAddress(99,"Rua 99", "99", "City99", "Coutry99");

        Address expected = new Address(88,"Rua 88","88","City88", "Country88");
        Address actual = addressRegister.getAddressByID(88);

        assertEquals(expected,actual,"They should be the same.");

        expected = new Address(99,"Rua 99", "99", "City99", "Coutry99");
        actual = addressRegister.getAddressByID(99);

        assertEquals(expected, actual, "They should be the same.");

        boolean thrown = false;
        try {
            addressRegister.getAddressByID(-1);
        } catch(IllegalArgumentException ex){
            ex.printStackTrace();
            thrown = true;
        }
        assertTrue(thrown, "The exception should be thrownbecause the id doesn't exist.");

    }
}