package modell;

//I ist der Integrierte Vergleichsdatentyp, S der Container für alle Daten
public class Binaerbaum<S extends Sortierelement<I>,I> {
	private Baumelement<S,I> wurzel;
    private Abschluss<S,I> abschluss;

    public Binaerbaum() {
        abschluss = new Abschluss<S,I>();
        wurzel=abschluss;
    }

    public void einfuegen( S s ) {
        wurzel=wurzel.einfuegen(s);
    }

    public S suchenOhneGrossbuchstaben( I suchschluessel ) {
        return wurzel.suche(suchschluessel,false);
    }
    
    public S suchen( I suchschluessel ) {
        return wurzel.suche(suchschluessel,true);
    }

    public void sortiertAusgeben(){
        wurzel.sortiertAusgeben();
    }

    public void printHoehe(){
        Knoten.resetOps();
        long start=System.currentTimeMillis();
        int hoehe=wurzel.getHoehe();
        long end=System.currentTimeMillis();
        System.out.println("Die H�he des Baums ist "+hoehe+".");
        System.out.println("Die gute Methode hat "+(end-start)+" Millisekunden gedauert");
        System.out.println("Operationen: "+Knoten.getOps());
    }
    
    public void printHoeheSchlecht(){
        Knoten.resetOps();
        long start=System.currentTimeMillis();
        int hoehe=wurzel.getHoeheSchlecht();
        long end=System.currentTimeMillis();
        System.out.println("Die H�he des Baums ist "+hoehe+".");
        System.out.println("Die schlechte Methode hat "+(end-start)+" Millisekunden gedauert");
        System.out.println("Operationen: "+Knoten.getOps());
    }


        
    public int zaehlen(){
      return wurzel.zaehlen();  
    }
    
    public int gebeZahlEbenen(int ebene){
        return wurzel.gebeZahlEbenen(ebene);
    }
    
    public void printZahlEbenen(int ebene){
        System.out.println("Ebene "+ebene+": "+gebeZahlEbenen(ebene));
    }
    
    public void printKnotenEbenen(int ebenen){
     wurzel.printKnotenEbenen(ebenen);   
    }
}
