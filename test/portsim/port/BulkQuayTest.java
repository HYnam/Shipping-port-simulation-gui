package portsim.port;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import portsim.ship.ContainerShip;
import portsim.ship.NauticalFlag;


import static org.junit.Assert.*;

public class BulkQuayTest {

    BulkQuay quay1;
    BulkQuay quay2;
    String containerShip;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Rule
    public Timeout timeout = Timeout.seconds(1);
}