import cs1.Keyboard;
import java.util.ArrayList;
public class Blackjack extends CardGame {
    private int deckLength=0;//length of the players deck
    public Blackjack() {
	super();//inherits default constructor
	hand=new ArrayList<Card>();//this is the player's array
    }
    public void deal() {
	shuffle(deck);//shuffles the deck
	System.out.println(deck);
        hand.add(deck.remove(0));//adds the Card to hand, removes it from deck
	
	hand.add(deck.remove(0));//does it again because you need 
	deckLength=2;
    }
    public void addtohand() {
	if (deckLength>4) {
	    System.out.println("your deck is full");
	}
	else {
	    hand.add(deck.remove(0));
	    
	    
	    deckLength+=1;
	}
    }
    public int sum() {
	int sum=0;
	for(int i=0; i<deckLength; i++) {
	    if(hand.get(i).getValue()>10){
		sum+=10;
	    }
	    else if(hand.get(i).getValue()==1){
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
		sum+=hand.get(i).getValue();
	    }
	}
	//diag
	System.out.println("prog run fin");
	return sum;
    }
    public boolean win() {
	return true;
    }
    public int gamble() {
	int bet=0;
	System.out.println("enter amount to gamble(must be less than value of bank account:");
	int u;
	u=Keyboard.readInt();
	if (u>Woo.viewBal()) {
	    System.out.println("you can't gamble that much! The dealer interprets this as all in!");
	    bet=Woo.viewBal();
	    
	    
	}
	else {
	    bet=u;
	}
	return bet;
    }
	    
    public void play() {
	int u=1;
	 System.out.println("These are your cards: \n"+printArray(hand));
	    while(u==1) {
	        
	    System.out.println("Ask dealer for other card?");
	    System.out.println("1 for yes, 2 for no");

	    u=Keyboard.readInt();
	    if(u==1) {
		addtohand();
		 System.out.println("These are your cards\n"+printArray(hand));
		 

	    }
	   
	    }
	    

	    System.out.println("These are your cards\n"+printArray(hand));
	    System.out.println("This is the sum"+sum());
	    sum();
	    int total=sum();
	    
	    if(total>21) {
		System.out.println("OVERFLOW");
	    }
	    else if(total==21) {
		System.out.println("blackjack");
	    }
	    else {
		System.out.println("Below 21!");
	    }
    }
    public String getInstructions() {
	return "The goal of the game is to get as close to 21 as possible.You can either call or keep ur hand.";
    }
    
}
