public class Card {
    public int value;
    public String symbol;
    public Card(int v,int s ) {
	value=v;
	if(s==0) {
	    symbol="clubs";
	}
	else if (s==1) {
	    symbol="diamonds";
	}
	else if (s==2) {
	    symbol="hearts";
	}
	else{
	    symbol="spades";
	}
	
    }
    public int getValue() {
	return value;
    }
    public String getSymbol() {
	return symbol;
    }
    public String toString() {
	if (value<11 && value>1) {
	    return value+" of "+symbol;
	}
	else if(value==11) {
	    return "J of "+symbol;
		}
	else if(value==12) {
	    return "Q of "+symbol;
	}
	else if(value==13) {
	    return "K of "+symbol;
	}
	else {
	    return "A of "+symbol;
	}
    }
}
