package frontend.button;

import frontend.form.KeyInfo;
import frontend.form.MainUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;

public class GenerateButton extends BaseButton implements ActionListener {
    KeyInfo keyInfo = null;
    MainUI mainUI ;

    public GenerateButton(MainUI currentUI){
        this.setBounds(0,75,150,50);
        this.setText("Generate key");
        this.addActionListener(this);
        this.mainUI = currentUI;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (keyInfo==null) {
            try {
                keyInfo = new KeyInfo(mainUI);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }

        if (!keyInfo.isShowing()){
            keyInfo.dispose();
            try {
                keyInfo = new KeyInfo(mainUI);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
