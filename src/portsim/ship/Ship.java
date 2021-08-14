package portsim.ship;

public abstract class Ship extends Object
/*
    Represents a ship whose movement is managed by the system
    Ships store various types of cargo which can be loaded and unloaded at a port
 */
{
    long Identifier;
    String NameOfShip;
    String PortOfOrigin;
    NauticalFlag FlagShipFlying;

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
            throw new IllegalArgumentException ("unique identifier out of range");
        }
    }

    public abstract boolean canDock(Quay quay)
    // Check if this ship can dock with the specified quay according to the conditions determined by the ships type
    {
        if ()
    }

    public abstract boolean canLoad(Cargo cargo)
    // Checks if the specified cargo can be loaded onto the ship according to the conditions determined by the ships type
    {

    }

    public abstract void loadCargo(Cargo cargo)
    // Loads the specified cargo onto the ship
    {

    }
}
