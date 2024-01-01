package frontend.button;

import backend.CustomRSA;
import backend.GlobalVar;
import frontend.form.KeyInfoForm;
import frontend.other.FeaturePanel;

import javax.swing.*;
import java.io.IOException;

public class GenerateKey extends BaseButton{
    public GenerateKey(KeyInfoForm keyInfoForm, FeaturePanel featurePanel){

        this.setBounds(0,0,160,30);
        this.setText("Generate Key");
        this.addActionListener(actionEvent -> {
            System.out.println(GlobalVar.mode);
            if(GlobalVar.mode == GlobalVar.RANDOM){
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


                    keyInfoForm.publickeyJTxtArea.setText(GlobalVar.rsa.exportCert());
                    keyInfoForm.privatekeyJTxtArea.setText(GlobalVar.rsa.exportPrivateKey());
                    keyInfoForm.qTextField.setText(GlobalVar.rsa.getQ());
                    keyInfoForm.pTextField.setText(GlobalVar.rsa.getP());


                    String messageLength = String.valueOf(GlobalVar.rsa.getLimitBody()-1);
                    featurePanel.maxEncryptLabel.setText("Can encrypt: ".concat(messageLength).concat(" bytes"));

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            else if (GlobalVar.mode==GlobalVar.MANUAL){
                GlobalVar.rsa = new CustomRSA(keyInfoForm.pTextField.getText(),keyInfoForm.qTextField.getText());
                if (!GlobalVar.rsa.isOk)
                    return;

                try {
                    keyInfoForm.publickeyJTxtArea.setText(GlobalVar.rsa.exportCert());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                keyInfoForm.privatekeyJTxtArea.setText(GlobalVar.rsa.exportPrivateKey());            }

        });
    }
}
