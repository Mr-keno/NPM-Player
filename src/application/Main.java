package application;
	
import javafx.application.Application;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;


public class Main extends Application {
	
	private static Stage primaryStage;
	private static Scene scene;
	public static Double maxWidth;
	public static Double maxHeight;
	
	@Override
	public void start(Stage stage) {
		
		primaryStage = stage;
		
		try {
//			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("VideoPlayerUI.fxml"));
			GridPane root = (GridPane) FXMLLoader.load(getClass().getResource("VideoPlayerUI2.fxml"));
			scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("NPM Player");
			Image icon = new Image(getClass().getResourceAsStream("/images/NPMPlayer.png"));
		    primaryStage.getIcons().add(icon);
			primaryStage.show();

			Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
			maxWidth = visualBounds.getWidth();
			maxHeight = visualBounds.getHeight();

			root.setMaxHeight(maxHeight);
			root.setMaxWidth(maxWidth);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public static Scene getScene() {
		return scene;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
