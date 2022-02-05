
package Model;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class Shot {
    
    public int x;
    public int y;
    public boolean finished = false;
    public boolean isEnemy;
    public int demage;
    public Rectangle2D.Double rect;
    
    public Shot(int x, int y, boolean isEnemy, int demage){
        this.isEnemy = isEnemy;
        this.y = y;
        this.x = x;
        this.demage = demage;
        rect = new Rectangle2D.Double(x,y, 10, 10);
    }
    
    public Shape getShape(){
        return rect;
    }
    
    public void setShapeY(int y){
        rect = new Rectangle2D.Double(this.x, y , 10, 10);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean getFinished() {
        return finished;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
    
    public int getDemage(){
        return this.demage;
    }
    
    public void movePattern(){
        
    }
    
}
