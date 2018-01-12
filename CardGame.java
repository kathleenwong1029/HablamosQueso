import java.lang.Math;
import java.util.ArrayList;
public abstract  class CardGame {
    //instance vars
    protected ArrayList<Card> deck = new ArrayList<Card>();//deck of cards
    public ArrayList<Card> hand;//the cards the player has

  //constructor
  public CardGame() {
	//initialize deck
	  for(int i=0;i<13;i++) {
	    for(int j=0;j<4;j++) {
		    Card c = new Card(i,j);
        deck.add(c);
		  }
	  }
  }

    //methods

    public abstract String getInstructions();
    public abstract void deal();
    //public abstract int placeBet();(added with gambling)*/


    public void shuffle(ArrayList arr) {
      int randomIndex;
      for( int i = arr.size()-1; i > 0; i-- ) {
     	  //pick an index at random
        randomIndex = (int)( (i+1) * Math.random() );
     	  //swap the values at position i and randomIndex
        arr.set( i, arr.set( randomIndex, arr.get(i) ) );
      }
    }

    
    public String printArray(ArrayList arr) {
	String retu="";
	for(int i=0;i<arr.size();i++) {
	    retu+=arr.get(i)+"\n";
	}
	return retu;
    }
    /*    public static void main(String[] args) {
	CardGame jav=new CardGame();
	jav.toString();
	System.out.println(jav.printArray(jav.deck));
	jav.shuffle(jav.deck);
	System.out.println(jav.printArray(jav.deck));

	}*/
}
