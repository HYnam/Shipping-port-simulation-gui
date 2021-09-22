package portsim.cargo;

import portsim.util.BadEncodingException;
import portsim.util.NoSuchCargoException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Denotes a cargo whose function is to be transported via a Ship or land
 * transport.
 * <p>
 * Cargo is kept track of via its ID.
 *
 * @ass1_partial
 */
public abstract class Cargo {
    /**
     * The ID of the cargo instance
     */
    private int id;

    /**
     * Destination for this cargo
     */
    private String destination;

    /**
     * Database of all cargo currently active in the simulation
     */
    private static Map<Integer, Cargo> cargoRegistry = new HashMap<>();

    /**
     * Creates a new Cargo with the given ID and destination port.
     * <p>
     * When a new piece of cargo is created, it should be added to the cargo registry.
     * @param id          cargo ID
     * @param destination destination port
     * @throws IllegalArgumentException if a cargo already exists with the
     *                                  given ID or ID &lt; 0
     * @ass1_partial
     */
    public Cargo(int id, String destination) throws IllegalArgumentException {
        if (id < 0 && cargoRegistry.keySet().equals(this.id)) {
            throw new IllegalArgumentException("Cargo ID must be greater than"
                + " or equal to 0: " + id);
        }
        this.id = id;
        this.destination = destination;
    }

    /**
     * Retrieve the ID of this piece of cargo.
     *
     * @return the cargo's ID
     * @ass1
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieve the destination of this piece of cargo.
     *
     * @return the cargo's destination
     * @ass1
     */
    public String getDestination() {
        return destination;
    }

    /** Returns the global registry of all pieces of cargo,
     * as a mapping from cargo IDs to Cargo instances.
     *
     * Adding or removing elements from the returned map should not affect the original map.
     *
     * @return cargo registry
     * */
    public static Map<Integer, Cargo> getCargoRegistry(){

    }

    /** Check if a cargo exists in the simulation using its ID
     * @param id unique key to identify cargo
     * @return true if there is a cargo stored in the registry with key id,
     * false otherwise
     * */
    public static boolean cargoExists(int id){
        return cargoRegistry.containsKey(id);
    }

    /** Return the cargo specified by the given ID
     * @param id unique key to identify cargo
     * @return cargo specified by the id
     * @throw NoSuchCargoException if the cargo does not exist in the registry
     * */
    public static Cargo getCargoById(int id) throws NoSuchCargoException{
        if (cargoExists(id) == false){
            throw new NoSuchCargoException();
        }
        else {
            return cargoRegistry.get(id);
        }
    }

    /** Return true if and only if this cargo is equal to the other given cargo
     * For two cargo to be equal, they must have the same ID and destination
     *
     * @param o other object to check equality
     * @return true if equal, false otherwise
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cargo cargo = (Cargo) o;
        return id == cargo.id && Objects.equals(destination, cargo.destination);
    }

    /** Return the hash code of this cargo
     *
     * Two cargo are equal according to equals(Object) method
     * should have the same hash code
     *
     * @return hash code of this cargo*/
    @Override
    public int hashCode() {
        return Objects.hash(id, destination);
    }

    /**
     * Returns the human-readable string representation of this cargo.
     * <p>
     * The format of the string to return is
     * <pre>CargoClass id to destination</pre>
     * Where:
     * <ul>
     *   <li>{@code CargoClass} is the cargo class name</li>
     *   <li>{@code id} is the id of this cargo </li>
     *   <li>{@code destination} is the destination of the cargo </li>
     * </ul>
     * <p>
     * For example: <pre>Container 55 to New Zealand</pre>
     *
     * @return string representation of this Cargo
     * @ass1
     */
    @Override
    public String toString() {
        return String.format("%s %d to %s",
            this.getClass().getSimpleName(),
            this.id,
            this.destination);
    }

    /** Return the machine-readable string representation of this Cargo
     * The format of the string to return is
     * <pre>CargoClass:id:destination</pre>
     * Where:
     * <ul>
     *      <li>{@code CargoClass} is the Cargo class name</li>
     *      <li>{@code id} is the id of this cargo</li>
     *      <li>{@code destination} is the destination of this cargo</li>
     * </ul>
     * For example: <pre>Container:3:Australia</pre>
     * OR
     * <pre>BulkCargo:2:France</pre>
     * </p>
     *
     * @return encoded string representation of this Cargo*/
    public String encode(){
        return String.format("%s:%d:%s",
                this.getClass().getSimpleName(),
                this.id,
                this.destination);
    }

    public static Cargo fromString(String string) throws BadEncodingException{

    }

    /**
     * Resets the global cargo registry.
     * This utility method is for the testing suite.
     *
     * @given
     */
    public static void resetCargoRegistry() {
        Cargo.cargoRegistry = new HashMap<>();
    }
}
