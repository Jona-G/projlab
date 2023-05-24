package application;

public class Pump extends VulnerableField {
	
	/* Override az input es output miatt, azoknak az erteke null lesz, ha leveszik
	 * roluk a csovet. Ilyenkor nem tud majd kitolni, vagy beszivni, mert ott el van zarva a fej.
	 */
	@Override
	public Field giveMePipeEnd(Field targetField) {//===============================================================================
		System.out.println("Field.giveMePipeEnd(Field targetField)");
		
		super.giveMePipeEnd(targetField);
		
		System.out.println("A felvett csoveg a pumpa bemenofejen volt?");
		Skeleton.printOptions();
		if (Skeleton.readNumber() == 1) {
			System.out.println("A bemenofej el lett zarva.");
		} else {
			System.out.println("A felvett csoveg a pumpa kimenofejen volt?");
			Skeleton.printOptions();
			if (Skeleton.readNumber() == 1) {
				System.out.println("A kimenofej el lett zarva.");
			}
		}
		
		return null;
	}
	
	/* Kitolas elott, es csak ekkor, magatol elromolhat.
	 * Az Pump felelossege megvizsgalni a vizkitolas felteteleit.
	 * Ha nem serult es van vize es az outputjan van cso es ebben a csoben nincs viz
	 * akkor beletolja a magaban tarolt vizet.
	 * Ha a feltetelek nem teljesulnek, akkor nem tol, ekkor ha van a pumpaban viz, benne marad.
	 */
	@Override
	public void push() {
		System.out.println("Pump.push()");
		
		System.out.println("Egy kis esellyel most elromolhat a pumpa. Romoljon el?");
		Skeleton.printOptions();
		
		if (Skeleton.readNumber() == 1) {
			Skeleton.getPump().breaksDown();
		}
		
		System.out.println("A pumpa ep es van benne viz es a kimenofej nincs elzarva es a kimeno csoben nincs viz?");
		Skeleton.printOptions();
		if (Skeleton.readNumber() == 1) {
			Skeleton.getPump().removeWater();
			Skeleton.getPipe().receiveWater(null);			
		}
	}
	
	/* Az Pump felelossege megvizsgalni a vizbehuzas felteteleit.
	 * Ha nem serult es nincs vize es az inputjan van cso es ebben a csoben van viz
	 * akkor a csobol kiveszi a vizet, es a tarolojaba teszi.
	 */
	@Override
	public void pull() {
		System.out.println("Pump.pull()");
		
		System.out.println("A pumpa ep es nincs benne viz es a szivofej nincs elzarva es a bemeno csoben van viz?");
		Skeleton.printOptions();
		if (Skeleton.readNumber() == 1) {
			Skeleton.getPipe().removeWater();
			Skeleton.getPump().receiveWater(null);			
		}
	}
	
	// Elromlik.
	@Override
	public void breaksDown() {
		System.out.println("Pump.breaksDown()");
		Skeleton.getPump().setDamaged(true);
	}

	public Field getInput() {
		System.out.println("Pump.getInput()");
		return null;
	}
	
	/* Beallitja az szivofejet/inputot a parameterkent kapott Field-re.
	 * A hivo felelossege, hogy az adott parameter a Pump szomszedja legyen,
	 * es hogy elkerulje, hogy az input es az output azonos legyen.
	 */
	@Override
	public void selectInput(Field input) {
		System.out.println("Pump.selectInput(Field input)");;
	}

	public Field getOutput() {
		System.out.println("Pump.getOutput()");
		return null;
	}

	/* Beallitja az kimeno fejet/outputot a parameterkent kapott Field-re.
	 * A hivo felelossege, hogy az adott parameter a Pump szomszedja legyen,
	 * es hogy elkerulje, hogy az input es az output azonos legyen.
	 */
	@Override
	public void selectOutput(Field output) {
		System.out.println("Pump.selectOutput(Field output)");
	}
}
