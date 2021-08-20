package portsim.port;

import portsim.cargo.Cargo;
import java.util.List;

public class Port extends Object
// A place where ships can come and dock with Quays to load / unload their cargo.
// Cargo is stored within the port.
{
    String NameOfPort;

    public Port(String name)
    /*
        Creates a new port with the given name
        The list of quays in the port and stored cargo should be initialised as empty lists.
    */
    {
        this.NameOfPort = name;
    }

    public String getName()
    // Returns the name of this port.
    {
        return this.NameOfPort;
    }

    public List<Quay> getQuays()
    /*
    Returns a list of all quays associated with this port.
    Adding or removing elements from the returned list should not affect the original list.

    The order in which quays appear in this list should be the same as the order in which they were added by calling addQuay(Quay).
     */
    {

    }

    public List<Cargo> getCargo()
    /*
    Returns the cargo stored at this port.
    Adding or removing elements from the returned list should not affect the original list.
     */
    {

    }

    public void addQuay (Quay quay)
    // Adds a quay to the ports control
    {
        return;
    }
}
