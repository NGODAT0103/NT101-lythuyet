package frontend.button;

import backend.GlobalVar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

public class SignData extends BaseButton{
    public SignData(JTextArea input){
        this.setText("Sign data");
        this.setBounds(140,200,100,40);
        this.setFont(featureFont);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {

                 if(!GlobalVar.rsa.hasPrivateKey){
                     JOptionPane.showMessageDialog(null,"Missing private key, can't sign data","Missing private key",JOptionPane.WARNING_MESSAGE);
                     return;
                 }

                 byte[] signature =  GlobalVar.rsa.signData(input.getText());
                 JFileChooser fileChooser = new JFileChooser();
                 fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir").concat("/signature")));
                 fileChooser.setDialogTitle("Select your path to save signature");
                 fileChooser.showSaveDialog(null);

                 if(fileChooser.getSelectedFile() == null)
                     return;

                 File file = new File(fileChooser.getSelectedFile().toString());
                 FileOutputStream writer = new FileOutputStream(file);
                 writer.write(signature);
                 writer.close();
                 JOptionPane.showMessageDialog(null,"Signature stored at: \n".concat(fileChooser.getSelectedFile().toString()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
