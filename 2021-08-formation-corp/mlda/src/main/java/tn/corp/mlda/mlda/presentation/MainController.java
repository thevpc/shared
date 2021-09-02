package tn.corp.mlda.mlda.presentation;

import java.util.Iterator;
import java.util.List;

import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import tn.corp.mlda.mlda.model.Album;
import tn.corp.mlda.mlda.service.AlbumService;

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

	private AlbumService service = new AlbumService();
    @FXML
    private VBox board;
    
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

		String category = categoryList.getSelectionModel().getSelectedItem();
		if ("<Tous>".equals(category)) {
			category = null;
		}
		String year = anneeFilter.getSelectionModel().getSelectedItem();
		Integer yearInt = (year == null || year.equals("<Tous>")) ? null : Integer.parseInt(year);
		String singer = null;
		List<Album> theAlbums = service.findAlbums(yearInt, category, singer);

		int columns = musicBox.getColumnCount();
		int rows = musicBox.getRowCount();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				try {
					int p = i * columns + j;
					if(p<theAlbums.size()) {
						Album a = theAlbums.get(p);
						Node vignette1=createVignetteByFXML(a);
						Node vignette2=createVignetteByCode(a);
						musicBox.add(vignette1, i, j);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		
		prepareBoard();
	}

	public void prepareBoard() {
		board.getChildren().add(
				new Line(0, 0, 20, 20)
		);
		board.getChildren().add(
				new Circle(10, 10, 10)
		);
	}
	
	public Node createVignetteByFXML(Album a) {
		try {
			Node vignette = App.loadFXML("vignette");
			ImageView imageView = (ImageView) vignette.lookup("image");
			Label title = (Label) vignette.lookup("title");
			Label description = (Label) vignette.lookup("description");
			title.setText(a.getTitle());
			description.setText(a.getDescription());
			imageView.setImage(new Image(a.getImage()));
			return vignette;
		} catch (Exception ex) {
			throw new IllegalArgumentException(ex);
		}
	}

	public Node createVignetteByCode(Album a) {
		BorderPane bp = new BorderPane();
		ImageView iv = new ImageView();
		bp.setCenter(iv);
		VBox vb = new VBox();
		bp.setBottom(vb);
		Label title = new Label();
		vb.getChildren().add(title);
		Label description = new Label();
		vb.getChildren().add(description);

		iv.setImage(new Image(a.getImage()));
		title.setText(a.getTitle());
		description.setText(a.getDescription());
		return bp;
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
