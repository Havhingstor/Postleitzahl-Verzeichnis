package modell;

//I ist der Integrierte Vergleichsdatentyp
public interface Sortierelement<I> {
	boolean istKleinerAls(Sortierelement<I> s);
    VergleichRueckgabe suche(I suchbegriff, boolean genau);
    I getDaten();
}
