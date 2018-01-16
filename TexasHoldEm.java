import cs1.Keyboard;
import java.util.ArrayList;
public class TexasHoldEm extends CardGame{

  public ArrayList<Card> board = new ArrayList<Card>();
  public int pot;
  public int bal = 1000;

  //have to make sure I code something in case the deck runs out

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

//getInstrustions
  public String getInstructions(){
    return "Check back later for instructions \n";
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

  public void addtoBal(int x){
    bal += x;
  }

  public void removeFromBal(int x){
    bal -= x;
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

  //print shoutcut because I have so much to do and so little tiempo
  private static void p(String stuff) {
        System.out.println(stuff);
    }

  public void play(){
    int r; //player input

    //title screen
    String title = "Texas Hold'em\nEnter 1 for instructions.\nEnter 2 to continue to game.\n";
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
    //pre-flop
    r = 3;
    while(r==3){
      p("Dealing cards...\n");
      deal();
      p("Pocket: \n" + getHand());
      p("\nBlind Bet\n");
      p("Balance: " + getBal() + "\n");
      p("1. Check\n2. Raise\n3. Fold\n");
      r = Keyboard.readInt();
      if (r == 1) {
        removeFromBal(50);
        addToPot(50);
        p("Adding 50 to pot.\nYour balance: " + getBal() +"\nPot: " + getPot() + "\n");
        break;
      }
      else if (r == 2) {
        p("How much would you like to raise the bet?\n");
        r = Keyboard.readInt();
        while (r > 950){
          p("Amount exceeds your balance. Please enter a valid amount.");
          r = Keyboard.readInt();
        }
        removeFromBal(r);
        addToPot(r);
        p("Adding "+r+" to pot.\nYour balance: " + getBal() +"\nPot: " + getPot() + "\n");
        break;
      }
      else {
        discardPocket();
      }
    }

    //flop
    setBoard();
    p("\nFlop\n");
    p("Board: \n" + getBoard() + "\n");
    p("Pocket: \n" + getHand() + "\n");
    p("Pot: " + getPot());
    p("Balance: " + getBal() + "\n");
    p("1. Check\n2. Raise\n3. Fold\n");
    r = Keyboard.readInt();
    if (r == 2) {
      p("How much would you like to raise the bet?\n");
      r = Keyboard.readInt();
      while (r > getBal()){
        p("Amount exceeds your balance. Please enter a valid amount.");
        r = Keyboard.readInt();
      }
      removeFromBal(r);
      addToPot(r);
      p("Adding "+r+" to pot.\nYour balance: " + getBal() +"\nPot: " + getPot() + "\n");
    }
    if (r == 3) {
      discardPocket();
      p("Still finding a way to make the game reset");
    }

    //turn
    addToBoard();
    p("\nTurn\n");
    p("Board: \n" + getBoard() + "\n");
    p("Pocket: \n" + getHand() + "\n");
    p("Pot: " + getPot());
    p("Balance: " + getBal() + "\n");
    p("1. Check\n2. Raise\n3. Fold\n");
    r = Keyboard.readInt();
    if (r == 2) {
      p("How much would you like to raise the bet?\n");
      r = Keyboard.readInt();
      while (r > getBal()){
        p("Amount exceeds your balance. Please enter a valid amount.");
        r = Keyboard.readInt();
      }
      removeFromBal(r);
      addToPot(r);
      p("Adding "+r+" to pot.\nYour balance: " + getBal() +"\nPot: " + getPot() + "\n");
    }
    if (r == 3) {
      discardPocket();
      p("Still finding a way to make the game reset");
    }

    //river
    addToBoard();
    p("\nRiver\n");
    p("Board: \n" + getBoard() + "\n");
    p("Pocket: \n" + getHand() + "\n");
    p("Pot: " + getPot());
    p("Balance: " + getBal() + "\n");
    p("1. Check\n2. Raise\n3. Fold\n");
    r = Keyboard.readInt();
    if (r == 2) {
      p("How much would you like to raise the bet?\n");
      r = Keyboard.readInt();
      while (r > getBal()){
        p("Amount exceeds your balance. Please enter a valid amount.");
        r = Keyboard.readInt();
      }
      removeFromBal(r);
      addToPot(r);
      p("Adding "+r+" to pot.\nYour balance: " + getBal() +"\nPot: " + getPot() + "\n");
    }
    if (r == 3) {
      discardPocket();
      p("Still finding a way to make the game reset");
    }

    //showdown
    p("\nShowdown!\n");
    p("Best possible hand: \n" + bestPossibleHand());
    p("Since there's no opponent playing against you yet yay you win the whole pot! thx 4 play");
    addtoBal(getPot());
    p("Balance: " + getBal());
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
    TexasHoldEm specialK = new TexasHoldEm();
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
  }

}
