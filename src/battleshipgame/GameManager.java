package battleshipgame;

import java.util.Random;
import java.util.Scanner;
import structures.Player;
import structures.Position;
import structures.ships.Destroyer;

/**
 *
 * @author pedronote
 */
public class GameManager {
    
    public Player p1;
    public Player p2;
    
    int turn = 0;
    
    Scanner scan;
    Random rand;
    
    public GameManager(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        
        scan = new Scanner(System.in);
        rand = new Random();
        
        startGame();
    }
    
    private void startGame() {
        
        setBoards();
        
        while (!p1.lostGame() && !p2.lostGame()) {
            if (turn % 2 == 0)
                System.out.println("Player 1 Turn");
            else
                System.out.println("Player 2 Turn");
            
            showBoards();
            Position attackPos = null;
            while (attackPos == null)
                attackPos = askAttackPosition();
            attackEnemy(attackPos);
            
            turn++;
        }
        
        if (!p1.lostGame()) 
            System.out.println(p1.name + " WINS!");
        else
            System.out.println(p2.name + " WINS!");
    }
    
    private void showBoards() {
        if (turn % 2 == 0) { 
            System.out.println("\n    ENEMY BOARD");
            p2.board.printBoardForEnemy();
            System.out.println("\n    P1 BOARD");
            p1.board.printBoard();
        } else {
            System.out.println("\n    ENEMY BOARD");
            p1.board.printBoardForEnemy();
            System.out.println("\n    P2 BOARD");
            p2.board.printBoard();
        }
    }
    
    private void setBoards() {
        
        while (p1.board.ships.size() != 4) {
            int xPos = rand.nextInt(10);
            int yPos = rand.nextInt(10);
            
            Destroyer des = new Destroyer();
            des.positions.add(new Position(xPos, yPos));
            
            if (rand.nextInt(100) > 50) {
                des.positions.add(new Position(xPos + 1, yPos));
            } else {
                des.positions.add(new Position(xPos - 1, yPos));
            }
            p1.board.addShip(des);
        }
        
        while (p2.board.ships.size() != 4) {
            int xPos = rand.nextInt(10);
            int yPos = rand.nextInt(10);
            
            Destroyer des = new Destroyer();
            des.positions.add(new Position(xPos, yPos));
            
            if (rand.nextInt(100) > 50) {
                des.positions.add(new Position(xPos + 1, yPos));
            } else {
                des.positions.add(new Position(xPos - 1, yPos));
            }
            p2.board.addShip(des);
        }
    }
    
    private Position askAttackPosition() {
        System.out.println("Insert Position to Attack - Format is LETTER, NUMBER (e.g. A, 5)");
        System.out.print("> ");
        Position attackPosition = null;
        try {
            attackPosition = new Position(scan.nextLine());
            if (!attackPosition.validPosition()) {
                System.out.println("Not a Valid Posistion!");
                return null;
            }
            System.out.println("Attacking (" + attackPosition.x + ", " + attackPosition.y + ")");
        } catch (Exception e) {
            System.out.println("Wrong Attack Format!");
        }
        
        return attackPosition;
    }
    
    private void attackEnemy(Position attackPos) {
        if (turn % 2 == 0) p2.board.hit(attackPos);
        else p1.board.hit(attackPos);
    }
}
