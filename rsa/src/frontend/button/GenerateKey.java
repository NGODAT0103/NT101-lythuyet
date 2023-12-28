package frontend.button;

import backend.CustomRSA;
import backend.GlobalVar;

import javax.swing.*;

public class GenerateKey extends BaseButton{
    public GenerateKey(JTextArea publicKey,JTextArea privateKey){
        this.setBounds(0,0,160,30);
        this.setText("Generate Key");
        this.addActionListener(actionEvent -> {
            try {
                int keySize;
                try {
                    keySize = Integer.parseInt(JOptionPane.showInputDialog("Input your key size: "));
                } catch (NumberFormatException e){
                    return;
                }

                if(keySize<512){
                    JOptionPane.showMessageDialog(null,"The key size must be large (512 bytes).","Key size is invalid.",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                GlobalVar.rsa = new CustomRSA(keySize);
                publicKey.setText(GlobalVar.rsa.exportCert());
                privateKey.setText(GlobalVar.rsa.exportPrivateKey());

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });
    }
}
