package portsim.evaluators;

import portsim.movement.Movement;
import portsim.util.Tickable;

import java.time.Instant;

/** A base class representing an object that gathers and reports data on various aspects of the
 * port's operation
 * */
public abstract class StatisticsEvaluator implements Tickable {

    /** the time since the evaluator was created */
    private long timeEvaluatorCreated;

    /** Creates a statistics evaluator and initialises the time since the evaluator was created to
     * zero
     * */
    public StatisticsEvaluator(){
        this.timeEvaluatorCreated = 0;
    }

    public long getTime(){
        return this.timeEvaluatorCreated;
    }

    /** Read a movement to update the relevant evaluator data.
     * This method is called by the Port.processMovement(Movement) method.
     * @param movement movement to read
     * */
    public abstract void onProcessMovement(Movement movement);

    /** Simulate a minute passing. The time since the evaluator was created should be
     * increment by one
     * */
    public void elapseOneMinute(){
        this.timeEvaluatorCreated++;
//        for (Instant.now().plusSeconds(60);;){  //Count 1 minute
//            this.timeEvaluatorCreated ++;
//        }
    }
}
