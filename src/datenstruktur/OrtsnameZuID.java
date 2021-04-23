package datenstruktur;

import modell.Sortierelement;
import modell.VergleichRueckgabe;

public class OrtsnameZuID implements Sortierelement<String> {
	private String name;
	private int id;
	
	public OrtsnameZuID(Postleitzahl plz) {
		name=plz.getOrt();
		id=plz.getID();
	}
	
	@Override
	public VergleichRueckgabe vergleiche(Sortierelement<String> s) {
		int vergleich=name.compareTo(s.getDaten());
		if(vergleich<0) {
			return VergleichRueckgabe.KLEINER;
		}else if(vergleich>0) {
			return VergleichRueckgabe.GROESSER;
		}else {
			return VergleichRueckgabe.GLEICH;
		}
	}

	@Override
	public VergleichRueckgabe suche(String suchbegriff, boolean genau) {
		int vergleich=0;
		if(genau) {
			vergleich=name.compareTo(suchbegriff);
		}else {
			vergleich=name.compareToIgnoreCase(suchbegriff);
		}
		if(vergleich<0) {
			return VergleichRueckgabe.KLEINER;
		}else if(vergleich>0) {
			return VergleichRueckgabe.GROESSER;
		}else {
			return VergleichRueckgabe.GLEICH;
		}
	}

	@Override
	public String getDaten() {
		return name;
	}

	public int getID() {
		return id;
	}

}
