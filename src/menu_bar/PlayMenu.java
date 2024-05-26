package menu_bar;

import java.io.File;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class PlayMenu {
	
	private static MediaPlayer createMediaPlayer () {
		return FileMenu.MEDIA_VIEW.getMediaPlayer();
	}

//Play, Resume, Pause, Stop	
	public static void processPlay(ActionEvent event, ImageView imgPlayPause) {
		MediaPlayer mediaPlayer = createMediaPlayer();
		mediaPlayer.play();
		FileMenu.IS_PLAY = true;
		imgPlayPause.setImage(new Image(new File("src/images/pause.png").toURI().toString()));
		
	}

	public static void processResume(ActionEvent event) {
		MediaPlayer mediaPlayer = createMediaPlayer();
		mediaPlayer.play();
		FileMenu.IS_PLAY = true;
		
	}

	public static void processPause(ActionEvent event, ImageView imgPlayPause) {
		MediaPlayer mediaPlayer = createMediaPlayer();
		mediaPlayer.pause();
		FileMenu.IS_PLAY = false;
		imgPlayPause.setImage(new Image(new File("src/images/play.png").toURI().toString()));
			
	}
	
	public static void processPlayPause(ActionEvent event, ImageView imgPlayPause, ImageView imgPlayOnScreen,
			AnchorPane processPlayOnScreen) {
		MediaPlayer mediaPlayer = createMediaPlayer();

		if (FileMenu.IS_PLAY) {
			processPause(event, imgPlayPause);
			imgPlayOnScreen.setImage(new Image(new File("src/images/pause_screen.png").toURI().toString()));
		} else {
			processPlay(event, imgPlayPause);
			imgPlayOnScreen.setImage(new Image(new File("src/images/play_screen.png").toURI().toString()));
		}
		processPlayOnScreen.setVisible(true);

		mediaPlayer.setOnEndOfMedia(() -> {
		});
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), e -> {
			processPlayOnScreen.setVisible(false);
		}));
		timeline.setCycleCount(1);
		timeline.play();
	}

	public static void processStop(ActionEvent event, ImageView imgPlayPause) {
		MediaPlayer mediaPlayer = createMediaPlayer();
		FileMenu.IS_PLAY=false;
		imgPlayPause.setImage(new Image(new File("src/images/play.png").toURI().toString()));
		mediaPlayer.stop();
		
	}

	
//Jump Backward
	public static void processMinus5s(ActionEvent event) {
		MediaPlayer mediaPlayer = createMediaPlayer();
		mediaPlayer.seek(mediaPlayer.getCurrentTime().subtract(Duration.seconds(5)));
		
	}

	public static void processMinus10s(ActionEvent event, AnchorPane processTenBackward, AnchorPane processTenForward) {
		MediaPlayer mediaPlayer = createMediaPlayer();
		mediaPlayer.seek(mediaPlayer.getCurrentTime().subtract(Duration.seconds(10)));
		processTenBackward.setVisible(true);
		processTenForward.setVisible(false);
		mediaPlayer.setOnEndOfMedia(() -> {
		});
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.3), e -> {
			processTenBackward.setVisible(false);
		}));
		timeline.setCycleCount(1);
		timeline.play();
		
	}

	public static void processMinus1min(ActionEvent event) {
		MediaPlayer mediaPlayer = createMediaPlayer();
		mediaPlayer.seek(mediaPlayer.getCurrentTime().subtract(Duration.seconds(60)));
		
	}

	
//Jump Forward
	public static void processPlus5s(ActionEvent event) {
		MediaPlayer mediaPlayer = createMediaPlayer();
		mediaPlayer.seek(mediaPlayer.getCurrentTime().add(Duration.seconds(5)));
		
	}

	public static void processPlus10s(ActionEvent event, AnchorPane processTenForward, AnchorPane processTenBackward) {
		MediaPlayer mediaPlayer = createMediaPlayer();
		mediaPlayer.seek(mediaPlayer.getCurrentTime().add(Duration.seconds(10)));
		processTenForward.setVisible(true);
		processTenBackward.setVisible(false);

		mediaPlayer.setOnEndOfMedia(() -> {
			// isSeekingForward = false;
		});
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.3), e -> {
			processTenForward.setVisible(false);
		}));
		timeline.setCycleCount(1);
		timeline.play();
		
	}

	public static void processPlus1min(ActionEvent event) {
		MediaPlayer mediaPlayer = createMediaPlayer();
		mediaPlayer.seek(mediaPlayer.getCurrentTime().add(Duration.seconds(60)));
		
	}

	
