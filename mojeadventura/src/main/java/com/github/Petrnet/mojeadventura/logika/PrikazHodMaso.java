package com.github.Petrnet.mojeadventura.logika;

import java.util.Observable;

/**
 * Třída PrikazHodMaso představuje příkaz pro hozeni masa Dinosaurovi
 * a jeho odstraneni z inventare.
 * 
 * Tato třída je součástí jednoduché hry. 
 * 
 * @author     Jan Riha, Petr Netolicky
 * @version    LS 2016/2017
 */
public class PrikazHodMaso extends Observable implements IPrikaz
{
    private static final String NAZEV = "hodMaso";
    private HerniPlan hPlan;
    Predmet klic = new Predmet("klic", "tento klic dokaze neco otevrit",true, true,"/com/github/Petrnet/mojeadventura/uiText/klic.jpg");
    Predmet raptorVejce = new Predmet("raptorVejce", "mensi vejce", true, true, "/com/github/Petrnet/mojeadventura/uiText/raptorvejce.jpg");
    Predmet triceratopsMaso = new Predmet("triceratopsMaso", "chutne velke maso",true, true,"/com/github/Petrnet/mojeadventura/uiText/Triceratopsmaso.jpg");
    Predmet dodoMaso = new Predmet("dodoMaso", "chutne male maso",true, true,"/com/github/Petrnet/mojeadventura/uiText/Dodomaso.jpg");
  
     /**
    * Konstruktor třídy
    *
    * @param    plan herní plán, ve kterém se bude ve hře "chodit" 
    * @param    batoh batoh, do kterého se ukládají předměty
    */    
    
    public PrikazHodMaso(HerniPlan hPlan)
    {
        this.hPlan = hPlan;
        
    }
    
    /**
     * Metoda představuje zpracování příkazu pro hození masa dinosaurovi.
     * Nejprve zkontroluje, zda byl zadán právě jeden název jako
     * parametr, ověří, zda v aktuální lokaci je dinosaurus s tímto
     * názvem. Dále je ověřeno zda-li batoh obsahuje maso.
     * Pokud je maso hozeno raptorovi, hráč dostane potřebné předměty.
     * Pokud je maso hozeno jinému dinosaurovi, vrátí se nazevDinosaura a
     * "nemuze od Tebe dostat maso"
     * @param parametry pole parametrů zadaných na příkazové řádce
     * @return výsledek zpracování, tj. text, který se vypíše na konzoli
     */
    public String proved(String... parametry)
    {     
        
        if (parametry.length == 0 || parametry.length > 1)
        { 
            return "Co mam hodit?";
        }
        
         String nazevDinosaura = parametry[0];
         Lokace aktLokace = hPlan.getAktualniLokace();
         Batoh batoh = hPlan.getBatoh();
       
         
         if (!aktLokace.obsahujeDinosaura(nazevDinosaura))
        {
            return "Dinosaurus " + nazevDinosaura + " tady neni";
        }
        
        if (!batoh.nazvyPredmetu().contains("dodoMaso") && !batoh.nazvyPredmetu().contains("triceratopsMaso"))   
            {
         return "Nemuzes hodit, protoze nemas maso";
         }
         
        if (aktLokace.getNazev().equals("jeskyne")&& batoh.nazvyPredmetu().contains("dodoMaso"))
        { 
             batoh.vyhodPredmet("dodoMaso");
             aktLokace.vlozPredmet(klic);
             aktLokace.vlozPredmet(raptorVejce);
             
             setChanged();
             notifyObservers();
             return "Hodil jsi maso raptorovi, nyni muzes sebrat klic a raptorVejce";
          
            }
  
         if (aktLokace.getNazev().equals("jeskyne")&& batoh.nazvyPredmetu().contains("triceratopsMaso"))
        { 
             batoh.vyhodPredmet("triceratopsMaso");
             aktLokace.vlozPredmet(klic);
             aktLokace.vlozPredmet(raptorVejce);
             setChanged();
             notifyObservers();
             
             return "Hodil jsi maso raptorovi, nyni muzes sebrat klic a raptorVejce";
            }
        
        return nazevDinosaura + ", nemuze od Tebe dostat maso";
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