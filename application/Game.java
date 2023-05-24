package application;

import java.awt.BorderLayout;
import java.awt.Dimension;
//import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.*;

/*
 * A logikat mozgato es a megjelenest kezelo statikus osztaly.
 */
public class Game extends JFrame implements KeyListener, Serializable {
	private static final long serialVersionUID = 1L;
	private static int repairmanScore;
	private static int saboteurScore;
	private static List<Field> fieldList;
	private static List<Player> playerList;
	private static Random random = new Random();
	private static boolean randomDisabled = false;
	
	private static List<FieldView> fieldViewList = new ArrayList<FieldView>();
	private static List<PlayerView> playerViewList = new ArrayList<PlayerView>();
	private static JTextArea infoTextArea  = new JTextArea(10, 70);
	private static Canvas canvas = new Canvas();
	private static JPanel bottomPanel = new JPanel();
	private static Game game = new Game();
	private static Map<Field, FieldView> fieldMap;
	private static Map<Player, PlayerView> playerMap;
	protected static int jatekosKovetkezik = 0;
	protected static int state = 0;
	
	/*
	 * Konstruktor.
	 */
	public Game() {
		super("Pipemaster 1988");
		infoTextArea.addKeyListener(this);
		getContentPane().add(canvas, BorderLayout.CENTER);
		infoTextArea.setEditable(false);
		bottomPanel.add(infoTextArea);
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(new Dimension(800, 600));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		Music music = new Music();
		music.setDaemon(true);
		music.start();
	}
	
	/*
	 * Lepes vegrahajtasa a jatekban a megadott szam alapjan.
	 */
	public static void step(int number) {
		canvas.repaint();
		if (state == 0) {
			print("=====> Mit szeretnel csinalni?");
			
			println("1 Uj jatek inditasa.");
			println("2 Mentett jatek folytatasa.");
			
			println("egyeb Vegeztem, ki szeretnek lepni.");
			jatekosKovetkezik = 0;
			state = 1;
		} else if (state == 1) {
			if (number == 1) {
				init();
				autoKor();
				jatekostKerdez();
			} else if (number == 2) {
				load();
			} else {
				game.dispose();
			}
		} else if (state == 3) {
			jatekosCselekszik(number);
		} else if (state == 31) {
			var p = playerViewList.get(jatekosKovetkezik).returnPlayer();
			int validIndex = Game.normalToValidIndex(p.getLocation().getNeighbors().size(), number);
			Field targetField = p.getLocation().getNeighbors().get(validIndex);
			boolean hasPumpBefore = p.hasPump();
			int neighborSizeBefore = targetField.getNeighbors().size();
			p.move(targetField);
			boolean hasPumpAfter = p.hasPump();
			int neighborSizeAfter = targetField.getNeighbors().size();
			
			if (!hasPumpBefore && hasPumpAfter) {
				new PumpView(p.showPumpInBackPack(), -30, -30);
			}
			
			if (neighborSizeAfter > neighborSizeBefore) {
				new PipeView(targetField.showNewPipe());
			}
			
			rollToNext();
		} else if (state == 32) {
			var p = playerViewList.get(jatekosKovetkezik).returnPlayer();
			int validIndex = Game.normalToValidIndex(p.getLocation().getNeighbors().size(), number);
			p.adjustInput(p.getLocation().getNeighbors().get(validIndex));
			rollToNext();
		} else if (state == 33) {
			var p = playerViewList.get(jatekosKovetkezik).returnPlayer();
			int validIndex = Game.normalToValidIndex(p.getLocation().getNeighbors().size(), number);
			p.adjustOutput(p.getLocation().getNeighbors().get(validIndex));
			rollToNext();
		}  else if (state == 34) {
			var p = playerViewList.get(jatekosKovetkezik).returnPlayer();
			int validIndex = Game.normalToValidIndex(p.getLocation().getNeighbors().size(), number);
			playerViewList.get(jatekosKovetkezik).pickupPipe(validIndex);
			rollToNext();
		}
	}
	
	/*
	 * Getter.
	 */
	public static Canvas GetCanvas() {
		return canvas;
	}
	
	/*
	 * Getter.
	 */
	public static JPanel getBottomPanel() {
		return bottomPanel;
	}
	
