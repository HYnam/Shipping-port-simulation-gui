package portsim.cargo;


public abstract class Cargo
{
    public Cargo(int id, String destination)
    // Creates a new Cargo with the given ID and destination port.
    {
        int Cargo_id = id;
        String dest_port = destination;

        if(Cargo_id < 0)
        {
            throw new IllegalArgumentException ("id out of range");
        }
    }


}
