package com.github.Petrnet.mojeadventura.logika;

/**
 * Třída Dinosaurus představuje jednotlivé Dinosaury, které je možné
 * ve hře najít.
 * 
 * 
 *
 * @author     Petr Netolický
 * @version    LS 2016/2017
 */



public class Dinosaurus
{
   private String nazev;
   private Predmet coMa;
   private Predmet zabijeciPredmet;
   private Batoh batoh;
   private boolean vymena = false; 
   
    
    
    
    /**
     * Vytvoří nového dinosaura
     * 
     * @param  nazev název Dinosaura
    
     */
  public Dinosaurus(String nazev, Predmet coMa, Predmet zabijeciPredmet)
    {
        this.nazev = nazev;
        this.coMa = coMa;
        this.zabijeciPredmet = zabijeciPredmet;
    }
    
   public Dinosaurus(String nazev)
    {
        this(nazev, null, null);
    }
    
    public boolean isVymena() {
    return vymena;
   }
    
   public Predmet getZabijeciPredmet()
    {
        return zabijeciPredmet;
    }
    
    public Predmet getCoMa()
    {
        return coMa;
    }
    
 public void setVymena(boolean vymena) {
        this.vymena = vymena;
   }
 
 public Predmet vymena(Predmet zabijeciPredmet)
 
 {
     if (batoh.obsahujePredmety(zabijeciPredmet)) 
     {
        return coMa; 
     }
     
     return null;
    }
 
     /**
     * Vrátí název předmětu.
     * 
     * @returns    název předmětu
     */
    
   
  public String getNazev()
  {
        return nazev;
  }
    

  @Override
  public String toString()
  {
    return "Dinosaurus: " + nazev;
  }
   
}
