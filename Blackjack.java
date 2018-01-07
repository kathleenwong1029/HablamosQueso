import cs1.Keyboard;
public class Blackjack extends CardGame {
    private int deckLength=0;
    public Blackjack() {
	super();
	hand=new Card[5];
    }
    public void deal() {
	shuffle(deck);
	hand[0]=deck[0];
	hand[1]=deck[1];
	deckLength=2;
    }
    public void addtohand() {
	if (deckLength>4) {
	    System.out.println("your deck is full");
	}
	else {
	    hand[deckLength]=deck[2];
	    deckLength+=1;
	}
    }
    public int sum() {
	int sum=0;
	for(int i=0; i<deckLength; i++) {
	    if(hand[i].getValue()>10){
		sum+=10;
	    }
	    else if(hand[i].getValue()==1){
		System.out.println("this card can either be 1 or 11");
		System.out.println("pick value for card(u can change it later)1) 1. 2) 11.");
		int user;
		user=Keyboard.readInt();
		if(user==1) {
		    sum+=1;
		}
		else {
		    sum+=11;
		}
		
	    }
	    else {
		sum+=hand[i].getValue();
	    }
	}
	return sum;
    }
    public String getInstructions() {
	return "The goal of the game is to get as close to 21 as possible.You can either call or keep ur hand.";
    }
    
}
