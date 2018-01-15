import java.util.ArrayList;
import java.lang.Math;
public class BlackjackOpponent extends Blackjack {
    //first working edition
    public boolean win=true;
    public String name;
    public BlackjackOpponent(String n) {
	super();
	deal();
	bet+=(int) (Math.random()*500);
	name=n;
	
    }
    public BlackjackOpponent(int i) {
	super();
	deal();
        System.out.println("dealer card:"+hand.get(0));
	bet+=(int) (Math.random()*1000);
	name="dealer";
	
    }
    public int sum() {
	int sum=0;//return value
	for(int i=0; i<deckLength; i++) {//goes thru each card!
	    if(hand.get(i).getValue()>10){//if the value of the card is greater than 10, than downcast it
		sum+=10;
	    }
	    
	    else if(hand.get(i).getValue()==1) {
		if (sum>10) {
		    sum+=1;
		}
		else {
		    sum+=11;
		}
	    }
	    else {
		sum+=hand.get(i).getValue();
	    }
	}
	//diag
	//System.out.println("prog run fin");
	return sum;
	
    }
    public void AI() {
	//System.out.println("opponent hand:"+hand);
	while (sum()<16 ) {
	    if (deckLength>4) {
		break;
	    }
	    addtohand();
	    System.out.println("opponent added card");
	    }
	if (sum()>21)
	    win=false;
	    //System.out.println("opponent hand:"+hand);
	//System.out.println("sum of opponent card:"+sum());
    }
    public String toString() {
	return name+"'s  hand:"+hand+"\n sum of cards:"+sum();
    }
}
