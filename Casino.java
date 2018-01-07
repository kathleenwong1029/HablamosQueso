import cs1.Keyboard;
public class Casino {
    //instance var
    
    //constructor
    public Casino() {
	//BankAccount default values

	
    }
    public Casino(int game, int difficulty, int balance, int numOfOpponents) {
	//modify BankAccount
	//make var for selected game
	
    }
    //methods
    public int viewBal() {
	return 0;
    }
    public void playBlackjack() {
	boolean bplay=true;
	Blackjack bob=new Blackjack();
	int u=1;
	System.out.println(bob.getInstructions());
	while(bplay==true) {
	    Blackjack rob=new Blackjack();
	    rob.deal();
	    System.out.println("These are your cards"+rob.printArray(rob.hand));
	    while(u==1) {
	    System.out.println("Ask dealer for other card?");
	    System.out.println("1 for yes, 2 for no");
	    u=Keyboard.readInt();
	    if(u==1) {
		rob.addtohand();
	    }
	    System.out.println("These are your cards"+rob.printArray(rob.hand));
	    }
	    System.out.println("These are your cards"+rob.printArray(rob.hand));
	    System.out.println("The sum of your cards:"+rob.sum());
	    if(rob.sum()>21) {
		System.out.println("OVERFLOW");
	    }
	    else if(rob.sum()==21) {
		System.out.println("blackjack");
	    }
	    else {
		System.out.println("close");
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
    }
    public static void main(String[] args) {
	Casino jane=new Casino();
	System.out.println("Welcome to the casino!");
	boolean cplay=true;
	while(cplay==true) {
	System.out.println("choose your game");
	System.out.println("1. Blackjack");
	System.out.println("2. Crazy Eights");
	System.out.println("3. TexasHoldEm");
	int user;
	user=Keyboard.readInt();
	if (user==1) {
	    jane.playBlackjack();
	}
	System.out.println("1. continue playing \n 2. Exit casino");
	int cas;
	cas=Keyboard.readInt();
	if(user==2) {
	    cplay=false;
	}
	}
    }
}
