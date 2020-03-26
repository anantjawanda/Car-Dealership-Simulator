import java.util.Random;
import java.util.ArrayList;
import java.util.Calendar;

/** 
	Anant Jawanda	500894314
	The Accounting system class holds an arraylist of Transaction objects
*/


public class AccountingSystem {
 
    Random rand1 = new Random();            //randomizer
	private int randomID;
    ArrayList<Transaction> accountingList;
    Transaction transactionOBJ;

    public AccountingSystem() {
        this.accountingList = new ArrayList<Transaction>();
    }

    /** 
		Constructor that initializes the instance variables
		@param date (uses Calendar class)
		@param car (car object)
		@param salesPerson (name of salesPerson)
		@param type (BUY or RET)
		@param salesPrice (vehicle price)
	*/
    public String add(Calendar date, Car car, String salesPerson, String type, double salePrice) {
		this.randomID = rand1.nextInt((99-1)+1) + 1;    //random id from 1-99
        transactionOBJ = new Transaction(randomID, date, car, salesPerson, type, salePrice);
        accountingList.add(transactionOBJ);
        String receipt = "ID: " + randomID + " " + transactionOBJ.display();
        return receipt;

    }

    //returns the randomID
    public int getRandomID(){
        return this.randomID;
    }

    //returns the transaction object matched with the inputted id
    public Transaction getTransaction(int id){
        Transaction ref = new Transaction();
        for (Transaction other: accountingList){
            if (other.getID() == id){
                ref = other;
            } else { 
                return null;
            }
        }
        return ref;
    }
 

}