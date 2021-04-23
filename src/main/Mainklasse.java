package main;

import datenstruktur.PLZ_Verzeichnis;

public class Mainklasse {
	
	public static void main(String[] args) {
		PLZ_Verzeichnis b=new PLZ_Verzeichnis(
				"D:\\Users\\Dokumente\\Schule\\11. Klasse\\Info\\Infoprojekt Postleitzahlen\\plz_de.csv");
		b.ausgeben();
		System.out.println();
		b.vorwahlSucheAusgeben(921);
		System.out.println();
		b.plzSucheAusgeben(95444);
		System.out.println();
		b.ortsnameAusgeben("Bayreuth");
		System.out.println();
//		b.bundeslaenderAusgeben("Bayern");
//		System.out.println();
//		System.out.println(b.getHoehe());
		System.out.println(b.getAnzahl());
		b.printHoehen();
//		Binaerbaum<Testeintrag,Integer> b=new Binaerbaum<Testeintrag,Integer>();
//		for(int i=0;i<10000000;++i) {
//			b.einfuegen(new Testeintrag(i));
//			System.out.println("Eingefügt: "+i);
//		}
////		b.handle((d)->{
////			System.out.println(d);
////		});
//		System.out.println(b.getHoehe());
	}
}
