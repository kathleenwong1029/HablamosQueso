import java.util.ArrayList;
import java.lang.Math;
public class BlackjackOpponent extends Blackjack {
    //second working edition
    /*remaining bugs:
splitting a hand doesn't quite work. The hand is split, but no comparison is made.
     */
    public boolean win=true;
    public String name;
    public Card headCard;
    public ArrayList<Card> deck2=new ArrayList<Card>();//if there is a split, use this deck
    //default constructor
    public BlackjackOpponent(String n) {
	//deal();
	bet+=(int) (Math.random()*500);
	name=n;
	
	
	
    }
    //constructor for dealer
    public BlackjackOpponent(int i) {
	//deal();
        //System.out.println("dealer card:"+hand.get(0));
	bet+=(int) (Math.random()*1000);
	name="dealer";
	
    }
    //sums up cards in deck
    public int sum(ArrayList<Card> deck) {
	int sum=0;//return value
	for(int i=0; i<deckLength; i++) {//goes thru each card!
	    if(hand.get(i).getValue()>10){//if the value of the card is greater than 10, than downcast it
		sum+=10;
	    }
	    
	    else if(hand.get(i).getValue()==1) {//if its an Ace, it can either be 1 or 11
		if (sum>10) {//if sum is more than 10 already, A must be 1
		    sum+=1;
		}
		else {//otherwise, it can be 11
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
    /***AI that determines what the opponent will do
	AI(deck, hand, hcard)
	- deck is the deck of cards that will be used to draw(this allows us to standardize the deck across all instances)
-hand is the hand that we currently have
-hcard is the card the dealer is displaying
postcond: outputs the sum of the hand, adds cards as needed to hand
    ***/
    public int AI(ArrayList<Card> deck,ArrayList<Card> hand,Card hCard) {
	headCard=hCard;
	int sum2=0;
        
	System.out.println(hand);
        if (deckLength>1 && hand.get(0).value==hand.get(1).value && sum(deck)<17 && deckLength<3) {//if there is a pair, and splitting the deck up will lead to more benefit than harm, then it can be split up
	    System.out.println(hand);
	    deckLength-=1;
	    deck2.add(hand.get(0));
	    hand.remove(0);
	    sum2=AI(deck, deck2, headCard );
	}
	while (sum(hand)<16 || ((headCard.value==10 || headCard.value==1) && sum(hand)<17)) {//if the dealer's card is a 10 or ace, we are more willing to take a risk with respect to adding cards to our deck
	    if (deckLength>4) {
		break;//if there are too many cards, end the while loop
	    }
	    
	    addtohand(deck);//otherwise, add another card
	    System.out.println(name+" added card");//tells the user that opponent added card
	    }
	if((sum(hand)>sum2 && sum(hand)<22)|| sum2>21 ) {//in a situation where there are two decks(when there is a pair), this compares the two and outputs the higher one
	    sum2=sum(hand);
	}
	if (sum2>21)//if the final sum is greater than 21
	    win=false;// we cannot win
	
	return sum2;//returns sum so we can compare the running of  the AI with 2 deck
    }
    public Card AIDealer(ArrayList<Card> deck) {//dealer has simpler logic
	if (name.equals("dealer")) {
	    System.out.println("dealer card:"+hand.get(0));//must display first card
	    headCard=hand.get(0);
	}
	
        
	while (sum(deck)<17) {//must add cards if below 17
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
	return name+"'s  hand:"+hand+"\n sum of cards:"+sum(hand);//shows us the other player's cards
    }
}
