package frontend.button;

import javax.swing.*;
import java.awt.*;

public class BaseButton extends JButton {
    static Font normalFont;
    static Font keyFont;
    static {
        normalFont = new Font("Times New Roman",Font.BOLD,20);
        keyFont =new Font("Times New Roman",Font.PLAIN,10);
    }
    protected BaseButton(){
        this.setFocusable(false);
        this.setHorizontalAlignment(JButton.CENTER);
        this.setVerticalAlignment(JButton.CENTER);
        this.setFont(normalFont);
        this.setVisible(true);
    }
}
