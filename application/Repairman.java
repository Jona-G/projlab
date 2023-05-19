package application;

public class Repairman extends Player {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Field pumpInTheBackpack = null;
	private Field pipeInHand = null;	
	private static int staticId = 0;
	private int id = staticId++;
	
	@Override
	public String toString() {
		return "repairman" + id;
	}
	
	/*
	 * 
	 */
	@Override
	public Field showPipeInHand() {
		return pipeInHand;
	}
	
	@Override
	public void step() {
		System.out.println(this + ".step()");
		super.step();/*
		Field location = this.getLocation();
		boolean isLocationDamaged = location.isDamaged();
		
		System.out.println("\t\t\t\t\tRepairmen score: " + Game.getRepairmanScore());
		System.out.println("\t\t\t\t\tSaboteur score: " + Game.getSaboteurScore());
		
		System.out.println("=====> Mit szeretne csinalni a szerelo? " + this + " located at " + location + " [" + (isLocationDamaged ? "damaged]" : "not damaged]"));
		System.out.println("1 Lepni valahova.");
		System.out.println("2 Megjavitani a mezot, amin all. (Csak csonel es pumpanal lesz ennek eredmenye.)");
		System.out.println("3 Szivofejet allitani. (Csak pumpanal lesz ennek eredmenye.)");
		System.out.println("4 Kimenofejet allitani (Csak pumpanal lesz ennek eredmenye.)");
		System.out.println("5 Kivalasztott csoveget felvenni a kezebe.");
		System.out.println("6 Kezben levo csoveget csatlakoztatni a mezohoz, amin all. (Csovon allva ennek biztosan nem lesz eredmenye.)");
		System.out.println("7 Pumpat lerakni. (Csak csovon allva lesz ennek eredmenye.)");
		System.out.println("8 Kilyukasztani a mezot, amin all. (Csak csonel lesz ennek eredmenye.)");
		System.out.println("9 Ragadossa teszi a mezot, amin all. (Csak csonel lesz ennek eredmenye.)");	
		System.out.println("egyeb Ne csinaljon semmit.");
		
		int answer = Skeleton.readNumber();
		var neighbors = this.getLocation().getNeighbors();
		int neighborSize = neighbors.size();
		
		if (answer == 1) {
			
			if (!Skeleton.printNeighbors(this, "=====> Hova szeretne lepni?", "Nincs hova lepni...")) {
				return;
			}
			answer = Skeleton.normalToValidIndex(neighborSize);
			this.move(neighbors.get(answer));
			
		} else if (answer == 2) {
			this.repair();
		} else if (answer == 3) {
			if (!Skeleton.printNeighbors(this, "=====> Mihez csatlakozzon a szivofej?", "Nincs mihez csatlakoznia...")) {
				return;
			}
			answer = Skeleton.normalToValidIndex(neighborSize);
			location.selectInput(neighbors.get(answer));
		} else if (answer == 4) {			
			if (!Skeleton.printNeighbors(this, "=====> Mihez csatlakozzon a kimenofej?", "Nincs mihez csatlakoznia...")) {
				return;
			}
			answer = Skeleton.normalToValidIndex(neighborSize);			
			location.selectOutput(neighbors.get(answer));
		} else if (answer == 5) {
			if (!Skeleton.printNeighbors(this, "=====> Mit vegyek fel a kezembe?", "Nincs mit felvenni...")) {
				return;
			}
			answer = Skeleton.normalToValidIndex(neighborSize);	
			this.pickupPipe(neighbors.get(answer));
		} else if (answer == 6) {
			this.placePipe();
		} else if (answer == 7) {
			this.placePump();
		} else if (answer == 8) {
			this.puncture();
		} else if (answer == 9) {
			this.tryToMakeSticky();
		} else {
			// Do nothing...
		}*/
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
