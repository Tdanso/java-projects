package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Controller.ViewController;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class GUI extends JFrame implements ActionListener {

    private final static int WINDOW_PREFERRED_WIDTH = 900;
    private final static int WINDOW_PREFERRED_HEIGHT = 600;

    private JButton stopButton;
    private JLabel playerNameLab;
    private JLabel playerNamePrefixLab;
    private JLabel playerScoreLab;
    private JLabel playerScorePrefixLab;
    private JLabel playerLifeLab;
    private JLabel playerLifePrefixLab;
    private JLabel special;
    private JLabel specialPerc;
    private JLabel mute;
    private JPanel rightPanel;
    private JPanel startPanel;
    private JPanel newGamePanel;
    private JPanel endGamePanel;
    private GamePanel gamePanel;
    public Image foto = null;
    public String shipeType;
    public boolean volume;

    public GUI() {
    	// Star Defender game another of it
        super("Danso's Shooting Game");
        this.setResizable(false);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(WINDOW_PREFERRED_WIDTH, WINDOW_PREFERRED_HEIGHT));
        this.setVisible(true);

    }

    public void paintBackground(Graphics g) {
        ImageIcon obj = new ImageIcon("img/foto.jpg");
        g.drawImage(obj.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
    }

    public void createGUI(String shipType) {
        this.shipeType = shipType;
        this.closeEndGamePanel();

        Timer timer = new Timer((int) 1, this);
        timer.start();

        this.setResizable(false);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(WINDOW_PREFERRED_WIDTH, WINDOW_PREFERRED_HEIGHT));

        gamePanel = new GamePanel(this.shipeType);
        gamePanel.setLayout(new GridLayout(750, 600));
        gamePanel.setPreferredSize(new Dimension(750, 600));

        this.setRightPanel();
        Container contPane = this.getContentPane();
        contPane.setLayout(new BorderLayout());
        contPane.add(rightPanel, BorderLayout.EAST);
        this.gamePanel.add(Box.createRigidArea(new Dimension(750, 0)));
        contPane.add(gamePanel, BorderLayout.WEST);

        this.pack();
        gamePanel.requestFocus();
    }

    private void setRightPanel() {

        this.rightPanel = new JPanel();
        this.rightPanel.setBackground(Color.LIGHT_GRAY);
        this.rightPanel.setPreferredSize(new Dimension(150, 600));
        this.rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        this.rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        this.playerNamePrefixLab = new JLabel("Player name:");
        this.playerNameLab = new JLabel(ViewController.getInstance().getPlayerName());

        this.playerScorePrefixLab = new JLabel("Points:");
        this.playerScoreLab = new JLabel(String.valueOf(ViewController.getInstance().getPlayerPoints()));

        this.playerLifePrefixLab = new JLabel("LifePoints:");
        this.playerLifeLab = new JLabel(String.valueOf(ViewController.getInstance().getPlayerLifePoints()));

        this.special = new JLabel("Special Shot:");
        this.specialPerc = new JLabel("");

        this.stopButton = new JButton("Stop Game");
        this.stopButton.addActionListener((ActionEvent e) -> {
            endGameButton(true);
            gamePanel.requestFocus();
        });

        this.mute = new JLabel();
        this.mute.setSize(new Dimension(25, 25));
        this.volume = true;
        this.setMute();
        this.mute.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                handleVolumeEvent();
            }
        });

        this.mute.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        this.playerNamePrefixLab.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        this.playerNameLab.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        this.playerScorePrefixLab.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        this.playerScoreLab.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        this.playerLifePrefixLab.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        this.playerLifeLab.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        this.special.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        this.specialPerc.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        this.stopButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

        this.rightPanel.add(this.mute);
        this.rightPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        this.rightPanel.add(this.playerNamePrefixLab);
        this.rightPanel.add(this.playerNameLab);
        this.rightPanel.add(Box.createVerticalGlue());
        this.rightPanel.add(this.playerScorePrefixLab);
        this.rightPanel.add(this.playerScoreLab);
        this.rightPanel.add(Box.createVerticalGlue());
        this.rightPanel.add(this.playerLifePrefixLab);
        this.rightPanel.add(this.playerLifeLab);
        this.rightPanel.add(Box.createVerticalGlue());
        this.rightPanel.add(this.special);
        this.rightPanel.add(this.specialPerc);
        this.rightPanel.add(Box.createVerticalGlue());
        this.rightPanel.add(this.stopButton);
        this.rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    public void endGameButton(boolean deadPlayer) {
        ViewController.getInstance().endGame(deadPlayer);
    }

    public void setMute() {
        BufferedImage img = null;
        try {
            if (volume) {
                img = ImageIO.read(new File("img/mute.png"));
                this.volume = false;
            } else {
                img = ImageIO.read(new File("img/unMute.png"));
                this.volume = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image dimg = img.getScaledInstance(this.mute.getWidth(), this.mute.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        this.mute.setIcon(imageIcon);
    }

    public void handleVolumeEvent() {
        this.setMute();
        this.gamePanel.setMuteVolume(volume);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        this.playerScoreLab.setText(String.valueOf(ViewController.getInstance().getPlayerPoints()));
        this.playerLifeLab.setText(String.valueOf(ViewController.getInstance().getPlayerLifePoints()));

        int perc = (ViewController.getInstance().getSpecialShotCounter() * 100) / 200;
        if (ViewController.getInstance().getSpecialShotCounter() < 200) {
            this.specialPerc.setForeground(Color.RED);
            this.specialPerc.setText(String.valueOf(perc) + "%");
        } else {
            this.specialPerc.setText(String.valueOf(100) + "%");
            this.specialPerc.setForeground(Color.GREEN);
        }
    }

    public void resetSpecialPerc() {
        this.specialPerc.setText(String.valueOf(0) + "%");
    }

    public void closeStartPanel() {
        this.startPanel.setVisible(false);
    }

    public void openNewGamePanel() {
        this.newGamePanel = new newGamePanel();
        this.getContentPane().add(newGamePanel);
        this.newGamePanel.setVisible(true);
        this.pack();
    }

    public void closeNewGamePanel() {
        this.newGamePanel.setVisible(false);
    }

    public void openGamePanel() {
        this.createGUI(GamePanel.shipType);
    }

    public void openStartPanel() {
        this.closeEndGamePanel();
        this.startPanel = new StartPanel();
        this.getContentPane().add(this.startPanel);
        this.startPanel.setVisible(true);
        this.pack();
    }

    public void openEndGamePanel(boolean deadPlayer) {
        this.gamePanel.timer.stop();
        this.gamePanel.setVisible(false);
        this.rightPanel.setVisible(false);
        this.endGamePanel = new EndGamePanel(deadPlayer);
        this.getContentPane().add(this.endGamePanel);
        this.endGamePanel.setVisible(true);
        this.pack();
    }

    public void closeEndGamePanel() {
        if (this.endGamePanel != null && this.endGamePanel.isVisible()) {
            this.endGamePanel.setVisible(false);
            this.gamePanel.sound.stopBackgroundMusic();
        }
    }

}
