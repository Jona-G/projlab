package application;

import java.util.Scanner;

public class Skeleton {
	private static Scanner scanner = new Scanner(System.in);
	
	public static Scanner getScanner() {
		return scanner;
	}

	
	// Beolvas egy szamot amit visszaad, ervenytelen esetben 0-t ad vissza.
	public static int readNumber() {
		try {
			return Integer.parseInt(scanner.nextLine());
		} catch (Exception e) {
			return 0;
		}
	}
	
	// Kiir egy igen-nem valasztasi lehetoseget.
	public static void printOptions() {
		System.out.println("1 Igen");
		System.out.println("egyeb Nem");
	}

	
	public static boolean printNeighbors(Player p, String question, String errorMsg) {
		System.out.println("Game.printNeighbors(" + p + ", " + question + ", " + errorMsg + ")");
		
		Field location = p.getLocation();
		boolean isLocationDamaged = location.isDamaged();
		var neighbors = location.getNeighbors();
		int neighborSize = neighbors.size();
		if (neighborSize == 0) {
			System.out.println(errorMsg);
			return false;
		}
		System.out.println(question + " " + p + " located at " + location + " [" + (isLocationDamaged ? "damaged]" : "not damaged]"));
		for (int i = 0; i < neighborSize - 1; ++i) {
			System.out.println((i + 1) + " " + neighbors.get(i));
		}
		System.out.println("egyeb " + neighbors.get(neighborSize - 1));
		return true;
	}
	
	public static int normalToValidIndex(int Size) {
		int answer = Skeleton.readNumber() - 1;
		answer = (answer < 0 || answer > Size - 1) ? Size - 1 : answer;
		return answer;
	}

}