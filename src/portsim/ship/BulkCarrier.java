package portsim.ship;

import portsim.cargo.BulkCargo;
import portsim.cargo.BulkCargoType;
import portsim.cargo.Cargo;
import portsim.port.Quay;
import portsim.util.NoSuchCargoException;

public class BulkCarrier extends Ship
// Represents a ship capable of carrying bulk cargo.
{
    int tonnageCapacityShip;
    BulkCargoType typeOfCargoOnBoard;

    public BulkCarrier(long imoNumber, String name, String originFlag, NauticalFlag flag, int capacity)
    // Creates a new bulk carrier with the given IMO number, name, origin port, nautical flag and cargo capacity.
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

    public boolean canDock(Quay quay)
    /*
        Check if this ship can dock with the specified quay.
        The conditions for a compatible quay are:

        Quay must be a BulkQuay.
        The quay's maximum cargo weight must be ≥ this ship's cargo weight in tonnes.
     */
    {

    }

    public boolean canLoad(Cargo cargo)
    /*
       Checks whether the specified cargo can be loaded onto the ship.
        The given cargo can only be loaded if all the following conditions are true:

            The ship does not have any cargo on board
            The cargo given is a BulkCargo
            The cargo tonnage is less than or equal to the ship's tonnage capacity
            The cargo's destination is the same as the ships origin country
     */
    {

    }

    public void loadCargo(Cargo cargo)
    // Loads the specified cargo onto the ship.
    {

    }

    public BulkCargo unloadCargo() throws NoSuchCargoException
    /*
        Unloads the cargo from the ship.
        The ship's cargo should be set to null at the end of the operation.
     */
    {

    }

    public BulkCargo getCargo()
    // Return the current cargo onboard this vessel.
    {

    }

    @Override
    public String toString()
    /*
     Returns the human-readable string representation of this BulkCarrier.
        The format of the string to return is

        BulkCarrier name from origin [flag] carrying cargoType
     */
    {
        return super.toString() + this.getClass().getName() + this.NameOfShip + "from " + this.PortOfOrigin + "["
                + this.FlagShipFlying + "]" + "carrying" + this.typeOfCargoOnBoard;
    }
}
