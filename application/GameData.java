package application;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class GameData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int repairmanScore = Game.getRepairmanScore();
	private int saboteurScore = Game.getSaboteurScore();
	private List<Field> fieldList = Game.getFieldList();
	private List<Player> playerList = Game.getPlayerList();
	private Random random = Game.getRandom();
	private boolean randomDisabled = Game.isRandomDisabled();
	
	public int getRepairmanScore() {
		return repairmanScore;
	}
	public int getSaboteurScore() {
		return saboteurScore;
	}
	public List<Field> getFieldList() {
		return fieldList;
	}
	public List<Player> getPlayerList() {
		return playerList;
	}
	public Random getRandom() {
		return random;
	}
	public boolean isRandomDisabled() {
		return randomDisabled;
	}
}
