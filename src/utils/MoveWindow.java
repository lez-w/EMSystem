package utils;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public final class MoveWindow {
    public static void moveStage(Stage primaryStage, AnchorPane pane) {
        //设置界面拖动
        final double[] xOffset = {0};
        final double[] yOffset = {0};
        pane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset[0] = event.getSceneX();
                yOffset[0] = event.getSceneY();
            }
        });
        //移动到的位置
        pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset[0]);
                primaryStage.setY(event.getScreenY() - yOffset[0]);
            }
        });
    }
}
