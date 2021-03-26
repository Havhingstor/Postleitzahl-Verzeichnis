package datenstruktur;

import modell.Sortierelement;
import modell.VergleichRueckgabe;

public class Postleitzahl implements Sortierelement<Integer> {
	private int postleitzahl;
	private String ortsname;
	
	public Postleitzahl(int postleitzahl, String ortsname) {
		if(postleitzahl<0) {
			postleitzahl*=-1;
		}
		if(postleitzahl>99999) {
			postleitzahl%=100000;
		}
		this.postleitzahl=postleitzahl;
		this.ortsname=ortsname;
	}
	
	@Override
	public boolean istKleinerAls(Sortierelement<Integer> s) {
		return postleitzahl<s.getDaten();
	}

	@Override
	public VergleichRueckgabe suche(Integer suchbegriff, boolean genau) {
		if(postleitzahl<suchbegriff) {
			return VergleichRueckgabe.KLEINER;
		}else if(postleitzahl>suchbegriff) {
			return VergleichRueckgabe.GROESSER;
		}else {
			return VergleichRueckgabe.GLEICH;
		}
	}

	@Override
	public Integer getDaten() {
		return postleitzahl;
	}
	
	@Override
	public String toString() {
		return ortsname+"("+getPLZ()+")";
	}
	
	public String getPLZ() {
		String plzStr=""+postleitzahl;
		String returner="";
		for(int i=0;i<(5-plzStr.length());++i){
			returner+="0";
		}
		returner+=plzStr;
		return returner;
	}
	
	public String getOrt() {
		return ortsname;
	}
		
}
