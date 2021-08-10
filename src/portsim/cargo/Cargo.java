package portsim.cargo;


public abstract class Cargo
{
    public int cargo_id;
    public String dest_port;

    public Cargo(int id, String destination)
    // Constructor: Creates a new Cargo with the given ID and destination port.
    {
        int cargo_id = id;
        String dest_port = destination;

        if(cargo_id < 0)
        {
            throw new IllegalArgumentException ("id out of range");
        }
    }

    public int getID()
    // Method: Retrieve the ID of this piece of cargo.
    {
        return cargo_id;
    }

    public String getDestination()
    // Method: Retrieve the destination of this piece of cargo
    {
        return dest_port;
    }

    @Override
    public String toString()
    // Method: Returns the human-readable string representation of this cargo.
    {
        return super.toString() + Cargo + cargo_id + "to" + dest_port;
    }
}
