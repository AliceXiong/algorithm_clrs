package cscie55.hw2;
/**
 * Floor class: Representing each floor in the building, and each floor the Elevator can visit.
 * @author Yuling (Alice) Xiong 
 * @version 1.0 March 1, 2016
 */
public class Floor {
	/**
	 * @param building declare the building that this floor belongs to
	 * @param floorNumber declare the associated floorNumber of this Floor
	 * @param passengers declare 
	 */
	private Building building;
	private int floorNumber;
	public int passengers;
	
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
	 * passengersWaiting method: 
	 * @return the number of passengers on the floor who are waiting for the elevator
	 */
	
	public int passengersWaiting(){
		return building.elevator().getwaitQue(floorNumber);
	}

	/**
	 * waitForElevator method: each time the passengers press this button, 
	 * it will update the number of passengers waited on that floor
	 */
	public void waitForElevator(){
		building.elevator().updatewaitQue(floorNumber-1);
		passengers++;
	}
	


}
