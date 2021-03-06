import cs1.Keyboard;
import java.util.ArrayList;
public class TexasHoldEm extends CardGame{

  public ArrayList<Card> board = new ArrayList<Card>();
  public int pot;
  public int bal; //should be set to current balence
  public boolean win;

//constructy for setting bal
public TexasHoldEm(int val){
  bal = val;
}

//accessors
  public String getHand(){
    return printArray(hand);
  }

  public String getBoard(){
    return printArray(board);
  }

  public int getPot(){
    return pot;
  }

  public int getBal(){
    return bal;
  }

  public boolean getWin(){
    return win;
  }

//getInstrustions
  public String getInstructions(){
    return "The objective of TexasHoldEm is to either play the better hand to win the pot, or to intimidate the opponent into giving up their bets by placing lots of high bets.\nThe game involves a shared set of five cards, called the board, and two cards per individual called the hole or pocket. Each game involves four rounds of betting, in which you can either:\n- Check, or pass\n- Raise, or add money to the pot\n- Fold, or give up your pocket and forfeit your bets to the opponent\nThe first round of betting takes place after the pocket is dealt, the second after the first three cards of the board are revealed (fold), the third after a fourth board card is revealed (turn), and the fourth after the final board card is revealed (river).\nDuring any round, if the opponent raises a bet, you can either follow up on that bet by placing the same amount from your own balence into the pot, or fold and forfeit your previously placed bets.\nThe final winner is determined by who plays the better poker hand.\n";
  }

//adds two random cards from the deck to hand
  public void deal(){
    shuffle(deck);
    hand.add(deck.get(0));
    deck.remove(0);
    hand.add(deck.get(0));
    deck.remove(0);
  }

//overrides abstract method but doesn't really do much
  public void deal(ArrayList<Card> deck){
    //nice one
  }

//resets pocket after a round
  public void discardPocket(){
    hand.remove(0);
    hand.remove(0);
  }

  public void setBoard(){
    //clear the board if not already
    if (board.size() > 0){
      for(int i = board.size(); i > 0 ; i--){
        board.remove(0);
      }
    }
    //add three cards at random, while removing them from deck
    shuffle(deck);
    int counter = 3;
    while (counter > 0){
      board.add(deck.get(0));
      deck.remove(0);
      counter--;
    }
  }

  public void addToBoard(){
    shuffle(deck);
    board.add(deck.get(0));
    deck.remove(0);
  }

  public void addToPot(int x){
    pot += x;
  }

  public void addToBal(int x){
    bal += x;
  }

  public void removeFromBal(int x){
    bal -= x;
  }

  public void setWin(boolean x){
    win = x;
  }

//the hard part
  public int getComboVal(Card v, Card w, Card x, Card y, Card z){
    ArrayList<Card> combo = new ArrayList<Card>();
    String values = "";
    String suits = "";
    combo.add(v);
    combo.add(w);
    combo.add(x);
    combo.add(y);
    combo.add(z);
    for (Card c: combo){
      values += c.getValue();
    }
    for (Card c: combo){
      suits += c.getSVal();
    }

    //tests for flush combos (require same suit)
    if (isConsistantString(suits)){
      //Royal Flush - 10 points
      if (sameChars(values, "101112131")){
        return 10;
      }
      //Straight Flush - 9 points
      if (isConsecutiveString(values) || sameChars(values,"678910") || sameChars(values,"7891011") || sameChars(values,"89101112") || sameChars(values,"910111213")){
        return 9;
      }
      //Flush - 6 points
      else{return 6;}
    }

    //tests for x of a kind
    ArrayList<Integer> freq = new ArrayList<Integer>();
    for (int i = 0; i < 14; i++){
      freq.add(0);
    }
    for (Card c: combo){
      freq.set(c.getValue(), freq.get(c.getValue())+1);
    }
    int pairCntr = 0;
    int tripletCntr = 0;
    int quadCntr = 0;
    for (int i = 0; i < freq.size(); i++){
      if (freq.get(i) == 2){ //Integer will automatically be unboxed to an int
        pairCntr += 1;
      }
      if (freq.get(i) == 3){
        tripletCntr += 1;
      }
      if (freq.get(i) == 4){
        quadCntr += 1;
      }
    }
    //Four of A Kind - 8 points
    if (quadCntr == 1) {return 8;}
    //Full House - 7 points
    if (tripletCntr == 1 && pairCntr == 1) {return 7;}
    //Three of a Kind - 4 points
    if (tripletCntr == 1) {return 4;}
    //Two Pair - 3 points
    if (pairCntr == 2) {return 3;}
    //Pair - 2 points
    if (pairCntr == 1) {return 2;}

    //Straight - 5 points
    //A is the highest card, 2 is the lowest card, no wrapping allowed
    if (isConsecutiveString(values) || sameChars(values,"678910") || sameChars(values,"7891011") || sameChars(values,"89101112") || sameChars(values,"910111213") || sameChars(values, "101112131")){
      return 5;
    }
    //if no combo achieved by any player (everyone has 1 point, then win is asserted off of who has the highest value card)
    return 1;
  }

//helper methods for said hard part
  //doesn't work for double digit values
  public boolean isConsistantString(String s){
    String lastChar = s.substring(0,1);
    for (int i = 1; i < s.length(); i++){
      if (!(s.substring(i,i+1).equals(lastChar))){
        return false;
      }
      else {
        lastChar = s.substring(i,i+1);
      }
    }
    return true;
  }

