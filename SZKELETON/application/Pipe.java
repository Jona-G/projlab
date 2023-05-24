package application;

public class Pipe extends VulnerableField {
	
	/* Amikor a cso vizet kap akkor az elfolyik, ha a cso lyukas, vagy csak egy szomszedja van.
	 * Ha nincs gond akkor a kapott viz a VulnerableField ososztaly tarolojaba kerul.
	 * A hivo felelossege megnezni, hogy a kuldott viznek van-e helye.
	 */
	@Override
	public void receiveWater(Water w) {
		System.out.println("Pipe.receiveWater(Water w)");
		
		System.out.println("Serult a cso, vagy kevesebb, mint ketto szomszedja van (vagyis az egyik vege szabad)?");
		Skeleton.printOptions();
		
		if (Skeleton.readNumber() == 1) {
			Skeleton.getWater().goToWaste();
			return;
		}
		
		super.receiveWater(null);
	}
	
	// Kilyukasztaskor megserul, es ha van benne ekkor viz, az ki is folyik egybol.
	@Override
	public void receivePuncture() {
		System.out.println("Pipe.receivePuncture()");
		
		Skeleton.getPipe().setDamaged(true);
		
		System.out.println("Van a csoben viz?");
		Skeleton.printOptions();
		
		if (Skeleton.readNumber() == 1) {
			Skeleton.getPipe().removeWater();
			Skeleton.getWater().goToWaste();
		}
	}
	
	// Ha mar allnak a csovon, ha viszik, vagy a ciszternanal levo szabad vegu cso, akkor nem lehet ralepni.
	@Override
	public boolean canReceivePlayer() {
		System.out.println("Pipe.canReceivePlayer()");
		System.out.println("Allnak mar a csovon, vagy viszik a csovet, vagy a cso egyik vege szabadon van?");
		Skeleton.printOptions();
		
		if (Skeleton.readNumber() == 1) {
			System.out.println("Nem lehet ralepni erre a csore.");
			return false;
		}
		
		System.out.println("Ra lehet lepni erre a csore.");
		return true;
	}
	
	/* A cso az egyik (x) szomszedjaval megszunik a szomszedsag (a "cso kettevagasa" miatt).
	 * A cso az x helyett a lerakott pumpa szomszedja lesz.
	 * A cso lesz a lerakott pumpa bemenete.	
	 * A "cso kettevagasabol" keletkezik egy uj cso (pipe);
	 * A pipe az x szomszedja lesz (mert a kettevagas elott az eredeti cso volt itt, most az uj pipe lesz itt).
	 * A pipe a lerakott pumpa szomszedja lesz.
	 * A pipe a lerakott pumpa kimenete lesz.
	 */
	@Override
	public boolean receivePump(Field pumpInTheBackpack) {
		System.out.println("Pipe.receivePump(Field pumpInTheBackpack)");
		
		System.out.println("Ra lehet tenni erre a csore a pumpat, van neki eleg hely a palyan?");
		Skeleton.printOptions();
		
		if (Skeleton.readNumber() == 1) {
			Skeleton.getPipe().getNeighbors();
			Skeleton.getPipe().stopBeingNeighbors(null);
			Skeleton.getPipe().becomeNeighbors(null);
			Skeleton.getPump().selectInput(null);
			
			Skeleton.getPipe().becomeNeighbors(null);
			Skeleton.getPipe().becomeNeighbors(null);
			Skeleton.getPump().selectOutput(null);
			System.out.println("Kettevagtuk a csovet, az egyik elvagott veg a bemenetre, a masik a pumpa kimenetere kerult.");
			return true;
		} else {
			System.out.println("Akkor most nem lehetett ide a pumpat letenni.");
			return false;
		}
	}
	
	// Cso szomszedjat nem lehet felvenni, ezert ez itt return null;
	@Override
	public Field giveMePipeEnd(Field targetField) {
		System.out.println("giveMePipeEnd(Field targetField) Cso szomszedjat nem lehet felvenni.");
		return null;
	}
	
	// Csohoz csovet ebben a rendszerben nem lehet csatlakoztatni.
	@Override
	public boolean receivePipe(Field f) {
		System.out.println("Pipe.receivePipe(Field f) Csohoz csovet ebben a jatekban nem lehet csatlakoztatni.");
		return false;
	}
}
