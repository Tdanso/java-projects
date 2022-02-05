package Model;

import java.awt.Shape;

public interface IModel {
    
    public void setPlayerName(String playerName);

    public String getPlayerName();
    
    public int getPlayerX();
    
    public int getPlayerY();
    
    public int getEnemiesX(int i);
    
    public int getEnemiesY(int i);
    
    public void updatePlayerPosition(int x);
    
    public void newShot(int x, int y, boolean isEnemy, String shipName);

    public int getShotX(int i);
    
    public int getShotY(int i);
    
    public void setShotX(int x, int i);
    
    public void setShotY(int y, int i);
    
    public Shape getShotShape(int i);
    
    public int getNumberOfShots();
    
    public Shape getShipShape(int i);
    
    public int getNumberOfEnemies();
    
    public void deleteShot(int i);
    
    public boolean getStateOfShot(int i);
    
    public void updatePlayerLifePoints(int i);
    
    public void updateEnemyLifePoints(int i);
    
    public boolean getIsEnemyShot(int i);
    
    public Shape getPlayerShape();
    
    public boolean isEnemyDead(int i);
    
    public int getPlayerLifePoints();
    
    public boolean isPlayerDead();
    
    public void removeDeadEnemy(int i);
    
    public void updateEnemiesPosition();
    
    public String getEnemyName(int i);

    public int getEnemyWidth(int i);
    
    public int getEnemyHeigth(int i);
    
    public void setPlayerPoints(int points);
    
    public int getPlayerPoints();
    
    public void writePoint();
    
    public void specialShot();
    
    public void setSpecialShotCounter(int points);
        
    public int getSpecialShotCounter();
    
    public int getEnemyLife(int i);

    public void resetSpecialShot();
}
