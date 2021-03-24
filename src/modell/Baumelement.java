package modell;

//I ist der Integrierte Vergleichsdatentyp, S der Container für alle Daten
public abstract class Baumelement<S extends Sortierelement<I>, I> {
	
	abstract Knoten<S,I> einfuegen(S s);
    abstract void sortiertAusgeben();
    abstract S suche(I suchbegriff, boolean genau);
    abstract int zaehlen();
    abstract int getHoehe();
    abstract int getHoeheSchlecht();
    abstract int gebeZahlEbenen(int ebenen);
    abstract void printKnotenEbenen(int ebenen);	
}
