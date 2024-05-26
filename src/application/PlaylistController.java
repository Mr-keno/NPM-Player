package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import database.FileDAO;
import database.FileMode;
import database.VideoFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import menu_bar.FileMenu;
import menu_bar.PlaylistMenu;

public class PlaylistController implements Initializable{

	@FXML
    private TableView<VideoFile> tbPlaylistTable;
	
    @FXML
    private TableColumn<VideoFile, String> tbDuration;

    @FXML
    private TableColumn<VideoFile, String> tbTitle;
    
    @FXML
    private TableColumn<VideoFile, String> tbPath;
    
    @FXML
    private Label lblPlayList;
    
    private FileMode mode;

    @FXML
    void processReturn(ActionEvent event) {
    	PlaylistMenu.getPlayListStage().hide();
    }
    
    @FXML
    void processClearList(ActionEvent event) {
    		switch (mode) {
			case RECENT:
				showConfirmationDialog("src/database/recentFile.txt", "Clear Recent Files");
				showFile(mode);
				break;
				
			case FAVORITE:
				showConfirmationDialog("src/database/favoriteFile.txt", "Clear Favorite List");
				showFile(mode);
				break;
				
			case PLAYLIST:
				showConfirmationDialog("src/database/playList.txt", "Clear Playlist Files");
				showFile(mode);
				break;

			default:
				break;
			}
    }
    
    @FXML
    void processOpenPlaylist(ActionEvent event) {
    	mode = FileMode.PLAYLIST;
    	showFile(mode);
    }

    @FXML
    void processOpenRecent(ActionEvent event) {
    	mode = FileMode.RECENT;
    	showFile(mode);
    }
    
    @FXML
    void processOpenFavorite(ActionEvent event) {
    	mode = FileMode.FAVORITE;
    	showFile(mode);
    }
    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		mode = FileMenu.MODE;
		showFile(mode);
		
		
		tbPlaylistTable.setOnMouseClicked(event -> {
			if(event.getClickCount() == 2) {
				handleDoubleClick(tbPlaylistTable);
			}
		});
		
	}
	
	private void showFile(FileMode mode) {
		tbTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
		tbDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
		tbPath.setCellValueFactory(new PropertyValueFactory<>("path"));
		
		ObservableList<VideoFile> files = FXCollections.observableArrayList();
		
		try {
			switch (mode) {
			case RECENT:
				files = FileDAO.readPath("src/database/recentFile.txt");
				FXCollections.reverse(files);
				tbPlaylistTable.setItems(files);
				lblPlayList.setText("Recent Files");
				break;
				
			case FAVORITE:
				files = FileDAO.readPath("src/database/favoriteFile.txt");
				tbPlaylistTable.setItems(files);
				lblPlayList.setText("Favorite Files");
				break;
				
			case PLAYLIST:
				files = FileDAO.readPath("src/database/playList.txt");
				tbPlaylistTable.setItems(files);
				lblPlayList.setText("Your Playlist");
				break;

			default:
				break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void handleDoubleClick(TableView<VideoFile> tableView) {
		
		PlaylistMenu.getPlayListStage().hide();
		
		VideoFile selectedFile = tableView.getSelectionModel().getSelectedItem();
		
		if (!FileMenu.IS_NULL) {
			VideoPlayerController.video.processClose(null);
		}
		
		File videoFile = new File(selectedFile.getPath());
		FileMenu.VIDEO_FILE = videoFile;
		
		String videoPath = videoFile.toURI().toString();
		
//		System.out.println(selectedFile.getPath());
//		System.out.println(videoPath);
		
		if(selectedFile != null) {
//			System.out.println("Double Clicked On : " + selectedFile.getTitle());
			VideoPlayerController.video.processPlayMedia(videoPath);
			FileMenu.IS_NULL=false;
		}
	}
	
	 private void showConfirmationDialog(String path, String title) {
	        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	        alert.setTitle(title);
	        alert.setHeaderText("Are you sure you want to proceed?");
	        alert.setContentText("This action cannot be undone.");

	        ButtonType buttonTypeYes = new ButtonType("Yes");
	        ButtonType buttonTypeNo = new ButtonType("No");

	        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
	        
	        Optional<ButtonType> result = alert.showAndWait();
	        
	        if (result.isPresent() && result.get() == buttonTypeYes) {
	            try {
					FileDAO.clearList(path);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        } else {
	            alert.close();
	        }
	    }

}
