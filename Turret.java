package Model;

public class Turret extends Enemy{
    
    private int endTurretScroll = 0;
    
    public Turret(int x, int y, int shapeWidth, int shapeHeigth, int lifePoints, boolean isEnemy, String shipSprite){
        super(x, y, shapeWidth, shapeHeigth, lifePoints, isEnemy, shipSprite);
    }
    
    @Override
    public void movePattern(){
        if (this.getX() < 700 && this.endTurretScroll == 0) {
                this.setX(this.getX() + 5);
                setShapeX(this.getX() + 5);
                if (this.getX() == 700) {
                    this.endTurretScroll = 1;
                }
            } else {
                if (this.endTurretScroll == 1) {
                    this.setX(this.getX() - 5);
                    setShapeX(this.getX() - 5);
                    if (this.getX() == 40) {
                        this.endTurretScroll = 0;
                    }
                }
            }
    }
    
    
}
