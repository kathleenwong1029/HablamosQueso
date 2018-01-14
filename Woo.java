import cs1.Keyboard;
public class Woo {
    //instance var
    public String hallOFFame;
    public static BankAccount myAccount;
    //constructor
    public Woo() {
	myAccount=new BankAccount();



    }
    public Woo(int game, int difficulty, int balance, int numOfOpponents) {


    }
    //methods
    public static int viewBal() {
	return myAccount.getBal();
    }

    public void playBlackjack() {
	boolean bplay=true;

	//future for computer opponent(3) of them
	//BlackjackOpponent tophie=new BlackjackOpponent();
	//BlackjackOpponent rob=new BlackjackOpponent();
	//BlackjackOpponent winpls=new BlackjackOpponent;


	while(bplay==true) {
	    Blackjack bob=new Blackjack();
	int u=1;
	System.out.println(bob.getInstructions());
	int gambleamt=bob.gamble();
        bob.deal();
	bob.play();
	if (bob.win) {
	    System.out.println("you won!");
	    myAccount.deposit(gambleamt);
	}
	else {
	    System.out.println("you lost!");
	    myAccount.withdraw(gambleamt);
	}


	    System.out.println("play again? 1 for yes 2 for no");
	    u=Keyboard.readInt();
	    if(u==2) {
		bplay=false;
	    }
	}
    }

    public void playTexasHoldEm() {
    }

    public void playCrazyEights() {
	/* boolean won = false;
      String winner;
      CrazyEight pjelly= new CrazyEight ();
      CrazyEightOpponent opp1 = new CrazyEightOpponent(opp1);
      CrazyEightOpponent opp2 = new CrazyEightOpponent(opp2);
      CrazyEightOpponent opp3 = new CrazyEightOpponent(opp3);
      pjelly.deal();
      System.out.println(pjelly.getInstructions());
      while(won== false){
      Card stockpile=
      opp1.turn(stockpile);
      if (opp1.winner ==true){
        winner=opp1.name;
        break;
      }
      // opp2.turn(stockpile);
      // if (opp2.winner ==true){
      //   winner=opp2.name;
      //   break;
      // }
      // opp3.turn(stockpile);
      // if (opp3.winner ==true){
      //   winner=opp3.name;
      //   break;
      // }
      pjelly.win(stockpile);
      if (pjelly.winner ==true){
        winner=pjelly;
        break;
      }

    }
      System.out.println(winner + "won!");
	*/
    }
    public static void main(String[] args) {
	Woo jane=new Woo();
	System.out.println("Welcome to the casino!");
	boolean cplay=true;
	while(cplay==true) {
	System.out.println("choose your game/action");
	System.out.println("1. Blackjack");
	System.out.println("2. Crazy Eights");
	System.out.println("3. TexasHoldEm");
	System.out.println("4. view your balance");
	int user;
	user=Keyboard.readInt();
	if (user==1) {
	    jane.playBlackjack();
	}
  if( user==2){
    jane.playCrazyEights();
  }
  if (user==4) {
      System.out.println(myAccount);
	  }
	System.out.println("1. continue playing \n 2. Exit casino");
	int cas;
	cas=Keyboard.readInt();
	if(cas==2) {
	    System.out.println("Thanks for playing! Here are your stats:");
	    break;
	}
	}
    }
}
