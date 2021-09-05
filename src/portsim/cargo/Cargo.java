package portsim.cargo;

/** Denotes a cargo whose function is to be transported via a Ship or land transport.
 * Cargo is kept track of via its ID.*/
public abstract class Cargo extends Object
{
    /** Cargo ID */
    private int cargoId;

    /** destination port */
    private String destPort;

    /** Constructor: Creates a new Cargo with the given ID and destination port.
     * @param id - cargo ID, int type
     * @param destination - destination port, string type
     * @throws IllegalArgumentException - if ID < 0
     * */
    public Cargo(int id, String destination)
    {
        this.cargoId = id;
        this.destPort = destination;

        if(id < 0)
        {
            throw new IllegalArgumentException();
        }
    }

    /** Method: Retrieve the ID of this piece of cargo.
     * @return the cargo's ID, int type
     * */
    public int getID()
    {
        return this.cargoId;
    }

    /** Method: Retrieve the destination of this piece of cargo
     * @return the cargo's destination, String type
     * */
    public String getDestination()
    {
        return this.destPort;
    }

    /** Returns the human-readable string representation of this cargo.
     * The format of the string to return is
     *
     * CargoClass id to destination
     * Where:
     * CargoClass is the cargo class name
     * id is the id of this cargo
     * destination is the destination of the cargo
     * For example:
     *
     * Cargo 23 to Australia
     * or
     * Container 55 to New Zealand
     * */
    @Override
    public String toString()
    {
        return super.toString() + this.getClass().getName() + getID()+ "to" + getDestination();
    }
}
