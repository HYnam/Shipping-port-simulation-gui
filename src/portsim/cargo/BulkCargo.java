package portsim.cargo;

public class BulkCargo
{
     // Bulk cargo is commodity cargo that is transported unpacked in large quantities.
    public BulkCargo(int id, String destination, int tonnage, BulkCargoType type){
        // Constructor: Creates a new Bulk Cargo with the given ID, destination, tonnage and type.
        if (id < 0 || tonnage < 0){
            throw new IllegalArgumentException ("id or tonnage out of range");
        }
    }

    public int getTonnage(){
        // Method: Returns the tonnage of this bulk cargo.
        return tonnage;
    }
}
