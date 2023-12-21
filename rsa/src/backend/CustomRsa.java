package backend;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class CustomRsa {

    KeyPairGenerator keyPairGenerator;
    PublicKey publickey;
    PrivateKey privateKey;
    static String beginCertPem;
    static String endCertPem;
    static String beginPrivatePem;
    static String endPrivatePem;
    KeyPair keyPair;
    static {
        beginCertPem = "-----BEGIN CERTIFICATE-----\n";
        endCertPem = "\n-----END CERTIFICATE-----";
        beginPrivatePem = "-----BEGIN RSA PRIVATE KEY-----\n";
        endPrivatePem = "\n-----END RSA PRIVATE KEY-----\n";
    }
    public CustomRsa(boolean generateKey) throws NoSuchAlgorithmException {
        this.keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        this.keyPair = this.keyPairGenerator.generateKeyPair();
        this.publickey = this.keyPair.getPublic();
        this.privateKey = this.keyPair.getPrivate();
    }

    public CustomRsa(){

    }


    public void importPublicKey(String publickey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String[] elements = publickey.split("\n");
        byte[] publickeyByte = Base64.getDecoder().decode(elements[1]);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(publickeyByte);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        this.publickey =  keyFactory.generatePublic(spec);
    }

    public void importPrivateKey(String privatekey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String[] elements = privatekey.split("\n");
        byte[] privatekeyBytes = Base64.getDecoder().decode(elements[1]);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privatekeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        this.privateKey =  keyFactory.generatePrivate(spec);
    }

    public PublicKey getPublicKey(){
      return  this.publickey;
    }
    public PrivateKey getPrivateKey(){
        return this.privateKey;
    }
    public byte[] encryptBytes(byte[] plaintextBytes) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE,publickey);
        return encryptCipher.doFinal(plaintextBytes);
    }
    public byte[] decryptBytes(byte[] cipherBytes) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher decryptCIpher = Cipher.getInstance("RSA");
        decryptCIpher.init(Cipher.DECRYPT_MODE,privateKey);
        return decryptCIpher.doFinal(cipherBytes);

    }

    public String exportCert(){
        return beginCertPem +
                Base64.getEncoder().encodeToString(publickey.getEncoded()) +
                endCertPem;
    }
    public String exportToFile() throws IOException {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File( System.getProperty("user.dir").concat("/rsakey")));
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setDialogTitle("Select your path to save");

        fileChooser.setAcceptAllFileFilterUsed(false);

        fileChooser.showSaveDialog(null);





        File certFile = new File(fileChooser.getSelectedFile().toString().concat("\\cert.pem"));
        File privateFile = new File(fileChooser.getSelectedFile().toString().concat("\\key.pem"));
        certFile.createNewFile();
        privateFile.createNewFile();
        FileWriter writer = new FileWriter(certFile);
        writer.write(exportCert());
        writer.close();
        writer = new FileWriter(privateFile);
        writer.write(exportPrivateKey());
        writer.close();

        return fileChooser.getSelectedFile().toString();

    }

    public void loadFromFile() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        JFileChooser jFileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(null,"pem");
        jFileChooser.setFileFilter(filter);
        jFileChooser.setCurrentDirectory(new File(System.getProperty("user.dir").concat("/rsakey/")));
        jFileChooser.showOpenDialog(new JFrame().getParent());
        FileInputStream reader = new FileInputStream(jFileChooser.getSelectedFile());
        String data = new String(reader.readAllBytes(), StandardCharsets.UTF_8);
        reader.close();
        if (data.contains(beginCertPem)){
            importPublicKey(data);
        }
        else if(data.contains(beginPrivatePem)){
            importPrivateKey(data);
        }
    }

    public String exportPrivateKey(){
        return beginPrivatePem +
                Base64.getEncoder().encodeToString(privateKey.getEncoded()) +
                endPrivatePem;
    }



    public byte[] signData(String plaintext) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature privateSignature = Signature.getInstance("SHA256withRSA");
        privateSignature.initSign(privateKey);
        privateSignature.update(plaintext.getBytes(StandardCharsets.UTF_8));

        return privateSignature.sign();
    }

    public boolean verifyData(byte[] signature,String originalMessage) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature publicSignature = Signature.getInstance("SHA256withRSA");
        publicSignature.initVerify(publickey);
        publicSignature.update(originalMessage.getBytes(StandardCharsets.UTF_8));
       return publicSignature.verify(signature);
    }

}
