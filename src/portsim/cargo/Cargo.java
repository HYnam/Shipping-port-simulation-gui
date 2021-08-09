package portsim.cargo;


public abstract class Cargo
{
    public Cargo(int id, String destination)
    // Constructor: Creates a new Cargo with the given ID and destination port.
    {
        int Cargo_id = id;
        String dest_port = destination;

        if(Cargo_id < 0)
        {
            throw new IllegalArgumentException ("id out of range");
        }
    }

    public int getID()
    // Method: Retrieve the ID of this piece of cargo.
    {
        return Cargo_id;
    }

    public String getDestination()
    // Method: Retrieve the destination of this piece of cargo
    {
        return dest_port;
    }

    public String toString()
    // Method: Returns the human-readable string representation of this cargo.
    {

    }
}
