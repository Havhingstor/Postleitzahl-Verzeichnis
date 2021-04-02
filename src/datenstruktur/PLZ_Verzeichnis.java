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
	
	public PLZ_Verzeichnis(String pfadCSV) {
		ids=new Binaerbaum<Postleitzahl,Integer>();
		plzs=new Binaerbaum<PlzZuID,Integer>();
		ladeAusCSV(pfadCSV);
	}
	
	public void ladeAusCSV(String pfad) {
		if(pfad!=null&&Pattern.matches(".*\\.[Cc][Ss][Vv]", pfad)) {
			File f=new File(pfad);
			if(f.exists()&&!f.isDirectory()) {
				try(BufferedReader br=new BufferedReader(new FileReader(pfad))){
					String line="";
					while((line=br.readLine())!=null) {
						String[] infos=line.split(";");
						int plz=0;
						String ortsname="";
						String bundesland="";
						int vorwahl=0;
						try {
							plz=Integer.parseInt(infos[2]);
						}catch(Exception e) {}
						try {
							vorwahl=Integer.parseInt(infos[3]);
						}catch(Exception e) {}
						try {
							ortsname=infos[0];
							if(infos[1].length()>0) {
								ortsname+=" "+infos[1];
							}
						}catch(Exception e) {}
						try {
							bundesland=infos[4];
						}catch(Exception e) {}
						if(plz!=0) {
							Postleitzahl newPLZ=new Postleitzahl(plz, ortsname, vorwahl, bundesland);
							ids.einfuegen(newPLZ);
							plzs.einfuegen(new PlzZuID(newPLZ));
						}
					}
					System.out.println("Die CSV-Datei "+pfad+" wurde erfolgreich eingelesen.");
				}catch (IOException e) {}
			}
		}
	}
	
	public void suchePLZ(int suche) {
		for(PlzZuID ergebnis: plzs.suchen(suche)) {
			System.out.println(ids.suchen(ergebnis.getID()).get(0));
		}
	}
	
	/*public void sucheVorwahl(int suche) {
		for(PlzZuID ergebnis: plzs.suchen(suche)) {
			System.out.println(ids.suchen(ergebnis.getID()).get(0));
		}
	}*/
	
	public void ausgeben() {
		plzs.handle((data)->{
			System.out.println(ids.suchen(data.getID()).get(0));
		});
	}
	
	public void ausgebenCSV() {
		plzs.handlePreorder((data)->{
		System.out.println(ids.suchen(data.getID()).get(0));
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
	}
}
