package portsim.evaluators;

import portsim.movement.Movement;
import portsim.port.Port;
import portsim.port.Quay;

/** Evaluator to monitor hwo many quays are currently occupied at the port */
public class QuayOccupancyEvaluator extends StatisticsEvaluator {

    private Port portMonitorQuay;

    /** Constructs a new QuayOccupancyEvaluator
     * @param port port to monitor quays
     * */
    public QuayOccupancyEvaluator(Port port){
        this.portMonitorQuay = port;
    }

    /** Return the number of quays that are currently occupied
     * A quay is occupied if Quay.isEmpty() returns false.
     * @returns number of quays
     * */
    public int getQuaysOccupied(){
        if (Quay.isEmpty() == false){
            return ;
        }
        else {
            return 0;
        }
    }

    /** QuayOccupancyEvaluator does not make use of onProcessMovement(), so this
     * method can be left empty.
     *
     * Does nothing. This method is not used by this evaluator.     *
     * @param movement movement to read
     * */
    public void onProcessMovement(Movement movement){

    }
}
