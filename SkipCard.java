// Author: Tytus Felbor

package unoPackage;

public abstract class SkipCard extends Card {

	protected void Skipcard(int color) {
		
		this.color = color;
		this.value = Card.skip;
	}
	
	protected boolean check(Table t){
		
		if (t.onTop.color == this.color) {
			
			return true;
			
		}
		else {
			
			return false;
		}		
	}	
}
