import cs1.Keyboard;
import java.util.ArrayList;
import java.util.HashMap;
public class TexasHoldEm extends CardGame{

  public ArrayList<Card> board = new ArrayList<Card>();
  public int pot;

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

//getInstrustions
  public String getInstructions(){
    return "Check back later for instructions \n";
  }

//adds one random card from the deck to hand
  public void deal(){
    shuffle(deck);
    hand.add(deck.get(0));
    deck.remove(0);
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

  /*public void placeBet(int x){

  }*/

//the hard part
  public int getComboVal(){
    ArrayList<Card> combo = new ArrayList<Card>();
    String values = "";
    String suits = "";
    for (Card c: board){
      combo.add(c);
    }
    for (Card c: hand){
      combo.add(c);
    }
    for (Card c: combo){
      values += c.getValue();
    }
    for (Card c: combo){
      suits += c.getSymbol();
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
      if (s.substring(i,i+1) != lastChar){
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

/*-----maybe it would be easier to place this in woo-----
  public boolean win(Object other){
    if (this.getComboVal() > other.getComboVal()){
      return true;
    }
    return false;
  }

  public boolean draw(Object other){
    if (this.getComboVal() == other.getComboVal()){
      return true;
    }
    return false;
  }

//should be giving this function the highest card in your hand in the case that no player has a playable hand

  public boolean highCard(Card c, Card other){
    if (c.getValue() > other.getValue()) {return true;}
    return false;
  }

  public boolean draw(Card c, Card other){
    if (c.getValue() == other.getValue()) {return true;}
    return false;
  }
*/

//-------------------------test-------------------------
  public static void main(String[] args){
    TexasHoldEm specialK = new TexasHoldEm();
    System.out.println(specialK.getInstructions());
    specialK.deal();
    specialK.deal();
    System.out.println("Pocket: \n" + specialK.getHand());
    specialK.setBoard();
    System.out.println("Board: \n" + specialK.getBoard());
    System.out.println("~bets are placed~");
    specialK.addToBoard();
    System.out.println("Board: \n" + specialK.getBoard());
    System.out.println("~bets are placed~");
    specialK.addToBoard();
    System.out.println("Board: \n" + specialK.getBoard());
    System.out.println("~bets are placed~");
    System.out.println("Poor Kathleen lost her family fortune. On to the next round.");
    specialK.discardPocket();
    specialK.deal();
    specialK.deal();
    specialK.setBoard();
    System.out.println("Pocket: \n" + specialK.getHand());
    System.out.println("Board: \n" + specialK.getBoard());
    System.out.println("Hand value: " + specialK.getComboVal());
  }

}
