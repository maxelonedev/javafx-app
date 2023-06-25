package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import static Controllers.AuthController.openNewScene;

public class LiteraryEditorController {

    @FXML Button btnBack;
    @FXML Button btnAuthors;
    @FXML Button btnBooks;

    @FXML void initialize(){
        btnBack.setOnAction(event -> {
            btnBack.getScene().getWindow().hide();
            openNewScene("/Views/Auth.fxml", "Auth", "/Views/Assets/Book.png");
        });
        // !
        btnAuthors.setOnAction(event -> {
            btnAuthors.getScene().getWindow().hide();
            openNewScene("/Views/LiteraryEditorAuthors.fxml", "Authors", "/Views/Assets/Option.png");
        });
        btnBooks.setOnAction(event -> {
            btnBooks.getScene().getWindow().hide();
            openNewScene("/Views/LiteraryEditorBooks.fxml", "Books", "/Views/Assets/Option.png");
        });
    }
}
