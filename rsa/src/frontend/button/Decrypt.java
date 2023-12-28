package frontend.button;
import backend.GlobalVar;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Decrypt extends BaseButton{
    public Decrypt(JTextArea input, JTextArea output){
        this.setText("Decrypt");
        this.setFont(featureFont);
        this.setBounds(340,200,100,40);

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (input.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter data", "Field empty", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    output.setText(GlobalVar.rsa.decrypt(input.getText()));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    JOptionPane.showMessageDialog(null,"Data is not correct format","Data invalid",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
