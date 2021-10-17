package portsim.evaluators;

import portsim.cargo.BulkCargo;
import portsim.cargo.BulkCargoType;
import portsim.cargo.Container;
import portsim.cargo.ContainerType;
import portsim.movement.CargoMovement;
import portsim.movement.Movement;
import portsim.movement.MovementDirection;
import portsim.movement.ShipMovement;

import java.util.HashMap;
import java.util.Map;

/** Collects data on what types of cargo are passing through the port. Gathers data on all
 * derivatives of the cargo class
 *
 * The data gathered is a count of how many times each type of cargo has entered the port. This
 * includes a count of how many times the port has received "BulkCargo" or "Container" class
 * cargo. As well as a count of how many times the port has seen each cargo subclass type
 * */
public class CargoDecompositionEvaluator extends StatisticsEvaluator {

    /** To store the number of distribution on cargo types entered the port */
    private Map<String, Integer> cargoDistribution;

    /** To store the number of distribution on BulkCargo type entered the port */
    private Map<BulkCargoType,Integer> bulkCargoDistribution;

    /** To store the number of distribution on Container type entered the port */
    private Map<ContainerType, Integer> containerDistribution;

    /** Constructs a new CargoDecompositionEvaluator */
    public CargoDecompositionEvaluator(){
        this.cargoDistribution = new HashMap<>();
        this.bulkCargoDistribution = new HashMap<>();
        this.containerDistribution = new HashMap<>();
    }

    /** Return the distribution of which cargo types that have entered the port
     * @return cargo distribution map
     * */
    public Map<String, Integer> getCargoDistribution(){
        return this.cargoDistribution;
    }

    /** Return the distribution of bulk cargo types that have entered the port
     * @return bulk cargo distribution map
     * */
    public Map<BulkCargoType, Integer> getBulkCargoDistribution(){
        return this.bulkCargoDistribution;
    }

    /** Return the distribution of container cargo types that have entered the port
     * @return container distribution map
     * */
    public Map<ContainerType, Integer> getContainerDistribution(){
        return this.containerDistribution;
    }

    /** Updates the internal distributions of cargo types using the given movement
     *
     * If the movement is not an {@code INBOUND} movement, this method return immediately
     * without taking any action
     *
     * If the movement is an {@code INBOUND} movement, do the following:
     * <ul>
     *     <li>If the movement is a ShipMovement, Retrieve the cargo from the ships and for
     *     each piece of cargo:</li>
     *
     *          <li>If the cargo class(Container/ BulkCargo) has been seen before (simple
     *          name exists as a key in the cargo map) -> increment that number</li>
     *
     *          <li>If the cargo class has not been seen before then add its class simple name
     *          as a key in the map with a corresponding value of 1</li>
     *
     *          <li>If the cargo type (Value of ContainerType/ BulkCargoType) for the given
     *          cargo class has been seen before (exists as a key in the map) increment
     *          that number</li>
     *
     *          <li>If the cargo type ((Value of ContainerType / BulkCargoType) for the given
     *          cargo class has not been seen before add as a key in the map with a
     *          corresponding value of 1</li>
     *      <li>If the movement is a CargoMovement, Retrieve the cargo from the movement.</li>
     *      For the cargo retrieved:
     *          <li>1. Complete steps 1-4 as given above for ShipMovement</li>
     * </ul>
     * @param movement movement to read
     * */
    public void onProcessMovement(Movement movement){
        if (movement.getDirection() == MovementDirection.INBOUND) {
            MovementDirection direction = MovementDirection.valueOf("INBOUND, OUTBOUND");
            if (movement.getDirection() == direction) {
                getCargoDistribution().merge("Container", 1, Integer::sum);
                getCargoDistribution().merge("BulkCargo", 1, Integer::sum);

                getBulkCargoDistribution().merge(BulkCargoType.valueOf("GRAIN, MINERALS, COAL, OIL, OTHER")
                        , 1, Integer::sum);
                getContainerDistribution().merge(ContainerType.valueOf("OTHER, TANKER, REEFER, OPEN_TOP, STANDARD")
                        , 1, Integer::sum);
            }
        }
    }
}