	/*
	 * Megkerdezi a jatekost, hogy mit szeretne csinalni.
	 */
	public static void jatekostKerdez() {
		playerViewList.get(jatekosKovetkezik).megkerdezik();
		state = 3;
		canvas.repaint();
	}
	
	/*
	 * A jatekos a megadott szam alapjan cselekszik.
	 */
	public static void jatekosCselekszik(int number) {
		playerViewList.get(jatekosKovetkezik).cselekszik(number);
		canvas.repaint();
	}
	
	/*
	 * A kovetkezo jatekosra kerul a sor, az utolso utan lezajlodik a mezok kore, es az elso jatekos jon.
	 */
	public static void rollToNext() {
		canvas.repaint();
		++jatekosKovetkezik;
		state = 3;
		if (jatekosKovetkezik > 3) {
			autoKor();
			jatekosKovetkezik = 0;
		}
		if (state == 3) {
			jatekostKerdez();
		}
	}
	
	/*
	 * Minden mezo tol, majd minden mezo behuz. A kor vegen ujrarajzolas, mert egy vagy tobb pumpa elromolhat.
	 * Ha vege a jateknak, akkor kiirja a gyoztest, es a fomenu jelenik meg.
	 */
	public static void autoKor() {
		for (Field f : fieldList) {
			f.push();
			canvas.repaint();
		}
		
		for (Field f : fieldList) {
			f.pull();
		}
		
		canvas.repaint();
		
		if (repairmanScore >= 100 || saboteurScore >= 100 && repairmanScore > saboteurScore) {
			print("Repairmen Win!");
			println("=====> Mit szeretnel csinalni?");
			
			println("1 Uj jatek inditasa.");
			println("2 Mentett jatek betoltese.");
			
			println("egyeb Vegeztem, ki szeretnek lepni.");
			jatekosKovetkezik = 0;
			state = 1;
		} else if (repairmanScore >= 100 || saboteurScore >= 100 && repairmanScore < saboteurScore) {
			print("Saboteurs Win!");
			println("=====> Mit szeretnel csinalni?");
			
			println("1 Uj jatek inditasa.");
			println("2 Mentett jatek betoltese.");
			
			println("egyeb Vegeztem, ki szeretnek lepni.");
			jatekosKovetkezik = 0;
			state = 1;
		} else if (repairmanScore >= 100 || saboteurScore >= 100 && repairmanScore == saboteurScore){
			print("It is a draw!");
			println("=====> Mit szeretnel csinalni?");
			
			println("1 Uj jatek inditasa.");
			println("2 Mentett jatek betoltese.");
			
			println("egyeb Vegeztem, ki szeretnek lepni.");
			jatekosKovetkezik = 0;
			state = 1;
		}
	}
	
	/*
	 * A grafikus konzol tartalmanak torlese.
	 */
	public static void clear() {
		infoTextArea.setText(null);
	}
	
	/*
	 * A grafikus konzol tartalmanak torlese, es szoveg beirasa. 
	 */
	public static void print(String szoveg) {
		infoTextArea.setText(szoveg);
	}
	
	/*
	 * A grafikus konzol tartalmanak kiegeszitese az adott sorral.
	 */
	public static void println(String szoveg) {
		infoTextArea.setText(infoTextArea.getText() + "\n" + szoveg);
	}
	
	/*
	 * Getter.
	 */
	public static JPanel getCanvas() {
		return canvas;
	}
	
	/*
	 * Getter.
	 */
	public static Game getGame() {
		return game;
	}
	
	/*
	 * A jatek belepesi pontja.
	 */
	public static void main(String[] args) {
		game.setVisible(true);
		
		if (args.length > 0) {
			try {
				Game.getRandom().setSeed(Long.parseLong(args[0]));
				//System.out.println("Random seed beallitva: " + args[0]);
			} catch (Exception e) {
				
			}
		} if (args.length > 0 && args[0].equals("norandom")) {
			Game.setRandomDisabled(true);
			println("Random kikapcsolva.");
		}
		
		println("Udvozollek ebben a programban, nyomj meg egy gombot a kezdeshez!");
	}
	
