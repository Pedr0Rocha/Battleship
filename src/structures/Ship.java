package structures;

import java.util.ArrayList;

/**
 *
 * @author pedronote
 */
public class Ship {
    
    public String name;
    public int size;
    public ArrayList<Position> positions;
    public boolean isAlive;
    
    public Ship(String name, int size) {
        this.name = name;
        this.size = size;
        this.positions = new ArrayList<>();
        this.isAlive = true;
    }
    
    public void hit(Position attackPos) {
        for (Position p : positions) {
            if ((p.x == attackPos.x && p.y == attackPos.y) && p.alivePosition) {
                p.alivePosition = false;
                checkAlive();
            }
        }
    }
    
    public void checkAlive() {
        int deadCount = 0;
        for (Position p: positions)
            if (!p.alivePosition)
                deadCount++;
        if (deadCount == this.size)
            this.isAlive = false;
    }
    
    public void printShipInfo() {
        System.out.println("Name: " + this.name);
        if (this.isAlive)
            System.out.println("Status: Alive");
        else 
            System.out.println("Status: Dead");
        
        System.out.println("Size: " + this.size);
        
        System.out.print("Positions:");
        for (int i = 0; i < this.positions.size(); i++)
            if (this.positions.get(i).alivePosition)
                System.out.print(" (" + this.positions.get(i).x + ", " + this.positions.get(i).y + ")");
        
        
        System.out.println("");
    }
    
}
