package portsim.ship;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import portsim.cargo.*;
import portsim.port.Quay;

import static org.junit.Assert.*;

public class ShipTest {

    private Ship dummyShip1;
    private Ship dummyShip2;

    class DummyShip extends Ship {

        public DummyShip(long imoNumber, String name, String originFlag,
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

        this.dummyShip1 = new DummyShip(2345678, "Apple", "Italy", NauticalFlag.WHISKEY);
        this.dummyShip2 = new DummyShip(2545679, "Legion", "France", NauticalFlag.BRAVO);
    }

    @After
    public void tearDown() throws Exception {
    }

    //
    //
    //
    //
    //
    //

    @Test
    //
    public void constructorThrowsExceptionInvalidLengthIMO() {
        try {
            new DummyShip(1234, "Legion", "France", NauticalFlag.BRAVO);
        } catch (IllegalArgumentException e) {}
    }

    @Test
    //
    public void getName() {
        assertEquals("Legion", dummyShip2.getName());
    }
}