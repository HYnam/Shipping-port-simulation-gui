package portsim.ship;

import portsim.cargo.Cargo;
import portsim.port.Quay;

/** Represents a ship whose movement is managed by the system
 * Ships store various types of cargo which can be loaded and unloaded at a port*/
public abstract class Ship extends Object

{
    private long Identifier;
    private String NameOfShip;
    private String PortOfOrigin;
    private NauticalFlag FlagShipFlying;

    /**Creates a new ship with the given IMO number, name, origin port flag and nautical flag
     * Parameters:
     * imoNumber - unique identifier
     * name - name of the ship
     * originFlag - port of origin
     * flag - the nautical flag this ship is flying
     * Throws:
     * IllegalArgumentException - if imoNumber < 0 or imoNumber is not 7 digits long (no leading zero's [0])
     * */
    public Ship(long imoNumber, String name, String originFlag, NauticalFlag flag)
    {
        this.Identifier = imoNumber;
        this.NameOfShip = name;
        this.PortOfOrigin = originFlag;
        this.FlagShipFlying = flag;

        // Change long type to String type
        String longAsString = Long.toString(imoNumber);

        if(imoNumber < 0 || longAsString.length() != 7)
        {
            throw new IllegalArgumentException ();
        }
    }

    /** Check if this ship can dock with the specified quay according to the conditions determined by the ships type
     * Parameters:
     * quay - quay to be checked
     * Returns:
     * true if the Quay satisfies the conditions else false*/
    public abstract boolean canDock(Quay quay);

    /** Checks if the specified cargo can be loaded onto the ship according to the conditions determined by the ships type
     * Parameters:
     * cargo - cargo to be loaded
     * Returns:
     * true if the Cargo satisfies the conditions else false
     * */
    public abstract boolean canLoad(Cargo cargo);

    /** Loads the specified cargo onto the ship
     * Parameters:
     * cargo - cargo to be loaded
     * Requires:
     * Cargo given is able to be loaded onto this ship according to the implementation of canLoad(Cargo)
     * */
    public abstract void loadCargo(Cargo cargo);

    /** Return this ship's name.
     * Returns:
     * name
     * */
    public String getName()
    {
        return this.NameOfShip;
    }

    /** Return this ship's IMO number
     * Returns:
     * imoNumber
     * */
    public long getImoNumber()
    {
        return this.Identifier;
    }

    /** Returns this ship's flag denoting its origin.
     * Returns:
     * originFlag
     * */
    public String getOriginalFlag()
    {
        return this.PortOfOrigin;
    }

    /** Returns the nautical flag the ship is flying
     * Returns:
     * flag
     * */
    public NauticalFlag getFlag()
    {
        return this.FlagShipFlying;
    }

    /**Returns the human-readable string representation of this Ship.
     * The format of the string to return is
     *
     * ShipClass name from origin [flag]
     * Where:
     * ShipClass is the Ship class
     * name is the name of this ship
     * origin is the country of origin of this ship
     * flag is the nautical flag of this ship
     * For example:
     * Ship Evergreen from Australia [BRAVO]
     * */
    @Override
    public String toString()
    {
        return super.toString() + this.getClass().getName() + this.getName() + "from " + this.getOriginalFlag() + "[" + this.FlagShipFlying + "]";
    }
}
