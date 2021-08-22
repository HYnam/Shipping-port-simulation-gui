package portsim.ship;

import portsim.cargo.Cargo;
import portsim.port.Quay;

public abstract class Ship extends Object
/*
    Represents a ship whose movement is managed by the system
    Ships store various types of cargo which can be loaded and unloaded at a port
 */
{
    public long Identifier;
    public String NameOfShip;
    public String PortOfOrigin;
    public NauticalFlag FlagShipFlying;

    public Ship(long imoNumber, String name, String originFlag, NauticalFlag flag)
    // Creates a new ship with the given IMO number, name, origin port flag and nautical flag
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

    public abstract boolean canDock(Quay quay);
    // Check if this ship can dock with the specified quay according to the conditions determined by the ships type

    public abstract boolean canLoad(Cargo cargo);
    // Checks if the specified cargo can be loaded onto the ship according to the conditions determined by the ships type

    public abstract void loadCargo(Cargo cargo);
    // Loads the specified cargo onto the ship

    public String getName()
    // Return this ship's name.
    {
        return this.NameOfShip;
    }

    public long getImoNumber()
    // Return this ship's IMO number
    {
        return this.Identifier;
    }

    public String getOriginalFlag()
    // Returns this ship's flag denoting its origin.
    {
        return this.PortOfOrigin;
    }

    public NauticalFlag getFlag()
    // Returns the nautical flag the ship is flying
    {
        return this.FlagShipFlying;
    }

    @Override
    public String toString()
    /*
    Returns the human-readable string representation of this Ship.
    The format of the string to return is

        ShipClass name from origin [flag]
     */
    {
        return super.toString() + this.getClass().getName() + this.getName() + "from " + this.getOriginalFlag() + "[" + this.FlagShipFlying + "]";
    }
}