	/*
	 * A jatek mentese.
	 */
	public static void save() {
		
		try {
			try (var out = new ObjectOutputStream(new FileOutputStream("save.txt"))) {
				var gameData = new GameData();
				out.writeObject(gameData);
				out.close();
			}
			System.out.println();
		} catch (IOException e) {
			print("I could not save the game...");
			e.printStackTrace();
		}
	}
	
	/*
	 * A jatek betoltese.
	 */
	public static void load() {
		try {
			try (var in = new ObjectInputStream(new FileInputStream("save.txt"))) {
				try {
					GameData gameData = (GameData)in.readObject();
					in.close();
					repairmanScore = gameData.getRepairmanScore();
					saboteurScore = gameData.getSaboteurScore();
					fieldList = gameData.getFieldList();
					playerList = gameData.getPlayerList();
					random = gameData.getRandom();
					randomDisabled = gameData.isRandomDisabled();
					
					fieldViewList = gameData.getFieldViewList();
					playerViewList = gameData.getPlayerViewList();
					game = gameData.getGame();
					fieldMap = gameData.getFieldMap();
					playerMap = gameData.getPlayerMap();
					jatekosKovetkezik = gameData.jatekosKovetkezik;
					state = gameData.state;
					infoTextArea.setText(gameData.getText());
					
				} catch (Exception e) {
					throw e;
				}
			}
		} catch (Exception e) {
			println("Nem tudtam betolteni, a jatek alaphelyzetbe all.");
			Game.init();
		}
	}
	
	/*
	 * Init, a jatek elemeinek felepitese.
	 */
	public static void init() {
		System.out.println("Game.init()");
		
		repairmanScore = 0;
		saboteurScore = 0;
		fieldList = new ArrayList<Field>();
		playerList = new ArrayList<Player>();
		
		
		fieldViewList = new ArrayList<FieldView>();
		playerViewList = new ArrayList<PlayerView>();
		fieldMap = new HashMap<Field, FieldView>();
		playerMap = new HashMap<Player, PlayerView>();
		
		Cistern.resetId();
		Pipe.resetId();
		Pump.resetId();
		Source.resetId();
		Repairman.resetId();
		Saboteur.resetId();
		
		
		int xKezdo = 280;
		int yKezdo = 135;
		
		int a = 140;
		int m = (int)Math.sqrt(3.0 / 4 * Math.pow(a, 2));
		
		var s0 = new Source();
		Game.addField(s0);
		new SourceView(s0, xKezdo, yKezdo);
		
		
		var s1 = new Source();
		Game.addField(s1);
		new SourceView(s1, xKezdo, yKezdo + a);
		
		
		var p0 = new Pump();		
		Game.addField(p0);
		new PumpView(p0, xKezdo + m, yKezdo - a / 2);
		
		var p1 = new Pump();		
		Game.addField(p1);
		new PumpView(p1, xKezdo + m, yKezdo + a / 2);
		
		var p2 = new Pump();		
		Game.addField(p2);
		new PumpView(p2, xKezdo + m, yKezdo + a + a / 2);		
		
		
		var c0 = new Cistern();
		Game.addField(c0);
		new CisternView(c0, xKezdo + m + m, yKezdo);
		
		var c1 = new Cistern();
		Game.addField(c1);
		new CisternView(c1, xKezdo + m + m, yKezdo + a);
		
		
		var pi0 = new Pipe();
		Game.addField(pi0);
		pi0.becomeNeighbors(s0);
		pi0.becomeNeighbors(p0);
		new PipeView(pi0);
		
		var pi1 = new Pipe();
		Game.addField(pi1);
		pi1.becomeNeighbors(p0);
		pi1.becomeNeighbors(c0);
		new PipeView(pi1);
		
		var pi2 = new Pipe();
		Game.addField(pi2);
		pi2.becomeNeighbors(s0);
		pi2.becomeNeighbors(p1);
		new PipeView(pi2);
		
		var pi3 = new Pipe();
		Game.addField(pi3);
		pi3.becomeNeighbors(p1);
		pi3.becomeNeighbors(c0);
		new PipeView(pi3);
		
		var pi4 = new Pipe();
		Game.addField(pi4);
		pi4.becomeNeighbors(s1);
		pi4.becomeNeighbors(p1);
		new PipeView(pi4);
		
		var pi5 = new Pipe();
		Game.addField(pi5);
		pi5.becomeNeighbors(p1);
		pi5.becomeNeighbors(c1);
		new PipeView(pi5);
		
		var pi6 = new Pipe();
		Game.addField(pi6);
		pi6.becomeNeighbors(s1);
		pi6.becomeNeighbors(p2);
		new PipeView(pi6);
		
		var pi7 = new Pipe();
		Game.addField(pi7);
		pi7.becomeNeighbors(p2);
		pi7.becomeNeighbors(c1);
		new PipeView(pi7);
		
		var pi8 = new Pipe();
		Game.addField(pi8);
		pi8.becomeNeighbors(s0);
		pi8.becomeNeighbors(s1);
		new PipeView(pi8);
		
		var pi9 = new Pipe();
		Game.addField(pi9);
		pi9.becomeNeighbors(p0);
		pi9.becomeNeighbors(p1);
		new PipeView(pi9);
		
		var pi10 = new Pipe();
		Game.addField(pi10);
		pi10.becomeNeighbors(p1);
		pi10.becomeNeighbors(p2);
		new PipeView(pi10);
		
		var pi11 = new Pipe();
		Game.addField(pi11);
		pi11.becomeNeighbors(c0);
		pi11.becomeNeighbors(c1);
		new PipeView(pi11);
		
		
		p0.selectInput(pi0);
		p0.selectOutput(pi1);
		
		p1.selectInput(pi2);
		p1.selectOutput(pi3);
		
		p2.selectInput(pi6);
		p2.selectOutput(pi7);
		
		
		var rep0 = new Repairman();
		Game.addPlayer(rep0);
		rep0.setLocation(s0);
		s0.addPlayer(rep0);
		new RepairmanView(rep0);
		
		
		
		var rep1 = new Repairman();
		Game.addPlayer(rep1);
		rep1.setLocation(s1);
		s1.addPlayer(rep1);
		new RepairmanView(rep1);
		
		
		
		var sab0 = new Saboteur();
		playerList.add(sab0);
		sab0.setLocation(c0);
		c0.addPlayer(sab0);
		new SaboteurView(sab0);
		new PipeView(c0.getNeighbors().get(3));
		
		
		
		var sab1 = new Saboteur();
		playerList.add(sab1);
		sab1.setLocation(c1);
		c1.addPlayer(sab1);
		new SaboteurView(sab1);
		new PipeView(c1.getNeighbors().get(3));
		
		canvas.repaint();
		
	}
	
