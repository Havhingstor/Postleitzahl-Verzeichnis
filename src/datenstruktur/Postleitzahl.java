package datenstruktur;

import modell.Sortierelement;
import modell.VergleichRueckgabe;

public class Postleitzahl implements Sortierelement<Integer> {
	private int id;
	private int postleitzahl;
	private String ortsname;
	private int vorwahl;
	private String bundesland;
	private String zusatz;
	
	public Postleitzahl(int postleitzahl, String ortsname, String zusatz, int vorwahl, String bundesland) {
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
		this.zusatz=zusatz;
		id=createID();
	}
	
	@Override
	public VergleichRueckgabe vergleiche(Sortierelement<Integer> s) {
		if(id<s.getDaten()) {
			return VergleichRueckgabe.KLEINER;
		}else if(id>s.getDaten()) {
			return VergleichRueckgabe.GROESSER;
		}else {
			return VergleichRueckgabe.GLEICH;
		}
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
		return getOrtMitZusatz()+" ("+bundesland+", PLZ: "+getPLZ()+", Tel.: "+getVorwahl()+")";
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
	
	public String getOrtMitZusatz() {
		if(zusatz!=null&&zusatz!="") {
			return ortsname+" "+zusatz;
		}else {
			return ortsname;
		}
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
			boolean zusatzBool=false;
			if(ortsname!=null&&plz.ortsname!=null) {
				ortsnBool=ortsname.equals(plz.ortsname);
			}else if(ortsname==null&&plz.ortsname==null) {
				ortsnBool=true;
			}
			if(bundesland!=null&&plz.bundesland!=null) {
				bundeslBool=bundesland.equals(plz.bundesland);
			}else if(bundesland==null&&plz.bundesland==null) {
				bundeslBool=true;
			}
			if(zusatz!=null&&plz.zusatz!=null) {
				zusatzBool=zusatz.equals(plz.zusatz);
			}else if(zusatz==null&&plz.zusatz==null) {
				zusatzBool=true;
			}
			return plz.postleitzahl==postleitzahl&&plz.vorwahl==vorwahl&&ortsnBool&&bundeslBool&&zusatzBool;
		}else {
			return false;
		}
	}
	
	public String getCSV() {
		return ortsname+";"+zusatz+";"+getPLZ()+";"+getVorwahl()+";"+bundesland;
	}
}
