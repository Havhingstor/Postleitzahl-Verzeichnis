package datenstruktur;

import modell.Sortierelement;
import modell.VergleichRueckgabe;

public class VorwahlZuID implements Sortierelement<Integer> {
	private int vorwahl;
	private int id; 
	
	public VorwahlZuID(Postleitzahl plz) {
		id=plz.getID();
		vorwahl=plz.getVorwahlInt();
	}

	@Override
	public VergleichRueckgabe vergleiche(Sortierelement<Integer> s) {
		if(vorwahl<s.getDaten()) {
			return VergleichRueckgabe.KLEINER;
		}else if(vorwahl>s.getDaten()) {
			return VergleichRueckgabe.GROESSER;
		}else {
			return VergleichRueckgabe.GLEICH;
		}
	}

	@Override
	public VergleichRueckgabe suche(Integer suchbegriff, boolean genau) {
		if(vorwahl<suchbegriff) {
			return VergleichRueckgabe.KLEINER;
		}else if(vorwahl>suchbegriff) {
			return VergleichRueckgabe.GROESSER;
		}else {
			return VergleichRueckgabe.GLEICH;
		}
	}

	@Override
	public Integer getDaten() {
		return vorwahl;
	}
	
	public int getID() {
		return id;
	}
}
