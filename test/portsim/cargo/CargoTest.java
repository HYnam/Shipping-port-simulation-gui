package portsim.cargo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.*;

public class CargoTest {

    private DummyCargo dummyCargo1;
    private DummyCargo dummyCargo2;
    private DummyCargo dummyCargo3;

    /*
     * Dummy Cargo don't extend BulkCargo or Container, useful
     * for testing methods overridden in Cargo subclasses
     */
    static class DummyCargo extends Cargo {

        public DummyCargo(int id, String destination) {
            super(id, destination);
        }

    }
    @Before
    public void setUp() throws Exception {

        dummyCargo1 = new DummyCargo(1,"Australia");
        dummyCargo2 = new DummyCargo(2,"Australia");
        dummyCargo3 = new DummyCargo(3,"New Zealand");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Rule
    public Timeout timeout = Timeout.seconds(1);

    @Test
    public void getIDCargo1() {
        assertEquals("The id was incorrect.", 1, dummyCargo1.getID());
    }

    @Test
    public void getIDCargo2(){
        assertEquals("The id was incorrect.", 2, dummyCargo2.getID());
    }

    @Test
    public void getIDCargo3(){
        assertEquals("The id was incorrect", 3, dummyCargo3.getID());
    }

    @Test
    public void getDestinationCargo1() {
        assertEquals("The distinction is incorrect", "Australia", dummyCargo1.getDestination());
    }

    @Test
    public void getDestinationCargo2() {
        assertEquals("The distinction is incorrect", "Australia", dummyCargo2.getDestination());
    }

    @Test
    public void getDestinationCargo3() {
        assertEquals("The distinction is incorrect", "New Zealand", dummyCargo3.getDestination());
    }

    @Test
    public void testToString() {
    }

    @Test
    //
    public void constructorThrowsExceptionNegativeID_Test(){
        try{
        Cargo exception = new DummyCargo(-1,"N/A");
        fail("Cargo constructor should throw an IllegalArgumentException if a" +
                " negative id is given");
        } catch (IllegalArgumentException expected) {}
    }
}