	/*
	 * A jatek elkezdese.
	 */
	public static void start() {
		System.out.println("Game.start()");
		while (repairmanScore < 100 && saboteurScore < 100) {
			for (Field f : fieldList) {
				f.push();
				canvas.repaint();
			}
			
			for (Field f : fieldList) {
				f.pull();
				canvas.repaint();
			}
			
			for (Player p : playerList) {
				p.step();
				canvas.repaint();
			}
		}
		
		if (repairmanScore > saboteurScore) {
			println("Repairmen Win!");
		} else if (repairmanScore < saboteurScore) {
			println("Saboteurs Win!");
		} else {
			println("It is a draw!");
		}
	}
	
	/*
	 * Getter.
	 */
	public static List<Player> getPlayerList() {
		return playerList;
	}
	
	/*
	 * Mezo hozzaadasa.
	 */
	public static void addField(Field f) {
		System.out.println("Game.addField(" + f + ")");
		fieldList.add(f);
	}
	
	/*
	 * Mezo eltavolitasa.
	 */
	public static void removeField(Field f) {
		System.out.println("Game.removeField(" + f + ")");
		fieldList.remove(f);
	}
	
	/*
	 * Jatekos hozzaadasa.
	 */
	public static void addPlayer(Player p) {
		System.out.println("Game.addPlayer(" + p + ")");
		playerList.add(p);
	}
	
	/*
	 * Jatekos eltavolitasa.
	 */
	public static void removePlayer(Player p) {
		System.out.println("Game.removePlayer(" + p + ")");
		playerList.remove(p);
	}
	
