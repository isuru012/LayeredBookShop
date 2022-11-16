package lk.ijse.bookshop.util;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class WindowControll {
    public static void window(AnchorPane pane, ActionEvent actionEvent){
        if(pane==null){
            System.exit(0);
        }else {
            Stage stage = (Stage) pane.getScene().getWindow();
            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setIconified(true);
        }
    }
}
