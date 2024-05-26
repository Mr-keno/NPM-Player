package menu_bar;

import application.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class VideoMenu {

//full-screen
	
	public static void processfullScreen(ActionEvent event, GridPane toolBar ,GridPane videoControlBar,
			GridPane menuBar) {
		MediaView mediaView = FileMenu.MEDIA_VIEW;
		Stage stage = Main.getPrimaryStage();
		if (stage.isFullScreen()) {
			stage.setFullScreen(false);
			videoControlBar.setVisible(true);
			toolBar.setVisible(false);
			menuBar.setVisible(true);
			resetZoom(mediaView);
//			mediaView.setFitWidth(1360.0);
//			mediaView.setFitHeight(1080.0);

		} else {
			stage.setFullScreen(true);
			toolBar.setVisible(false);
			FileMenu.MEDIA_VIEW.requestFocus();
			applyZoom(mediaView, 1.11, 1.11);
//			mediaView.fitWidthProperty().bind(Main.getPrimaryStage().widthProperty());
//			mediaView.fitHeightProperty().bind(Main.getPrimaryStage().heightProperty());;
		}
	}

//Hide and show Menu Bar and Button bar
	public static void processMouseMove(MouseEvent event, GridPane videoControlBar, GridPane menuBar, GridPane toolBar, Label lblVideoName) {
		// TODO Auto-generated method stub
		//System.out.println(event.getSceneX() + ","+ event.getSceneY());

		// check is full-screen
//		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Stage stage = Main.getPrimaryStage();

		if (stage.isFullScreen()) {
			videoControlBar.setVisible(false);
			menuBar.setVisible(false);
			toolBar.setVisible(false);
			
			if (!FileMenu.IS_CLOSE) {
				lblVideoName.setVisible(true);
				lblVideoName.setText(FileMenu.VIDEO_FILE.getName());
				Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3.0), e -> {
					lblVideoName.setVisible(false);
				}));
				timeline.setCycleCount(1);
				timeline.play();
			}
			

			if (event.getSceneY() > 700) {
				videoControlBar.setVisible(true);
			}
			if (event.getSceneY() < 50) {
				menuBar.setVisible(true);
			}
			if (event.getSceneX() > 1200 && event.getSceneY() < 700) {
				toolBar.setVisible(true);
			}

		} else {
			videoControlBar.setVisible(true);
			menuBar.setVisible(true);
			toolBar.setVisible(false);
			if (event.getSceneX() > 730 && event.getSceneY() > 30) {
				toolBar.setVisible(true);
			}
		}
		
		
	}
		
	//Zoom
		public static void processZoomAuto(ActionEvent event) {
			MediaView mediaView = FileMenu.MEDIA_VIEW;
			resetZoom(mediaView);
			applyZoom(mediaView, 1.0, 1.0);
		}

		public static void processZoom25(ActionEvent event) {
			MediaView mediaView = FileMenu.MEDIA_VIEW;
			resetZoom(mediaView);
			applyZoom(mediaView, 0.25, 0.25);
		}

		public static void processZoom50(ActionEvent event) {
			MediaView mediaView = FileMenu.MEDIA_VIEW;
			resetZoom(mediaView);
			applyZoom(mediaView, 0.5, 0.5);
		}

		public static void processZoom75(ActionEvent event) {
			MediaView mediaView = FileMenu.MEDIA_VIEW;
			resetZoom(mediaView);
			applyZoom(mediaView, 0.75, 0.75);
		}

		public static void processZoom100(ActionEvent event) {
			MediaView mediaView = FileMenu.MEDIA_VIEW;
			resetZoom(mediaView);
			applyZoom(mediaView, 1.0, 1.0);
		}

		public static void processZoom125(ActionEvent event) {
			MediaView mediaView = FileMenu.MEDIA_VIEW;
			resetZoom(mediaView);
			applyZoom(mediaView, 1.25, 1.25);
		}

		public static void processZoom200(ActionEvent event) {
			MediaView mediaView = FileMenu.MEDIA_VIEW;
			resetZoom(mediaView);
			applyZoom(mediaView, 2.0, 2.0);
		}

		public static void processZoom300(ActionEvent event) {
			MediaView mediaView = FileMenu.MEDIA_VIEW;
			resetZoom(mediaView);
			applyZoom(mediaView, 3.0, 3.0);
		}

		private static void resetZoom(MediaView mediaView) {
			mediaView.setScaleX(1.0);
			mediaView.setScaleY(1.0);
		}

		private static void applyZoom(MediaView mediaView, double scaleXFactor, double scaleYFactor) {
			double currentScaleX = mediaView.getScaleX();
			double currentScaleY = mediaView.getScaleY();

			double zoomScaleX = currentScaleX * scaleXFactor;
			double zoomScaleY = currentScaleY * scaleYFactor;

			mediaView.setScaleX(zoomScaleX);
			mediaView.setScaleY(zoomScaleY);
		}
		
		//Rotate
	    public static void processRotate180(ActionEvent event, MediaView mediaView) {
	    	mediaView.setRotate(mediaView.getRotate() + 180.0);
	    }

	    public static void processRotate90anticlockwise(ActionEvent event, MediaView mediaView) {
	    	mediaView.setRotate(mediaView.getRotate()-90.0);
	    }

	    public static void processRotate90clockwise(ActionEvent event, MediaView mediaView) {
	    	mediaView.setRotate(mediaView.getRotate()+90.0);
	    }
	    
	    //Aspect Ratio
	    public static void processAspectSixteenToNine(ActionEvent event, MediaView mediaView) {
	    	mediaView.setFitWidth(1600);
	    	mediaView.setFitHeight(900);
	    }
}