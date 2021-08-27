package portsim.cargo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BulkCargoTest {

    private BulkCargo bulkCargo1;
    private BulkCargo bulkCargo2;
    private BulkCargo bulkCargo3;

    @Before
    public void setup(){
        bulkCargo1 = new BulkCargo(1, "Hong Kong", 1000, BulkCargoType.OIL);
        bulkCargo2 = new BulkCargo(2, "Australia", 1500, BulkCargoType.COAL);
        bulkCargo3 = new BulkCargo(3, "UK", 2000, BulkCargoType.GRAIN);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getTonnageBulkCargo1() {
        assertEquals("This is no correct", 1000, bulkCargo1.getTonnage());
    }

    @Test
    public void getTonnageBulkCargo2() {
        assertEquals("This is no correct", 1500, bulkCargo2.getTonnage());
    }

    @Test
    public void getTonnageBulkCargo3() {
        assertEquals("This is no correct", 2000, bulkCargo3.getTonnage());
    }

    @Test
    public void getTypeBulkCargo1() {
        assertEquals("This is not correct", BulkCargoType.OIL, bulkCargo1.getType());
    }

    @Test
    public void getTypeBulkCargo2() {
        assertEquals("This is not correct", BulkCargoType.COAL, bulkCargo2.getType());
    }

    @Test
    public void getTypeBulkCargo3() {
        assertEquals("This is not correct", BulkCargoType.GRAIN, bulkCargo2.getType());
    }

    @Test
    public void testToStringBulkCargo1() {
        String expected = "BulkCargo 1 to Hong Kong [OIL - 1000]";

        assertEquals("The ToString() does not match", expected, bulkCargo1.toString());
    }

    @Test
    public void testToStringBulkCargo2() {
        String expected = "BulkCargo 2 to Australia [COAL - 1500]";

        assertEquals("The ToString() does not match", expected, bulkCargo2.toString());
    }

    @Test
    public void testToStringBulkCargo3() {
        String expected = "BulkCargo 3 to UK [GRAIN - 2000]";

        assertEquals("The ToString() does not match", expected, bulkCargo3.toString());
    }
}