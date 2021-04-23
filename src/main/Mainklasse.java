package main;

import datenstruktur.PLZ_Verzeichnis;

public class Mainklasse {
	
	public static void main(String[] args) {
		PLZ_Verzeichnis b=new PLZ_Verzeichnis(
				"D:\\Users\\Dokumente\\Schule\\11. Klasse\\Info\\Infoprojekt Postleitzahlen\\plz_de.csv");
		b.ausgeben();
		System.out.println();
		b.vorwahlAusgeben(921);
		System.out.println();
		b.plzAusgeben(95444);
		System.out.println();
		b.ortsnameAusgeben("Bayreuth");
		System.out.println();
//		b.bundeslaenderAusgeben("Bayern");
//		System.out.println();
		System.out.println(b.getHoehe());
		System.out.println(b.getAnzahl());
//		Binaerbaum<PlzZuID,Integer> b=new Binaerbaum<PlzZuID,Integer>();
//		for(int i=0;i<5000;++i) {
//			if(i%500==0) {
//				System.out.print("");
//			}
//			b.einfuegen(new PlzZuID(new Postleitzahl(i%500, null, null, i, null)));
//			System.out.println("Eingefügt: "+i+" ("+i%500+")");
//		}
//		b.handlePreorder((d)->{
//			System.out.println(d.getID()+" ("+d.getDaten()+")");
//		});
//		System.out.println(b.getHoehe());
	}
}
