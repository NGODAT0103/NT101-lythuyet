package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppUI extends JFrame implements ActionListener {
    static Font titleFont;


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("Got click");
    }
    static {
        titleFont = new Font("Times New Roman",Font.BOLD,35);
    }


    public AppUI(){
        this.setSize(720,560);
        this.setTitle("RSA tool");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        ImageIcon imageicon = new ImageIcon("encrypt.png");
        this.setIconImage(imageicon.getImage());
        this.setLayout(null);

        JLabel welcomeLabel = new JLabel();
        welcomeLabel.setText("Welcome to RSA project tool");
        welcomeLabel.setFont(titleFont);
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setVerticalAlignment(JLabel.CENTER);
        welcomeLabel.setBounds(0,0,720,50);
        welcomeLabel.setOpaque(true);

        this.add(welcomeLabel);
        this.add(new GenrateJbutton());

        this.setVisible(true);

    }

}
