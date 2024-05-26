package menu_bar;

import java.io.File;
import java.io.IOException;
import application.VideoPlayerController;
import database.FileDAO;
import database.FileMode;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;



public class FileMenu {

	public static Media MEDIA;
	public static MediaView MEDIA_VIEW;
	static boolean IS_PLAY;
	static boolean IS_MUTE;
	public static File VIDEO_FILE;
	public static String END_TIME;
	public static FileMode MODE = FileMode.RECENT;
	public static boolean IS_NULL = true;
	public static boolean IS_CLOSE = true;

	// -- open File Start -- //
	public static void processOpenFile(ActionEvent event, String initialPath, MediaView mediaView,
			ImageView imgViewLogo, Slider seekerSlider, Slider volumeSlider, Label lblStart, Label lblEnd, 
			Label lblVideoName ,ImageView imgPlayPause, Label lblLogo, ImageView imgMuteUnmute) {

		if(!IS_NULL) {
			processClose(event, imgViewLogo, seekerSlider, lblStart, lblEnd, lblVideoName, lblLogo);
		}

		FileChooser fileChooser = new FileChooser();
		if (!initialPath.isEmpty()) {
			fileChooser.setInitialDirectory(new File(initialPath));
		}
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Video Files", "*.mp4", "*.mkv", "*.flv", "*.mp3", "*.wav"));

		VIDEO_FILE = fileChooser.showOpenDialog(new Stage());
		File videoFile = VIDEO_FILE;

		if (videoFile == null) {
			return;
		}

		initialPath = videoFile.getParent();
		String videoPath = videoFile.toURI().toString();
		
		processPlayMedia(imgViewLogo, videoPath, mediaView, volumeSlider, seekerSlider, lblStart, lblEnd, imgPlayPause, lblVideoName, lblLogo, imgMuteUnmute);

		return;

	}

