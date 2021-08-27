package portsim.port;

import portsim.cargo.Cargo;

import java.util.ArrayList;
import java.util.List;

/** A place where ships can come and dock with Quays to load / unload their cargo.
 * Cargo is stored within the port.
 * */
public class Port extends Object
{
    /** name of the port */
    private String NameOfPort;

    /** Creates a new port with the given name.
     * The list of quays in the port and stored cargo should be initialised as empty lists.
     *
     * Parameters:
     * name - name of the port
     * */
    public Port(String name)
    {
        this.NameOfPort = name;
    }

    /** Returns the name of this port.
     * Returns:
     * port's name
     * */
    public String getName()
    {
        return this.NameOfPort;
    }

    /** Returns a list of all quays associated with this port.
     * Adding or removing elements from the returned list should not affect the original list.
     *
     * The order in which quays appear in this list should be the same as the order in which they were added by calling addQuay(Quay).
     *
     * Returns:
     * all quays
     * */
    public List<Quay> getQuays()
    {
        // Declare a list called quays to store all the quays
        List<Quay> quays = new ArrayList<>();
        quays.addAll();
    }

    /** Returns the cargo stored at this port.
     *  Adding or removing elements from the returned list should not affect the original list.
     *  Returns:
     * port cargo
     * */
    public List<Cargo> getCargo()
    {

    }

    /** Adds a quay to the ports control
     * Parameters:
     * quay - the quay to add
     * */
    public void addQuay (Quay quay)
    {

    }
}
