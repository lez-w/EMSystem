package application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.Dialogs;
import utils.MoveWindow;

import java.io.IOException;
import java.net.Socket;

public class Main extends Application {
    public static Stage primaryStage;
    public static Socket ss;
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage=primaryStage;
        primaryStage.initStyle(StageStyle.TRANSPARENT);//设置无最大最小样式
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
        MoveWindow.moveStage(primaryStage, pane);//设置窗口可移动
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
//        try {
//            //连接到服务器
//            Main.ss = new Socket("127.0.0.1", 6000);
//        } catch (IOException e) {
//            //服务器未启动
//           Alert error = new Alert(Alert.AlertType.ERROR, "服务器未启动，请启动服务器以后重试！");
//			error.initOwner(primaryStage);//设置提示框所属的Stage,以便使用相同图标
//			error.setTitle("服务器错误");
//			error.setHeaderText("Error");
//			error.showAndWait();
//            System.exit(0);
//        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
