package portsim.port;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import portsim.ship.NauticalFlag;
import portsim.ship.Ship;

import static org.junit.Assert.*;

public class BulkQuayTest {

    private static BulkQuay quay1;
    private static BulkQuay quay2;

    @Before
    public void setUp(){
        quay1 = new BulkQuay(31, 200);
        quay2 = new BulkQuay(32, 100);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test(expected = IllegalArgumentException.class)
    public void ConstructorException1(){
        BulkQuay bulkQuayId = new BulkQuay(-1, 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ConstructorException2(){
        BulkQuay bulkQuayTonnage = new BulkQuay(3, -1);
    }

    @Test
    public void getMaxTonnageQuay1(){
        assertEquals("The maximum tonnage is not correct", 200, quay1.getMaxTonnage());
    }

    @Test
    public void getMaxTonnageQuay2(){
        assertEquals("The max tonnage is incorrect", 100, quay2.getMaxTonnage());
    }

    @Test
    public void testToStringBulkQuay1() {
        String expected = "BulkQuay 31 [Ship: 12345] - 200";

        assertEquals("The ToString() does not match", expected, quay1.toString());
    }

    @Test
    public void testToStringBulkQuay2(){
        String expected = "BulkQuay 32 [Ship: 56789] - 100";

        assertEquals("The ToString() does not match", expected, quay2.toString());
    }
}