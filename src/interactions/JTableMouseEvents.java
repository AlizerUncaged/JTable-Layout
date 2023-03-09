package interactions;

import data.Order;
import components.ControlledJTable;
import views.MainForm;

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
        var selectedRow = form.getTable().rowAtPoint(e.getPoint());
        if (e.getClickCount() == 1) {

            if (form.isUpdateMode()) {
                // If from update mode, clear the order fields.
                form.getDataHandler().fillData(new Order());
            }

            form.setUpdateMode(false, 0);
            // Reselect the row again.
            formTable.changeSelection(selectedRow, 0, false,false);
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
