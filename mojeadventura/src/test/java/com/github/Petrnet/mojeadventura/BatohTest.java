/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.Petrnet.mojeadventura;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.github.Petrnet.mojeadventura.logika.*;



import static org.junit.Assert.*;



/*******************************************************************************
 * Testovací třída {@code BatohTest} slouží ke komplexnímu otestování
 * třídy {@link BatohTest}.
 *
 * @author  author name
 * @version 0.00.0000 — 20yy-mm-dd
 */
public class BatohTest
{

    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @Before
    public void setUp()
    {
    }


    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každého testu.
     */
    @After
    public void tearDown()
    {
    }
   /***************************************************************************
     * Test vložení předmětu
     */
    @Test
    public void testVlozPredmet()
    {
        Predmet predmet1 = new Predmet("a", "popis a", true, true,"ano");
        Predmet predmet2 = new Predmet("b", "popis b", false, true,"ano");
        Batoh batoh = new Batoh();
        assertTrue(batoh.vlozPredmet(predmet1));
        assertFalse(batoh.vlozPredmet(predmet2));
        
    }
    
    /***************************************************************************
     * Test vyhození předmětu
     */
    @Test
    public void testVyhodPredmet()
    {
        Predmet predmet1 = new Predmet("a", "popis a", true, true,"ano");
        Batoh batoh = new Batoh();
        batoh.vlozPredmet(predmet1);
        assertTrue(batoh.obsahujePredmet("a"));
        batoh.vyhodPredmet("a");
        assertFalse(batoh.obsahujePredmet("a"));
        
        
    }
    /***************************************************************************
     * Test omezeni batohu
     */
    @Test
    public void testOmezenostBatohu()
    {
        Batoh batoh = new Batoh();
        Predmet predmet1 = new Predmet("a", "popis a", true, true,"ano");
        Predmet predmet2 = new Predmet("b", "popis b", true, true,"ano");
        Predmet predmet3 = new Predmet("c", "popis c", true, true,"ano");
        Predmet predmet4 = new Predmet("d", "popis d", true, true,"ano");
        Predmet predmet5 = new Predmet("e", "popis e", true, true,"ano");
        Predmet predmet6 = new Predmet("f", "popis f", true, true,"ano");
        Predmet predmet7 = new Predmet("f", "popis g", true, true,"ano");
        assertTrue(batoh.vlozPredmet(predmet1));
        assertTrue(batoh.vlozPredmet(predmet2));
        assertTrue(batoh.vlozPredmet(predmet3));
        assertTrue(batoh.vlozPredmet(predmet4));
        assertTrue(batoh.vlozPredmet(predmet5));
        assertTrue(batoh.vlozPredmet(predmet6));
        assertFalse(batoh.vlozPredmet(predmet7));
        
    }
    
    
    


   
}
