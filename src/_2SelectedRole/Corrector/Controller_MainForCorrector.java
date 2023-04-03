package _2SelectedRole.Corrector;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import static _1Authorization.Main.openNewScene;

public class Controller_MainForCorrector {

    @FXML Button Back;
    @FXML Button Stages;
    @FXML Button Books;

    @FXML void initialize(){
        Back.setOnAction(event -> {
            openNewScene("/_1Authorization/Authorization.fxml", "Авторизация/", "/assets/book.png");
        });
        Books.setOnAction(event -> {
            openNewScene("/_3ButtonsForSelectedRole/Watch/Corrector/Watch_Books.fxml", "Корректор/Книги", "/assets/option.png");
        });
        Stages.setOnAction(event -> {
            openNewScene("/_3ButtonsForSelectedRole/Watch/Corrector/Update_Stages.fxml", "Корректор/Этапы", "/assets/option.png");
        });
    }
}
