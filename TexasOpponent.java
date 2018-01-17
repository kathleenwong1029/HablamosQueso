import java.util.ArrayList;
public class TexasOpponent extends TexasHoldEm {

  public boolean bettingOff = false;

  //constructy
  public TexasOpponent(){
    super(500);
  }

  public void setBettingOff(){
    bettingOff = true;
  }

  //manualSetBoard method is inherited

  public void manualAddToBoard(Card c){
    board.add(c);
  }

  public boolean blindBet(){
    if (bettingOff == true) {return false;}
    else {
      if (Math.random()<0.05){ //5% chance that the opponent will try to raise blind bet
        return true;
      }
      return false;
    }
  }

  //returns the hand value based on the string name
  public int getBestComboVal(){
    String holder = bestPossibleHand();
    holder = holder.substring(holder.indexOf("]")+2);
    if (holder.equals("Royal Flush")) {return 10;}
    if (holder.equals("Straight Flush")) {return 9;}
    if (holder.equals("Four of a Kind")) {return 8;}
    if (holder.equals("Full House")) {return 7;}
    if (holder.equals("Flush")) {return 6;}
    if (holder.equals("Straight")) {return 5;}
    if (holder.equals("Three of a Kind")) {return 4;}
    if (holder.equals("Two Pairs")) {return 3;}
    if (holder.equals("Pair")) {return 2;}
    return 1;
  }



//----------------------------methods for testing-----------------------------
  public static void main(String[] args){
    TexasOpponent oui = new TexasOpponent();
    System.out.println("Balence: " + oui.getBal());
    Card a = new Card(10,3);
    Card b = new Card(11,3);
    Card c = new Card(12,3);
    Card d = new Card(13,3);
    Card e = new Card(1,3);
    oui.manualSetBoard(a,b,c);
    oui.deal();
    oui.setHand(d,e);
    System.out.println(oui.bestPossibleHand());
    System.out.println(oui.getBestComboVal());
  }

}
