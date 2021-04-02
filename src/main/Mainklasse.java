package main;

import datenstruktur.PLZ_Verzeichnis;

public class Mainklasse {

	public static void main(String[] args) {
		PLZ_Verzeichnis b=new PLZ_Verzeichnis("C:\\Users\\pasch\\Desktop\\plz_kurz.csv");
		b.ausgeben();
		System.out.println();
		b.suchePLZ(95444);
	}
}
