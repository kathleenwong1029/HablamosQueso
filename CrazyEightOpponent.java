import java.util.ArrayList;

public class CrazyEightOpponent extends CrazyEight{

  private int deckLength;
  public String name;
  private boolean isValidMove = false;
  public boolean winner =false;
  public Card topCard;
  int a;
  public String suit="";

  //constuctor
  public CrazyEightOpponent(){
  }

  //overloaded constructor
  public CrazyEightOpponent(String heyhey){
    this();
    name=heyhey;
  }
  public Card getTopCard(){
    return topCard;
  }

  public void setTopCard(CrazyEight main){
    topCard = main.getTopCard();
  }
  public void setTopCard2(CrazyEightOpponent main){
    topCard = main.getTopCard();
  }

  public String getSuit(){
    return suit;
  }

  public void setSuit(CrazyEight main){
    suit = main.getSuit();
  }

  public void setSuit2(CrazyEightOpponent main){
    suit = main.getSuit();
  }

  public void turn(Card other){
    System.out.println("The topCard is " + topCard);
    //testing purposes
    System.out.println("player" + name + "has" +printArray(hand) + "\n");

    a = deckLength-1;
    //if player has no usable cards, they are forced to draw
    while(isValidMove){
    outer:
    while(a>-1){
      //if the last card played was an eight, opponent requires a card of the declared suit
      //or an eight
      if(!(suit=="")){
        if(hand.get(a).symbol==suit||hand.get(a).value==8){
          isValidMove=true;
          break outer;
        }
      }
      //if the last card played was not a suit, an eight or card of similar suit of number can be played
      else{
      if(hand.get(a).value==8||hand.get(a).value==other.value||hand.get(a).symbol==other.symbol){
        isValidMove=true;
        break outer;
      }
    }
      a--;
      //opponent has to keep drawing cards until one is playable
      if (a==0){
        drawCard();
        deckLength++;
        System.out.println("Player " + name + " drew a card!");
        a=deckLength-1;
      }
    }
  }

    inner:
    //iterate through hand for playable card
    for(int i=0;i<hand.size()-1;i++){
      if(!(suit=="")){
        if((hand.get(i).value==8)||(hand.get(i).symbol==suit)){
          topCard= hand.get(i);
        }
        }
      else{
      if ((hand.get(i).value==8)||(hand.get(i).value==other.value)||(hand.get(i).symbol==other.symbol)) {
        topCard= hand.get(i);}
      }
        deck.add(hand.get(i));
        System.out.println("Player " + name + " played"+ hand.get(i));

        //if computer opponent plays an 8, a random suit is chosen
        if(hand.get(i).value==8){
          double whichSuit=Math.random();
          if(whichSuit<.25){
            System.out.println("The suit is set to diamonds.");
            suit="clubs";
          }
          if(whichSuit>.25 && whichSuit<.50){
            System.out.println("The suit is set to clubs.");
            suit="diamonds";
          }
          if(whichSuit>.50 && whichSuit<.75){
            System.out.println("The suit is set to hearts.");
            suit="hearts";
          }
          if(whichSuit>.75 && whichSuit<1.00){
            System.out.println("The suit is set to spades.");
            suit="spades";
          }
        }
        hand.remove(i);
        deckLength--;
        break inner;
      }

    if(deckLength==0){
      winner=true;
    }
  }

}
