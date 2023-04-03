package _2SelectedRole.ChiefEditor;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
public class Controller_MainForChiefEditor {
    @FXML Button Back;
    @FXML Button Authors;
    @FXML Button Manuscripts;
    @FXML Button Books;
    @FXML Button Book_edition;
    @FXML Button Stages;
    @FXML void initialize(){
        Back.setOnAction(event -> {
            openNewScene("/_1Authorization/Authorization.fxml", "Авторизация/", "/assets/book.png");
        });
        Authors.setOnAction(event -> {
            openNewScene("/_3ButtonsForSelectedRole/InsertUpdateDelete/ChiefEditor/IUD_Authors.fxml",
                    "Главный редактор/Авторы", "/assets/option.png");
        });
        Manuscripts.setOnAction(event -> {
            openNewScene("/_3ButtonsForSelectedRole/InsertUpdateDelete/ChiefEditor/IUD_Manuscripts.fxml",
                    "Главный редактор/Рукописи", "/assets/option.png");
        });
        Books.setOnAction(event -> {
            openNewScene("/_3ButtonsForSelectedRole/InsertUpdateDelete/ChiefEditor/IUD_Books.fxml",
                    "Главный редактор/Книги", "/assets/option.png");
        });
        Book_edition.setOnAction(event -> {
            openNewScene("/_3ButtonsForSelectedRole/InsertUpdateDelete/ChiefEditor/IUD_Book_edition.fxml",
                    "Главный редактор/Издание книги", "/assets/option.png");
        });
        Stages.setOnAction(event -> {
            openNewScene("/_3ButtonsForSelectedRole/InsertUpdateDelete/ChiefEditor/IUD_Stages.fxml",
                    "Главный редактор/Этапы", "/assets/option.png");
        });
    }
    public void openNewScene(String window, String title, String image){
        Back.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        try { loader.load(); } catch (IOException e) {e.printStackTrace();}
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle(title);
        Image Icon = new Image(image);
        stage.getIcons().add(Icon);
        stage.setScene(new Scene(root));
        stage.show();
    }
}

