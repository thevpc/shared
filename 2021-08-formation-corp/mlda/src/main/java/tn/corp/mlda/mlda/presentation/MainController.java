package tn.corp.mlda.mlda.presentation;

import java.util.Iterator;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.util.Duration;
import tn.corp.mlda.mlda.model.Album;
import tn.corp.mlda.mlda.service.AlbumService;

public class MainController {
	@FXML
	private ChoiceBox<String> yearFilter;

	@FXML
	private ChoiceBox<String> albumFilter;

	@FXML
	private ChoiceBox<String> singerFilter;

	@FXML
	private Button myButton;

	@FXML
	private GridPane musicBox;

	private AlbumService service = new AlbumService();
    @FXML
    private VBox board;
    
	@FXML
	private ListView<String> categoryList;

	
    @FXML
    private BarChart<?, ?> barChart;
    
	@FXML
	public void initialize() {
		
		service.loadData();
		
		categoryList.getItems().add("<Tous>");
		categoryList.getItems().addAll(service.findCategoryNames());
		categoryList.getSelectionModel().getSelectedItems().addListener(this::onChangeCategory);

		yearFilter.getItems().add("<Tous>");
		yearFilter.getItems().addAll(service.findYearsNames());
		yearFilter.getSelectionModel().selectedItemProperty().addListener(this::onChangeYear);

		singerFilter.getItems().add("<Tous>");
		singerFilter.getItems().addAll(service.findSingerNames());
		singerFilter.getSelectionModel().selectedItemProperty().addListener(this::onChangeSinger);

		reAppliquerFitre();
		
		prepareBoard();
		prepareBarChart();
	}

	public void prepareBoard() {
		Group p=new Group();
	    Shape M=new Polyline(
					0, 24, 
					0, 0,
					12, 12,
					24, 0,
					24, 24
					);
		int x= 30;
	    Shape L=new Polyline(
				x+0, 0, 
				x+0, 24,
				x+24, 24
				);
		x= 60;
		Shape D=new Arc(
				x+0, 12, 
				24, 12,
				-90, 180
				);
		x= 90;
		Shape A=new Polyline(
				x+0, 24, 
				x+0, 0,
				x+24, 0,
				x+24, 12,
				x+0, 12,
				x+24, 12,
				x+24, 24
				);
		
		//

		M.setStrokeWidth(3.0);
	    M.setStroke(Color.YELLOWGREEN);

	    L.setStrokeWidth(3.0);
	    L.setStroke(Color.DARKBLUE);

		D.setStrokeWidth(3.0);
	    D.setStroke(Color.YELLOWGREEN);
	    D.setFill(Color.GREENYELLOW);
		
	    A.setStrokeWidth(3.0);
	    A.setStroke(Color.DARKBLUE);

	    p.getChildren().add(M);
		p.getChildren().add(L);
		p.getChildren().add(D);
		p.getChildren().add(A);
//		board.getChildren().add(
//				new Circle(10, 10, 10)
//		);
		board.getChildren().add(p);
		Label descr=new Label("Media Library Desktop Application");
		descr.setFont(new Font(8));
		board.getChildren().add(descr);
		
		TranslateTransition tr=new TranslateTransition();
		tr.setDuration(Duration.seconds(3));
		tr.setAutoReverse(true);
		tr.setCycleCount(Animation.INDEFINITE);
		tr.setToY(30);
		tr.setNode(board);
		tr.play();
	}
	
//	public Node createVignetteByFXML(Album a) {
//		try {
//			Node vignette = App.loadFXML("vignette");
//			ImageView imageView = (ImageView) vignette.lookup("image");
//			Label title = (Label) vignette.lookup("title");
//			Label description = (Label) vignette.lookup("description");
//			title.setText(a.getTitle());
//			description.setText(a.getDescription());
//			imageView.setImage(new Image(a.getImage()));
//			return vignette;
//		} catch (Exception ex) {
//			throw new IllegalArgumentException(ex);
//		}
//	}

