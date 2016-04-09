package cscie55.hw2;
//import cscie55.hw2.Building; 
/**
 * Elevator class: move up and down, and talk with Building
 * @author Yuling (Alice) Xiong 
 * @version 1.0 March 1, 2016
 */
public class Elevator {
	/**
	 * @param currentFloor declare and initialize the currentFloor 
	 * @param Direction declare Direction object with two options
	 * @param passengers declare and initialize number of passengers in the elevator
	 * @param CAPACITY declare and initialize the capacity of this elevator
	 * @param building declare the building object
	 * @param boardQue array to track how many passengers will get off the elevator in boardQue[Floor]
	 * @param waitQue array to track how many passengers will get on the elevator in waitQue[Floor]
	 */
    private int currentFloor = 1;
    public enum Direction {UP, DOWN}; 
    Direction direction = Direction.UP;
    public int passengers = 0;
    public static final int CAPACITY = 10; 
    private Building building;
    private int[] boardQue ; 
    private int[] waitQue ; 
    
    /**
     * Elevator constructor: help initialize the building object and
     * pass 'the number of floors' into the size of boardQue and waitQue array
     * @param building
     */
    public Elevator (Building building) {
        this.building = building;
        boardQue = new int[building.FLOORS];
        waitQue = new int[building.FLOORS];
    }
    
    /**
     * updatewaitQue method: each time this button is pressed, the waitQue[floorNumber] will increase by 1
     * @param floorNumber
     */
    public void updatewaitQue(int floorNumber){
    	waitQue[floorNumber]=waitQue[floorNumber] + 1;
    };
    
    /**
     * getwaitQue method: waitQue's access is private, so we define this get method here
     * @param floorNumber
     * @return
     */
    public int getwaitQue(int floorNumber){
    	return waitQue[floorNumber-1];
    }
    
    /** 
     * move method:
     * Modifies the direction of travel, if the ground floor or top floor has been reached direction move up or down
     * number of passengers in the elevator will be updated according to boardQue and waitQue 
     * We need take capacity into consideration. If the calculated passengers>10,  
     * then we update waitQue[currentFloor] = Passengers - 10, and forces passengers = 10
     * @throws ElevatorFullException 
     */
    public void move() {   	
    	
        if(direction == Direction.DOWN && currentFloor > 1) {
            currentFloor--; 
        }else if (currentFloor < 7) {
            currentFloor++;
        }

        if(currentFloor == Building.FLOORS){
            direction = Direction.DOWN;
        }
        else if(currentFloor == 1){
            direction = Direction.UP;
            //passengers = 0;
        }
        
        while(boardQue[currentFloor-1] != 0 ) {
        	boardQue[currentFloor-1]--;
        	passengers--;
        	building.floor(currentFloor).passengers++;
        }
        
        while(waitQue[currentFloor-1]!=0){
        	try {
        		boardPassenger(1);
        	} catch (ElevatorFullException e) {
        		break;
        	}
        	waitQue[currentFloor-1]--;
        }

        //if(boardQue[currentFloor-1] != 0 || waitQue[currentFloor-1]!=0){
        //   passengers = passengers - boardQue[currentFloor-1] + waitQue[currentFloor-1];
        //    boardQue[currentFloor-1] = 0;
            
        //    if(passengers<=CAPACITY){
        //    	waitQue[currentFloor-1] = 0;}
            
        //    else if (passengers>CAPACITY){
        //    	waitQue[currentFloor-1] = passengers - CAPACITY;
        //    	passengers = CAPACITY;
        //    }   
        //}
    }
    
    
    /**
     * Get currentFloor method, since currentFloor's access is private
     * @return the elevator's current floor number
     */
    public int currentFloor(){
    	return currentFloor;
    }
    
    /**
     * Get passengers method, since passengers's access is private
     * @return the number of passengers currently on the elevator
     */
    public int passengers(){
    	return passengers;
    }
    
    /** Adds to the Elevator a passenger destined for the indicated floor
     *  @param floor passenger will be boarded at that floor
     */
    public void boardPassenger(int floor) throws ElevatorFullException{

    	if (passengers >= CAPACITY) {
    		throw new ElevatorFullException();
   		}

    	
    	if (direction == Direction.UP) {
    		boardQue[floor-1]++;
    	}

    	passengers++;
    	
    }



}