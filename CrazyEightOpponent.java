import java.util.ArrayList;

public class CrazyEightOpponent extends CrazyEight{

  private int deckLength;
  public String name;
  public Card topCard;

  //overloaded constructor
  public CrazyEightOpponent(String heyhey){
    name=heyhey;
  }
  //accessors/mutators
  public Card getTopCard(){
    return topCard;
  }
  public void setTopCard(CrazyEightOpponent main){
    topCard = main.getTopCard();
  }
  public void setTopCard2(CrazyEight main){
    topCard = main.getTopCard();
  }

  //opponent's turn
    public ArrayList<Card> turn(Card other, ArrayList<Card> deck){
    //variables
    boolean isValidMove=false ;
    boolean cardPlayed =false;
    //testing purposes
    //System.out.println("The topCard is: " + getTopCard());
    //System.out.println("player" + name + "has"+ "\n" +printArray(hand) );

    while(!(isValidMove)){
    //iterate through hand for playable card
    int i=0;
    while(i<hand.size()){
      //card can be played if it matches the number of the topCard's number or if it is an eight
      if((!(other.value==8)) && hand.get(i).value==other.value){
        cardPlayed=true;
      }
      //an eight can always be played
      if(hand.get(i).value==8){
          cardPlayed=true;
        }
        //card can be played if it matches the symbol of the topCard's symbol
        if(hand.get(i).symbol==other.symbol){
          cardPlayed=true;
        }
      if(cardPlayed){
        isValidMove=true;
        topCard= hand.get(i);
        deck.add(hand.get(i));
        System.out.println("\nPlayer " + name + " played"+ hand.get(i));
        //if computer opponent plays an 8, a random suit is chosen
        if(hand.get(i).value==8){
          double whichSuit=Math.random();
          if(whichSuit<.25){
            System.out.println("The suit is set to clubs.");
            topCard.symbol="clubs";
          }
          if(whichSuit>.25 && whichSuit<.50){
            System.out.println("The suit is set to diamonds.");
            topCard.symbol="diamonds";
          }
          if(whichSuit>.50 && whichSuit<.75){
            System.out.println("The suit is set to hearts.");
            topCard.symbol="hearts";
          }
          if(whichSuit>.75 && whichSuit<1.00){
            System.out.println("The suit is set to spades.");
            topCard.symbol="spades";
          }
        }
        hand.remove(i);
        break ;
      }
     if(!(cardPlayed)){
      i++;
     }
   }
   //if deck did not have playable card, keep drawing
   if(!(isValidMove)){
     drawCard(deck);
  }
}//end while loop
    return deck;
}//end turn method
}//end CrazyEightOpponent
