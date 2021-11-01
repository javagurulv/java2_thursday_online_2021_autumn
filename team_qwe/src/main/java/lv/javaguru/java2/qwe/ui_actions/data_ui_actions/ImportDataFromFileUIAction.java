package lv.javaguru.java2.qwe.ui_actions.data_ui_actions;

import lv.javaguru.java2.qwe.core.services.data_services.ImportSecuritiesService;
import lv.javaguru.java2.qwe.ui_actions.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.io.IOException;

//@Component
public class ImportDataFromFileUIAction implements UIAction {

    @Autowired private ImportSecuritiesService importSecuritiesService;

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
            } catch (IOException | ArrayIndexOutOfBoundsException e) {
                System.out.println("ERROR!");
                e.printStackTrace();
            }
        }
    }

}