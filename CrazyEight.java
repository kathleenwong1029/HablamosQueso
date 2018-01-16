import cs1.Keyboard;
import java.util.ArrayList;

public class CrazyEight extends CardGame{

  //variables
  private boolean drawAgain;
  public Card topCard;
  private boolean winner=false;
  private boolean cardPlayed =false;

  //constructor
  public CrazyEight(){
    super();
    shuffle(deck);
    deal(deck);
    //the top card is the next card in the stockpile after dealing
    topCard = deck.get(0);
    deck.remove(0);
  }

  //deals five cards
  public void deal(ArrayList<Card> deck){
     drawCard(deck);
     drawCard(deck);
     drawCard(deck);
     drawCard(deck);
     drawCard(deck);
  }

  //draw
  public void drawCard(ArrayList<Card> deck){
    hand.add(deck.get(0));
    deck.remove(0);
  }

  //accessors
  public Card getTopCard(){
    return topCard;
  }

  public void setTopCard(CrazyEightOpponent main){
    topCard = main.getTopCard();
  }
  public int lose(){
    return hand.size();
  }

  //player wins when they use all their cards
  public boolean win(){
    return (hand.size()==0);
  }

  public void play(Card other){

    System.out.println("\nThe topCard is: " + getTopCard() +"\n");
    outer:
    while(winner == false){
      System.out.println("Here is your hand: " );
      System.out.println(printArray(hand));
      while(!(drawAgain)) {
      //ask player if they need to draw
      System.out.println("Do you need to draw?(Type yes or no)");
      String draw =Keyboard.readString();
      if(draw.equals("yes")){
        drawCard(deck);
      }
      System.out.println(printArray(hand));
      if(draw.equals("no")){
        break ;
      }
    }
      //allows player to choose which card to play
      System.out.println("Which card would you like to discard?(Enter number under card)");
      int user;
      user=Keyboard.readInt();

      //card can be played if it matches the number of the topCard's number or if it is an eight
      if(!(other.value==8) && hand.get(user).value==other.value){
        cardPlayed=true;
      }
      //an eight can always be played
      if(hand.get(user).value==8){
          cardPlayed=true;
      }
        //card can be played if it matches the symbol of the topCard's symbol
      if(hand.get(user).symbol==other.symbol){
          cardPlayed=true;
        }
        if(cardPlayed){
          topCard=hand.get(user);
          //if the card played is an eight, user can declare a suit for the next player
          if(topCard.value==8){
              System.out.println("Which suit would you like to declare?");
              String suitAnswer=Keyboard.readString();
              if(suitAnswer=="diamond"||suitAnswer=="diamonds"){
                topCard.symbol="diamonds";
              }
              if(suitAnswer=="club"||suitAnswer=="clubs"){
                topCard.symbol="clubs";
              }
              if(suitAnswer=="heart"||suitAnswer=="hearts"){
                topCard.symbol="hearts";
              }
              if(suitAnswer=="spade"||suitAnswer=="spades"){
                topCard.symbol="spades";
              }
              System.out.println("The suit has been set to: " + topCard.symbol +" ");
            }
            //after card is played, it is removed from the player's hand and added to the end of the deck
            deck.add(hand.get(user));
            hand.remove(user);
            return;
        }
        else{
          //gives error message if invalid card is attempted
          System.out.println("Sorry, not a valid move. Try again!");
        }

        //checks if player has won the game
        if(win()==true){
          winner=true;
          return;
        }

    }
  }

  //instructions
  public String getInstructions(){
    String s = "Welcome to the CrazyEights Table!";
    s+="\n The goal of the game is to use up all your cards.";
    s+= "You can discard a card only if it matches the rank or suit of the card on the discard pile.";
    s+="Playing an 8 allows you to declare any suit.";
    s+=" If no card is available, draw from the stockpile.";
    return s;
  }
}
