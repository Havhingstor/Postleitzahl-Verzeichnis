package modell;

//I ist der Integrierte Vergleichsdatentyp, S der Container für alle Daten
public class Abschluss<S extends Sortierelement<I>, I> extends Baumelement<S,I> {
	int ebenen=0;
    
	@Override
    public Knoten<S,I> einfuegen(S s){
        return new Knoten<S,I>(s,this,this);
    }

	@Override
    public void sortiertAusgeben(){}

	@Override
    public S suche(I suchbegriff, boolean genau){
        return null;
    }
    public void neueEbeneAusrechnen(int bisher){
        if(bisher>ebenen){
            ebenen=bisher;
        }
    }

	@Override
    public int getHoehe(){
        return 0;
    }

	@Override
    public int getHoeheSchlecht(){
        return 0;
    }
    
    public void ebenenAusgeben(){
        System.out.println("Anzahl der Ebenen: "+ebenen);
        ebenen=0;
    }

	@Override
    public int zaehlen(){
     return 0;   
    }

	@Override
    int gebeZahlEbenen(int ebenen){
        return 0;
    }

	@Override
    void printKnotenEbenen(int ebenen){
        if(ebenen==1){
            System.out.println("ABSCHLUSS");
        }
    }
}
