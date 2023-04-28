package application;

public abstract class VulnerableField extends Field {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Water water = null;			// A tarolt viz, null, ha ures.
	private boolean damaged = false;	// A serulest tarolja, alapbol nem serult.
	
	@Override
	public boolean hasWater() {
		System.out.println(this + ".hasWater()" + (water != null ? " van vize" : " nincs vize"));
		return water != null;
	}
	
	@Override
	public boolean isDamaged() {
		System.out.println(this + ".isDamaged() " + (damaged? "serult" : "nem serult"));
		return damaged;
	}
	
	public void setDamaged(boolean damaged) {
		System.out.println(this + ".setDamaged(" + damaged + ")");
		this.damaged = damaged;
	}
	
	// Kiveszi es visszaadja a tarolt vizet. A tarolo erteket null-ra allitja, azaz
	// uresre. A hivo felelossege, hogy ellenorizze, hogy van-e viz, amit ki lehet venni
	// a hivas elott.
	@Override
	public Water removeWater() {
		System.out.println(this + ".removeWater() " + this + " give away " + water);
		Water w = water;
		water = null;
		return w;
	}
	
	// A taroloba beteszi a kapott vizet. A hivo felelossege megnezni, hogy a tarolo
	// ures-e a hivas elott, azaz a tarolo erteke null.
	@Override
	public void receiveWater(Water w) {
		System.out.println(this + ".receiveWater(" + w + ")");
		water = w;
	}
	
	/* Megjavul a javitas hatasara.
	 * A Pipe override-olja.
	 */
	@Override
	public void receiveRepair() {
		System.out.println(this + ".receiveRepair()");
		damaged = false;
	}
	
}
