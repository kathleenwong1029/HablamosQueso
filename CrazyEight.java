import cs1.Keyboard;
import java.util.ArrayList;

public class CrazyEight extends CardGame{

  //variables
  private int deckLength;
  public boolean winner =false;
  private boolean drawAgain;
  public Card topCard;
  public String suit="";

  //constructor
  public CrazyEight(){
    super();
    shuffle(deck);
    deal();
    //the top card is the next card in the stockpile after dealing
    topCard = deck.get(0);
    deck.remove(0);
  }

  //deals five cards
  public void deal(){
     drawCard();
     drawCard();
     drawCard();
     drawCard();
     drawCard();
     deckLength = 5;
  }

  //draw
  public void drawCard(){
    hand.add(deck.get(0));
    deck.remove(0);
    deckLength++;
  }

  //accessors
  public Card getTopCard(){
    return topCard;
  }

  public void setTopCard(CrazyEightOpponent main){
    topCard = main.getTopCard();
  }

  public String getSuit(){
    return suit;
  }

  public void setSuit(CrazyEightOpponent main){
    suit = main.getSuit();
  }

  //player wins when they use all their cards
  public boolean win(){
    return (deckLength==0);
  }

  public void win(Card other){
    System.out.println("The topCard is " + topCard);
    outer:
    while(winner == false){
      System.out.println(printArray(hand));
      while(!(drawAgain==true)) {
      //ask player if they need to draw
      System.out.println("Do you need to draw?(Type yes or no)");
      String draw =Keyboard.readString();
      if(draw.equals("yes")){
        drawCard();
        deckLength++;
      }
      System.out.println(printArray(hand));
      if(draw.equals("no")){
        drawAgain=true;
      }
    }
    drawAgain=false;
      //allows player to choose which card to play
      System.out.println("Which card would you like to discard?");
      int user;
      user=Keyboard.readInt();
      //if an eight was played, user must play a card of declared suit of another eight
      if(!(suit=="")){
        if(hand.get(user).value==8||hand.get(user).symbol==suit){
        topCard=hand.get(user);}
        else{
          //gives error message if invalid card is attempted
          System.out.println("Sorry, not a valid move. Try again!");
          System.out.println(printArray(hand));
          break outer;
        }
      }
      //if a regular card was played, user can play an eight or any card of the same value of suit
      else{
        if(hand.get(user).value==8||hand.get(user).value==other.value || hand.get(user).symbol==other.symbol){
        topCard = hand.get(user);}
        else{
          //gives error message if invalid card is attempted
          System.out.println("Sorry, not a valid move. Try again!");
          System.out.println(printArray(hand));
          break outer;
        }
      }
      //if the card played is an eight, user can declare a suit for the next player
      if(topCard.value==8){
          System.out.println("Which suit would you like to declare?");
          String suitAnswer=Keyboard.readString();
          if(suitAnswer=="diamond"||suitAnswer=="diamonds"){
            suit="diamonds";
          }
          if(suitAnswer=="club"||suitAnswer=="clubs"){
            suit="clubs";
          }
          if(suitAnswer=="heart"||suitAnswer=="hearts"){
            suit="hearts";
          }
          if(suitAnswer=="spade"||suitAnswer=="spades"){
            suit="spades";
          }
          System.out.println("The suit has been set to: " + suit);
        }
        //after card is played, it is removed from the player's hand and added to the end of the deck
        deck.add(hand.get(user));
        hand.remove(user);
        deckLength-=1;
        //checks if player has won the game
        if(win()==true){
          winner=true;
        }
        break outer;
    }
  }

  //instructions
  public String getInstructions(){
    String s = "Welcome to the CrazyEights Table!";
    s+="\n The goal of the game is to use up all your cards.";
    s+= "You can discard a card only if it matches the rank of suit of the card on the discard pile.";
    s+="Playing an 8 allows you to declare any suit.";
    s+=" If no card is available, draw from the stockpile.";
    return s;
  }


}
