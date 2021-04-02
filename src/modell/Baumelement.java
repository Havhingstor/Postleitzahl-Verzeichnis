package modell;

import java.util.ArrayList;

//I ist der Integrierte Vergleichsdatentyp, S der Container für alle Daten
public abstract class Baumelement<S extends Sortierelement<I>, I> {
	
	abstract Knoten<S,I> einfuegen(S s);
    abstract void inorderAusgeben();
    abstract void preorderAusgeben();
    abstract ArrayList<S> suche(I suchbegriff, boolean genau);
    abstract int zaehlen();
    abstract int getHoehe();
    abstract int gebeZahlEbenen(int ebenen);
    abstract void printKnotenEbenen(int ebenen);
    abstract void handleInorder(SortierelementHandler<S,I> handler);
    abstract void handlePreorder(SortierelementHandler<S,I> handler);
}
    