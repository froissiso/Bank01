/* 
Program to simulate an account holder and actions related to the account.
The user is shown a menu of actions and can select to create an account with a certain initial balance, 
make deposits or withdrawals, show examples of not fulfillment of certain withdrawal conditions, change the annual interest rate,
add interests and see results over a period of 12 months, apply the rate to a month or execute several actions in a row.
      
- Programmer: Francisco Rois Siso
- Date: 01/28/2017
- Source File Name: AccountHolderTest.java
- Lab 1
- ITMD510 Object-Oriented Application Development

 */

// import InputMismatchException to catch the exception when the user types something incorrect
import java.util.InputMismatchException;
// import Scanner to receive input from the user
import java.util.Scanner;
// import libraries for date and time display, for lab submission purposes
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * The class AccountHolderTest displays a menu to the user to select an action between 10 possibilities.
 * All of the actions use the class AccountHolder.java and test its functionalities.
 * The actions available are: 
 * 1. Create an account, 
 * 2. Make a deposit,  
 * 3. Make a withdrawal, 
 * 4. Show me what happens when I try a withdrawal that drops my balance below $100,  
 * 5. Initially set the interest to 4%, 
 * 6. Show interest being added over a period of 12 months, 
 * 7. Update the interest amount to 5%, 
 * 8. Display the new balance given the new interest update, 
 * 9. Execute all actions from 1 to 8, one after the other, 
 * 10. Exit program 
 * 
 * @author Francisco Rois Siso
 *
 */
public class AccountHolderTest {
	
	// static AccountHolder object, which will be created and modified by the user
	static AccountHolder account;
	// boolean to indicate that the account has already been created. This boolean is used if the user 
	//tries to make a deposit or a withdrawal without having before introduced an initial balance amount
	static boolean accountCreated = false;
	// Scanner variable sc declaration
	static Scanner sc;
	
	public static void main (String args[]){
		
		// display the Welcome Menu and the possible actions that the user can select to perform
		displayWelcomeMenu();
		// show date and time for lab submission purposes
		showDateAndTime();
		
		// declaration of the String that will store the number of the action selected by the user
		String selection;
		// boolean to indicate that the user has finished and therefore he wants to exit the program
		// It will be used in the main while loop
		boolean finish = false;
		// scan input from the user
		sc = new Scanner(System.in);

		//the program will display again the Main Menu to the user after every action, 
		// until the user has finished and therefore selects to exit the program (action 10)
		while(!finish){
			// scan input from the user
			selection = sc.next();
			// show the selection if is one of the available ones
			if(selection.equals("1")||selection.equals("2")||selection.equals("3")||selection.equals("4")||selection.equals("5")||selection.equals("6")||selection.equals("7")||selection.equals("8")||selection.equals("9")||selection.equals("10")){			
				System.out.println("\nYOUR SELECTION: "+selection+"\n");
				}
			
			try{
				// perform a different action depending on the selection made by the user
				switch (selection){
				// 1. Create an account
				case "1": 
					createAnAccount();
					// show date and time for lab submission purposes
					showDateAndTime();
					// display again the Main Menu
					displayWelcomeMenu();
					break;
				// 2. Make a deposit
				case "2":			
					makeADeposit();
					// show date and time for lab submission purposes
					showDateAndTime();
					// display again the Main Menu
					displayWelcomeMenu();
					break;
				// 3. Make a withdrawal
				case "3": 
					makeAWithdrawal();
					// show date and time for lab submission purposes
					showDateAndTime();
					// display again the Main Menu
					displayWelcomeMenu();
					break;
				// 4. Show me what happens when I try a withdrawal that drops my balance below $100
				case "4": 
					example100Withdrawal();
					// show date and time for lab submission purposes
					showDateAndTime();
					// display again the Main Menu
					displayWelcomeMenu();
					break;
				// 5. Initially set the interest to 4%
				case "5": 
					updateAnnualInterestRate(0.04);
					// show date and time for lab submission purposes
					showDateAndTime();
					// display again the Main Menu
					displayWelcomeMenu();
					break;
				// 6. Show interest being added over a period of 12 months
				case "6":
					showInterestAddedByMonth();
					// show date and time for lab submission purposes
					showDateAndTime();
					// display again the Main Menu
					displayWelcomeMenu();
					break;
				// 7. Update the interest amount to 5%
				case "7":
					updateAnnualInterestRate(0.05);
					// show date and time for lab submission purposes
					showDateAndTime();
					// display again the Main Menu
					displayWelcomeMenu();
					break;
				// 8. Display the new balance given the new interest update
				case "8":
					displayBalanceNextMonthWithInterest();
					// show date and time for lab submission purposes
					showDateAndTime();
					// display again the Main Menu
					displayWelcomeMenu();
					break;
				// 9. Execute all actions from 1 to 8, one after the other
				case "9":
					executeActions1to8();
					// show date and time for lab submission purposes
					showDateAndTime();
					// display again the Main Menu
					displayWelcomeMenu();
					break;
				// 10. Exit program
				case "10":
					// change boolean to true in order to exit the while loop
					finish = true;
					exitProgram();
					break;
				default:
					break;
				}
			}
			// catch InputMismatchException in the case that the user types something unexpected
			catch(InputMismatchException e){
				// the user is asked to try again the action and the Main Menu is displayed
				System.out.println("\nThe input introduced is not valid. Please, try again.");
				displayWelcomeMenu();
			}
			// catch any other kind of exception in the case that it happens
			catch(Exception e){
				// The user is informed, asked to try again the action and the Main Menu is displayed
				System.out.println("\nAn unexpected exception occurred. Please, try again.");
				displayWelcomeMenu();
			}
		}
	}
	
