package lv.javaguru.java2.qwe.ui_actions.data_ui_actions;

import lv.javaguru.java2.qwe.database.Database;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import javax.swing.*;
import java.io.IOException;

import static lv.javaguru.java2.qwe.utils.UtilityMethods.messageDialog;

public class ImportDataFromFileUIAction implements UIAction {

    private final Database database;

    public ImportDataFromFileUIAction(Database database) {
        this.database = database;
    }

    @Override
    public void execute() {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose file");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getPath();
            try {
                database.importSecurities(path);
                messageDialog("Data from " + path + " has been imported!");
            } catch (IOException e) {
                System.out.println("ERROR!");
                e.printStackTrace();
            }
        }

    }

}