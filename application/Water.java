package application;

public class Water {
	
	// A viz elfolyik.
	public void goToWaste() {
		System.out.println("Water.goToWaste()");
		Game.increaseSaboteurScore(0);
	}
	
	// A viz a ciszternaba jut.
	public void goToCistern() {
		System.out.println("Water.goToCistern()");
		Game.increaseRepairmanScore(0);
	}
}