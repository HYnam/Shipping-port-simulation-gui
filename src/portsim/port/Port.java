package portsim.port;

import portsim.cargo.Cargo;
import portsim.evaluators.StatisticsEvaluator;
import portsim.movement.CargoMovement;
import portsim.movement.Movement;
import portsim.movement.MovementDirection;
import portsim.movement.ShipMovement;
import portsim.util.BadEncodingException;
import portsim.util.Encodable;
import portsim.util.Tickable;

import java.io.IOException;
import java.io.Reader;
import java.util.*;

/**
 * A place where ships can come and dock with Quays to load / unload their
 * cargo.
 * <p>
 * Ships can enter a port through its queue. Cargo is stored within the port at warehouses.
 *
 * @ass1_partial
 */
public class Port implements Tickable, Encodable {

    /**
     * The name of this port used for identification
     */
    private String name;
    /**
     * The quays associated with this port
     */
    private List<Quay> quays;
    /**
     * The cargo currently stored at the port at warehouses. Cargo unloaded from trucks / ships
     */
    private List<Cargo> storedCargo;

    /** The time since the simulation was started */
    private long timeSimulationStarted;

    /** Queue of ships waiting to be docked at this port */
    private ShipQueue shipQueue;

    /** To store movements ordered by the time of the movement */
    private PriorityQueue<Movement> priorityQueue;

    /** Store the statistics Evaluator of the port's operation */
    private List<StatisticsEvaluator> statisticsEvaluatorList;

    /**
     * Creates a new port with the given name.
     * <p>
     * The time since the simulation was started should be initialised as 0.
     * <p>
     * The list of quays in the port, stored cargo (warehouses) and statistics evaluators should be
     * initialised as empty lists.
     * <p>
     * An empty ShipQueue should be initialised, and a PriorityQueue should be initialised
     * to store movements ordered by the time of the movement (see {@link Movement#getTime()}).
     *
     * @param name name of the port
     * @ass1_partial
     */
    public Port(String name) {
        this.name = name;

        this.quays = new ArrayList<Quay>();
        this.storedCargo = new ArrayList<Cargo>();
        this.timeSimulationStarted = 0;
        this.shipQueue = null;    // Not sure
        this.priorityQueue = new PriorityQueue<>();  // Not sure
        this.statisticsEvaluatorList = new ArrayList<StatisticsEvaluator>();
    }

