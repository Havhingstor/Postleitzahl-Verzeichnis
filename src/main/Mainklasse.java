package main;

import datenstruktur.PLZ_Verzeichnis;

public class Mainklasse {

	public static void main(String[] args) {
		PLZ_Verzeichnis b=new PLZ_Verzeichnis("D:\\Users\\Dokumente\\Schule\\11. Klasse\\Info\\Infoprojekt Postleitzahlen\\plz_de.csv");
		b.ausgeben();
		System.out.println();
		b.vorwahlAusgeben(921);
		System.out.println();
		b.plzAusgeben(95444);
		System.out.println();
		b.ortsnameAusgeben("Bayreuth");
		System.out.println();
		System.out.println(b.getHoehe());
		System.out.println(b.getAnzahl());
	}
}
