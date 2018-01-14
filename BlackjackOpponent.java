import java.util.ArrayList;
public class BlackjackOpponent extends Blackjack {
    //first working edition
    
    public BlackjackOpponent() {
	super();
	
	
    }
    public BlackjackOpponent(int i) {
	super();
        
	
    }
    public int sum() {
	int sum=0;//return value
	for(int i=0; i<deckLength; i++) {//goes thru each card!
	    if(hand.get(i).getValue()>10){//if the value of the card is greater than 10, than downcast it
		sum+=10;
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
	deal();
	System.out.println("opponent hand:"+hand);
	/*while (sum()<16) {
	    if (deckLength>4) {
		break;
	    }
	    addtohand();
	    }*/
	System.out.println("sum of opponent card:"+sum());
    }
}