    /** Creates a new port with the given name, time elapsed, ship queue, quays and stored
     * cargo
     *
     * The list of statistics evaluators should be initialised as an empty list.
     *
     * A PriorityQueue should be initialised to store movements ordered by the time of the
     * movement
     *
     * @param name name of the port
     * @param time number of minutes since simulation started
     * @param shipQueue ships waiting to enter the port
     * @param quays the port's quays
     * @param storedCargo the cargo stored at the port
     *
     * @throws IllegalArgumentException if time < 0
     * */
    public Port(String name, long time, ShipQueue shipQueue,
                List<Quay> quays, List<Cargo> storedCargo)
        throws IllegalArgumentException{
        if (time < 0){
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.timeSimulationStarted = time;
        this.shipQueue = shipQueue;
        this.quays = quays;
        this.storedCargo = storedCargo;

        this.statisticsEvaluatorList = new ArrayList<StatisticsEvaluator>();
        this.priorityQueue = new PriorityQueue<>();  // Not sure
    }

    /** Adds a movement to the PriorityQueue of movements.
     *
     * If the given movement's action time is less than the current number of minutes
     * elapsed than an IllegalArgumentException should be thrown.
     *
     * @param movement movement to add
     * @throws IllegalArgumentException
     * */
    public void addMovement(Movement movement) throws
            IllegalArgumentException{
        this.priorityQueue.add(movement);
        // How to throw exception!!
    }

    /** Processes a movement.
     * The action taken depends on the type of movement to be processed.
     * If the movement is a ShipMovement:
     * <ul>
     *     <li>If the movement direction is INBOUND then the ship should be added to the ship
     *     queue</li>
     *
     *     <li>If the movement direction is OUTBOUND then any cargo stored in the port whose
     *     destination is the ship's origin port should be added to the ship according to
     *     Ship.canLoad(Cargo). Next, the ship should be removed from the quay it is
     *     currently docked in (if any).</li>
     * </ul>
     * If the movement is a CargoMovement:
     * <ul>
     *     <li>If the movement direction is INBOUND then all of the cargo that is being moved
     *     should be added to the port's stored cargo.</li>
     *
     *     <li>If the movement direction is OUTBOUND then all cargo with the given IDs should
     *     be removed from the port's stored cargo.</li>
     * </ul>
     * Finally, the movement should be forwarded onto each statistics evaluator stored by the
     * port by calling StatisticsEvaluator.onProcessMovement(Movement).
     *
     * @param movement movement to execute*/
    public void processMovement(Movement movement){
        if (movement == ShipMovement){
            if (movement.getDirection() == MovementDirection.INBOUND){
                this.shipQueue.add(ship);
            } else if (movement.getDirection() == MovementDirection.OUTBOUND){

            }
        } else if (movement == CargoMovement){
            if (movement.getDirection() == MovementDirection.INBOUND){
                this.storedCargo.add();
            } else if (movement.getDirection() == MovementDirection.OUTBOUND){
                this.storedCargo.remove(Cargo.getCargoById(id));
            }
        }
    }

    /** Adds the given statistics evaluator to the port's list of evaluators.
     * If the port already has an evaluator of that type, no action should be taken.
     * @param eval statistics evaluator to add to the port
     * */
    public void addStatisticsEvaluator(StatisticsEvaluator eval){
        if (this.statisticsEvaluatorList.contains(eval)){
            // No action should be taken
        } else {
            this.statisticsEvaluatorList.add(eval); // Add eval to list
        }
    }

    /**
     * Returns the name of this port.
     *
     * @return port's name
     * @ass1
     */
    public String getName() {
        return name;
    }

    /** Returns the time since simulation started
     * @return time in minutes
     * */
    public long getTime(){
        return timeSimulationStarted;
    }

    /**
     * Returns a list of all quays associated with this port.
     * <p>
     * Adding or removing elements from the returned list should not affect the original list.
     * <p>
     * The order in which quays appear in this list should be the same as
     * the order in which they were added by calling {@link #addQuay(Quay)}.
     *
     * @return all quays
     * @ass1
     */
    public List<Quay> getQuays() {
        return new ArrayList<>(this.quays);
    }

    /**
     * Returns the cargo stored in warehouses at this port.
     * <p>
     * Adding or removing elements from the returned list should not affect the original list.
     *
     * @return port cargo
     * @ass1
     */
    public List<Cargo> getCargo() {
        return new ArrayList<>(this.storedCargo);
    }

    /** Returns the queue of ships waiting to be docked at this port.
     * @return port's queue of ships
     * */
    public ShipQueue getShipQueue(){
        return shipQueue;
    }

    public PriorityQueue<Movement> getMovements(){
        return priorityQueue;
    }

    /** Returns the list of evaluators at the port.
     * Adding or removing elements from the returned list should not affect the original list.
     * @return the ports evaluators
     * */
    public List<StatisticsEvaluator> getEvaluators(){
        return new ArrayList<>(this.statisticsEvaluatorList);
    }

    /**
     * Adds a quay to the ports control.
     *
     * @param quay the quay to add
     * @ass1
     */
    public void addQuay(Quay quay) {
        this.quays.add(quay);
    }

    /** Advances the simulation by one minute.
     *
     * On each call to elapseOneMinute(), the following actions should be completed by the
     * port in order:
     * <ul>
     *     <li>1. Advance the simulation time by 1</li>
     *
     *     <li>2. If the time is a multiple of 10, attempt to bring a ship from the ship queue to any
     *     empty quay that matches the requirements from Ship.canDock(Quay). The
     *     ship should only be docked to one quay.</li>
     *
     *     <li>3. If the time is a multiple of 5, all quays must unload the cargo from ships docked
     *     (if any) and add it to warehouses at the port (the Port's list of stored cargo)</li>
     *
     *     <li>4. All movements stored in the queue whose action time is equal to the current
     *     time should be processed by processMovement(Movement)</li>
     *
     *     <li>5. Call StatisticsEvaluator.elapseOneMinute() on all statistics evaluators</li>
     * </ul>*/
    public void elapseOneMinute(){
        this.timeSimulationStarted ++;
        if (this.timeSimulationStarted % 10 == 0) { // Check if time multiple of 10

        } else if (this.timeSimulationStarted % 5 == 0){    // Check if time multiple of 5

        }
    }

    /** Returns the machine-readable string representation of this Port.
     * The format of the string to return is
     * <pre>
     *    Name
     *    Time
     *    numCargo
     *    EncodedCargo
     *    EncodedCargo...
     *    numShips
     *    EncodedShip
     *    EncodedShip...
     *    numQuays
     *    EncodedQuay
     *    EncodedQuay...
     *    ShipQueue:numShipsInQueue:shipID,shipID,...
     *    StoredCargo:numCargo:cargoID,cargoID,...
     *    Movements:numMovements
     *    EncodedMovement
     *    EncodedMovement...
     *    Evaluators:numEvaluators:EvaluatorSimpleName,EvaluatorSimpleName,...
     * </pre>
     * Where:
     * <ul>
     *     <li>Name is the name of the Port</li>
     *     <li>Time is the time elapsed since the simulation started</li>
     *     <li>numCargo is the total number of cargo in the simulation</li>
     *
     *     <li>If present (numCargo > 0): EncodedCargo is the encoded representation of each
     *     individual cargo in the simulation</li>
     *
     *     <li>numShips is the total number of ships in the simulation</li>
     *
     *     <li>If present (numShips > 0): EncodedShip is the encoded representation of each
     *     individual ship encoding in the simulation</li>
     *
     *     <li>numQuays is the total number of quays in the Port</li>
     *
     *     <li>If present (numQuays > 0): EncodedQuay is the encoded representation of each
     *     individual quay in the simulation</li>
     *
     *     <li>numShipsInQueue is the total number of ships in the ship queue in the port</li>
     *
     *     <li>If present (numShipsInQueue > 0): shipID is each ship's ID in the
     *     aforementioned queue</li>
     *
     *     <li>numCargo is the total amount of stored cargo in the Port</li>
     *
     *     <li>If present (numCargo > 0): cargoID is each cargo's ID in the stored cargo list of
     *     Port</li>
     *
     *     <li>numMovements is the number of movements in the list of movements in Port</li>
     *
     *     <li>If present (numMovements > 0): EncodedMovement is the encoded
     *     representation of each individual Movement in the aforementioned list</li>
     *
     *     <li>numEvaluators is the number of statistics evaluators in the Port evaluators list</li>
     *
     *     <li>If present (numEvaluators > 0): EvaluatorSimpleName is the name given by
     *     Class.getSimpleName() for each evaluator in the aforementioned list
     *     separated by a comma</li>
     *
     *     <li>Each line is separated by a System.lineSeparator()</li>
     * </ul>
     * For example the minimum/ default encoding would be:
     * <pre>
     *     PortName
     *     0
     *     0
     *     0
     *     0
     *     ShipQueue:0:
     *     StoredCargo:0:
     *     Movements:0
     *     Evaluators:0:
     * </pre>
     * @return encoded string representation of this Port
     * */
    public String encode(){
        return getName() + System.lineSeparator() + getTime() + System.lineSeparator()
                + getCargo().size() + System.lineSeparator() ;
    }

    public static Port initialisePort(Reader reader) throws
            IOException, BadEncodingException{

    }

}
