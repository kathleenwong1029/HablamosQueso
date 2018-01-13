import cs1.Keyboard;
import java.util.ArrayList;

public class CrazyEight extends CardGame{
  private int deckLength;
  private boolean isValidMove = false;
  private boolean winner =false;
  private boolean drawAgain;
  private int cardNum=5;
  //constructor
  public CrazyEight(){
    super();
    shuffle(deck);
  }

  //deals five cards
  public void deal(){
     hand.add(deck.get(0));
	   hand.add(deck.get(1));
     hand.add(deck.get(2));
	   hand.add(deck.get(3));
     hand.add(deck.get(4));
     deckLength = 5;
  }

  //draw
  public void drawCard(){
    hand.add(deck.get(cardNum));
    cardNum++;
    deckLength++;
  }

  public boolean win(){
    return (deckLength==0);
  }

  public String win(Card other){
    // int a = deckLength-1;
    // //if player has no usable cards, they are forced to draw
    // while(isValidMove==false){
    // while(a>0){
    //   if(hand.get(a).value==8||hand.get(a).value==other.value||hand.get(a).symbol==other.symbol){
    //     isValidMove=true;
    //     break;
    //   }
    //   if (a==0){
    //     drawCard();
    //     cardNum+=1;
    //     deckLength++;
    //     System.out.println("Here's your new deck:");
    //   }
    //   a--;
    // }}

    while(winner == false){
      System.out.println(printArray(hand));
      while(!(drawAgain==true)) {
      System.out.println("Do you need to draw?");
      String draw =Keyboard.readString();
      if(draw.equals("yes")){
        drawCard();
        cardNum+=1;
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
      if(hand.get(user).value==other.value || hand.get(user).symbol==other.symbol){
        hand.remove(user);
        deckLength-=1;
        if(win()==true){
          winner=true;
          break;
        }
        System.out.println(printArray(hand));
      }
      else{
        System.out.println("Sorry, not a valid move. Try again!");
      }
    }
    return "Congratulations! You're winner!";
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
