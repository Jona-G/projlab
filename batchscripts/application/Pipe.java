package application;

public class Pipe extends VulnerableField {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int staticId = 0;
	private int id = staticId++;
	private static int shortTime = 5;
	
	private int stickyTime = 0;
	private int slipperyTime = 0;
	private int invulnerableTime = 0;
	
	@Override
	public String toString() {
		return "pipe" + id;
	}
	
	/*
	 * Megmondja, hogy viszik-e a csovet.
	 */
	@Override
	public boolean isCarried() {
		for (Player p : Game.getPlayerList()) {
			if (this == p.showPipeInHand()) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public void clearSticky() {
		System.out.println(this + ".clearSticky()");
		stickyTime = 0;
	}
	
	@Override
	public boolean fieldIsSticky() {
		System.out.println(this + ".fieldIsSticky()");
		return stickyTime > 0;
	}
	
	@Override
	public void tryToBecomeSticky() {
		System.out.println(this + ".tryToBecomeSticky()");
		System.out.println("Ragados lett a cso.");
		stickyTime = shortTime;
		slipperyTime = 0;
	}
	
	@Override
	public void tryToBecomeSlippery() {
		System.out.println(this + ".tryToBecomeSlippery()");
		System.out.println("Csuszos lett a cso.");
		slipperyTime = shortTime;
		stickyTime = 0;
	}
	
	/*
	 * Ha serult, akkor megjavul, es 3-5 korre serthetetlenne valik.
	 */
	@Override
	public void receiveRepair() {
		if (this.isDamaged()) {
			super.receiveRepair();
			if (Game.isRandomDisabled()) {
				System.out.println("Random kikapcsolva, fix 4 korre valik serhetetlenne.");
				invulnerableTime = 4;
			} else {
				invulnerableTime = Math.abs(Game.getRandom().nextInt()) % 3 + 3;
			}
			System.out.println(invulnerableTime + " korre serthetetlenne valt ez a cso.");
		}
	}
	
	/*
	 * A pipe NEM tol vizet, ezt az iteraciot arra hasznalja, hogy itt es csak itt update-elje a lejaro countereit.
	 */
	@Override
	public void push() {
		if (stickyTime > 0) {
			--stickyTime;
		}
		
		if (slipperyTime > 0) {
			--slipperyTime;
		}
		
		if (invulnerableTime > 0) {
			--invulnerableTime;
		}
		
	}
	
	// Amikor a cso vizet kap akkor az elfolyik, ha a cso lyukas, vagy csak egy szomszedja van.
	// Ha nincs gond akkor a kapott viz a VulnerableField ososztaly tarolojaba kerul.
	// A hivo felelossege megnezni, hogy a kuldott viznek van-e helye.
	@Override
	public void receiveWater(Water w) {
		System.out.println(this + ".receiveWater(" + w + ")");
		if (this.isDamaged() || this.getNeighbors().size() < 2) {
			w.goToWaste();
			return;
		}
		
		super.receiveWater(w);
	}
	
	// Kilyukasztaskor megserul, es ha van benne ekkor viz, az ki is folyik egybol.
	@Override
	public void receivePuncture() {
		System.out.println(this + ".receivePuncture()");
		
		if (invulnerableTime > 0) {
			System.out.println("Most nem lehet kilyukasztani a csovet.");
			return;
		}
		
		this.setDamaged(true);
		
		if (this.hasWater()) {
			this.removeWater().goToWaste();
		}
	}
	
	// Ha mar allnak a csovon, ha viszik, vagy nincs ket szomszedja, akkor nem lehet ralepni.
	@Override
	public boolean canReceivePlayer() {
		System.out.println(this + ".canReceivePlayer()");
		if (this.getPlayers().size() > 0 || this.getNeighbors().size() < 2) {
			return false;
		}
		
		return true;
	}
	
	// A cso az egyik (x) szomszedjaval megszunik a szomszedsag (a "cso kettevagasa" miatt).
	// A cso az x helyett a lerakott pumpa szomszedja lesz.
	// A cso lesz a lerakott pumpa bemenete.	
	// A "cso kettevagasabol" keletkezik egy uj cso (pipe);
	// A pipe az x szomszedja lesz (mert a kettevagas elott az eredeti cso volt itt, most az uj pipe lesz itt).
	// A pipe a lerakott pumpa szomszedja lesz.
	// A pipe a lerakott pumpa kimenete lesz.
	@Override
	public boolean receivePump(Field pumpInTheBackpack) {
		System.out.println(this + ".receivePump(" + pumpInTheBackpack + ")");
		// Kesobbi game logika itt donthet meg ugy, hogy most nem lehet ide pumpat letenni es return false.
		
		Field x = this.getNeighbors().get(1);
		this.stopBeingNeighbors(x);
		this.becomeNeighbors(pumpInTheBackpack);
		pumpInTheBackpack.selectInput(this);
		
		Pipe pipe = new Pipe();
		pipe.becomeNeighbors(x);
		pipe.becomeNeighbors(pumpInTheBackpack);
		pumpInTheBackpack.selectOutput(pipe);
		
		return true;
	}
	
	// Cso szomszedjat nem lehet felvenni, ezert ez itt return null;
	@Override
	public Field giveMePipeEnd(Field targetField) {
		System.out.println(this + ".giveMePipeEnd(" + targetField + ") Cso szomszedjat nem lehet felvenni!!!");
		return null;
	}
	
	// Csohoz csovet ebben a rendszerben nem lehet csatlakoztatni.
	@Override
	public boolean receivePipe(Field f) {
		System.out.println(this + ".receivePipe(" + f + ") Csohoz csovet ebben a jatekban nem lehet csatlakoztatni.");
		return false;
	}
}
