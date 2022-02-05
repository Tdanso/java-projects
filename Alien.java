package Model;
public class Alien extends Enemy{
    
    public Alien(int x, int y, int shapeWidth, int shapeHeigth, int lifePoints, boolean isEnemy, String shipSprite){
        super(x,y,shapeWidth,shapeHeigth,lifePoints,isEnemy, shipSprite);
    }
    
    @Override
    public void movePattern(){
    }
    
    
}
