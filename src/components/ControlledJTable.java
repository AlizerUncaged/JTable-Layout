package components;

import data.TableModel;
import interactions.UserInteractionListener;

import javax.swing.*;

/**
 * A JTable with added functionality to control its selectability.
 */
public class ControlledJTable extends JTable {
    private boolean _canSelect = true;
    /**
     * Creates a new instance of ControlledJTable with the default auto resize mode.
     */
    public ControlledJTable()
    {
        this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }

    /**
     * Sets a UserInteractionListener to handle user interactions with the table.
     *
     * @param userInteractionListener The UserInteractionListener to set.
     */
    public void setUserInteractionsListener(UserInteractionListener userInteractionListener)
    {
        this.addMouseListener(userInteractionListener.getTableMouseHandler());
    }

    /**
     * Sets whether the table can be selected or not.
     *
     * @param selectable Whether the table can be selected or not.
     */
    public void setSelectable(boolean selectable)
    {
        _canSelect = selectable;
    }

    /**
     * Returns whether the table can be selected or not.
     *
     * @return true if the table can be selected, false otherwise.
     */
    public boolean canSelect()
    {
        return _canSelect;
    }

    /**
     * Returns the TableModel of this ControlledJTable.
     *
     * @return The TableModel of this ControlledJTable.
     */
    public TableModel getTableModel() {
        return (TableModel) this.getTableModel();
    }

    /**
     * Overrides the changeSelection method of JTable to respect the selectability of the table.
     *
     * @param rowIndex The row index to change the selection to.
     * @param columnIndex The column index to change the selection to.
     * @param toggle Whether or not to toggle the selection.
     * @param extend Whether or not to extend the selection.
     */
    @Override
    public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend)
    {
        if (!_canSelect)
            return;

        super.changeSelection(rowIndex, columnIndex, toggle, extend);
    }

}
