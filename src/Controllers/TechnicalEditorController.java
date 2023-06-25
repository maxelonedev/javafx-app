package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import static Controllers.AuthController.openNewScene;

public class TechnicalEditorController {

    @FXML Button btnBack, btnAuthors, btnBooks, btnStages;

    @FXML void initialize(){
        btnBack.setOnAction(event -> {
            btnBack.getScene().getWindow().hide();
            openNewScene("/Views/Auth.fxml", "Auth", "/Views/Assets/Book.png");
        });
        // !
        btnAuthors.setOnAction(event -> {
            btnAuthors.getScene().getWindow().hide();
            openNewScene("/_3ButtonsForSelectedRole/Watch/TechnicalEditor/Watch_Authors.fxml", "Technical Editor", "/Views/Assets/Option.png");
        });
        btnBooks.setOnAction(event -> {
            btnBooks.getScene().getWindow().hide();
            openNewScene("/_3ButtonsForSelectedRole/Watch/TechnicalEditor/IllustratorBooks.fxml","Technical Editor", "/Views/Assets/Option.png");
        });
        btnStages.setOnAction(event -> {
            btnStages.getScene().getWindow().hide();
            openNewScene("/_3ButtonsForSelectedRole/Watch/TechnicalEditor/IllustratorStages.fxml", "Technical Editor", "/Views/Assets/Option.png");
        });
    }
}
