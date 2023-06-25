package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import static Controllers.AuthController.openNewScene;

public class CorrectorController {

    @FXML Button btnBack, btnStages, btnBooks;

    @FXML void initialize(){
        btnBack.setOnAction(event -> {
            btnBack.getScene().getWindow().hide();
            openNewScene("/Views/Auth.fxml", "Auth", "/Views/Assets/Book.png");
        });
        // !
        btnBooks.setOnAction(event -> {
            openNewScene("/_3ButtonsForSelectedRole/Watch/Corrector/IllustratorBooks.fxml", "Books", "/Views/Assets/Option.png");
            btnBooks.getScene().getWindow().hide();
        });
        btnStages.setOnAction(event -> {
            openNewScene("/_3ButtonsForSelectedRole/Watch/Corrector/IllustratorStages.fxml", "Stages", "/Views/Assets/Option.png");
            btnStages.getScene().getWindow().hide();
        });
    }
}
