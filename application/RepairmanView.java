package application;

import java.awt.Color;
import java.awt.Graphics;

public class RepairmanView extends PlayerView {
	private static final long serialVersionUID = 1L;
	private Repairman repairman;
	
	public RepairmanView(Repairman repairman) {
		this.repairman = repairman;
		Game.getPlayerViewList().add(this);
		Game.getPlayerMap().put(repairman, this);
	}

	@Override
	public Player returnPlayer() {
		return repairman;
	}

	public Repairman getRepairman() {
		return repairman;
	}
	
	@Override
	public void display(Graphics g) {
		g.setColor(Color.ORANGE);
		
		if (repairman.getParalyzedTime() > 0) {
			g.setColor(Color.MAGENTA);
		}
		
		x = repairman.getLocation().getView().getXRef();
		y = repairman.getLocation().getView().getYRef();
		g.fillRect(x - 5, y - 30, 10, 30);
		if (repairman.hasPump()) {
			g.setColor(Color.GREEN);
			g.fillRect(x, y - 25, 10, 10);
			g.setColor(Color.ORANGE);
		}
		
		g.drawString(repairman.toString(), x - 20, y - 40);
		g.drawString(Integer.toString(Game.getRepairmanScore()), 370, 20);
	}
	
	@Override
	public void pickupPipe(int validIndex) {
		repairman.pickupPipe(repairman.getLocation().getNeighbors().get(validIndex));
	}
	
	@Override
	public void megkerdezik() {
		repairman.step();
		Game.getCanvas().repaint();
		String elso = "=====> Mit szeretne csinalni a " + repairman.toString() + "?";
		Game.print(elso);
		Game.println("1 Lepni valahova.");
		Game.println("2 Megjavitani a mezot, amin all. (Csak csonel es pumpanal lesz ennek eredmenye.)");
		Game.println("3 Szivofejet allitani. (Csak pumpanal lesz ennek eredmenye.)");
		Game.println("4 Kimenofejet allitani (Csak pumpanal lesz ennek eredmenye.)");
		Game.println("5 Kivalasztott csoveget felvenni a kezebe.");
		Game.println("6 Kezben levo csoveget csatlakoztatni a mezohoz, amin all. (Csovon allva ennek biztosan nem lesz eredmenye.)");
		Game.println("7 Pumpat lerakni. (Csak csovon allva lesz ennek eredmenye.)");
		Game.println("8 Kilyukasztani a mezot, amin all. (Csak csonel lesz ennek eredmenye.)");
		Game.println("9 Ragadossa teszi a mezot, amin all. (Csak csonel lesz ennek eredmenye.)");	
		Game.println("egyeb Ne csinaljon semmit.");
	}
	
	@Override
	public void cselekszik(int answer) {
		//var neighbors = repairman.getLocation().getNeighbors();
		//int neighborSize = neighbors.size();
		
		if (answer == 1) {
			/*
			if (neighborSize == 0) {
				System.out.println("Nincs hova lepni...");
				return;
			}
			System.out.println("=====> Hova szeretne lepni? " + this + " located at " + location + " => " + (isLocationDamaged ? "damaged" : "not damaged"));
			for (int i = 0; i < neighborSize - 1; ++i) {
				System.out.println((i + 1) + " " + neighbors.get(i));
			}
			System.out.println("egyeb " + neighbors.get(neighborSize - 1));
			*/
			
			if (!Game.printNeighbors(repairman, "=====> Hova szeretne lepni?", "Nincs hova lepni...")) {
				return;
			}
			Game.state = 31;
			//answer = Skeleton.normalToValidIndex(neighborSize);
			//repairman.move(neighbors.get(answer));
			
		} else if (answer == 2) {
			repairman.repair();
			Game.rollToNext();
		} else if (answer == 3) {
			/*
			if (neighborSize == 0) {
				System.out.println("Nincs mihez csatlakoznia...");
				return;
			}
			System.out.println("=====> Mihez csatlakozzon a szivofej?");
			for (int i = 0; i < neighborSize - 1; ++i) {
				System.out.println((i + 1) + " " + neighbors.get(i));
			}
			System.out.println("egyeb " + neighbors.get(neighborSize - 1));
			answer = Skeleton.readNumber() - 1;
			answer = (answer < 0 || answer > neighborSize - 1) ? neighborSize - 1 : answer;
			*/
			if (!Game.printNeighbors(repairman, "=====> Mihez csatlakozzon a szivofej?", "Nincs mihez csatlakoznia...")) {
				return;
			}
			Game.state = 32;
			//answer = Skeleton.normalToValidIndex(neighborSize);
			//repairman.getLocation().selectInput(neighbors.get(answer));
		} else if (answer == 4) {			
			if (!Game.printNeighbors(repairman, "=====> Mihez csatlakozzon a kimenofej?", "Nincs mihez csatlakoznia...")) {
				return;
			}
			Game.state = 33;
			//answer = Skeleton.normalToValidIndex(neighborSize);			
			//repairman.getLocation().selectOutput(neighbors.get(answer));
		} else if (answer == 5) {
			if (!Game.printNeighbors(repairman, "=====> Mit vegyek fel a kezembe?", "Nincs mit felvenni...")) {
				return;
			}
			Game.state = 34;
			Game.getCanvas().repaint();
			//answer = Skeleton.normalToValidIndex(neighborSize);	
			//repairman.pickupPipe(neighbors.get(answer));
		} else if (answer == 6) {
			repairman.placePipe();
			Game.rollToNext();
		} else if (answer == 7) {
			int tmpX = repairman.getLocation().getView().getXRef();
			System.out.println("1");
			int tmpY = repairman.getLocation().getView().getYRef();
			System.out.println("2");
			var pumpView = repairman.showPumpInBackPack().getView();
			System.out.println("3");
			if(repairman.placePump()) {
				System.out.println("4");
				pumpView.move(tmpX, tmpY);
				System.out.println("5");
				new PipeView(Game.getFieldList().get(Game.getFieldList().size() - 1));
				System.out.println("6");
				Game.getCanvas().repaint();
			}
			Game.rollToNext();
		} else if (answer == 8) {
			repairman.puncture();
			Game.rollToNext();
		} else if (answer == 9) {
			repairman.tryToMakeSticky();
			Game.rollToNext();
		} else {
			// Do nothing...
			Game.rollToNext();
		}
		Game.getCanvas().repaint();
	}
}
