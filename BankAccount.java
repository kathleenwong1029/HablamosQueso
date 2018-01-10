public class BankAccount{

  int bal;

  //constructor
  public BankAccount(){
    bal = 0;
  }

  //accessor
  public int getBal(){
    return bal;
  }

  //deposit
  public void deposit( int amount ) {
    bal = bal + amount;
  }

  //withdraw
  public String withdraw( int amount ){
    if (amount <= bal){
       bal = bal - amount;
       return "New balance: " + bal;
    }
    else{
       bal = bal - amount;
       return "New balance: " + bal + "\nBalance is below 0!";
    }
  }

/*------------------------main method for testing------------------------
  public static void main (String[] args){
    BankAccount longwong = new BankAccount();
    System.out.println(longwong.getBal());
    longwong.deposit(50);
    System.out.println(longwong.getBal());
    System.out.println(longwong.withdraw(100));
  }
*/

}