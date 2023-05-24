package application;

public abstract class Player {	
	
	public abstract void step();
	
	/* Ha ra lehet lepni a mezore, akkor oda lep. A hivo felelossege olyan targetField-et
	 * adni parameterkent, ami szomszedos mezo.
	 */
	public void move(Field targetField) {
		System.out.println("Player.move(Field targetField)");
		
		System.out.println("Hol all a jatekos?");
		System.out.println("1 Ciszternan");
		System.out.println("2 Csovon");
		System.out.println("3 Pumpan");
		System.out.println("egyeb Forrason");
		
		int oldLocationNumber = Skeleton.readNumber();
		
		System.out.println("Hova szeretne lepni a jatekos?");
		System.out.println("1 Ciszternara");
		System.out.println("2 Csore");
		System.out.println("3 Pumpara");
		System.out.println("egyeb Forrasra");
		
		int newLocationNumber = Skeleton.readNumber();
		
		// Csorol csore vagy nem csorol nem csore lepni nem lehet.
		if (oldLocationNumber == 2 && newLocationNumber == 2 || oldLocationNumber != 2 && newLocationNumber != 2) {
			System.out.println("Ilyen lepes nem lehetseges a jatekban!");
			return;
		}

		boolean okToMove;
		
		if (newLocationNumber == 1) {
			okToMove = Skeleton.getCistern().canReceivePlayer();
		} else if (newLocationNumber == 2) {
			okToMove = Skeleton.getPipe().canReceivePlayer();
		} else if (newLocationNumber == 3) {
			okToMove = Skeleton.getPump().canReceivePlayer();
		} else {
			okToMove = Skeleton.getSource().canReceivePlayer();
		}
		
		if (okToMove) {
			if (oldLocationNumber == 1) {
				Skeleton.getCistern().removePlayer(null);
			} else if (oldLocationNumber == 2) {
				Skeleton.getPipe().removePlayer(null);
			} else if (oldLocationNumber == 3) {
				Skeleton.getPump().removePlayer(null);
			} else {
				Skeleton.getSource().removePlayer(null);
			}
			
			Skeleton.getRepairman().setLocation(null);
			
			if (newLocationNumber == 1) {
				Skeleton.getCistern().addPlayer(null);
			} else if (newLocationNumber == 2) {
				Skeleton.getPipe().addPlayer(null);
			} else if (newLocationNumber == 3) {
				Skeleton.getPump().addPlayer(null);
			} else {
				Skeleton.getSource().addPlayer(null);
			}
		}
	}
	
	/* A mezo szivofejet/inputjat allitja, a polimorfikus selectInput miatt csak a Pump-nal
	 * csinal majd valamit, mashol nem.
	 */
	public void adjustInput(Field targetField) {
		System.out.println("Player.adjustInput(Field targetField)");
		
		Skeleton.getRepairman().getLocation();
		System.out.println("Hol all a jatekos?");
		System.out.println("1 Ciszternan");
		System.out.println("2 Csovon");
		System.out.println("3 Pumpan");
		System.out.println("egyeb Forrason");
		
		int answer = Skeleton.readNumber();
		
		if (answer == 1) {
			Skeleton.getCistern().selectInput(null);
		} else if (answer == 2) {
			Skeleton.getPipe().selectInput(null);
		} else if (answer == 3) {
			Skeleton.getPump().selectInput(null);
		} else {
			Skeleton.getSource().selectInput(null);
		}
	}
	
	/* A mezo kimento fejet/outputjat allitja, a polimorfikus selectOutput miatt csak a Pump-nal
	 * csinal majd valamit, mashol nem.
	 */
	public void adjustOutput(Field targetField) {
		System.out.println("Player.adjustOutput(Field targetField)");
		
		Skeleton.getRepairman().getLocation();
		System.out.println("Hol all a jatekos?");
		System.out.println("1 Ciszternan");
		System.out.println("2 Csovon");
		System.out.println("3 Pumpan");
		System.out.println("egyeb Forrason");
		
		int answer = Skeleton.readNumber();
		
		if (answer == 1) {
			Skeleton.getCistern().selectOutput(null);
		} else if (answer == 2) {
			Skeleton.getPipe().selectOutput(null);
		} else if (answer == 3) {
			Skeleton.getPump().selectOutput(null);
		} else {
			Skeleton.getSource().selectOutput(null);
		}
	}

	public Field getLocation() {
		System.out.println("Player.getLocation()");
		return null;
	}

	public void setLocation(Field location) {
		System.out.println("Player.setLocation(Field location)");
	}
	
	/* Polimorfikus fuggveny, a Repairman override-olja.
	 * A Saboteur sosem fog pumpat kapni, mert az "hazudja" hogy mar van neki.
	 */
	public boolean hasPump() {
		System.out.println("Player.hasPump() Szabotor nem tud pumpat vinni/kapni, ezert a metodus mindig azt mondja, hogy mar van a jatekosnak.");
		return true;
	}
	
	/* Polimorfikus fuggveny, a Repairman override-olja.
	 * A Saboteur-on nem lesz meghivva.
	 */
	public void receivePump(Field pump) {
		System.out.println("Player.receivePump(Field pump) Ez nem csinal semmit, szabotor nem kaphat pumpat.");
	}
}
