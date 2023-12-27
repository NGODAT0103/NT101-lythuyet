package frontend.form;

import backend.CustomRSA;
import backend.GlobalVar;
import frontend.button.KeyInfoButton;
import frontend.other.FeaturePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class MainUI extends JFrame implements ActionListener {
    static Font titleFont;
    CustomRSA rsa;
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("Got click");
    }
    static {
        titleFont = new Font("Times New Roman",Font.BOLD,35);
    }


    public MainUI() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
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
        this.add(new KeyInfoButton());
        this.add(new FeaturePanel());
        this.setVisible(true);
        GlobalVar.rsa = new CustomRSA();
    }

}
