package main;

import modell.Binaerbaum;

public class Mainklasse {

	public static void main(String[] args) {
		Binaerbaum<Testeintrag,Long> b=new Binaerbaum<Testeintrag,Long>();
		for(long i=0;i<20000;++i) {
			b.einfuegen(new Testeintrag(i));
		}
		b.sortiertAusgeben();
		System.out.println(b.suchen(120));
	}

}
