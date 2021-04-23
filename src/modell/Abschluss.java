package modell;

import java.util.ArrayList;

//I ist der Integrierte Vergleichsdatentyp, S der Container für alle Daten
public class Abschluss<S extends Sortierelement<I>, I> extends Baumelement<S,I> {
	int ebenen=0;
    
	@Override
    public Knoten<S,I> einfuegen(S s){
        return new Knoten<S,I>(s,this,this);
    }

	@Override
    public ArrayList<S> suche(I suchbegriff, boolean genau){
        return new ArrayList<S>();
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
    
    public void ebenenAusgeben(){
        System.out.println("Anzahl der Ebenen: "+ebenen);
        ebenen=0;
    }

	@Override
    public int zaehlen(){
     return 0;   
    }

	@Override
	void handleInorder(SortierelementHandler<S, I> handler) {}

	@Override
	void handlePreorder(SortierelementHandler<S, I> handler) {}

	@Override
	void handlePostorder(SortierelementHandler<S, I> handler) {}

	@Override
	boolean isAbschluss() {
		return true;
	}

	@Override
	Baumelement<S, I> getNext() {
		return null;
	}
}
