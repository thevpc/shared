package tn.corp.mlda.mlda.presentation;

import java.io.IOException;
import javafx.fxml.FXML;
import tn.corp.mlda.mlda.App;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}