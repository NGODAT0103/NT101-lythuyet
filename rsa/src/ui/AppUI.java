package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppUI extends JFrame implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        Object test = actionEvent.getSource();
        System.out.println("Got click");
    }

    private final JPanel firstPanel;
    JButton testButton;


    public AppUI(){
        this.setSize(720,560);
        this.setTitle("RSA tool");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        ImageIcon imageicon = new ImageIcon("encrypt.png");
        this.setIconImage(imageicon.getImage());
        this.setLayout(null);
        this.firstPanel = new JPanel();
        this.firstPanel.setBackground(Color.BLUE);
        this.firstPanel.setBounds(0,0,200,200);
        this.firstPanel.setLayout( new BorderLayout());



        testButton = new JButton();
        testButton.setText("This is button");
        testButton.setSize(100,100);
        testButton.setVerticalAlignment(JButton.CENTER);
        testButton.setHorizontalAlignment(JButton.CENTER);
        testButton.addActionListener(this);

        this.firstPanel.add(testButton);

        this.add(firstPanel);
        this.setVisible(true);


    }

}
