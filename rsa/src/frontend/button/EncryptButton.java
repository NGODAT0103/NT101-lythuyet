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

public class EncryptButton extends BaseButton {

    public EncryptButton(JTextArea input,JTextArea output){
        this.setText("Encrypt");
        this.setFont(new Font("Times New Roman",Font.BOLD,15));
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

                if (input.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter data", "Field empty", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    byte[] encryptData = GlobalVar.rsa.encryptBytes(input.getText().getBytes(StandardCharsets.UTF_8));
                    output.setText(Base64.getEncoder().encodeToString(encryptData));


                } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException |
                         IllegalBlockSizeException | BadPaddingException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }
}
