package frontend;

import backend.CustomRsa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class KeyInfo extends JFrame  {
    static Font NORMALFONT;
    static Font KETFONT;

    JLabel publickeyLabel,privatekeyLabel;
    JTextArea publickeyJTxtArea,privatekeyJTxtArea;
    JButton saveJbutton;
    static {
        NORMALFONT = new Font("Times New Roman",Font.BOLD,22);
        KETFONT =new Font("Times New Roman",Font.PLAIN,18);
    }



    KeyInfo() throws NoSuchAlgorithmException {
        this.setLayout(null);
        this.setSize(720,560);
        this.setTitle("Your RSA key");
        this.setResizable(false);
        publickeyLabel = new JLabel();
        publickeyLabel.setText("Public key: ");
        publickeyLabel.setFont(NORMALFONT);
        publickeyLabel.setBounds(140,20,150,100);
      //  publickeyLabel.setBackground(Color.RED);
        publickeyLabel.setPreferredSize(new Dimension(150,100));
        privatekeyLabel = new JLabel();
        privatekeyLabel.setText("Private key: ");
        privatekeyLabel.setFont(NORMALFONT);
        privatekeyLabel.setBounds(500,20,150,100);
        privatekeyLabel.setBackground(Color.RED);
        privatekeyLabel.setPreferredSize(new Dimension(150,100));
        publickeyJTxtArea = new JTextArea();
        publickeyJTxtArea.setFont(KETFONT);
        publickeyJTxtArea.setPreferredSize(new Dimension(340,400));
        publickeyJTxtArea.setText("This is your public key");
        publickeyJTxtArea.setLineWrap(true);
        publickeyJTxtArea.setBounds(0,100,340,400);

        publickeyJTxtArea.addMouseListener(new MouseTripClick(publickeyJTxtArea));







      //  publickeyJTxtArea.setBackground(Color.red);
        privatekeyJTxtArea = new JTextArea();
        privatekeyJTxtArea.setFont(KETFONT);
        privatekeyJTxtArea.setPreferredSize(new Dimension(340,400));
        privatekeyJTxtArea.setText("This is your Private key");
        privatekeyJTxtArea.setLineWrap(true);
        privatekeyJTxtArea.setBounds(360,100,340,400);
        privatekeyJTxtArea.setAutoscrolls(true);
        privatekeyJTxtArea.addMouseListener(new MouseTripClick(privatekeyJTxtArea));
       // privatekeyJTxtArea.setBackground(Color.blue);


        CustomRsa rsa = new CustomRsa(true);
        saveJbutton = new JButton();
        saveJbutton.setText("Save to ");
        saveJbutton.setFont(NORMALFONT);
        saveJbutton.setBounds(250,0,175,50);
        saveJbutton.setFocusable(false);
        saveJbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    JOptionPane.showMessageDialog(null,"Key stored at: \n".concat(rsa.exportToFile()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        publickeyJTxtArea.setText(rsa.exportCert());
        privatekeyJTxtArea.setText(rsa.exportPrivateKey());
        privatekeyLabel.invalidate();

        publickeyJTxtArea.setEditable(false);
        privatekeyJTxtArea.setEditable(false);


        this.add(publickeyJTxtArea);
        this.add(privatekeyLabel);
        this.add(privatekeyJTxtArea);
        this.add(publickeyLabel);
        this.add(saveJbutton);
        this.setVisible(true);





    }

}
