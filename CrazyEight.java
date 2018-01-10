import cs1.Keyboard;
public class CrazyEight extends CardGame{
  private int deckLength;
  private boolean isValidMove = false;
  private boolean winner =false;
  private boolean drawAgain;
  private int cardNum=5;
  //constructor
  public CrazyEight(){
    super();
    hand = new Card [5];
    shuffle(deck);
  }

  //deals five cards
  public void deal(){
     hand[0]=deck[0];
	   hand[1]=deck[1];
     hand[2]=deck[2];
	   hand[3]=deck[3];
     hand[4]=deck[4];
     deckLength = 5;
  }

  //draw
  public void drawCard(){
    Card[] newHand=new Card[deckLength+1] ;
    for(int i=0;i<deckLength;i++){
      newHand[i]=hand[i];
    }
    newHand[deckLength]=deck[cardNum];
    hand=newHand;
    deckLength++;
  }

  public boolean win(){
    return (deckLength==0);
  }

  public String win(Card other){
    int a = deckLength-1;
    //if player has no usable cards, they are forced to draw
    while(isValidMove==false){
    while(a>0){
      if(hand[a].value==8||hand[a].value==other.value||hand[a].symbol==other.symbol){
        isValidMove=true;
        break;
      }
      if (a==0){
        drawCard();
        cardNum+=1;
        System.out.println("Here's your new deck:");
      }
      a--;
    }}

    while(winner == false){
      System.out.println(printArray(hand));
      while(!(drawAgain==true)) {
      System.out.println("Would you like to draw?");
      String draw =Keyboard.readString();
      if(draw.equals("yes")){
        drawCard();
        cardNum+=1;
      }
      System.out.println(printArray(hand));
      if(draw.equals("no")){
        drawAgain=true;
      }
    }
    drawAgain=false;
      //allows player to choose which card to play
      System.out.println("Which card would you like to discard?");
      int user;
      user=Keyboard.readInt();
      if(hand[user].value==other.value || hand[user].symbol==other.symbol){
        Card[] nHand=new Card[deckLength-1];
        for(int i =0;i<user;i++){
          nHand[i]=hand[i];
        }
        for(int i =hand.length-1;i>user;i--){
          nHand[i-1]=hand[i];
        }
        hand = nHand;
        deckLength-=1;
        if(win()==true){
          winner=true;
          break;
        }
        System.out.println(printArray(hand));
      }
      else{
        System.out.println("Sorry, not a valid move. Try again!");
      }
    }
    return "Congratulations! You're winner!";
  }

  //instructions
  public String getInstructions(){
    String s = "The goal of the game is to use up all your cards.";
    s+= "You can discard a card only if it matches the rank of suit of the card on the discard pile.";
    s+="Playing an 8 allows you to declare any suit.";
    s+=" If no card is available, draw from the stockpile.";
    return s;
  }


}
