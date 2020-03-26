import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/** 
	Anant Jawanda	500894314
	The objects of this class hold information about the buying or returning of a car.
*/


public class Transaction {

    Calendar calendar;
    private int transactionID;
    String salesP;
    private String type;
    private Double salePrice;
    Car carRef = new Car();
    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd YYYY");

    public Transaction(){
        this.transactionID = 0;
    }

    /** 
		Constructor that initializes the instance variables
        @param transactionID (id)
		@param date (uses Calendar class)
		@param car (car object)
		@param salesPerson (name of salesPerson)
		@param type (BUY or RET)
		@param salePrice (vehicle price)
	*/

    public Transaction(int transactionID, Calendar date, Car car, String salesP, String type, Double salePrice) {
        this.transactionID = transactionID;
        carRef = car;
        this.salesP = salesP;
        this.type = type;
        this.salePrice = salePrice;    
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd YYYY");
        this.calendar = date;
    }
    //Getter methods
    public String getSP(){
        return salesP;}

    public String getType(){
        return this.type;}

    public int getMonth() {
        return calendar.get(Calendar.MONTH);}

    public String returnMonth (int month){
        calendar.set(Calendar.MONTH, month);
        String theDate = sdf.format(calendar.getTime());
        return theDate.substring(0,3);}

    public int getID(){
        return this.transactionID;}

    public double getSalesPrice(){
        return this.salePrice;}

    //displays the transaction
    public String display(){
        String transactionList = "ID: " + transactionID + " " + (sdf.format(calendar.getTime())) + " " + type + " SalesPerson: " + salesP + " Car: " + carRef.display();
        return transactionList;
    }

    //returns the car object connected to the transaction
    public Car transactionCar(){
        return this.carRef;
    }

}