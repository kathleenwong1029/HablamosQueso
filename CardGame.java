import java.lang.Math;
import java.util.ArrayList;
public abstract  class CardGame {
    //instance vars
    protected ArrayList<Card> deck = new ArrayList<Card>();//deck of cards
    public ArrayList<Card> hand = new ArrayList<Card>();//the cards the player has

  //constructor
  public CardGame() {
	//initialize deck
	  for(int i=1;i<14;i++) {
	    for(int j=0;j<4;j++) {
		    Card c = new Card(i,j);
        deck.add(c);
		  }
	  }
  }

    //methods

    public abstract String getInstructions();
    public abstract void deal(ArrayList<Card> deck);
    //public abstract int placeBet();(added with gambling)*/

    //shuffles
    public void shuffle(ArrayList arr) {
      int randomIndex;
      for( int i = arr.size()-1; i > 0; i-- ) {
     	  //pick an index at random
        randomIndex = (int)( (i+1) * Math.random() );
     	  //swap the values at position i and randomIndex
        arr.set( i, arr.set( randomIndex, arr.get(i) ) );
      }
    }

    //prints out card
  public String printArray(ArrayList<Card> arr) {
	String retu="";
  String a="";
  String b="";
  String c="";
  String d="";
	for(int i=0;i<arr.size();i++) {
    //suit symbols
    String sign="";
    if(arr.get(i).symbol=="clubs"){
      sign="\u2663";
    }
    if(arr.get(i).symbol=="hearts"){
      sign="\u2665";
    }
    if(arr.get(i).symbol=="diamonds"){
      sign="\u2666";
    }
    if(arr.get(i).symbol=="spades"){
      sign="\u2660";
    }
    //royals
    String value;
    value= String.valueOf(arr.get(i).value);
    if(arr.get(i).value==1){
      value="A";
    }
    if(arr.get(i).value==11){
      value="J";
    }
    if(arr.get(i).value==12){
    value="Q";
    }
    if(arr.get(i).value==13){
      value="K";
    }
      //more spaces if double digit value
      if(arr.get(i).value==10){
      a+="-------";
      b+="|"+value+" "+value+ "|";
      c+="|  "+sign+ "  |";
      d+="   " +i+"   "; }
      else{
      a+="------";
      b+="|"+value+"  "+value+ "|";
      c+="|  "+sign+ " |";
      d+="   " +i+"  ";}
	}
  retu+=a+"\n"+b+"\n"+c+"\n"+b+"\n"+a+"\n"+d+"\n"+arr;
  return retu;

    }

}
