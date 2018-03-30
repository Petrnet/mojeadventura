package com.github.Petrnet.mojeadventura.logika;
/**
 * Třída PrikazZabij implementuje pro hru příkaz zabij.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author     Petr Netolický
 * @version    LS 2016/2017
 */

public class PrikazZabij implements IPrikaz
{
    private static final String NAZEV = "zabij";
    private HerniPlan hPlan;
    Predmet triceratopsMaso = new Predmet("triceratopsMaso", "chutne velke maso");
    Predmet dodoMaso = new Predmet("dodoMaso", "chutne male maso");
    Predmet tRexVejce = new Predmet("tRexVejce", "velke vejce");
    private Dinosaurus dinosaurus;
    
  
    
     /**
    * Konstruktor třídy
    *
    * @param    plan herní plán, ve kterém se bude ve hře "chodit" 
    * @param    batoh batoh, do kterého se ukládají předměty
    */    
    public PrikazZabij(HerniPlan hPlan, Dinosaurus dinosaurus)
    {
        this.hPlan = hPlan;
        this.dinosaurus = dinosaurus;
    }
    
    /**
     * Metoda představuje zpracování příkazu pro zabití dinosaura.
     * Nejprve zkontroluje, zda byl zadán právě jeden název jako
     * parametr, ověří, zda v aktuální lokaci je dinosaurus s tímto
     * názvem, pokud hráč má měc a použije příkaz zabij v oblasti breh či louka
     * odstraní ho a v lokaci se objeví maso.
     * Pokud hráč použije tento příkaz v lokaci tRexDoupe
     * tRex bude odstraněn z lokace a objeví se jeho tRexVejce.
     * Pokud bude příkaz zabij použit v jiném případě,
     * hra skočí a hráč prohrál.
     * 
     * @param parametry může jako parametr obsahovat název dinosaura, kterýho chce hráč zabít
     * @return výsledek zpracování, tj. text, který se vypíše na konzoli
     */
    public String proved(String... parametry)
    {       
        if (parametry.length == 0 || parametry.length > 1 )
        { 
            return "Koho mam zabit?";
        }
        
        String nazevDinosaura = parametry[0];
        Lokace aktLokace = hPlan.getAktualniLokace();
        Batoh batoh = hPlan.getBatoh();
        
       
        if (!aktLokace.obsahujeDinosaura(nazevDinosaura))
        {
            return "Dinosaurus " + nazevDinosaura + " tady neni";
        }
        
        if (batoh.obsahujePredmety(dinosaurus.getZabijeciPredmet()))
        {
           dinosaurus.getCoMa();
           
           return "nazevPredmetu";
        }
        
        if (aktLokace.getNazev().equals("breh") && batoh.nazvyPredmetu().contains("mec"))
        {
           aktLokace.odstranDinosaura(nazevDinosaura);
           aktLokace.vlozPredmet(dodoMaso);
           return nazevDinosaura + ", byl zabit, nyni si muzes vzit jeho maso";
        }
        
        if (aktLokace.getNazev().equals("louka") && batoh.nazvyPredmetu().contains("mec"))
        {
           aktLokace.odstranDinosaura(nazevDinosaura);
           aktLokace.vlozPredmet(triceratopsMaso);
           return nazevDinosaura + ", byl zabit, nyni si muzes vzit jeho maso";
        }
        
        if (aktLokace.getNazev().equals("tRexovoDoupe") && batoh.nazvyPredmetu().contains("puska"))
        {
           aktLokace.odstranDinosaura(nazevDinosaura);
           aktLokace.vlozPredmet(tRexVejce);
           return nazevDinosaura + ", byl zabit, nyni si muzes vzit tRexVejce";
        }
        hPlan.setProhra(true);
        return "Nezvladnes zabit " +  nazevDinosaura;
        
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

