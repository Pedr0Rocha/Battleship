package structures;

import java.util.ArrayList;

/**
 *
 * @author pedronote
 */
public class Player {
    
    public String name;
    public Board board;
    
    public Player(String name) {
        this.name = name;
        board = new Board(10, 10);
    }
    
    public boolean lostGame() {
        return (this.board.ships.size() <= 0);
    }
}
