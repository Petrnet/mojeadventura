/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.Petrnet.mojeadventura.logika;

import java.util.Observable;

/**
 * Class HerniPlan - třída představující mapu a stav adventury.
 * 
 * Tato třída inicializuje prvky ze kterých se hra skládá:
 * vytváří všechny lokace, propojuje je vzájemně pomocí východů 
 * a pamatuje si aktuální lokaci, ve které se hráč právě nachází.
 *
 * @author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha, Petr Netolicky
 * @version    LS 2016/2017
 */
public class HerniPlan extends Observable{
    
    private static final String NAZEV_PRO_LOKACE = "more";
    
    private boolean prohra = false; 
    
    private Lokace aktualniLokace;
    
    private Batoh batoh;
    
   
    
    /**
     * Konstruktor který vytváří jednotlivé lokace a propojuje je pomocí východů.
     */
    public HerniPlan() {
        zalozLokaceHry();
        batoh = new Batoh();

        
    }

   
    /**
     * Vytváří jednotlivé lokace a propojuje je pomocí východů.
     * Jako výchozí aktuální lokaci nastaví domeček.
     */
    private void zalozLokaceHry() {
        // vytvářejí se jednotlivé lokace
        Lokace pristav = new Lokace("pristav","pristav, ve kterém hru zacinas",0,0);
        Lokace breh = new Lokace("breh","zde se nachazi dodo",130,0);
        Lokace jeskyne = new Lokace("jeskyne","staráaplesniva jeskyne ve ktere zije raptor",130,-60);
        Lokace more = new Lokace(NAZEV_PRO_LOKACE,"more ve kterem zije masosaurus, umres, jestli vejdes",290,0);
        Lokace louka = new Lokace("louka","na louce naleznes triceratopse",130,60);
        Lokace skaly = new Lokace("skaly","ve skalach naleznes pteranodona",0,60);
        Lokace jurskyParkBudova = new Lokace("jurskyParkBudova","v budove naleznes mec a zamcenou truhlu",290,60);
        Lokace laborator = new Lokace("laborator","zde naleznes inkubator pro vytvoreni vejce Indomina Rexe",290,120);
        Lokace tRexDoupe = new Lokace("tRexovoDoupe","vstup na vlastni nebezpeci",0,120);
        
       
        // přiřazují se průchody mezi lokacemi (sousedící lokace)
        pristav.setVychod(breh);
        breh.setVychod(pristav);
        breh.setVychod(jeskyne);
        breh.setVychod(more);
        more.setVychod(breh);
        jeskyne.setVychod(breh);
        louka.setVychod(breh);
        breh.setVychod(louka);
        skaly.setVychod(louka);
        louka.setVychod(skaly);
        louka.setVychod(jurskyParkBudova);
        jurskyParkBudova.setVychod(louka);
        jurskyParkBudova.setVychod(laborator);
        laborator.setVychod(jurskyParkBudova);
        skaly.setVychod(tRexDoupe);
        tRexDoupe.setVychod(skaly);
        
       
        
       

        // vytvoříme několik věci
        Predmet mec = new Predmet("mec", "ostry mec",true,true,"/com/github/Petrnet/mojeadventura/uiText/mec.png");
        Predmet lod = new Predmet("lod", "stara lod, ktera te sem zavezla", false, true,"/com/github/Petrnet/mojeadventura/uiText/lod.jpg");
        Predmet puska = new Predmet("puska", "velmi uderna zbran", true, false,"/com/github/Petrnet/mojeadventura/uiText/puska.PNG");
        Predmet inkubator = new Predmet ("inkubator", "pristroj na vytvareni vajec", false, true,"/com/github/Petrnet/mojeadventura/uiText/ninja.jpg");
        Predmet triceratopsMaso = new Predmet("triceratopsMaso", "chutne velke maso",true,true,"/com/github/Petrnet/mojeadventura/uiText/Triceratopsmaso.jpg");
        Predmet dodoMaso = new Predmet("dodoMaso", "chutne male maso",true,true,"/com/github/Petrnet/mojeadventura/uiText/Dodomaso.jpg");
        Predmet tRexVejce = new Predmet("tRexVejce", "velke vejce",true,true,"/com/github/Petrnet/mojeadventura/uiText/Trexvejce.jpg");
        Predmet raptorVejce = new Predmet("raptorVejce", "mensi vejce",true,true,"/com/github/Petrnet/mojeadventura/uiText/raptorvejce.jpg");

   
        
        
        // umístíme věci do prostorů
        pristav.vlozPredmet(lod);
        jurskyParkBudova.vlozPredmet(mec);
        jurskyParkBudova.vlozPredmet(puska);
        laborator.vlozPredmet(inkubator);
      
        aktualniLokace = pristav;  // hra začíná v pristavu
        
        // vytvoření dinosaurů
        
        Dinosaurus dodo = new Dinosaurus("dodo", dodoMaso, mec);
        Dinosaurus triceratops = new Dinosaurus("triceratops", triceratopsMaso, mec);
        Dinosaurus tRex = new Dinosaurus("tRex", tRexVejce, puska);
        Dinosaurus raptor = new Dinosaurus("raptor");
        Dinosaurus masosaurus = new Dinosaurus ("masosaurus");
        Dinosaurus pteranodon = new Dinosaurus ("pteranodon");
        
        //umístění dinosaurů
        
        breh.vlozDinosaur(dodo);
        louka.vlozDinosaur(triceratops);
        tRexDoupe.vlozDinosaur(tRex);
        jeskyne.vlozDinosaur(raptor);
        more.vlozDinosaur(masosaurus);
        skaly.vlozDinosaur(pteranodon);
    }

    /**
     * Metoda vrací odkaz na aktuální lokaci, ve které se hráč právě nachází.
     *
     * @return    aktuální lokace
     */
    
    public Lokace getAktualniLokace() {
        return aktualniLokace;
    }
    /**
     * Metoda vrací odkaz na aktuální batoh
     *
     * @return    batoh
     */
     public Batoh getBatoh() {
        return batoh;
    }
    
    /**
     * Metoda nastaví aktuální lokaci, používá se nejčastěji při přechodu mezi lokacemi
     *
     * @param    lokace nová aktuální lokace
     */
    public void setAktualniLokace(Lokace lokace) {
       aktualniLokace = lokace;
       setChanged();
       notifyObservers();
    }
    
    /**
     * Metoda vrací informaci, zda hráč vyhrál (zda vytvořil vejce Indomina).
     * 
     * @return    true, pokud hráč vyhrál; jinak false
     */
    public boolean hracVyhral() {
        if (batoh.obsahujePredmet("vejceIndomina")){
        return true;
    }
    return false;
    }
   /**
     * Metoda vrací informaci, zda hráč dotazil do proherní lokace
     * 
     * @return    true, pokud hráč prohral; jinak false
     */
   public boolean hracProhral() {
   return aktualniLokace.getNazev().equals(NAZEV_PRO_LOKACE);
   }
   
   /**
     * Metoda vrací informaci, zda hráč prohrál (byl sežrán).
     * 
     * @return    true, pokud hráč prohral; jinak false
   */
    
   public boolean isProhra() {
   return prohra;
   }
     /**
     * Metoda která nastavuje, zda-li hráč prohrál.
     * 
     * @param   prohra, true pokud je hra prohraná; jinak false
     * 
   */
    
   public void setProhra(boolean prohra) {
        this.prohra = prohra;
   }
}
