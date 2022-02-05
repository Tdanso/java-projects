package Model;

import java.awt.Shape;
import java.util.ArrayList;

public class Model implements IModel {

    final public static int PLAYER_X = 450;
    final public static int PLAYER_Y = 520;
    final public static int TURRET_X = 450;
    final public static int TURRET_Y = 520;
    final public static int PLAYER_LIFEPOINTS = 500;
    final public static int ALIEN_LIFEPOINTS = 50;
    final public static int TURRET_LIFEPOINTS = 100;
    final public static int ALIEN_POINTS = 25;
    final public static int TURRET_POINTS = 50;
    final public static int ALIEN_DEMAGE = 50;
    final public static int TURRET_DEMAGE = 100;
    final public static int PLAYER_DEMAGE = 10;
    final public static int PLAYER_ICON_WIDTH = 75;
    final public static int PLAYER_ICON_HEIGTH = 75;
    final public static int ALIEN_ICON_WIDTH = 65;
    final public static int ALIEN_ICON_HEIGTH = 50;
    final public static int TURRET_ICON_WIDTH = 75;
    final public static int TURRET_ICON_HEIGTH = 75;
    final public static String PLAYER_TYPE_NAME = "player";
    final public static String ALIEN_TYPE_NAME = "alien";
    final public static String TURRET_TYPE_NAME = "turret";
    final public static int SPECIAL_FIRE_POINTS = 200;
    final public static int SHOTS_MOVEMENT_UNITS = 15;
    
    
    private static Model instance = null;
    private static String playerName;
    private static long startTime;
    private static long stopTime;
    public ArrayList<Enemy> enemies; //enemies ships
    public Player player; //player ship
    public ArrayList<Shot> shots;
    public fileChart chart;
    public static int level = 1;


    public Model() {
        if(startTime == 0)
                startTime = System.nanoTime();
        
        if (this.playerName == null) {
            this.playerName = "Unknown";
        }
        this.enemies = new ArrayList<Enemy>();
        this.player = new Player(Model.PLAYER_X, Model.PLAYER_Y, Model.PLAYER_ICON_WIDTH, Model.PLAYER_ICON_HEIGTH, Model.PLAYER_LIFEPOINTS, false, PLAYER_TYPE_NAME,  "paolo");
        this.shots = new ArrayList<Shot>();
        switch (level) {
            case 1:
                this.populateBoardForLevelOne();
                break;
            case 2:
                this.populateBoardForLevelTwo();
                break;
            case 3:
                this.populateBoardForLevelThree();
                break;
        }
        
        this.chart = new fileChart();
    }

    public void populateBoardForLevelOne() {
        this.player.setIsEnemy(false);
        /*initial coordinates of the player*/
        this.player.setX(PLAYER_X);
        this.player.setY(PLAYER_Y);
        int dx = 75;

        for (int i = 0; i < 5; i++) {
            enemies.add(new Alien(dx, 50, Model.ALIEN_ICON_WIDTH, Model.ALIEN_ICON_HEIGTH, Model.ALIEN_LIFEPOINTS, true, ALIEN_TYPE_NAME));
            dx += 150;
        }

        dx = 155;
        for (int i = 0; i < 4; i++) {
            enemies.add(new Alien(dx, 200, Model.ALIEN_ICON_WIDTH, Model.ALIEN_ICON_HEIGTH, Model.ALIEN_LIFEPOINTS, true, ALIEN_TYPE_NAME));
            dx += 150;
        }
    }

    public void populateBoardForLevelTwo() {
       /*initial coordinates of the player*/
        this.player.setX(PLAYER_X);
        this.player.setY(PLAYER_Y);
        int dx = 75;

        enemies.add(new Turret(20, 50, Model.TURRET_ICON_WIDTH, Model.TURRET_ICON_HEIGTH, Model.TURRET_LIFEPOINTS, true, TURRET_TYPE_NAME));
        for (int i = 0; i < 5; i++) {
            enemies.add(new Alien(dx, 200, Model.ALIEN_ICON_WIDTH, Model.ALIEN_ICON_HEIGTH, Model.ALIEN_LIFEPOINTS, true, ALIEN_TYPE_NAME));
            dx += 150;
        }
        dx = 155;
        for (int i = 0; i < 4; i++) {
            enemies.add(new Alien(dx, 300, Model.ALIEN_ICON_WIDTH, Model.ALIEN_ICON_HEIGTH, Model.ALIEN_LIFEPOINTS, true, ALIEN_TYPE_NAME));
            dx += 150;
        }

    }
    
    public void populateBoardForLevelThree(){
        this.player.setX(PLAYER_X);
        this.player.setY(PLAYER_Y);
        int dx = 60;

        enemies.add(new Turret(20, 50, Model.TURRET_ICON_WIDTH, Model.TURRET_ICON_HEIGTH, Model.TURRET_LIFEPOINTS, true, TURRET_TYPE_NAME));
            for (int i = 0; i < 4; i++) {
            enemies.add(new Alien(dx, 130, Model.ALIEN_ICON_WIDTH, Model.ALIEN_ICON_HEIGTH, Model.ALIEN_LIFEPOINTS, true, ALIEN_TYPE_NAME));
            dx += 80;
            enemies.add(new Alien(dx, 160, Model.ALIEN_ICON_WIDTH, Model.ALIEN_ICON_HEIGTH, Model.ALIEN_LIFEPOINTS, true, ALIEN_TYPE_NAME));
            dx += 80;
        }
        enemies.add(new Alien(dx, 130, Model.ALIEN_ICON_WIDTH, Model.ALIEN_ICON_HEIGTH, Model.ALIEN_LIFEPOINTS, true, ALIEN_TYPE_NAME));
        dx = 60;
        enemies.add(new Turret(690, 250, Model.TURRET_ICON_WIDTH, Model.TURRET_ICON_HEIGTH, Model.TURRET_LIFEPOINTS, true, TURRET_TYPE_NAME));
        for (int i = 0; i < 5; i++) {
            enemies.add(new Alien(dx, 320, Model.ALIEN_ICON_WIDTH, Model.ALIEN_ICON_HEIGTH, Model.ALIEN_LIFEPOINTS, true, ALIEN_TYPE_NAME));
            dx += 150;
        }
    }

