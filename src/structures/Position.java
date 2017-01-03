package structures;

/**
 *
 * @author pedronote
 */
public class Position {
    
    public int x;
    public int y;
    public boolean alivePosition;
    
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
        this.alivePosition = true;
    }
    
    public Position(String position) {
        position = position.replace(" ", "");
        String[] pos = position.split(",");
        
        char charPos = (pos[0]).toUpperCase().toCharArray()[0];
        
        this.x = (int)(charPos - 65);
        this.y = Integer.parseInt(pos[1]);
        this.alivePosition = true;
    }
    
    public boolean validPosition() {
        if (this.x < 0 || this.y < 0)
            return false;
        if (this.x > 10 || this.y > 10)
            return false;
        return true;
    }
}
