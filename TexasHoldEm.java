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
    ArrayList<Card> combi = new ArrayList<Card>();
    String combo = "";
    String values = "";
    String suits = "";
    for (Card c: board){
      combi.add(c);
    }
    for (Card c: hand){
      combi.add(c);
    }
    for (Card c: combi){
      combo += c.getValue(); //values are stored in even indicies
      combo += c.getSymbol(); //symbols are stored in odd indicies
    }
    for (Card c: combi){
      values += c.getValue();
    }
    for (Card c: combi){
      suits += c.getSymbol();
    }
    //tests for flush combos (require same suit)
    if (isConsistantString(suits)){
      //Royal Flush - 10 points
      if (sameChars(values, "1011121314")){
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
    //Four of A Kind - 8 points
    if (
    (sameChars("1111", values) && values.length() <= 6) ||
    (sameChars("2222", values) && values.length() <= 6) ||
    (sameChars("3333", values) && values.length() <= 6) ||
    (sameChars("4444", values) && values.length() <= 6) ||
    sameChars("5555", values) ||
    sameChars("6666", values) ||
    sameChars("7777", values) ||
    sameChars("8888", values) ||
    sameChars("9999", values) ||
    sameChars("10101010", values) ||
    sameChars("11111111", values) ||
    sameChars("12121212", values) ||
    sameChars("13131313", values) ||
    sameChars("14141414", values)
    )
    {return 8;}
/* THIS IS TOO HARD -- TOO MANY EXCEPTIONS USING THIS METHOD
    //Three of A Kind - 4 points
    if (values.length() <= 7){
      for (int c = 0; c < 10; c++){ //single digit value triplets
        String repeated = String.valueOf(c) + String.valueOf(c) + String.valueOf(c);
        //exception: hand = "11, 1, x, x, x"
        if (sameChars(repeated, values)){ //if values contains the triplet
          String temp = "";
          int rmCounter = 3;
          for (int i = 0; i < values.length(); i++){
            if (values.substring(i,i+1).equals(String.valueOf(c))){
              rmCounter--;
            }
            else {
              temp += values.substring(i,i+1);
            } //all values except for repeated are added to temp
            if (rmCounter == 0){
              if (i != values.length()-1){
                temp += values.substring(i+1);
              }
              break;
            } //if triplet removed, add the rest of the values string to temp and break
          }
          //Full House - 7 points
          if (temp.length() == 2 && isConsistantString(temp)){
            return 7;
          } //if temp only has two values and they are a pair, then Full House
          if (temp.length() == 4){
            int c1Counter = 0;
            int c2Counter = 0;
            String c2 = "";
            for (int i = 1; i < 4; i++){
              if (temp.substring(i,i+1).equals(temp.substring(0,1))) {
                c1Counter++;
              } //if current char is equal to first, c1Counter++
              if (temp.substring(i,i+1).equals(c2)){
                c2Counter++;
              } //if current char is equal to c2, c2Counter++
              else{
                c2 = temp.substring(i,i+1);
                c2Counter = 0;
              } //else set c2 to current char and reset c2Counter
            }
            if (c1Counter == c2Counter || c1Counter == 3){
              return 7;
            } //if 1 match with c1 and 1 match with c2 (two pairs), or the other 3 chars match with the first (two pairs of 11), then Full House
          }
          return 4; //return 4 if not a Full House, but Three of a Kind
        }
      }
    }
    else {
      for (int c = 10; c < 15; c++) { //for triplets of double digit values
        String repeated = String.valueOf(c) + String.valueOf(c) + String.valueOf(c);
        int rmCounter = 3;
        for (int i = 0; i < values.length(); i++){
          if (values.indexOf(String.valueOf(c)) != -1){
              rmCounter--;
            }
            else {
              temp += values.substring(i,i+1);
            } //all values except for repeated are added to temp
            if (rmCounter == 0){
              if (i != values.length()-1){
                temp += values.substring(i+1);
              }
              break;
            } //if triplet removed, add the rest of the values string to temp and break
        }
      }
    }
*/

    //Two Pair - 3 points --> nested if?
    //Pair - 2 points
    //Straight - 5 points
    if (isConsecutiveString(values) || sameChars(values,"678910") || sameChars(values,"7891011") || sameChars(values,"89101112") || sameChars(values,"910111213")){
      return 5;
    }
    //if no combo achieved by any player (everyone has 1 point, then win is asserted off of who has the highest value card)
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
  public boolean sameChars(String s, String t){
    String baseline = t;
    for (int i = 0; i < s.length(); i++){
      String c = s.substring(i,i+1); //current character
      if (baseline.contains(c){
        baseline = baseline.substring(0,baseline.indexOf(c)) + baseline.substring(baseline.indexOf(c)+1);
      }
      else {return false;}
    }
    return true;
  }


  public boolean win(Object other){
    if (this.getComboVal() > other.getComboVal()){
      return true;
    }
    else{ return false; }
  }

  public boolean draw(Object other){
    if (this.getComboVal() == other.getComboVal()){
      return true;
    }
    else { return false; }
  }

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
    specialK.setBoard();
    System.out.println("Board: \n" + specialK.getBoard());
  }

}
