package application;

import java.io.Serializable;

public class Water implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int staticId = 0;
	private int id = staticId++;
	
	@Override
	public String toString() {
		return "water" + id;
	}
	
	// A viz elfolyik.
	public void goToWaste() {
		System.out.println(this + ".goToWaste()");
		Game.increaseSaboteurScore(1);
	}
	
	// A viz a ciszternaba jut.
	public void goToCistern() {
		System.out.println(this + ".goToCistern()");
		Game.increaseRepairmanScore(1);
	}
}