	/**
	 * displayedWelcomeMenu displays the Menu Menu so the user can select one of the available actions
	 */
	static void displayWelcomeMenu(){
		System.out.println("\n\t\t\t>> WELCOME TO THE IIT BANK SYSTEM <<");
		System.out.println(" ---------------------------------------------------------------------------------------");

		//MENU
		System.out.print("| What can we do for you today?");
		System.out.print("\t\t\t\t\t\t\t\t");
		System.out.println("|");
		System.out.print("| Please, select one of the following options:");
		System.out.print("\t\t\t\t\t\t");
		System.out.println("|");
		System.out.print("|\t\t\t\t\t\t\t\t\t\t\t");
		System.out.println("|");
		System.out.print("|    1. Create an account");
		System.out.print("\t\t\t\t\t\t\t\t");
		System.out.println("|");
		System.out.print("|    2. Make a deposit");
		System.out.print("\t\t\t\t\t\t\t\t\t");
		System.out.println("|");
		System.out.print("|    3. Make a withdrawal");
		System.out.print("\t\t\t\t\t\t\t\t");
		System.out.println("|");
		System.out.print("|    4. Show me what happens when I try a withdrawal that drops my balance below $100");
		System.out.print("\t");
		System.out.println("|");
		System.out.print("|    5. Initially set the interest to 4%");
		System.out.print("\t\t\t\t\t\t");
		System.out.println("|");
		System.out.print("|    6. Show interest being added over a period of 12 months");
		System.out.print("\t\t\t\t");
		System.out.println("|");
		System.out.print("|    7. Update the interest amount to 5%");
		System.out.print("\t\t\t\t\t\t");
		System.out.println("|");
		System.out.print("|    8. Display the new balance given the new interest update");
		System.out.print("\t\t\t\t");
		System.out.println("|");
		System.out.print("| -> 9. Execute all actions from 1 to 8, one after the other");
		System.out.print("\t\t\t\t");
		System.out.println("|");
		System.out.print("|    10. Exit program");
		System.out.print("\t\t\t\t\t\t\t\t\t");
		System.out.println("|");

		System.out.println(" ---------------------------------------------------------------------------------------");
	}
	
