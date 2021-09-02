open module tn.corp.mlda.mlda {
    requires javafx.controls;
    requires javafx.fxml;

    //opens tn.corp.mlda.mlda to javafx.fxml;
    exports tn.corp.mlda.mlda.model;
    exports tn.corp.mlda.mlda.presentation;
    exports tn.corp.mlda.mlda.service;
}
