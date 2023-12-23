package frontend.button;

import backend.GlobalVar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class ImportKey extends BaseButton{
    public ImportKey(JTextArea publickey,JTextArea privatekey) {
        this.setText("Import key");
        this.setBounds(290,0,150,30);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    GlobalVar.rsa.loadFromFile();
                    if (GlobalVar.rsa.hasPublicKey)
                        publickey.setText(GlobalVar.rsa.exportCert());
                    if(GlobalVar.rsa.hasPrivateKey)
                        privatekey.setText(GlobalVar.rsa.exportPrivateKey());
                } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }
}
