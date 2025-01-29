// Author: Tytus Felbor

package unoPackage;

public abstract class WildCard extends Card{

	protected WildCard(int color) {
		
		this.color = color;
		this.value = Card.wild;
		
	}
	
	protected boolean check(Table t) {
		
		if (this.color == Card.BLACK) {
			return true;
		}
		
		if (this.color == t.onTop.color) {
			return true;
		}
		else {
			return false;
		}	
	}	
}
