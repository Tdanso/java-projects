package Model;

public class Player extends Ship{
    public String name;
    public int points;
    public int specialShotCounter;
    
    
    public Player(int x, int y, int shapeWidth, int shapeHeigth, int lifePoints, boolean isEnemy, String name, String shipSprite){
        super(x, y, shapeWidth, shapeHeigth, lifePoints, isEnemy, shipSprite);
        this.name = name;
        this.points = 0;
        this.specialShotCounter = 0;
    }
    
    public void setPoints(int points){
        this.points += points;
    }
    
    public int getPoints(){
        return this.points;
    }
    
    public void setSpecialShotCounter(int points){
        this.specialShotCounter += points;
    }
    
    public int getSpecialShotCounter(){
        return specialShotCounter;
    }

    public void resetSpecialShotCounter(){
        this.specialShotCounter = 0;
    }
}
