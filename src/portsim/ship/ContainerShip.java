package portsim.ship;

import portsim.cargo.Cargo;
import portsim.cargo.Container;
import portsim.port.Quay;
import portsim.util.NoSuchCargoException;
import java.util.List;

public class ContainerShip extends Ship
// Represents a ship capable of carrying shipping containers.
{
    int ContainerCapacityOfShip;

    public ContainerShip(long imoNumber, String name, String originFlag, NauticalFlag flag, int capacity)
    // Creates a new container ship with the given IMO number, name and origin port, nautical flag and cargo capacity.
    {
        super(imoNumber, name, originFlag, flag);
        this.ContainerCapacityOfShip = capacity;

        // Change long type to String type
        String longAsString = Long.toString(imoNumber);

        if(imoNumber < 0 || longAsString.length() != 7 || capacity < 0)
        {
            throw new IllegalArgumentException ("unique identifier out of range");
        }
    }

    public boolean canDock(Quay quay)
    /*
         Checks if this ship can dock with the specified quay.
            The conditions for a compatible quay are:

            Quay must be a ContainerQuay.
            The quays maximum number of containers must be ≥ the number of containers on board.
     */
    {
        if(quay.isEmpty() == false && )
            return true;
        else
            return false;
    }

    public boolean canLoad(Cargo cargo)
    /*
    Checks whether the specified cargo can be loaded onto the ship.
    The given cargo can only be loaded if all the following conditions are true:

        The cargo given is a Container
        The current number of containers on board is less than the container capacity
        The cargo's destination is the same as the ships origin country
     */
    {

    }

    public void loadCargo(Cargo cargo)
    // Loads the specified cargo onto the ship.
    {

    }

    public List<Container> unloadCargo() throws NoSuchCargoException
    // Unloads the cargo from the ship.
    // The ship's cargo should be set to an empty list.
    {

    }

    public List<Container> getCargo()
    /*
    Returns the current cargo onboard this vessel
    Adding or removing elements from the returned list should be not affect the original list.
     */
    {

    }

    @Override
    public String toString()
    /*
       Returns the human-readable string representation of this ContainerShip.
        The format of the string to return is

        ContainerShip name from origin [flag] carrying num containers
     */
    {
        return super.toString() + this.getClass().getName() + this.NameOfShip + "from" + this.PortOfOrigin + "[" + this.FlagShipFlying + "]" + "carrying"
                +"containers";
    }
}
