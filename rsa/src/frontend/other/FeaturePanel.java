package frontend.other;
import frontend.button.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.nio.charset.StandardCharsets;

public class FeaturePanel extends JPanel {

    JTextArea input;
    JTextArea output;
   public JLabel maxEncryptLabel,currentLengthLabel;
    public FeaturePanel(){
        this.setBounds(180,160,450,350);
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(null);


        maxEncryptLabel = new JLabel();
        maxEncryptLabel.setFont(new Font("Times New Roman",Font.PLAIN,15));
        maxEncryptLabel.setText("Can encrypt: "+"00 bytes");
        maxEncryptLabel.setBounds(0,250,150,30);



        currentLengthLabel = new JLabel();
        currentLengthLabel.setBounds(0,300,150,30);
        currentLengthLabel.setFont(new Font("Times New Roman",Font.PLAIN,15));
        currentLengthLabel.setText("Message length: ");


        input = new JTextArea();
        input.setFont(new Font("Times New Roman",Font.PLAIN,15));
        input.setText("This is your Input");
        input.addMouseListener(new MouseTripClick(this.input));
        input.setBounds(0,0,450,100);
        input.setBackground(Color.CYAN);
        input.setLineWrap(true);
        input.setWrapStyleWord(true);
        input.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                String messageLength =  String.valueOf(input.getText().getBytes(StandardCharsets.UTF_8).length);
                currentLengthLabel.setText("Message length: ".concat(messageLength));
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                String messageLength =  String.valueOf(input.getText().getBytes(StandardCharsets.UTF_8).length);
                currentLengthLabel.setText("Message length: ".concat(messageLength));
            }


            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                String messageLength =  String.valueOf(input.getText().getBytes(StandardCharsets.UTF_8).length);
                currentLengthLabel.setText("Message length: ".concat(messageLength));
            }
        });


        String messageLength =  String.valueOf(input.getText().getBytes(StandardCharsets.UTF_8).length);
        currentLengthLabel.setText("Message length: ".concat(messageLength));
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
        this.add(maxEncryptLabel);
        this.add(currentLengthLabel);
        this.add(output);
        this.add(new Encrypt(input,output));
        this.add(new Decrypt(input,output));
        this.add(new SignData(input));
        this.add(new VerifyData(input));
        this.setVisible(true);

    }
}

