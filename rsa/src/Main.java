import backend.CustomRSA;
import backend.OAEP;
import frontend.form.MainUI;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

public class Main {
    public static void main(String[] args) throws Exception {
          new MainUI();
//        CustomRSA customRSA = new CustomRSA();
//        customRSA.loadFromFile();
//        customRSA.loadFromFile();
//        String plainText = "This is your Input";
//        byte[] signatureBytes =  customRSA.signData(plainText);
//
//        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir").concat("/signature")));
//        fileChooser.setDialogTitle("Select your path to save signature");
//        fileChooser.showSaveDialog(null);
//
//        if(fileChooser.getSelectedFile() == null)
//            return;
//
//        File file = new File(fileChooser.getSelectedFile().toString());
//        FileOutputStream writer = new FileOutputStream(file);
//        writer.write(signatureBytes);
//        writer.close();
//
//
//        JFileChooser jFileChooser = new JFileChooser();
//        jFileChooser.setCurrentDirectory(new File(System.getProperty("user.dir").concat("/signature")));
//        jFileChooser.setDialogTitle("Select your signature");
//        jFileChooser.setFileFilter(new FileNameExtensionFilter(null,"signature"));
//        jFileChooser.setDialogType(JFileChooser.FILES_ONLY);
//        jFileChooser.showOpenDialog(null);
//        FileInputStream reader = new FileInputStream(jFileChooser.getSelectedFile());
//        byte[] signature = reader.readAllBytes();
//        reader.close();
//        boolean valid = customRSA.verifyData(signature,plainText);
//        System.out.println(valid);



//        String plainText = "Hello world";
//       byte[] paddedPlainText =  OAEP.pad(plainText.getBytes(StandardCharsets.UTF_8),2048);
//       byte[] unPadPlainText = OAEP.unpad(paddedPlainText);
//        assert unPadPlainText != null;
//        String result = new String(unPadPlainText,StandardCharsets.UTF_8);
//        int stop = 0 ;
    }
}