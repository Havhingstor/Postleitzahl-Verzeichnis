package modell;

import java.util.List;

//I ist der Integrierte Vergleichsdatentyp, S der Container für alle Daten
public abstract class Baumelement<S extends Sortierelement<I>, I> {
	
	abstract Knoten<S,I> einfuegen(S s);
    abstract List<S> suche(I suchbegriff, boolean genau);
    abstract int zaehlen();
    abstract int getHoehe();
    abstract void handleInorder(SortierelementHandler<S,I> handler);
    abstract void handlePreorder(SortierelementHandler<S,I> handler);
    abstract void handlePostorder(SortierelementHandler<S,I> handler);
    abstract boolean isAbschluss();
    abstract Baumelement<S,I> getNext();
    abstract Baumelement<S,I> optimiere();
    abstract Baumelement<S,I> rotiereRechts();
    abstract Baumelement<S,I> rotiereLinks();
    abstract boolean ueberpruefe(boolean aussenseiteLinks);
}
    
