package ru.kpfu.itis.browser;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BrowserController extends Application implements Initializable {

    private final String DEFAULT_ADDRESS = "https://yandex.ru/";

    private List<String> history;
    private int currentPage;

    @FXML private WebView webView;
    @FXML private TextField textFieldAddress;
    @FXML private Text textStatus;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("browser.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Simple browser");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        load(DEFAULT_ADDRESS);
        history = new ArrayList<>();
        history.add(DEFAULT_ADDRESS);
        currentPage = 0;
        updateTextFieldAddress();
    }

    private void load(String url) {
        try {
            webView.getEngine().load((new URL(url)).toString());
            updateTextStatus(StatusTitles.SUCCESS.getDescription());
        } catch (MalformedURLException ex) {
            updateTextStatus(StatusTitles.ERROR.getDescription());
        }
    }

    private void updateTextFieldAddress() {
        textFieldAddress.setText(history.get(currentPage));
    }

    private void updateTextStatus(String text) {
        textStatus.setText(text);
    }

    @FXML
    private void buttonPrevOnClicked(ActionEvent actionEvent) {
        if (currentPage > 0) {
            currentPage--;
            load(history.get(currentPage));
            updateTextFieldAddress();
        }
    }

    @FXML
    private void buttonNextOnClicked(ActionEvent actionEvent) {
        if (currentPage < history.size() - 1) {
            currentPage++;
            load(history.get(currentPage));
            updateTextFieldAddress();
        }
    }

    @FXML
    private void buttonGoOnClicked(ActionEvent actionEvent) {
        load(textFieldAddress.getText());
        history.add(textFieldAddress.getText());
        currentPage = history.size() - 1;
    }

}
