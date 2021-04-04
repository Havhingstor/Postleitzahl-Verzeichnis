package modell;

import java.util.ArrayList;

//I ist der Integrierte Vergleichsdatentyp, S der Container für alle Daten
public abstract class Baumelement<S extends Sortierelement<I>, I> {
	
	abstract Knoten<S,I> einfuegen(S s);
    abstract ArrayList<S> suche(I suchbegriff, boolean genau);
    abstract int zaehlen();
    abstract int getHoehe();
    abstract void handleInorder(SortierelementHandler<S,I> handler);
    abstract void handlePreorder(SortierelementHandler<S,I> handler);
    abstract void handlePostorder(SortierelementHandler<S,I> handler);
}
    