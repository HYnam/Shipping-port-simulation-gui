package portsim.cargo;

public enum BulkCargoType
{
    COAL,
    GRAIN,
    MINERALS,
    OIL,
    OTHER;

    public static BulkCargoType[] values()
    // Returns an array containing the constants of this enum type, in the order they are declared
    {
            for (BulkCargoType c : BulkCargoType.values())
                System.out.println(c);
        }
    }
}
