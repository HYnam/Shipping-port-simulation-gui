package portsim.movement;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import portsim.ship.BulkCarrier;
import portsim.ship.ContainerShip;
import portsim.ship.NauticalFlag;
import portsim.ship.Ship;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ShipMovementTest {

    ShipMovement movement1;
    ShipMovement movement2;
    ShipMovement movement3;
    Ship ship1;
    Ship ship2;
    Ship ship3;

    @Before
    public void setUp() throws Exception {
        ship1 = new ContainerShip(1234567, "Polly", "USA",
                NauticalFlag.WHISKEY, 4);
        ship2 = new BulkCarrier(4567890, "Panda", "Japan",
                NauticalFlag.NOVEMBER, 10);
        ship3 = new BulkCarrier(5678901, "Switch", "Poland",
                NauticalFlag.BRAVO, 15);

        movement1 = new ShipMovement(0,MovementDirection.INBOUND,ship1);
        movement2 = new ShipMovement(0,MovementDirection.OUTBOUND,ship2);
        movement3 = new ShipMovement(50,MovementDirection.INBOUND,ship3);
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
    //
    //

    @Test
    //
    public void getShip() {
        assertEquals(ship1,movement1.getShip());
        assertEquals(ship2,movement2.getShip());
        assertEquals(ship3,movement3.getShip());
    }
}