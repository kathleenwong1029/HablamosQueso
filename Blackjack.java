import cs1.Keyboard;
import java.util.ArrayList;
public class Blackjack extends CardGame {
    public boolean win=true;
    protected int deckLength=0;//length of the players deck
    //note to self: add instance vars that track value of computer opponent bank account?
    public Blackjack() {
	super();//inherits default constructor
	hand=new ArrayList<Card>();//this is the player's array
    }
    public  void deal() {
	shuffle(deck);//shuffles the deck
	System.out.println(deck);
        hand.add(deck.remove(0));//adds the Card to hand, removes it from deck
	
	hand.add(deck.remove(0));//does it again because you need 
	deckLength=2;//deck length is 2 cards
    }
    public void addtohand() {//adds card to your hand, if you want to
	if (deckLength>4) {
	    //you can't have more than 5 cards
	}
	else {
	    hand.add(deck.remove(0));//adds additional card to deck
	    //System.out.println(deck);//diag
	    
	    
	    deckLength+=1;//deck is now one card longer
	}
    }
    public int sum() {//sums up value of cards
	int sum=0;//return value
	for(int i=0; i<deckLength; i++) {//goes thru each card!
	    if(hand.get(i).getValue()>10){//if the value of the card is greater than 10, than downcast it
		sum+=10;
	    }
	    else if(hand.get(i).getValue()==1){//if the card is an ace(card equal to 1)
		System.out.println("this card("+hand.get(i)+") can either be 1 or 11");//allows user to choose what value to make this card
		System.out.println("pick value for card(u can change it later)\n 1) 1. 2) 11.");
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
	//System.out.println("prog run fin");
	return sum;
    }

    public int gamble() {
	int bet=0;//gamble default:0
	System.out.println("enter amount to gamble(must be less than value of bank account:");
	int u;
	u=Keyboard.readInt();
	if (u>Woo.viewBal()) {
	    System.out.println("you can't gamble that much! The dealer interprets this as all in!");
	    bet=Woo.viewBal();//puts in bet(entire value of bank account)
	    
	    
	}
	else {
	    bet=u;//puts in bet
	}
	return bet;
    }
	    
    public  void play() {
	int u=1;
	BlackjackOpponent tophie=new BlackjackOpponent(1);//dealer
	BlackjackOpponent pj=new BlackjackOpponent();
	BlackjackOpponent kdove=new BlackjackOpponent();
	tophie.AI();
	pj.AI();
	kdove.AI();
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
	    
	    int total=sum();
	    
	    System.out.println("This is the sum\n"+total);
	    if(total==21) {
		System.out.println("blackjack");
	    }
	    if(tophie.sum()>sum()) {
		win=false;
	    }
	    if(pj.sum()>sum()) {
		win=false;
	    }
	    if(kdove.sum()>sum()) {
		win=false;
	    }
	    
    }
    public String getInstructions() {
	return "The goal of the game is to get as close to 21 as possible(but never above it).You are dealt 2 cards at the beginning. YOu have the option of asking for up to 3 more cards to get up to your desired sum. Jacks, Queens, and Kings are all equal to 10. Aces can be equal to either 1 or 11. All other cards are equal to their respective values. This game has a designated DEALER(the opponent). A good tip is to look at the card that the dealer is showing to decide whether or not you will ask for another card from the deck!";
    }
    
}
