package portsim.movement;

/** The movement of ships or cargo coming into or out of the port from land or sea.*/
public abstract class Movement extends Object {
    /** the time the movement should occur */
    private long timeOfMoveOccur;

    /** the direction of the movement */
    private MovementDirection directionToMove;

    /** Creates a new movement with the given action time and direction
     * @param time - the time the movement should occur, long type
     * @param direction - the direction of the movement, MovementDirection
     * @throws IllegalArgumentException - if time < 0
     * */
    public Movement(long time, MovementDirection direction) {
        this.timeOfMoveOccur = time;
        this.directionToMove = direction;

        if (time < 0) {
            throw new IllegalArgumentException();
        }
    }

    /** Returns the time the movement should be actioned
     * @return movement time, long type
     * */
    public long getTime() {
        return this.timeOfMoveOccur;
    }

    /** Returns the direction of the movement.
     * @return movement direction, MovementDirection
     * */
    public MovementDirection getDirection() {
        return this.directionToMove;
    }

    /** Returns the human-readable string representation of this Movement.
     * The format of the string to return is
     *
     * DIRECTION MovementClass to occur at time
     * Where:
     * DIRECTION is the direction of the movement
     * MovementClass is the Movement class name
     * time is the time the movement is meant to occur
     * For example:
     * INBOUND Movement to occur at 120
     * */
    @Override
    public String toString() {
        return super.toString() + getDirection() +
                this.getClass().getName() + "to occur at " + getTime();
    }
}
