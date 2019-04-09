package controller;

import application.Main;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    public AnchorPane anchorPane,pane1;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer leftDrawer;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            AnchorPane box = FXMLLoader.load(getClass().getResource("../view/Menu.fxml"));
            AnchorPane a = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));

            leftDrawer.setSidePane(box);
            leftDrawer.setOverLayVisible(false);//设置JFXDrawer不显示背景阴影
            for(Node btn : box.getChildren()){
                if (btn.getAccessibleText() != null){//按钮的AccessibleText不为空
                    btn.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {//点击
                        switch (btn.getAccessibleText()){
                            case "btn_1":anchorPane.setBackground(new Background(new BackgroundFill(Paint.valueOf("#000000"),CornerRadii.EMPTY, Insets.EMPTY)));
                            break;
                            case "btn_2":anchorPane.setBackground(new Background(new BackgroundFill(Paint.valueOf("#FFFFFF"),CornerRadii.EMPTY, Insets.EMPTY)));
                            break;
                            case "btn_3":
                                AnchorPane b = null;
                                try {
                                    b = FXMLLoader.load(getClass().getResource("../view/Monitoring.fxml"));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                pane1.getChildren().setAll(b);
                            break;
                            case "btn_4": pane1.getChildren().setAll(a);
                            break;
                            case "btn_5":System.exit(0);
                        }
                    });
                }
            }

            HamburgerBackArrowBasicTransition burgerTask = new HamburgerBackArrowBasicTransition(hamburger);
            burgerTask.setRate(-1);
            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED,(event -> {
                burgerTask.setRate(burgerTask.getRate() * (-1));
                burgerTask.play();

                if (leftDrawer.isOpened()){
                    leftDrawer.close();
                }else{
                    leftDrawer.open();
                }
            }));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void btnClose(ActionEvent event) {//关闭
        System.exit(0);

    }

    @FXML
    protected void btnMin(ActionEvent event) {
        //Main.primaryStage.toBack();//最小化
        Main.primaryStage.setIconified(true);//最小化
    }


}
