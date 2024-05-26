package menu_bar;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class HelpMenu {

	private static Stage helpStage = new Stage();

	public void processAbout() {
		helpStage.hide();
		Parent mainRoot;
		try {
			mainRoot = FXMLLoader.load(getClass().getResource("AboutUI.fxml"));
			Scene mainScene = new Scene(mainRoot);
			helpStage.setScene(mainScene);
			helpStage.setResizable(false);
			helpStage.setTitle("About NPM Player");
			Image icon = new Image(getClass().getResourceAsStream("/images/NPMPlayer.png"));
		    helpStage.getIcons().add(icon);
			helpStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void processCheckForUpdate() {
		helpStage.hide();
		Parent mainRoot;
		try {
			mainRoot = FXMLLoader.load(getClass().getResource("CheckForUpdateUI.fxml"));
			Scene mainScene = new Scene(mainRoot);
			helpStage.setScene(mainScene);
			helpStage.setResizable(false);
			helpStage.setTitle("Check for update");
			Image icon = new Image(getClass().getResourceAsStream("/images/NPMPlayer.png"));
		    helpStage.getIcons().add(icon);
			helpStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void processShortcutKeys() {
		helpStage.hide();
		Parent mainRoot;
		try {
			mainRoot = FXMLLoader.load(getClass().getResource("ShortcutKeyUI.fxml"));
			Scene mainScene = new Scene(mainRoot);
			helpStage.setScene(mainScene);
			helpStage.setResizable(false);
			helpStage.setTitle("Shortcut Keys");
			Image icon = new Image(getClass().getResourceAsStream("/images/NPMPlayer.png"));
		    helpStage.getIcons().add(icon);
			helpStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void processFAQs() {
		helpStage.hide();
		Parent mainRoot;
		try {
			mainRoot = FXMLLoader.load(getClass().getResource("FAQsUI.fxml"));
			Scene mainScene = new Scene(mainRoot);
			helpStage.setScene(mainScene);
			helpStage.setResizable(false);
			helpStage.setTitle("FAQs");
			Image icon = new Image(getClass().getResourceAsStream("/images/NPMPlayer.png"));
		    helpStage.getIcons().add(icon);
			helpStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public void processContactUs() {
		helpStage.hide();
		Parent mainRoot;
		try {
			mainRoot = FXMLLoader.load(getClass().getResource("ContactUs.fxml"));
			Scene mainScene = new Scene(mainRoot);
			helpStage.setScene(mainScene);
			helpStage.setResizable(false);
			helpStage.setTitle("Shortcut Keys");
			Image icon = new Image(getClass().getResourceAsStream("/images/NPMPlayer.png"));
		    helpStage.getIcons().add(icon);
			helpStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Stage getHelpStage() {
		return helpStage;
	}

}
