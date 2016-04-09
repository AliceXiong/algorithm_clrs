package cscie55.hw3;

//import java.util.List;
//import java.util.ArrayList;

import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
import cscie55.hw3.Building;
import cscie55.hw3.ElevatorFullException;
import cscie55.hw3.Elevator.Direction;

/**
 * Elevator class: move up and down, and talk with Building
 * @author Yuling (Alice) Xiong 
 * @version 1.0 March 1, 2016
 */
public class Elevator {
	/**
	 * @param currentFloor declare and initialize the currentFloor 
	 * @param Direction declare Direction object with two options
	 * @param CAPACITY declare and initialize the capacity of this elevator
	 * @param building declare the building object
	 * @param passengerSet declare passengerSet collection to store all the passenger objects in the HashSet
	 */
    private int currentFloor = 1;
    public enum Direction {UP, DOWN}; 
    Direction direction = Direction.UP;
    public static final int CAPACITY = 10; 
    private Building building;
    private Set<Passenger> passengerSet;
    
    
    /**
     * Elevator constructor: help initialize the building object and passengerSet
     * @param building
     * @param passengerSet
     */
    public Elevator (Building building, Set<Passenger> passengerSet) {
        this.building = building;
        this.passengerSet = new HashSet<Passenger> ();
    }
    
    
    /**
     * goingUp method
     * @return true if the elevator is going up, false otherwise
     */
    public boolean goingUp(){
    	//boolean check;
    	if(direction == Direction.UP) {
    		return true;
    	} 
    	return false;
    }
    
    /**
     * goingDown method
     * @return true if the elevator is going down, false otherwise
     */
    public boolean goingDown(){
    	//boolean check;
    	if(direction == Direction.DOWN) {
    		return true;
    	} 
    	return false;
    }
    
    
    /** 
     * move method:
     * Modifies the direction of travel, if the ground floor or top floor has been reached direction move up or down
     * passengerSet collection in the elevator will be updated according to those 3 collections in the floor class
     *    - Disembark passengers from passengerSet to the floor's residentSet before load in passengers. 
     * 	  - Remove passengers from goingUpSet/goingDownSet in the floor, and move them to the elevator's passengerSet  
     * We need take capacity into consideration. If the loaded passengers>10,  then we try catch to throw exception.
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
        }
        
        //System.out.println("currentFloor:"+currentFloor);
        
        //Disembark passengers to the floor before load in passengers. 
        Iterator<Passenger> passengerItr = passengerSet.iterator();
        System.out.println("passengerItr.hasNext():"+passengerItr.hasNext());
        	while(passengerItr.hasNext()){
        		Passenger p = passengerItr.next();
        		//System.out.println("passengerItr.next():"+p);
        		//System.out.println("stop passengerItr.next().destinationFloor():"+p.destinationFloor());
        		
        		if(p.destinationFloor()==currentFloor){
        			//System.out.println("disembark1-------passengerItr.next():"+p);
        			passengerItr.remove();
        			p.setDestinationFloor(-1);
        			//System.out.println("disembark2-------passengerItr.next():"+p);
        			p.setCurrentFloor(currentFloor);
        			
        			building.floor(currentFloor).residentSet.add(p);
        		}
        	}

        
        //delete passengers in the goingUpSet/goingDownSet
        //and move them to the elevator's passengerSet     
        if(goingUp()){
        	//System.out.println("--------building.floor(currentFloor).goingUpSet:"+building.floor(currentFloor).goingUpSet);
        	Iterator<Passenger> goingUpItr = building.floor(currentFloor).goingUpSet.iterator();
        	//System.out.println("--------goingUpItr.hasNext():"+goingUpItr.hasNext());
        	while(goingUpItr.hasNext()){
        		try{
        			boardPassenger(goingUpItr.next());
        			goingUpItr.remove();
        		} catch (ElevatorFullException ex){
        			break;
        		}
        	}
        } else if(goingDown()){
        	Iterator<Passenger> goingDownItr = building.floor(currentFloor).goingDownSet.iterator();
        	while(goingDownItr.hasNext()){
        		try{
        			boardPassenger(goingDownItr.next());
        			goingDownItr.remove();
        		} catch(ElevatorFullException ex){
        			break;
        		}
        	}
        }

    }
    
    /**
     * boardPassenger method: add passengers to the elevator's passengerSet on the condition that it is less than the capacity.
     * @param passenger
     * @throws ElevatorFullException
     */
    public void boardPassenger(Passenger passenger) throws ElevatorFullException{

    	if (passengerSet.size() >= CAPACITY) {
    		throw new ElevatorFullException();
   		}
    	passengerSet.add(passenger);
    	
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
    public Set<Passenger> passengers(){
    	return passengerSet;
    }
}

    


