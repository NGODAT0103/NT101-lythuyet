package backend;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Random;

public class CustomRSA {
	static String beginCertPem;
	static String endCertPem;
	static String beginPrivatePem;
	static String endPrivatePem;
	public boolean hasPublicKey,hasPrivateKey;
	
	private final static SecureRandom random = new SecureRandom();
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
	
	public CustomRSA(int bitLength){
		BigInteger p = BigInteger.probablePrime(bitLength / 2, new Random());
		BigInteger q = BigInteger.probablePrime(bitLength / 2, new Random());
		BigInteger phi = (p.subtract(one)).multiply(q.subtract(one));
		
		this.modulusBig = p.multiply(q);
		this.publicKeyBig = new BigInteger("65537");
		this.privateKeyBig = publicKeyBig.modInverse(phi);
		hasPublicKey = true;
		hasPrivateKey = true;
		
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
	
	public String encrypt(String plainText){

		byte[] plainTextBytes = plainText.getBytes(StandardCharsets.UTF_8);
		BigInteger cipherText = encrypt(new BigInteger(plainTextBytes));
		byte[] cipherTextBytes = cipherText.toByteArray();
		return Base64.getEncoder().encodeToString(cipherTextBytes);
	}

	public String decrypt(String cipherTextEncodedB64){
		byte[] cipherTextBytes = Base64.getDecoder().decode(cipherTextEncodedB64);

		BigInteger plainText = decrypt(new BigInteger(cipherTextBytes));
		byte[] plainTextBytes = plainText.toByteArray();
		return new String(plainTextBytes, StandardCharsets.UTF_8);
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
	public void importPublicKey(String publickey) throws NoSuchAlgorithmException, InvalidKeySpecException {
		String[] elements = publickey.split("\n");
		 byte[] publicKeyBytes = Base64.getDecoder().decode(elements[1]);
		 modulusBig = new BigInteger(publicKeyBytes);
		 hasPublicKey = true;
	}
	public void importPrivateKey(String privatekey) throws NoSuchAlgorithmException, InvalidKeySpecException {
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


	public byte[] signData(String plaintext) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		return new byte[4];
	}

	public boolean verifyData(byte[] signature,String originalMessage) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		return true;

	}




}
