package application;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.JTextArea;

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
	private JTextArea infoTextArea  = Game.getInfoTextArea();
	private Canvas canvas = Game.GetCanvas();
	private JPanel bottomPanel = Game.getBottomPanel();
	private Game game = Game.getGame();
	private Map<Field, FieldView> fieldMap = Game.getFieldMap();
	private Map<Player, PlayerView> playerMap = Game.getPlayerMap();
	protected int jatekosKovetkezik = Game.jatekosKovetkezik;
	protected int state = Game.state;
	
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
	public List<FieldView> getFieldViewList() {
		return fieldViewList;
	}
	public List<PlayerView> getPlayerViewList() {
		return playerViewList;
	}
	public JTextArea getInfoTextArea() {
		return infoTextArea;
	}
	public Canvas getCanvas() {
		return canvas;
	}
	public JPanel getBottomPanel() {
		return bottomPanel;
	}
	public Game getGame() {
		return game;
	}
	public Map<Field, FieldView> getFieldMap() {
		return fieldMap;
	}
	public Map<Player, PlayerView> getPlayerMap() {
		return playerMap;
	}
}
