package application;

/*
 * A szerelo logikai osztaja.
 */
public class Repairman extends Player {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Field pumpInTheBackpack = null;
	private Field pipeInHand = null;	
	private static int staticId = 0;
	private int id = staticId++;
	
	/*
	 * Szovegge alakitas.
	 */
	@Override
	public String toString() {
		return "repairman" + id;
	}
	
	/*
	 * A statikus id nullazasa.
	 */
	public static void resetId() {
		staticId = 0;
	}
	
	/*
	 * Visszaadja a kezeben tartott csovet.
	 */
	@Override
	public Field showPipeInHand() {
		return pipeInHand;
	}
	
	/*
	 * Cselekszik.
	 */
	@Override
	public void step() {
		System.out.println(this + ".step()");
		super.step();
	}
	
	// Megnezi, hogy van-e nala pumpa.
	@Override
	public boolean hasPump() {
		System.out.println(this + ".hasPump()");
		return !(pumpInTheBackpack == null);
	}
	
	// A hivo felelossege megnezni, hogy az adni kivant pumpanak van-e helye.
	@Override
	public void receivePump(Field pump) {
		System.out.println(this + ".receivePump(" + pump + ")");
		pumpInTheBackpack = pump;
	}
	
	// Megjavitja a mezot, amin all. A polimorfikus receiveRepair() csak a Pipe-ot es a Pump-ot javitja meg.
	public void repair() {
		System.out.println(this + ".repair()");
		this.getLocation().receiveRepair();
	}
	
	// Ha van pumpaja es a mezore, amin all, le lehet tenni a pumpat, akkor leteszi es eltavolitja a hatizsakbol. 
	public boolean placePump() {
		if (!this.hasPump()) {
			System.out.println("Nincs nala pumpa, amit le lehetne rakni");
			return false;
		}
		System.out.println(this + ".placePump()");
		if (this.hasPump() && this.getLocation().receivePump(pumpInTheBackpack)) {
			this.move(pumpInTheBackpack);
			pumpInTheBackpack = null;
			System.out.println("A pumpa sikeresen lerakva");
			return true;
		}
		
		return false;
	}
	
	/*
	 * Visszaadja a hatizsakban tartott pumpat.
	 */
	@Override
	public Field showPumpInBackPack() {
		return pumpInTheBackpack;
	}
	
	// Ha nincs a szerelo kezeben cso, akkor megprobalja felvenni a pipe-ot.
	// A hivo felelossege, hogy a parameterkent adott pipe annak a mezonek a szomszedja
	// legyen, amin all.
	// A giveMePipeEnd(pipe) felelossege, hogy csak akkor adjon vissza field-et,
	// ha az egy felveheto cso, es annak gondoskodjon a helyes levalasztasarol.
	public void pickupPipe(Field pipe) {
		System.out.println(this + ".pickupPipe(" + pipe + ")");
		if (pipeInHand == null) {
			pipeInHand = this.getLocation().giveMePipeEnd(pipe);
			if (pipeInHand != null && pipeInHand.hasWater()) {
				pipeInHand.removeWater().goToWaste();
			}
		} else {
			System.out.println("Mar van cso " + this + " kezeben!");
		}
	}
	
	// Ha van a kezeben csoveg, es az le lehet tenni az adott mezore, akkor leteszi,
	// es eltavolitja a kezebol.
	public void placePipe() {
		if (pipeInHand == null) {
			System.out.println("Nincs a kezeben cso, amit lerakhatna...");
			return;
		}
		Field location = this.getLocation();
		if (pipeInHand != null && (pipeInHand.getNeighbors().size() == 0 ||!location.closeNeighborWith(pipeInHand.getNeighbors().get(0))) && location.receivePipe(pipeInHand)) {
			pipeInHand = null;
			System.out.println("A lerakas sikeres volt.");
		} else {
			System.out.println("A lerakas sikertelen volt.");
		}
	}
}
