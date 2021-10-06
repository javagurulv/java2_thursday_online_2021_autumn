package lv.javaguru.java2.qwe.ui_actions.data_ui_actions;

import lv.javaguru.java2.qwe.core.services.data_services.ImportSecuritiesService;
import lv.javaguru.java2.qwe.dependency_injection.DIComponent;
import lv.javaguru.java2.qwe.dependency_injection.DIDependency;
import lv.javaguru.java2.qwe.ui_actions.UIAction;

import javax.swing.*;
import java.io.IOException;

@DIComponent
public class ImportDataFromFileUIAction implements UIAction {

    @DIDependency private ImportSecuritiesService importSecuritiesService;

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