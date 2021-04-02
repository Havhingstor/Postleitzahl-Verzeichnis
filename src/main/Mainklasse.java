package main;

import datenstruktur.PLZ_Verzeichnis;

public class Mainklasse {

	public static void main(String[] args) {
		PLZ_Verzeichnis b=new PLZ_Verzeichnis("D:\\Users\\Dokumente\\Schule\\11. Klasse\\Info\\Infoprojekt Postleitzahlen\\plz_de.csv");
		b.ausgeben();
		System.out.println();
		b.suchePLZ(95444);
		System.out.println(b.getHoehe());
		System.out.println(b.getAnzahl());
	}
}
