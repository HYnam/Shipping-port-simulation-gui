package portsim.port;

/** A Bulk Quay is a type of quay specifically designed for the unloading of Bulk Carrier vessels. */
public class BulkQuay extends Quay
{
    /** maximum tonnage the quay can handle */
    private int MaxTonnageCanHandle;

    /** Creates a new Bulk Quay with the given ID and max tonnage.*/
    public BulkQuay(int id, int maxTonnage)
    {
        super(id);
        this.MaxTonnageCanHandle = maxTonnage;

        if(id < 0 || maxTonnage < 0)
        {
            throw new IllegalArgumentException ("ID or maxTonnage must be greater than zero");
        }
    }

    /** Returns the maximum number of tonnes of cargo this quay can handle.
     * Return: maxTonnage
     * */
    public int getMaxTonnage()
    {
        return this.MaxTonnageCanHandle;
    }

    /** Returns the human-readable string representation of this BulkQuay.
     * The format of the string to return is
     *
     * BulkQuay id [Ship: imoNumber] - maxTonnage
     * Where:
     * id is the ID of this quay
     * imoNumber is the IMO number of the ship docked at this quay, or None if the quay is unoccupied.
     * maxTonnage is the maximum weight in tonnes of this quay.
     * For example:
     *
     * BulkQuay 2 [Ship: 2325336] - 120
     * */
    @Override
    public String toString()
    {
        return super.toString() + "BulkQuay " + getId() + "[Ship: " + this.getShip() + "] - " + this.getMaxTonnage();
    }
}
