package com.github.Petrnet.mojeadventura.logika;

import java.util.Observable;

/**
 * Třída PrikazVezmi představuje příkaz pro sebrání předmětu z aktuální lokace
 * a jeho vložení do batohu (inventáře) postavy.
 * 
 * @author     Jan Riha, Petr Netolicky
 * @version    LS 2016/2017
 */
public class PrikazVezmi extends Observable implements IPrikaz
{
    private static final String NAZEV = "vezmi";
    private HerniPlan hPlan;
    private boolean odemceny;
    
     /**
    * Konstruktor třídy
    *
    * @param    plan herní plán, ve kterém se bude ve hře "chodit" 
    * @param    batoh batoh, do kterého se ukládají předměty
    */    
    
    public PrikazVezmi(HerniPlan hPlan)
    {
        this.hPlan = hPlan;
      
    }
    
    /**
     * Metoda představuje zpracování příkazu pro sebrání předmětu.
     * Nejprve zkontroluje, zda byl zadán právě jeden název jako
     * parametr, ověří, zda v aktuální lokaci je předmět s tímto
     * názvem, zda je přenositelný, zda je v batohu místo a
     * následně předmět odebere z lokace a vloží ho do batohu.
     * 
     * @param parametry pole parametrů zadaných na příkazové řádce
     * @return výsledek zpracování, tj. text, který se vypíše na konzoli
     */
    public String proved(String... parametry)
    {        
        if (parametry.length < 1)
        {
            return "Nevim, co mam sebrat";
        }
        
        if (parametry.length > 1)
        {
            return "Tomu nerozumim, nedokazu sebrat vice veci najednou";
        }
        
        String nazevPredmetu = parametry[0];
        Lokace aktLokace = hPlan.getAktualniLokace();
        Batoh batoh = hPlan.getBatoh();
        
        if (!aktLokace.obsahujePredmet(nazevPredmetu))
        {
            return "Predmet " + nazevPredmetu + " tady neni";
        }
  
        Predmet predmet = aktLokace.vezmiPredmet(nazevPredmetu);
        if (!predmet.isPrenositelny())
        {
            aktLokace.vlozPredmet(predmet);
            return "Predmet " + nazevPredmetu + " fakt neuneses";
        }
        
        if (!predmet.isOdemceny() && !batoh.nazvyPredmetu().contains("klic"))
        {
            aktLokace.vlozPredmet(predmet);
            return "Predmet " + nazevPredmetu + " je zamceny mohutnymi retezy, potrebujes klic, aby jsi ho mohl vzit";
        }
        
        if (!batoh.jeVolno())
        {
             aktLokace.vlozPredmet(predmet);
             return "V batohu uz nemas volne misto, musis neco zahodit";
        }
         predmet.setOdemceny(odemceny);
         batoh.vlozPredmet(predmet);
         setChanged();
         notifyObservers();

        return "Sebral(a) jsi predmet " + nazevPredmetu;
    }
    
    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání).
     *
     * @return    název příkazu
     */
	public String getNazev()
	{
	    return NAZEV;
	}
    
}
