package portsim.port;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import portsim.cargo.*;
import portsim.ship.BulkCarrier;
import portsim.ship.ContainerShip;
import portsim.ship.NauticalFlag;
import portsim.ship.Ship;

import java.util.*;

import static org.junit.Assert.*;


public class PortTest {

    Port port1;
    Port port2;

    List<Quay> quays1;
    List<Quay> quays2;

    Quay quay1;
    Quay quay2;
    Quay quay3;

    List<Cargo> sCargo1;
    List<Cargo> sCargo2;

    Ship ship1;
    Ship ship2;
    Ship ship3;
    Ship ship4;

    Cargo cargo1;
    Cargo cargo2;
    Cargo cargo3;
    Cargo cargo4;
    Cargo cargo5;

    @Before
    public void setUp() throws Exception {
        ship1 = new BulkCarrier(3456789, "Glorious", "Switzerland",
                NauticalFlag.HOTEL, 120);
        ship2 = new BulkCarrier(4567890, "Panda", "Japan",
                NauticalFlag.NOVEMBER, 10);
        ship3 = new ContainerShip(1234567, "Polly", "USA",
                NauticalFlag.WHISKEY, 4);
        ship4 = new ContainerShip(9876543, "Samantha", "Australia",
                NauticalFlag.BRAVO, 3);
        cargo1 = new Container(1,"Australia", ContainerType.OPEN_TOP);
        cargo2 = new Container(2,"New Zealand",ContainerType.OTHER);
        cargo3 = new BulkCargo(3,"Japan",150, BulkCargoType.OIL);
        cargo4 = new BulkCargo(4,"Finland",200,BulkCargoType.GRAIN);
        cargo5 = new BulkCargo(5,"ABC",10,BulkCargoType.OTHER);

        ship1.loadCargo(cargo5);

        quay1 = new BulkQuay(1,200);
        quay2 = new BulkQuay(2,250);
        quay3 = new ContainerQuay(3,100);

        quays1 = new ArrayList<>();
        quays2 = new ArrayList<>();

        quays1.add(quay1);

        quays2.add(quay1);
        quays2.add(quay3);

        sCargo1 = new ArrayList<>();
        sCargo2 = new ArrayList<>();
        sCargo1.add(cargo1);
        sCargo2.add(cargo1);
        sCargo2.add(cargo2);
        sCargo2.add(cargo3);

        port1 = new Port("Danie");
        port2 = new Port("Ahmad");

        port1.addQuay(quay1);

        port2.addQuay(quay1);
        port2.addQuay(quay3);
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

    @Test
    //
    public void getName() {
        assertEquals("Danie",port1.getName());
        assertEquals("Ahmad",port2.getName());
    }
}