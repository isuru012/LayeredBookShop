package lk.ijse.bookshop.controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.bookshop.util.Navigation;
import lk.ijse.bookshop.util.Routes;
import lk.ijse.bookshop.util.WindowControll;

import java.io.IOException;

public class LoadingScreenController {
    public ProgressBar progressBar;

    public ImageView imageView;
    public Label lblLoad;
    public AnchorPane pane;
    public JFXButton btnminimize;
    Thread t1;
    static int i = 0;


    public void initialize() throws IOException {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                lblLoad.setText(String.valueOf(progressBar.getProgress() * 100));
            }
        });
        t1 = new Thread() {
            @Override
            public void run() {
                while (progressBar.getProgress() <= 1) {

                    progressBar.setProgress(progressBar.getProgress() + 0.1);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if (lblLoad.getText().equals("100%")) {
                                try {
                                    Navigation.navigate(Routes.LOGIN,pane);
                                    
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                i += 10;
                                lblLoad.setText(i + "%");
                            }
                        }
                    });


                    try {
                        t1.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        };
        t1.start();


    }


    public void minimizeOnAction(ActionEvent actionEvent) {
        WindowControll.window(pane,actionEvent);
    }

    public void closeOnAction(ActionEvent actionEvent) {
        WindowControll.window(null,actionEvent);
    }
}
