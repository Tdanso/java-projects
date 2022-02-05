package Controller;

import View.View;
import Model.Model;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class ViewController implements IViewController {

    private static ViewController instance = null;
    int endTurretScroll = 0;
    boolean specialShotIsDone = false;

    private ViewController() {
    }

    @Override
    public void openStartWindow() {
        //this.specialShotIsDone = false;
        View.getInstance().openStartPanel();
    }

    @Override
    public void closeStartWindow() {
        //this.specialShotIsDone = false;
        View.getInstance().closeStartPanel();
    }

    @Override
    public void openNewGameWindow() {
        //this.specialShotIsDone = false;
        View.getInstance().openNewGamePanel();
    }

    @Override
    public void closeNewGameWindow() {
        //this.specialShotIsDone = false;
        View.getInstance().closeNewGamePanel();
    }

    @Override
    public void openMainGUI(String shipType) {
        //this.specialShotIsDone = false;
        closeNewGameWindow();
        View.getInstance().openMainGUI(shipType);
    }

    @Override
    public void setPlayerName(String playerName) {
        Model.getInstance().setPlayerName(playerName);
    }

    @Override
    public String getPlayerName() {
        return Model.getInstance().getPlayerName();
    }

    @Override
    public int getPlayerX() {
        return Model.getInstance().getPlayerX();
    }

    @Override
    public int getPlayerY() {
        return Model.getInstance().getPlayerY();
    }

    @Override
    public void uploadPlayerPosition(int x) {
        Model.getInstance().updatePlayerPosition(x);
    }

    @Override
    public int getShotX(int i) {
        return Model.getInstance().getShotX(i);
    }

    @Override
    public int getShotY(int i) {
        return Model.getInstance().getShotY(i);
    }

    @Override
    public int getNumberOfShots() {
        return Model.getInstance().getNumberOfShots();
    }

    @Override
    public void newShot(int x, int y, boolean isEnemy, String enemyName) {
        Model.getInstance().newShot(x, y, isEnemy, enemyName);
    }

    @Override
    public void updateShotsPosition() {
        for (int i = 0; i < Model.getInstance().getNumberOfShots(); i++) {
            if (Model.getInstance().getIsEnemyShot(i)) {
                Model.getInstance().setShotY(Model.getInstance().getShotY(i) + Model.SHOTS_MOVEMENT_UNITS, i);
            } else {
                Model.getInstance().setShotY(Model.getInstance().getShotY(i) - Model.SHOTS_MOVEMENT_UNITS, i);
            }
        }
    }

    @Override
    public void isContain() {
        this.isContainEnemy();
        this.isContainPlayer();
    }

    public void isContainEnemy() {
        for (int i = 0; i < this.getNumberOfShots(); i++) {
            if (!Model.getInstance().getIsEnemyShot(i)) {
                for (int j = 0; j < this.getNumberOfEnemies(); j++) {
                    if (Model.getInstance().getShipShape(j).contains((Rectangle2D) Model.getInstance().getShotShape(i)) && !Model.getInstance().getStateOfShot(i)) {
                        Model.getInstance().updateEnemyLifePoints(j);
                        Model.getInstance().deleteShot(i);//delete the shot
                    }
                }
            }
        }
    }

    public void isContainPlayer() {
        for (int i = 0; i < this.getNumberOfShots(); i++) {
            if (Model.getInstance().getIsEnemyShot(i)) {
                if (Model.getInstance().getPlayerShape().contains((Rectangle2D) Model.getInstance().getShotShape(i)) && !Model.getInstance().getStateOfShot(i)) {
                    Model.getInstance().updatePlayerLifePoints(i);//decrease player lifepoints
                    Model.getInstance().deleteShot(i); //delete the shot
                    if (Model.getInstance().isPlayerDead()) {
                        this.endGame(true);
                    }
                }
            }
        }
    }

    @Override
    public boolean getStateOfShot(int i) {
        return Model.getInstance().getStateOfShot(i);
    }

    @Override
    public void randomEnemyShot() {
        int i = (int) (Math.random() * Model.getInstance().getNumberOfEnemies());
        this.newShot(this.getEnemiesX(i) - 3, this.getEnemiesY(i) + 25 - 3, true, Model.getInstance().getEnemyName(i)); //-3  = offset to centre the bullet (dim. bullet/2)
    }                                                                                                                   

    @Override
    public boolean getIsEnemyShot(int i) {
        return Model.getInstance().getIsEnemyShot(i);
    }

    @Override
    public void specialShot() {
        Model.getInstance().specialShot();
        Model.getInstance().resetSpecialShot();
        this.specialShotIsDone = true;
    }

    @Override
    public boolean isSpecialShotReady() {
        return (!this.specialShotIsDone && Model.getInstance().getSpecialShotCounter() >= Model.SPECIAL_FIRE_POINTS);
    }

    @Override
    public int getPlayerLifePoints() {
        return Model.getInstance().getPlayerLifePoints();
    }

    @Override
    public void endGame(boolean deadPlayer) {
        this.specialShotIsDone = false;
        Model.getInstance().writePoint();
        View.getInstance().openEndGamePanel(deadPlayer);
        Model.level = 1;
        Model.setInstance(null);
    }

    @Override
    public int getEnemiesX(int i) {
        return Model.getInstance().getEnemiesX(i);
    }

    @Override
    public int getEnemiesY(int i) {
        return Model.getInstance().getEnemiesY(i);
    }

    @Override
    public int getNumberOfEnemies() {
        return Model.getInstance().getNumberOfEnemies();
    }

    @Override
    public boolean getIsDeadAlien(int i) {
        return Model.getInstance().isEnemyDead(i);
    }

    @Override
    public void removeDeadEnemy(int i) {
        if (ViewController.getInstance().getIsDeadAlien(i)) {
            switch (Model.getInstance().getEnemyName(i)) {
                case "turret":
                    Model.getInstance().setPlayerPoints(Model.TURRET_POINTS);
                    if (!this.specialShotIsDone) {
                        Model.getInstance().setSpecialShotCounter(Model.TURRET_POINTS);
                    }
                    break;
                case "alien":
                    Model.getInstance().setPlayerPoints(Model.ALIEN_POINTS);
                    if (!this.specialShotIsDone) {
                        Model.getInstance().setSpecialShotCounter(Model.ALIEN_POINTS);
                    }
                    break;
            }
        }
        Model.getInstance().removeDeadEnemy(i);
    }

    @Override
    public void endOfLevel() {
        if (Model.getInstance().getNumberOfEnemies() == 0) {
            if (Model.level != 3) {
                this.specialShotIsDone = false;
                Model.level = Model.level + 1;
                Model.setInstance(null);
            } else {
                this.endGame(false);
            }

        }

    }

    @Override
    public void updateEnemiesPosition() {
        Model.getInstance().updateEnemiesPosition();
    }

    @Override
    public int getLevel() {
        return Model.level;
    }

    @Override
    public String getEnemyName(int i) {
        return Model.getInstance().getEnemyName(i);
    }

    @Override
    public int getEnemyWidth(int i) {
        return Model.getInstance().getEnemyWidth(i);
    }

    @Override
    public int getEnemyHeigth(int i) {
        return Model.getInstance().getEnemyHeigth(i);
    }

    @Override
    public int getPlayerPoints() {
        return Model.getInstance().getPlayerPoints();
    }

    //---------------------------------------------------------------
    // STATIC METHODS
    //---------------------------------------------------------------
    public static IViewController getInstance() {
        if (instance == null) {
            instance = new ViewController();
        }
        return instance;
    }

    public int getEnemyLifePoints(int i) {
        return Model.getInstance().getEnemyLife(i);
    }

    @Override
    public int getSpecialShotCounter() {
        return Model.getInstance().getSpecialShotCounter();
    }

}
