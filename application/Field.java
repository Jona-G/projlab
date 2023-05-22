package application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Field implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Field> neighbors = new ArrayList<Field>();
	private List<Player> players = new ArrayList<Player>();
	
	public boolean fieldIsSlippery() {
		System.out.println(this + ".fieldIsSlippery()");
		return false;
	}
	
	public FieldView getView() {
		return Game.getFieldMap().get(this);
	}
	
	/*
	 * Polimorfikus fuggveny, itt nem csinal semmit, a Pipe override-olja.
	 */
	public void tryToBecomeSlippery() {
		System.out.println(this + ".tryToBecomeSlippery()");
		System.out.println("Nem lett csuszos, mert ez nem cso...");
	}
	
	/*
	 * 
	 * Polimorfikus fuggveny, itt return false, a Pipe override-olja.
	 * Megmondja, hogy viszik-e a csovet.
	 */
	public boolean isCarried() {
		System.out.println(this + ".isCarried()");
		return false;
	}
	
	/*
	 * Polimorfikus fuggveny, itt nem csinal semmit, a Pipe override-olja.
	 */
	public void tryToBecomeSticky() {
		System.out.println(this + ".tryToBecomeSticky()");
		System.out.println("Nem lett ragados, mert ez nem cso...");
	}
	
	public void clearSticky() {
		System.out.println(this + ".clearSticky()");
		System.out.println("Nem csinal semmit.");
	}
	
	public boolean fieldIsSticky() {
		System.out.println(this + ".fieldIsSticky()");
		return false;
	}
	
	public void addNeighbor(Field f) {
		System.out.println(this + ".addNeighbor(" + f + ")");
		neighbors.add(f);
	}
	
	public void removeNeighbor(Field f) {
		System.out.println(this + ".removeNeighbor(" + f + ")");
		neighbors.remove(f);
	}
	
	public void becomeNeighbors(Field f) {
		System.out.println(this + ".becomeNeighbors(" + f + ")");
		this.addNeighbor(f);
		f.addNeighbor(this);
	}
	
	// Polimorfikus, a Pump override-olja az input es az output miatt.
	public void stopBeingNeighbors(Field f) {
		System.out.println(this + ".stopBeingNeighbors(" + f + ")");
		this.removeNeighbor(f);
		f.removeNeighbor(this);
	}
	
	public List<Field> getNeighbors() {
		System.out.println(this + ".getNeighbors()");
		return neighbors;
	}
	
	public void removePlayer(Player p) {
		System.out.println(this + ".removePlayer(" + p + ")");
		players.remove(p);
	}
	
	public List<Player> getPlayers() {
		System.out.println(this + ".getPlayers()");
		return players;
	}
	
	
	
	// Polimorfikus fuggveny, a Cistern override-olja
	public void addPlayer(Player p) {
		System.out.println(this + ".addPlayer(" + p + ")");
		players.add(p);
	}
	
	// Polimorfikus fuggveny, a Pipe override-olja
	public boolean canReceivePlayer() {
		System.out.println(this + ".canReceivePlayer() Mindig tud jatekost fogadni.");
		return true;
	}
	
	// Polimorfikus fuggveny, a Pump es a Pipe override-olja
	public boolean hasWater() {
		System.out.println(this + ".hasWater() Mindig nemet ad vissza.");
		return false;
	}
	
	// Polimorfikus fuggveny, a VulnerableField override-olja
	public Water removeWater() {
		System.out.println(this + ".removeWater() Mindig null a visszaterese, azaz nem ad vissza vizet.");
		return null;
	}
	
	// Polimorfikus fuggveny, a VulnerableField override-olja
	public void receiveWater(Water w) {
		System.out.println(this + ".receiveWater(" + w + ") Nem csinal semmit.");
	}
	
	// Polimorfikus fuggveny, a VulnerableField override-olja
	public boolean isDamaged() {
		System.out.println(this + ".isDamaged() Mindig nemet ad vissza.");
		return false;
	}	
	
	// Polimorfikus fuggveny, a Source es a Pump (es a Pipe) override-olja
	public void push() {
		System.out.println(this + ".push() Nem csinal semmit.");
	}
	
	// Polimorfikus fuggveny, a Pump es a Cistern override-olja
	public void pull() {
		System.out.println(this + ".pull() Nem csinal semmit.");
	}
	
	// Polimorfikus fuggveny, a Pump override-olja
	public void breaksDown() {
		System.out.println(this + ".breaksDown() Nem csinal semmit.");
	}
	
	// Polimorfikus fuggveny, a Pipe override-olja
	public void receivePuncture() {
		System.out.println(this + ".receivePuncture() Nem csinal semmit.");
	}
	
	// Polimorfikus fuggveny, a Pump es a Pipe override-olja
	public void receiveRepair() {
		System.out.println(this + ".receiveRepair() Nem csinal semmit.");
	}
	
	/* Polimorfikus fuggveny, a Pipe override-olja, ott nem csinal semmit, return null;
	 * Ha allnak a csovon, akkor nem lehet felvenni.
	 */
	public Field giveMePipeEnd(Field targetField) {		
		if (targetField.getPlayers().size() > 0) {
			System.out.println("Allnak a csovon, nem lehet felvenni.");
			return null;
		}
		
		if (!targetField.isCarried() && targetField.getNeighbors().size() == 1) {
			return targetField;
		}
		
		this.stopBeingNeighbors(targetField);		
		return targetField;
	}
	
	// Polimorfikus fuggveny, a Pipe override-olja, ott nem csinal semmit, return false;
	// A parameterkent adott kezben vitt cso csatlakozik a Field-hez, amint meghivtak a fuggvenyt, ha van neki helye.
	public boolean receivePipe(Field f) {
		System.out.println(this + ".receivePipe(" + f + ")");
		if (neighbors.size() >= 5) {
			System.out.println("5-nel tobb cso nem csatlakozhat, ez most nem jott ossze.");
			return false;
		}
		
		this.becomeNeighbors(f);
		return true;
	}
	
	// Polimorfikus fuggveny, a Pump override-olja
	public void selectInput(Field input) {
		System.out.println(this + ".selectInput(" + input + ") Nem csinal semmit.");
	}
	
	// Polimorfikus fuggveny, a Pump override-olja
	public void selectOutput(Field output) {
		System.out.println(this + ".selectOutput(" + output + ") Nem csinal semmit.");
	}

	// Polimorfikus fuggveny, a Pipe override-olja
	public boolean receivePump(Field pumpInTheBackpack) {
		System.out.println(this + ".receivePump(" + pumpInTheBackpack + ") A pumpa lerakasa itt nem lehetseges, ezert hamissal ter vissza.");
		return false;
	}
	
	/* Megmondja, hogy ennek es a celmezonek van-e kozos szomszedja, ha igen akkor kozeli szomszedok.
	 * Onmagaval minden mezo kozeli szomszednak szamit.
	 * Ez azert hasznos, mert kozeli szomszedokat nem szeretnenk (ismetelten) osszekotni, beleertve az onmagaval valo hurkot is.
	 */
	public boolean closeNeighborWith(Field targetField) {
		System.out.println(this + ".closeNeighborWith(" + targetField + ")");
		
		if (this == targetField) {
			return true;
		}
		
		for (Field f : this.neighbors) {
			if (f.neighbors.contains(targetField)) {
				return true;
			}
		}
		
		return false;
	}
	
	public Field showNewPipe() {
		return null;
	}
}
