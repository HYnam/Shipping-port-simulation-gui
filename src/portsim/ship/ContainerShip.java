package portsim.ship;

import portsim.cargo.Cargo;
import portsim.cargo.Container;
import portsim.port.ContainerQuay;
import portsim.port.Quay;
import portsim.util.NoSuchCargoException;

import java.util.ArrayList;
import java.util.List;

/** Represents a ship capable of carrying shipping containers.*/
public class ContainerShip extends Ship
{
    /** the container capacity of this ship */
    private int containerCapacityOfShip;

    /** list of containers on board */
    private List<Container> containersOnBoard = new ArrayList<>();

    /** Creates a new container ship with the given IMO number, name and origin port, nautical flag and cargo capacity.
     * @param imoNumber - unique identifier, long type
     * @param name - name of the ship, String type
     * @param originFlag - port of origin, String type
     * @param flag - the nautical flag this ship is flying, NauticalFlag
     * @param capacity - the container capacity of this ship, int type
     * @throws IllegalArgumentException - if imoNumber < 0, imoNumber is not 7 digits long or if the container capacity is < than 0
     * */
    public ContainerShip(long imoNumber, String name, String originFlag, NauticalFlag flag, int capacity)
    {
        super(imoNumber, name, originFlag, flag);
        this.containerCapacityOfShip = capacity;

        // Change long type to String type
        String longAsString = Long.toString(imoNumber);

        if(imoNumber < 0 || longAsString.length() != 7 || capacity < 0)
        {
            throw new IllegalArgumentException ("unique identifier out of range");
        }
    }

    /**Checks if this ship can dock with the specified quay.
     * The conditions for a compatible quay are:
     * Quay must be a ContainerQuay.
     * The quays maximum number of containers must be ≥ the number of containers on board.
     * Specified by:
     * canDock in class Ship
     * @param quay - quay to be checked
     * @return true if the Quay satisfies the conditions else false
     * */
    public boolean canDock(Quay quay)
    {
        if (quay instanceof ContainerQuay && quay.getShip() == null)
            return true;
        else
            return false;
    }

    /**Checks whether the specified cargo can be loaded onto the ship.
     * The given cargo can only be loaded if all the following conditions are true:
     *
     *         The cargo given is a Container
     *         The current number of containers on board is less than the container capacity
     *         The cargo's destination is the same as the ships origin country
     * Specified by:
     * canLoad in class Ship
     * @param cargo - cargo to be loaded
     * @return true if the Cargo satisfies the conditions else false
     * */
    public boolean canLoad(Cargo cargo)
    {
        if (cargo instanceof Container
            && containersOnBoard.size() < containerCapacityOfShip
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
        containersOnBoard.add((Container) cargo);
    }

    /**Unloads the cargo from the ship.
     * The ship's cargo should be set to an empty list.
     * @return the ship's cargo before it was unloaded
     * @throws NoSuchCargoException - if the ship has already been unloaded (i.e. the ship has no cargo onboard)
     * */
    public List<Container> unloadCargo() throws NoSuchCargoException
    {
        if (this.containersOnBoard.size() > 0) {
            List<Container> listForReturn = new ArrayList<>(this.containersOnBoard);
            this.containersOnBoard.clear();
            return listForReturn;
        } else {
            throw new NoSuchCargoException("the ship has no cargo onboard");
        }
    }

    /** Returns the current cargo onboard this vessel
     * Adding or removing elements from the returned list should be not affect the original list.
     * @return containers on the vessel
     * */
    public List<Container> getCargo()
    {
        List<Container> listForReturn = new ArrayList<>(this.containersOnBoard);
        return listForReturn;
    }

    /**Returns the human-readable string representation of this ContainerShip.
     * The format of the string to return is
     *
     *         ContainerShip name from origin [flag] carrying num containers
     * Where:
     * name is the name of this ship
     * origin is the country of origin of this ship
     * flag is the nautical flag of this ship
     * num is the number of containers on board
     * For example:
     *  ContainerShip Evergreen from Australia [BRAVO] carrying 3 containers
     * */
    @Override
    public String toString()
    {
        return super.toString() + this.getClass().getName() + getName() + "from" + getOriginalFlag() + "[" + getFlag() + "]" + "carrying"
                +"containers";
    }
}
