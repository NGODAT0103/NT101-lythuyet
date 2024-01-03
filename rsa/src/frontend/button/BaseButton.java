package frontend.button;

import javax.swing.*;
import java.awt.*;

public class BaseButton extends JButton {
    static Font NORMALFONT;
    static Font KEYFONT;
    static Font FEATUREFONT;
    static {
        NORMALFONT = new Font("Times New Roman",Font.BOLD,20);
        KEYFONT =new Font("Times New Roman",Font.PLAIN,10);
        FEATUREFONT = new Font("Times New Roman",Font.BOLD,15);

    }
    protected BaseButton(){
        this.setFocusable(false);
        this.setHorizontalAlignment(JButton.CENTER);
        this.setVerticalAlignment(JButton.CENTER);
        this.setFont(NORMALFONT);
        this.setVisible(true);
    }
}
