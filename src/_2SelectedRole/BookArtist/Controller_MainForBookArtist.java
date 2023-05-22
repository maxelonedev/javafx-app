package _2SelectedRole.BookArtist;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import static _1Authorization.Main.openNewScene;

public class Controller_MainForBookArtist {

    @FXML Button Back;
    @FXML Button Stages;
    @FXML Button Books;

    @FXML void initialize(){
        Back.setOnAction(event -> {
            openNewScene("/_1Authorization/Authorization.fxml", "Авторизация/", "/assets/book.png");
            Back.getScene().getWindow().hide();
        });
        Stages.setOnAction(event -> {
            openNewScene("/_3ButtonsForSelectedRole/Watch/BookArtist/Update_Stages.fxml", "Иллюстратор/Этапы", "/assets/option.png");
            Stages.getScene().getWindow().hide();
        });
        Books.setOnAction(event -> {
            openNewScene("/_3ButtonsForSelectedRole/Watch/BookArtist/Watch_Books.fxml", "Иллюстратор/Книги", "/assets/option.png");
            Books.getScene().getWindow().hide();
        });
    }
}
