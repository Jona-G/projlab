package application;

/*
 * A pumpa logikai osztalya.
 */
public class Pump extends VulnerableField {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Field input = null;
	private Field output = null;
	private static int staticId = 0;
	private int id = staticId++;
	
	/*
	 * Szovegge alakitas.
	 */
	@Override
	public String toString() {
		return "pump" + id;
	}
	
	/*
	 * A statikus szamlalo nullazasa.
	 */
	public static void resetId() {
		staticId = 0;
	}
	
	
	// Override az input es output miatt, azoknak az erteke null lesz, ha leveszik
	// roluk a csovet. Ilyenkor nem tud majd kitolni, vagy beszivni, mert ott el van zarva a fej.
	@Override
	public Field giveMePipeEnd(Field targetField) {
		System.out.println( this + ".giveMePipeEnd(" + targetField + ")");
		Field result = super.giveMePipeEnd(targetField);
		
		if (result == input) {
			input = null;
		} else if (result == output) {
			output = null;
		}
		
		return result;
	}
	
	// Kitolas elott, es csak ekkor, magatol elromolhat.
	// Az Pump felelossege megvizsgalni a vizkitolas felteteleit.
	// Ha nem serult es van vize es az outputjan van cso es ebben a csoben nincs viz
	// akkor beletolja a magaban tarolt vizet.
	// Ha a feltetelek nem teljesulnek, akkor nem tol, ekkor ha van a pumpaban viz, benne marad.
	@Override
	public void push() {
		System.out.println(this + ".push()");
		if (Math.abs(Game.getRandom().nextInt()) % 100 < 5) {
			this.breaksDown();
		}
		
		if (!this.isDamaged() && this.hasWater() && output != null && !output.hasWater()) {
			output.receiveWater(this.removeWater());
		}
	}
	
	// Az Pump felelossege megvizsgalni a vizbehuzas felteteleit.
	// Ha nem serult es nincs vize es az inputjan van cso es ebben a csoben van viz
	// akkor a csobol kiveszi a vizet, es a tarolojaba teszi.
	@Override
	public void pull() {
		System.out.println(this + ".pull()");
		if (!this.isDamaged() && !this.hasWater() && input != null && input.hasWater()) {
			this.receiveWater(input.removeWater());
		}
	}
	
	// Elromlik.
	@Override
	public void breaksDown() {
		System.out.println(this + ".breaksDown()");
		if (Game.isRandomDisabled()) {
			System.out.println("Random kikapcsolva, nem romlik el.");
			return;
		}
		this.setDamaged(true);
	}

	/*
	 * Getter.
	 */
	public Field getInput() {
		System.out.println(this + ".getInput()");
		return input;
	}
	
	// Beallitja az szivofejet/inputot a parameterkent kapott Field-re.
	// A hivo felelossege, hogy az adott parameter a Pump szomszedja legyen,
	// es hogy elkerulje, hogy az input es az output azonos legyen.
	@Override
	public void selectInput(Field input) {
		System.out.println(this + ".selectInput(" + input + ")");
		if (input == this.output) {
			this.output = null;
			System.out.println("A kimenofej el lett zarva mert erre a csore lett raallitva a szivofej.");
		}
		this.input = input;
	}

	/*
	 * Getter.
	 */
	public Field getOutput() {
		System.out.println(this + ".getOutput()");
		return output;
	}

	// Beallitja az kimeno fejet/outputot a parameterkent kapott Field-re.
	// A hivo felelossege, hogy az adott parameter a Pump szomszedja legyen,
	// es hogy elkerulje, hogy az input es az output azonos legyen.
	@Override
	public void selectOutput(Field output) {
		System.out.println(this + ".selectOutput(" + output + ")");
		if (output == this.input) {
			this.input = null;
			System.out.println("A szivofej el lett zarva mert erre a csore lett raallitva a kimenofej.");
		}
		this.output = output;
	}
}
