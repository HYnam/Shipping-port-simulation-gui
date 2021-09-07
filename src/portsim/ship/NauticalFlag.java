package portsim.ship;

/**Stores information about particular nautical flags flown by ships
 * Maritime flags are used internationally to communicate
 * what is happening onboard a ship. Only a selection have been used below.
 * */
public enum NauticalFlag {
    /**The ship is carrying dangerous cargo */
    BRAVO,

    /**The ship is ready to be docked*/
    HOTEL,

    /** Default flag where ship has no specific status */
    NOVEMBER,

    /** Ship requires medical assistance*/
    WHISKEY
}
