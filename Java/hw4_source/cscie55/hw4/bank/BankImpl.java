package cscie55.hw4.bank;


import java.util.HashMap;
import java.util.Map;
/**
 * BankImpl class: Implements Bank Interface to simulate a Bank, which manages a set of Accounts
 * @author Yuling (Alice) Xiong 
 * @version 1.0 March 24, 2016
 */

public class BankImpl implements Bank{
	/**
	 * @param numberOfAccounts: declare number of accounts within this bank
	 * @param accountMap: declare accounts collection to store all the accounts object in this hashmap
	 */
	private int numberOfAccounts;
	private  Map<Integer,Account> accountMap = new HashMap<Integer,Account>();
	
	
	/**
	 * addAccount method: add account object to the accountMap collection
	 * @param account
	 * @throws DuplicateAccountException
	 */
	public void addAccount(Account account) throws DuplicateAccountException {
		if(accountMap.containsKey(account.id())){
			throw new DuplicateAccountException(account.id());
		} else {
			accountMap.put(account.id(),account);
		}
	}
	

	//Experiment with synchronizing access to objects
	/**
	 * transferWithoutLocking method: withdraw money from account with fromId to account with toId.
	 * Just calls withdraw on one account and deposit it on the other without doing any synchronization.
	 * @param fromId
	 * @param toId
	 * @param amount
	 * @throws InsufficientFundsException
	 */
	public void transferWithoutLocking(int fromId, int toId, long amount) 
			throws InsufficientFundsException{
		accountMap.get(fromId).withdraw(amount);
		accountMap.get(toId).deposit(amount);
		
	}
	
	/**
	 * transferLockingBank method: withdraw money from account with fromId to account with toId.
	 * Does the transfer while synchronizing on the Bank object. This means that only one thread can do a transfer at any given moment.
	 * @param fromId
	 * @param toId
	 * @param amount
	 * @throws InsufficientFundsException
	 */
	public synchronized void transferLockingBank(int fromId, int toId, long amount) 
			throws InsufficientFundsException{
		accountMap.get(fromId).withdraw(amount);
		accountMap.get(toId).deposit(amount);
	}
	
	/**
	 * transferLockingAccounts method: withdraw money from account with fromId to account with toId.
	 * Does the transfer, locking just the two affected accounts. This provide for greater concurrency. 
	 * @param fromId
	 * @param toId
	 * @param amount
	 * @throws InsufficientFundsException
	 */
	public void transferLockingAccounts(int fromId, int toId, long amount) 
			throws InsufficientFundsException{		
		Account fromAccount = accountMap.get(fromId);
		Account toAccount = accountMap.get(toId);
			synchronized(fromAccount){
				fromAccount.withdraw(amount);
			}

			synchronized (toAccount){
				toAccount.deposit(amount);
			}

	}
	
	/**
	 * totalBalances method: sum all the accounts' balances together
	 * @return total Balances for all the accounts in this bank
	 */
	public long totalBalances(){	
		long totalBalances = 0;
		for (Account val : accountMap.values()){
			totalBalances += val.balance();
		}	
		return totalBalances;
	}
	
	/**
	 * numberOfAccounts method: calculate size of accountMap
	 * @return number Of Accounts within this bank
	 */
	public int numberOfAccounts(){
		numberOfAccounts = accountMap.size();
		return numberOfAccounts;
	}
	
}
