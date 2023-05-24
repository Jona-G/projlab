package application;

public class Source extends Field {
	
	// Vegigmegy az osszes szomszedjan, ha nincs nekik vizuk, akkor ad nekik egy ujat.
	public void push() {
		System.out.println("Source.push()");
		
		while (true) {
			System.out.println("Van meg a forrasnak cso szomszedja?");
			Skeleton.printOptions();
			
			if (Skeleton.readNumber() == 1) {
				System.out.println("Ez a cso ures?");
				Skeleton.printOptions();
				if (Skeleton.readNumber() == 1) {
					Skeleton.getPipe().receiveWater(null);
				}
			} else {
				break;
			}
		}
	}
}