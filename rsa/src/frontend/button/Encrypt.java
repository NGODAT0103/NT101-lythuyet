package frontend.button;
import backend.GlobalVar;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.security.GuardedObject;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Encrypt extends BaseButton {

    public Encrypt(JTextArea input, JTextArea output){
        this.setText("Encrypt");
        this.setFont(featureFont);
        this.setBounds(240,200,100,40);

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (GlobalVar.rsa==null)
                {
                    JOptionPane.showMessageDialog(null
                            ,"There is not RSA key, please Import or generate.",
                            "Key not found",
                            JOptionPane.ERROR_MESSAGE);

                    return;
                }

                if (!GlobalVar.rsa.hasPublicKey)
                {
                    JOptionPane.showMessageDialog(null,"Missing your public key","Warning",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                else if(!GlobalVar.rsa.hasPrivateKey){
                    JOptionPane.showMessageDialog(null,"Missing your private key","Warning",JOptionPane.WARNING_MESSAGE);
                    return;
                }


                if (input.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter data", "Field empty", JOptionPane.WARNING_MESSAGE);
                    return;
                }


                System.out.println("Length input: "+input.getText().length());
                if(input.getText().length()>= GlobalVar.rsa.getModulusBig().bitLength()/8)
                {
                    JOptionPane.showMessageDialog(null, "Please enter input field value < "+ GlobalVar.rsa.keySize()+" bytes", "Input max size key", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    output.setText(GlobalVar.rsa.encrypt(input.getText()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }


            }
        });
    }
}
