/*
- Programmer: Francisco Rois Siso
- Date: 01/28/2017
- File Name: AccountHolder.java
- Lab 1
- ITMD510 Object-Oriented Application Development
*/

// import java.util library
import java.util.*;

/**
 * The class AccountHolder simulates an account holder, including its balance, annual interest rate, 
 * methods to access and modify the data, and methods to make a deposit or a withdrawal under certain conditions.
 * 
 * @author Francisco Rois Siso
 *
 */

public class AccountHolder {

	// variables declaration and initialization in some case
	private static double annualInterestRate = 0.04;
	private double balance;
	
	/**
	 * AccountHolder constructor without input parameters.
	 * Creates an object AccountHolder with balance 0.0.
	 */
	public AccountHolder(){
		//if no parameter is included, the balance is set to 0.0 by default
		this.balance = 0.0;
		//annualInterestRate = 0.04;
	}
	
	/**
	 * AccountHolder constructor with the initial balance as an input parameter.
	 * Creates an object AccountHolder with the initial balance indicated, if a condition is fulfilled: the initial balance is not negative.
	 * If the input is negative, the user is informed and asked to select a different amount or create an account with initial balance 0.0
	 * Once the initial balance is correct, the AccountHolder is created with that value
	 * 
	 * @param initialBalance The initial balance for the new AccountHolder, as a double
	 */
	public AccountHolder(double initialBalance){
		// if the initial balance is not negative, proceed normally
		if(initialBalance >= 0.0){
			this.balance = initialBalance;
		}
		// if the initial balance is negative, do the following:
		else{
			// show possible actions to proceed. Create and account with balance 0.0 or introduce a different balance
			System.out.println("Initial balance can not be negative. Please, select one of the following actions:");
			System.out.println("a. Create an account with balance 0.0");
			System.out.println("b. Introduce a different positive balance");
			
			// open scanner to receive input from the user
			Scanner scanner = new Scanner(System.in);
			String answer = scanner.next();
			
			// the user must select one of the possible actions to proceed. The user is asked again until he proceeds correctly
			while(!answer.equals("a") && !answer.equals("A") && !answer.equals("b") && !answer.equals("B")){
				System.out.println("Please, select one of the possible actions (a or b)");
				answer = scanner.next();
			}
			
			// a. Create an account with balance 0
			if(answer.equals("a")||answer.equals("A")){
				this.balance = 0.0;
			}
			
			// b. Introduce a different positive balance
			else {
				// ask for the new positive balance
				System.out.println("Please, introduce the new balance: ");
				double newBalance = scanner.nextDouble();
				// if the balance introduced continues to be negative, ask again until the value introduced is positive
				if(newBalance < 0.0){
					// boolean to indicate that the balance introduced by the user is negative
					boolean negativeBalance = true;
					System.out.println("Please, introduce a positive value for the balance: ");
					
					// the user is asked until the balance introduced is correct (non negative)
					while(negativeBalance){
						newBalance = scanner.nextDouble();
						// if the value introduced is negative, ask again
						if(newBalance < 0.0){
							System.out.println("Please, introduce a positive value for the balance: ");
						}
						// when the value is finally positive or 0, then exit the while loop and proceed to create the account
						else{
							// exit the loop by changing the value of the boolean
							negativeBalance = false;
						}
					}
				}
				//when the new balance introduced is positive or 0, create the new account with that balance
				this.balance = newBalance;
				
			}
		}
		// inform the user about the balance of the new account created
		System.out.println("The balance of your new account is: $"+balance);

	}
	
	/**
	 * setBalance method
	 * @param balance The new balance of the account, as a double
	 */
	public void setBalance(double balance){
		this.balance = balance;
	}
	
	/**
	 * getBalance method
	 * @return The balance of the account, as a double
	 */
	public double getBalance(){
		return this.balance;
	}
	
	/**
	 * Method to deposit a certain amount in the account
	 * @param depositAmount Amount to deposit in the account, as a double
	 */
	public void deposit(double depositAmount){
		// add the deposit amount to the current balance
		balance += depositAmount;
		
		// show the deposit amount
		System.out.println("\nDeposit:\t\t+$"+depositAmount);
		System.out.println("-------------------------------");

		// inform about the new balance after the deposit
		System.out.println("Your new balance is:\t $"+balance);
	}
	
