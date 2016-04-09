package cscie55.hw1.elevatortest;

import cscie55.hw1.elevator.*;

/**
 * Create test version for Elevator class to see whether it functions well
 * @author Yuling (Alice) Xiong 
 * @version 1.0 Feb 15, 2015
 */
public class Elevatortest {

    public static void main(String[] args) {
        /**Create an Elevator object*/
        Elevator myElevator = new Elevator();

        /**Board two passengers for the 3rd floor*/
        myElevator.boardPassenger(3);
        myElevator.boardPassenger(3);
        /**Board one passengers for the 5th floor*/
        myElevator.boardPassenger(5);

        /**Move the Elevator from the ground floor to the top floor,
          *then back to the ground floor 2*NUMBFLOORS-1 = 13*/
        for(int i = 1; i <= 2*myElevator.NUMBFLOORS-1; i++){
            System.out.println(myElevator);
            myElevator.move();
        }
   
    }

}
