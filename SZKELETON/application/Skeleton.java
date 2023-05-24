package application;

import java.util.Scanner;

public class Skeleton {
	private static Scanner scanner = new Scanner(System.in);
	private static Cistern cistern = new Cistern();
	private static Pipe pipe = new Pipe();
	private static Pump pump = new Pump();
	private static Source source = new Source();
	private static Repairman repairman = new Repairman();
	private static Saboteur saboteur = new Saboteur();
	private static Water water = new Water();
	
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

	public static Cistern getCistern() {
		return cistern;
	}

	public static Pipe getPipe() {
		return pipe;
	}

	public static Pump getPump() {
		return pump;
	}

	public static Source getSource() {
		return source;
	}

	public static Repairman getRepairman() {
		return repairman;
	}

	public static Saboteur getSaboteur() {
		return saboteur;
	}

	public static Water getWater() {
		return water;
	}	
}
