package _2SelectedRole.LeadEditor;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
public class Controller_MainForLeadEditor {
    @FXML Button Back;
    @FXML Button Authors;
    @FXML Button Manuscripts;
    @FXML Button Books;
    @FXML Button Stages;
    @FXML Button Book_edition;
    @FXML void initialize(){
        Back.setOnAction(event -> {
            openNewScene("/_1Authorization/Authorization.fxml", "Авторизация/", "/assets/book.png");
        });
        Authors.setOnAction(event -> {
            openNewScene("/_3ButtonsForSelectedRole/InsertUpdateDelete/LeadEditor/IUD_Authors.fxml", "Менеджер/Авторы", "/assets/option.png");
        });
        Manuscripts.setOnAction(event -> {
            openNewScene("/_3ButtonsForSelectedRole/InsertUpdateDelete/LeadEditor/IUD_Manuscripts.fxml", "Менеджер/Рукописи", "/assets/option.png");
        });
        Books.setOnAction(event -> {
            openNewScene("/_3ButtonsForSelectedRole/InsertUpdateDelete/LeadEditor/IUD_Books.fxml", "Менеджер/Книги", "/assets/option.png");
        });
        Stages.setOnAction(event -> {
            openNewScene("/_3ButtonsForSelectedRole/InsertUpdateDelete/LeadEditor/IUD_Stages.fxml", "Менеджер/Этапы", "/assets/option.png");
        });
        Book_edition.setOnAction(event -> {
            openNewScene("/_3ButtonsForSelectedRole/InsertUpdateDelete/LeadEditor/IUD_Book_edition.fxml", "Менеджер/Издательство книги", "/assets/option.png");
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
