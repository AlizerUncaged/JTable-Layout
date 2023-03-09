package views;

import com.formdev.flatlaf.FlatDarculaLaf;
import components.ControlledJTable;
import controllers.Controller;
import data.TableModel;
import interactions.UserInteractionListener;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (Exception e) {

        }

        var tableModel = new TableModel();
        var controlledJTable = new ControlledJTable();
        var form = new MainForm();
        var userInteractionListener = new UserInteractionListener();

        new Controller(
                form,
                tableModel,
                userInteractionListener,
                controlledJTable
        ).start();
    }
}