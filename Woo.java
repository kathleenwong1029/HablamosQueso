import cs1.Keyboard;
public class Woo {
    //instance var
    //public String hallOFFame;
    public static BankAccount myAccount;
    //constructor
    public Woo() {
	myAccount=new BankAccount();
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
        bob.deal(bob.deck);
	BlackjackOpponent tophie=new BlackjackOpponent(1);//dealer
	BlackjackOpponent pj=new BlackjackOpponent("BlackjackMeister6969");
	BlackjackOpponent kdove=new BlackjackOpponent("joe");
	tophie.deal(bob.deck);
	pj.deal(bob.deck);
	kdove.deal(bob.deck);
	tophie.AIDealer(bob.deck);
	Card h=tophie.hand.get(0);
	pj.AI(bob.deck,pj.hand,h);
	kdove.AI(bob.deck,pj.hand,h);
	bob.play();
	    System.out.println("Opponent Hands:");
	    System.out.println(tophie.toString());
	     System.out.println(pj.toString());
	      System.out.println(kdove.toString());
	      int total=bob.total;
	      boolean win=true;
	      if(total==21) {
		System.out.println("blackjack");
	    }
	    if(total>21) {
		win=false;
	    }
	    if(tophie.sum(tophie.hand)>total && tophie.win) {
		System.out.println("you lost to dealer!");
		win=false;
	    }
	    if(pj.sum(pj.hand)>total && pj.win) {
		System.out.println("you lost to BlackjackMeister");
		win=false;
	    }
	    if(kdove.sum(kdove.hand)>total && kdove.win ) {
		System.out.println("you lost to joe");
		win=false;
	    }
	      if (win) {
	    System.out.println("you won!");
	    myAccount.deposit(bob.bet);
	    bob.bet=0;
	}
	else {
	    System.out.println("you lost!");
	    myAccount.withdraw(bob.y);
	    bob.bet=0;
	}


	    System.out.println("play again? 1 for yes 2 for no");
	    u=Keyboard.readInt();
	    if(u==2) {
		bplay=false;
	    }
	}
    }

    public void playTexasHoldEm() {
      int earnings;
      TexasHoldEm specialK = new TexasHoldEm(viewBal());
      TexasOpponent yamyam = new TexasOpponent();
      specialK.play(yamyam);
      if(specialK.getWin()){
        earnings = specialK.getBal() - viewBal();
        myAccount.deposit(earnings);
      }
      else {
        earnings = viewBal() - specialK.getBal();
        myAccount.withdraw(earnings);
      }
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
      pjelly.play(pjelly.getTopCard());
        if (pjelly.win()){
          winner="You";
          break;
        }

      opp1.setTopCard2(pjelly);
      pjelly.deck=opp1.turn(opp1.getTopCard(),pjelly.deck);
      if (opp1.win()){
        winner=opp1.name;
        break;
      }

      opp2.setTopCard(opp1);
      pjelly.deck=opp2.turn(opp2.getTopCard(),pjelly.deck);
      if (opp2.win()){
        winner=opp2.name;
        break;
      }

      opp3.setTopCard(opp2);
      pjelly.deck=opp3.turn(opp3.getTopCard(),pjelly.deck);
      time+=1;
      if (opp3.win()){
        winner=opp3.name;
        break;
      }
    }
      System.out.println(winner + "won!");
      //if player lost, they lose ten dollars for every card they still have
      if(!(winner=="You")){
        int moneyLost=20*pjelly.lose();
        myAccount.withdraw(moneyLost);
        System.out.println("Sorry.You lost "+moneyLost+" dollars.");
      }
      //if player wins, they earn five hundred dollars
      else{
          myAccount.deposit(100);
          System.out.println("You won 1000 dollars!!!");
      }
    }
    public static void main(String[] args) {
	Woo jane=new Woo();
	System.out.println("Welcome to the casino! Your balance is 500 dollars, happy spending");
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

  if(user==3){
    jane.playTexasHoldEm();
  }

    String tier = "";
    if (myAccount.getBal()<0){
      tier="tinfoil";
      System.out.println("You owe money to the casino. You are no longer allowed to play. Adios amigo.");
      break;
    }
    if(myAccount.getBal()<2000){
      tier="gold";
    }
    if(myAccount.getBal()>=2000&&myAccount.getBal()<=8000){
      tier="platinum";
    }
    if(myAccount.getBal()>8000&&myAccount.getBal()<=22500){
      tier="diamond";
    }
    if(myAccount.getBal()>22500){
      tier="god";
      System.out.println("You have reached the highest tier in this casino. You are bankrupting the casino so goodbye!");
      break;
    }

  if (user==4) {
    System.out.println(myAccount);
    System.out.println("Your status is: "+ tier);
  }

	System.out.println("1. Continue playing \n2. Exit casino");
	int cas;
	cas=Keyboard.readInt();
	if(cas==2) {
	    System.out.println("Thanks for playing! Here are your stats:");
      System.out.println(myAccount);
      System.out.println("Your status is: "+ tier);
	    break;
	}
	}
    }
}
