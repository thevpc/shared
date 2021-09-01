package tn.corp.mlda.mlda.presentation;

import java.io.IOException;
import javafx.fxml.FXML;
import tn.corp.mlda.mlda.App;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
