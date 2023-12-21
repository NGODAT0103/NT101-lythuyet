package frontend.other;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class MouseTripClick  extends MouseAdapter {
    JTextArea jTextArea;
    public MouseTripClick(JTextArea jTextArea){
        this.jTextArea = jTextArea;

    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount()==3)
        {
            jTextArea.requestFocus();
            jTextArea.setFocusable(true);
            jTextArea.selectAll();
        }

    }
}
