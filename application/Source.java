package application;

public class Source extends Field {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int staticId = 0;
	private int id = staticId++;
	
	@Override
	public String toString() {
		return "source" + id;
	}
	
	public static void resetId() {
		staticId = 0;
	}
	
	// Vegigmegy az osszes szomszedjan, ha nincs nekik vizuk, akkor ad nekik egy ujat.
	public void push() {
		System.out.println(this + ".push()");
		for (Field f : this.getNeighbors()) {
			if (!f.hasWater()) {
				f.receiveWater(new Water());
			}
		}
	}
	
}
