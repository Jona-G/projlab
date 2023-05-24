package application;

public class Game {
	
	public static void init() {
		System.out.println("Game.init() Itt majd felepul a jatek, megjelennek a mezok es a jatekosok.");
	}
	
	public static void start() {
		System.out.println("Game.start()");
		
		while (true) {
			Game.getRepairmanScore();
			Game.getSaboteurScore();
			System.out.println("Valamelyik csapat mar elerte a gyozelemhez szukseges pontszamot?");
			Skeleton.printOptions();
			
			if (Skeleton.readNumber() != 1) {
				while (true) {
					System.out.println("Van meg mezo a kor elso mezoiteraciojaban? (Ez a viz kitolasanak az uteme.)");
					Skeleton.printOptions();
					if (Skeleton.readNumber() == 1) {
						System.out.println("Milyen mezo ez?");
						System.out.println("1 Ciszterna");
						System.out.println("2 Cso");
						System.out.println("3 Pumpa");
						System.out.println("egyeb Forras");
						
						int answer = Skeleton.readNumber();
						
						if (answer == 1) {
							Skeleton.getCistern().push();
						} else if (answer == 2) {
							Skeleton.getPipe().push();
						} else if (answer == 3) {
							Skeleton.getPump().push();
						} else {
							Skeleton.getSource().push();
						}
					} else {
						break;
					}
				}
				
				while (true) {
					System.out.println("Van meg mezo a kor masodik mezoiteraciojaban? (Ez a viz behuzasanak az uteme.)");
					Skeleton.printOptions();
					if (Skeleton.readNumber() == 1) {
						System.out.println("Milyen mezo ez?");
						System.out.println("1 Ciszterna");
						System.out.println("2 Cso");
						System.out.println("3 Pumpa");
						System.out.println("egyeb Forras");
						
						int answer = Skeleton.readNumber();
						
						if (answer == 1) {
							Skeleton.getCistern().pull();
						} else if (answer == 2) {
							Skeleton.getPipe().pull();
						} else if (answer == 3) {
							Skeleton.getPump().pull();
						} else {
							Skeleton.getSource().pull();
						}
					} else {
						break;
					}
				}
				
				while (true) {
					System.out.println("Van meg jatekos, aki nem lepett?");
					Skeleton.printOptions();
					if (Skeleton.readNumber() == 1) {
						System.out.println("Milyen jatekos ez?");
						System.out.println("1 Szerelo");
						System.out.println("egyeb Szabotor");
						
						if (Skeleton.readNumber() == 1) {
							Skeleton.getRepairman().step();
						} else {
							Skeleton.getSaboteur().step();
						}
					} else {
						break;
					}
				}
				
			} else {
				System.out.println("Akkor az a csapat gyozott, akinek tobb pontja van es vege a jateknak! Koszonom, hogy jatszottal!");
				break;
			}
		}
	}
	
	public static void addField(Field f) {
		System.out.println("Game.addField(Field f)");
	}
	
	public static void removeField(Field f) {
		System.out.println("Game.removeField(Field f)");
	}
	
	public static void addPlayer(Player p) {
		System.out.println("Game.addPlayer(Player p)");
	}
	
	public static void removePlayer(Player p) {
		System.out.println("Game.removePlayer(Player p1)");
	}
	
	public static int getRepairmanScore() {
		System.out.println("Game.getRepairmanScore()");
		return 0;
	}
	
	public static void increaseRepairmanScore(int gain) {
		System.out.println("Game.increaseRepairmanScore(int gain)");
	}
	
	public static int getSaboteurScore() {
		System.out.println("Game.getSaboteurScore()");
		return 0;
	}
	
	public static void increaseSaboteurScore(int gain) {
		System.out.println("Game.increaseSaboteurScore(int gain)");
	}
	
}