	/**
	 * createAnAccount asks the user for an initial balance value and creates the account if the value is accepted
	 */
	static void createAnAccount(){
		// ask for an initial balance amount
		System.out.println("Please, introduce the initial balance for the account: ");
		// get the input amount specified by the user
		double initialAmount = sc.nextDouble();
		// try to create a new AccountHolder object with the initial balance amount specified, by calling the constructor
		account = new AccountHolder(initialAmount);
		// boolean changed to true to indicate that the account has been created and deposits or withdrawals can be done
		accountCreated = true;
	}
	
	/**
	 * makeDeposit sums a certain quantity to the current account's balance
	 */
	static void makeADeposit(){
		// if an account has not been created yet, the user is informed and the deposit process is stopped
		if(accountCreated == false){
			System.out.println("You need to create an account first in order to make a deposit.");
		}
		// else the user is asked to introduce the amount to deposit and the deposit is performed
		else{
			System.out.println("Please, introduce the amount to deposit");
			double depositAmount = sc.nextDouble();
			account.deposit(depositAmount);
		}
	}
	
	/**
	 * makeAWithdrawal allows the user to make a withdraw
	 */
	static void makeAWithdrawal(){
		// if an account has not been created yet, the user is informed and the withdrawal process is stopped
		if(accountCreated == false){
			System.out.println("You need to create an account first in order to make a withdrawal.");
		}
		// else, the user is asked for the amount to withdraw and the process is then initialized
		else{
			System.out.println("Please, introduce the amount to withdraw");
			double withdrawalAmount = sc.nextDouble();
			account.withdrawal(withdrawalAmount);
		}
	}
	
	/**
	 * example100Withdrawal shows an example to the user in which an account holder with a balance of $150 tries to perform
	 * a withdrawal of $80, so the balance would be below $100. This action is therefore not permitted and the user is asked
	 * to introduce a suitable amount to proceed with the withdrawal. This is a simulation, therefore the balances shown in
	 * this example are not used by other methods outside this one.
	 */
	static void example100Withdrawal(){
		// fictional balance to illustrate the example
		double fictionalBalance = 150.0;
		// fictional intended withdrawal to illustrate the example
		double intendedWithdrawal = 80.0;
		// the user is informed about the example
		System.out.println("Next, we will show you an example of a denied withdrawal, when the resulting balance will go below $100:");
		System.out.println("\n\t Fictional balance:\t$"+fictionalBalance);
		System.out.println("\t Intended withdrawal:\t-$"+intendedWithdrawal+"\n");
		System.out.println(">> PLEASE, TYPE ANYTHING TO PROCEED WITH THE EXAMPLE <<\n");
		sc.next();
		
		// an AccountHolder object is created with the fictional initial balance to illustrate the example
		AccountHolder example_account = new AccountHolder(fictionalBalance);
		// the fictional withdrawn process is started
		example_account.withdrawal(intendedWithdrawal);
	}
	
	/**
	 * updateAnnualInterestRate allows to update the value of the static variable annualInterestRate, defined in AccountHolder.java
	 * @param newAnnualInterest The new value for the annual interest rate, as a double
	 */
	static void updateAnnualInterestRate(double newAnnualInterest){
		// the user is informed of the previous and new values, and the static variable value is updated
		System.out.println("Previous annual interest rate: \t\t\t"+(account.getAnnualInterestRate())*100.0+"%");
		//AccountHolder.annualInterestRate = newAnnualInterest;
		account.modifyMonthlyInterest(newAnnualInterest);
		System.out.println("The annual interest rate is now set to: \t"+(account.getAnnualInterestRate())*100.0+"%");
	}
	
