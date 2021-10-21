package portsim.evaluators;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import portsim.movement.MovementDirection;
import portsim.movement.ShipMovement;
import portsim.ship.ContainerShip;
import portsim.ship.NauticalFlag;
import static org.junit.Assert.*;

public class ShipThroughputEvaluatorTest {

    private ContainerShip containerShip;
    private ShipThroughputEvaluator shipThroughputEvaluator;
    private ShipMovement shipMovement;

    @Before
    public void setUp() throws Exception {
        containerShip = new ContainerShip(2545345, "Ship3", "France", NauticalFlag.HOTEL, 20);
        shipThroughputEvaluator = new ShipThroughputEvaluator();
        shipMovement = new ShipMovement(100, MovementDirection.OUTBOUND, containerShip);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetThroughputPreHour1() {
        assertEquals("This is not correct", 0, shipThroughputEvaluator.getThroughputPerHour());
    }

    @Test
    public void testOnProcessMovement() {
        shipThroughputEvaluator.onProcessMovement(shipMovement);
        assertEquals("This is not correct", 1,  shipThroughputEvaluator.getThroughputPerHour());
    }

    @Test
    public void testElapseOneMinute() throws InterruptedException {
        shipThroughputEvaluator.elapseOneMinute();
        shipThroughputEvaluator.elapseOneMinute();
        assertEquals("This is not correct", 2, shipThroughputEvaluator.getTime());
    }
}