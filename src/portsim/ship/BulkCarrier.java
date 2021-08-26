package portsim.ship;

import portsim.cargo.BulkCargo;
import portsim.cargo.BulkCargoType;
import portsim.cargo.Cargo;
import portsim.port.Quay;
import portsim.util.NoSuchCargoException;

/** Represents a ship capable of carrying bulk cargo.*/
public class BulkCarrier extends Ship
{
    /**  the tonnage capacity of this ship */
    private int tonnageCapacityShip;

    /**  */
    private BulkCargoType typeOfCargoOnBoard;

    /** Creates a new bulk carrier with the given IMO number, name, origin port, nautical flag and cargo capacity.
     * Parameters:
     * imoNumber - unique identifier
     * name - name of the ship
     * originFlag - port of origin
     * flag - the nautical flag this ship is flying
     * capacity - the tonnage capacity of this ship
     * Throws:
     * IllegalArgumentException - if imoNumber < 0, imoNumber is not 7 digits long or if the tonnage capacity is < than 0
     * */
    public BulkCarrier(long imoNumber, String name, String originFlag, NauticalFlag flag, int capacity)
    {
        super(imoNumber, name, originFlag, flag);
        this.tonnageCapacityShip = capacity;

        // Change long type to String type
        String longAsString = Long.toString(imoNumber);

        if(imoNumber < 0 || longAsString.length() != 7 || capacity < 0)
        {
            throw new IllegalArgumentException ("unique identifier out of range");
        }
    }

    /** Check if this ship can dock with the specified quay.
     * The conditions for a compatible quay are:
     *
     * Quay must be a BulkQuay.
     * The quay's maximum cargo weight must be ≥ this ship's cargo weight in tonnes.
     * Specified by:
     * canDock in class Ship
     * Parameters:
     * quay - quay to be checked
     * Returns:
     * true if the Quay satisfies the conditions else false
     * */
    public boolean canDock(Quay quay)
    {

    }

    /** Checks whether the specified cargo can be loaded onto the ship.
     * The given cargo can only be loaded if all the following conditions are true:
     *
     *             The ship does not have any cargo on board
     *             The cargo given is a BulkCargo
     *             The cargo tonnage is less than or equal to the ship's tonnage capacity
     *             The cargo's destination is the same as the ships origin country
     * Specified by:
     * canLoad in class Ship
     * Parameters:
     * cargo - cargo to be loaded
     * Returns:
     * true if the Cargo satisfies the conditions else false
     * */
    public boolean canLoad(Cargo cargo)
    {

    }

    /** Loads the specified cargo onto the ship.
     * Specified by:
     * loadCargo in class Ship
     * Parameters:
     * cargo - cargo to be loaded
     * Requires:
     * Cargo given is able to be loaded onto this ship according to canLoad(Cargo)
     * */
    public void loadCargo(Cargo cargo)
    {

    }

    /** Unloads the cargo from the ship.
     *  The ship's cargo should be set to null at the end of the operation.
     *  Returns:
     * the ships cargo
     * Throws:
     * NoSuchCargoException - if the ship has already been unloaded
     * */
    public BulkCargo unloadCargo() throws NoSuchCargoException
    {

    }

    /** Return the current cargo onboard this vessel.\
     * Returns:
     * bulk cargo on the vessel
     * */
    public BulkCargo getCargo()
    {

    }

    /** Returns the human-readable string representation of this BulkCarrier.
     *  The format of the string to return is
     *
     *         BulkCarrier name from origin [flag] carrying cargoType
     * Where:
     * name is the name of this ship
     * origin is the country of origin of this ship
     * flag is the nautical flag of this ship
     * cargoType is the type of cargo on board or the literal String nothing if there is no cargo currently on board
     * For example:
     *  BulkCarrier Evergreen from Australia [BRAVO] carrying OIL
     *  */
    @Override
    public String toString()
    {
        return super.toString() + this.getClass().getName() + getName()+ "from " + getOriginalFlag() + "["
                + getFlag() + "]" + "carrying" + this.typeOfCargoOnBoard;
    }
}
