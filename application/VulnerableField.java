package application;

public abstract class VulnerableField extends Field {
	
	@Override
	public boolean hasWater() {
		System.out.println("VulnerableField.hasWater()");
		System.out.println("Van vize?");
		Skeleton.printOptions();
		
		if (Skeleton.readNumber() == 1) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean isDamaged() {
		System.out.println("VulnerableField.isDamaged()");
		System.out.println("Serult?");
		Skeleton.printOptions();
		
		if (Skeleton.readNumber() == 1) {
			return true;
		}
		
		return false;
	}
	
	public void setDamaged(boolean damaged) {
		System.out.println("VulnerableField.setDamaged(boolean damaged)");
	}
	
	/* Kiveszi es visszaadja a tarolt vizet. A tarolo erteket null-ra allitja, azaz
	 * uresre. A hivo felelossege, hogy ellenorizze, hogy van-e viz, amit ki lehet venni
	 * a hivas elott.
	 */
	@Override
	public Water removeWater() {
		System.out.println("VulnerableField.removeWater()");
		return null;
	}
	
	/* A taroloba beteszi a kapott vizet. A hivo felelossege megnezni, hogy a tarolo
	 * ures-e a hivas elott, azaz a tarolo erteke null.
	 */
	@Override
	public void receiveWater(Water w) {
		System.out.println("VulnerableField.receiveWater(Water w)");
	}
	
	// Megjavul a javitas hatasara.
	@Override
	public void receiveRepair() {
		System.out.println("VulnerableField.receiveRepair()");
	}
	
}
