package portsim.port;

import portsim.cargo.*;
import portsim.evaluators.StatisticsEvaluator;
import portsim.movement.CargoMovement;
import portsim.movement.Movement;
import portsim.movement.MovementDirection;
import portsim.movement.ShipMovement;
import portsim.ship.BulkCarrier;
import portsim.ship.ContainerShip;
import portsim.ship.NauticalFlag;
import portsim.ship.Ship;
import portsim.util.BadEncodingException;
import portsim.util.Encodable;
import portsim.util.NoSuchCargoException;
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
        this.shipQueue = new ShipQueue();
        this.priorityQueue = new PriorityQueue<>();
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
        this.priorityQueue = new PriorityQueue<>();
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
        if (movement.getTime() < getTime()){
            throw new IllegalArgumentException();
        }
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
        if (movement instanceof ShipMovement) {
            if (movement.getDirection() == MovementDirection.INBOUND) {
                this.shipQueue.add(((ShipMovement) movement).getShip());
            } else if (movement.getDirection() == MovementDirection.OUTBOUND){
                for (int i=0; i<this.storedCargo.size(); i++) {
                    if (((ShipMovement) movement).getShip().canLoad(this.storedCargo.get(i))) {
                        ((ShipMovement) movement).getShip().loadCargo(this.storedCargo.get(i));
                    }
                }
            }
        }
        if (movement instanceof CargoMovement) {
            if (movement.getDirection() == MovementDirection.INBOUND) {
                this.storedCargo.addAll(((CargoMovement) movement).getCargo());
            } else if (movement.getDirection() == MovementDirection.OUTBOUND){
                for (Cargo cargo : ((CargoMovement) movement).getCargo()) {
                    this.storedCargo.removeIf(p -> p.getId() == cargo.getId());
                }
            }
        }
        for (StatisticsEvaluator statisticsEvaluator : statisticsEvaluatorList) {
            statisticsEvaluator.onProcessMovement(movement);
        }
        /**
        ShipMovement directionShip = new ShipMovement(120, MovementDirection.INBOUND,
                new Ship(1258691, "Evergreen", "Australia", NauticalFlag.BRAVO) {
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
        });
        if (movement instanceof directionShip.){
            if (movement.getDirection() == MovementDirection.INBOUND){
                this.shipQueue.add(ship);
            } else if (movement.getDirection() == MovementDirection.OUTBOUND){

            }
        } else if (movement.getDirection() == new CargoMovement()){
            if (movement.getDirection() == MovementDirection.INBOUND){
                this.storedCargo.add();
            } else if (movement.getDirection() == MovementDirection.OUTBOUND){
                this.storedCargo.remove(Cargo.getCargoById(id));
            }
        }
         **/
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
    public void elapseOneMinute() {
        this.timeSimulationStarted++;
        if (this.timeSimulationStarted % 10 == 0) { // Check if time multiple of 10
            Ship ship = shipQueue.poll();
            for (Quay quay : quays) { // Bring a ship to an empty quay
                if (ship.canDock(quay)) {
                    quay.shipArrives(ship);
                    break;
                }
            }
        } else if (this.timeSimulationStarted % 5 == 0) {    // Check if time multiple of 5
            for (Quay quay : quays) {
                if (!quay.isEmpty()) {
                    if (quay.getShip() instanceof BulkCarrier) {
                        try {
                            storedCargo.add(((BulkCarrier) quay.getShip()).unloadCargo());
                        } catch (NoSuchCargoException e) {
                            e.printStackTrace();
                        }
                    } else if (quay.getShip() instanceof ContainerShip) {
                        try {
                            storedCargo.addAll(((ContainerShip) quay.getShip()).unloadCargo());
                        } catch (NoSuchCargoException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        for (Movement movement : priorityQueue) { // Process movement
            if (movement.getTime() == this.timeSimulationStarted) {
                processMovement(movement);
            }
        }
        for (StatisticsEvaluator statisticsEvaluator : statisticsEvaluatorList) { // Call elapseOneMinute() on all statisticsEvaluator
            statisticsEvaluator.elapseOneMinute();
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
        StringBuilder result = new StringBuilder();
        result.append(name).append(System.lineSeparator()); //Name
        result.append(timeSimulationStarted).append(System.lineSeparator()); // Time
        result.append(storedCargo.size()).append(System.lineSeparator()); // numCargo
        for (Cargo cargo : storedCargo) { // EncodedCargo
            result.append(cargo.encode()).append(System.lineSeparator());
        }
        int numOfShip = 0; // numShips
        for (Quay quay : quays) {
            if (!quay.isEmpty()) {
                numOfShip++;
            }
        }
        numOfShip += shipQueue.getShipQueue().size();
        result.append((char) numOfShip).append(System.lineSeparator());
        for (Quay quay : quays) { // EncodedShip
            if (!quay.isEmpty()) {
                result.append(quay.getShip().encode()).append(System.lineSeparator());
            }
        }
        for (Ship ship : shipQueue.getShipQueue()) {
            result.append(ship.encode()).append(System.lineSeparator());
        }
        result.append((char) quays.size()).append(System.lineSeparator()); // numQuays
        for (Quay quay : quays) { // EncodedQuay
            result.append(quay.encode()).append(System.lineSeparator());
        }
        StringBuilder txt = new StringBuilder("ShipQueue:");
        txt.append(shipQueue.getShipQueue().size()).append(":");
        for (Ship ship : shipQueue.getShipQueue()) {
            txt.append(ship.getImoNumber()).append(",");
        }
        result.append(txt.substring(0, txt.length() - 1)).append(System.lineSeparator());
        txt = new StringBuilder("StoredCargo:");
        txt.append(storedCargo.size()).append(":");
        for (Cargo cargo : storedCargo) {
            txt.append(cargo.getId()).append(",");
        }
        result.append(txt.substring(0, txt.length() - 1)).append(System.lineSeparator());
        result.append("Movements:").append((char) priorityQueue.size()).append(System.lineSeparator());
        for (Movement movement : priorityQueue) {
            result.append(movement.encode()).append(System.lineSeparator());
        }
        txt = new StringBuilder("Evaluators:");
        txt.append(statisticsEvaluatorList.size()).append(":");
        for (StatisticsEvaluator evaluator : statisticsEvaluatorList) {
            txt.append(evaluator.getClass().getSimpleName()).append(",");
        }
        result.append(txt.substring(0, txt.length() - 1)).append(System.lineSeparator());
        return result.toString();
    }

    /** Creates a port instance by reading various ship, quay, cargo, movement and evaluator
     * entities from the given reader.
     *
     * The provided file should be in the format:
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
     *    ShipQueue:NumShipsInQueue:shipID,shipId
     *    StoredCargo:numCargo:cargoID,cargoID
     *    Movements:numMovements
     *    EncodedMovement
     *    EncodedMovement...
     *    Evaluators:numEvaluators:EvaluatorSimpleName, EvaluatorSimpleName
     * </pre>
     * As specified by encode()
     * The encoded string is invalid if any of the following conditions are true:
     * <ul>
     *     <li>The time is not a valid long (i.e. cannot be parsed by
     *     Long.parseLong(String)).</li>
     *
     *     <li>The number of cargo is not an integer (i.e. cannot be parsed by
     *     Integer.parseInt(String)).</li>
     *
     *     <li>The number of cargo to be read in does not match the number specified above.
     *     (ie. too many / few encoded cargo following the number)</li>
     *
     *     <li>An encoded cargo line throws a BadEncodingException</li>
     *     <li>The number of ships is not an integer (i.e. cannot be parsed by
     *     Integer.parseInt(String))</li>
     *
     *     <li>The number of ship to be read in does not match the number specified above.
     *     (ie. too many / few encoded ships following the number)</li>
     *
     *     <li>An encoded ship line throws a BadEncodingException</li>
     *     <li>The number of quays is not an integer (i.e. cannot be parsed by
     *     Integer.parseInt(String)).</li>
     *
     *     <li>The number of quays to be read in does not match the number specified above.
     *     (ie. too many / few encoded quays following the number)</li>
     *
     *     <li>An encoded quay line throws a BadEncodingException</li>
     *     <li>The shipQueue does not follow the last encoded quay</li>
     *     <li>The number of ships in the shipQueue is not an integer (i.e. cannot be parsed by
     *     Integer.parseInt(String)).</li>
     *
     *     <li>The imoNumber of the ships in the shipQueue are not valid longs. (i.e. cannot
     *     be parsed by Long.parseLong(String)).</li>
     *
     *     <li>Any imoNumber read does not correspond to a valid ship in the simulation</li>
     *     <li>The storedCargo does not follow the encoded shipQueue</li>
     *     <li>The number of cargo in the storedCargo is not an integer (i.e. cannot be parsed
     *     by Integer.parseInt(String)).</li>
     *
     *     <li>The id of the cargo in the storedCargo are not valid Integers. (i.e. cannot be
     *     parsed by Integer.parseInt(String)).</li>
     *
     *     <li>Any cargo id read does not correspond to a valid cargo in the simulation</li>
     *     <li>The movements do not follow the encoded storedCargo</li>
     *     <li>The number of movements is not an integer (i.e. cannot be parsed by
     *     Integer.parseInt(String)).</li>
     *
     *     <li>The number of movements to be read in does not match the number specified
     *     above. (ie. too many / few encoded movements following the number)</li>
     *
     *     <li>An encoded movement line throws a BadEncodingException</li>
     *     <li>The evaluators do not follow the encoded movements</li>
     *     <li>The number of evaluators is not an integer (i.e. cannot be parsed by
     *     Integer.parseInt(String)).</li>
     *
     *     <li>The number of evaluators to be read in does not match the number specified
     *     above. (ie. too many / few encoded evaluators following the number)</li>
     *
     *     <li>An encoded evaluator name does not match any of the possible evaluator classes</li>
     *     <li>If any of the following lines are missing:</li>
     *          <ul>
     *              Name
     *              Time
     *              Number of Cargo
     *              Number of Ships
     *              Number of Quays
     *              ShipQueue
     *              StoredCargo
     *              Movements
     *              Evaluators
     *          </ul>
     * </ul>
     * @param reader reader from which to load all info
     * @return port created by reading from given reader
     * @throws IOException if an IOException is encountered when reading from the reader
     * @throws BadEncodingException if the reader reads a line that does not adhere to the rules
     * above indicating that the contents of the reader are invalid
     * */
    public static Port initialisePort(Reader reader) throws
            IOException, BadEncodingException{
        int data = reader.read();
        StringBuilder txt = new StringBuilder();
        while (data != -1) {
            txt.append((char) data);
            data = reader.read();
        }
        reader.close();

        String[] txtArr = txt.toString().split(System.lineSeparator());

        int counter = 0;
        String name = txtArr[counter]; // name
        counter++;
        Long time = Long.parseLong(txtArr[counter]); //time
        counter++;
        int numCargo = Integer.parseInt(txtArr[counter]); //numCargo
        counter++;
        List<Cargo> cargoList = new ArrayList<>(); // CargoList
        for (int i=counter; i<counter+numCargo;i++) {
            String[] arr = txtArr[i].split(":");
            if (arr[0].equals("BulkCargo")) {
                int id = Integer.parseInt(arr[1]);
                String destination = arr[2];
                String type = arr[3];
                int tonnage = Integer.parseInt(arr[4]);
                cargoList.add(new BulkCargo(id, destination, tonnage, BulkCargoType.valueOf(type)));
            } else if (arr[0].equals("Container")) {
                int id = Integer.parseInt(arr[1]);
                String destination = arr[2];
                String type = arr[3];
                cargoList.add(new Container(id, destination, ContainerType.valueOf(type)));
            }
        }
        counter += numCargo;
        int numShip = Integer.parseInt(txtArr[counter]); // numShip
        counter++;
        List<Ship> shipList = new ArrayList<>(); // shipList
        for (int i=counter; i<counter+numShip; i++) {
            String[] arr = txtArr[i].split(":");
            if (arr[0].equals("ContainerShip")) {
                int id = Integer.parseInt(arr[1]);
                String shipName = arr[2];
                String originFlag = arr[3];
                NauticalFlag flag = NauticalFlag.valueOf(arr[4]);
                int capacity = Integer.parseInt(arr[5]);
                int numOfCargo = Integer.parseInt(arr[6]);
            } else if (arr[0].equals("BulkCarrier")) {
                int id = Integer.parseInt(arr[1]);
                String shipName = arr[2];
                String originFlag = arr[3];
                NauticalFlag flag = NauticalFlag.valueOf(arr[4]);
                int capacity = Integer.parseInt(arr[5]);
            }
        }
        counter += numShip;
        int numQuays = Integer.parseInt(txtArr[counter]); // numQuays
        counter++;
        for (int i=counter; i<counter+numQuays; i++) { // QuaysList
            String[] arr = txtArr[i].split(":");
        }
        counter += numQuays;
        List<Ship> shipQueue = new ArrayList<>(); // shipQueue
        String[] sqArr = txtArr[counter].split(":"); //ship id
        counter++;
        String[] moveArr = txtArr[counter].split(":");
        int numMovement = Integer.parseInt(moveArr[1]); // movement
        for (int i=0; i<counter+numMovement; i++) { // movementList
            String[] arr = txtArr[i].split(":");
            if (arr[0].equals("ShipMovement")) {

            } else if (arr[0].equals("CargoMovement")) {

            }
        }
        counter += numMovement;
        String[] evaluatorsArr = txtArr[counter].split(":"); // evaluators
        int numEvaluators = Integer.parseInt(evaluatorsArr[1]);
        List<StatisticsEvaluator> evaluators = new ArrayList<>(); // evaluators list
        return null;
    }

}
