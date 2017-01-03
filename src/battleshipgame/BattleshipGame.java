package battleshipgame;

import structures.Player;

/**
 *
 * @author pedronote
 */
public class BattleshipGame {

    public static void main(String[] args) {
        Player p1 = new Player("Pedro");
        Player p2 = new Player("Machine");
        
        GameManager gm = new GameManager(p1, p2);
    }
    
}
