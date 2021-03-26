package main;

import modell.Sortierelement;
import modell.VergleichRueckgabe;

public class Testeintrag implements Sortierelement<Long> {
	long nr;
	
	Testeintrag(long nr){
		this.nr=nr;
	}
	
	@Override
	public boolean istKleinerAls(Sortierelement<Long> s) {
		return this.nr<s.getDaten();
	}

	@Override
	public VergleichRueckgabe suche(Long suchbegriff, boolean genau) {
		if(suchbegriff==nr) {
			return VergleichRueckgabe.GLEICH;
		}else if(nr>suchbegriff) {
			return VergleichRueckgabe.GROESSER;
		}else {
			return VergleichRueckgabe.KLEINER;
		}
	}

	@Override
	public Long getDaten() {
		return nr;
	}
	
	public String toString() {
		return "Testeintrag: "+nr;
	}
	
}
