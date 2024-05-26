package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import menu_bar.FileMenu;
import menu_bar.ViewMenu;

public class ViewController implements Initializable{

    @FXML
    private Label lblDuration;

    @FXML
    private Label lblSize;

    @FXML
    private Label lblName;

    @FXML
    private Label lblPath;

    @FXML
    void processReturn(ActionEvent event) {
    	ViewMenu.getViewStage().close();
    }
	
	MediaPlayer mediaPlayer = FileMenu.MEDIA_VIEW.getMediaPlayer();
	File videoFile = FileMenu.VIDEO_FILE;
	Media media = FileMenu.MEDIA;
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Long fileSize = 0L;
		try {
			fileSize = Files.size(videoFile.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double fileSizeinKB = fileSize/1024.0;
		double fileSizeinMB = fileSizeinKB/1024.0;
		double fileSizeinGB = fileSizeinMB/1024.0;
		
		lblName.setText(videoFile.getName());
		lblPath.setText(videoFile.getParent());
		lblDuration.setText(FileMenu.updateTime(mediaPlayer.getTotalDuration()));
		if (fileSizeinMB > 1024) {
			lblSize.setText(String.format("%.02f GB", fileSizeinGB));
		} else {
			lblSize.setText(String.format("%.02f MB", fileSizeinMB));
		}
		
		
		
		
	}
    
    

}
