package portsim.evaluators;

import portsim.movement.Movement;
import portsim.movement.MovementDirection;

import java.util.HashMap;
import java.util.Map;

/** Gathers data on how many ships each country has sent to this port.
 *
 * Stores a mapping of country-of-origin flags to the number of times that flag has been seen in
 * inbound movements.
 * */
public class ShipFlagEvaluator extends StatisticsEvaluator {

    /** Flag distribution at this port */
    private Map<String, Integer> flagDistribution;

    /** Constructs a new ShipFlagEvaluator */
    public ShipFlagEvaluator(){
        this.flagDistribution = new HashMap<>();
    }

    /** Return the flag distribution seen at this port
     * @return flag distribution
     * */
    public Map<String, Integer> getFlagDistribution(){
        return flagDistribution;
    }

    /** Return the number of times the given flag has been seen at the port.
     * @param flag country flag to find in the mapping
     * @return number of times flag seen or 0 if not seen
     * */
    public int getFlagStatistics(String flag){
        if (getFlagDistribution().containsKey(flag)){
            return getFlagDistribution().get(flag);
        } else {
            return 0;
        }
    }

    /** Updates the internal mapping of ship country flags using the given movement.
     *
     * If the movement is not an INBOUND movement, this method returns immediately
     * without taking any action.
     *
     * If the movement is not a ShipMovement, this method returns immediately without
     * taking any action
     *
     * If the movement is an INBOUND ShipMovement, do the following:
     * <ul>
     *     <li>If the flag has been seen before (exists as a key in the map) increment that
     *     number</li>
     *
     *     <li>If the flag has not been seen before add as a key in the map with a
     *     corresponding value of 1</li>
     * </ul>
     * @param movement movement to read
     * */
    public void onProcessMovement(Movement movement){
        if (movement.getDirection() != MovementDirection.INBOUND){
            // take no action
        } else if ((movement.getDirection() != MovementDirection.OUTBOUND)
            || (movement.getDirection() != MovementDirection.INBOUND)){
            // take no action if movement is not a ShipMovement
        }

        if (movement.getDirection() == MovementDirection.INBOUND){
            if (getFlagStatistics(flag) > 0){
                getFlagStatistics(flag) ++;
            } else {
                this.flagDistribution.put(flag, 1);
            }
        }
    }
}
