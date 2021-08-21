package portsim.cargo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.*;

public class ContainerTest {

    Container container1;
    Container container2;
    Container container3;

    @Before
    public void setUp() throws Exception {
        container1 = new Container(1,"Australia",ContainerType.OTHER);
        container2 = new Container(2,"Australia",ContainerType.OPEN_TOP);
        container3 = new Container(3,"New Zealand",ContainerType.OTHER);
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

    @Test
    //
    public void toString_Test() {
        assertEquals("Container 1 to Australia [OTHER]",
                container1.toString());
        assertEquals("Container 2 to Australia [OPEN_TOP]",
                container2.toString());

    }
}