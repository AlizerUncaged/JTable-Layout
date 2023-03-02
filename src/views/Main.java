package views;

import com.formdev.flatlaf.FlatDarculaLaf;
import controllers.Controller;
import data.TableModel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (Exception e) {

        }

        var tableModel = new TableModel();
        var form = new MainForm();
        var userInteractionListener = new UserInteractionListener();

        new Controller(
                form,
                tableModel,
                userInteractionListener
        ).start();
    }
}