	public Node createVignetteByCode(Album a) {
		BorderPane control = new BorderPane();
		control.setPadding(new Insets(10));
		ImageView image = new ImageView();
		control.setCenter(image);
		VBox vb = new VBox();
		VBox.setVgrow(vb, Priority.ALWAYS);
		vb.setFillWidth(true);
		control.setBottom(vb);
		Label title = new Label();
		vb.getChildren().add(title);
		Label description = new Label();
		vb.getChildren().add(description);

		image.setImage(new Image(getClass().getResourceAsStream(a.getImage())));
		image.setFitWidth(64);
		image.setFitHeight(64);
		title.setText(a.getTitle());
		title.setAlignment(Pos.CENTER);
		description.setText(a.getDescription());
		description.setAlignment(Pos.CENTER);
		
		
		final FadeTransition fadeIn = new FadeTransition(Duration.millis(100));
        fadeIn.setNode(control);
        fadeIn.setToValue(1);
        control.setOnMouseEntered(e -> fadeIn.playFromStart());

        final FadeTransition fadeOut = new FadeTransition(Duration.millis(100));
        fadeOut.setNode(control);
        fadeOut.setToValue(0.5);
        control.setOnMouseExited(e -> fadeOut.playFromStart());

        control.setOpacity(0.8);
        
        
        RotateTransition rotation = new RotateTransition(Duration.seconds(0.5), control);
        rotation.setCycleCount(Animation.INDEFINITE);
        rotation.setByAngle(360);
        control.setOnMouseEntered(e -> rotation.playFromStart());
        control.setOnMouseExited(e -> {
        	control.setRotate(0);
        	rotation.pause();
        });
        
		return control;
	}

	void onChangeYear(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		reAppliquerFitre();
	}
	
	void onChangeSinger(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		reAppliquerFitre();
	}
	
	public void onChangeCategory(Change<? extends String> ch) {
		reAppliquerFitre();
	}

	void reAppliquerFitre() {
		musicBox.getChildren().clear();
		String category = categoryList.getSelectionModel().getSelectedItem();
		if ("<Tous>".equals(category)) {
			category = null;
		}
		String singer = singerFilter.getSelectionModel().getSelectedItem();
		if ("<Tous>".equals(singer)) {
			singer = null;
		}
		String year = yearFilter.getSelectionModel().getSelectedItem();
		Integer yearInt = (year == null || year.equals("<Tous>")) ? null : Integer.parseInt(year);
		List<Album> theAlbums = service.findAlbums(yearInt, category, singer);

		int columns = musicBox.getColumnCount();
		int rows = musicBox.getRowCount();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				try {
					int p = i * columns + j;
					if(p<theAlbums.size()) {
						Album a = theAlbums.get(p);
//						Node vignette1=createVignetteByFXML(a);
						Node vignette2=createVignetteByCode(a);
						musicBox.add(vignette2, i, j);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@FXML
	void onClickClose(ActionEvent event) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setContentText("Malla Ra7a");
		alert.show();
		// System.exit(0);
	}
	
	public void prepareBarChart() {
		barChart.getXAxis().setLabel("Albums");
		barChart.getYAxis().setLabel("Visits");
		barChart.getData().clear();
		XYChart.Series dataSeries1 = new XYChart.Series();
		dataSeries1.setName("2014");

		String category = categoryList.getSelectionModel().getSelectedItem();
		if ("<Tous>".equals(category)) {
			category = null;
		}
		String singer = singerFilter.getSelectionModel().getSelectedItem();
		if ("<Tous>".equals(singer)) {
			singer = null;
		}
		String year = yearFilter.getSelectionModel().getSelectedItem();
		Integer yearInt = (year == null || year.equals("<Tous>")) ? null : Integer.parseInt(year);
		List<Album> theAlbums = service.findAlbums(yearInt, category, singer);

		for (Album album : theAlbums) {
			dataSeries1.getData().add(new XYChart.Data(album.getTitle(), (int)(Math.random()*200)));
		}
		barChart.getData().add(dataSeries1);
	}

}
