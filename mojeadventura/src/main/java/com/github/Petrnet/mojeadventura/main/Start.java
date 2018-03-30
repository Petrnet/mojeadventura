/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.Petrnet.mojeadventura.main;

import com.github.Petrnet.mojeadventura.logika.*;
import com.github.Petrnet.mojeadventura.uiText.TextoveRozhrani;


/*******************************************************************************
 * Třída  Start je hlavní třídou projektu, který představuje jednoduchou textovou
 * adventuru určenou k dalším úpravám a rozšiřování.
 *
 * @author     Jarmila Pavlíčková, Jan Říha
 * @version    LS 2016/2017
 */
public class Start
{
    /***************************************************************************
     * Metoda, prostřednictvím níž se spouští celá aplikace.
     *
     * @param args Parametry příkazového řádku
     */
	 public static void main(String[] args)
	    {
	        
	        IHra hra = new Hra();
	        TextoveRozhrani ui = new TextoveRozhrani(hra);
	        ui.hraj();
	    }
	}

