import cs1.Keyboard;
import java.util.ArrayList;
public class TexasHoldEm extends CardGame{

  public ArrayList<Card> board = new ArrayList<Card>();
  public int pot;
  //HashMap for winning hands oooh fancy;

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
