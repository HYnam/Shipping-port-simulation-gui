package portsim.port;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import portsim.ship.ContainerShip;
import portsim.ship.NauticalFlag;
import portsim.ship.Ship;

import static org.junit.Assert.*;

public class QuayTest {

    DummyQuay quay1;
    DummyQuay quay2;
    Ship containerShip;
    BulkQuay bulkQuay;
    ContainerQuay containerQuay;


    /*
     * Dummy Quay don't extend BulkQuay or ContainerQuay, useful
     * for testing methods overridden in Quay subclasses
     */
    static class DummyQuay extends Quay {

        public DummyQuay(int id) {
            super(id);
        }

    }

    @Before
    public void setUp() throws Exception {
        containerShip = new ContainerShip(1234567,"ABC","DEF",
                NauticalFlag.BRAVO,50);
        quay1 = new DummyQuay(1);
        quay2 = new DummyQuay(2);
        bulkQuay = new BulkQuay(1,50);
        containerQuay = new ContainerQuay(1,50);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Rule
    public Timeout timeout = Timeout.seconds(1);

    //
    //
    //
    //
    //
    //
    //
    //

    //
    //
    //
    //
    //

    //
    //
    //
    //
    //
    //

    @Test
    //
    public void shipDeparts() {
        quay1.shipArrives(containerShip);
        assertEquals(quay1.getShip(),containerShip);
        quay1.shipDeparts();
        assertNull(quay1.getShip());
    }

    @Test
    //
    public void isEmpty() {
        assertTrue(quay1.isEmpty());
        quay1.shipArrives(containerShip);
        assertFalse(quay1.isEmpty());
    }
}