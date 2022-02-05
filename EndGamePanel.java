package View;

import Controller.ViewController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EndGamePanel extends JPanel{

    public JButton restart;
    public JButton exit;
    public JLabel title;
    public ImageIcon background;
    public boolean deadPlayer;

    public EndGamePanel(boolean deadPlayer) {
        this.deadPlayer = deadPlayer;
        background = new ImageIcon("img/foto.jpg");
        this.setPanel();
    }

    public void setPanel() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        if(this.deadPlayer){
            this.title = new JLabel("You are dead! Exit or Retry?");
            this.title.setFont(new Font("Serif", Font.CENTER_BASELINE, 40));
            this.title.setForeground(Color.RED);
            this.restart = new JButton("RETRY");
        }else{
            this.title = new JLabel("YOU WON!");
            this.title.setFont(new Font("Serif", Font.CENTER_BASELINE, 40));
            this.title.setForeground(Color.GREEN);
            this.restart = new JButton("RESTART");
        }

        this.restart.setMaximumSize(new Dimension(120, 40));
        this.restart.addActionListener((ActionEvent e) -> {
            handleRestartEvent();
        });
        
        this.exit = new JButton("EXIT");
        this.exit.setMaximumSize(new Dimension(120, 40));
        this.exit.addActionListener((ActionEvent e) -> {
            handleExitEvent();
        });

        this.title.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        this.restart.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        this.exit.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(title, BorderLayout.CENTER);
        this.add(Box.createRigidArea(new Dimension(0, 100)));
        this.add(restart, BorderLayout.CENTER);
        this.add(Box.createRigidArea(new Dimension(0, 80)));
        this.add(exit, BorderLayout.CENTER);
        this.add(Box.createRigidArea(new Dimension(0, 80)));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.background.getImage(), 0, 0, null);
    }

    public void handleRestartEvent() {
            View.getInstance().openMainGUI(GamePanel.shipType);
    }
    
    public void handleExitEvent() {
            View.getInstance().openStartPanel();
    }

}