	/**
	 * showInterestAddedByMonth shows to the user the balances resulting from the addition of the interests month by month,
	 * during a period of 12 months. The current value of the annual interest rate is applied
	 */
	static void showInterestAddedByMonth(){
		//if the account has not been created yet by using the action 1 on the menu, it is required to the user to continue
		if(account == null){
			System.out.println("\nPlease, select option 1 first and create an account in our bank, then you will be able to see the interests applied.");
		}
		else{
			
			// the user is informed of the annual interest rate that will be applied, and then the balances for 12 months are displayed
			System.out.println("\nHere you have the balances by month for a period of one year.");
			System.out.println("Interest applied: "+(account.getAnnualInterestRate()*100.0)+"%\n");
			// when the AccountHolder is added to the output String, the method toString is automatically called
			System.out.println("Base balance\t"+account+"\n");
			System.out.print("Month 1");
			account.monthlyInterest();
			System.out.println("\t\t" + account);
			System.out.print("Month 2");
			account.monthlyInterest();
			System.out.println("\t\t" + account);
			System.out.print("Month 3");
			account.monthlyInterest();
			System.out.println("\t\t" + account);
			System.out.print("Month 4");
			account.monthlyInterest();
			System.out.println("\t\t" + account);
			System.out.print("Month 5");
			account.monthlyInterest();
			System.out.println("\t\t" + account);
			System.out.print("Month 6");
			account.monthlyInterest();
			System.out.println("\t\t" + account);
			System.out.print("Month 7");
			account.monthlyInterest();
			System.out.println("\t\t" + account);
			System.out.print("Month 8");
			account.monthlyInterest();
			System.out.println("\t\t" + account);
			System.out.print("Month 9");
			account.monthlyInterest();
			System.out.println("\t\t" + account);
			System.out.print("Month 10");
			account.monthlyInterest();
			System.out.println("\t" + account);
			System.out.print("Month 11");
			account.monthlyInterest();
			System.out.println("\t" + account);
			System.out.print("Month 12");
			account.monthlyInterest();
			System.out.println("\t" + account);
		}
	}
	
	/**
	 * displayBalanceNextMonthWithInterest applies the current monthly interest rate to the following month, calculated from
	 * the current annual interest rate
	 */
	static void displayBalanceNextMonthWithInterest(){
		//apply the current monthly interest rate, calculated from the current annual interest rate
		account.monthlyInterest();
		// information is shown
		System.out.println("\nThis is the balance for the new month after applying the current annual interest rate ("+account.getAnnualInterestRate()*100.0+"%):");
		System.out.println(account);
	}
	
	/**
	 * executeActions1to8 is a method that executes all the actions of the Main Menu from 1 to 8, one after the other
	 */
	static void executeActions1to8(){
		System.out.println(">>>>>\tACTION 1: Create an account\t<<<<<\n");
		createAnAccount();
		System.out.println("\n\t=========================");
		System.out.println("\n>>>>>\tACTION 2: Make a deposit\t<<<<<\n");
		makeADeposit();
		System.out.println("\n\t=========================");
		System.out.println("\n>>>>>\tACTION 3: Make a withdrawal\t<<<<<\n");
		makeAWithdrawal();
		System.out.println("\n\t=========================");
		System.out.println("\n>>>>>\tACTION 4: Show me what happens when I try a withdrawal that drops my balance below $100\t<<<<<\n");
		example100Withdrawal();
		System.out.println("\n\t=========================");
		System.out.println("\n>>>>>\tACTION 5: Initially set the interest to 4%\t<<<<<\n");
		updateAnnualInterestRate(0.04);
		System.out.println("\n\t=========================");
		System.out.println("\n>>>>>\tACTION 6: Show interest being added over a period of 12 months\t<<<<<\n");
		showInterestAddedByMonth();
		System.out.println("\n\t=========================");
		System.out.println("\n>>>>>\tACTION 7: Update the interest amount to 5%\t<<<<<\n");
		updateAnnualInterestRate(0.05);
		System.out.println("\n\t=========================");
		System.out.println("\n>>>>>\tACTION 8: Display the new balance given the new interest update\t<<<<<\n");
		displayBalanceNextMonthWithInterest();
	}
	
	/**
	 * exitProgram shows a goodbye message when the user chooses to exit the program
	 */
	static void exitProgram(){
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("THANK YOU, WE HOPE THAT YOU COME BACK SOON WITH ALL YOUR MONEY AND YOUR RELATIVES'");
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
	}
	
	/**
	 * showDateAndTime allows to show the current date and time for lab submission purposes
	 */
	static void showDateAndTime(){
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		System.out.println("\nCur dt=" + timeStamp + "\nProgrammed by Francisco Rois Siso\n");
	}
}
