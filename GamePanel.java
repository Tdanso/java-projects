package View;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.MouseInputListener;
import Controller.ViewController;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GamePanel extends JPanel implements ActionListener, MouseInputListener, KeyListener {

    final static int PLAYER_ICON_WIDTH = 75;
    final static int PLAYER_ICON_HEIGTH = 75;
    final static int ALIEN_ICON_WIDTH = 50;
    final static int ALIEN_ICON_HEIGTH = 50;
    final static int TURRET_ICON_WIDTH = 75;
    final static int TURRET_ICON_HEIGTH = 75;
    Timer timer = new Timer((int) 30, this);
    public static String shipType;
    public boolean muteVolume;
    int c = 0;
    ImageIcon turretPhoto;
    ImageIcon backgroundPhoto;
    ImageIcon enemyPhoto;
    ImageIcon playerPhoto;
    ImageIcon iconExpl;
    SoundEffect sound;
    

    public GamePanel(String shipType) {
        super();
        timer.start();
        this.sound = new SoundEffect();
        this.addMouseListener((MouseListener) this);
        this.addMouseMotionListener((MouseMotionListener) this);
        this.addKeyListener((KeyListener) this);
        this.shipType = shipType;
        this.loadPhotos();
        this.sound.backgroundMusic();
    }

    @Override
    public synchronized void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.paintBackground(g);
        this.paintEnemies(g);
        this.paintExplosions(g);
        this.paintPlayer(g);
        this.paintShot(g);
    }

    public void loadPhotos() {
        this.turretPhoto = new ImageIcon("img/turret.png");
        this.backgroundPhoto = new ImageIcon("img/foto.jpg");
        this.enemyPhoto = new ImageIcon("img/alien.png");
        this.playerPhoto = new ImageIcon(this.shipType);
        this.iconExpl = new ImageIcon("img/expl.png");
    }
    
    public void setMuteVolume(boolean b){
        if(b)
            this.sound.stopBackgroundMusic();
        else
            this.sound.backgroundMusic();
        this.muteVolume = b;
    }


    public void paintBackground(Graphics g) {
        g.drawImage(backgroundPhoto.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
    }

    public void paintPlayer(Graphics g) {
        g.drawImage(playerPhoto.getImage(), ViewController.getInstance().getPlayerX() - (PLAYER_ICON_WIDTH / 2), ViewController.getInstance().getPlayerY() - (PLAYER_ICON_HEIGTH / 2), PLAYER_ICON_WIDTH, PLAYER_ICON_HEIGTH, this);
    }

    public void paintEnemies(Graphics g) {
        for (int i = 0; i < ViewController.getInstance().getNumberOfEnemies(); i++) {
            if (!ViewController.getInstance().getIsDeadAlien(i)) {
                if (ViewController.getInstance().getEnemyName(i).equals("turret")) {
                    g.drawImage(turretPhoto.getImage(), ViewController.getInstance().getEnemiesX(i) - (ViewController.getInstance().getEnemyWidth(i) / 2), ViewController.getInstance().getEnemiesY(i) - (ViewController.getInstance().getEnemyHeigth(i) / 2), ViewController.getInstance().getEnemyWidth(i), ViewController.getInstance().getEnemyWidth(i), this);
                } else {
                    g.drawImage(enemyPhoto.getImage(), ViewController.getInstance().getEnemiesX(i) - (ViewController.getInstance().getEnemyWidth(i) / 2), ViewController.getInstance().getEnemiesY(i) - (ViewController.getInstance().getEnemyHeigth(i) / 2), ViewController.getInstance().getEnemyWidth(i), ViewController.getInstance().getEnemyWidth(i), this);
                }
            }
        }
    }

    public synchronized void paintExplosions(Graphics g) {
        for (int i = 0; i < ViewController.getInstance().getNumberOfEnemies(); i++) {
            if (ViewController.getInstance().getIsDeadAlien(i)) {
                g.drawImage(iconExpl.getImage(), ViewController.getInstance().getEnemiesX(i) - (ViewController.getInstance().getEnemyWidth(i) / 2), ViewController.getInstance().getEnemiesY(i) - (ViewController.getInstance().getEnemyHeigth(i) / 2), ViewController.getInstance().getEnemyWidth(i), ViewController.getInstance().getEnemyWidth(i), this); 
                ViewController.getInstance().removeDeadEnemy(i);
                i = i - 1;
                if(!this.muteVolume)
                    this.sound.explosionSound();
            }
        }
    }

    public void paintShot(Graphics g) {
        for (int i = 0; i < Controller.ViewController.getInstance().getNumberOfShots(); i++) {
            if (!ViewController.getInstance().getStateOfShot(i)) {
                if (ViewController.getInstance().getIsEnemyShot(i)) {
                    g.setColor(Color.GREEN);
                    g.fillRect(Controller.ViewController.getInstance().getShotX(i), Controller.ViewController.getInstance().getShotY(i), 5, 10);
                } else {
                    g.setColor(Color.RED);
                    g.fillRect(Controller.ViewController.getInstance().getShotX(i), Controller.ViewController.getInstance().getShotY(i), 10, 10);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ViewController.getInstance().endOfLevel();
        if (this.c != 0 && (this.c % 15) == 0) {
            ViewController.getInstance().randomEnemyShot();
            if(!this.muteVolume)
                sound.enemyShotSound();
        }
        ViewController.getInstance().updateEnemiesPosition();
        ViewController.getInstance().isContain();
        ViewController.getInstance().updateShotsPosition();
        this.repaint();
        c++; //variable used to slow down the enemies
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        ViewController.getInstance().newShot(e.getX() - 5, 480, false, "player");
        if(!this.muteVolume)
            this.sound.playerShotSound();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //to-do
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //to-do
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //to-do
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        ViewController.getInstance().uploadPlayerPosition(e.getX());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        ViewController.getInstance().uploadPlayerPosition(e.getX());
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                ViewController.getInstance().newShot(ViewController.getInstance().getPlayerX() - 5, 480, false, "player");
                if(!this.muteVolume)
                    this.sound.playerShotSound();
                break;
            case KeyEvent.VK_Z:
                if (ViewController.getInstance().isSpecialShotReady()) {
                    ViewController.getInstance().specialShot();
                }
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                ViewController.getInstance().uploadPlayerPosition(ViewController.getInstance().getPlayerX() - 7);
                break;
            case KeyEvent.VK_RIGHT:
                ViewController.getInstance().uploadPlayerPosition(ViewController.getInstance().getPlayerX() + 7);
                break;
        }
    }

}