//Slow
	public static void processSlow2x(ActionEvent event) {
		MediaPlayer mediaPlayer = createMediaPlayer();
		mediaPlayer.setMute(false);
		mediaPlayer.setRate(0.7);
		//mediaPlayer.setVolume(0.0);
		
	}

	public static void processSlow4x(ActionEvent event) {
		MediaPlayer mediaPlayer = createMediaPlayer();
		mediaPlayer.setMute(true);
		mediaPlayer.setRate(0.25);
		//mediaPlayer.setVolume(0.0);
		
	}

	public static void processSlow6x(ActionEvent event) {
		MediaPlayer mediaPlayer = createMediaPlayer();
		mediaPlayer.setMute(true);
		mediaPlayer.setRate(0.166);
		//mediaPlayer.setVolume(0.0);
		
	}

	
//Fast
	public static void processFast2x(ActionEvent event) {
		MediaPlayer mediaPlayer = createMediaPlayer();
		mediaPlayer.setMute(false);
		mediaPlayer.setRate(1.5);
		//mediaPlayer.setVolume(0.0);
		
	}

	public static void processFast4x(ActionEvent event) {
		MediaPlayer mediaPlayer = createMediaPlayer();
		mediaPlayer.setMute(true);
		mediaPlayer.setRate(4.0);
		//mediaPlayer.setVolume(0.0);
		
	}

	public static void processFast6x(ActionEvent event) {
		MediaPlayer mediaPlayer = createMediaPlayer();
		mediaPlayer.setMute(true);
		mediaPlayer.setRate(6.0);
		//mediaPlayer.setVolume(0.0);
		
	}

	
//Normal
	public static void process1x(ActionEvent event) {
		MediaPlayer mediaPlayer = createMediaPlayer();
		mediaPlayer.setMute(false);
		mediaPlayer.setRate(1.0);
		//mediaPlayer.setVolume(100);
		
	}
	
	public static void processMousePlayPause(MouseEvent event, ImageView imgPlayPause, AnchorPane processPlayOnScreen, ImageView imgPlayOnScreen) {
		MediaPlayer mediaPlayer = createMediaPlayer();
		if (FileMenu.IS_PLAY) {
			FileMenu.IS_PLAY = false;
			imgPlayPause.setImage(new Image(new File("src/images/play.png").toURI().toString()));
			imgPlayOnScreen.setImage(new Image(new File("src/images/pause_screen.png").toURI().toString()));
			mediaPlayer.pause();
		} else {
			mediaPlayer.play();
			FileMenu.IS_PLAY = true;
			imgPlayPause.setImage(new Image(new File("src/images/pause.png").toURI().toString()));
			imgPlayOnScreen.setImage(new Image(new File("src/images/play_screen.png").toURI().toString()));
		}
		
		processPlayOnScreen.setVisible(true);
		
		mediaPlayer.setOnEndOfMedia(() -> {
		});
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), e -> {
			processPlayOnScreen.setVisible(false);
		}));
		timeline.setCycleCount(1);
		timeline.play();


//		int playState = FileMenu.IS_PLAY ? 1 : 0;
//		
//		switch (playState) {
//		    case 0:
//		        FileMenu.IS_PLAY = true;
//		        mediaPlayer.play();
//		        imgPlayPause.setImage(new Image(new File("src/images/pause.png").toURI().toString()));
//		        break;
//		    case 1:
//		        FileMenu.IS_PLAY = false;
//		        mediaPlayer.pause();
//		        imgPlayPause.setImage(new Image(new File("src/images/play.png").toURI().toString()));
//		        break;
//		        
//		    default:
//		        break;
//		}	
	}
	

}