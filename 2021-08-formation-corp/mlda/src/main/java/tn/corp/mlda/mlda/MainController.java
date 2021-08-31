package tn.corp.mlda.mlda;

import java.util.Iterator;

import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class MainController {
	@FXML
	private ChoiceBox<String> anneeFilter;

	@FXML
	private ChoiceBox<String> albumFilter;

	@FXML
	private ChoiceBox<String> chanteurFilter;

	@FXML
	private Button myButton;

	@FXML
	private GridPane musicBox;

	@FXML
	void clickMe(ActionEvent event) {

	}

	@FXML
	private ListView<String> categoryList;

	@FXML
	public void initialize() {
		categoryList.getItems().add("<Tous>");
		categoryList.getItems().add("Pop");
		categoryList.getItems().add("Oriential");
		categoryList.getItems().add("Funk");
		categoryList.getItems().add("Blues");
		categoryList.getItems().add("Classical");
		categoryList.getItems().add("Rock");
		categoryList.getItems().add("Rock&Roll");
		categoryList.getItems().add("Hard Rock");
		categoryList.getSelectionModel().getSelectedItems().addListener(this::onChangeListItem);

		anneeFilter.getItems().add("<Tous>");
		anneeFilter.getItems().add("2021");
		anneeFilter.getItems().add("2020");
		anneeFilter.getItems().add("2019");
		anneeFilter.getSelectionModel().selectedItemProperty().addListener(this::onChangeAnnee);
		
		int columns=musicBox.getColumnCount();
		int rows=musicBox.getRowCount();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				try {
					Node vignette = App.loadFXML("vignette");
					ImageView imageView=(ImageView)vignette.lookup("image");
					Label title=(Label)vignette.lookup("title");
					Label description=(Label)vignette.lookup("description");
					//prepare here ...
					musicBox.add(vignette,i, j);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	void onChangeAnnee(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		reAppliquerFitre();
	}

	public void onChangeListItem(Change<? extends String> ch) {
		String selectedItem = categoryList.getSelectionModel().getSelectedItem();
		// faire ce qu'il faut quand on selectionne
		reAppliquerFitre();
	}

	void reAppliquerFitre() {

	}

	@FXML
	void onClickClose(ActionEvent event) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setContentText("Malla Ra7a");
		alert.show();
		// System.exit(0);
	}

}
