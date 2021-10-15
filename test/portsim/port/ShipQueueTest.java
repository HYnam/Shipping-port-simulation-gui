package portsim.port;

import org.junit.After;
import org.junit.Before;

import portsim.cargo.Cargo;
import portsim.ship.NauticalFlag;
import portsim.ship.Ship;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.*;

public class ShipQueueTest {

    private Queue<Ship> ships;

    private Ship shipping1;
    private Ship shipping2;
    private Ship shipping3;

    class ShippingShips extends Ship {
        public ShippingShips(long imoNumber, String name, String originFlag,
                             NauticalFlag flag) {
            super(imoNumber, name, originFlag, flag);
        }

        @Override
        public boolean canDock(Quay quay) {
            return false;
        }

        @Override
        public boolean canLoad(Cargo cargo) {
            return false;
        }

        @Override
        public void loadCargo(Cargo cargo) {

        }
    }

    @Before
    public void setUp() throws Exception {
        this.shipping1 = new ShippingShips(2345678, "Apple", "Italy", NauticalFlag.WHISKEY);
        this.shipping2 = new ShippingShips(2545679, "Legion", "France", NauticalFlag.BRAVO);
        this.shipping3 = new ShippingShips(1234567, "Peter", "UK", NauticalFlag.HOTEL);

        ships.add(shipping1);
        ships.add(shipping2);
        ships.add(shipping3);
    }

    @After
    public void tearDown() throws Exception {
    }

}