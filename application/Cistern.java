package application;

public class Cistern extends Field {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int staticId = 0;
	private int id = staticId++;
	
	@Override
	public String toString() {
		return "cistern" + id;
	}
	
	// Felelossege hogy megnezze az osszes szomszedjan, hogy van-e ott viz,
	// ha van akkor elveszi es ez a viz a ciszternaba jut.
	@Override
	public void pull() {
		System.out.println(this + ".pull()");
		for (Field f : this.getNeighbors()) {
			if (f.hasWater()) {
				f.removeWater().goToCistern();
			}
		}
	}
	
	// Megnezi, hogy a jatekosnak van-e pumpaja a hatizsakjaban, ha nincs akkor ad neki egy ujat.
	// Szabotor sosem kap, mert neki mindig hasPump() == true.
	// Megnezi, hogy a ralepeskor van-e neki uj csove (ami csak a cisternahoz kapcsologik, a masik szomszedja null),
	// ha nincs, akkor gyorsan csinal egyet, ha van akkor nem.
	@Override
	public void addPlayer(Player p) {
		System.out.println(this + ".addPlayer(" + p + ")");
		super.addPlayer(p);
		
		if (!p.hasPump()) {
			p.receivePump(new Pump());
		}
		
		if (!this.hasNewPipe()) {
			var pipe = new Pipe();
			this.becomeNeighbors(pipe);
			Game.addField(pipe);
		}
	}
	
	// Megnezi, hogy van-e neki uj csove (ami csak a cisternahoz kapcsologik, a masik szomszedja null).
	public boolean hasNewPipe() {
		System.out.println(this + ".hasNewPipe()");
		for (Field pipe : this.getNeighbors()) {
			if (pipe.getNeighbors().size() == 1 && !pipe.isCarried()) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public Field showNewPipe() {
		for (Field pipe : this.getNeighbors()) {
			if (pipe.getNeighbors().size() == 1 && !pipe.isCarried()) {
				return pipe;
			}
		}
		
		return null;
	}
}
