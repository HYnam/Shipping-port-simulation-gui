package portsim.cargo;

import java.util.Objects;

/**
 * Bulk cargo is commodity cargo that is transported unpacked in large quantities.
 *
 * @ass1_partial
 */
public class BulkCargo extends Cargo {
    /**
     * The weight in tonnes of the bulk cargo
     */
    private int tonnage;

    /**
     * The type of bulk cargo
     */
    private BulkCargoType type;

    /**
     * Creates a new Bulk Cargo with the given ID, destination, tonnage and type.
     *
     * @param id          cargo ID
     * @param destination destination port
     * @param tonnage     the weight of the cargo
     * @param type        the type of cargo
     * @throws IllegalArgumentException if a cargo already exists with the
     *                                  given ID or ID &lt; 0 or tonnage &lt; 0
     * @ass1
     */
    public BulkCargo(int id, String destination, int tonnage,
                     BulkCargoType type) throws IllegalArgumentException {
        super(id, destination);
        if (tonnage < 0) {
            throw new IllegalArgumentException("The cargo tonnage "
                + "must be greater than or equal to 0: " + tonnage);
        }
        this.tonnage = tonnage;
        this.type = type;
    }

    /**
     * Returns the weight in tonnes of this bulk cargo.
     *
     * @return cargo tonnage
     * @ass1
     */
    public int getTonnage() {
        return tonnage;
    }

    /**
     * Returns the BulkCargoType of this bulk cargo.
     *
     * @return cargo type
     * @ass1
     */
    public BulkCargoType getType() {
        return type;
    }

    /** Return true if and only if this BulkCargo is equal to the given BulkCargo
     * For BulkCargo to be equal, they must have the same ID, destination type and tonnage
     *
     * @param o other object to check equality
     * @return true if equal, false otherwise*/
    public boolean equals(Object o){
        if (o == null){
            return false;
        }
        if (!(o instanceof BulkCargo)){
            return false;
        }

        BulkCargo testEqual = (BulkCargo) o;

        return this.getId() == testEqual.getId()
                && this.getDestination().equals(testEqual.getDestination())
                && this.getTonnage() == testEqual.getTonnage();
    }

    /** Return the hash code of this BulkCargo
     * Two BulkCargo are equal according to
     * equals(Object) method should have the same hash code.
     *
     * @return hash code of this BulkCargo*/
    public int hashCode(){
        return Objects.hash(getId(), getDestination(), getTonnage());
    }

    /**
     * Returns the human-readable string representation of this BulkCargo.
     * <p>
     * The format of the string to return is
     * <pre>BulkCargo id to destination [type - tonnage]</pre>
     * Where:
     * <ul>
     *   <li>{@code id} is the id of this cargo </li>
     *   <li>{@code destination} is the destination of the cargo </li>
     *   <li>{@code type} is the type of cargo</li>
     *   <li>{@code tonnage} is the tonnage of the cargo</li>
     * </ul>
     * For example: <pre>BulkCargo 42 to Brazil [OIL - 420]</pre>
     *
     * @return string representation of this BulkCargo
     * @ass1
     */
    @Override
    public String toString() {
        return String.format("%s [%s - %d]",
            super.toString(),
            this.type,
            this.tonnage);
    }

    /** Returns the machine-readable string representation of this BulkCargo.
     * <p>
     * The format of the string to return is
     * <pre>BulkCargo:id:destination:type:tonnage</pre>
     * Where:
     * <ul>
     *     <li>{@code id} is the id of this cargo</li>
     *     <li>{@code destination} is the destination of the cargo</li>
     *     <li>{@code type} is the bulk cargo type</li>
     *     <li>{@code tonnage} is the bulk cargo weight in tonnes</li>
     * </ul>
     * For example: <pre>BulkCargo:2:Germany:GRAIN:50</pre>
     * </p>
     *
     * @return encoded string representation of this Cargo*/
    @Override
    public String encode(){
        return String.format("%s:%s:%d",
                super.encode(),
                this.type,
                this.tonnage);
    }
}
