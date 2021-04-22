package main;

import modell.Sortierelement;
import modell.VergleichRueckgabe;

public class Testeintrag implements Sortierelement<Integer> {
	int nr;
	
	Testeintrag(int nr){
		this.nr=nr;
	}
	
	@Override
	public boolean istKleinerAls(Sortierelement<Integer> s) {
		return this.nr<s.getDaten();
	}

	@Override
	public VergleichRueckgabe suche(Integer suchbegriff, boolean genau) {
		if(suchbegriff==nr) {
			return VergleichRueckgabe.GLEICH;
		}else if(nr>suchbegriff) {
			return VergleichRueckgabe.GROESSER;
		}else {
			return VergleichRueckgabe.KLEINER;
		}
	}

	@Override
	public Integer getDaten() {
		return nr;
	}
	
	public String toString() {
		return "Testeintrag: "+nr;
	}
	
}
