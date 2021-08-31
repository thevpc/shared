module tn.corp.mlda.mlda {
    requires javafx.controls;
    requires javafx.fxml;

    opens tn.corp.mlda.mlda to javafx.fxml;
    exports tn.corp.mlda.mlda;
}
