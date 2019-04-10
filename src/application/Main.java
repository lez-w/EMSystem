package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.MoveWindow;

import java.net.Socket;

public class Main extends Application {
    public static Stage primaryStage;
    public static Socket ss;
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage=primaryStage;
        primaryStage.initStyle(StageStyle.TRANSPARENT);//设置无最大最小样式
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/Main.fxml"));
        MoveWindow.moveStage(primaryStage, pane);//设置窗口可移动
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
