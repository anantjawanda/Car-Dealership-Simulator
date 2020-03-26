import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

/** 
	Anant Jawanda 500894314
	This class contains a linkedlist of sales team members
*/

public class SalesTeam {
    
    private LinkedList<String> list = new LinkedList<String>();
    Random rand = new Random();
	private int randomPerson;

    public SalesTeam(){
        list.addLast("Drake");
        list.addLast("Jorja");
        list.addLast("Frank");
        list.addLast("Gambino");
        list.addLast("Travis");
    }

    public String returnSalesPerson() {         //returns random sales person from linkedlist
        int max = list.size();  
        int min = 0;
        this.randomPerson = rand.nextInt((max-min-1) + 1);      
        return list.get(randomPerson);
    }

    
    public String displayAll() {                //displays all members
        String team = "Team: ";
        ListIterator<String> iter2 = list.listIterator();
        while (iter2.hasNext()){
            team += iter2.next() + " ";  
        }
        return team + "\n";
    }

    public int numOfSalesPeople() {             //returns the total num of members
        return this.list.size();            
    }
    
    public String getNthSalesPerson(int num) {      //returns the nth sales person
        String name = "";
        int iter = 0;
        ListIterator<String> iterator = list.listIterator();
        while (iter != num){
            iter++;
            name = iterator.next();
        }
        return name;
    }

}