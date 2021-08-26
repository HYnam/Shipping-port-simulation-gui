package portsim.cargo;

/** Represents a shipping container, used for holding or transporting something*/
public class Container extends Cargo
{
    /** type of container */
    private ContainerType TypeOfContainer;

    /** Creates a new Container of the specified ContainerType, with the given ID and destination.
     * Parameters:
     * id - cargo ID
     * destination - destination port
     * type - type of container
     * Throws:
     * IllegalArgumentException - if ID < 0
     * */
    public Container(int id, String destination, ContainerType type)
    {
        super(id, destination);
        this.TypeOfContainer = type;

        if (id < 0)
        {
            throw new IllegalArgumentException();
        }
    }

    /** Returns the type of this container.
     * Returns:
     * container type
     * */
    public ContainerType getType()
    {
        return this.TypeOfContainer;
    }

    /** Returns the human-readable string representation of this Container.
     * The format of the string to return is
     *
     * Container id to destination [type]
     * Where:
     * id is the id of this cargo
     * destination is the destination of the cargo
     * type is the type of cargo
     * For example:
     * Container 42 to Brazil [OTHER]
     * */
    @Override
    public String toString()
    {
        return super.toString() + "Container" + getID()+ "to" + getDestination() + "[" + getType() + "]";
    }
}
