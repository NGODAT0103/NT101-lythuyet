package frontend.form;
import backend.CustomRSA;
import backend.GlobalVar;
import frontend.button.FromFileButton;
import frontend.button.KeyInfoButton;
import frontend.other.FeaturePanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainUI extends JFrame implements ActionListener {
    static Font titleFont;
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("Got click");
    }
    static {
        titleFont = new Font("Times New Roman",Font.BOLD,35);
    }


    public MainUI() throws IOException {
        this.setSize(720,560);
        this.setTitle("RSA tool");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        ImageIcon imageicon = new ImageIcon("encrypt.png");
        this.setIconImage(imageicon.getImage());
        this.setLayout(null);
        JLabel welcomeLabel = new JLabel();
        welcomeLabel.setText("Welcome to RSA project tool");
        welcomeLabel.setFont(titleFont);
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setVerticalAlignment(JLabel.CENTER);
        welcomeLabel.setBounds(0,0,720,50);
        welcomeLabel.setOpaque(true);
        this.add(welcomeLabel);

        FeaturePanel featurePanel = new FeaturePanel();
        this.add(featurePanel);
        this.add(new KeyInfoButton(featurePanel));


        FromFileButton forInput = new FromFileButton(featurePanel,FromFileButton.INPUT);

        forInput.setBounds(50,180,100,50);
        FromFileButton forOutput = new FromFileButton(featurePanel,FromFileButton.OUTPUT);
        forOutput.setBounds(50,280,100,50);


        this.add(forOutput);
        this.add(forInput);
        this.setVisible(true);






        GlobalVar.rsa = new CustomRSA();
    }

}
