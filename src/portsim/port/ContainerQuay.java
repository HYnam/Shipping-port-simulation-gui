package portsim.port;

public class ContainerQuay extends Quay
// A Container Quay is a type of quay specifically designed for the unloading of Container Ship vessels.
{
    int MaxContainerQuayHandle;

    public ContainerQuay(int id, int maxContainers)
    // Creates a new Container Quay with the given ID and maximum number of containers.
    {
        super(id);
        this.MaxContainerQuayHandle = maxContainers;

        if(id < 0 || maxContainers < 0)
        {
            throw new IllegalArgumentException ("ID or maxContainers must be greater than zero");
        }
    }

    public int getMaxContainers()
    // Returns the maximum number of containers of this quay can process at once.
    {
        return this.MaxContainerQuayHandle;
    }

    @Override
    public String toString()
    /*
    Returns the human-readable string representation of this ContainerQuay.
    The format of the string to return is

        ContainerQuay id [Ship: imoNumber] - maxContainers
     */
    {
        return super.toString() + "ContainerQuay " + this.QuayId + "[Ship: " + this.getShip() + "] - " + this.getMaxContainers();
    }
}
