package modell;

import java.util.ArrayList;
import java.util.Arrays;

//I ist der Integrierte Vergleichsdatentyp, S der Container für alle Daten
public class Knoten<S extends Sortierelement<I>,I> extends Baumelement<S,I> {

	private Baumelement<S,I> linkerNachfolger, rechterNachfolger, zentralerNachfolger;
	private S daten;
    private int hoehe;
    private boolean hoeheValid;
    
    public Knoten(S s,Baumelement<S,I> links, Baumelement<S,I> rechts){
        daten=s;
        linkerNachfolger=links;
        rechterNachfolger=rechts;
        zentralerNachfolger=new Abschluss<S,I>();
        hoehe=0;
        hoeheValid=false;
    }
    
    @Override
    public Knoten<S,I> einfuegen(S s){
    	VergleichRueckgabe vergleich=s.vergleiche(daten);
        if(vergleich==VergleichRueckgabe.KLEINER){
            linkerNachfolger=linkerNachfolger.einfuegen(s);
            hoeheValid=false;
        }else if(vergleich==VergleichRueckgabe.GROESSER){
            rechterNachfolger=rechterNachfolger.einfuegen(s);
            hoeheValid=false;
        }else {
        	zentralerNachfolger=zentralerNachfolger.einfuegen(s);
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
            	Baumelement<S,I> nachfolger=zentralerNachfolger;
            	while(!nachfolger.isAbschluss()) {
            		returner.add(((Knoten<S,I>)nachfolger).getDaten());
            		nachfolger=nachfolger.getNext();
            	}
                return returner;
            default:
                return rechterNachfolger.suche(suchbegriff,genau);
        }
    }

    @Override
    public int getHoehe(){
    	if(!hoeheValid) {
	        int rechts=rechterNachfolger.getHoehe();
	        int links=linkerNachfolger.getHoehe();
	        if(links>rechts){
	        	hoehe=links+1;   
	        }else{
	            hoehe=rechts+1;
	        }
	        hoeheValid=true;
    	}
    	return hoehe;
    }

    @Override
    public int zaehlen(){
        return rechterNachfolger.zaehlen()+linkerNachfolger.zaehlen()+zentralerNachfolger.zaehlen()+1;
    }

	@Override
	public void handleInorder(SortierelementHandler<S, I> handler) {
		linkerNachfolger.handleInorder(handler);
		handler.handle(daten);
		zentralerNachfolger.handleInorder(handler);
		rechterNachfolger.handleInorder(handler);
	}

	@Override
	public void handlePreorder(SortierelementHandler<S, I> handler) {
		handler.handle(daten);
		zentralerNachfolger.handlePreorder(handler);
		linkerNachfolger.handlePreorder(handler);
		rechterNachfolger.handlePreorder(handler);
	}

	@Override
	public void handlePostorder(SortierelementHandler<S, I> handler) {
		linkerNachfolger.handlePostorder(handler);
		rechterNachfolger.handlePostorder(handler);
		zentralerNachfolger.handlePostorder(handler);
		handler.handle(daten);
	}
	
	public S getDaten() {
		return daten;
	}


	@Override
	boolean isAbschluss() {
		return false;
	}

	@Override
	Baumelement<S, I> getNext() {
		return zentralerNachfolger;
	}
	
}
