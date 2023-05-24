package application;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Random;

/*
 * A Game szerializalasahoz hasznalt segedosztaly.
 */
public class GameData implements Serializable {
	private static final long serialVersionUID = 1L;
	private int repairmanScore = Game.getRepairmanScore();
	private int saboteurScore = Game.getSaboteurScore();
	private List<Field> fieldList = Game.getFieldList();
	private List<Player> playerList = Game.getPlayerList();
	private Random random = Game.getRandom();
	private boolean randomDisabled = Game.isRandomDisabled();
	
	private List<FieldView> fieldViewList = Game.getFieldViewList();
	private List<PlayerView> playerViewList = Game.getPlayerViewList();
	private String text = Game.getInfoTextArea().getText();
	private Game game = Game.getGame();
	private Map<Field, FieldView> fieldMap = Game.getFieldMap();
	private Map<Player, PlayerView> playerMap = Game.getPlayerMap();
	protected int jatekosKovetkezik = Game.jatekosKovetkezik;
	protected int state = Game.state;
	
	/*
	 * Getter.
	 */
	public int getRepairmanScore() {
		return repairmanScore;
	}
	
	/*
	 * Getter.
	 */
	public int getSaboteurScore() {
		return saboteurScore;
	}
	
	/*
	 * Getter.
	 */
	public List<Field> getFieldList() {
		return fieldList;
	}
	
	/*
	 * Getter.
	 */
	public List<Player> getPlayerList() {
		return playerList;
	}
	
	/*
	 * Getter.
	 */
	public Random getRandom() {
		return random;
	}
	
	/*
	 * Getter.
	 */
	public boolean isRandomDisabled() {
		return randomDisabled;
	}
	
	/*
	 * Getter.
	 */
	public List<FieldView> getFieldViewList() {
		return fieldViewList;
	}
	
	/*
	 * Getter.
	 */
	public List<PlayerView> getPlayerViewList() {
		return playerViewList;
	}
	
	/*
	 * Getter.
	 */
	public Game getGame() {
		return game;
	}
	
	/*
	 * Getter.
	 */
	public Map<Field, FieldView> getFieldMap() {
		return fieldMap;
	}
	
	/*
	 * Getter.
	 */
	public Map<Player, PlayerView> getPlayerMap() {
		return playerMap;
	}
	
	/*
	 * Getter.
	 */
	public String getText() {
		return text;
	}
}
