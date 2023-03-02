package interactions;

import views.ControlledJTable;
import views.MainForm;

import javax.swing.*;
import java.awt.event.*;

public class JTableMouseEvents implements MouseListener {
    private MainForm form;

    public JTableMouseEvents(MainForm form)
    {
        this.form = form;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // check double click
        var formTable = ((ControlledJTable) form.getTable());

        if (e.getClickCount() == 1)
        {
            form.setUpdateMode(false, 0);
        }

        if (e.getClickCount() == 2 && formTable.canSelect()) {
            form.setUpdateMode(true,
                    form.getTable().rowAtPoint(e.getPoint()));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
