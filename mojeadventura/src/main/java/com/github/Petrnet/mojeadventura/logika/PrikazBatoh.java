package com.github.Petrnet.mojeadventura.logika;

/**
 * Třída PrikazBatoh implementuje pro hru příkaz batoh, který vypíše seznam věcí v batohu.
 * 
 * 
 * Tato třída je součástí jednoduché hry. 
 * 
 * @author     Petr Netolicky
 * @version    LS 2016/2017
 */
public class PrikazBatoh implements IPrikaz {

    private static final String NAZEV = "batoh";
    private HerniPlan hPlan;
   

     /**
    * Konstruktor třídy
    *
    * @param    plan herní plán, ve kterém se bude ve hře "chodit" 
    * @param    batoh batoh, do kterého se ukládají předměty
    */    
   
    public PrikazBatoh(HerniPlan hPlan)
    {
        this.hPlan = hPlan;
    }

  /**
     * Metoda představuje zpracování příkazu pro výpis věcí v batohu.
     * Nejprve se ověří, že metoda je bez parametrická.
     * Pokud je metoda bezparametru proběhne výpis všech věcí v batohu.
     * 
     * @param parametry pole parametrů zadaných na příkazové řádce, bez parametru
     * @return výsledek zpracování, tj. text, který se vypíše na konzoli
     */
    
    public String proved(String... parametry) {
        if (parametry.length > 0)
        {
            return "Tento prikaz takhle nefunguje, napis pouze UkazBatoh";
        }
        
        
        return hPlan.getBatoh().nazvyPredmetu() + "\n";  
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    public String getNazev() {
        return NAZEV;
    }
}
    

