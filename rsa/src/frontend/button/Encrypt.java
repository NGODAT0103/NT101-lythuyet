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

                try {
                    byte[] encryptData = GlobalVar.rsa.encryptBytes(input.getText().getBytes(StandardCharsets.UTF_8));
                    if (encryptData == null)
                        return;
                    output.setText(Base64.getEncoder().encodeToString(encryptData));


                } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException |
                         IllegalBlockSizeException | BadPaddingException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }
}
