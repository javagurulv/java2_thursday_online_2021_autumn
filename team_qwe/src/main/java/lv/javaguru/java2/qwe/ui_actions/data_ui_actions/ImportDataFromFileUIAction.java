package lv.javaguru.java2.qwe.ui_actions.data_ui_actions;

import lv.javaguru.java2.qwe.core.services.data_services.ImportSecuritiesService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import javax.swing.*;
import java.io.IOException;

public class ImportDataFromFileUIAction implements UIAction {

    private final ImportSecuritiesService importSecuritiesService;

    public ImportDataFromFileUIAction(ImportSecuritiesService importSecuritiesService) {
        this.importSecuritiesService = importSecuritiesService;
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
                importSecuritiesService.execute(path);
//                messageDialog("Data from " + path + " has been imported!");
            } catch (IOException e) {
                System.out.println("ERROR!");
                e.printStackTrace();
            }
        }
    }

}