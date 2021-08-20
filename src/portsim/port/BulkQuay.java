package portsim.port;

public class BulkQuay extends Quay
// A Bulk Quay is a type of quay specifically designed for the unloading of Bulk Carrier vessels.
{
    int MaxTonnageCanHandle;

    public BulkQuay(int id, int maxTonnage)
    // Creates a new Bulk Quay with the given ID and max tonnage.
    {
        super(id);
        this.MaxTonnageCanHandle = maxTonnage;

        if(id < 0 || maxTonnage < 0)
        {
            throw new IllegalArgumentException ("ID or maxTonnage must be greater than zero");
        }
    }

    public int getMaxTonnage()
    // Returns the maximum number of tonnes of cargo this quay can handle
    {
        return this.MaxTonnageCanHandle;
    }

    @Override
    public String toString()
    /*
    Returns the human-readable string representation of this BulkQuay.
        The format of the string to return is

        BulkQuay id [Ship: imoNumber] - maxTonnage
     */
    {
        return super.toString() + "BulkQuay " + this.QuayId + "[Ship: " + this.getShip() + "] - " + this.getMaxTonnage();
    }
}
