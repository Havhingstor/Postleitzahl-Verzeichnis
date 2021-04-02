package datenstruktur;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

import modell.Binaerbaum;

public class PLZ_Verzeichnis {
	private Binaerbaum<Postleitzahl,Integer> plzs;
	
	public PLZ_Verzeichnis(String pfadCSV) {
		plzs=new Binaerbaum<Postleitzahl,Integer>();
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
						plzs.einfuegen(new Postleitzahl(plz, ortsname, vorwahl, bundesland));
					}
					System.out.println("Die CSV-Datei "+pfad+" wurde erfolgreich eingelesen.");
				}catch (IOException e) {}
			}
		}
	}
	
	public void suchePLZ(int suche) {
		for(Postleitzahl ergebnis: plzs.suchen(suche)) {
			System.out.println(ergebnis);
		}
	}
	
	public void ausgeben() {
		plzs.ausgeben();
	}
	
	public void ausgebenCSV() {
		plzs.postorderAusgeben();
	}
	
	public int getHoehe() {
		return plzs.getHoehe();
	}
	
	public void postleitzahlEintragen(Postleitzahl plz) {
		plzs.einfuegen(plz);
	}
}
