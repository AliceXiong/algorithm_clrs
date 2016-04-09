package cscie55.hw4.bank;

/**
 * AccountImpl class: Implements Account Inteface
 * @author Yuling (Alice) Xiong 
 * @version 1.0 March 24, 2016
 */

public class AccountImpl implements Account{
	/**
	 * @param id: declare id of this account
	 * @param balance: declare balance in this account
	 */
	private int id;
	private long balance;
	
	/**
	 * AccountImpl constructor: help initialize the id of this account
	 * @param id
	 */
	public AccountImpl(int id){
		this.id = id;
	}
	
	/**
	 * getID method
	 * @return id of this account
	 */
	public int id() {
		return id;
	}
	
	/**
	 * getBalance method
	 * @return balance of this account
	 */
	public long balance(){
		return balance;
	}
	
	/**
	 * deposit method: deposit nonnegative money into this account
	 * @param amount
	 */

	@Override
	public void deposit(long amount) {
		if (amount > 0) {
			balance += amount;
		} else {
			throw new IllegalArgumentException();
		}
		
	}
	/**
	 * withdraw method: withdraw money from this account
	 * @param amount
	 * @throws InsufficientFundsException
	 */
	public void withdraw(long amount) throws InsufficientFundsException {
		if(amount <= balance && amount > 0){
			balance -=  amount;
		} else if(amount > balance){
			throw new InsufficientFundsException(this, amount);
		} else{
			throw new IllegalArgumentException();
		}
		
	}


}

	

