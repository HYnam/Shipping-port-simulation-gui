package portsim.ship;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import portsim.cargo.*;
import portsim.port.BulkQuay;
import portsim.port.ContainerQuay;
import portsim.util.NoSuchCargoException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ContainerShipTest {
    private ContainerShip ship1;
    private ContainerShip ship2;
    private ContainerQuay validQuay;
    private BulkQuay invalidQuay;
    private Container container1;
    private Container container2;
    private List<Container> compare = new ArrayList<>();


    @Before
    public void setUp() throws Exception {
        ship1 = new ContainerShip(1234567, "Polly", "USA",
                NauticalFlag.WHISKEY, 4);
        ship2 = new ContainerShip(2345678, "Apple", "Belgium",
                NauticalFlag.BRAVO, 3);
        validQuay = new ContainerQuay(1, 5);
        invalidQuay = new BulkQuay(2, 30);

        container1 = new Container(1, "USA", ContainerType.REEFER);
        container2 = new Container(2, "USA", ContainerType.TANKER);

        compare.add(container1);
        compare.add(container2);
        ship1.loadCargo(container1);
        ship1.loadCargo(container2);
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
    public void loadCargo_Test(){
        ship2.loadCargo(container1);
        assertTrue(ship2.getCargo().contains(container1));
    }
}