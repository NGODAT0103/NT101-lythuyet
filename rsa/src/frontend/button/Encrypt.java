package frontend.button;
import backend.GlobalVar;
import javax.swing.*;
import java.nio.charset.StandardCharsets;
public class Encrypt extends BaseButton {

    public Encrypt(JTextArea input, JTextArea output){
        this.setText("Encrypt");
        this.setFont(featureFont);
        this.setBounds(240,200,100,40);

        this.addActionListener(actionEvent -> {
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

            int messageBytesLength = input.getText().getBytes(StandardCharsets.UTF_8).length;

            System.out.println("Length input: "+messageBytesLength);
            if(messageBytesLength>= GlobalVar.rsa.getLimitBody())
            {
                JOptionPane.showMessageDialog(null, "Please enter input field value < "+ GlobalVar.rsa.getLimitBody()+" bytes", "Input max size key", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                output.setText(GlobalVar.rsa.encrypt(input.getText()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        });
    }
}
