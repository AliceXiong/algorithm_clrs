package cscie55.hw1.elevator;
/**
 * Traveling up until it reaches the top of the building, and back down
 * @author Yuling (Alice) Xiong 
 * @version 1.0 Feb 15, 2015
 */
public class Elevator {
    public static final int NUMBFLOORS = 7;
    public int currentFloor = 1;
    public enum Direction {UP, DOWN}; 
    Direction direction = Direction.UP;
    private int passengers = 0;
    private int[] tracking = {5,0,0,0,0,0,0,0}; 
    

    /** Modifies the direction of travel, if the ground floor or top floor has been reached 
     *  direction move up or down
     */
    public void move(){
        if(direction == Direction.DOWN){
            currentFloor--;
        }else{
            currentFloor++;
        }

        if(currentFloor == NUMBFLOORS){
            direction = Direction.DOWN;
        }
        else if(currentFloor == 1){
            direction = Direction.UP;
        }

        if(tracking[currentFloor] != 0){
            passengers = passengers - tracking[currentFloor];
            tracking[currentFloor] = 0;
        }       
    }

    /** Adds to the Elevator a passenger destined for the indicated floor
     *  @param floor the boarding passenger will stop at
     */
    public void boardPassenger(int floor){
        passengers++;
        tracking[floor]++;
    }

    /** 
     * Assist in debugging and testing
     * @return string number of passengers on board, and the current floor
     **/
    public String toString(){
        //System.out.println( "Floor "+currentFloor+" : "+passengers+" passengers\n  ");
        return "Floor " +currentFloor+ " : " +passengers+ " passengers\n";
    }
}
