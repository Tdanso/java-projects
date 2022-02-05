package View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class StartPanel extends JPanel{
    
    public JList list;
    public JLabel title;
    public JButton start;
    public JButton classifica;
    public JScrollPane scrollPane;
    public ImageIcon background;
    
    public StartPanel(){
        background = new ImageIcon("img/foto.jpg");
        this.setPanel();
    }
    
    public void setPanel(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.title = new JLabel("SH00TING GAME");
        this.title.setFont(new Font("Serif", Font.CENTER_BASELINE, 40));
        this.title.setForeground(Color.RED);
        
        this.start = new JButton("NEW GAME");
        this.start.setPreferredSize(new Dimension(100,40));
        this.start.setBackground(Color.green);
        this.start.addActionListener((ActionEvent e) -> {
            handleStartEvent();
        });
        
        this.classifica = new JButton("CHART");
        this.classifica.setPreferredSize(new Dimension(100,40));
        this.classifica.setBackground(Color.green);
        this.classifica.addActionListener((ActionEvent e) -> {
            handleClassificaEvent();
        });
        
        list = new JList(createListModel());
        list.setVisibleRowCount(4);
        list.setFixedCellHeight(30);
        
        this.scrollPane = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        this.scrollPane.setMaximumSize(new Dimension(350,300));
        
        this.title.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        this.start.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        this.scrollPane.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        this.classifica.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        
        this.add(Box.createRigidArea(new Dimension(0,10)));
        this.add(title, BorderLayout.CENTER);
        this.add(Box.createRigidArea(new Dimension(0,70)));
        this.add(start, BorderLayout.CENTER);
        this.add(Box.createRigidArea(new Dimension(0,80)));
        this.add(classifica, BorderLayout.CENTER);
        this.add(Box.createRigidArea(new Dimension(0,20)));
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(Box.createVerticalGlue());
        
        this.scrollPane.setVisible(false);
    }
    
    public DefaultListModel createListModel(){
        DefaultListModel listModel = new DefaultListModel();
        String absolutePath = "data/chart.txt";
        
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(absolutePath))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                listModel.addElement(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        
        return listModel;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.background.getImage(), 0, 0, null);
    }
    
    public void handleStartEvent(){
        View.getInstance().closeStartPanel();
        View.getInstance().openNewGamePanel();
    }
    
    public void handleClassificaEvent(){
        if(!this.scrollPane.isVisible()){
            this.scrollPane.setVisible(true);
            this.revalidate();
            this.repaint();
            
        }else{
            this.scrollPane.setVisible(false);
        }
        
    }
    
}

