package application;

public class Saboteur extends Player {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int staticId = 0;
	private int id = staticId++;
	
	@Override
	public String toString() {
		return "saboteur" + id;
	}
	
	public void tryToMakeSlippery() {
		this.getLocation().tryToBecomeSlippery();
	}
	
	@Override
	public void step() {
		System.out.println(this + ".step()");
		super.step();
		Field location = this.getLocation();
		boolean isLocationDamaged = location.isDamaged();
		
		System.out.println("\t\t\t\t\tRepairmen score: " + Game.getRepairmanScore());
		System.out.println("\t\t\t\t\tSaboteur score: " + Game.getSaboteurScore());
		
		System.out.println("=====> Mit szeretne csinalni a szabotor? " + this + " located at " + location + " => " + (isLocationDamaged ? "damaged" : "not damaged"));
		System.out.println("1 Lepni valahova.");
		System.out.println("2 Kilyukasztani a mezot, amin all. (Csak csonel lesz ennek eredmenye.)");
		System.out.println("3 Szivofejet allitani. (Csak pumpanal lesz ennek eredmenye.)");
		System.out.println("4 Kimenofejet allitani. (Csak pumpanal lesz ennek eredmenye.)");
		System.out.println("5 Ragadossa teszi a mezot, amin all. (Csak csonel lesz ennek eredmenye.)");
		System.out.println("6 Csuszossa teszi a mezot, amin all. (Csak csonel lesz ennek eredmenye.)");
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
			this.puncture();
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
			this.tryToMakeSticky();
		} else if (answer == 6) {
			this.tryToMakeSlippery();
		} else {
			// Do nothing...
		}
	}	
}
