package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class MapController implements Initializable {
    @FXML
    private WebView webView;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WebEngine webEngine = webView.getEngine();
        URL url = getClass().getResource("../resource/video/map.html");
        webEngine.load(url.toExternalForm());
    }
}
