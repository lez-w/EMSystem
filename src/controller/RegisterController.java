package controller;

import application.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import model.User;
import utils.Dialogs;
import utils.HttpRequest;
import utils.JsonUtil;
import utils.MoveWindow;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author lzw
 * @Description 注册实现类
 * @date 2019-03-28  22:06:37
 **/
public class RegisterController implements Initializable {

    @FXML
    private JFXTextField uName;

    @FXML
    private JFXButton regBtn;

    @FXML
    private JFXButton backBtn;

    @FXML
    private JFXPasswordField uPwd;

    @FXML
    private JFXPasswordField uPwd1;

    @FXML
    private StackPane pane;

    @FXML
    private AnchorPane root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        root.getChildren().remove(pane);//不显示提示框避免遮挡
    }

    /*
     * @Description 注册方法
     * @params [event]
     * @return void
     * @author lzw
     * @date 2019/3/28 22:28
     **/
    @FXML
    public void registerAction(ActionEvent event) {//注册事件
        root.getChildren().add(pane);//显示提示框
        String userName = uName.getText();
        String userPwd = uPwd.getText();//用户名和密码
        String userPwd1 = uPwd1.getText();
        if (userName.equals("") || userPwd.equals("") || userPwd1.equals("")) {
            Dialogs.showNoInfoDialog("         注册失败", "用户名和密码不能为空。", pane, regBtn, root);
        } else {
            if (userPwd.equals(userPwd1)) {
                User user = new User(userName, userPwd);
                String JSONStr= HttpRequest.sendPost("HTTP://localhost:8080/register", "uName=" + user.getUname() + "&uPwd=" + user.getUpwd());
                boolean flag = JsonUtil.jsonStr2data("code",JSONStr).equals("200");
                if (flag) {
                    //注册成功之后进入主界面
                    Dialogs.showYesInfoDialog("         注册成功", "恭喜你，注册成功！", pane, regBtn, root);
                } else {
                    Dialogs.showNoInfoDialog("         注册失败", "该用户名已经被使用，请重新输入用户名进行注册。", pane, regBtn, root);
                }
            } else {
                Dialogs.showNoInfoDialog("         注册失败", "两次输入的密码不一致，请重新输入。", pane, regBtn, root);
            }
        }
    }

    @FXML
    public void backAction(ActionEvent event) {//返回
        try {
            AnchorPane myRoot = FXMLLoader.load(Main.class.getResource("../view/Login.fxml"));
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
