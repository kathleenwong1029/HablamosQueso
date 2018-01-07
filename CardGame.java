import java.lang.Math;
import java.util.ArrayList;
public  class CardGame {
    //instance vars
    protected Card[] deck=new Card[52];//deck of cards
    public Card[] hand;//the cards the player has
    
    //constructor
    public CardGame() {
	//initialize deck
	int counter=0;
	for(int i=0;i<13;i++) {
	    for(int j=0;j<4;j++) {
		deck[counter]=new Card(i,j);
		counter++;
		    }
	}
    }
	
    /*/methods
   
    public abstract void getInstructions();
    public abstract void deal();
    //public abstract int placeBet();(added with gambling)*/
    
       
    public void shuffle(Card[] deck) {
	Card storage;
	int swap1;
	int swap2;
	for(int i=0; i<1000; i++) {//does 1000 swaps
	    swap1=(int)(Math.random()*52);
	    swap2=(int)(Math.random()*52);
	    storage=deck[swap1];
	    deck[swap1]=deck[swap2];
	    deck[swap2]=storage;
	}
	
    }
    public String printArray(Card[] deck) {
	String retu="[";
	for(int i=0;i<deck.length;i++) {
	    retu+=deck[i]+",";
	}
	return retu;
    }
    public static void main(String[] args) {
	CardGame jav=new CardGame();
	jav.toString();
	System.out.println(jav.printArray(jav.deck));
	jav.shuffle(jav.deck);
	System.out.println(jav.printArray(jav.deck));
	    
    }
}
