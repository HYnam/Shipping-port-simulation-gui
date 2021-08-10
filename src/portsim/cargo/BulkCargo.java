package portsim.cargo;

public class BulkCargo
// Bulk cargo is commodity cargo that is transported unpacked in large quantities.
{
    public int cargo_id;
    public String dest_port;
    public int weight;
    public BulkCargoType type_of_cargo;

    public BulkCargo(int id, String destination, int tonnage, BulkCargoType type)
    // Constructor: Creates a new Bulk Cargo with the given ID, destination, tonnage and type.
    {
        // Initialise variables
        cargo_id = id;
        dest_port = destination;
        weight = tonnage;
        type_of_cargo = type;

        if (id < 0 || tonnage < 0)
        {
            throw new IllegalArgumentException ("id or tonnage out of range");
        }
    }

    public int getTonnage()
    // Method: Returns the tonnage of this bulk cargo.
    {
        return cargo_id;
    }

    public BulkCargoType getType()
    // Method: Returns the type of this bulk cargo.
    {
        return type_of_cargo;
    }

    @Override
    public String toString()
    // Method: Returns the human-readable string representation of this BulkCargo.
    {
        return super.toString() + "BulkCargo" + cargo_id + "to" + dest_port + '[' + type_of_cargo + '-' + weight + ']';
    }
}
