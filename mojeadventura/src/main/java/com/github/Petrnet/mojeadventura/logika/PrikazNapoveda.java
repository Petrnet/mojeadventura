/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.Petrnet.mojeadventura.logika;

/**
 * Třída PrikazNapoveda implementuje pro hru příkaz napoveda.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author     Jarmila Pavlickova, Luboš Pavlíček, Jan Říha, Petr Netolicky
 * @version    LS 2016/2017
 */
class PrikazNapoveda implements IPrikaz {

    private static final String NAZEV = "napoveda";
    private SeznamPrikazu platnePrikazy;

   /**
    * Konstruktor třídy
    *
    * @param    platnePrikazy seznam příkazů, které je možné ve hře použít, aby je nápověda mohla zobrazit uživateli.
    */    
    public PrikazNapoveda(SeznamPrikazu platnePrikazy) {
        this.platnePrikazy = platnePrikazy;
    }

    /**
     * Vrací základní nápovědu po zadání příkazu "napoveda". Nyní se vypisuje
     * vcelku primitivní zpráva a seznam dostupných příkazů.
     *
     * @return    napoveda ke hre
     */
    @Override
    public String proved(String... parametry) {
        return "Tvým úkolem je vytvořit vejce Indomina Rexe\n"
        + "k tomu potřebuješ získat vejce tRexe a vejce raptora.\n"
        + "Nejdříve si zkus opatřit nějaké zbraně, třeba meč.\n"
        + "Potom už zvádneš zabít jednoho z býložravců \n"
        + "Vem si od býložravců maso a hod ho raptorovi \n"
        + "Od raptora vem klíč a jeho vejce, opatři si pušku \n"
        + "a zabij tRexe. \n"
        + "V budově laboratoř vytvoříš vejce a hru vyhraješ. \n"
        + "\n"
        + "Můžeš zadat tyto příkazy:\n"
        + platnePrikazy.vratNazvyPrikazu();
    }

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání).
     *
     * @return    název příkazu
     */
    @Override
      public String getNazev() {
        return NAZEV;
     }

}
