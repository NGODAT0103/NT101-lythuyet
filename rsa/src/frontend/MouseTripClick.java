package frontend;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseTripClick  extends MouseAdapter {

    JTextArea jTextArea;
    MouseTripClick(JTextArea jTextArea){
        this.jTextArea = jTextArea;

    }
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getClickCount());
        if (e.getClickCount()==3)
        {
            jTextArea.requestFocus();
            jTextArea.setFocusable(true);
            jTextArea.selectAll();
        }

    }
}
