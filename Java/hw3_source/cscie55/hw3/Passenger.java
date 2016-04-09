package cscie55.hw3;
/**
 * Passenger class: records a passenger's current floor and destination floor.
 * @author Yuling (Alice) Xiong 
 * @version 1.0 March 1, 2016
 */
public class Passenger {
	/**
	 * @param id declare a passenger's id
	 * @param currentFloor declare a passenger's currentFloor and initialize it as 1
	 * @param destinationFloor declare a passenger's destinationFloor and initialize it as -1, which means this passenger is a resident on the floor.
	 */
	private int id;
	private int currentFloor = 1;
	private int destinationFloor = -1;
	
	/**
	 * Passenger constructor: help initialize the id of this passenger
	 * @param id
	 */
	public Passenger(int id){
		this.id = id;
	}
	
	/**
	 * currentFloor method
	 * @return current Floor of this passenger
	 */
	public int currentFloor(){
		return currentFloor;
	}
	
	/**
	 * destinationFloor method
	 * @return destination Floor of this passenger
	 */
	public int destinationFloor(){
		return destinationFloor;
	}
	
	/**
	 * setDestinationFloor method: assist with updating the destinationFloor of the passenger
	 * @param floor
	 */
	public void setDestinationFloor(int floor){
		destinationFloor = floor;
	}
	
	/**
	 * setCurrentFloor method: assist with updating the currentFloor of the passenger
	 * @param floor
	 */
	public void setCurrentFloor(int floor){
		currentFloor = floor;
	}
	
	/**
	 * waitForElevator method:Sets the Passenger's destination floor to newDestinationFloor.
	 * @param newDestinationFloor
	 */
	public void waitForElevator(int newDestinationFloor){
		destinationFloor = newDestinationFloor;
	}
	
	/**
	 * boardElevator method: Set the passenger's current floor to be undefined
	 */
	public void boardElevator(){
		currentFloor = -1;
	}
	
	/**
	 * arrive method:The Passenger is on an elevator and arrives at his or her destination. 
	 * Copy the value of the destination floor to the current floor, and set the destination floor to be undefined.
	 */
	public void arrive(){
		currentFloor = destinationFloor;
		destinationFloor = -1;
	}
	
	/**
	 * toString method: assist with debugging.
	 */
	public String toString(){
		return "Passenger ID"+id+", from floor "+currentFloor+" land in "+destinationFloor+".\n";
	}
}
