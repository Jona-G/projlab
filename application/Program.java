package application;

public class Program {
	
	public static void main(String[] args) {
		if (args.length > 0) {
			try {
				Game.getRandom().setSeed(Long.parseLong(args[0]));
				System.out.println("Random seed beallitva: " + args[0]);
			} catch (Exception e) {
				
			}
		} if (args.length > 0 && args[0].equals("norandom")) {
			Game.setRandomDisabled(true);
			System.out.println("Random kikapcsolva.");
		}
		
		System.out.println("Udvozollek ebben a tesztprogramban, kerlek valassz a felkinalt lehetosegek kozul!");
		
		while (true) {
			System.out.println("=====> Mit szeretnel csinalni?");
			
			System.out.println("1 Uj jatek inditasa.");
			System.out.println("2 Mentett jatek betoltese.");
			
			System.out.println("egyeb Vegeztem, ki szeretnek lepni.");
			
			int answer = Skeleton.readNumber();
			boolean wantToExit = false;
			
			switch (answer) {
			case 1:
				Game.init();
				Game.start();
				break;
			case 2:
				Game.load();
				Game.start();
				break;
			case 0:
			default:
				wantToExit = true;
				break;
			}
			
			
			if (wantToExit) {
				break;
			}
		}
				
		System.out.println("Koszonom, hogy kiprobaltad ezt a programot!");
	}

}
