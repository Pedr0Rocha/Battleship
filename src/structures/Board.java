package structures;

import java.util.ArrayList;

/**
 *
 * @author pedronote
 */
public class Board {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public final int UNKNOWN = -1;
    public final int WATER = 0;
    public final int SHIP = 1;
    public final int HIT = 2;
    
    public int width;
    public int height;
    public int[][] board;
    public int[][] boardShadow;
    public ArrayList<Ship> ships;
    
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        board = new int[width][height];
        boardShadow = new int[width][height];
        ships = new ArrayList<>();
        
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++) { 
                board[i][j] = WATER; 
                boardShadow[i][j] = UNKNOWN;
            }
    }
    
    public boolean addShip(Ship ship) {
        for (Position p : ship.positions) {
            if (board[p.x][p.y] == SHIP)
                return false;
            else 
                board[p.x][p.y] = SHIP;
        }
        System.out.println(ship.name + " added to the board.");
        ships.add(ship);
        return true;
    }
    
    public boolean hit(Position attackPos) {
        if (board[attackPos.x][attackPos.y] == SHIP) {
            board[attackPos.x][attackPos.y] = HIT;
            boardShadow[attackPos.x][attackPos.y] = HIT;
            Ship ship = getShipInPosition(attackPos);
            ship.hit(attackPos);
            if (!ship.isAlive) {
                System.out.println(ship.name + " sinked!");
                ships.remove(ship);
            }
            System.out.println("HIT!");
            return true;
        } else { 
            boardShadow[attackPos.x][attackPos.y] = WATER;
            System.out.println("MISS!");
            return false;
        }
    }
    
    public Ship getShipInPosition(Position pos) {
        for (Ship s : this.ships)
            for (Position p : s.positions)
                if (pos.x == p.x && pos.y == p.y)
                    return s;
        return null;
    }
    
    public void printBoard() {
        for (int i = 0; i < this.width; i++) {
            if (i == 0) System.out.print("   ");
            System.out.print(i + " ");
        }
        System.out.println("");
        
        for (int i = 0; i < this.width; i++) {
            System.out.println("");
            for (int j = 0; j < this.height; j++) {
                if (j == 0)
                    System.out.print((char)(65+i) + "  ");
                if (board[i][j] == HIT)
                    System.out.print(ANSI_RED + "X " + ANSI_RESET);
                else if (board[i][j] == WATER)
                    System.out.print(ANSI_BLUE + board[i][j] + " " + ANSI_RESET);
                else if (board[i][j] == SHIP)
                    System.out.print(ANSI_GREEN + board[i][j] + " " + ANSI_RESET);
            }
        } 
        System.out.println("");   
    }
    
    public void printBoardForEnemy() {
        for (int i = 0; i < this.width; i++) {
            if (i == 0) System.out.print("   ");
            System.out.print(i + " ");
        }
        System.out.println("");
        
        for (int i = 0; i < this.width; i++) {
            System.out.println("");
            for (int j = 0; j < this.height; j++) {
                if (j == 0)
                    System.out.print((char)(65+i) + "  ");
                if (board[i][j] == HIT)
                    System.out.print(ANSI_RED + "X " + ANSI_RESET);
                else if (boardShadow[i][j] == UNKNOWN)
                    System.out.print(ANSI_BLACK + "0 " + ANSI_RESET);
                else if (boardShadow[i][j] == WATER)
                    System.out.print(ANSI_BLUE + "0 " + ANSI_RESET);
            }
        } 
        System.out.println("");   
    }
    
    
    public void useFlare() {
        System.out.println("Revealing the enemy board!");
    }
}
