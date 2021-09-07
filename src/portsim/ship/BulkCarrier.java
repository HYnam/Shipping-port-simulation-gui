package portsim.ship;

import portsim.cargo.BulkCargo;
import portsim.cargo.BulkCargoType;
import portsim.cargo.Cargo;
import portsim.port.BulkQuay;
import portsim.port.Quay;
import portsim.util.NoSuchCargoException;
import java.lang.*;

/** Represents a ship capable of carrying bulk cargo.*/
public class BulkCarrier extends Ship
{
    /**  the tonnage capacity of this ship */
    private int tonnageCapacityShip;

    /**  the cargo on this ship */
    private BulkCargo cargoOnBoard;

    /** Creates a new bulk carrier with the given IMO number, name, origin port, nautical flag and cargo capacity.
     * @param imoNumber - unique identifier, long type
     * @param name - name of the ship, String type
     * @param originFlag - port of origin, String type
     * @param flag - the nautical flag this ship is flying, NauticalFlag
     * @param capacity - the tonnage capacity of this ship, int type
     * @throws IllegalArgumentException - if imoNumber < 0, imoNumber is not 7 digits long or if the tonnage capacity is < than 0
     * */
    public BulkCarrier(long imoNumber, String name, String originFlag, NauticalFlag flag, int capacity)
    {
        super(imoNumber, name, originFlag, flag);
        this.tonnageCapacityShip = capacity;
        this.cargoOnBoard = null;

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
     * @param quay - quay to be checked
     * @return true if the Quay satisfies the conditions else false
     * */
    public boolean canDock(Quay quay)
    {
        if(quay instanceof BulkQuay && ((BulkQuay) quay).getMaxTonnage() >= this.tonnageCapacityShip){
            return true;
        }else{
            return false;
        }
    }

    /** Checks whether the specified cargo can be loaded onto the ship.
     * The given cargo can only be loaded if all the following conditions are true:
     *
     *             The ship does not have any cargo on board
     *             The cargo given is a BulkCargo
     *             The cargo tonnage is less than or equal to the ship's tonnage capacity
     *             The cargo's destination is the same as the ship origin country
     * Specified by:
     * canLoad in class Ship
     * @param cargo - cargo to be loaded
     * @return true if the Cargo satisfies the conditions else false
     * */
    public boolean canLoad(Cargo cargo)
    {
        if(this.cargoOnBoard == null
            && cargo instanceof BulkCargo
            && ((BulkCargo) cargo).getTonnage() <= this.tonnageCapacityShip
            && cargo.getDestination().equals(getOriginalFlag())) {
            return true;
        } else {
            return false;
        }
    }

    /** Loads the specified cargo onto the ship.
     * Specified by:
     * loadCargo in class Ship
     * @param cargo - cargo to be loaded
     * Requires:
     * Cargo given is able to be loaded onto this ship according to canLoad(Cargo)
     * */
    public void loadCargo(Cargo cargo)
    {
        this.cargoOnBoard = (BulkCargo) cargo;
    }

    /** Unloads the cargo from the ship.
     *  The ship's cargo should be set to null at the end of the operation.
     *  @return the ships cargo
     * @throws NoSuchCargoException - if the ship has already been unloaded
     * */
    public BulkCargo unloadCargo() throws NoSuchCargoException {
        if (this.cargoOnBoard != null) {
            BulkCargo cargoForReturn = this.cargoOnBoard;
            this.cargoOnBoard = null;
            return cargoForReturn;
        } else {
            throw new NoSuchCargoException("the ship has already been unloaded");
        }
    }

    /** Return the current cargo onboard this vessel.
     * @return bulk cargo on the vessel
     * */
    public BulkCargo getCargo()
    {
        return this.cargoOnBoard;
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
                + getFlag() + "]" + "carrying" + this.cargoOnBoard.getType();
    }
}
