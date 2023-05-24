package application;

import java.util.List;

public abstract class Field {
	
	public void addNeighbor(Field f) {
		System.out.println("Field.addNeighbor(Field f)");
	}
	
	public void removeNeighbor(Field f) {
		System.out.println("Field.removeNeighbor(Field f)");
	}
	
	public void becomeNeighbors(Field f) {
		System.out.println("Field.becomeNeighbors(Field f) => Kolcsonosen letrejon a szomszedsag.");
	}
	
	// Polimorfikus, a Pump override-olja az input es az output miatt.
	public void stopBeingNeighbors(Field f) {
		System.out.println("Field.stopBeingNeighbors(Field f) => Kolcsonosen megszunik a szomszedsag.");
	}
	
	public List<Field> getNeighbors() {
		System.out.println("Field.getNeighbors()");
		return null;
	}
	
	public void removePlayer(Player p) {
		System.out.println("Field.removePlayer(Player p)");
	}
	
	public List<Player> getPlayers() {
		System.out.println("Field.getPlayers()");
		return null;
	}
	
	// Polimorfikus fuggveny, a Cistern override-olja
	public void addPlayer(Player p) {
		System.out.println("Field.addPlayer(Player p)");
	}
	
	// Polimorfikus fuggveny, a Pipe override-olja
	public boolean canReceivePlayer() {
		System.out.println("Field.canReceivePlayer() Mindig tud jatekost fogadni.");
		return true;
	}
	
	// Polimorfikus fuggveny, a Pump es a Pipe override-olja
	public boolean hasWater() {
		System.out.println("Field.hasWater() Mindig nemet ad vissza.");
		return false;
	}
	
	// Polimorfikus fuggveny, a VulnerableField override-olja
	public Water removeWater() {
		System.out.println("Field.removeWater() Mindig null a visszaterese, azaz nem ad vissza vizet.");
		return null;
	}
	
	// Polimorfikus fuggveny, a VulnerableField override-olja
	public void receiveWater(Water w) {
		System.out.println("Field.receiveWater(Water w) Nem csinal semmit.");
	}
	
	// Polimorfikus fuggveny, a VulnerableField override-olja
	public boolean isDamaged() {
		System.out.println("Field.isDamaged() Mindig nemet ad vissza.");
		return false;
	}	
	
	// Polimorfikus fuggveny, a Source es a Pump override-olja
	public void push() {
		System.out.println("Field.push() Nem csinal semmit.");
	}
	
	// Polimorfikus fuggveny, a Pump es a Cistern override-olja
	public void pull() {
		System.out.println("Field.pull() Nem csinal semmit.");
	}
	
	// Polimorfikus fuggveny, a Pump override-olja
	public void breaksDown() {
		System.out.println("Field.breaksDown() Nem csinal semmit.");
	}
	
	// Polimorfikus fuggveny, a Pipe override-olja
	public void receivePuncture() {
		System.out.println("Field.receivePuncture() Nem csinal semmit.");
	}
	
	// Polimorfikus fuggveny, a Pump es a Pipe override-olja
	public void receiveRepair() {
		System.out.println("Field.receiveRepair() Nem csinal semmit.");
	}
	
