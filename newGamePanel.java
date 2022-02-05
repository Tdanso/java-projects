package View;

import Controller.ViewController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class newGamePanel extends JPanel {

    public JButton start;
    public JLabel title;
    public JLabel title2;
    public JLabel errorName;
    public JLabel player1;
    public JLabel player2;
    public JLabel player3;
    public JTextField name;
    public ImageIcon background;

    public newGamePanel() {
        background = new ImageIcon("img/foto.jpg");
        this.setPanel();
    }

    public void setPanel() {
        this.setPlayer1();
        this.setPlayer2();
        this.setPlayer3();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.title = new JLabel("Type the player's name: ");
        this.title.setFont(new Font("Serif", Font.CENTER_BASELINE, 40));
        this.title.setForeground(Color.GREEN);

        this.title2 = new JLabel("Choose the ship of the player: ");
        this.title2.setFont(new Font("Serif", Font.BOLD, 30));
        this.title2.setForeground(Color.GREEN);
        
        this.errorName = new JLabel("INSERT NAME!");
        this.errorName.setFont(new Font("Serif", Font.CENTER_BASELINE, 25));
        this.errorName.setForeground(Color.RED);

        this.name = new JTextField("INSERT NAME!", 20);
        this.name.setMaximumSize(new Dimension(200, 30));
        this.name.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if(name.getText().equals("INSERT NAME!")){
                    name.setText("");
                }
            }
        });

        this.title.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        this.title2.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        this.name.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        this.player1.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        this.player2.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        this.player3.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        this.errorName.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(title, BorderLayout.CENTER);
        this.add(Box.createRigidArea(new Dimension(0, 50)));
        this.add(name, BorderLayout.CENTER);
        this.add(Box.createRigidArea(new Dimension(0, 50)));
        this.add(title2, BorderLayout.CENTER);
        this.add(Box.createRigidArea(new Dimension(0, 30)));
        this.add(player1, BorderLayout.CENTER);
        this.add(Box.createRigidArea(new Dimension(0, 30)));
        this.add(player2, BorderLayout.CENTER);
        this.add(Box.createRigidArea(new Dimension(0, 30)));
        this.add(player3, BorderLayout.CENTER);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(errorName, BorderLayout.CENTER);
        this.add(Box.createRigidArea(new Dimension(0, 1)));
        
        this.errorName.setVisible(false);

    }

    public void setPlayer1() {
        this.player1 = new JLabel();
        this.player1.setSize(new Dimension(70, 70));

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("img/player.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        Image dimg = img.getScaledInstance(this.player1.getWidth(), this.player1.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        this.player1.setIcon(imageIcon);

        this.player1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                handleStartEvent("img/player.png");
            }
        });
    }

    public void setPlayer2() {
        this.player2 = new JLabel();
        this.player2.setSize(new Dimension(70, 70));

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("img/player2.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        Image dimg = img.getScaledInstance(this.player2.getWidth(), this.player2.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        this.player2.setIcon(imageIcon);

        this.player2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                handleStartEvent("img/player2.png");
            }
        });
    }

    public void setPlayer3() {
        this.player3 = new JLabel();
        this.player3.setSize(new Dimension(70, 70));

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("img/player3.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        Image dimg = img.getScaledInstance(this.player3.getWidth(), this.player3.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        this.player3.setIcon(imageIcon);

        this.player3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                handleStartEvent("img/player3.png");
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.background.getImage(), 0, 0, null);
    }

    public void handleStartEvent(String shipType) {
        if (!this.name.getText().equals("") && !this.name.getText().equals("INSERT NAME!")) {
            ViewController.getInstance().setPlayerName(this.name.getText());
            View.getInstance().closeNewGamePanel();
            View.getInstance().openMainGUI(shipType);
        }else{
            this.errorName.setVisible(true);
            repaint();
        }

    }

}
