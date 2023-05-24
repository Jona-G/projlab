package application;

public class Repairman extends Player {
	
	@Override
	public void step() {
		System.out.println("Repairman.step()");
		
		System.out.println("Mit szeretne csinalni a szerelo?");
		System.out.println("1 Lepni valahova.");
		System.out.println("2 Megjavitani a mezot, amin all. (Csak csonel es pumpanal lesz ennek eredmenye.)");
		System.out.println("3 Szivofejet allitani. (Csak pumpanal lesz ennek eredmenye.)");
		System.out.println("4 Kimenofejet allitani (Csak pumpanal lesz ennek eredmenye.)");
		System.out.println("5 Kivalasztott csoveget felvenni a kezebe.");
		System.out.println("egyeb Kezben levo csoveget csatlakoztatni a mezohoz, amin all. (Csovon allva ennek biztosan nem lesz eredmenye.)");
		
		int answer = Skeleton.readNumber();
		
		if (answer == 1) {
			Skeleton.getRepairman().move(null);
		} else if (answer == 2) {
			Skeleton.getRepairman().repair();
		} else if (answer == 3) {
			Skeleton.getRepairman().adjustInput(null);
		} else if (answer == 4) {
			Skeleton.getRepairman().adjustOutput(null);
		} else if (answer == 5) {
			Skeleton.getRepairman().pickupPipe(null);
		} else {
			Skeleton.getRepairman().placePipe();
		}
	}
	
	// Megnezi, hogy van-e nala pumpa.
	@Override
	public boolean hasPump() {
		System.out.println("Repairman.hasPump()");
		System.out.println("Van a szerelonek pumpaja?");
		Skeleton.printOptions();
		
		return Skeleton.readNumber() == 1;
	}
	
	// A hivo felelossege megnezni, hogy az adni kivant pumpanak van-e helye.
	@Override
	public void receivePump(Field pump) {
		System.out.println("Repairman.receivePump(Field pump)");
	}
	
	// Megjavitja a mezot, amin all. A polimorfikus receiveRepair() csak a Pipe-ot es a Pump-ot javitja meg.
	public void repair() {
		System.out.println("Repairman.repair()");
		
		Skeleton.getRepairman().getLocation();
		
		System.out.println("Hol all a szerelo?");
		System.out.println("1 Ciszternan");
		System.out.println("2 Csovon");
		System.out.println("3 Pumpan");
		System.out.println("egyeb Forrason");
		
		int answer = Skeleton.readNumber();
		
		if (answer == 1) {
			Skeleton.getCistern().receiveRepair();
		} else if (answer == 2) {
			Skeleton.getPipe().receiveRepair();
		} else if (answer == 3) {
			Skeleton.getPump().receiveRepair();
		} else {
			Skeleton.getSource().receiveRepair();
		}
	}
	
	// Ha van pumpaja es a mezore, amin all, le lehet tenni a pumpat, akkor leteszi es eltavolitja a hatizsakbol. 
	public void placePump() {
		System.out.println("Repairman.placePump()");
		
		Skeleton.getRepairman().getLocation();
		
		System.out.println("Hol all a szerelo?");
		System.out.println("1 Ciszternan");
		System.out.println("2 Csovon");
		System.out.println("3 Pumpan");
		System.out.println("egyeb Forrason");
		
		int answer = Skeleton.readNumber();
		boolean pumpIsPlaced;
		
		if (answer == 1) {
			pumpIsPlaced = Skeleton.getCistern().receivePump(null);
		} else if (answer == 2) {
			pumpIsPlaced = Skeleton.getPipe().receivePump(null);
		} else if (answer == 3) {
			pumpIsPlaced = Skeleton.getPump().receivePump(null);
		} else {
			pumpIsPlaced = Skeleton.getSource().receivePump(null);
		}
		
		if (pumpIsPlaced) {
			System.out.println("A pumpa le lett rakva, es el lett tavolitva a hatizsakbol.");
		}
	}
	
	/* Ha nincs a szerelo kezeben cso, akkor megprobalja felvenni a pipe-ot.
	 * A hivo felelossege, hogy a parameterkent adott pipe annak a mezonek a szomszedja
	 * legyen, amin all.
	 * A giveMePipeEnd(pipe) felelossege, hogy csak akkor adjon vissza field-et,
	 * ha az egy felveheto cso, es annak gondoskodjon a helyes levalasztasarol.
	 */
	public void pickupPipe(Field pipe) {
		System.out.println("Repairman.pickupPipe(Field pipe)");
		
		System.out.println("Van a szerelo kezeben cso?");
		Skeleton.printOptions();
		
		if (Skeleton.readNumber() != 1) {
			Skeleton.getRepairman().getLocation();
			
			System.out.println("Hol all a szerelo?");
			System.out.println("1 Ciszternan");
			System.out.println("2 Csovon");
			System.out.println("3 Pumpan");
			System.out.println("egyeb Forrason");
			
			int answer = Skeleton.readNumber();
			
			if (answer == 1) {
				Skeleton.getCistern().giveMePipeEnd(null);
			} else if (answer == 2) {
				Skeleton.getPipe().giveMePipeEnd(null);
			} else if (answer == 3) {
				Skeleton.getPump().giveMePipeEnd(null);
			} else {
				Skeleton.getSource().giveMePipeEnd(null);
			}
		}
	}
	
	/* Ha van a kezeben csoveg, es az le lehet tenni az adott mezore, akkor leteszi,
	 * es eltavolitja a kezebol.
	 */
	public void placePipe() {
		System.out.println("Repairman.placePipe()");
		
		System.out.println("Van a szerelo kezeben cso?");
		Skeleton.printOptions();
		
		if (Skeleton.readNumber() == 1) {
			Skeleton.getRepairman().getLocation();
			
			System.out.println("Hol all a szerelo?");
			System.out.println("1 Ciszternan");
			System.out.println("2 Csovon");
			System.out.println("3 Pumpan");
			System.out.println("egyeb Forrason");
			
			int answer = Skeleton.readNumber();
			boolean pipeIsPlaced;
			
			if (answer == 1) {
				pipeIsPlaced = Skeleton.getCistern().receivePipe(null);
			} else if (answer == 2) {
				pipeIsPlaced = Skeleton.getPipe().receivePipe(null);
			} else if (answer == 3) {
				pipeIsPlaced = Skeleton.getPump().receivePipe(null);
			} else {
				pipeIsPlaced = Skeleton.getSource().receivePipe(null);
			}
			
			if (pipeIsPlaced) {
				System.out.println("A cso csatlakoztatva lett, es mar nincs a szerelo kezeben.");
			}
		}
	}
}
