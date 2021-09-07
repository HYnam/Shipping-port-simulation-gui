package portsim.cargo;

/** Represents a shipping container, used for holding or transporting something*/
public class Container extends Cargo {
    /** type of container */
    private ContainerType typeOfContainer;

    /** Creates a new Container of the specified ContainerType, with the given ID and destination.
     * @param id - cargo ID, int type
     * @param destination - destination port, String type
     * @param type - type of container, ContainerType
     * @throws IllegalArgumentException - if ID < 0
     * */
    public Container(int id, String destination, ContainerType type) {
        super(id, destination);
        this.typeOfContainer = type;

        if (id < 0) {
            throw new IllegalArgumentException();
        }
    }

    /** Returns the type of this container.
     * @return container type
     * */
    public ContainerType getType() {
        return this.typeOfContainer;
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
    public String toString() {
        return super.toString() + "Container"
               + super.getId() + " to " + super.getDestination() + "[" + getType() + "]";
    }
}
