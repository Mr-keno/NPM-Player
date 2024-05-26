package menu_bar;

import java.io.File;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class AudioMenu {
	
	private static MediaPlayer createMediaPlayer () {
		return FileMenu.MEDIA_VIEW.getMediaPlayer();
	}

	public static void processVolumeUp(ActionEvent event, Slider volumeSlider, AnchorPane processVolumeOnScreen, Label lblVolumeOnScreen, ImageView imgVolumeUpOnScreen) {
		MediaPlayer mediaPlayer = createMediaPlayer();
		mediaPlayer.setMute(false);
		FileMenu.IS_MUTE = false;
		mediaPlayer.setVolume(mediaPlayer.getVolume()+0.2);
		volumeSlider.setValue(mediaPlayer.getVolume()*100);
		processVolumeOnScreen.setVisible(true);
		imgVolumeUpOnScreen.setImage(new Image(new File("src/images/volume_screen.png").toURI().toString()));
		lblVolumeOnScreen.setVisible(true);
		lblVolumeOnScreen.setText((int) volumeSlider.getValue() + "%");
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.2), e -> {
			processVolumeOnScreen.setVisible(false);
			lblVolumeOnScreen.setVisible(false);
		}));
		timeline.setCycleCount(1);
		timeline.play();
	}

	public static void processVolumeDown(ActionEvent event, Slider volumeSlider, AnchorPane processVolumeOnScreen, Label lblVolumeOnScreen, ImageView imgVolumeUpOnScreen) {
		MediaPlayer mediaPlayer = createMediaPlayer();
		mediaPlayer.setMute(false);
		FileMenu.IS_MUTE = false;
		mediaPlayer.setVolume(mediaPlayer.getVolume()-0.2);
		volumeSlider.setValue(mediaPlayer.getVolume()*100);
		processVolumeOnScreen.setVisible(true);
		imgVolumeUpOnScreen.setImage(new Image(new File("src/images/volumeDown_screen.png").toURI().toString()));
		lblVolumeOnScreen.setVisible(true);
		lblVolumeOnScreen.setText((int) volumeSlider.getValue() + "%");
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.2), e -> {
			processVolumeOnScreen.setVisible(false);
			lblVolumeOnScreen.setVisible(false);
		}));
		timeline.setCycleCount(1);
		timeline.play();
	}

	public static void processMute(ActionEvent event, Slider volumeSlider, ImageView imgMuteUnmute) {
		MediaPlayer mediaPlayer = createMediaPlayer();
		mediaPlayer.setMute(true);
		FileMenu.IS_MUTE = true;
		imgMuteUnmute.setImage(new Image(new File("src/images/mute.png").toURI().toString()));
		volumeSlider.setValue(0);
	}
	
	public static void processUnmute(ActionEvent event, Slider volumeSlider, ImageView imgMuteUnmute) {
		MediaPlayer mediaPlayer = createMediaPlayer();
		mediaPlayer.setMute(false);
		FileMenu.IS_MUTE = false;
		imgMuteUnmute.setImage(new Image(new File("src/images/volume.png").toURI().toString()));
		volumeSlider.setValue(100);
	}
	
    public static void processMuteUnmute(ActionEvent event, Slider volumeSlider, ImageView imgMuteUnmute) {
    	if(FileMenu.IS_MUTE) {
    		processUnmute(event, volumeSlider, imgMuteUnmute);
    	} else {
    		processMute(event, volumeSlider, imgMuteUnmute);
    	}
    }


	public static void processFast5s(ActionEvent event) {
		
	}
	
	

}
