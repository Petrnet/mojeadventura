package com.github.Petrnet.mojeadventura.logika;
/**
 * Třída PrikazVytvor implementuje pro hru příkaz vytvor.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author     Petr Netolický
 * @version    LS 2016/2017
 */



public class PrikazVytvor implements IPrikaz
{
    private static final String NAZEV = "vytvor";
    private HerniPlan hPlan;
    Predmet vejceIndomina = new Predmet("vejceIndomina", "obrovske vejce");
  
     /**
    * Konstruktor třídy
    *
    * @param    plan herní plán, ve kterém se bude ve hře "chodit" 
    * @param    batoh batoh, do kterého se ukládají předměty
    */    
    
    public PrikazVytvor(HerniPlan hPlan)
    {
        this.hPlan = hPlan;
    }
    
    /**
     * Metoda představuje zpracování příkazu pro vytvoreni vejce.
     * Nejprve zkontroluje, zda je příkaz zadán bez parametru.
     * Pak ověří, že se hráč nachází v lokaci laborator.
     * Pokud hráč má obě dvě vejce v batohu, vytvoří se vejce vejceIndomina
     * a hráč vyhrál. Pokud hráč má pouze jedno vejce v batohu, vypíše se jaké
     * vejce je potřeba sehnat.
     * 
     * @param parametry pole parametrů zadaných na příkazové řádce, bez parametru
     * @return výsledek zpracování, tj. text, který se vypíše na konzoli
     */
    public String proved(String... parametry)
    {     
        
        if (parametry.length > 0)
        { 
            return "Prikaz je bez parametru, nemuzes ho takhle pouzit, pouzij pouze vytvor";
        }
        
         
         Lokace aktLokace = hPlan.getAktualniLokace();
         Batoh batoh = hPlan.getBatoh();
         
     if (!aktLokace.getNazev().equals("laborator"))
     {
         return "Zde nemuzes prikaz pouzit";
     }
        
        
        if (batoh.nazvyPredmetu().contains("raptorVejce")&& batoh.nazvyPredmetu().contains ("tRexVejce"))
    {
            if (!batoh.jeVolno())
         {
             aktLokace.vlozPredmet(vejceIndomina);
             return "V batohu uz nemas volne misto, musis neco zahodit";
         }
        
         batoh.vlozPredmet(vejceIndomina);
         return "Sebral(a) jsi predmet " + vejceIndomina;
    }
     
    if (batoh.nazvyPredmetu().contains("raptorVejce"))
    {
        return "Potřebuješ sehnat ještě tRexVejce";
    }
    
    if (batoh.nazvyPredmetu().contains("tRexVejce"))
    {
        return "Potřebuješ sehnat ještě raptorVejce";
    }
       
        
       return "Pro vytvoření vejceIndomina potřebuješ tRexVejce a raptorVejce";
        
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
