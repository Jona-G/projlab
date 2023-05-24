package application;

public class Saboteur extends Player {
	
	@Override
	public void step() {
		System.out.println("Saboteur.step()");
		
		System.out.println("Mit szeretne csinalni a szabotor?");
		System.out.println("1 Lepni valahova.");
		System.out.println("2 Kilyukasztani a mezot, amin all. (Csak csonel lesz ennek eredmenye.)");
		System.out.println("3 Szivofejet allitani. (Csak pumpanal lesz ennek eredmenye.)");
		System.out.println("egyeb Kimenofejet allitani. (Csak pumpanal lesz ennek eredmenye.)");
		
		int answer = Skeleton.readNumber();
		
		if (answer == 1) {
			Skeleton.getSaboteur().move(null);
		} else if (answer == 2) {
			Skeleton.getSaboteur().puncture();
		} else if (answer == 3) {
			Skeleton.getSaboteur().adjustInput(null);
		} else {
			Skeleton.getSaboteur().adjustOutput(null);
		}
	}
	
	// Kilyukasztja amin all. A polimorfikus receivePuncture() miatt ez csak csovet lyukaszt ki.
	public void puncture() {
		System.out.println("Saboteur.puncture()");
		
		Skeleton.getSaboteur().getLocation();
		System.out.println("Hol all a szabotor?");
		System.out.println("1 Ciszternan");
		System.out.println("2 Csovon");
		System.out.println("3 Pumpan");
		System.out.println("egyeb Forrason");
		
		int answer = Skeleton.readNumber();
		
		if (answer == 1) {
			Skeleton.getCistern().receivePuncture();
		} else if (answer == 2) {
			Skeleton.getPipe().receivePuncture();
		} else if (answer == 3) {
			Skeleton.getPump().receivePuncture();
		} else {
			Skeleton.getSource().receivePuncture();
		}
	}
	
}
