import cs1.Keyboard;
import java.util.ArrayList;
public class Blackjack extends CardGame {
    //public boolean win=true;
    public static int bet;
    protected int deckLength=0;//length of the players deck
    public int y;
    public int total;
    public int total2;
    //note to self: add instance vars that track value of computer opponent bank account?
    public Blackjack() {
	super();//inherits default constructor
	hand=new ArrayList<Card>();//this is the player's array
	
    }
    public  void deal(ArrayList<Card> deck) {
	shuffle(deck);//shuffles the deck

        hand.add(deck.remove(0));//adds the Card to hand, removes it from deck
	
	hand.add(deck.remove(0));//does it again because you need
	//System.out.println(hand);
	deckLength=2;//deck length is 2 cards
    }
    public void addtohand(ArrayList<Card> deck) {//adds card to your hand, if you want to. This takes in deck, to keep the deck universal for when there are opponents.
	if (deckLength>4) {
	    //you can't have more than 5 cards
	}
	else {
	    hand.add(deck.remove(0));//adds additional card to deck
	    //System.out.println(deck);//diag
	    
	    
	    deckLength+=1;//deck is now one card longer
	}
    }
    public int sum(ArrayList<Card> hand) {//sums up value of cards
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
        System.out.println("current betting pot size:"+bet);
	System.out.println("enter amount to gamble(must be less than value of bank account:");
	int u;
	u=Keyboard.readInt();
	if (u>Woo.viewBal()) {
	    System.out.println("you can't gamble that much! The dealer interprets this as all in!");
	    u=Woo.viewBal();
	    bet+=u;//puts in bet(entire value of bank account)
	    
	    
	}
	else {
	    bet+=u;//puts in bet
	}
	y=u;
	return u;
    }
	    
    public  void play() {//main function, allows you to play the game, split your hand, and basically do all the special features of blackjack
	
	int u=1;
	gamble();
	System.out.println("These are your cards: \n"+printArray(hand));
	while(u==1) {
	    if (hand.get(0).value==hand.get(1).value) {//split card
		System.out.println("want to split your cards?(this doubles your bet, and you will basically have two chances to get as close to 21 as possible!) 1 for yes, 2 for no");
		u=Keyboard.readInt();
		if (u==1) {
		    bet+=y;
		    y+=y;
		    ArrayList<Card> hand2=new ArrayList<Card>();
			 
		    hand2.add(hand.get(0));
		    hand.remove(0);
		    deckLength-=1;
			 
		    while(u==1) {
			System.out.println("One of your hands "+hand+"\n THe other hand "+hand2);
			System.out.println("Add cards to one of your hands? 1 for yes, 2 for no");
			u=Keyboard.readInt();
			if (u==2) {
			    break;
			}
			hand2.add(deck.get(0));
			deck.remove(0);
			    
		    }
		    total2=sum(hand2);
		}
		    
		    
		    
	    }
	        
	    System.out.println("Ask dealer for other card?");
	    System.out.println("1 for yes, 2 for no");

	    u=Keyboard.readInt();
	    if(u==1) {
		addtohand(deck);
		System.out.println("These are your cards\n"+printArray(hand));
		 

	    }
	   
	}
	    

	System.out.println("These are your cards\n"+printArray(hand));
	    
	total=sum(hand);
	if (total2> total ||( total2<22 && total>21)) {
	    total=total2;
	}
	//lists sum and opponent hands
	System.out.println("This is the sum\n"+total);
    }
    
    public String getInstructions() {
	return "The goal of the game is to get as close to 21 as possible(but never above it).You are dealt 2 cards at the beginning. YOu have the option of asking for up to 3 more cards to get up to your desired sum. Jacks, Queens, and Kings are all equal to 10. Aces can be equal to either 1 or 11. All other cards are equal to their respective values. This game has a designated DEALER(the opponent). A good tip is to look at the card that the dealer is showing to decide whether or not you will ask for another card from the deck!";
    }
    
}
