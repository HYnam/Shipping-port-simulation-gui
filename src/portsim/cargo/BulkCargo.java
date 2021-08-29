package portsim.cargo;

/** Bulk cargo is commodity cargo that is transported unpacked in large quantities.*/
public class BulkCargo extends Cargo
{

    /** the weight of the cargo */
    private int Weight;

    /** the type of cargo */
    private BulkCargoType TypeOfCargo;

    /** Constructor: Creates a new Bulk Cargo with the given ID, destination, tonnage and type.
     * Parameters:
     * id - cargo ID
     * destination - destination port
     * tonnage - the weight of the cargo
     * type - the type of cargo
     * Throws:
     * IllegalArgumentException - if ID < 0 or tonnage < 0
     * */
    public BulkCargo(int id, String destination, int tonnage, BulkCargoType type)
    {

        // Initialise variables
        super(id, destination);
        this.Weight = tonnage;
        this.TypeOfCargo = type;

        // throw exception
        if (id < 0 || tonnage < 0)
        {
            throw new IllegalArgumentException();
        }
    }

    /** Method: Returns the tonnage of this bulk cargo.
     * Returns:
     * cargo tonnage
     * */
    public int getTonnage()
    {
        return this.Weight;
    }

    /** Method: Returns the type of this bulk cargo.
     * Returns:
     * cargo type
     * */
    public BulkCargoType getType()
    {
        return this.TypeOfCargo;
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
    public String toString()
    {
        return super.toString() + "BulkCargo" + getID() + "to" + getDestination() + '[' + getType() + " - " + getTonnage() + ']';
    }
}
