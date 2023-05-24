package application;

/*
 * A szabotor logikai osztaja.
 */
public class Saboteur extends Player {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int staticId = 0;
	private int id = staticId++;
	
	/*
	 * Szovegge alakitas.
	 */
	@Override
	public String toString() {
		return "saboteur" + id;
	}
	
	/*
	 * A statikus szamlalo nullazasa.
	 */
	public static void resetId() {
		staticId = 0;
	}
	
	/*
	 * Megprobalja csuszossa tenni a mezot, amin all,
	 * de ennek csak csovon lesz eredmenye.
	 */
	public void tryToMakeSlippery() {
		this.getLocation().tryToBecomeSlippery();
	}
	
	/*
	 * Lep.
	 */
	@Override
	public void step() {
		System.out.println(this + ".step()");
		super.step();
	}	
}
