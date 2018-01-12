import cs1.Keyboard;
public class TexasHoldEm extends CardGame{

  public ArrayList<Card> board;
  public int pot;
  //HashMap for winning hands oooh fancy;

  //have to make sure I code something in case the deck runs out

//returns one random card from the deck
  public Card deal(){
    shuffle(deck);
    Card retCard = deck.get(0);
    deck.remove(0);
    return retCard;
  }

  public void setBoard(){
    //clear the board
    for(int i = board.size(); i > 0 ; i--){
      board.remove(0);
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
    specialK = new TexasHoldEm();
    System.out.println("Pocket: \n" + printArray(specialK.deal()));
    setBoard();
    System.out.println("Board: \n" + printArray(board));
    System.out.println("Pretend a round just happened");
    addToBoard();
    System.out.println("Board: \n" + printArray(board));
  }

}
