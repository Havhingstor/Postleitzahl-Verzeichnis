package datenstruktur;

import modell.Sortierelement;
import modell.VergleichRueckgabe;

public class PlzZuID implements Sortierelement<Integer> {
	private int id;
	private int plz;
	
	public PlzZuID(Postleitzahl plz) {
		id=plz.getID();
		this.plz=plz.getPLZInt();
	}
	
	@Override
	public VergleichRueckgabe vergleiche(Sortierelement<Integer> s) {
		if(plz<s.getDaten()) {
			return VergleichRueckgabe.KLEINER;
		}else if(plz>s.getDaten()) {
			return VergleichRueckgabe.GROESSER;
		}else {
			return VergleichRueckgabe.GLEICH;
		}
	}
	
	@Override
	public VergleichRueckgabe suche(Integer suchbegriff, boolean genau) {
		if(plz<suchbegriff) {
			return VergleichRueckgabe.KLEINER;
		}else if(plz>suchbegriff) {
			return VergleichRueckgabe.GROESSER;
		}else {
			return VergleichRueckgabe.GLEICH;
		}
	}
	
	@Override
	public Integer getDaten() {
		return plz;
	}
	
	public int getID() {
		return id;
	}
	
}
