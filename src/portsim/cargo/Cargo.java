package portsim.cargo;


public abstract class Cargo extends Object
// Denotes a cargo whose function is to be transported via a Ship or land transport.
{
    public int cargoId;
    public String destPort;

    public Cargo(int id, String destination)
    // Constructor: Creates a new Cargo with the given ID and destination port.
    {
        this.cargoId = id;
        this.destPort = destination;

        if(id < 0)
        {
            throw new IllegalArgumentException ("id out of range");
        }
    }

    public int getID()
    // Method: Retrieve the ID of this piece of cargo.
    {
        return this.cargoId;
    }

    public String getDestination()
    // Method: Retrieve the destination of this piece of cargo
    {
        return this.destPort;
    }

    @Override
    public String toString()
    /* Method: Returns the human-readable string representation of this cargo.
       e.g:  CargoClass id to destination
     */
    {
        return super.toString() + CargoClass + this.cargoId + "to" + this.destPort;
    }
}
