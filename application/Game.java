package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
	private static int repairmanScore;
	private static int saboteurScore;
	private static List<Field> fieldList;
	private static List<Player> playerList;
	private static Random random = new Random();
	private static boolean randomDisabled = false;
	
	public static void save() {
		
		try {
			String fileName = "";
			try {
				System.out.println("Hova mentsen:");
				fileName = Skeleton.getScanner().nextLine();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try (var out = new ObjectOutputStream(new FileOutputStream(fileName))) {
				var gameData = new GameData();
				out.writeObject(gameData);
				out.close();
			}
			System.out.println();
		} catch (IOException e) {
			System.out.println("I could not save the game...");
			e.printStackTrace();
		}
		//*/
	}
	
	public static void load() {
		String fileName = "";
		try {
			System.out.println("Honnan toltse be:");
			fileName = Skeleton.getScanner().nextLine();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			try (var in = new ObjectInputStream(new FileInputStream(fileName))) {
				try {
					GameData gameData = (GameData)in.readObject();
					in.close();
					
					repairmanScore = gameData.getRepairmanScore();
					saboteurScore = gameData.getSaboteurScore();
					fieldList = gameData.getFieldList();
					playerList = gameData.getPlayerList();
					random = gameData.getRandom();
					randomDisabled = gameData.isRandomDisabled();
					
				} catch (Exception e) {
					System.out.println("baj van");
					throw e;
				}
			}
		} catch (Exception e) {
			System.out.println("Nem tudtam betolteni, a jatek alaphelyzetbe all.");
			Game.init();
		}
		//*/
	}
	
	public static void init() {
		System.out.println("Game.init()");
		
		repairmanScore = 0;
		saboteurScore = 0;
		fieldList = new ArrayList<Field>();
		playerList = new ArrayList<Player>();
		
		
		var s0 = new Source();
		Game.addField(s0);
		
		var s1 = new Source();
		Game.addField(s1);
		
		
		
		var p0 = new Pump();		
		Game.addField(p0);
		
		var p1 = new Pump();		
		Game.addField(p1);
		
		var p2 = new Pump();		
		Game.addField(p2);
		
		var p3 = new Pump();
		Game.addField(p3);
		
		
		
		var c0 = new Cistern();
		Game.addField(c0);
		
		var c1 = new Cistern();
		Game.addField(c1);
		
		
		
		var pi0 = new Pipe();
		Game.addField(pi0);
		pi0.becomeNeighbors(s0);
		pi0.becomeNeighbors(p0);
		
		var pi1 = new Pipe();
		Game.addField(pi1);
		pi1.becomeNeighbors(p0);
		pi1.becomeNeighbors(p1);		
		
		var pi2 = new Pipe();
		Game.addField(pi2);
		pi2.becomeNeighbors(p1);
		pi2.becomeNeighbors(c0);
		
		var pi3 = new Pipe();
		Game.addField(pi3);
		pi3.becomeNeighbors(s1);
		pi3.becomeNeighbors(p2);
		
		var pi4 = new Pipe();
		Game.addField(pi4);
		pi4.becomeNeighbors(p2);
		pi4.becomeNeighbors(p3);
		
		var pi5 = new Pipe();
		Game.addField(pi5);
		pi5.becomeNeighbors(p3);
		pi5.becomeNeighbors(c1);
		
		var pi6 = new Pipe();
		Game.addField(pi6);
		pi6.becomeNeighbors(p0);
		pi6.becomeNeighbors(p2);
		
		var pi7 = new Pipe();
		Game.addField(pi7);
		pi7.becomeNeighbors(p1);
		pi7.becomeNeighbors(p3);
		
		
		
		p0.selectInput(pi0);
		p0.selectOutput(pi1);
		
		p1.selectInput(pi1);
		p1.selectOutput(pi2);
		
		p2.selectInput(pi3);
		p2.selectOutput(pi4);
		
		p3.selectInput(pi4);
		p3.selectOutput(pi5);
				
		
		
		var rep0 = new Repairman();
		Game.addPlayer(rep0);
		rep0.setLocation(s0);
		s0.addPlayer(rep0);
		
		
		
		var rep1 = new Repairman();
		Game.addPlayer(rep1);
		rep1.setLocation(s1);
		s1.addPlayer(rep1);		
		
		
		
		var sab0 = new Saboteur();
		playerList.add(sab0);
		sab0.setLocation(c0);
		c0.addPlayer(sab0);
		
		
		
		var sab1 = new Saboteur();
		playerList.add(sab1);
		sab1.setLocation(c1);
		c1.addPlayer(sab1);
		
	}
	
	public static void start() {
		System.out.println("Game.start()");
		while (repairmanScore < 100 && saboteurScore < 100) {
			for (Field f : fieldList) {
				f.push();
			}
			
			for (Field f : fieldList) {
				f.pull();
			}
			
			for (Player p : playerList) {
				p.step();
			}
			
			System.out.println("\t\t\t\t\tRepairmen score: " + repairmanScore);
			System.out.println("\t\t\t\t\tSaboteur score: " + saboteurScore);
			
			System.out.println("=====>Szeretnel menteni es/vagy kilepni?");
			System.out.println("1 Mentek es kilepek.");
			System.out.println("2 Mentek.");
			System.out.println("3 Kilepek.");
			System.out.println("egyeb Nem.");
			
			int answer = Skeleton.readNumber();
			
			if (answer == 1) {
				Game.save();
				return;
			} else if (answer == 2) {
				Game.save();
			} else if (answer == 3) {
				return;
			}
		}
		
		if (repairmanScore > saboteurScore) {
			System.out.println("Repairmen Win!");
		} else if (repairmanScore < saboteurScore) {
			System.out.println("Saboteurs Win!");
		} else {
			System.out.println("It is a draw!");
		}
	}
	
	public static List<Player> getPlayerList() {
		return playerList;
	}
	
	public static void addField(Field f) {
		System.out.println("Game.addField(" + f + ")");
		fieldList.add(f);
	}
	
	public static void removeField(Field f) {
		System.out.println("Game.removeField(" + f + ")");
		fieldList.remove(f);
	}
	
	public static void addPlayer(Player p) {
		System.out.println("Game.addPlayer(" + p + ")");
		playerList.add(p);
	}
	
	public static void removePlayer(Player p) {
		System.out.println("Game.removePlayer(" + p + ")");
		playerList.remove(p);
	}
	
	public static int getRepairmanScore() {
		System.out.println("Game.getRepairmanScore()");
		return repairmanScore;
	}
	
	public static void setRepairmanScore(int score) {
		System.out.println("Game.setRepairmanScore(" + score + ")");
		repairmanScore = score;
	}
	
	public static void increaseRepairmanScore(int gain) {
		System.out.println("Game.increaseRepairmanScore(" + gain + ")");
		repairmanScore += gain;
	}
	
	public static int getSaboteurScore() {
		System.out.println("Game.getSaboteurScore()");
		return saboteurScore;
	}
	
	public static void setSaboteurScore(int score) {
		System.out.println("Game.setSaboteurScore(" + score + ")");
		saboteurScore = score;
	}
	
	public static void increaseSaboteurScore(int gain) {
		System.out.println("Game.increaseSaboteurScore(" + gain + ")");
		saboteurScore += gain;
	}

	public static Random getRandom() {
		return random;
	}

	public static void setRandom(Random random) {
		Game.random = random;
	}

	public static List<Field> getFieldList() {
		return fieldList;
	}

	public static boolean isRandomDisabled() {
		return randomDisabled;
	}

	public static void setRandomDisabled(boolean randomDisabled) {
		Game.randomDisabled = randomDisabled;
	}
	
}
