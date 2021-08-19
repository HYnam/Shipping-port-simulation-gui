package portsim.port;


public abstract class Quay extends Object
// Quay is a platform lying alongside or projecting into the water where ships are moored for loading or unloading.
{
    int QuayId;
    public Quay(int id)
    // Creates a new Quay with the given ID, with no ship docked at the quay.
    {
        this.QuayId = id;
    }
}
