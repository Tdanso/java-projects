package Model;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public abstract class Ship {
    
    public int x;
    public int y;
    public int width;
    public int heigth;
    public boolean isEnemy;
    public int lifePoints;
    public Rectangle2D.Double rect;
    public String shipName;
    
    
    public Ship(int x, int y, int width, int heigth, int lifePoints, boolean isEnemy, String shipName){
        this.lifePoints = lifePoints;
        this.x = x;
        this.y = y;
        this.width = width;
        this.heigth = heigth;
        this.isEnemy = isEnemy;
        rect = new Rectangle2D.Double(x - (width/2), y - (heigth/2), width, heigth);
        this.shipName = shipName;
    }
    
    public void checkCollision(Rectangle2D re){
        if(rect.contains(re)){
        }
    }
    
    public Shape getShape(){
        return rect;
    }
    
    public void setShapeX(int x){
        this.rect = new Rectangle2D.Double(x - (rect.getWidth()/2), y - (rect.getHeight()/2), rect.getWidth(), rect.getHeight()); 
    }
    
    public int getLifePoints(){
        return this.lifePoints;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public boolean getIsEnemy(){
        return isEnemy;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public void setIsEnemy(boolean isEnemy){
        this.isEnemy = isEnemy;
    }

    public void updateLifePoints(int demage){
        this.lifePoints = this.lifePoints - demage;
    }
    
    public void setShapeY(int y){
       this.rect = new Rectangle2D.Double(x - (rect.getWidth()/2), y - (rect.getHeight()/2), rect.getWidth(), rect.getHeight());
    }
    
    public String getShipName(){
        return this.shipName;
    }
    
    public int getWidth(){
        return this.width;
    }
    
    public int getHeigth(){
        return this.heigth;
    }
    
    
    
}
