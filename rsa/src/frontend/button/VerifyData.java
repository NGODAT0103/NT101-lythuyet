package frontend.button;
import backend.GlobalVar;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
public class VerifyData extends BaseButton{
    public VerifyData(JTextArea original){
        this.setFont(FEATUREFONT);
        this.setText("Verify");
        this.setBounds(40,200,100,40);

        this.addActionListener(actionEvent -> {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setCurrentDirectory(new File(System.getProperty("user.dir").concat("/signature")));
            jFileChooser.setDialogTitle("Select your signature");
            jFileChooser.setFileFilter(new FileNameExtensionFilter(null,"signature"));
            jFileChooser.setDialogType(JFileChooser.FILES_ONLY);
            jFileChooser.showOpenDialog(null);


            if (jFileChooser.getSelectedFile() == null)
                return;

            try {
                FileInputStream reader = new FileInputStream(jFileChooser.getSelectedFile());
                byte[] signature = reader.readAllBytes();
                reader.close();
                boolean isValid = GlobalVar.rsa.verifyData(signature,original.getText());

                if (isValid)
                    JOptionPane.showMessageDialog(null,"Data is valid","Verify successful",JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null,"Signature invalid or data has been tampered","Verify failed",JOptionPane.ERROR_MESSAGE);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
