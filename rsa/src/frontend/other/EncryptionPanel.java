package frontend.other;
import frontend.button.DecryptButton;
import frontend.button.EncryptButton;
import javax.swing.*;
import java.awt.*;
public class EncryptionPanel extends JPanel {

    JTextArea input;
    JTextArea output;
    public EncryptionPanel(){
        this.setBounds(180,160,450,350);
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(null);


        input = new JTextArea();
        input.setFont(new Font("Times New Roman",Font.PLAIN,15));
        input.setText("This is your Input");
        input.addMouseListener(new MouseTripClick(this.input));
        input.setBounds(0,0,450,100);
        input.setBackground(Color.CYAN);
        input.setLineWrap(true);
        input.setVisible(true);

        output = new JTextArea();
        output.setFont(new Font("Times New Roman",Font.PLAIN,15));
        output.setText("This is your output");
        output.addMouseListener(new MouseTripClick(this.output));
        output.setBounds(0,100,450,100);
        output.setBackground(Color.YELLOW);
        output.setLineWrap(true);
        output.setVisible(true);


        this.add(input);
        this.add(output);
        this.add(new EncryptButton(input,output));
        this.add(new DecryptButton(input,output));
        this.setVisible(true);

    }
}

