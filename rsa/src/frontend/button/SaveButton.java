package frontend.button;

import backend.CustomRsa;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SaveButton extends BaseButton{
    public SaveButton(CustomRsa rsa){
        this.setBounds(250,0,175,50);
        this.setText("Save to ");
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    JOptionPane.showMessageDialog(null,"Key stored at: \n".concat(rsa.exportToFile()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
