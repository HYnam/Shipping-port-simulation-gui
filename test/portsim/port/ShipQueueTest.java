package portsim.port;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import portsim.cargo.BulkCargo;
import portsim.ship.BulkCarrier;
import portsim.ship.ContainerShip;
import portsim.ship.NauticalFlag;
import portsim.ship.Ship;
import portsim.util.BadEncodingException;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.*;

public class ShipQueueTest {

    private ShipQueue shipQueue = new ShipQueue();
    private ShipQueue shipQueue2 = new ShipQueue();

    private BulkCarrier bulkCarrier1;
    private BulkCarrier bulkCarrier2;
    private ContainerShip containerShip1;
    private ContainerShip containerShip2;
    private ContainerShip containerShip3;

    private BulkCargo bulkCargo;
    @Before
    public void setUp() throws Exception {
        bulkCarrier1 = new BulkCarrier(2345678, "Ship1", "Italy", NauticalFlag.WHISKEY, 10000);
        bulkCarrier2 = new BulkCarrier(2345679, "Ship2", "China", NauticalFlag.BRAVO, 1000);
        containerShip1 = new ContainerShip(2545345, "Ship3", "France", NauticalFlag.HOTEL, 20);
        containerShip2 = new ContainerShip(2545349, "Ship4", "Canada", NauticalFlag.WHISKEY, 30);
        containerShip3 = new ContainerShip(2545949, "Ship5", "Canada", NauticalFlag.NOVEMBER, 30);

        shipQueue.add(bulkCarrier1);
        shipQueue.add(bulkCarrier2);
        shipQueue.add(containerShip1);
        shipQueue.add(containerShip2);
        shipQueue.add(containerShip3);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testPoll() {
        assertEquals("This is not correct", bulkCarrier1, shipQueue.poll());
    }

    @Test
    public void testPeekWithDangerousCargo() {
        assertEquals("This is not correct", bulkCarrier2, shipQueue.peek());
    }

    @Test
    public void testPeekWithMultipleMedicalCargo() {
        shipQueue = new ShipQueue();
        shipQueue.add(bulkCarrier1);
        shipQueue.add(containerShip1);
        shipQueue.add(containerShip2);
        shipQueue.add(containerShip3);

        assertEquals("This is not correct", bulkCarrier1, shipQueue.peek());
    }

    @Test
    public void testPeekWithReadyToDockedCargo() {
        shipQueue = new ShipQueue();
        shipQueue.add(containerShip1);
        shipQueue.add(containerShip3);
        assertEquals("This is not correct", containerShip1, shipQueue.peek());
    }

    @Test
    public void testPeekWithNoShip() {
        shipQueue = new ShipQueue();
        assertEquals("This is not correct", null, shipQueue.peek());
    }

    @Test
    public void testGetShipQueue() {
        shipQueue = new ShipQueue();
        shipQueue.add(bulkCarrier1);
        Queue<Ship> testShipsQueue = new LinkedList<>();
        testShipsQueue.add(bulkCarrier1);
        assertEquals("This is not correct", testShipsQueue, shipQueue.getShipQueue());
    }

    @Test
    public void testEqualsIfTwoQueueAreEqual() {
        shipQueue = new ShipQueue();
        shipQueue2 = new ShipQueue();

        shipQueue.add(containerShip1);
        shipQueue2.add(containerShip1);
        shipQueue.add(containerShip2);
        shipQueue2.add(containerShip2);

        assertTrue("This is not correct", shipQueue.equals(shipQueue2));
    }

    @Test
    public void testEqualsIfTwoQueueAreNotEqual() {
        shipQueue = new ShipQueue();
        shipQueue2 = new ShipQueue();

        shipQueue.add(containerShip1);
        shipQueue2.add(containerShip2);
        shipQueue.add(containerShip2);
        shipQueue2.add(containerShip1);

        assertFalse("This is not correct", shipQueue.equals(shipQueue2));
    }

    @Test
    public void testHashTwoEqualQueue() {
        shipQueue = new ShipQueue();
        shipQueue2 = new ShipQueue();

        shipQueue.add(containerShip1);
        shipQueue2.add(containerShip1);
        shipQueue.add(containerShip2);
        shipQueue2.add(containerShip2);


        assertTrue("This is not correct", shipQueue.hashCode() == shipQueue2.hashCode());
    }

    @Test
    public void testHashTwoNotEqualQueue() {
        shipQueue = new ShipQueue();
        shipQueue2 = new ShipQueue();

        shipQueue.add(containerShip1);
        shipQueue2.add(containerShip2);
        shipQueue.add(containerShip2);
        shipQueue2.add(containerShip1);


        assertFalse("This is not correct", shipQueue.hashCode() == shipQueue2.hashCode());
    }

    @Test
    public void testEncode() {
        shipQueue = new ShipQueue();

        shipQueue.add(containerShip1);
        shipQueue.add(containerShip2);

        assertEquals("This is not correct", "ShipQueue:2:2545345,2545349",shipQueue.encode());
    }

    @Test
    public void testFromString() throws BadEncodingException {
        shipQueue = new ShipQueue();

        shipQueue.add(containerShip1);
        shipQueue.add(containerShip2);

        String encoded = shipQueue.encode();
        assertEquals("This is not correct", true, ShipQueue.fromString(encoded).equals(shipQueue));
    }
}