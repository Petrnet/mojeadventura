package com.github.Petrnet.mojeadventura;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.Petrnet.mojeadventura.logika.*;

import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída LokaceTest slouží ke komplexnímu otestování
 * třídy Lokace
 *
 * @author     Jarmila Pavlickova, Jan Riha
 * @version    LS 2016/2017
 */
public class LokaceTest
{
    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /**
     * Testuje, zda jsou správně nastaveny průchody mezi prostory hry. Prostory
     * nemusí odpovídat vlastní hře, 
     */
    @Test
    public  void testLzeProjit() {		
        Lokace lokace1 = new Lokace("hala", "vstupní hala budovy VŠE na Jižním městě",0,0);
        Lokace lokace2 = new Lokace("bufet", "bufet, kam si můžete zajít na svačinku",0,0);
        lokace1.setVychod(lokace2);
        lokace2.setVychod(lokace1);
        assertEquals(lokace2, lokace1.vratSousedniLokaci("bufet"));
        assertEquals(null, lokace2.vratSousedniLokaci("pokoj"));
    }
    
    @Test
    public void testVeci()
    {
        Lokace lokace1 = new Lokace(null, null,0,0);
        Predmet predmet1 = new Predmet("a", "popis a", true, true,"ano");
        Predmet predmet2 = new Predmet("b", "popis b", false, true,"ano");
        lokace1.vlozPredmet(predmet1);
        lokace1.vlozPredmet(predmet2);
        assertTrue(lokace1.obsahujePredmet("a"));
        assertSame(predmet1, lokace1.najdiPredmet("a"));
        assertSame(predmet1, lokace1.vezmiPredmet("a"));
        assertTrue(lokace1.obsahujePredmet("b"));
        assertSame(predmet2, lokace1.najdiPredmet("b"));
        assertSame(predmet2, lokace1.vezmiPredmet("b"));
        assertFalse(lokace1.obsahujePredmet("c"));
        assertNull(lokace1.najdiPredmet("c"));
        assertNull(lokace1.vezmiPredmet("c"));
    }
    
    @Test
    public void testDinosauru()
    {
        Lokace lokace1 = new Lokace("hala", "vstupní hala budovy VŠE na Jižním městě",0,0);
        Lokace lokace2 = new Lokace("bufet", "bufet, kam si můžete zajít na svačinku",0,0);
        Dinosaurus dinosaur1 = new Dinosaurus("a");
        Dinosaurus dinosaur2 = new Dinosaurus("c");
        lokace1.vlozDinosaur(dinosaur1);
        lokace1.vlozDinosaur(dinosaur2);
        assertTrue(lokace1.obsahujeDinosaura("a"));
        assertSame(dinosaur1, lokace1.odstranDinosaura("a"));
        assertTrue(lokace1.obsahujeDinosaura("c"));
        assertSame(dinosaur2, lokace1.odstranDinosaura("c"));
        assertFalse(lokace1.obsahujeDinosaura("lk"));
        assertNull(lokace1.odstranDinosaura("lk"));
    }

}
