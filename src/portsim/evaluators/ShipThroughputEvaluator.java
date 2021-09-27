package portsim.evaluators;

import portsim.movement.Movement;

/** Gathers data on how many ships pass through the port over time.
 *
 * This evaluator only counts ships that have passed through the port in the last hour (60
 * minutes)
 * */
public class ShipThroughputEvaluator extends StatisticsEvaluator {

    /** Constructs a new ShipThroughputEvaluator
     *
     * Immediately after creating a new ShipThroughputEvaluator,
     * getThroughputPerHour() should return 0
     * */
    public ShipThroughputEvaluator(){

    }

    /** Return the number of ships that have passed through the port in the last 60 minutes.
     * @return ships throughput
     * */
    public int getThroughputPerHour(){

    }

    /** Updates the internal count of ships that have passed through the port using the given
     * movement
     *
     * If the movement is not an OUTBOUND ShipMovement, this method returns immediately
     * without taking any action.
     *
     * Otherwise, the internal state of this evaluator should be modified such that
     * getThroughputPerHour() should return a value 1 more than before this method was
     * called.
     *
     * Where validMovement is an OUTBOUND ShipMovement.
     * @param movement movement to read
     * */
    public void onProcessMovement(Movement movement){

    }

    /** Simulate a minute passing. The time since the evaluator was created should be
     * incremented by one.
     *
     * If it has been more than 60 minutes since a ship exited the port, it should no longer be
     * counted towards the count returned by getThroughputPerHour().
     * */
    public void elapseOneMinute(){

    }
}
