package cscie55.hw3;
/**
 * This exception is thrown by elevator when the elevator's maximum capacity is reached
 * It is caught by the floor the passengers are trying to board from. If the maximum
 * capacity is reached, leftover passengers are retained on the floor and the elevator leaves
 * @author Yuling Xiong (Alice)
 *
 */
public class ElevatorFullException extends Exception {

}
