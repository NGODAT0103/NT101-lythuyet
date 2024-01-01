package frontend.button;

import frontend.form.KeyInfoForm;
import frontend.other.FeaturePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class KeyInfoButton extends BaseButton implements ActionListener {
    KeyInfoForm keyInfo = null;
    FeaturePanel featurePanel;

    public KeyInfoButton(FeaturePanel featurePanel){
        this.setBounds(0,75,180,50);
        this.featurePanel = featurePanel;
        this.setText("Your RSA key");
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (keyInfo==null) {
            try {
                keyInfo = new KeyInfoForm(this.featurePanel);
            } catch (NoSuchAlgorithmException | IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (!keyInfo.isShowing()){
            keyInfo.dispose();
            try {
                keyInfo = new KeyInfoForm(this.featurePanel);
            } catch (NoSuchAlgorithmException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
