package portsim.cargo;

public class Container extends Cargo
{
    // Represents a shipping container, used for holding or transporting something
    public int cargo_id;
    public String dest_port;
    public ContainerType type;

    public Container(int id, String destination, ContainerType type)
    {
        // Creates a new Container of the specified ContainerType, with the given ID and destination.
        super.
        this.cargo_id = id;
        this.dest_port = destination;
        this.type = type;

        if (id < 0)
        {
            throw new IllegalArgumentException ("id out of range");
        }
    }

    public ContainerType getType()
    {
        // Returns the type of this container.
        return this.type;
    }

    @Override
    public String toString()
    {
        /* Returns the human-readable string representation of this Container.
            e.g: Container id to destination [type]
         */
        return super.toString() + "Container" + this.cargo_id + "to" + this.dest_port + "[" + this.type + "]";
    }
}
