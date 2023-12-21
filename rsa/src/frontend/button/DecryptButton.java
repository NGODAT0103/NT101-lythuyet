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

public class DecryptButton extends BaseButton{
    public DecryptButton(JTextArea input,JTextArea output){
        this.setText("Decrypt");
        this.setFont(new Font("Times New Roman",Font.BOLD,15));
        this.setBounds(340,200,100,40);

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (input.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter data", "Field empty", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    byte[] decryptData = GlobalVar.rsa.decryptBytes(Base64.getDecoder().decode(input.getText()));
                    output.setText(new String(decryptData,StandardCharsets.UTF_8));
                } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException |
                         IllegalBlockSizeException | BadPaddingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
