package modell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//I ist der Integrierte Vergleichsdatentyp, S der Container für alle Daten
public class Knoten<S extends Sortierelement<I>,I> extends Baumelement<S,I> {

	private Baumelement<S,I> linkerNachfolger, rechterNachfolger, zentralerNachfolger;
	private S daten;
    int hoeheRechts=0, hoeheLinks=0;
    boolean validRechts=false, validLinks=false;
    
    public Knoten(S s,Baumelement<S,I> links, Baumelement<S,I> rechts){
        daten=s;
        linkerNachfolger=links;
        rechterNachfolger=rechts;
        zentralerNachfolger=new Abschluss<S,I>();
    }
    
    @Override
    public Knoten<S,I> einfuegen(S s){
    	VergleichRueckgabe vergleich=s.vergleiche(daten);
        if(vergleich==VergleichRueckgabe.KLEINER){
            linkerNachfolger=linkerNachfolger.einfuegen(s).optimiere();
            hoeheLinks=linkerNachfolger.getHoehe();
        }else if(vergleich==VergleichRueckgabe.GROESSER){
            rechterNachfolger=rechterNachfolger.einfuegen(s).optimiere();
            hoeheRechts=rechterNachfolger.getHoehe();
        }else {
        	zentralerNachfolger=zentralerNachfolger.einfuegen(s);
        }
        return this;
    }
    
    @Override
    public List<S> suche(I suchbegriff, boolean genau){
        switch (daten.suche(suchbegriff,genau)) {
            case GROESSER:
                return linkerNachfolger.suche(suchbegriff,genau);
            case GLEICH:
            	List<S> returner = new ArrayList<S>(Arrays.asList(daten));
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
        if(hoeheLinks>hoeheRechts){
        	return hoeheLinks+1;   
        }else{
            return hoeheRechts+1;
        }
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
	
	public boolean isAVL() {
		int diff=hoeheRechts-hoeheLinks;
		return diff>=-1&&diff<=1;
	}
	
	@Override
	public Baumelement<S,I> rotiereRechts() {
		if(!linkerNachfolger.isAbschluss()) {
			Knoten<S,I> neuerOberknoten=(Knoten<S,I>)linkerNachfolger;
			linkerNachfolger=neuerOberknoten.getRechterNachfolger();
			hoeheLinks=linkerNachfolger.getHoehe();
			neuerOberknoten.setRechterNachfolger(this);
			return neuerOberknoten;
		}else {
			return this;
		}
	}
	
	@Override
	public Baumelement<S,I> rotiereLinks() {
		if(!rechterNachfolger.isAbschluss()) {
			Knoten<S,I> neuerOberknoten=(Knoten<S,I>)rechterNachfolger;
			rechterNachfolger=neuerOberknoten.getLinkerNachfolger();
			hoeheRechts=rechterNachfolger.getHoehe();
			neuerOberknoten.setLinkerNachfolger(this);
			return neuerOberknoten;
		}else {
			return this;
		}
	}
	
	private Baumelement<S, I> getLinkerNachfolger() {
		return linkerNachfolger;
	}

	private void setLinkerNachfolger(Baumelement<S, I> linkerNachfolger) {
		this.linkerNachfolger = linkerNachfolger;
		hoeheLinks=linkerNachfolger.getHoehe();
	}

	private Baumelement<S, I> getRechterNachfolger() {
		return rechterNachfolger;
	}

	private void setRechterNachfolger(Baumelement<S, I> rechterNachfolger) {
		this.rechterNachfolger = rechterNachfolger;
		hoeheRechts=rechterNachfolger.getHoehe();
	}

	@Override
	public Baumelement<S,I> optimiere(){
		int diff=hoeheRechts-hoeheLinks;
		if(diff>1) {
			if(!rechterNachfolger.ueberpruefe(false)) {
				rechterNachfolger=rechterNachfolger.rotiereRechts();
			}
			return rotiereLinks();
		}else if(diff<-1) {
			if(!linkerNachfolger.ueberpruefe(true)) {
				linkerNachfolger=linkerNachfolger.rotiereLinks();
			}
			return rotiereRechts();
		}
		return this;
	}

	@Override
	boolean ueberpruefe(boolean aussenseiteLinks) {
		if(aussenseiteLinks) {
			return hoeheLinks>=hoeheRechts;
		}else {
			return hoeheRechts>=hoeheLinks;
		}
	}
		
}
