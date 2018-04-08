package com.github.Petrnet.mojeadventura.logika;
import java.util.*;






/**
 * Třída Batoh představuje batoh do kterého se mohou ukládat přenositelné předměty
 * 
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author     Petr Netolicky
 * @version    LS 2016/2017
 */

public class Batoh extends Observable
{

private Map<String,Predmet> seznamPredmetu;
private int Velikost = 6;  

 
    /**
    * Konstruktor třídy
    *
    * @param    seznam Předmětů
    */    

public Batoh ()
{
    seznamPredmetu = new HashMap<>();
    
}

     /**
     * Metoda, který zjistí zda-li je v batohu volno.
     
     * @return   true, pokud je v batohu volno; jinak false
     */ 
public boolean jeVolno() 
{
        if(seznamPredmetu.size() < Velikost)
        {
            return seznamPredmetu.size() < Velikost;
        }
        else {
            return false;
        }
        
    }
    
     /**
     * Metoda, která vrátí názav předmětu v batohu.
     
     * @return   název Předmětu
     */ 
     public Predmet getPredmet(String nazev) 
    {
        return seznamPredmetu.get(nazev);
    }

    /**
     * Metoda, který zjistí zda-li je batoh prázdný.
     
     * @return   true, pokud je v batohu prázdno; jinak false
     */ 
    public boolean jePrazdny() 
{
        if(seznamPredmetu.isEmpty())
        {
            return seznamPredmetu.isEmpty();
        }
        else {
            return false;
        }
        
    }
    
    /**
     * Metoda, který zjistí zda-li batoh obsahuje určitý předmět.
     * @param  nazevPredmetu, název Předmětu, který chceme zjistit zda-li je v batohu
     * @return   true, pokud je v předmět v batohu; jinak false
     */ 
    
    public boolean obsahujePredmet(String nazevPredmetu)
  {
        return seznamPredmetu.containsKey(nazevPredmetu);
  }
    
   /**
     * Metoda, která vloží předmět do batohu.
     * @param  nejakyPredmet, Předmět, který chceme vložit
     * @return   true, pokud je v předmět v batohu; jinak false
     */ 
    
  public boolean vlozPredmet(Predmet nejakyPredmet)
  {
      if (jeVolno() && nejakyPredmet.isPrenositelny()){
      
      seznamPredmetu.put(nejakyPredmet.getNazev(), nejakyPredmet);
      setChanged();
      notifyObservers();
      return true;
  }
  else{
      return false;
    }
    
}

    /**
     * Metoda, která vyhodí předmět z batohu.
     * @param  nazev, předmět který chceme vyhodit
     * @return   vrátí vyhozený předmět
     */ 
public Predmet vyhodPredmet(String nazev){
        
        Predmet vyhozenyPredmet = null;
        if (seznamPredmetu.containsKey(nazev)) {
            vyhozenyPredmet = seznamPredmetu.get(nazev);
            seznamPredmetu.remove(nazev);
        }
        setChanged();
        notifyObservers();
        return vyhozenyPredmet;  
    } 
      /**
     * Metoda, která zjistí obsah batohu
     * @return   seznam věcí, které jsou v batohu.
     */ 
public String nazvyPredmetu() {
        String nazvy = "V batohu máš: ";
        for (String nazevPredmetu : seznamPredmetu.keySet()){
            nazvy += nazevPredmetu + " ";
        }
        return nazvy;
 }

/**
 * Metoda, která zjistí, zda-li batoh obsahuje predmet
 * @return vrátí buď true=je v batohu nebo false=není v batohu
 */ 

 public boolean obsahujePredmety(Predmet predmet) {
        
        if (seznamPredmetu.containsKey(predmet))
        {
            return true;
        }
        return false;
 }
 
 /**
  * Metoda, která vrací hodnoty jednotlivých predmětů v kolekci
  * @return hodnoty předmětů v kolekci
  */ 
 public Collection<Predmet> getPredmetyBatoh() {
		return Collections.unmodifiableCollection(seznamPredmetu.values());
	}
 
 /**
  * Metoda, která vrací předměty
  * @return vrácená hodnota p předmětů
  */ 
 public List<Predmet> getPredmety(){
		
		List<Predmet> p = new ArrayList<>();
     for (Predmet predmet : seznamPredmetu.values()){
     	p.add(predmet);
     }	
     return p;
	}
 
 /**
  * Metoda, která vrací předměty
  * @return vrácená hodnota předmětů
  */ 
 public Map<String, Predmet> getSeznamPredmetu(){
	   	return seznamPredmetu;
	   }
        
  
@Override
    public String toString()
    {
        return nazvyPredmetu();
    }

}
       