  //doesn't work for double digit values
  public boolean isConsecutiveString(String s){
    String lastChar = s.substring(0,1);
    for (int i = 1; i < s.length(); i++){
      if (s.substring(i,i+1) != lastChar+1){
        return false;
      }
      else {
        lastChar = s.substring(i,i+1);
      }
    }
    return true;
  }

  //tests to see if t contains the letters in s
  //only works for Strings of the same length
  public boolean sameChars(String s, String t){
    if (s.length() != t.length()) {return false;}
    String baseline = t;
    for (int i = 0; i < s.length(); i++){
      String c = s.substring(i,i+1); //current character
      if (baseline.contains(c)){
        baseline = baseline.substring(0,baseline.indexOf(c)) + baseline.substring(baseline.indexOf(c)+1);
      }
      else {return false;}
    }
    return true;
  }

//returns the hand name
  public String getComboName(Card a, Card b, Card c, Card d, Card e){
    int comboVal = getComboVal(a,b,c,d,e);
    if (comboVal == 10) {return "Royal Flush";}
    if (comboVal == 9) {return "Straigh Flush";}
    if (comboVal == 8) {return "Four of a Kind";}
    if (comboVal == 7) {return "Full House";}
    if (comboVal == 6) {return "Flush";}
    if (comboVal == 5) {return "Straight";}
    if (comboVal == 4) {return "Three of a Kind";}
    if (comboVal == 3) {return "Two Pairs";}
    if (comboVal == 2) {return "Pair";}
    return "Not a playable hand";
  }

//Determines best possible hand for player and CPU opponent
  public String bestPossibleHand(){
    ArrayList<Card> bestHand = new ArrayList<Card>();
    int bestHandVal = 0;
    ArrayList<Card> combi = new ArrayList<Card>();
    for (Card c : board){
      combi.add(c);
    }
    for (Card c : hand){
      combi.add(c);
    }
    for (int a = 0; a < combi.size()-4; a++){
      for (int b = a+1; b < combi.size()-3; b++){
        for (int c = b+1; c < combi.size()-2; c++){
          for (int d = c+1; d < combi.size()-1; d++){
            for (int e = d+1; e < combi.size(); e++){
              int currentHandVal = getComboVal(combi.get(a), combi.get(b), combi.get(c), combi.get(d), combi.get(e));
              if (currentHandVal > bestHandVal){
                bestHandVal = currentHandVal;
                bestHand.clear();
                bestHand.add(combi.get(a));
                bestHand.add(combi.get(b));
                bestHand.add(combi.get(c));
                bestHand.add(combi.get(d));
                bestHand.add(combi.get(e));
              }
            }
          }
        }
      }
    }
    return printArray(bestHand) + "\n" + getComboName(bestHand.get(0), bestHand.get(1), bestHand.get(2), bestHand.get(3), bestHand.get(4));
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

  //print shoutcut because I have so much to do and so little tiempo
  private static void p(String stuff) {
        System.out.println(stuff);
    }

  public void play(TexasOpponent opp){
    int r; //player input

    //title screen
    String title = "\nTexas Hold'em\nEnter 1 for instructions.\nEnter 2 to continue to game.\n";
    p(title);
    r = Keyboard.readInt();
    while (r == 1) {
      p(getInstructions() + "Enter 1 to return\n");
      r = Keyboard.readInt();
      if (r == 1){
        p(title);
        r = Keyboard.readInt();
      }
    }

    //starting the actual game
    while(true){
      //pre-flop
      r = 3;
      while(r==3){
        p("\nDealing cards...\n");
        deal();
        opp.deal();
        p("Pocket: \n" + getHand());
        p("\nBlind Bet\n");
        p("Balance: " + getBal() + "\n");
        p("1. Call 50\n2. Raise\n3. Fold\n");
        r = Keyboard.readInt();
        if (r == 1) {
          if (getBal()>50){
            removeFromBal(50);
            addToPot(50);
            p("Adding 50 to pot.\nYour balance: " + getBal());
            opp.removeFromBal(50);
            addToPot(50);
            p("\nOpponent added 50 to pot!\nOpponent balance: " + opp.getBal());
            p("Pot: " + getPot() + "\n");
          }
          else {
            opp.setBettingOff();
            int allIn = getBal();
            addToPot(allIn);
            removeFromBal(allIn);
            p("Adding "+ allIn + " to pot.\nYour balance: "+ getBal());
            opp.removeFromBal(allIn);
            addToPot(allIn);
            p("\nOpponent added "+ allIn + " to pot!\nOpponent balance: " + opp.getBal());
            p("Pot: " + getPot() + "\n");
          }
          break;
        }
        else if (r == 2) {
          opp.addToRaiseCnt();
          p("\nHow much would you like to raise the bet?\n");
          r = Keyboard.readInt();
          while (r > getBal() || r > opp.getBal()){
            p("Amount exceeds balance. Please enter a valid amount.");
            r = Keyboard.readInt();
          }
          removeFromBal(r);
          addToPot(r);
          p("Adding "+r+" to pot.\nYour balance: " + getBal());
          opp.removeFromBal(r);
          addToPot(r);
          p("Opponent also added "+r+" to pot.\nOpponent balance: " + opp.getBal());
          p("Pot: " + getPot() + "\n");
          if (opp.blindBet()){
            p("Opponent wants to raise 50.\n1. Call 50\n2. Fold\n");
            r = Keyboard.readInt();
            if (r==1 && getBal()>=50){
              removeFromBal(50);
              addToPot(50);
              p("Adding 50 to pot.\nYour balance: " + getBal());
              opp.removeFromBal(50);
              addToPot(50);
              p("Opponent also added 50 to pot.\nOpponent balance: " + opp.getBal());
              p("Pot: " + getPot() + "\n");
            }
            if (r==1 && getBal()<50){
              p("Amount exceeds balance.");
              r = 2;
            }
            if (r==2){
              p("\nDiscarding pocket...");
              discardPocket();
              opp.discardPocket();
              opp.addToBal(getPot());
              addToPot(getPot()*-1);
              continue;
            }
          }
          break;
        }
        else {
          p("\nDiscarding pocket...");
          discardPocket();
          opp.discardPocket();
          continue;
        }
      }

      //flop
      setBoard();
      opp.manualSetBoard(board.get(0), board.get(1), board.get(2));
      p("\nFlop\n");
      p("Board: \n" + getBoard() + "\n");
      p("Pocket: \n" + getHand() + "\n");
      p("Pot: " + getPot());
      p("Balance: " + getBal() + "\n");
      p("1. Check\n2. Raise\n3. Fold\n");
      r = Keyboard.readInt();
      if (r == 2) {
        opp.addToRaiseCnt();
        p("\nHow much would you like to raise the bet?\n");
        r = Keyboard.readInt();
        while (r > getBal() || r > opp.getBal()){
          p("Amount exceeds balance. Please enter a valid amount.");
          r = Keyboard.readInt();
        }
        if (opp.foldChance()){
          p("Opponent has folded.");
          p("\nDiscarding pocket...");
          discardPocket();
          opp.discardPocket();
          addToBal(getPot());
          addToPot(getPot()*-1);
          continue;
        }
        else{
          removeFromBal(r);
          addToPot(r);
          p("Adding "+r+" to pot.\nYour balance: " + getBal());
          opp.removeFromBal(r);
          addToPot(r);
          p("Opponent also added "+r+" to pot.\nOpponent balance: " + opp.getBal());
          p("Pot: " + getPot() + "\n");
        }
      }
      if (r == 3) {
        p("\nDiscarding pocket...");
          discardPocket();
          opp.discardPocket();
          opp.addToBal(getPot());
          addToPot(getPot()*-1);
          continue;
      }
      if (opp.raiseChance() && getBal() > 100){
          p("Opponent wants to raise 100.\n1. Call 100\n2. Fold");
          r = Keyboard.readInt();
          if (r==1 && getBal()>=100){
            removeFromBal(100);
            addToPot(100);
            p("Adding 100 to pot.\nYour balance: " + getBal());
            opp.removeFromBal(100);
            addToPot(100);
            p("Opponent also added 100 to pot.\nOpponent balance: " + opp.getBal());
            p("Pot: " + getPot() + "\n");
          }
          if (r==1 && getBal()<100){
            p("Amount exceeds balance.");
            r = 2;
          }
          if (r==2){
            p("\nDiscarding pocket...");
            discardPocket();
            opp.discardPocket();
            opp.addToBal(getPot());
            addToPot(getPot()*-1);
            continue;
          }
      }

      //turn
      addToBoard();
      opp.manualAddToBoard(board.get(3));
      p("\nTurn\n");
      p("Board: \n" + getBoard() + "\n");
      p("Pocket: \n" + getHand() + "\n");
      p("Pot: " + getPot());
      p("Balance: " + getBal() + "\n");
      p("1. Check\n2. Raise\n3. Fold\n");
      r = Keyboard.readInt();
      if (r == 2) {
        opp.addToRaiseCnt();
        p("\nHow much would you like to raise the bet?\n");
        r = Keyboard.readInt();
        while (r > getBal() || r > opp.getBal()){
          p("Amount exceeds balance. Please enter a valid amount.");
          r = Keyboard.readInt();
        }
        if (opp.foldChance()){
          p("Opponent has folded.");
          p("\nDiscarding pocket...");
          discardPocket();
          opp.discardPocket();
          addToBal(getPot());
          addToPot(getPot()*-1);
          continue;
        }
        else{
          removeFromBal(r);
          addToPot(r);
          p("Adding "+r+" to pot.\nYour balance: " + getBal());
          opp.removeFromBal(r);
          addToPot(r);
          p("Opponent also added "+r+" to pot.\nOpponent balance: " + opp.getBal());
          p("Pot: " + getPot() + "\n");
        }
      }
      if (r == 3) {
        p("\nDiscarding pocket...");
          discardPocket();
          opp.discardPocket();
          opp.addToBal(getPot());
          addToPot(getPot()*-1);
          continue;
      }
      if (opp.raiseChance() && getBal() > 100){
          p("Opponent wants to raise 100.\n1. Call 100\n2. Fold");
          r = Keyboard.readInt();
          if (r==1 && getBal()>=100){
            removeFromBal(100);
            addToPot(100);
            p("Adding 100 to pot.\nYour balance: " + getBal());
            opp.removeFromBal(100);
            addToPot(100);
            p("Opponent also added 100 to pot.\nOpponent balance: " + opp.getBal());
            p("Pot: " + getPot() + "\n");
          }
          if (r==1 && getBal()<100){
            p("Amount exceeds balance.");
            r = 2;
          }
          if (r==2){
            p("\nDiscarding pocket...");
            discardPocket();
            opp.discardPocket();
            opp.addToBal(getPot());
            addToPot(getPot()*-1);
            continue;
          }
      }

      //river
      addToBoard();
      opp.manualAddToBoard(board.get(4));
      p("\nRiver\n");
      p("Board: \n" + getBoard() + "\n");
      p("Pocket: \n" + getHand() + "\n");
      p("Pot: " + getPot());
      p("Balance: " + getBal() + "\n");
      p("1. Check\n2. Raise\n3. Fold\n");
      r = Keyboard.readInt();
      if (r == 2) {
        opp.addToRaiseCnt();
        p("\nHow much would you like to raise the bet?\n");
        r = Keyboard.readInt();
        while (r > getBal() || r > opp.getBal()){
          p("Amount exceeds balance. Please enter a valid amount.");
          r = Keyboard.readInt();
        }
        if (opp.foldChance()){
          p("Opponent has folded.");
          p("\nDiscarding pocket...");
          discardPocket();
          opp.discardPocket();
          addToBal(getPot());
          addToPot(getPot()*-1);
          continue;
        }
        else{
          removeFromBal(r);
          addToPot(r);
          p("Adding "+r+" to pot.\nYour balance: " + getBal());
          opp.removeFromBal(r);
          addToPot(r);
          p("Opponent also added "+r+" to pot.\nOpponent balance: " + opp.getBal());
          p("Pot: " + getPot() + "\n");
        }
      }
      if (r == 3) {
        p("\nDiscarding pocket...");
          discardPocket();
          opp.discardPocket();
          opp.addToBal(getPot());
          addToPot(getPot()*-1);
          continue;
      }
      if (opp.raiseChance() && getBal() > 100){
          p("Opponent wants to raise 100.\n1. Call 100\n2. Fold");
          r = Keyboard.readInt();
          if (r==1 && getBal()>=100){
            removeFromBal(100);
            addToPot(100);
            p("Adding 100 to pot.\nYour balance: " + getBal());
            opp.removeFromBal(100);
            addToPot(100);
            p("Opponent also added 100 to pot.\nOpponent balance: " + opp.getBal());
            p("Pot: " + getPot() + "\n");
          }
          if (r==1 && getBal()<100){
            p("Amount exceeds balance.");
            r = 2;
          }
          if (r==2){
            p("\nDiscarding pocket...");
            discardPocket();
            opp.discardPocket();
            opp.addToBal(getPot());
            addToPot(getPot()*-1);
            continue;
          }
      }
      break;
    }

    //showdown
    while(true){
      p("\nShowdown!\n");
      p("Best possible hand: \n" + bestPossibleHand());
      p("Hit 1 to reveal opponent hand\n");
      r = Keyboard.readInt();
      p("Opponent hand: \n" + opp.bestPossibleHand());
      if (getBestComboVal() > opp.getBestComboVal()){
        setWin(true);
        p("Congratulations, you won!");
        addToBal(getPot());
        p("Balance: " + getBal());
        break;
      }
      if (getBestComboVal() == opp.getBestComboVal()){
        setWin(true);
        p("Draw!");
        addToBal(getPot()/2);
        p("Balance: " + getBal()); //should be the same as what you started out with
        break;
      }
      else {
        setWin(false);
        p("You lost! Better luck next time :^(");
        p("Balance: " + getBal());
        break;
      }
    }
}




//----------------------------methods for testing-----------------------------

//----setHand method for testing getComboVal method-----
  public void setHand(Card a, Card b){
    discardPocket();
    hand.add(a);
    hand.add(b);
  }

//-manualSetBoard method for testing getComboVal method-
  public void manualSetBoard(Card a, Card b, Card c){
      for(int i = board.size(); i > 0 ; i--){
        board.remove(0);
      }
      board.add(a);
      board.add(b);
      board.add(c);
  }

//main
  public static void main(String[] args){
    TexasHoldEm specialK = new TexasHoldEm(12);
    System.out.println(specialK.getInstructions());
    specialK.deal();
    System.out.println("Pocket: \n" + specialK.getHand());
    specialK.setBoard();
    System.out.println("~bets are placed~");
    specialK.addToBoard();
    System.out.println("~bets are placed~");
    specialK.addToBoard();
    System.out.println("~bets are placed~");
    System.out.println("Board: \n" + specialK.getBoard());
    System.out.println("Best possible hand: \n" + specialK.bestPossibleHand());
    System.out.println("Poor Kathleen lost her family fortune. On to the next round.");

    Card a = new Card(10,3);
    Card b = new Card(11,3);
    Card c = new Card(12,3);
    Card d = new Card(13,3);
    Card e = new Card(1,3);
    specialK.setHand(d,e);
    System.out.println("Pocket: \n" + specialK.getHand());
    specialK.manualSetBoard(a,b,c);
    System.out.println("Board: \n" + specialK.getBoard());
    System.out.println("Hand value: " + specialK.getComboVal(a,b,c,d,e));
    System.out.println("Best possible hand: " + specialK.bestPossibleHand());
  }

}
