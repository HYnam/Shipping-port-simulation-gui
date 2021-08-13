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

        if(imoNumber < 0 || imoNumber.length())
    }
}
