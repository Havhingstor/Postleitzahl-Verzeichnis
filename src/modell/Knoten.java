package modell;

import java.util.ArrayList;
import java.util.Arrays;

//I ist der Integrierte Vergleichsdatentyp, S der Container für alle Daten
public class Knoten<S extends Sortierelement<I>,I> extends Baumelement<S,I> {

	private Baumelement<S,I> linkerNachfolger, rechterNachfolger;
    private S daten;
    private int hoehe;
    private boolean hoeheValid;
    
    public Knoten(S s,Baumelement<S,I> links, Baumelement<S,I> rechts){
        daten=s;
        linkerNachfolger=links;
        rechterNachfolger=rechts;
        hoehe=0;
        hoeheValid=false;
    }
    
    @Override
    public Knoten<S,I> einfuegen(S s){
        if(s.istKleinerAls(daten)){
            linkerNachfolger=linkerNachfolger.einfuegen(s);
        }else{
            rechterNachfolger=rechterNachfolger.einfuegen(s);
        }
        hoeheValid=false;
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
    	if(!hoeheValid) {
	        int links=linkerNachfolger.getHoehe();
	        int rechts=rechterNachfolger.getHoehe();
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
        return rechterNachfolger.zaehlen()+linkerNachfolger.zaehlen()+1;
    }

	@Override
	public void handleInorder(SortierelementHandler<S, I> handler) {
		linkerNachfolger.handleInorder(handler);
		System.out.println("Links: "+linkerNachfolger.getHoehe()+" Rechts: "+rechterNachfolger.getHoehe());
		handler.handle(daten);
		rechterNachfolger.handleInorder(handler);
	}

	@Override
	public void handlePreorder(SortierelementHandler<S, I> handler) {
		System.out.println("Links: "+linkerNachfolger.getHoehe()+" Rechts: "+rechterNachfolger.getHoehe());
		handler.handle(daten);
		linkerNachfolger.handlePreorder(handler);
		rechterNachfolger.handlePreorder(handler);
	}

	@Override
	public void handlePostorder(SortierelementHandler<S, I> handler) {
		linkerNachfolger.handlePostorder(handler);
		rechterNachfolger.handlePostorder(handler);
		System.out.println("Links: "+linkerNachfolger.getHoehe()+" Rechts: "+rechterNachfolger.getHoehe());
		handler.handle(daten);
	}
	
	@Override
	public Baumelement<S,I> rotiere(boolean rechts){
		if(rechts) {
			Baumelement<S,I> anschluss=linkerNachfolger;
			linkerNachfolger=anschluss.getAnschluss(this);
			if(linkerNachfolger==null) {
				linkerNachfolger=anschluss;
				return this;
			}else {
				return anschluss;
			}
		}else {
			Baumelement<S,I> anschluss=rechterNachfolger;
			rechterNachfolger=anschluss.getAnschluss(this);
			if(rechterNachfolger==null) {
				rechterNachfolger=anschluss;
				return this;
			}else {
				return anschluss;
			}
		}
	}
	
	public S getDaten() {
		return daten;
	}
	
	@Override
	public Baumelement<S, I> getAnschluss(Knoten<S,I> referenz) {
		if(referenz.getDaten().istKleinerAls(daten)) {
			Baumelement<S,I> anschluss=linkerNachfolger;
			linkerNachfolger=referenz;
			return anschluss;
		}else {
			Baumelement<S,I> anschluss=rechterNachfolger;
			rechterNachfolger=referenz;
			return anschluss;
		}
	}

	@Override
	Baumelement<S, I> optimiere() {
		linkerNachfolger=linkerNachfolger.optimiere();
		rechterNachfolger=rechterNachfolger.optimiere();
		int hoeheLinks=linkerNachfolger.getHoehe(), hoeheRechts=rechterNachfolger.getHoehe(), diff=hoeheRechts-hoeheLinks;
		if(diff>1) {
			rechterNachfolger=rechterNachfolger.ueberpruefe(hoeheLinks,false);
			hoeheValid=false;
			return rotiere(false);
		}else if(diff<-1) {
			linkerNachfolger=linkerNachfolger.ueberpruefe(hoeheRechts, true);
			hoeheValid=false;
			return rotiere(true);
		}else {
			return this;
		}
	}

	/**
	 * @param links true, wenn die äußere Seite links ist
	 */
	@Override
	Baumelement<S, I> ueberpruefe(int referenzhoehe, boolean links) {
		int meineHoehe;
		if(links) {
			meineHoehe=linkerNachfolger.getHoehe();
		}else {
			meineHoehe=rechterNachfolger.getHoehe();
		}
		if(meineHoehe==referenzhoehe+1) {
			return this;
		}else if(meineHoehe==referenzhoehe) {
			return rotiere(!links);
		}else {
			System.out.println("Fail");
			return this;
		}
	}
	
}
