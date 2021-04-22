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
    abstract Baumelement<S,I> rotiere(boolean rechts);
    abstract Baumelement<S,I> getAnschluss(Knoten<S,I> referenz);
    abstract Baumelement<S,I> optimiere();
    abstract Baumelement<S,I> ueberpruefe(int referenzhoehe, boolean links);
}
    