	/* Polimorfikus fuggveny, a Pipe override-olja, ott nem csinal semmit, return null;
	 * A hivo felelossege, hogy parameterkent megadja a Field (amin all) egyik szomszedjat.
	 * A felvenni kivant Field (cso) allapotai:
	 * Egy szomszedja van (amin allunk egyebkent), nem fehet felvenni, mert viszik.
	 * Ket szomszedja van (amin allunk es null) csak ide van csatlakoztatva, fel lehet venni.
	 * A null szomszed ilyenkor remove, egy szomszeddal mar mas nem veheti fel.
	 * Null szomszeddal rendelkezo, csak egy csatlakozott veggel rendelkezo cso csak a ciszternanal keletkezhet.
	 * Csak a ciszternanal lehet felvenni ilyen csovet, a cso tovabbra is a ciszternahoz csatlakozik, a null szomszedja ilyenkor
	 * eltavolitasra kerul, igy mas mar nem veheti fel mikozben viszik.
	 * Egyeb normal esetekben a csonek ket Field szomszedja van, ha levesszuk, akkor az adott
	 * mezovel megszunik szomszednak lenni, csak egy szomszedja marad a masik vegen, igy nem lehet meg egyszer felvenni
	 * amig le nem teszik, es megint ket szomszedja nem lesz.
	 *
	 * A Pump is override-olja az input es az output miatt.
	 *
	 * Osszefoglalva:
	 * Egy szomszeddal rendelkezo csovet nem lehet felvenni (ez azt jelzi, hogy viszik).
	 * Ket szomszeddal rendelkezo csovet fel lehet venni.
	 * Ha a masodik szomszedja null, az azt jelzi, hogy ciszternahoz csatlakoztatott, szabad vegu cso.
	 * Felvetelkor a Ciszterna szomszedja marad, a null szomszed torlodik, lerakasig nem veheto fel, mert csak egy szomszedja van.
	 * Ha mindket szomszedja Field, akkor megszunik szomszed lenni azzal, amirol leveszik, lerakasig nem veheto fel, mert csak egy szomszedja van.
	 */
	public Field giveMePipeEnd(Field targetField) {
		System.out.println("Field.giveMePipeEnd(Field targetField)");
		
		System.out.println("Hol all a jatekos?");
		System.out.println("1 Ciszternan");
		System.out.println("2 Pumpan");
		System.out.println("egyeb Forrason");
		
		int answer = Skeleton.readNumber();
		
		if (answer == 1) {
			Skeleton.getCistern().getNeighbors();
		} else if (answer == 2) {
			Skeleton.getPump().getNeighbors();
		} else {
			Skeleton.getSource().getNeighbors();
		}
		
		System.out.println("A kivalasztott csonek kevesebb mint ketto szomszedja van?");
		Skeleton.printOptions();
		
		if (Skeleton.readNumber() == 1) {
			System.out.println("Ezt a csovet most nem lehet felvenni, mert az egyik veget mar viszik.");
			return null;
		}
		
		System.out.println("A kivalasztott cso masodik szomszedja null/szabad? (Ez csak forrasnal fordulhat elo.)");
		Skeleton.printOptions();
		
		if (Skeleton.readNumber() == 1) {
			System.out.println("Ezt a csoveget most fel lehet felvenni, mert van egy nem csatlakozott veg, amit vinni tudunk.");
			return null;
		} else {
			System.out.println("Ezt a csoveget most fel lehet felvenni, mert a masik vege csatlakozik valahova, ezt a veget pedig le fogjuk csatlakoztatni.");
			Skeleton.getPipe().stopBeingNeighbors(null);
		}
		
		return null;
	}
	
	/* Polimorfikus fuggveny, a Pipe override-olja, ott nem csinal semmit, return false;
	 * A parameterkent adott kezben vitt cso csatlakozik a Field-hez, amint meghivtak a fuggvenyt, ha van neki helye.
	 */
	public boolean receivePipe(Field f) {
		System.out.println("Field.receivePipe(Field f)");
		
		// Itt majd lekerdezzuk a szomszedok szamat, itt most csak kiirjuk, hogy mi lesz majd meghivva.
		System.out.println("Field.getNeighbors().size()");
		
		System.out.println("A mezohoz csatlakoztatott csovek szama mar elerte a maximalis megengedett erteket?");
		Skeleton.printOptions();
		
		if (Skeleton.readNumber() == 1) {
			System.out.println("Akkor nem lehet mar tobb csovet a csatlakoztatni.");
			return false;
		}
		
		Skeleton.getPipe().becomeNeighbors(null);
		System.out.println("A csovet csatlakoztattuk a mezohoz.");
		return true;
	}
	
	// Polimorfikus fuggveny, a Pump override-olja
	public void selectInput(Field input) {
		System.out.println("Field.selectInput(Field input) Nem csinal semmit.");
	}
	
	// Polimorfikus fuggveny, a Pump override-olja
	public void selectOutput(Field output) {
		System.out.println("Field.selectOutput(Field output) Nem csinal semmit.");
	}

	// Polimorfikus fuggveny, a Pipe override-olja
	public boolean receivePump(Field pumpInTheBackpack) {
		System.out.println("Field.receivePump(Field pumpInTheBackpack) A pumpa lerakasa itt nem lehetseges, ezert hamissal ter vissza.");
		return false;
	}
}
