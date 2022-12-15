package lapr.project.controller;

import lapr.project.model.DomainClasses.Client;
import lapr.project.model.DomainClasses.Platform;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CheckScootersBatteryControllerTest {
    private Platform platform;
    private CheckScootersBatteryController checkScootersBatteryController;
    private AddPharmacyController addPharmacyController;
    private AddScooterController addScooterController;
    private AddScooterParkController addScooterParkController;

    @BeforeEach
    void setUp() {
        platform = ApplicationEM.getInstance().getPlatform();
        tearDown();
        checkScootersBatteryController = new CheckScootersBatteryController();
        addPharmacyController = new AddPharmacyController();
        addScooterController = new AddScooterController();
        addScooterParkController = new AddScooterParkController();

        addPharmacyController.addAddress(3601, "Rua 6", "135", "Porto", "Portugal");
        addPharmacyController.addPharmacy("teste", "Farmacia Torres", 3601);

        addScooterController.addScooter(3611, 24.0, 3.3, 4.7, 15.0, 134.5, 4.3, 20.0, "teste", "12");
        addScooterController.addScooter(3612, 24.0, 3.3, 4.7, 16.0, 134.5, 4.3, 20.0, "teste", "13");
        addScooterParkController.addScooterPark(3621, "teste", 10.0);

        platform.getParkSlotRegister().addParkSlot(3631, 3621, 3611);
        platform.getParkSlotRegister().addParkSlot(3632, 3621, 3612);


    }

    @AfterEach
    void tearDown() {
        platform.getParkSlotRegister().removeParkSlot(3631);
        platform.getParkSlotRegister().removeParkSlot(3632);
        platform.getScooterParkRegister().removeScooterPark(3621);
        platform.getScooterRegister().removeScooter(3611);
        platform.getScooterRegister().removeScooter(3612);
        platform.getPharmacyRegister().removePharmacy("teste");
        platform.getAddressRegister().removeAddress(3601);
    }


    @Test
    void checkScootersParkedBatterys() {


        List<Double> expected = new ArrayList<>();
        double e1 = platform.getScooterRegister().getScooter(3611).getCurrentBattery();
        double e2 = platform.getScooterRegister().getScooter(3612).getCurrentBattery();
        expected.add(e1);
        expected.add(e2);


        List<Double> result = new ArrayList<>();
        Double r1 = checkScootersBatteryController.checkScootersParkedBatterys(3611, "teste");
        Double r2 = checkScootersBatteryController.checkScootersParkedBatterys(3612, "teste");
        result.add(r1);
        result.add(r2);
        System.out.println(expected + " AND " + result);
        assertEquals(expected, result);


    }


}