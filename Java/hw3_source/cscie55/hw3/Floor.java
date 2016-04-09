package cscie55.hw3;

import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

import cscie55.hw3.Building;

/**
 * Floor class: Representing each floor in the building, and each floor the Elevator can visit.
 * @author Yuling (Alice) Xiong 
 * @version 1.0 March 11, 2016
 */
public class Floor {
	/**
	 * @param building declare the building that this floor belongs to
	 * @param floorNumber declare the associated floorNumber of this Floor
	 * @param residentSet declare the set of passengers who are resident on the Floor, not waiting for the elevator
	 * @param goingUpSet declare the list of passengers who are waiting for an elevator going up. It can keep the sequence of new added passengers, which is not available in Set. 
	 * @param goingDownSet declare the list of passengers who are waiting for an elevator going down. It can keep the sequence of new added passengers.
	 */
	private Building building;
	private int floorNumber;
	public Set<Passenger> residentSet= new HashSet<Passenger> ();
	public List<Passenger> goingUpSet= new ArrayList<Passenger> ();
	public List<Passenger> goingDownSet= new ArrayList<Passenger> ();
	
	
	/**
	 * The Floor constructor to initialize the building and floorNumber associated with
	 * @param building
	 * @param floorNumber
	 */
	public Floor(Building building, int floorNumber){
		this.building = building;
		this.floorNumber = floorNumber;

	}


	/**
	 * waitForElevator method: each time the passengers press this button, 
	 * it will update those three collections of passengers on that floor
	 */
	public void waitForElevator(Passenger passenger, int destinationFloor){
		if(residentSet.contains(passenger)) {
			residentSet.remove(passenger);
		}
		
		passenger.setDestinationFloor(destinationFloor); 
		if(destinationFloor > floorNumber){
			goingUpSet.add(passenger);
			//System.out.println("Floor goingUpSet:"+goingUpSet);
			//System.out.println("Floor residentSet:"+residentSet);
		} else if (destinationFloor < floorNumber){
			goingDownSet.add(passenger);
		} else {
			passenger.setDestinationFloor(-1); 
			residentSet.add(passenger);
		}
	}
	
	/**
	 * isResident method:Check whether this passenger is resident on the Floor
	 * @param passenger
	 * @return true if the passenger is resident on the Floor, false otherwise.
	 */
	public boolean isResident(Passenger passenger){
		if(passenger.destinationFloor()==-1){
			return true;
		}
		return false;
	}
	
	/**
	 * enterGroundFloor method: add a passenger to the Floor's residents. 
	 * @param passenger
	 */
	public void enterGroundFloor(Passenger passenger){
		passenger.setDestinationFloor(-1); 
		residentSet.add(passenger);
		
	}


}