	/**
	 * Method to withdraw a certain amount from the account.
	 * The withdrawal can only be done if the resulting balance is equal or higher than $100.
	 * If a withdrawal allows the account balance to drop below $500, 
	 * a transaction fee of $50 will be deducted from the current account holder’s balance.
	 * 
	 * @param withdrawalAmount Amount requested to withdraw from the account, as a double
	 */
	public void withdrawal(double withdrawalAmount){
		double withdrawal = withdrawalAmount;
		// open scanner to receive input from the user
		Scanner sc = new Scanner(System.in);
		
		// if the balance is lower than $100, the user is not allowed to withdraw, since the balance is already under the minimum
		if(balance <= 100.0){
			// inform the user
			System.out.println("You are not allowed to withdraw, your balance is below $100");
			// withdrawal is not permitted, therefore a withdrawal of $0 is performed
			withdrawal = 0.0;
		}
		// the withdrawal is done only if the resulting balance remains above 100
		else if(balance - withdrawal < 100.0){
			// ask for a correct withdrawal value
			System.out.println("Please, introduce a lower amount to withdraw so your balance remains over $100");
			//calculate maximum withdrawal
			double maximumWithdrawal = balance-100;
			System.out.println("The maximum amount you are currently allowed to withdraw is $"+maximumWithdrawal);
			// receive answer from user
			double newWithdrawal = sc.nextDouble();
			// check that the new amount is correct
			// if the amount is still higher than the maximum permitted, keep asking for a correct value
			if(newWithdrawal > maximumWithdrawal){
				// boolean to indicate that the amount to withdraw indicated by the user is not accepted
				boolean invalidWithdrawal = true;
				// the user is asked to introduce an amount until the value introduced is a valid withdrawal amount
				while(invalidWithdrawal){
					// user is informed of the maximum he is allowed to withdraw to stay under the required conditions
					System.out.println("The maximum amount you are currently allowed to withdraw is $"+maximumWithdrawal);
					System.out.println("Please, introduce a correct value: ");
					//receive amount from user
					newWithdrawal = sc.nextDouble();
					// when the amount is valid, get out of the loop by changing the value of the boolean
					if(newWithdrawal <= maximumWithdrawal){
						invalidWithdrawal = false;
					}
				}
			}
			// change the value of the withdrawal by the new correct value, after all the conditions are fulfilled
			withdrawal = newWithdrawal;			
			
		}
		
		// calculate how would it be the balance after the withdrawal
		double potentialBalance = balance - withdrawal;
		// if the balance is going to be lower than 500, the user will be charged with 50. 
		// The user is alerted of this fact and is offered a different action
		if(balance >= 500.0 && potentialBalance < 500.0){
			System.out.println("If you withdraw $"+withdrawal+" you will be charged with $50 extra because your balance will drop below $500.");
			System.out.println("Please, select an action: ");
			System.out.println("\na. Withdraw the selected amount, paying the $50 fee");
			System.out.println("b. Introduce a different amount to withdraw");
			
			// receive answer from user
			String action = sc.next();
			// the answer must be a, A, b or B
			while(!action.equals("a")&&!action.equals("A")&&!action.equals("b")&&!action.equals("B")){
				System.out.println("\nPlease, select an action to proceed:");
				System.out.println("a. Withdraw the selected amount, paying the $50 fee");
				System.out.println("b. Introduce a different amount to withdraw");
				action = sc.next();
			}
			// a. Withdraw the selected amount: simply continue the program
			// b. Introduce a different amount to withdraw
			if(action.equals("b")||action.equals("B")){
				double maximumWithdrawalWithoutFee = balance - 500;
				// It is shown the maximum amount to withdraw by the user if he does not want to be charged a $50 fee
				System.out.println("The maximum amount to withdraw to keep the balance at $500 is $" + maximumWithdrawalWithoutFee);
				// The user is asked again to introduce a quantity to withdraw, now that he is aware of the situation
				System.out.println("Please, select the amount that you want to withdraw");
				double newWithdrawalProbablyWithoutFee = sc.nextDouble();
				// the withdrawal amount is updated with the new specified amount
				withdrawal = newWithdrawalProbablyWithoutFee;
			}
		}
		
		// show final amount to withdraw
		System.out.println("\nWithdrawal amount:\t-$"+ withdrawal);
		// charge $50 fee if balance drops below $500
		if(balance >= 500.0 && (balance - withdrawal) < 500.0){
			balance -= 50.0;
			// show fee and total extracted
			System.out.println("Fee:\t\t\t-$50.0");
			System.out.println("-------------------------------");
			double totalExtracted = withdrawal+50.0;
			System.out.println("Total extrated:\t\t-$"+totalExtracted);
		}
		//update balance
		balance -= withdrawal;
		
		System.out.println("-------------------------------");
		// inform of the new balance
		System.out.println("Your new balance is:\t $"+balance);
	}
	
	/**
	 * monthlyInterest applies to the balance the equivalent monthly interest rate, calculated from the annual interest rate
	 */
	public void monthlyInterest(){
		balance += balance * (annualInterestRate / 12.0);
	}
	
	/**
	 * modifyMonthlyInterest allows to update the private static variable annualInterestRate, in the case that the conditions
	 * are correctly fulfilled (0.0<=newrate<=1.0)
	 * @param rateUpdate new annual interest rate, as a double
	 */
	public void modifyMonthlyInterest(double rateUpdate){
		// check that the new interest rate is equal or higher than 0.0 and lower or equal to 1.0
		if(rateUpdate < 0.0){
			System.out.println("The new annual interest rate can not be negative");
		}
		else if(rateUpdate > 1.0){
			System.out.println("The new annual interest can not be higher than 1.0");
		}
		// if the conditions are fulfilled, then update the value for the interest rate
		else{
			annualInterestRate = rateUpdate;
		}
	}
	
	/**
	 * getAnnualInterestRate get the private static variable annualInterestRate
	 */
	public double getAnnualInterestRate(){
		return annualInterestRate;
	}
	/**
	 * Method toString modified to show the balance in a certain format, as a String
	 */
	public String toString(){
		return String.format("$%.2f", balance);
	}
}
