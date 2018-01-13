import java.util.ArrayList;

public class CrazyEightOpponent extends CardGame{

  private int deckLength;
  public String name;
  private boolean isValidMove = false;
  private boolean winner =false;
  private boolean drawAgain;
  int a;
  private int cardNum=46;
  private Card stock;

  public CrazyEightOpponent(){
    deal();
  }
  public CrazyEightOpponent(String heyhey){
    this();
    name=heyhey;
  }

  public void drawCard(){
    hand.add(deck.get(cardNum));
    cardNum--;
    deckLength++;
  }

  public void deal(){
    drawCard();
    drawCard();
    drawCard();
    drawCard();
    drawCard();
  }

  public void turn(Card other){
    a = deckLength-1;
    //if player has no usable cards, they are forced to draw
    while(isValidMove==false){
    while(a>0){
      if(hand.get(a).value==8||hand.get(a).value==other.value||hand.get(a).symbol==other.symbol){
        isValidMove=true;
        break;
      }
      else{
        a--;
      }
      if (a==0){
        drawCard();
        cardNum+=1;
        deckLength++;
        System.out.println("Player" + name + "drew a card!");
        a=deckLength-1;
      }
    }
  }
    for(int i=0;i<hand.size()-1;i++){
      if ((hand.get(i).value==other.value) || (hand.get(i).symbol==other.symbol)){
        hand.remove(i);
        deckLength--;
      }
    }
    deckLength--;
    if(deckLength==0){
      winner=true;
    }
  }

  public String getInstructions(){
    return "opponent for CrazyEight game";
  }
}
