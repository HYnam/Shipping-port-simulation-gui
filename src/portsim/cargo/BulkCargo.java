package portsim.cargo;

public class BulkCargo extends Cargo
// Bulk cargo is commodity cargo that is transported unpacked in large quantities.
{
    public int cargoId;
    public String destPort;
    public int weight;
    public BulkCargoType typeOfCargo;

    public BulkCargo(int id, String destination, int tonnage, BulkCargoType type)
    // Constructor: Creates a new Bulk Cargo with the given ID, destination, tonnage and type.
    {
        // Initialise variables
        this.cargoId = id;
        this.destPort = destination;
        this.weight = tonnage;
        this.typeOfCargo = type;

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
        return this.typeOfCargo;
    }

    @Override
    public String toString()
    /* Method: Returns the human-readable string representation of this BulkCargo.
        e.g.: BulkCargo id to destination [type - tonnage]
     */
    {
        return super.toString() + "BulkCargo" + this.cargoId + "to" + this.destPort + '[' + this.typeOfCargo + '-' + this.weight + ']';
    }
}
