package main;

import datenstruktur.Postleitzahl;
import modell.Binaerbaum;

public class Mainklasse {

	public static void main(String[] args) {
		Binaerbaum<Postleitzahl,Integer> b=new Binaerbaum<Postleitzahl,Integer>();
		for(int i=0;i<1000;++i) {
			b.einfuegen(new Postleitzahl(i%333, ""+i));
		}
		b.sortiertAusgeben();
		System.out.println(b.suchen(120));
	}

}
