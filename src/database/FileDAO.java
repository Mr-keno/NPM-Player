package database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Duration;

public class FileDAO {
	public static void writePath(String title, String path, String duration, String database) throws IOException {
		File pathFile = new File(database);
		BufferedWriter pathWriter = new BufferedWriter(new FileWriter(pathFile, true));
		pathWriter.write(title + " : " + path + " : " + duration + "\r\n");
		pathWriter.flush();
		pathWriter.close();

		System.out.println("successfully wrote : " + path);

	}

	public static ObservableList<VideoFile> readPath(String path) throws IOException {
		File pathFile = new File(path);
		BufferedReader reader = new BufferedReader(new FileReader(pathFile));
		String line;
		ObservableList<VideoFile> videoFiles = FXCollections.observableArrayList();

		Set<String> seenLines = new HashSet<>();

		while ((line = reader.readLine()) != null) {

			if (!seenLines.contains(line)) {
				seenLines.add(line);
				String[] values = line.split(" : ");
				videoFiles.add(new VideoFile(values[0], values[1], values[2]));
				
			}

		}
		reader.close();

		return videoFiles;
	}

	public static void showAlertWithAutoClose(String contentText) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(contentText);
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> alert.close()));
		timeline.setCycleCount(1); // Run once
		timeline.play();

		alert.showAndWait();
	}

	public static void clearList(String filePath) throws IOException {
		// Open the file for writing, which will truncate it (remove all content)
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			// Writing nothing effectively deletes all content
		}
	}

}
