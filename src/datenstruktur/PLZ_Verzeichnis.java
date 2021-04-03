package datenstruktur;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

import modell.Binaerbaum;

public class PLZ_Verzeichnis {
	private Binaerbaum<Postleitzahl,Integer> ids;
	private Binaerbaum<PlzZuID,Integer> plzs;
	private Binaerbaum<VorwahlZuID, Integer> vorwahlen;
	private Binaerbaum<OrtsnameZuID, String> ortsnamen;
	private Binaerbaum<BundeslandZuID, String> bundeslaender;
	
	public PLZ_Verzeichnis(String pfadCSV) {
		ids=new Binaerbaum<Postleitzahl,Integer>();
		plzs=new Binaerbaum<PlzZuID,Integer>();
		vorwahlen=new Binaerbaum<VorwahlZuID, Integer>();
		ortsnamen=new Binaerbaum<OrtsnameZuID, String>();
		bundeslaender=new Binaerbaum<BundeslandZuID, String>();
		ladeAusCSV(pfadCSV);
	}
	
	public void ladeAusCSV(String pfad) {
		if(pfad!=null&&Pattern.matches(".*\\.[Cc][Ss][Vv]", pfad)) {
			File f=new File(pfad);
			if(f.exists()&&!f.isDirectory()) {
				try(BufferedReader br=new BufferedReader(new FileReader(pfad))){
					String line="";
					Postleitzahl letztePLZ=null;
					while((line=br.readLine())!=null) {
						String[] infos=line.split(";");
						int plz=0;
						String ortsname="";
						String bundesland="";
						int vorwahl=0;
						String zusatz="";
						try {
							plz=Integer.parseInt(infos[2]);
						}catch(Exception e) {}
						try {
							vorwahl=Integer.parseInt(infos[3]);
						}catch(Exception e) {}
						try {
							ortsname=infos[0];
						}catch(Exception e) {}
						try {
							zusatz=infos[1];
						}catch(Exception e) {}
						try {
							bundesland=infos[4];
						}catch(Exception e) {}
						if(plz!=0) {
							Postleitzahl newPLZ=new Postleitzahl(plz, ortsname, zusatz, vorwahl, bundesland);
							if(!newPLZ.equals(letztePLZ)) {
								ids.einfuegen(newPLZ);
								plzs.einfuegen(new PlzZuID(newPLZ));
								vorwahlen.einfuegen(new VorwahlZuID(newPLZ));
								ortsnamen.einfuegen(new OrtsnameZuID(newPLZ));
								bundeslaender.einfuegen(new BundeslandZuID(newPLZ));
								letztePLZ=newPLZ;
							}
						}
					}
					System.out.println("Die CSV-Datei "+pfad+" wurde erfolgreich eingelesen.");
				}catch (IOException e) {}
			}
		}
	}
	
	public void plzAusgeben(int suche) {
		for(PlzZuID ergebnis: plzs.suchen(suche)) {
			System.out.println(ids.suchen(ergebnis.getID()).get(0));
		}
	}
	
	public Postleitzahl suchePLZ(int suche) {
		for(PlzZuID ergebnis: plzs.suchen(suche)) {
			return ids.suchen(ergebnis.getID()).get(0);
		}
		return null;
	}
	
	public void vorwahlAusgeben(int suche) {
		for(VorwahlZuID ergebnis: vorwahlen.suchen(suche)) {
			System.out.println(ids.suchen(ergebnis.getID()).get(0));
		}
	}
	
	public void ortsnameAusgeben(String suche) {
		for(OrtsnameZuID ergebnis: ortsnamen.suchen(suche)) {
			System.out.println(ids.suchen(ergebnis.getID()).get(0));
		}
	}
	
	public void bundeslaenderAusgeben(String suche) {
		for(BundeslandZuID ergebnis: bundeslaender.suchen(suche)) {
			System.out.println(ids.suchen(ergebnis.getID()).get(0));
		}
	}
	
	public void ausgeben() {
		plzs.handle((data)->{
			System.out.println(ids.suchen(data.getID()).get(0));
		});
	}
	
	public void ausgebenCSV() {
		plzs.handlePreorder((data)->{
			System.out.println(ids.suchen(data.getID()).get(0).getCSV());
		});
	}
	
	public int getHoehe() {
		return plzs.getHoehe();
	}
	
	public int getAnzahl() {
		return plzs.zaehlen();
	}
	
	public void postleitzahlEintragen(Postleitzahl plz) {
		ids.einfuegen(plz);
		plzs.einfuegen(new PlzZuID(plz));
		vorwahlen.einfuegen(new VorwahlZuID(plz));
		ortsnamen.einfuegen(new OrtsnameZuID(plz));
		bundeslaender.einfuegen(new BundeslandZuID(plz));
	}
}
