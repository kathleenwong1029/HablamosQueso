import java.util.ArrayList;

public class CrazyEightOpponent extends CrazyEight{

  private int deckLength;
  public String name;
  private boolean isValidMove = false;
  public boolean winner =false;
  public Card topCard;
  int a;


  public CrazyEightOpponent(){
    deal();
  }

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

  public void turn(Card other){
    System.out.println("The topCard is " + topCard);
    a = deckLength-1;
    //if player has no usable cards, they are forced to draw
    outer:
    while(isValidMove){
    while(a>-1){
      if(hand.get(a).value==8||hand.get(a).value==other.value||hand.get(a).symbol==other.symbol){
        isValidMove=true;
        break outer;
      }
      else{
        a--;
      }
      if (a==0){
        drawCard();
        deckLength++;
        System.out.println("Player " + name + " drew a card!");
        a=deckLength-1;
      }
    }
  }

    for(int i=0;i<hand.size()-1;i++){
      if ((hand.get(i).value==other.value) || (hand.get(i).symbol==other.symbol)){
        topCard= hand.get(i);
        deck.add(hand.get(i));
        System.out.println("Player" + name + "played"+hand.get(i) );
        hand.remove(i);
        deckLength--;
        break;
      }
    }
    deckLength--;
    if(deckLength==0){
      winner=true;
    }
  }

}
