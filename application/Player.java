package application;

import java.io.Serializable;

public abstract class Player implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int standardTimeOut = 3;
	private Field location = null;
	private int paralyzedTime = 0;
	
	public void step() {
		if (paralyzedTime > 0) {
			--paralyzedTime;
			System.out.println("---->The player cannot move now, " + (paralyzedTime <= 1 ? "but he can in the next round." : "and in the next " + (paralyzedTime - 1) + " round(s)."));
		}
	}
	
	public int getParalyzedTime() {
		return paralyzedTime;
	}
	
	public PlayerView getView() {
		return Game.getPlayerMap().get(this);
	}
	
	/*
	 * 
	 */
	public Field showPipeInHand() {
		return null;
	}
	
	public Field showPumpInBackPack() {
		return null;
	}
	
	/*
	 * Ragadossa teszi a mezot, de csak ha az Pipe.
	 */
	public void tryToMakeSticky() {
		this.getLocation().tryToBecomeSticky();
	}
	
	// Ha ra lehet lepni a mezore, akkor oda lep. A hivo felelossege olyan targetField-et
	// adni parameterkent, ami szomszedos mezo.
	public void move(Field targetField) {
		System.out.println(this + ".move(" + targetField + ")");
		
		if (targetField.canReceivePlayer() && paralyzedTime <= 0) {
			var oldLocation = this.getLocation();
			
			this.getLocation().removePlayer(this);
			this.setLocation(targetField);
			targetField.addPlayer(this);
			System.out.println(this + " moved to " + targetField);
			
			if (targetField.fieldIsSticky()) {
				System.out.println("Ragacsos csore lepett...");
				paralyzedTime = standardTimeOut;
				targetField.clearSticky();
			} else if (targetField.fieldIsSlippery()) {
				System.out.println("At fog csuszni valahova...");
				if (Game.isRandomDisabled()) { // Ha nincs random, akkor csusszon vissza az eredeti helyere.
					this.move(oldLocation);
				} else {
					this.move(targetField.getNeighbors().get(Math.abs(Game.getRandom().nextInt()) % 2));
				}
			}
			
		} else {			
			System.out.println(this + " could not move to " + targetField);
		}
	}
	
	// A mezo szivofejet/inputjat allitja, a polimorfikus selectInput miatt csak a Pump-nal
	// csinal majd valamit, mashol nem.
	public void adjustInput(Field targetField) {
		System.out.println(this + ".adjustInput(" + targetField + ")");
		location.selectInput(targetField);
	}
	
	// A mezo kimento fejet/outputjat allitja, a polimorfikus selectOutput miatt csak a Pump-nal
	// csinal majd valamit, mashol nem.
	public void adjustOutput(Field targetField) {
		System.out.println(this + ".adjustOutput(" + targetField + ")");
		location.selectOutput(targetField);
	}

	public Field getLocation() {
		System.out.println(this + ".getLocation()");
		return location;
	}

	public void setLocation(Field location) {
		System.out.println(this + ".setLocation(" + location + ")");
		this.location = location;
	}
	
	// Polimorfikus fuggveny, a Repairman override-olja.
	// A Saboteur sosem fog pumpat kapni, mert az "hazudja" hogy mar van neki.
	public boolean hasPump() {
		System.out.println(this + ".hasPump() Szabotor nem tud pumpat vinni/kapni, ezert a metodus mindig azt mondja, hogy mar van a jatekosnak.");
		return true;
	}
	
	// Polimorfikus fuggveny, a Repairman override-olja.
	// A Saboteur-on nem lesz meghivva.
	public void receivePump(Field pump) {
		System.out.println(this + ".receivePump(" + pump + ") Ez nem csinal semmit, szabotor nem kaphat pumpat.");
	}
	
	// Kilyukasztja amin all. A polimorfikus receivePuncture() miatt ez csak csovet lyukaszt ki.
	public void puncture() {
		System.out.println(this + ".puncture()");
		this.getLocation().receivePuncture();
	}
}
