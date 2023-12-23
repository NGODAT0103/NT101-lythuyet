package frontend.other;
import frontend.button.Decrypt;
import frontend.button.Encrypt;
import frontend.button.SignData;
import frontend.button.VerifyData;

import javax.swing.*;
import java.awt.*;
public class FeaturePanel extends JPanel {

    JTextArea input;
    JTextArea output;
    public FeaturePanel(){
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
        input.setWrapStyleWord(true);
        input.setVisible(true);

        output = new JTextArea();
        output.setFont(new Font("Times New Roman",Font.PLAIN,15));
        output.setText("This is your output");
        output.addMouseListener(new MouseTripClick(this.output));
        output.setBounds(0,100,450,100);
        output.setBackground(Color.YELLOW);
        output.setLineWrap(true);
        output.setWrapStyleWord(true);
        output.setVisible(true);


        this.add(input);
        this.add(output);
        this.add(new Encrypt(input,output));
        this.add(new Decrypt(input,output));
        this.add(new SignData(input));
        this.add(new VerifyData(input));
        this.setVisible(true);

    }
}