	private static void sliderFunction(Slider volumeSlider, Slider seekerSlider, MediaPlayer mediaPlayer,
			Label lblStart, Label lblEnd, Label lblVideoName, ImageView imgPlayPause, ImageView imgMuteUnmute) {
		volumeSlider.setValue(mediaPlayer.getVolume() * 100);
		volumeSlider.valueProperty().addListener(observable -> {
			mediaPlayer.setMute(false);
			mediaPlayer.setVolume(volumeSlider.getValue() / 100);
		});

		// seekerSlider
		mediaPlayer.setOnReady(() -> {
			seekerSlider.setMax(mediaPlayer.getTotalDuration().toSeconds());
			MEDIA_VIEW.requestFocus();
			showEndTime(mediaPlayer, lblEnd);
			timeCounting(mediaPlayer, lblStart);
			imgPlayPause.setImage(new Image(new File("src/images/pause.png").toURI().toString()));
			imgMuteUnmute.setImage(new Image(new File("src/images/volume.png").toURI().toString()));
			MEDIA_VIEW.setRotate(0);
			mediaPlayer.setRate(1.0);
			mediaPlayer.setMute(false);
			
			IS_PLAY = true;
			IS_MUTE = false;
			IS_NULL = false;
			IS_CLOSE = false;

			try {
				FileDAO.writePath(VIDEO_FILE.getName(), VIDEO_FILE.getAbsolutePath(), END_TIME,
						"src/database/recentFile.txt");
				// FileDAO.readPath();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
		
		lblVideoName.setVisible(true);
		lblVideoName.setText(VIDEO_FILE.getName());
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3.0), e -> {
			lblVideoName.setVisible(false);
		}));
		timeline.setCycleCount(1);
		timeline.play();

		mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
			seekerSlider.setValue(newValue.toSeconds());
		});

		seekerSlider.setOnMouseDragged(mouseEvent -> {
			mediaPlayer.seek(Duration.seconds(seekerSlider.getValue()));
		});

		seekerSlider.setOnMousePressed(mouseEvent -> {
			mediaPlayer.seek(Duration.seconds(seekerSlider.getValue()));
		});

	}

	private static void showEndTime(MediaPlayer mediaPlayer, Label lblEnd) {
		long totalSeconds = (long) mediaPlayer.getTotalDuration().toSeconds();

		long hour = totalSeconds / 3600;
		long minute = (totalSeconds % 3600) / 60;
		long second = totalSeconds % 60;

		END_TIME = String.format("%02d:%02d:%02d", hour, minute, second);

		lblEnd.setText(END_TIME);
	}

	private static void timeCounting(MediaPlayer mediaPlayer, Label lblStart) {

		mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
			String formatedTime = updateTime(newValue);
			lblStart.setText(formatedTime);
		});

	}

	public static String updateTime(Duration duration) {
		long secondCount = (long) duration.toSeconds();
		long minuteCount = secondCount / 60;
		secondCount = secondCount % 60;
		long hourCount = minuteCount / 60;
		minuteCount = minuteCount % 60;

		String formattedTime = String.format("%02d:%02d:%02d", hourCount, minuteCount, secondCount);
		return formattedTime;
	}
	// -- open File End -- //

	// -- play list, recent, favorite start --
	public static void processPlaylist() {

	}

	public static void processRecentFile() {

	}

	public static void processFavorite() {

	}
	// -- play list, recent, favorite end --

	// -- Close and Quit start --
	public static void processClose(ActionEvent event, ImageView imgViewLogo, Slider seekerSlider, Label lblStart,
			Label lblEnd, Label lblVideoName, Label lblLogo) {
		MediaPlayer mediaPlayer = MEDIA_VIEW.getMediaPlayer();
		if(mediaPlayer != null) {
			mediaPlayer.stop();
		}
		MEDIA_VIEW.setMediaPlayer(null);
	//	mediaPlayer.dispose();
		seekerSlider.setValue(0.0);
		lblStart.setText("00:00:00");
		lblEnd.setText("00:00:00");
		MEDIA_VIEW.setVisible(false);
		
		imgViewLogo.setImage(new Image(new File("src/images/mainLogo.png").toURI().toString()));
		imgViewLogo.setVisible(true);
		lblLogo.setVisible(true);
	    
	    lblVideoName.setVisible(false);
		IS_CLOSE = true;
		
	}

	public static void processQuit(Stage primaryStage) {
		if (primaryStage != null) {
			primaryStage.close();
		}
	}
	// -- Close and Quit end --
	
	public static void processPlayMedia(ImageView imgViewLogo, String videoPath, MediaView mediaView, Slider volumeSlider, Slider seekerSlider, Label lblStart, Label lblEnd, ImageView imgPlayPause, Label lblVideoName, Label lblLogo, ImageView imgMuteUnmute) {
		
		
		imgViewLogo.setVisible(false);
		lblLogo.setVisible(false);
		
		MEDIA = new Media(videoPath);
		MediaPlayer mediaPlayer = new MediaPlayer(MEDIA);
		mediaView.setMediaPlayer(mediaPlayer);
		mediaPlayer.setAutoPlay(true);
		
		if (videoPath.contains(".mp3") || videoPath.contains(".wav")) {
			imgViewLogo.setImage(new Image(new File("src/images/audioPlaying.gif").toURI().toString()));
			imgViewLogo.setFitHeight(300.0);
			imgViewLogo.setFitWidth(300.0);
			imgViewLogo.setVisible(true);
		} 
		
		MEDIA_VIEW = mediaView;
		MEDIA_VIEW.setVisible(true);
		MEDIA_VIEW.getMediaPlayer().setOnEndOfMedia(() -> {
			MEDIA_VIEW.setVisible(false);
			imgViewLogo.setVisible(true);
			lblLogo.setVisible(true);
			processClose(null , imgViewLogo, seekerSlider, lblStart, lblEnd, lblVideoName, lblLogo);			
		});
		
//		DoubleProperty widthProperty = mediaView.fitWidthProperty();
//		DoubleProperty heightProperty = mediaView.fitHeightProperty();
//
//		widthProperty.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
//		heightProperty.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));

//		mediaView.fitWidthProperty().bind(Main.getPrimaryStage().widthProperty());
//		mediaView.fitHeightProperty().bind(Main.getPrimaryStage().heightProperty());
		
		mediaView.fitWidthProperty().bind(VideoPlayerController.video.getGridView().widthProperty());
		mediaView.fitHeightProperty().bind(VideoPlayerController.video.getGridView().heightProperty());

		sliderFunction(volumeSlider, seekerSlider, mediaPlayer, lblStart, lblEnd, lblVideoName, imgPlayPause, imgMuteUnmute);
		
	}

}
