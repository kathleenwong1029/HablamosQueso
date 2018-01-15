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


	while(bplay==true) {
	    Blackjack bob=new Blackjack();
	int u=1;
	System.out.println(bob.getInstructions());
	int gambleamt;
        bob.deal();
	bob.play();
	if (bob.win) {
	    System.out.println("you won!");
	    myAccount.deposit(bob.y);
	}
	else {
	    System.out.println("you lost!");
	    myAccount.withdraw(bob.y);
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
	    boolean won = false;
      String winner="";
      int time = 0;

      //instantiaing user
      CrazyEight pjelly= new CrazyEight();
      //instantiaing three computer opponents
      CrazyEightOpponent opp1 = new CrazyEightOpponent("opp1");
      CrazyEightOpponent opp2 = new CrazyEightOpponent("opp2");
      CrazyEightOpponent opp3 = new CrazyEightOpponent("opp3");
      //print initial instructions
      System.out.println(pjelly.getInstructions());
      //plays game
      while(won== false){
      //for the first turn, the suit and topCard will be null;
      if(time>0){
        pjelly.setTopCard(opp3);
      }
      pjelly.win(pjelly.getTopCard());
        if (pjelly.winner ==true){
          winner="You";
          won=true;
        }

      opp1.setTopCard2(pjelly);
      opp1.turn(opp1.getTopCard());
      if (opp1.winner ==true){
        winner=opp1.name;
        won=true;
      }

      opp2.setTopCard(opp1);
      opp2.turn(opp2.getTopCard());
      if (opp2.winner ==true){
        winner=opp2.name;
        won=true;
      }

      opp3.setTopCard(opp2);
      opp3.turn(opp3.getTopCard());
      time+=1;
      if (opp3.winner ==true){
        winner=opp3.name;
        won=true;
      }
    }
      System.out.println(winner + "won!");

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
