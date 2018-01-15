import cs1.Keyboard;
import java.util.ArrayList;

public class CrazyEight extends CardGame{
  private int deckLength;
  public boolean winner =false;
  private boolean drawAgain;
  public Card topCard;


  //constructor
  public CrazyEight(){
    super();
    shuffle(deck);
    deal();
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

  public Card getTopCard(){
    return topCard;
  }
  public void setTopCard(CrazyEightOpponent main){
    topCard = main.getTopCard();
  }

  public boolean win(){
    return (deckLength==0);
  }

  public void win(Card other){

    System.out.println("The topCard is " + topCard);
    outer:
    while(winner == false){
      System.out.println(printArray(hand));
      while(!(drawAgain==true)) {
      System.out.println("Do you need to draw?(type in yes or no)");
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
      System.out.println("Which card would you like to discard?(enter 1 for 1st card, 2 for 2nd card etc.)");
      int user;
      user=Keyboard.readInt();
      if(hand.get(user).value==other.value || hand.get(user).symbol==other.symbol){
        topCard = hand.get(user);
        deck.add(hand.get(user));
        hand.remove(user);
        deckLength-=1;
        if(win()==true){
          winner=true;
        }
        break outer;
      }
      else{
        System.out.println("Sorry, not a valid move. Try again!");
        System.out.println(printArray(hand));
      }
    }
  }

  //instructions
  public String getInstructions(){
    String s = "The goal of the game is to use up all your cards.";
    s+= "You can discard a card only if it matches the rank of suit of the card on the discard pile.";
    s+="Playing an 8 allows you to declare any suit.";
    s+=" If no card is available, draw from the stockpile.";
    return s;
  }


}