	/*
	 * Getter.
	 */
	public static int getRepairmanScore() {
		System.out.println("Game.getRepairmanScore()");
		return repairmanScore;
	}
	
	/*
	 * Setter.
	 */
	public static void setRepairmanScore(int score) {
		System.out.println("Game.setRepairmanScore(" + score + ")");
		repairmanScore = score;
	}
	
	/*
	 * Pontnoveles.
	 */
	public static void increaseRepairmanScore(int gain) {
		System.out.println("Game.increaseRepairmanScore(" + gain + ")");
		repairmanScore += gain;
	}
	
	/*
	 * Getter.
	 */
	public static int getSaboteurScore() {
		System.out.println("Game.getSaboteurScore()");
		return saboteurScore;
	}
	
	/*
	 * Setter.
	 */
	public static void setSaboteurScore(int score) {
		System.out.println("Game.setSaboteurScore(" + score + ")");
		saboteurScore = score;
	}
	
	/*
	 * Pontnoveles.
	 */
	public static void increaseSaboteurScore(int gain) {
		System.out.println("Game.increaseSaboteurScore(" + gain + ")");
		saboteurScore += gain;
	}
	
	/*
	 * Getter.
	 */
	public static Random getRandom() {
		return random;
	}
	
	/*
	 * Setter.
	 */
	public static void setRandom(Random random) {
		Game.random = random;
	}
	
	/*
	 * Getter.
	 */
	public static List<Field> getFieldList() {
		return fieldList;
	}
	
	/*
	 * Getter.
	 */
	public static boolean isRandomDisabled() {
		return randomDisabled;
	}
	
	/*
	 * Setter.
	 */
	public static void setRandomDisabled(boolean randomDisabled) {
		Game.randomDisabled = randomDisabled;
	}
	
	/*
	 * Getter.
	 */
	public static JTextArea getInfoTextArea() {
		return infoTextArea;
	}
	
	/*
	 * Getter.
	 */
	public static Map<Field, FieldView> getFieldMap() {
		return fieldMap;
	}
	
	/*
	 * Getter.
	 */
	public static Map<Player, PlayerView> getPlayerMap() {
		return playerMap;
	}
	
	/*
	 * Getter.
	 */
	public static List<FieldView> getFieldViewList() {
		return fieldViewList;
	}
	
	/*
	 * Getter.
	 */
	public static List<PlayerView> getPlayerViewList() {
		return playerViewList;
	}
	
	/*
	 * Gomblenyomas figyelo.
	 */	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar() == '1') {
			System.out.println("sldfsd");
			step(1);
		} else if(e.getKeyChar() == '2') {
			step(2);
		} else if(e.getKeyChar() == '3') {
			step(3);
		} else if(e.getKeyChar() == '4') {
			step(4);
		} else if(e.getKeyChar() == '5') {
			step(5);
		} else if(e.getKeyChar() == '6') {
			step(6);
		} else if(e.getKeyChar() == '7') {
			step(7);
		} else if(e.getKeyChar() == '8') {
			step(8);
		} else if(e.getKeyChar() == '9') {
			step(9);
		} else if (e.getKeyChar() == 's') {
			save();
		} else {
			step(0);
		}
	}

	/*
	 * Nincs hasznalva.
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * Nincs hasznalva.
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}
	
	/*
	 * Kiirja a szomszedokat, elotte egy kerdest, es ha nincs szomszed, akkor a hibauzenetet.
	 */
	public static boolean printNeighbors(Player p, String question, String errorMsg) {
		
		Field location = p.getLocation();
		var neighbors = location.getNeighbors();
		int neighborSize = neighbors.size();
		if (neighborSize == 0) {
			println(errorMsg);
			return false;
		}
		
		print(question);
		
		for (int i = 0; i < neighborSize - 1; ++i) {
			println((i + 1) + " " + neighbors.get(i));
		}
		println("egyeb " + neighbors.get(neighborSize - 1));
		return true;
	}
	
	/*
	 * Sorszambol ervenyes indexet csinal.
	 */
	public static int normalToValidIndex(int Size, int answer) {
		--answer;
		answer = (answer < 0 || answer > Size - 1) ? Size - 1 : answer;
		return answer;
	}
	
}
