package _2SelectedRole.TechnicalEditor;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
public class Controller_MainForTechnicalEditor {
    @FXML Button Back;
    @FXML Button Authors;
    @FXML Button Books;
    @FXML Button Stages;
    @FXML void initialize(){
        Back.setOnAction(event -> {
            openNewScene("/_1Authorization/Authorization.fxml", "Авторизация/", "/assets/book.png");
        });
        Authors.setOnAction(event -> {
            openNewScene("/_3ButtonsForSelectedRole/Watch/TechnicalEditor/Watch_Authors.fxml",
                    "Технический редактор/Авторы", "/assets/option.png");
        });
        Books.setOnAction(event -> {
            openNewScene("/_3ButtonsForSelectedRole/Watch/TechnicalEditor/Watch_Books.fxml",
                    "Технический редактор/Книги", "/assets/option.png");
        });
        Stages.setOnAction(event -> {
            openNewScene("/_3ButtonsForSelectedRole/Watch/TechnicalEditor/Update_Stages.fxml",
                    "Технический редактор/Этапы", "/assets/option.png");
        });
    }
    public void openNewScene(String window, String title, String image){
        Back.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        try { loader.load(); } catch (IOException e) {e.printStackTrace();}
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        Image Icon = new Image(image);
        stage.getIcons().add(Icon);
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
