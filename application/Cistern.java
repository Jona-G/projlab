package application;

public class Cistern extends Field {
	
	/* Felelossege hogy megnezze az osszes szomszedjan, hogy van-e ott viz,
	 * ha van akkor elveszi es ez a viz a ciszternaba jut.
	 */
	@Override
	public void pull() {
		System.out.println("Cistern.pull()");
		
		while (true) {
			System.out.println("Van meg a ciszternanak cso szomszedja?");
			Skeleton.printOptions();
			
			if (Skeleton.readNumber() == 1) {
				System.out.println("Van neki vize?");
				Skeleton.printOptions();
				if (Skeleton.readNumber() == 1) {
					Skeleton.getPipe().removeWater();
					Skeleton.getWater().goToCistern();
				}
			} else {
				break;
			}
		}
	}
	
	/* Megnezi, hogy a jatekosnak van-e pumpaja a hatizsakjaban, ha nincs akkor ad neki egy ujat.
	 * Szabotor sosem kap, mert neki mindig hasPump() == true.
	 * Megnezi, hogy a ralepeskor van-e neki uj csove (ami csak a cisternahoz kapcsologik, a masik szomszedja null),
	 * ha nincs, akkor gyorsan csinal egyet, ha van akkor nem.
	 */
	@Override
	public void addPlayer(Player p) {
		System.out.println("Cistern.addPlayer(Player p)");
		
		super.addPlayer(null);

		System.out.println("Ki lepett a ciszternara?");
		System.out.println("1 Szerelo");
		System.out.println("egyeb Szabotor");
		
		int answer = Skeleton.readNumber();
		boolean hasPump;
		
		if (answer == 1) {
			hasPump = Skeleton.getRepairman().hasPump();
		} else {
			hasPump = Skeleton.getSaboteur().hasPump();
		}
		
		if (!hasPump && answer == 1) {
			Skeleton.getRepairman().receivePump(null);
		} else if (!hasPump) {
			Skeleton.getSaboteur().receivePump(null);
		}

		System.out.println("Van a ciszternanak szabad vegu felveheto csove?");
		Skeleton.printOptions();
		
		if (Skeleton.readNumber() != 1) {
			Skeleton.getCistern().becomeNeighbors(null);
			Skeleton.getPipe().addNeighbor(null);
			Game.addField(null);
		}
	}
	
	// Megnezi, hogy van-e neki uj csove (ami csak a cisternahoz kapcsologik, a masik szomszedja null).
	public boolean hasNewPipe() {
		System.out.println("Cistern.hasNewPipe()");
		
		while (true) {
			System.out.println("Van meg a ciszternanak cso szomszedja?");
			Skeleton.printOptions();
			if (Skeleton.readNumber() == 1) {
				System.out.println("Ennek a csonek az egyik vege szabad?");
				Skeleton.printOptions();
				if (Skeleton.readNumber() == 1) {
					return true;
				}
			} else {
				return false;
			}
		}
	}
}
