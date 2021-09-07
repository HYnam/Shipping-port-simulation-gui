package portsim.ship;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import portsim.cargo.BulkCargo;
import portsim.cargo.BulkCargoType;
import portsim.port.BulkQuay;
import portsim.port.ContainerQuay;
import portsim.util.NoSuchCargoException;

import static org.junit.Assert.*;

public class BulkCarrierTest {

    private BulkCarrier bulkCarrier1;
    private BulkCarrier bulkCarrier2;
    private BulkCarrier bulkCarrier3;

    private BulkQuay bulkQuay;
    private ContainerQuay containerQuay;

    private BulkCargo bulkCargo;
    @Before
    public void setUp() {
        bulkCarrier1 = new BulkCarrier(1234567, "Peter", "Hong Kong", NauticalFlag.BRAVO, 2000);
        bulkCarrier2 = new BulkCarrier(2345567, "Johnson", "London", NauticalFlag.HOTEL, 3000);
        bulkCarrier3 = new BulkCarrier(5555678, "Jackson", "New York", NauticalFlag.NOVEMBER, 5000);
    }

    @Test
    public void testCanDockWithCorrectQuay() {
        bulkQuay = new BulkQuay(300, 10000);
        boolean result = bulkCarrier1.canDock(bulkQuay);
        boolean expected = true;
        assertTrue(result == expected);
    }

    @Test
    public void testCanDockWithIncorrectQuay() {
        containerQuay = new ContainerQuay(301, 10);
        boolean result = bulkCarrier1.canDock(containerQuay);
        boolean expected = false;
        assertTrue(result == expected);
    }

    @Test
    public void testCanLoadWithCorrectCargo() {
        bulkCargo = new BulkCargo(231003, "Hong Kong", 500, BulkCargoType.OIL);
        boolean result = bulkCarrier1.canLoad(bulkCargo);
        boolean expected = true;
        assertTrue(result == expected);
    }

    @Test
    public void testCanLoadCargoWithIncorrectDestination() {
        bulkCargo = new BulkCargo(231003, "London", 500, BulkCargoType.OIL);
        boolean result = bulkCarrier1.canLoad(bulkCargo);
        boolean expected = false;
        assertTrue(result == expected);
    }

    @Test
    public void testCanLoadCargoWithIncorrectTonnage() {
        bulkCargo = new BulkCargo(231003, "Hong Kong", 50000, BulkCargoType.OIL);
        boolean result = bulkCarrier1.canLoad(bulkCargo);
        boolean expected = false;
        assertTrue(result == expected);
    }

    @Test
    public void testGetCargo() {
        bulkCargo = new BulkCargo(231003, "Hong Kong", 500, BulkCargoType.OIL);
        bulkCarrier1.loadCargo(bulkCargo);
        BulkCargo result = bulkCarrier1.getCargo();
        BulkCargo expected = bulkCargo;
        assertEquals("Cannot get the cargo from bulkCarrier", result, expected);
    }

    @Test
    public void testUnloadCargo() throws NoSuchCargoException {
        bulkCargo = new BulkCargo(231003, "Hong Kong", 500, BulkCargoType.OIL);
        bulkCarrier1.loadCargo(bulkCargo);
        BulkCargo result;
        BulkCargo expected;

        result = bulkCarrier1.unloadCargo();
        expected = bulkCargo;
        assertEquals("The return form unloadCargo is incorrect", result, expected);

        result = bulkCarrier1.getCargo();
        expected = null;
        assertEquals("The cargo in bulkCarrier1 is not null after unloadCargo()", result, expected);
    }

    @Test(expected = NoSuchCargoException.class)
    public void testUnloadCargoException() throws NoSuchCargoException {
        if(bulkCarrier1.getCargo() == null) {
            bulkCarrier1.unloadCargo();
        } else {
            bulkCarrier1.unloadCargo();
            bulkCarrier1.unloadCargo();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void ConstructorException1() {
        BulkCarrier bulkCarrierImoNumber = new BulkCarrier(-1, "Peter", "Shang Hai", NauticalFlag.WHISKEY, 3000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ConstructorException2() {
        BulkCarrier bulkCarrierImoNumberLength = new BulkCarrier(1234, "John", "New Zealand", NauticalFlag.BRAVO, 4000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ConstructorException3() {
        BulkCarrier bulkCarrierCapacity = new BulkCarrier(7777777, "Tommy", "Australia", NauticalFlag.HOTEL, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ConstructorException4() {
        BulkCarrier bulkCarrierAll = new BulkCarrier(-1, "Maria", "Hong Kong", NauticalFlag.BRAVO, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ConstructorException5() {
        BulkCarrier bulkCarrier = new BulkCarrier(7, "Jackson", "London", NauticalFlag.HOTEL, -1);
    }

    @Test
    public void testToStringBulkCarrier1() {
        bulkCargo = new BulkCargo(231003, "Hong Kong", 500, BulkCargoType.MINERALS);
        bulkCarrier1.loadCargo(bulkCargo);
        String expected = "BulkCarrier Peter from Hong Kong [BRAVO] carrying MINERALS";
        assertEquals("The message toString does not match", expected, bulkCarrier1.toString());
    }

    @Test
    public void testToStringBulkCarrier2() {
        bulkCargo = new BulkCargo(231003, "London", 500, BulkCargoType.OIL);
        bulkCarrier2.loadCargo(bulkCargo);
        String expected = "BulkCarrier Johnson from London [HOTEL] carrying OIL";
        assertEquals("The message toString does not match", expected, bulkCarrier2.toString());
    }

    @Test
    public void testToStringBulkCarrier3() {
        bulkCargo = new BulkCargo(231003, "New York", 500, BulkCargoType.COAL);
        bulkCarrier3.loadCargo(bulkCargo);
        String expected = "BulkCarrier Jackson from New York [NOVEMBER] carrying COAL";
        assertEquals("The message toString does not match", expected, bulkCarrier3.toString());
    }

    @After
    public void tearDown() throws Exception {
    }

}