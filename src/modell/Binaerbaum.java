package modell;

import java.util.ArrayList;

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

    public ArrayList<S> suchenOhneGrossbuchstaben( I suchschluessel ) {
        return wurzel.suche(suchschluessel,false);
    }
    
    public ArrayList<S> suchen( I suchschluessel ) {
        return wurzel.suche(suchschluessel,true);
    }
    
    public void handle(SortierelementHandler<S,I> handler) {
    	wurzel.handleInorder(handler);
    }
    
    public void handlePreorder(SortierelementHandler<S,I> handler) {
    	wurzel.handlePreorder(handler);
    }
    
    public void ausgeben(){
        wurzel.inorderAusgeben();
    }
    
    public void preorderAusgeben() {
    	wurzel.preorderAusgeben();
    }

    public int getHoehe(){
        return wurzel.getHoehe();
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
