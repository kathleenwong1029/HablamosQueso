import java.util.ArrayList;
public class TexasOpponent extends TexasHoldEm {

  public boolean bettingOff = false;

  //constructy
  public TexasOpponent(){
    super(500);
    bettingOff = false;
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

  public boolean raiseChance(){
    if (bettingOff == true) {return false;}
    if (getBal()<100) {return false;}
    else {
      if (Math.random()*10 < getBestComboVal()){ //always at least 10% chance of raising regardless of hand, will be more likely to raise the higher the value of the current best possible hand
        return true;
      }
      return false;
    }
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
