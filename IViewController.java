package Controller;

import java.awt.Shape;

public interface IViewController {
    
        public void openStartWindow();

	public void closeStartWindow();

	public void openNewGameWindow();
    
        public void closeNewGameWindow();

	public void openMainGUI(String shipType);
        
        public String getPlayerName();
        
        public void setPlayerName(String playerName);
        
        public int getPlayerX();
        
        public int getPlayerY();
        
        public int getEnemiesX(int i);
        
        public int getEnemiesY(int i);
        
        public void uploadPlayerPosition(int x);
        
        public void newShot(int x, int y, boolean isEnemy, String enemyName);
        
        public void updateShotsPosition();
        
        public int getShotX(int i);
        
        public int getShotY(int i);
        
        public int getNumberOfShots();
        
        public int getNumberOfEnemies();
        
        public void isContain();
        
        public boolean getStateOfShot(int i);
        
        public void randomEnemyShot();

        public boolean getIsEnemyShot(int i);

        public int getPlayerLifePoints();
        
        public boolean getIsDeadAlien(int i);
        
        public void removeDeadEnemy(int i);
        
        public void endOfLevel();
        
        public int getLevel();

        public void updateEnemiesPosition();
        
        public String getEnemyName(int i);
        
        public int getEnemyWidth(int i);
    
        public int getEnemyHeigth(int i);
        
        public int getPlayerPoints();
  
        public void endGame(boolean deadPlayer);
        
        public void specialShot();
                
        public boolean isSpecialShotReady();
        
        public int getEnemyLifePoints(int i);
        
        public int getSpecialShotCounter();
        
}
