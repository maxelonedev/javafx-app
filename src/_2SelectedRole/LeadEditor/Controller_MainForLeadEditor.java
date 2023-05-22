package _2SelectedRole.LeadEditor;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import static _1Authorization.Main.openNewScene;

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
            Back.getScene().getWindow().hide();
        });
        Authors.setOnAction(event -> {
            openNewScene("/_3ButtonsForSelectedRole/InsertUpdateDelete/LeadEditor/IUD_Authors.fxml", "Менеджер/Авторы", "/assets/option.png");
            Authors.getScene().getWindow().hide();
        });
        Manuscripts.setOnAction(event -> {
            openNewScene("/_3ButtonsForSelectedRole/InsertUpdateDelete/LeadEditor/IUD_Manuscripts.fxml", "Менеджер/Рукописи", "/assets/option.png");
            Manuscripts.getScene().getWindow().hide();
        });
        Books.setOnAction(event -> {
            openNewScene("/_3ButtonsForSelectedRole/InsertUpdateDelete/LeadEditor/IUD_Books.fxml", "Менеджер/Книги", "/assets/option.png");
            Books.getScene().getWindow().hide();
        });
        Stages.setOnAction(event -> {
            openNewScene("/_3ButtonsForSelectedRole/InsertUpdateDelete/LeadEditor/IUD_Stages.fxml", "Менеджер/Этапы", "/assets/option.png");
            Stages.getScene().getWindow().hide();
        });
        Book_edition.setOnAction(event -> {
            openNewScene("/_3ButtonsForSelectedRole/InsertUpdateDelete/LeadEditor/IUD_Book_edition.fxml", "Менеджер/Издательство книги", "/assets/option.png");
            Book_edition.getScene().getWindow().hide();
        });
    }
}
