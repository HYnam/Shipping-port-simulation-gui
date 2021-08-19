package portsim.cargo;

public class Container extends Cargo
{
    // Represents a shipping container, used for holding or transporting something
    public ContainerType TypeOfContainer;

    public Container(int id, String destination, ContainerType type)
    {
        // Creates a new Container of the specified ContainerType, with the given ID and destination.
        super(id, destination);
        this.TypeOfContainer = type;

        if (id < 0)
        {
            throw new IllegalArgumentException ("id out of range");
        }
    }

    public ContainerType getType()
    {
        // Returns the type of this container.
        return this.TypeOfContainer;
    }

    @Override
    public String toString()
    {
        /* Returns the human-readable string representation of this Container.
            e.g: Container id to destination [type]
         */
        return super.toString() + "Container" + this.getID()+ "to" + this.getDestination() + "[" + this.TypeOfContainer + "]";
    }
}