    @Override
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public String getPlayerName() {
        return this.playerName;
    }

    @Override
    public int getPlayerX() {
        return this.player.getX();
    }

    @Override
    public int getPlayerY() {
        return this.player.getY();
    }

    @Override
    public void updatePlayerPosition(int x) {
        this.player.setX(x);
        this.player.setShapeX(x);
    }

    @Override
    public void newShot(int x, int y, boolean isEnemy, String shipName){
        switch(shipName){
            case "alien":
            this.shots.add(new Shot(x, y, isEnemy, Model.ALIEN_DEMAGE));
            break;
            case "turret":
            this.shots.add(new Shot(x, y, isEnemy, Model.TURRET_DEMAGE));
            break;
            case "player":
            this.shots.add(new Shot(x, y, isEnemy, Model.PLAYER_DEMAGE));
            break;
        }
    }

    @Override
    public int getShotX(int i) {
        return this.shots.get(i).getX();
    }

    @Override
    public int getShotY(int i) {
        return this.shots.get(i).getY();
    }

    @Override
    public void setShotX(int x, int i) {
        this.shots.get(i).setX(x);
    }

    @Override
    public void setShotY(int y, int i) {
        this.shots.get(i).setY(y);
        this.shots.get(i).setShapeY(y);
    }

    @Override
    public int getNumberOfShots() {
        return this.shots.size();
    }

    @Override
    public Shape getShipShape(int i) {
        return enemies.get(i).getShape();
    }

    @Override
    public int getEnemiesX(int i) {
        return this.enemies.get(i).getX();
    }

    @Override
    public int getEnemiesY(int i) {
        return this.enemies.get(i).getY();
    }

    @Override
    public Shape getShotShape(int i) {
        return shots.get(i).getShape();
    }

    @Override
    public int getNumberOfEnemies() {
        return this.enemies.size();
    }

    @Override
    public void deleteShot(int i) {
        this.shots.get(i).setFinished(true);
    }

    @Override
    public boolean getStateOfShot(int i) {
        return this.shots.get(i).getFinished();
    }

    @Override
    public void updatePlayerLifePoints(int i) {
        this.player.updateLifePoints(this.shots.get(i).getDemage());
    }

    @Override
    public void updateEnemyLifePoints(int i) {
        this.enemies.get(i).updateLifePoints(Model.PLAYER_DEMAGE);
    }

    @Override
    public boolean getIsEnemyShot(int i) {
        return this.shots.get(i).isEnemy;
    }

    @Override
    public Shape getPlayerShape() {
        return this.player.getShape();
    }

    @Override
    public boolean isEnemyDead(int i) {
        return (this.enemies.get(i).getLifePoints() <= 0);
    }
    
    @Override
    public boolean isPlayerDead() {
        return this.player.getLifePoints() <= 0;
    }

    @Override
    public int getPlayerLifePoints() {
        return this.player.getLifePoints();
    }

    @Override
    public void removeDeadEnemy(int i) {
        this.enemies.remove(i);
    }

    //STATIC METHOD
    public static IModel getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    public static void setInstance(IModel newInstance) {
        instance = (Model) newInstance;
    }
    
    @Override
    public void updateEnemiesPosition() {
        for(int i = 0; i < this.enemies.size(); i++){
            this.enemies.get(i).movePattern();
        }
    }
    
    @Override
    public String getEnemyName(int i){
        return enemies.get(i).getShipName();
    }

    @Override
    public int getEnemyWidth(int i) {
        return this.enemies.get(i).getWidth();
    }

    @Override
    public int getEnemyHeigth(int i) {
        return this.enemies.get(i).getHeigth();
    }
    
    @Override
    public void setPlayerPoints(int points){
        this.player.setPoints(points);
    }
    
    @Override
    public int getPlayerPoints(){
        return this.player.getPoints();
    }
    
    public void writePoint(){
        Model.stopTime = System.nanoTime();
        double time = Model.stopTime - Model.startTime;
        time = time / 1000000000;
        time = Math.round(time*100.0)/100.0;
        chart.write(this.getPlayerName() + " - level: " + level + " - points: " + this.getPlayerPoints() + " - time: " + time + "\n");
    }
    
    @Override
    public void specialShot(){
        int dx = 10;
        for(int i = 0; i < 25; i++){
            this.newShot(dx, this.getPlayerY(), false, "player");
            dx += 30;
        }
    }
    
    @Override
    public void setSpecialShotCounter(int points){
        this.player.setSpecialShotCounter(points);
    }
    
    @Override
    public int getSpecialShotCounter(){
        return this.player.getSpecialShotCounter();
    }
    
    public int getEnemyLife(int i){
        return this.enemies.get(i).lifePoints;
    }

    @Override
    public void resetSpecialShot() {
        this.player.resetSpecialShotCounter();
    }
    
}
