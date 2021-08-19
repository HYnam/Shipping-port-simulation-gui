package portsim.cargo;


public class BulkCargo extends Cargo
// Bulk cargo is commodity cargo that is transported unpacked in large quantities.
{

    public int weight;
    public BulkCargoType TypeOfCargo;

    public BulkCargo(int id, String destination, int tonnage, BulkCargoType type)
    // Constructor: Creates a new Bulk Cargo with the given ID, destination, tonnage and type.
    {
        // Initialise variables
        super(id, destination);
        this.weight = tonnage;
        this.TypeOfCargo = type;

        if (id < 0 || tonnage < 0)
        {
            throw new IllegalArgumentException ("id or tonnage out of range");
        }
    }

    public int getTonnage()
    // Method: Returns the tonnage of this bulk cargo.
    {
        return this.weight;
    }

    public BulkCargoType getType()
    // Method: Returns the type of this bulk cargo.
    {
        return this.TypeOfCargo;
    }

    @Override
    public String toString()
    /* Method: Returns the human-readable string representation of this BulkCargo.
        e.g.: BulkCargo id to destination [type - tonnage]
     */
    {
        return super.toString() + "BulkCargo" + this.cargoId + "to" + this.destPort + '[' + this.TypeOfCargo + '-' + this.weight + ']';
    }
}
