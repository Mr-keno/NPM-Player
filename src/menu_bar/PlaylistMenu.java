package menu_bar;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class PlaylistMenu {

private static Stage playListStage = new Stage();
	
	public void processPlayList(ActionEvent event) {
		playListStage.hide();
		Parent mainRoot;
		try {
			mainRoot = FXMLLoader.load(getClass().getResource("PlaylistUI.fxml"));
			Scene mainScene = new Scene(mainRoot);
			playListStage.setScene(mainScene);
			//viewStage.setResizable(false);
			playListStage.setTitle("Video Information");
			Image icon = new Image(getClass().getResourceAsStream("/images/NPMPlayer.png"));
		    playListStage.getIcons().add(icon);
			playListStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static Stage getPlayListStage() {
		return playListStage;
	}
	
}
