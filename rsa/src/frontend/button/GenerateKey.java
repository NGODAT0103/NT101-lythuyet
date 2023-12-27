package frontend.button;

import backend.CustomRSA;
import backend.GlobalVar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class GenerateKey extends BaseButton{
    public GenerateKey(JTextArea publicKey,JTextArea privateKey){
        this.setBounds(0,0,160,30);
        this.setText("Generate Key");
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    GlobalVar.rsa = new CustomRSA(2048);
                    publicKey.setText(GlobalVar.rsa.exportCert());
                    privateKey.setText(GlobalVar.rsa.exportPrivateKey());

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }
}
