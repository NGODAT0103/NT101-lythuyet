package frontend.form;
import backend.GlobalVar;
import frontend.button.GenerateKey;
import frontend.button.ImportKey;
import frontend.other.MouseTripClick;
import frontend.button.SaveButton;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class KeyInfoForm extends JFrame  {
   public static Font NORMALFONT;
    public static Font KETFONT;

    JLabel publickeyLabel,privatekeyLabel;
    JTextArea publickeyJTxtArea,privatekeyJTxtArea;
    static {
        NORMALFONT = new Font("Times New Roman",Font.BOLD,22);
        KETFONT =new Font("Times New Roman",Font.PLAIN,18);
    }



    public KeyInfoForm() throws NoSuchAlgorithmException, IOException {
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

        publickeyJTxtArea.setEditable(false);
        privatekeyJTxtArea.setEditable(false);


        if(GlobalVar.rsa.hasPublicKey&& GlobalVar.rsa.hasPrivateKey)
        {
            publickeyJTxtArea.setText(GlobalVar.rsa.exportCert());
            privatekeyJTxtArea.setText(GlobalVar.rsa.exportPrivateKey());
        }
        else if(GlobalVar.rsa.hasPrivateKey) {
            privatekeyJTxtArea.setText(GlobalVar.rsa.exportPrivateKey());
            JOptionPane.showMessageDialog(null,"Missing your public key","Warning",JOptionPane.WARNING_MESSAGE);
        }
        else if(GlobalVar.rsa.hasPublicKey){
            publickeyJTxtArea.setText(GlobalVar.rsa.exportCert());
            JOptionPane.showMessageDialog(null,"Missing your private key","Warning",JOptionPane.WARNING_MESSAGE);
        }
        this.add(publickeyJTxtArea);
        this.add(privatekeyLabel);
        this.add(privatekeyJTxtArea);
        this.add(publickeyLabel);
        this.add(new SaveButton());
        this.add(new GenerateKey(publickeyJTxtArea,privatekeyJTxtArea));
        this.add(new ImportKey(publickeyJTxtArea,privatekeyJTxtArea));

        this.setVisible(true);
    }

}
