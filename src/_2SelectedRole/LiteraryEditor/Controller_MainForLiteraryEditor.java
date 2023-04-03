package _2SelectedRole.LiteraryEditor;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import static _1Authorization.Main.openNewScene;

public class Controller_MainForLiteraryEditor {

    @FXML Button Back;
    @FXML Button Authors;
    @FXML Button Books;

    @FXML void initialize(){
        Back.setOnAction(event -> {
            openNewScene("/_1Authorization/Authorization.fxml", "Авторизация/", "/assets/book.png");
        });
        Authors.setOnAction(event -> {
            openNewScene("/_3ButtonsForSelectedRole/Watch/LiteraryEditor/Watch_Authors.fxml", "Литературный редактор/Авторы", "/assets/option.png");
        });
        Books.setOnAction(event -> {
            openNewScene("/_3ButtonsForSelectedRole/Watch/LiteraryEditor/Watch_Books.fxml", "Литературный редактор/Книги", "/assets/option.png");
        });
    }
}
