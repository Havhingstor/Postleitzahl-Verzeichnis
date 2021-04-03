package datenstruktur;

import modell.Sortierelement;
import modell.VergleichRueckgabe;

public class Postleitzahl implements Sortierelement<Integer> {
	private int id;
	private int postleitzahl;
	private String ortsname;
	private int vorwahl;
	private String bundesland;
	
	public Postleitzahl(int postleitzahl, String ortsname, int vorwahl, String bundesland) {
		if(postleitzahl<0) {
			postleitzahl*=-1;
		}
		if(postleitzahl>99999) {
			postleitzahl%=100000;
		}
		if(vorwahl<0) {
			vorwahl*=-1;
		}
		this.postleitzahl=postleitzahl;
		this.ortsname=ortsname;
		this.bundesland=bundesland;
		this.vorwahl=vorwahl;
		id=createID();
	}
	
	@Override
	public boolean istKleinerAls(Sortierelement<Integer> s) {
		return id<s.getDaten();
	}

	@Override
	public VergleichRueckgabe suche(Integer suchbegriff, boolean genau) {
		if(id<suchbegriff) {
			return VergleichRueckgabe.KLEINER;
		}else if(id>suchbegriff) {
			return VergleichRueckgabe.GROESSER;
		}else {
			return VergleichRueckgabe.GLEICH;
		}
	}

	@Override
	public Integer getDaten() {
		return id;
	}
	
	@Override
	public String toString() {
		return ortsname+"("+bundesland+", PLZ: "+getPLZ()+", Tel.: "+getVorwahl()+")";
	}
	
	public String getPLZ() {
		if(postleitzahl==0) {
			return "";
		}
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
	
	public String getVorwahl() {
		if(vorwahl==0) {
			return "";
		}else {
			return "0"+vorwahl;
		}
	}
	
	public String getBundesland() {
		return bundesland;
	}
	
	public int getID() {
		return id;
	}
	
	protected int getPLZInt() {
		return postleitzahl;
	}
	
	protected int getVorwahlInt() {
		return vorwahl;
	}
	
	private int createID() {
		return (""+postleitzahl+vorwahl+ortsname+bundesland).hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o!=null&&o.getClass()==this.getClass()) {
			Postleitzahl plz=(Postleitzahl)o;
			boolean ortsnBool=false;
			boolean bundeslBool=false;
			if(!(ortsname==null^plz.ortsname==null)) {
				ortsnBool=ortsname.equals(plz.ortsname);
			}
			if(!(bundesland==null^plz.bundesland==null)) {
				bundeslBool=bundesland.equals(plz.bundesland);
			}
			return plz.postleitzahl==postleitzahl&&plz.vorwahl==vorwahl&&ortsnBool&&bundeslBool;
		}else {
			return false;
		}
	}
}
