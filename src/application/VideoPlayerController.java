package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import database.FileDAO;
import database.FileMode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import menu_bar.AudioMenu;
import menu_bar.FileMenu;
import menu_bar.HelpMenu;
import menu_bar.PlayMenu;
import menu_bar.PlaylistMenu;
import menu_bar.VideoMenu;
import menu_bar.ViewMenu;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;

public class VideoPlayerController implements Initializable {

	@FXML
	private GridPane gridView;

	@FXML
	private ImageView imgMuteUnmute;

	@FXML
	private ImageView imgViewLogo;

	@FXML
	private ImageView imgPlayPause;

	@FXML
	private MediaView mediaView;

	@FXML
	private AnchorPane processTenBackward;

	@FXML
	private AnchorPane processTenForward;

	@FXML
	private AnchorPane processPlayOnScreen;

	@FXML
	private AnchorPane processVolumeOnScreen;

	@FXML
	private ImageView imgVolumeUpOnScreen;

	@FXML
	private ImageView imgPlayOnScreen;

	@FXML
	private Slider seekerSlider;

	@FXML
	private Slider volumeSlider;

	@FXML
	private Label lblEnd;

	@FXML
	private Label lblVideoName;

	@FXML
	private Label lblStart;

	@FXML
	private Label lblVolumeOnScreen;
	
	@FXML
    private Label lblLogo;

	@FXML
	private GridPane menuBar;

	@FXML
	private GridPane videoControlBar;

	@FXML
	private GridPane toolBar;

	public VideoPlayerController() {
		// TODO Auto-generated constructor stub
	}

	public VideoPlayerController(ImageView imgMuteUnmute, ImageView imgViewLogo, ImageView imgPlayPause,
			MediaView mediaView, AnchorPane processTenBackward, AnchorPane processTenForward,
			AnchorPane processPlayOnScreen, AnchorPane processVolumeOnScreen, ImageView imgVolumeUpOnScreen,
			ImageView imgPlayOnScreen, Slider seekerSlider, Slider volumeSlider, Label lblEnd, Label lblVideoName,
			Label lblStart, Label lblVolumeOnScreen, GridPane menuBar, GridPane videoControlBar, GridPane toolBar,
			GridPane gridView, Label lblLogo) {
		super();
		this.imgMuteUnmute = imgMuteUnmute;
		this.imgViewLogo = imgViewLogo;
		this.imgPlayPause = imgPlayPause;
		this.mediaView = mediaView;
		this.processTenBackward = processTenBackward;
		this.processTenForward = processTenForward;
		this.processPlayOnScreen = processPlayOnScreen;
		this.processVolumeOnScreen = processVolumeOnScreen;
		this.imgVolumeUpOnScreen = imgVolumeUpOnScreen;
		this.imgPlayOnScreen = imgPlayOnScreen;
		this.seekerSlider = seekerSlider;
		this.volumeSlider = volumeSlider;
		this.lblEnd = lblEnd;
		this.lblVideoName = lblVideoName;
		this.lblStart = lblStart;
		this.lblVolumeOnScreen = lblVolumeOnScreen;
		this.menuBar = menuBar;
		this.videoControlBar = videoControlBar;
		this.toolBar = toolBar;
		this.gridView = gridView;
		this.lblLogo = lblLogo;
	}

	public static VideoPlayerController video;

	
	private String initialPath = "/media/nayhtet/DATA/MOVIES";
//	private String initialPath = "";
	

	// -- File Menu Start --
	@FXML
	void processOpenFIle(ActionEvent event) {
		FileMenu.processOpenFile(event, initialPath, mediaView, imgViewLogo, seekerSlider, volumeSlider, lblStart,
				lblEnd, lblVideoName, imgPlayPause, lblLogo, imgMuteUnmute);
	}

	public void processPlayMedia(String path) {
		FileMenu.processPlayMedia(video.imgViewLogo, path, video.mediaView, video.volumeSlider, video.seekerSlider, video.lblStart, 
				video.lblEnd, video.imgPlayPause, video.lblVideoName, video.lblLogo, video.imgMuteUnmute);
	}

	@FXML
	void processQuit(ActionEvent event) {
		FileMenu.processQuit(Main.getPrimaryStage());
	}

	@FXML
	void processClose(ActionEvent event) {
		FileMenu.processClose(event, imgViewLogo, seekerSlider, lblStart, lblEnd, lblVideoName, lblLogo);
	}

	@FXML
	void processOpenPlayList(ActionEvent event) {
		FileMenu.MODE = FileMode.PLAYLIST;
		new PlaylistMenu().processPlayList(event);
	}

	@FXML
	void processOpenFavorite(ActionEvent event) {
		FileMenu.MODE = FileMode.FAVORITE;
		new PlaylistMenu().processPlayList(event);
	}

