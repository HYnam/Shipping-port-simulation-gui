package portsim.port;

import portsim.ship.Ship;
import portsim.util.BadEncodingException;
import portsim.util.Encodable;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

/** Queue of ships waiting to enter a Quay at the port. Ships are chosen based on their priority. */
public class ShipQueue implements Encodable {

    /** Queue of ships */
    private Queue<Ship> ships;

    /** Constructs a new ShipQueue with an initially empty queue of ships. */
    public ShipQueue(){
        this.ships = new LinkedList<>();
    }

    /** Gets the next ship to enter the port and removes it from the queue.
     *
     *  The same rules as described in peek() should be used for determining which ship to
     *  remove and return
     *
     *  @return next ship to dock
     *  */
    public Ship poll(){
        Ship removed = ships.remove();  // Remove the ship in the head of queue
        return removed;
    }

    /** Returns the next ship waiting to enter the port. The queue should not change.
     *
     * The rules for determining which ship in the queue should be returned next are as
     * follows:
     * <ul>
     *     <li>If a ship is carrying dangerous cargo, it should be returned. If more than one
     *     ship is carrying dangerous cargo return the one added to the queue first.</li>
     *
     *     <li>If a ship requires medical assistance, it should be returned. If more than one
     *     ship requires medical assistance, return the one added to the queue first.</li>
     *
     *     <li>If a ship is ready to be docked, it should be returned. If more than one ship is
     *     ready to be docked, return the one added to the queue first.</li>
     *
     *     <li>If there is a container ship in the queue, return the one added to the queue first.</li>
     *
     *     <li>If this point is reached and no ship has been returned, return the ship that was
     *     added to the queue first.</li>
     *
     *     <li>If there are no ships in the queue, return null.</li>
     * </ul>
     * @return next ship in queue
     * */
    public Ship peek(){

    }

    /** Adds the specified ship to the queue
     * @param ship to be added to queue
     * */
    public void add(Ship ship){
        this.ships.add(ship);
    }

    /** Returns a list containing all the ships currently stored in this ShipQueue.
     *
     * The order of the ships in the returned list should be the order in which the ships were
     * added to the queue
     *
     * Adding or removing elements from the returned list should not affect the original list.
     * @return ships in queue
     * */
    public List<Ship> getShipQueue(){
        List<Ship> results = new LinkedList<Ship>(ships);
        return results;
    }

    /** Returns true if and only if this ship queue is equal to the other given ship queue.
     * For two ship queue to be equal, they must have the same ships in the queue.
     * @param o other object to check equality
     * @return true if equal, false otherwise
     * */
    public boolean equals(Object o){
        if (o == null){
            return false;
        }
        if (!(o instanceof ShipQueue)){
            return false;
        }

        ShipQueue testEqual = (ShipQueue) o;

        if (this.ships == testEqual.ships){
            return true;
        }
        else {
            return false;
        }
    }

    /** Returns the hash code of this ship queue.
     *
     * Two ship queue's that are equal according to equals(Object) method should have
     * the same hash code
     * @return hash code of this ship queue
     * */
    public int hashCode(){
        return Objects.hash(ships);
    }

    /** Return the machine-readable string representation of this ShipQueue
     * The format of the string to return is
     * <pre>ShipQueue:numShipsInQueue:shipID,shipID,...</pre>
     * Where:
     * <ul>
     *     <li>numShipsInQueue is the total number of ships in the ship queue in the port</li>
     *
     *     <li>If present (numShipsInQueue > 0): shipID is each ship's ID in the
     *     aforementioned queue</li>
     * </ul>
     * For example:
     * <pre>ShipQueue:0:</pre>
     * or
     * <pre>ShipQueue:2:3456789,1234567</pre>
     * @return encoded string representation of this ShipQueue
     * */
    public String encode(){
        return String.format("%s:$d:%s",
                this.getClass().getSimpleName(),
                ships.size(),
                (ships.size() != 0 ? this.ships.getImoNumber() : ""));  // How to get ImoNumber of ships in queue
    }

    /** Creates a ship queue from a string encoding.
     *
     * The format of the string should match the encoded representation of a ship queue, as
     * described in encode().
     *
     * The encoded string is invalid if any of the following conditions are true:
     * <ul>
     *     <li>The number of colons (:) detected was more/fewer than expected.</li>
     *     <li>The string does not start with the literal string "ShipQueue"</li>
     *
     *     <li>The number of ships in the shipQueue is not an integer (i.e. cannot be parsed by
     *     Integer.parseInt(String)).</li>
     *
     *     <li>The number of ships in the shipQueue does not match the number specified.</li>
     *
     *     <li>The imoNumber of the ships in the shipQueue are not valid longs. (i.e. cannot
     *     be parsed by Long.parseLong(String)).</li>
     *
     *     <li>Any imoNumber read does not correspond to a valid ship in the simulation</li>
     * </ul>
     * @param string string containing the encoded ShipQueue
     * @return decoded ship queue instance
     * @throws BadEncodingException if the format of the given string is invalid according to the rules above
     * */
    public static ShipQueue fromString(String string) throws BadEncodingException{

    }
}
