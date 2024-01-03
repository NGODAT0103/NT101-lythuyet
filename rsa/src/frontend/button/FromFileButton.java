package frontend.button;

import frontend.other.FeaturePanel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class FromFileButton extends BaseButton implements ActionListener {

    public static int OUTPUT;
    public static int INPUT;
    FeaturePanel featurePanel;
    int textArea ;

    static {
        INPUT = 0;
        OUTPUT = 1;
    }
   public FromFileButton(FeaturePanel featurePanel, int textArea) {
       this.setText("From file");
       this.featurePanel = featurePanel;
       this.textArea = textArea;
       this.setFont(FEATUREFONT);
       this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        byte[] data;


        JFileChooser jFileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(null,"txt");
        jFileChooser.setFileFilter(filter);
        jFileChooser.showOpenDialog(null);
        if (jFileChooser.getSelectedFile() == null)
            return;


        try {
            FileInputStream fileInputStream = new FileInputStream(jFileChooser.getSelectedFile());
            data = fileInputStream.readAllBytes();
            fileInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        switch (textArea){
            case 0:
                featurePanel.input.setText(new String(data, StandardCharsets.UTF_8));
            case 1:
                featurePanel.output.setText(new String(data,StandardCharsets.UTF_8));
        }
    }
}
