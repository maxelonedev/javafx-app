package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import static Controllers.AuthController.openNewScene;

public class IllustratorController {

    @FXML Button btnBack, btnStages, btnBooks;

    @FXML void initialize(){
        btnBack.setOnAction(event -> {
            btnBack.getScene().getWindow().hide();
            openNewScene("/Views/Auth.fxml", "Auth", "/Views/Assets/Book.png");
        });
        btnStages.setOnAction(event -> {
            btnStages.getScene().getWindow().hide();
            openNewScene("/Views/IllustratorStages.fxml", "Stages", "/Views/Assets/Option.png");
        });
        btnBooks.setOnAction(event -> {
            btnBooks.getScene().getWindow().hide();
            openNewScene("/Views/IllustratorBooks.fxml", "Books", "/Views/Assets/Option.png");
        });
    }
}
