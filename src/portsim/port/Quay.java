package portsim.port;

import portsim.movement.CargoMovement;
import portsim.ship.Ship;
import portsim.util.BadEncodingException;
import portsim.util.Encodable;

import java.util.Objects;


/**
 * Quay is a platform lying alongside or projecting into the water where
 * ships are moored for loading or unloading.
 *
 * @ass1_partial
 */
public abstract class Quay implements Encodable {
    /**
     * The ID of the quay
     */
    private int id;

    /**
     * The ship currently in the Quay
     */
    private Ship ship;

    /**
     * Creates a new Quay with the given ID, with no ship docked at the quay.
     *
     * @param id quay ID
     * @throws IllegalArgumentException if ID &lt; 0
     * @ass1
     */
    public Quay(int id) throws IllegalArgumentException {
        if (id < 0) {
            throw new IllegalArgumentException("Quay ID must be greater than"
                + " or equal to 0: " + id);
        }
        this.id = id;
        this.ship = null;
    }

    /**
     * Get the id of this quay
     *
     * @return quay id
     * @ass1
     */
    public int getId() {
        return id;
    }

    /**
     * Docks the given ship at the Quay so that the quay becomes occupied.
     *
     * @param ship ship to dock to the quay
     * @ass1
     */
    public void shipArrives(Ship ship) {
        this.ship = ship;
    }

    /**
     * Removes the current ship docked at the quay.
     * The current ship should be set to {@code null}.
     *
     * @return the current ship or null if quay is empty.
     * @ass1
     */
    public Ship shipDeparts() {
        Ship current = this.ship;
        this.ship = null;
        return current;
    }

    /**
     * Returns whether a ship is currently docked at this quay.
     *
     * @return true if there is no ship docked else false
     * @ass1
     */
    public boolean isEmpty() {
        return this.ship == null;
    }

    /**
     * Returns the ship currently docked at the quay.
     *
     * @return ship at quay or null if no ship is docked
     * @ass1
     */
    public Ship getShip() {
        return ship;
    }

    /** Returns true if and only if this Quay is equal to the other given Quay.
     *
     * For two Quays to be equal, they must have the same ID and ship docked status (must
     * either both be empty or both be occupied).
     *
     * @param o other object to check equality
     * @return true if equal, false otherwise
     * */
    public boolean equals(Object o){
        if (o == null){
            return false;
        }
        if (!(o instanceof Quay)){
            return false;
        }

        Quay testEqual = (Quay) o;

        if (this.getId() == testEqual.getId()
                && this.isEmpty() == testEqual.isEmpty()){
            return true;
        }
        else {
            return false;
        }
    }

    /** Returns the hash code of this quay.
     *
     * Two quays that are equal according to equals(Object) method should have the same
     * hash code.
     *
     * @return hash code of this quay
     * */
    public int hashCode(){
        return Objects.hash(getId(), isEmpty());
    }

    /**
     * Returns the human-readable string representation of this quay.
     * <p>
     * The format of the string to return is
     * <pre>QuayClass id [Ship: imoNumber]</pre>
     * Where:
     * <ul>
     * <li>{@code id} is the ID of this quay</li>
     * <li>{@code imoNumber} is the IMO number of the ship docked at this
     * quay, or {@code None} if the quay is unoccupied.</li>
     * </ul>
     * <p>
     * For example: <pre>BulkQuay 1 [Ship: 2313212]</pre> or
     * <pre>ContainerQuay 3 [Ship: None]</pre>
     *
     * @return string representation of this quay
     * @ass1
     */
    @Override
    public String toString() {
        return String.format("%s %d [Ship: %s]",
            this.getClass().getSimpleName(),
            this.id,
            (this.ship != null ? this.ship.getImoNumber() : "None"));
    }

    /** Returns the machine-readable string representation of this Quay.
     *  The format of the string to return is
     *  <pre>QuayClass:id:imoNumber</pre>
     *
     *  Where:
     *  <ul>
     *      <li>{@code QuayClass} is the Quay class name</li>
     *      <li>{@code id} is the ID of this quay</li>
     *      <li>{@code imoNumber} is the IMO number of the ship docked at this quay, or None if the
     *      quay is unoccupied</li>
     *  </ul>
     *  For example:
     *  <pre>BulkQuay:3:1258691</pre>
     *  or
     *  <pre>ContainerQuay:3:None</pre>
     *
     *  @return encoded string representation of this quay
     *  */
    public String encode(){
        return String.format("%s:%d:%s",
                this.getClass().getSimpleName(),
                this.id,
                (this.ship != null ? this.ship.getImoNumber() : "None"));
    }

    /** Reads a Quay from its encoded representation in the given string.
     *
     * The format of the string should match the encoded representation of a Quay, as
     * described in encode()
     *
     * The encoded string is invalid if any of the following conditions are true:
     * <ul>
     *     <li>The number of colons (:) detected was more/fewer than expected.</li>
     *     <li>The quay id is not a long (i.e. cannot be parsed by {@code Long.parseLong(String)}).</li>
     *     <li>The quay id is less than one (1).</li>
     *     <li>The quay type specified is not one of BulkQuay or ContainerQuay</li>
     *
     *     <li>If the encoded ship is not None then the ship must exist and the imoNumber
     *     specified must be an integer (i.e. can be parsed by
     *     {@code Integer.parseInt(String)}</li>
     *
     *     <li>The quay capacity is not an integer (i.e. cannot be parsed by
     *     {@code Integer.parseInt(String)}</li>
     * </ul>
     *
     * @param string string containing the encoded Quay
     * @return decoded Quay instance
     * @throws BadEncodingException if the format of the given string is invalid according to the
     * rules above
     * */
    public static Quay fromString(String string) throws BadEncodingException{
        String[] listOfStrings = string.split(":"); // Split wherever we see ":"
        if (listOfStrings.length != 3) {
            throw new BadEncodingException();
        }
        try {
            Long.parseLong(listOfStrings[1]);
        } catch (Exception i){
            throw new BadEncodingException();
        }
        if (Long.parseLong(listOfStrings[1]) < 0){
            throw new BadEncodingException();
        }
        if (!listOfStrings[0].equals("BulkQuay") || !listOfStrings[0].equals("ContainerQuay")){
            throw new BadEncodingException();
        }
        if (listOfStrings[2] != null)
            try{
                Integer.parseInt(listOfStrings[2]);
            } catch (Exception e){
                throw new BadEncodingException();
            }
        try{
            Integer.parseInt();
        } catch (Exception j){
            throw new BadEncodingException();
        }
        return Quay.fromString(listOfStrings[0]);
    }
}
