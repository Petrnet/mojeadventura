package com.github.Petrnet.mojeadventura.logika;

import java.util.Observable;

/**
 * Třída PrikazPoloz představuje příkaz pro odebrání předmětu z batohu
 * a jeho vložení do aktuální lokace.
 * 
 * @author     Petr Netolicky
 * @version    LS 2016/2017
 */


public class PrikazPoloz extends Observable implements IPrikaz
{
    private static final String NAZEV = "poloz";
    private HerniPlan hPlan;
    
     /**
    * Konstruktor třídy
    *
    * @param    plan herní plán, ve kterém se bude ve hře "chodit" 
    * @param    batoh batoh, do kterého se ukládají předměty
    */    
   
    public PrikazPoloz(HerniPlan hPlan)
    {
        this.hPlan = hPlan;
    }
    
    /**
     * Metoda představuje zpracování příkazu pro odložení předmětu.
     * Nejprve zkontroluje, zda byl zadán právě jeden název jako
     * parametr. Ověří, že batoh není prázdný a zda-li je
     * v batohu předmět s tímto názvem.
     * Následně předmět odebere z batohu a vloží ho do lokace.
     * 
     * @param parametry pole parametrů zadaných na příkazové řádce, předmět
     * @return výsledek zpracování, tj. text, který se vypíše na konzoli
     */
    public String proved(String... parametry)
    {        
        if (parametry.length < 1)
        {
            return "Nevim, co mam polozit";
        }
        
        if (parametry.length > 1)
        {
            return "Tomu nerozumim, nedokazu sebrat vice veci najednou";
        }
        
        
        String nazevPredmetu = parametry[0];
        Lokace aktLokace = hPlan.getAktualniLokace();
        Batoh batoh = hPlan.getBatoh();
        
        if (batoh.jePrazdny())
         {
             return "V batohu nic nemas, nic nemuzes zahodit";
         }
         
         if (!batoh.obsahujePredmet(nazevPredmetu))
         {
             return "V batohu nemas tenhle predmet";
         }
         Predmet predmet = batoh.vyhodPredmet(nazevPredmetu);
         aktLokace.vlozPredmet(predmet);
         setChanged();
         notifyObservers();
           
         return "Vyhodil si " + nazevPredmetu;

        
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

