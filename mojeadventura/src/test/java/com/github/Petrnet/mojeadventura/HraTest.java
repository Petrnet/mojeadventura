package com.github.Petrnet.mojeadventura;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import com.github.Petrnet.mojeadventura.logika.Hra;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author     Jarmila Pavlíčková, Jan Říha
 * @version    LS 2016/2017
 */
public class HraTest {
    private Hra hra1;

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
        hra1 = new Hra();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí.
     * Po každém použití příkazu vezmi se testuje, zda-li je předmět skutečně uložen v batohu.
     * Několikrát je i otestování zda-li po příkazu jdi se hráč nachází v přepokládané lokaci.
     * 
     * 
     */
    @Test
    public void testPrubehHry() {
        assertEquals("pristav", hra1.getHerniPlan().getAktualniLokace().getNazev());
        hra1.zpracujPrikaz("jdi breh");
        assertEquals(false, hra1.konecHry());
        assertEquals("breh", hra1.getHerniPlan().getAktualniLokace().getNazev());
        hra1.zpracujPrikaz("jdi louka");
        assertEquals(false, hra1.konecHry());
        assertEquals("louka", hra1.getHerniPlan().getAktualniLokace().getNazev());
        hra1.zpracujPrikaz("jdi jurskyParkBudova");
        assertEquals(false, hra1.konecHry());
        assertEquals("jurskyParkBudova", hra1.getHerniPlan().getAktualniLokace().getNazev());
        assertEquals(false, hra1.konecHry());    
        hra1.zpracujPrikaz("vezmi mec");
        assertEquals(false, hra1.konecHry());
        assertEquals(true, hra1.getHerniPlan().getBatoh().obsahujePredmet("mec"));
        hra1.zpracujPrikaz("vezmi puska");
        assertEquals(false, hra1.konecHry());
        assertEquals(false, hra1.getHerniPlan().getBatoh().obsahujePredmet("puska"));
        hra1.zpracujPrikaz("jdi louka");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi breh");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("zabij dodo");
        assertEquals(false, hra1.konecHry());  
        hra1.zpracujPrikaz("vezmi dodoMaso");
        assertEquals(false, hra1.konecHry());
        assertEquals(true, hra1.getHerniPlan().getBatoh().obsahujePredmet("dodoMaso"));
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi jeskyne");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("hodMaso raptor");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("vezmi raptorVejce");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("vezmi klic");
        assertEquals(false, hra1.konecHry());
        assertEquals(true, hra1.getHerniPlan().getBatoh().obsahujePredmet("klic"));
        assertEquals(true, hra1.getHerniPlan().getBatoh().obsahujePredmet("raptorVejce"));
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi breh");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi louka");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi jurskyParkBudova");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("vezmi puska");
        assertEquals(false, hra1.konecHry());
        assertEquals(true, hra1.getHerniPlan().getBatoh().obsahujePredmet("puska"));
        hra1.zpracujPrikaz("jdi louka");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi skaly");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi tRexovoDoupe");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("vezmi tRexVejce");
        assertEquals(false, hra1.konecHry());
        assertEquals(false, hra1.getHerniPlan().getBatoh().obsahujePredmet("tRexVejce"));
        hra1.zpracujPrikaz("zabij tRex");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("vezmi tRexVejce");
        assertEquals(false, hra1.konecHry());
        assertEquals(true, hra1.getHerniPlan().getBatoh().obsahujePredmet("tRexVejce"));
        hra1.zpracujPrikaz("jdi skaly");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi louka");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi jurskyParkBudova");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi laborator");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("vytvor");
        assertEquals(true, hra1.konecHry());
        
    }
}