	@FXML
	void processOpenRecent(ActionEvent event) {
		FileMenu.MODE = FileMode.RECENT;
		new PlaylistMenu().processPlayList(event);
	}

	@FXML
	void processAddToFavorite(ActionEvent event) {

		FileDAO.showAlertWithAutoClose("This video is added to favorite list!");

		try {
			FileDAO.writePath(FileMenu.VIDEO_FILE.getName(), FileMenu.VIDEO_FILE.getAbsolutePath(), FileMenu.END_TIME,
					"src/database/favoriteFile.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void processAddToPlaylist(ActionEvent event) {
		FileDAO.showAlertWithAutoClose("This video is added to playlist!");

		try {
			FileDAO.writePath(FileMenu.VIDEO_FILE.getName(), FileMenu.VIDEO_FILE.getAbsolutePath(), FileMenu.END_TIME,
					"src/database/playList.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// -- File Menu End --

	// -- Play Menu Start --
	@FXML
	void processPlayPause(ActionEvent event) {
		PlayMenu.processPlayPause(event, imgPlayPause, imgPlayOnScreen, processPlayOnScreen);
	}

	@FXML
	void processPlay(ActionEvent event) {
		PlayMenu.processPlay(event, imgPlayPause);
	}

	@FXML
	void processResume(ActionEvent event) {
		PlayMenu.processResume(event);
	}

	@FXML
	void processPause(ActionEvent event) {
		PlayMenu.processPause(event, imgPlayPause);
	}

	@FXML
	void processStop(ActionEvent event) {
		PlayMenu.processStop(event, imgPlayPause);
	}

	@FXML
	void processPlus5s(ActionEvent event) {
		PlayMenu.processPlus5s(event);
	}

	@FXML
	void processPlus10s(ActionEvent event) {
		PlayMenu.processPlus10s(event, processTenForward, processTenBackward);
	}

	@FXML
	void processPlus1min(ActionEvent event) {
		PlayMenu.processPlus1min(event);
	}

	@FXML
	void processMinus5s(ActionEvent event) {
		PlayMenu.processMinus5s(event);
	}

	@FXML
	void processMinus10s(ActionEvent event) {
		PlayMenu.processMinus10s(event, processTenBackward, processTenForward);
	}

	@FXML
	void processMinus1min(ActionEvent event) {
		PlayMenu.processMinus1min(event);
	}

	@FXML
	void process1x(ActionEvent event) {
		PlayMenu.process1x(event);
	}

	@FXML
	void processFast2x(ActionEvent event) {
		PlayMenu.processFast2x(event);
	}

	@FXML
	void processFast4x(ActionEvent event) {
		PlayMenu.processFast4x(event);
	}

	@FXML
	void processFast6x(ActionEvent event) {
		PlayMenu.processFast6x(event);
	}

	@FXML
	void processSlow2x(ActionEvent event) {
		PlayMenu.processSlow2x(event);
	}

	@FXML
	void processSlow4x(ActionEvent event) {
		PlayMenu.processSlow4x(event);
	}

	@FXML
	void processSlow6x(ActionEvent event) {
		PlayMenu.processSlow6x(event);
	}
	// -- Play Menu End --

	// -- Video Menu Start --
	@FXML
	void processFullScreen(ActionEvent event) {
		VideoMenu.processfullScreen(event, toolBar, videoControlBar, menuBar);
	}

	// Zoom
	@FXML
	void processZoomAuto(ActionEvent event) {
		VideoMenu.processZoomAuto(event);
	}

	@FXML
	void processZoom25(ActionEvent event) {
		VideoMenu.processZoom25(event);
	}

	@FXML
	void processZoom50(ActionEvent event) {
		VideoMenu.processZoom50(event);
	}

	@FXML
	void processZoom75(ActionEvent event) {
		VideoMenu.processZoom75(event);
	}

	@FXML
	void processZoom100(ActionEvent event) {
		VideoMenu.processZoom100(event);
	}

	@FXML
	void processZoom125(ActionEvent event) {
		VideoMenu.processZoom125(event);
	}

	@FXML
	void processZoom200(ActionEvent event) {
		VideoMenu.processZoom200(event);
	}

	@FXML
	void processZoom300(ActionEvent event) {
		VideoMenu.processZoom300(event);
	}

	// Rotate
	@FXML
	void processRotate180(ActionEvent event) {
		VideoMenu.processRotate180(event, mediaView);
	}

	@FXML
	void processRotate90left(ActionEvent event) {
		VideoMenu.processRotate90anticlockwise(event, mediaView);
	}

	@FXML
	void processRotate90right(ActionEvent event) {
		VideoMenu.processRotate90clockwise(event, mediaView);
	}

	// Aspect Ratio
	@FXML
	void processAspectSixteenToNine(ActionEvent event) {
		VideoMenu.processAspectSixteenToNine(event, mediaView);
	}
	// -- Video Menu End --

	// -- Audio Menu Start --
	@FXML
	void processVolumeUp(ActionEvent event) {
		AudioMenu.processVolumeUp(event, volumeSlider, processVolumeOnScreen, lblVolumeOnScreen, imgVolumeUpOnScreen);
	}

	@FXML
	void processVolumeDown(ActionEvent event) {
		AudioMenu.processVolumeDown(event, volumeSlider, processVolumeOnScreen, lblVolumeOnScreen, imgVolumeUpOnScreen);
	}

	@FXML
	void processMute(ActionEvent event) {
		AudioMenu.processMute(event, volumeSlider, imgMuteUnmute);
	}

	@FXML
	void processUnmute(ActionEvent event) {
		AudioMenu.processUnmute(event, volumeSlider, imgMuteUnmute);
	}

	@FXML
	void processMuteUnmute(ActionEvent event) {
		AudioMenu.processMuteUnmute(event, volumeSlider, imgMuteUnmute);
	}

	@FXML
	void processDelay5s(ActionEvent event) {

	}

	@FXML
	void processFast5s(ActionEvent event) {

	}
	// -- Audio Menu End --

	// -- View Menu Start --
	@FXML
	void processVideoInformation(ActionEvent event) {
		new ViewMenu().processVideoInformation(event);
	}

	// -- View Menu End --

	// -- Subtitle Menu Start --

	// -- Subtitle Menu End --

	// -- Subtitle Menu Start --

	// -- Subtitle Menu End --

	// -- Help Menu Start --
	@FXML
	void processAbout(ActionEvent event) {
		new HelpMenu().processAbout();
	}

	@FXML
	void processCheckForUpdate(ActionEvent event) {
		new HelpMenu().processCheckForUpdate();
	}

	@FXML
	void processShortcutKeys(ActionEvent event) {
		new HelpMenu().processShortcutKeys();
	}

	@FXML
	void processFAQs(ActionEvent event) {
		new HelpMenu().processFAQs();
	}
	
	@FXML
	void processContactUs(ActionEvent event) {
		new HelpMenu().processContactUs();
	}
	// -- Help Menu End --

	// KeyEvent
	@FXML
	void processKeyPressed(KeyEvent event) {
		Scene scene = Main.getScene();
		scene.setOnKeyPressed(action -> handleKeyPressed(event.getCode()));
	}

	private void handleKeyPressed(KeyCode code) {
		switch (code) {
		case SPACE:
			processPlayPause(null);
			break;

		case RIGHT:
			processPlus10s(null);
			break;

		case LEFT:
			processMinus10s(null);
			break;

		case UP:
			processVolumeUp(null);
			break;

		case DOWN:
			processVolumeDown(null);
			break;

		case ENTER:
			processFullScreen(null);
			break;

		default:
			break;
		}
	}

	// MouseMove Event
	@FXML
	void processMouseMove(MouseEvent event) {
		VideoMenu.processMouseMove(event, videoControlBar, menuBar, toolBar, lblVideoName);
	}

	@FXML
	void processMousePlayPause(MouseEvent event) {
		PlayMenu.processMousePlayPause(event, imgPlayPause, processPlayOnScreen, imgPlayOnScreen);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		video = new VideoPlayerController(imgMuteUnmute, imgViewLogo, imgPlayPause, mediaView, processTenBackward,
				processTenForward, processPlayOnScreen, processVolumeOnScreen, imgVolumeUpOnScreen, imgPlayOnScreen,
				seekerSlider, volumeSlider, lblEnd, lblVideoName, lblStart, lblVolumeOnScreen, menuBar, videoControlBar,
				toolBar, gridView, lblLogo);

		processTenForward.setVisible(false);
		processTenBackward.setVisible(false);
		processPlayOnScreen.setVisible(false);
		processVolumeOnScreen.setVisible(false);
		lblVolumeOnScreen.setVisible(false);
		lblVideoName.setVisible(false);
		lblLogo.setVisible(true);
		
		Timeline toolBarHandle = new Timeline(new KeyFrame(Duration.seconds(3.0), e -> {
			toolBar.setVisible(false);
		}));
		toolBarHandle.play();

		imgViewLogo.setVisible(true);
		RotateTransition rotateTransition = new RotateTransition(Duration.seconds(2.5), imgViewLogo);
		rotateTransition.setByAngle(360);
		rotateTransition.setCycleCount(1);
		rotateTransition.setOnFinished(event -> {
			Timeline pauseTimeline = new Timeline(new KeyFrame(Duration.seconds(3), e -> {
				rotateTransition.play();
			}));
			pauseTimeline.play();
		});
		rotateTransition.play();
	}

	public GridPane getGridView() {
		return video.gridView;
	}

	public void setGridView(GridPane gridView) {
		this.gridView = gridView;
	}

}
