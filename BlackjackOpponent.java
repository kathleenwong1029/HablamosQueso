import java.util.ArrayList;
import java.lang.Math;
public class BlackjackOpponent extends Blackjack {
    //first working edition
    public boolean win=true;
    public String name;
    public ArrayList<Card> deck2;//if there is a split
    public BlackjackOpponent(String n) {
	//deal();
	//bet+=(int) (Math.random()*500);
	name=n;
	
	
    }
    public BlackjackOpponent(int i) {
	//deal();
        //System.out.println("dealer card:"+hand.get(0));
	bet+=(int) (Math.random()*1000);
	name="dealer";
	
    }
    public int sum(ArrayList<Card> deck) {
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
	
	return sum;
	
    }
    public int AI(ArrayList<Card> deck,Card headCard) {
	Card hCard=headCard;
	int sum2=0;
	
	if (name.equals("dealer")) {
	    System.out.println("dealer card:"+hand.get(0));
	}
	
        if (deckLength>1 && deck.get(0).value==deck.get(1).value && sum(deck)<17 && deckLength<3) {
	    deck2.add(deck.get(1));
	    deck.remove(0);
	    sum2=AI(deck2, hCard);
	}
	while (sum(deck)<16 || ((headCard.value==10 || headCard.value==1) && sum(deck)<17)) {
	    if (deckLength>4) {
		break;
	    }
	    addtohand(deck);
	    System.out.println(name+" added card");
	    }
	if((sum(deck)>sum2 && sum(deck)<22)|| sum2>21 ) {
	    sum2=sum(deck);
	}
	if (sum2>21)
	    win=false;
	
    return sum2;
    }
    public Card AIDealer(ArrayList<Card> deck) {
	if (name.equals("dealer")) {
	    System.out.println("dealer card:"+hand.get(0));
	}
	
        
	while (sum(deck)<17) {
	    if (deckLength>4) {
		break;
	    }
	    addtohand(deck);
	    System.out.println(name+" added card");
	    }
	if (sum(deck)>21)
	    win=false;
	return hand.get(0);
    }
    
    public String toString() {
	return name+"'s  hand:"+hand+"\n sum of cards:"+sum(deck);
    }
}
