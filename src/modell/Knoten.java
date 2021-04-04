package modell;

import java.util.ArrayList;
import java.util.Arrays;

//I ist der Integrierte Vergleichsdatentyp, S der Container für alle Daten
public class Knoten<S extends Sortierelement<I>,I> extends Baumelement<S,I> {

	private Baumelement<S,I> linkerNachfolger, rechterNachfolger;
    private S daten;
    
    public Knoten(S s,Baumelement<S,I> links, Baumelement<S,I> rechts){
        daten=s;
        linkerNachfolger=links;
        rechterNachfolger=rechts;
    }
    
    @Override
    public Knoten<S,I> einfuegen(S s){
        if(s.istKleinerAls(daten)){
            linkerNachfolger=linkerNachfolger.einfuegen(s);
        }else{
            rechterNachfolger=rechterNachfolger.einfuegen(s);
        }
        return this;
    }
    
    @Override
    public ArrayList<S> suche(I suchbegriff, boolean genau){
        switch (daten.suche(suchbegriff,genau)) {
            case GROESSER:
                return linkerNachfolger.suche(suchbegriff,genau);
            case GLEICH:
            	ArrayList<S> returner = new ArrayList<S>(Arrays.asList(daten));
            	returner.addAll(linkerNachfolger.suche(suchbegriff, genau));
            	returner.addAll(rechterNachfolger.suche(suchbegriff, genau));
                return returner;
            default:
                return rechterNachfolger.suche(suchbegriff,genau);
        }
    }

    @Override
    public int getHoehe(){
        int links=linkerNachfolger.getHoehe();
        int rechts=rechterNachfolger.getHoehe();
        if(links>rechts){
         return links+1;   
        }else{
            return rechts+1;
        }
    }

    @Override
    public int zaehlen(){
        return rechterNachfolger.zaehlen()+linkerNachfolger.zaehlen()+1;
    }

	@Override
	void handleInorder(SortierelementHandler<S, I> handler) {
		linkerNachfolger.handleInorder(handler);
		handler.handle(daten);
		rechterNachfolger.handleInorder(handler);
	}

	@Override
	void handlePreorder(SortierelementHandler<S, I> handler) {
		handler.handle(daten);
		linkerNachfolger.handlePreorder(handler);
		rechterNachfolger.handlePreorder(handler);
	}

	@Override
	void handlePostorder(SortierelementHandler<S, I> handler) {
		linkerNachfolger.handlePostorder(handler);
		rechterNachfolger.handlePostorder(handler);
		handler.handle(daten);
	}

}
