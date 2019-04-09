package controller;

import application.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import utils.Dialogs;
import utils.HttpRequest;
import utils.JsonUtil;
import utils.MoveWindow;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXMLViewFlowContext
    private ViewFlowContext context;

    @FXML
    private MediaView mediaView;

    @FXML
    private JFXTextField uName;

    @FXML
    private JFXPasswordField uPwd;

    @FXML
    private JFXButton registerBtn;

    @FXML
    private StackPane dialogPane;

    @FXML
    private JFXButton loginBtn;

    @FXML
    private AnchorPane pane;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pane.getChildren().remove(dialogPane);//不显示提示框避免遮挡
        //设置登陆界面背景为一个视频
        URL url = getClass().getResource("../resource/video/登录背景.mp4");
        Media media = new Media(url.toExternalForm());
        MediaPlayer player = new MediaPlayer(media);
        mediaView.setMediaPlayer(player);
        player.play();

        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("用户名不能为空!");
        uName.getValidators().add(validator);
        uName.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) uName.validate();
        });

        validator = new RequiredFieldValidator();
        validator.setMessage("密码不能为空!");
        uPwd.getValidators().add(validator);
        uPwd.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) uPwd.validate();
        });
    }
    @FXML
    protected void loginAction(ActionEvent event){
        pane.getChildren().add(dialogPane);//显示提示框
        // 登陆事件
        String userName = uName.getText().toString();
        String userPwd = uPwd.getText().toString();// 获取用户输入的用户名和密码
        boolean b =(userPwd.equals(""));
        if (userName.equals("") || userPwd.equals("")) {
            Dialogs.showNoInfoDialog("         登陆失败!","用户名和密码不能为空。",dialogPane,loginBtn,pane);
        }else{
            String JSONStr= HttpRequest.sendPost("http://localhost:8080/login", "uName=" + userName + "&uPwd=" + userPwd);
            boolean flag = JsonUtil.jsonStr2data("code",JSONStr).equals("200");
            if (flag ) {
                // 登陆成功之后进入主界面
                AnchorPane myRoot = null;
                try {
                    myRoot = FXMLLoader.load(Main.class.getResource("../view/Main.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene scene = new Scene(myRoot);
                Main.primaryStage.setScene(scene);
                Main.primaryStage.show();
            } else {//密码错误
                Dialogs.showNoInfoDialog("         登陆失败!","您输入的用户名或密码错误，请重新输入。",dialogPane,loginBtn,pane);
            }
        }
    }



    @FXML
    public void registerAction(ActionEvent event){//跳转注册页面
        try {
            AnchorPane myRoot = FXMLLoader.load(Main.class.getResource("../view/Register.fxml"));
            Scene scene = new Scene(myRoot);
            MoveWindow.moveStage(Main.primaryStage, myRoot);//设置窗口可移动
            Main.primaryStage.setScene(scene);
            Main.primaryStage.show();
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
