package source;

import java.awt.*;

import static source.Constants.*;

public class Main {
  public static void main(String[] args) {
    consoleLog("starting main..");

    Menu menu = new Menu();
    menu.start();

    consoleLog("started main");

    /**
     ** HOE DE MAP WORDT AANGEMAAKT EN DE PANELS GESHOWED WORDEN**
     *
     * Main maakt nieuwe instance van Menu aan 'menu'
     * dan called die menu.start() functie
     *
     * er bestaat een PanelMainSide en een PanelMainMenu die zitten in de JPanel[] ParentPanel
     * PanelMainMenu shows de main menu in het begin.
     * PanelMainSide shows de game.
     *
     * Dan heb je in de PanelMainSide een PanelMainSideMenu
     * Die shows links de verschillende tabs enz. en rechts de board/end screen
     *
     * die functie maakt de gehele layout aan
     * Board wordt met de Menu class aangemaakt 'board'
     * board wordt toegevoegd aan de panelBoard
     * @see Menu#panelBoard
     *
     * Dan naar de Board Class
     *
     * Met de constuctor van Board wordt er een nieuwe Map instance aangemaakt 'map'
     * Ook wordt er een nieuwe Player instance aangemaakt 'player'
     * Er wordt een standaard gridsize en imgsize meegegeven 'medium'
     * Ook krijgt het board een keylistener van de ArrowKeyListener class.
     *
     * De functie van paintcomponent in Board heeft als functie om de player en tiles van de map instance te tekenen.
     * ook heeft de Board functies zoals setupNextmap en setupRestartMap.
     * de setupNextMap functie creeëert een nieuwe map en de setupRestartMap functie restart de huidige.
     * @see Board#paintComponent(Graphics)
     *
     * Dan naar de Map Class
     *
     * Met de constructor van map wordt een tile[][] aangemaakt die de grootte krijgt van de board instance.
     * ook wordt de int level een waarde gegeven namelijk: MAP_CODE.length = 3
     * En sizeMap = 1
     * Dan wordt de generateTiles() functie aangeroepen. Standaard is het dus de tutorial map die wordt gegenereerd door de waardes die zijn
     * aangegeven in de constructor van Map.
          *
          * De primaire functie van Map is dus de generateTiles() functie. Deze dienen als 'skelet' voor de Board class die de
     * Tiles daarwerkelijk gaat tekenen.
          * De Map class is bedoelt om te bepalen of op die plaats dan een wall, barricade, key, player etc. komt
     * Hoe de map het leest is uit een String die aangemaakt zijn in de Constants class.
          *
          * Dus op een rijtje:
          * - Er wordt een menu aangemaakt
     * - menu maakt board aan
     * - met het aanmaken van board wordt een map en player aangemaakt
     * - paintcomponent van board leest voor elke tile van map wat die moet tekenen
     * - map leest van constants wat er op elke coördinaat van tiles[][] moet komen.
     */
}
}
