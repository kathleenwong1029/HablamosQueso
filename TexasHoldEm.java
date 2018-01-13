import cs1.Keyboard;
import java.util.ArrayList;
import java.util.HashMap;
public class TexasHoldEm extends CardGame{

  public ArrayList<Card> board = new ArrayList<Card>();
  public int pot;
  public HashMap<String, Integer> possibleCombos = new HashMap<String, Integer>();

  //have to make sure I code something in case the deck runs out

//constructy
  public TexasHoldEm(){
    super();
    //add all possible combos to possibleCombo HashMap
    //Royal Flush
    possibleCombos.put("AKQJ10", 10);
    //Straight Flush
      //have to hardcode to differentiate between Straight Flush and Straight
    for (int i = 0; i < 9; i++){
      String key = "";
      key = "x" + i + (i+1) + (i+2) + (i+3) + (i+4);
      possibleCombos.put(key, 9);
    }
    //Four of a Kind
    for (int i = 0; i < 14; i++){
      for (int x = 0; x < 14; x++){
        String key = "";
        key = i + i + i + i + x;
        possibleCombos.put(key, 8);
      }
    }
    //Full House
    for (int i = 0; i < 14; i++){
      for (int x = 0; x < 14; x++){
        String key = "";
        key = i + i + i + x + x;
        possibleCombos.put(key, 7);
      }
    }
    //Flush
      //have to hardcode in win
    //Straight
    for (int i = 0; i < 9; i++){
      String key = "";
      key = "x" + i + (i+1) + (i+2) + (i+3) + (i+4);
      possibleCombos.put(key, 9);
    }
    //hardcode three of a kind, two pairs, pair, and high card
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

  public int getComboVal(){
    ArrayList<Card> combi = new ArrayList<Card>();
    for (Card c: board){
      combi.add(c);
    }
    for (Card c: hand){
      combi.add(c);
    }
    String combo = printArray(combi);

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
