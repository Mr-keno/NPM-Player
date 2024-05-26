package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import menu_bar.HelpMenu;

public class AboutController {

    @FXML
    void processReturn(ActionEvent event) {
    	HelpMenu.getHelpStage().close();
    }

}
