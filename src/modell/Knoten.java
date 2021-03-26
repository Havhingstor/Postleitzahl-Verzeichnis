package modell;

import java.util.ArrayList;
import java.util.Arrays;

//I ist der Integrierte Vergleichsdatentyp, S der Container für alle Daten
public class Knoten<S extends Sortierelement<I>,I> extends Baumelement<S,I> {

	private Baumelement<S,I> linkerNachfolger, rechterNachfolger;
    private S daten;
    private static long ops;
    
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
    public void sortiertAusgeben(){
       linkerNachfolger.sortiertAusgeben();
       System.out.println(daten);
       rechterNachfolger.sortiertAusgeben();
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
        ++ops;
        int links=linkerNachfolger.getHoehe();
        int rechts=rechterNachfolger.getHoehe();
        if(links>rechts){
         return links+1;   
        }else{
            return rechts+1;
        }
    }

    @Override
    public int getHoeheSchlecht(){
        ++ops;
        if(linkerNachfolger.getHoehe()>rechterNachfolger.getHoehe()){
         return linkerNachfolger.getHoehe()+1;   
        }else{
            return rechterNachfolger.getHoehe()+1;
        }
    }

    @Override
    public int zaehlen(){
        return rechterNachfolger.zaehlen()+linkerNachfolger.zaehlen()+1;
    }
    
    public static long getOps(){
        return ops;
    }
    
    public static void resetOps(){
        ops=0;
    }

    @Override
    public int gebeZahlEbenen(int ebenen){
        if(ebenen==1){
            return 1;
        }else{
            return linkerNachfolger.gebeZahlEbenen(ebenen-1)+rechterNachfolger.gebeZahlEbenen(ebenen-1);
        }
    }

    @Override
    public void printKnotenEbenen(int ebenen){
        if(ebenen==1){
            System.out.println(daten);
        }else{
         linkerNachfolger.printKnotenEbenen(ebenen-1);
         rechterNachfolger.printKnotenEbenen(ebenen-1);
        }
    }

}
