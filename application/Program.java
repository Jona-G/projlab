package application;

public class Program {
	
	public static void main(String[] args) {
		
		System.out.println("Udvozollek ebben a teszprogramban, kerlek valassz a felkinalt lehetosegek kozul!");
		
		while (true) {
			System.out.println("Mit szeretnel tesztelni?");
			
			System.out.println("1 Az egesz jatekot. (A csapat nyer esetet itt lehet kiprobalni.)");
			System.out.println("2 A szerelo lep valahova.");
			System.out.println("3 A szerelo megjavitja a mezot, amin all. (Csak csonel es pumpanal lesz ennek eredmenye.)");
			System.out.println("4 A szerelo szivofejet allit. (Csak pumpanal lesz ennek eredmenye.)");
			System.out.println("5 A szerelo kimenofejet allit. (Csak pumpanal lesz ennek eredmenye.)");
			System.out.println("6 A szerelo a kivalasztott csoveget felveszi a kezebe. (Csovon allva ennek biztosan nem lesz eredmenye.)");
			System.out.println("7 A szerelo a kezben levo csoveget csatlakoztatja a mezohoz, amin all. (Csovon allva ennek biztosan nem lesz eredmenye.)");
			System.out.println("8 A szabotor lep valahova.");
			System.out.println("9 A szabotor kilyukasztja a mezot, amin all. (Csak csonel lesz ennek eredmenye.)");
			System.out.println("10 A szabotor a szivofejet allitja. (Csak pumpanal lesz ennek eredmenye.)");
			System.out.println("11 A szabotor a kimenofejet allitja. (Csak pumpanal lesz ennek eredmenye.)");
			System.out.println("12 A cso vizet kap.");		
			System.out.println("13 A pumpa vizet tol ki. (A random elromlast is itt lehet kiprobalni.)");
			System.out.println("14 A pumpa vizet huz be.");
			System.out.println("15 A forras vizet biztosit.");
			System.out.println("16 A cisterna vizet gyujt.");
			System.out.println("17 Vizet mellefolyat.");
			
			System.out.println("egyeb Vegeztem, szeretnek kilepni.");
			
			int answer = Skeleton.readNumber();
			boolean wantToExit = false;
			
			switch (answer) {
			case 1:
				Game.init();
				Game.start();
				break;
			case 2:
				Skeleton.getRepairman().move(null);
				break;
			case 3:
				Skeleton.getRepairman().repair();
				break;
			case 4:
				Skeleton.getRepairman().adjustInput(null);
				break;
			case 5:
				Skeleton.getRepairman().adjustOutput(null);
				break;
			case 6:
				Skeleton.getRepairman().pickupPipe(null);
				break;
			case 7:
				Skeleton.getRepairman().placePipe();
				break;
			case 8:
				Skeleton.getSaboteur().move(null);
				break;
			case 9:
				Skeleton.getSaboteur().puncture();
				break;
			case 10:
				Skeleton.getSaboteur().adjustInput(null);
				break;
			case 11:
				Skeleton.getSaboteur().adjustOutput(null);
				break;
			case 12:
				Skeleton.getPipe().receiveWater(null);
				break;
			case 13:
				Skeleton.getPump().push();
				break;
			case 14:
				Skeleton.getPump().pull();
				break;
			case 15:
				Skeleton.getSource().push();
				break;
			case 16:
				Skeleton.getCistern().pull();
				break;
			case 17:
				Skeleton.getWater().goToWaste();
				break;

			case 0:
			default:
				wantToExit = true;
				break;
			}
			
			
			if (wantToExit) {
				break;
			}
		}
		
		System.out.println("Koszonom, hogy kiprobaltad ezt a programot!");
	}

}
