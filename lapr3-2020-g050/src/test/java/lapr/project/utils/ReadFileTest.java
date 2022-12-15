package lapr.project.utils;

import lapr.project.model.DomainClasses.Client;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReadFileTest {

    ReadFile readFile;

    @Test
    void readFiles() {

        readFile = new ReadFile();
        boolean address = readFile.readAddressFile();
        boolean pharmacies = readFile.readPharmaciesFile();
        boolean clients = readFile.readClientsFile();
        boolean geo = readFile.readGeoLocationFile();
        boolean airPath = readFile.readAirPathFile();
        boolean landPath = readFile.readLandPathFile();

        assertTrue(pharmacies,"Should be true");
        assertTrue(address,"Should be true");
        assertTrue(clients,"Should be true");
        assertTrue(geo,"Should be true");
        assertTrue(landPath,"Should be true");
        assertTrue(airPath,"Should be true");
    }
}