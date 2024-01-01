package frontend.form;
import backend.GlobalVar;
import frontend.button.GenerateKey;
import frontend.button.ImportKey;
import frontend.other.FeaturePanel;
import frontend.other.MouseTripClick;
import frontend.button.SaveButton;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class KeyInfoForm extends JFrame implements ActionListener {
   public static Font NORMALFONT;
    public static Font KETFONT;
    public static Font RADIOBUTTON;

   public JLabel publickeyLabel,privatekeyLabel;
    public JTextArea publickeyJTxtArea,privatekeyJTxtArea;

  public   JTextField qTextField,pTextField;
    JRadioButton randomRButton,manualRButton;
    static {
        NORMALFONT = new Font("Times New Roman",Font.BOLD,22);
        KETFONT =new Font("Times New Roman",Font.PLAIN,18);
        RADIOBUTTON  = new Font("Time New Roman",Font.PLAIN,15);
    }



    public KeyInfoForm(FeaturePanel featurePanel) throws NoSuchAlgorithmException, IOException {
        this.setLayout(null);
        this.setSize(720,560);
        this.setTitle("Your RSA key");
        this.setResizable(false);
        publickeyLabel = new JLabel();
        publickeyLabel.setText("Public key: ");
        publickeyLabel.setFont(NORMALFONT);
        publickeyLabel.setBounds(140,80,150,100);
      //  publickeyLabel.setBackground(Color.RED);
        privatekeyLabel = new JLabel();
        privatekeyLabel.setText("Private key: ");
        privatekeyLabel.setFont(NORMALFONT);
        privatekeyLabel.setBounds(500,80,150,100);
        privatekeyLabel.setBackground(Color.RED);
        publickeyJTxtArea = new JTextArea();
        publickeyJTxtArea.setFont(KETFONT);
        publickeyJTxtArea.setText("This is your public key");
        publickeyJTxtArea.setLineWrap(true);
        publickeyJTxtArea.setBounds(0,150,340,350);
        publickeyJTxtArea.addMouseListener(new MouseTripClick(publickeyJTxtArea));


      //  publickeyJTxtArea.setBackground(Color.red);
        privatekeyJTxtArea = new JTextArea();
        privatekeyJTxtArea.setFont(KETFONT);
        privatekeyJTxtArea.setText("This is your Private key");
        privatekeyJTxtArea.setLineWrap(true);
        privatekeyJTxtArea.setBounds(360,150,340,350);
        privatekeyJTxtArea.setAutoscrolls(true);
        privatekeyJTxtArea.addMouseListener(new MouseTripClick(privatekeyJTxtArea));
       // privatekeyJTxtArea.setBackground(Color.blue);

        publickeyJTxtArea.setEditable(false);
        privatekeyJTxtArea.setEditable(false);


        JLabel pLabel = new JLabel();
        pLabel.setFont(NORMALFONT);
        pLabel.setText("p: ");
        pLabel.setBounds(0,40,30,30);
        pTextField = new JTextField();
        pTextField.setFont(NORMALFONT);
        pTextField.setBounds(30,45,400,30);


        JLabel qlaBel = new JLabel();
        qlaBel.setFont(NORMALFONT);
        qlaBel.setText("q: ");
        qlaBel.setBounds(0,80,30,30);
        qTextField = new JTextField();
        qTextField.setFont(NORMALFONT);
        qTextField.setBounds(30,85,400,30);

        if(GlobalVar.rsa.hasPublicKey&& GlobalVar.rsa.hasPrivateKey)
        {
            publickeyJTxtArea.setText(GlobalVar.rsa.exportCert());
            privatekeyJTxtArea.setText(GlobalVar.rsa.exportPrivateKey());
            qTextField.setText(GlobalVar.rsa.getQ());
            pTextField.setText(GlobalVar.rsa.getP());
        }
        else if(GlobalVar.rsa.hasPrivateKey) {
            privatekeyJTxtArea.setText(GlobalVar.rsa.exportPrivateKey());
            JOptionPane.showMessageDialog(null,"Missing your public key","Warning",JOptionPane.WARNING_MESSAGE);
        }
        else if(GlobalVar.rsa.hasPublicKey){
            publickeyJTxtArea.setText(GlobalVar.rsa.exportCert());
            JOptionPane.showMessageDialog(null,"Missing your private key","Warning",JOptionPane.WARNING_MESSAGE);
        }


        randomRButton = new JRadioButton();
        randomRButton.setFont(RADIOBUTTON);
        randomRButton.setText("Random");
        randomRButton.setBounds(450,0,100,50);
        randomRButton.addActionListener(this);

        manualRButton = new JRadioButton();
        manualRButton.setFont(RADIOBUTTON);
        manualRButton.setText("Manual");
        manualRButton.setBounds(570,0,100,50);
        manualRButton.addActionListener(this);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(randomRButton);
        buttonGroup.add(manualRButton);

        randomRButton.setSelected(true);




        this.add(randomRButton);
        this.add(manualRButton);
        this.add(publickeyJTxtArea);
        this.add(privatekeyLabel);
        this.add(privatekeyJTxtArea);
        this.add(publickeyLabel);
        this.add(new SaveButton());
        this.add(new GenerateKey(this,featurePanel));
        this.add(new ImportKey(publickeyJTxtArea,privatekeyJTxtArea));
        this.add(pLabel);
        this.add(qlaBel);
        this.add(pTextField);
        this.add(qTextField);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource()==randomRButton)
            GlobalVar.mode = GlobalVar.RANDOM;
        else if(actionEvent.getSource()==manualRButton)
            GlobalVar.mode= GlobalVar.MANUAL;
    }
}
