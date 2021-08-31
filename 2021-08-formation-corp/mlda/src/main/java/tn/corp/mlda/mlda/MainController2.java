package tn.corp.mlda.mlda;

import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;

public class MainController2 {
	@FXML
	private ChoiceBox<String> anneeFilter;

	@FXML
	private ChoiceBox<String> albumFilter;

	@FXML
	private ChoiceBox<String> chanteurFilter;

	@FXML
	private Button myButton;

	@FXML
	void clickMe(ActionEvent event) {

	}

	@FXML
	private ListView<String> categoryList;

	@FXML
	public void initialize() {
		categoryList.getItems().add("Pop");
		categoryList.getItems().add("Oriential");
		categoryList.getItems().add("Funk");
		categoryList.getItems().add("Blues");
		categoryList.getItems().add("Classical");
		categoryList.getItems().add("Rock");
		categoryList.getItems().add("Rock&Roll");
		categoryList.getItems().add("Hard Rock");
		categoryList.getSelectionModel().getSelectedItems().addListener(this::onChangeListItem);

		anneeFilter.getItems().add("");
		anneeFilter.getItems().add("2021");
		anneeFilter.getItems().add("2020");
		anneeFilter.getItems().add("2019");
		anneeFilter.getSelectionModel().selectedItemProperty().addListener(this::onChangeAnnee);
	}
	
    void onChangeAnnee(ObservableValue<? extends String> observable, String oldValue, String newValue) {
    	reAppliquerFitre();
    }


	public void onChangeListItem(Change<? extends String> ch) {
		String selectedItem = categoryList.getSelectionModel().getSelectedItem();
		// faire ce qu'il faut quand on selectionne
    	reAppliquerFitre();
	}

	void reAppliquerFitre(){
		
	}
	@FXML
	void onClickClose(ActionEvent event) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setContentText("Malla Ra7a");
		alert.show();
		// System.exit(0);
	}

}
