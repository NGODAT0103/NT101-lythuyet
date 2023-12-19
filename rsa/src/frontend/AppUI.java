package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppUI extends JFrame implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("Got click");
    }


    public AppUI(){
        this.setSize(720,560);
        this.setTitle("RSA tool");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        ImageIcon imageicon = new ImageIcon("encrypt.png");
        this.setIconImage(imageicon.getImage());
        this.setLayout(new BorderLayout());


        JPanel titleJpanel = new JPanel();
        titleJpanel.setBackground(Color.blue);
        JPanel statusJpanel = new JPanel();
        statusJpanel.setBackground(Color.RED);

        titleJpanel.setPreferredSize(new Dimension(720,140));
        JLabel label = new JLabel();
        label.setText("This is label");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        this.add(label,BorderLayout.CENTER);

        this.add(titleJpanel,BorderLayout.NORTH);
        this.add(statusJpanel,BorderLayout.WEST);
        this.setVisible(true);

    }

}
