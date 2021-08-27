package portsim.cargo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.*;


public class BulkCargoTest {

    BulkCargo bulkCargo1;
    BulkCargo bulkCargo2;
    BulkCargo bulkCargo3;

    @Before
    public void setup(){
        bulkCargo1 = new BulkCargo(1, "Hong Kong", 1000, );
    }

}