package frontend.button;

import backend.CustomRsa;
import backend.GlobalVar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SaveButton extends BaseButton{
    public SaveButton(){
        this.setBounds(160,0,130,30);
        this.setText("Save to ");
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    JOptionPane.showMessageDialog(null,"Key stored at: \n".concat(GlobalVar.rsa.exportToFile()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
