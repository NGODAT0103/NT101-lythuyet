package backend;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

public class CustomRSA {
	static String beginCertPem;
	static String endCertPem;
	static String beginPrivatePem;
	static String endPrivatePem;
	public boolean hasPublicKey,hasPrivateKey;
	
	private final static BigInteger one = new BigInteger("1");
	static {
		beginCertPem = "-----BEGIN CERTIFICATE-----\n";
		endCertPem = "\n-----END CERTIFICATE-----";
		beginPrivatePem = "-----BEGIN RSA PRIVATE KEY-----\n";
		endPrivatePem = "\n-----END RSA PRIVATE KEY-----\n";
	}


	private BigInteger modulusBig;
	
	private BigInteger publicKeyBig;
	private BigInteger privateKeyBig;
	
	public CustomRSA(int bitLength) throws Exception {
        do {
            BigInteger p = BigInteger.probablePrime(bitLength / 2, new Random());
            BigInteger q = BigInteger.probablePrime(bitLength / 2, new Random());
            BigInteger phi = (p.subtract(one)).multiply(q.subtract(one));
            this.modulusBig = p.multiply(q);
            this.publicKeyBig = new BigInteger("65537");
            this.privateKeyBig = publicKeyBig.modInverse(phi);

        } while ((modulusBig.bitLength() / 8) % 2 != 0);


		hasPublicKey = true;
		hasPrivateKey = true;
		System.out.println(getModulusBig().bitLength()/8);
		
	}
	public CustomRSA(){
		hasPublicKey = false;
		this.publicKeyBig = new BigInteger("65537");
		hasPrivateKey  = false;
	}

	public BigInteger encrypt(BigInteger plainText){
		return plainText.modPow(publicKeyBig, modulusBig);
	}
	
	public BigInteger decrypt(BigInteger cipherText){
		return cipherText.modPow(privateKeyBig, modulusBig);
	}
	
	public String encrypt(String plainText) throws Exception {
		while (true){
			byte[] paddedPlainTextBytes = OAEP.pad(plainText.getBytes(StandardCharsets.UTF_8), keySize());
			assert paddedPlainTextBytes != null;
			BigInteger paddedCipherTextBig = new BigInteger(paddedPlainTextBytes);
			byte[] encrypted = encrypt(paddedCipherTextBig).toByteArray();
			if (Arrays.equals(paddedPlainTextBytes, decrypt(new BigInteger(encrypted)).toByteArray()))
				return Base64.getEncoder().encodeToString(encrypted);
		}

	}
	public int getLimitBody(){
		return keySize()-32*2-1;
	}

	public String decrypt(String cipherTextEncodedB64) throws Exception {
		byte[] cipherTextBytes = Base64.getDecoder().decode(cipherTextEncodedB64);
		BigInteger plainTextBig = decrypt(new BigInteger(cipherTextBytes));
		byte[] unpadPlainText = OAEP.unpad(plainTextBig.toByteArray());
        assert unpadPlainText != null;
        return new String(unpadPlainText, StandardCharsets.UTF_8);
	}
	
	public BigInteger getModulusBig(){
		return this.modulusBig;
	}
	

	public String exportCert() throws IOException {
		return beginCertPem +
				Base64.getEncoder().encodeToString(modulusBig.toByteArray())+
				endCertPem;
	}
	public String exportPrivateKey(){
		return beginPrivatePem +
				Base64.getEncoder().encodeToString(privateKeyBig.toByteArray()) +
				endPrivatePem;
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
	public void importPublicKey(String publickey)  {
		String[] elements = publickey.split("\n");
		 byte[] publicKeyBytes = Base64.getDecoder().decode(elements[1]);
		 modulusBig = new BigInteger(publicKeyBytes);
		 hasPublicKey = true;
	}
	public void importPrivateKey(String privatekey){
		String[] elements = privatekey.split("\n");
		byte[] privateKeyBytes = Base64.getDecoder().decode(elements[1]);
		privateKeyBig = new BigInteger(privateKeyBytes);
		hasPrivateKey = true;
	}

	public void loadFromFile() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		JFileChooser jFileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(null,"pem");
		jFileChooser.setFileFilter(filter);
		jFileChooser.setCurrentDirectory(new File(System.getProperty("user.dir").concat("/rsakey/")));
		jFileChooser.showOpenDialog(new JFrame().getParent());

		if (jFileChooser.getSelectedFile() == null)
			return;

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

	public int keySize(){
		return getModulusBig().bitLength()/8;
	}

	public byte[] signData(String plainText) throws Exception {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		byte[] hashValue = messageDigest.digest(plainText.getBytes(StandardCharsets.UTF_8));
		System.out.println(Arrays.toString(hashValue));
		while (true){
			byte[] paddedHashValue = OAEP.pad(hashValue, keySize());
			assert paddedHashValue != null;
			BigInteger paddedHashValueBig = new BigInteger(paddedHashValue);
			byte[] signature = decrypt(paddedHashValueBig).toByteArray();
			if (Arrays.equals(paddedHashValue, encrypt(new BigInteger(signature)).toByteArray()))
				return signature;
		}
	}

	public boolean verifyData(byte[] signature,String originalText) throws Exception {
		byte[] paddedHashValueFromSignature = encrypt(new BigInteger(signature)).toByteArray();
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		byte[] unpadHashValueFromSignature = OAEP.unpad(paddedHashValueFromSignature);
		System.out.println(Arrays.toString(unpadHashValueFromSignature));
		byte[] hashValueFromOriginal = messageDigest.digest(originalText.getBytes(StandardCharsets.UTF_8));
		return Arrays.equals(hashValueFromOriginal, unpadHashValueFromSignature);
	}

}
