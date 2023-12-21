package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;

public class GenrateJbutton extends JButton implements ActionListener {
    static Font normalFont;
    static Font keyFont;
    KeyInfo keyInfo = null;

    static {
        normalFont = new Font("Times New Roman",Font.BOLD,20);
        keyFont =new Font("Times New Roman",Font.PLAIN,10);
    }

    GenrateJbutton(){
        this.setBounds(0,75,150,50);
        this.setText("Generate key");
        this.setFocusable(false);
        this.setHorizontalAlignment(JButton.CENTER);
        this.setVerticalAlignment(JButton.CENTER);
        this.setFont(normalFont);
        this.setPreferredSize(new Dimension(75,75));
        this.setVisible(true);
        this.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (keyInfo==null) {
            try {
                keyInfo = new KeyInfo();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }

        if (!keyInfo.isShowing()){
            keyInfo.dispose();
            try {
                keyInfo = new KeyInfo();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
