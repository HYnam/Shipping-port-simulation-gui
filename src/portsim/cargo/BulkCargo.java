package portsim.cargo;

/** Bulk cargo is commodity cargo that is transported unpacked in large quantities.*/
public class BulkCargo extends Cargo {

    /** the weight of the cargo */
    private int weight;

    /** the type of cargo */
    private BulkCargoType typeOfCargo;

    /** Constructor: Creates a new Bulk Cargo with the given ID, destination, tonnage and type.
     * @param id - cargo ID, int type
     * @param destination - destination port, string type
     * @param tonnage - the weight of the cargo, int type
     * @param type - the type of cargo, BulkCargoType
     * @Throws IllegalArgumentException - if ID < 0 or tonnage < 0
     * */
    public BulkCargo(int id, String destination, int tonnage, BulkCargoType type) {

        // Initialise variables
        super(id, destination);
        this.weight = tonnage;
        this.typeOfCargo = type;

        // throw exception
        if (id < 0 || tonnage < 0) {
            throw new IllegalArgumentException();
        }
    }

    /** Method: Returns the tonnage of this bulk cargo.
     * @return cargo tonnage, int type
     * */
    public int getTonnage() {
        return this.weight;
    }

    /** Method: Returns the type of this bulk cargo.
     * @return cargo type, BulkCargoType
     * */
    public BulkCargoType getType() {
        return this.typeOfCargo;
    }

    /** Returns the human-readable string representation of this BulkCargo.
     * The format of the string to return is
     *
     * BulkCargo id to destination [type - tonnage]
     * Where:
     * id is the id of this cargo
     * destination is the destination of the cargo
     * type is the type of cargo
     * tonnage is the tonnage of the cargo
     * For example:
     * BulkCargo 42 to Brazil [OIL - 420]
     * */
    @Override
    public String toString() {
        return "BulkCargo " + getId() + " to "
               + getDestination() + " [" + getType() + " - " + getTonnage() + "]";
    }
}
