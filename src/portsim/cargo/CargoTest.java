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

    @Test
    public void getID() {
    }

    @Test
    public void getDestination() {
    }

    @Test
    public void testToString() {
    }

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
    //
    public void constructorThrowsExceptionNegativeID_Test(){
        try{
        Cargo exception = new DummyCargo(-1,"N/A");
        fail("Cargo constructor should throw an IllegalArgumentException if a" +
                " negative id is given");
        } catch (IllegalArgumentException expected) {}
    }
}