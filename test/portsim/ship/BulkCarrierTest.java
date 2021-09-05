package portsim.ship;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BulkCarrierTest {

    private BulkCarrier bulkCarrier1;
    private BulkCarrier bulkCarrier2;
    private BulkCarrier bulkCarrier3;

    @Before
    public void setUp() {
        bulkCarrier1 = new BulkCarrier(1234567, "Peter", "Hong Kong", NauticalFlag.BRAVO, 2000);
        bulkCarrier2 = new BulkCarrier(2345567, "Johnson", "London", NauticalFlag.HOTEL, 3000);
        bulkCarrier3 = new BulkCarrier(5555678, "Jackson", "New York", NauticalFlag.NOVEMBER, 5000);
    }

    @After
    public void tearDown() throws Exception {
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


}