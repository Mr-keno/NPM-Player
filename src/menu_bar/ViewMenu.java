package menu_bar;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ViewMenu {
	
	private static Stage viewStage = new Stage();
	
	public void processVideoInformation(ActionEvent event) {
		viewStage.hide();
		Parent mainRoot;
		try {
			mainRoot = FXMLLoader.load(getClass().getResource("VideoInformationUI.fxml"));
			Scene mainScene = new Scene(mainRoot);
			viewStage.setScene(mainScene);
			//viewStage.setResizable(false);
			viewStage.setTitle("Video Information");
			Image icon = new Image(getClass().getResourceAsStream("/images/NPMPlayer.png"));
		    viewStage.getIcons().add(icon);
			viewStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static Stage getViewStage() {
		return viewStage;
	}

}
