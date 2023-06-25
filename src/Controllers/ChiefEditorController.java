package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import static Controllers.AuthController.openNewScene;

public class ChiefEditorController {
    @FXML Button btnBack, btnAuthors, btnManuscripts, btnBooks, btnBookEdition, btnStages;

    @FXML void initialize(){
        btnBack.setOnAction(event -> {
            btnBack.getScene().getWindow().hide();
            openNewScene("/Views/Auth.fxml", "Auth", "/Views/Assets/Book.png");
        });
        // !
        btnAuthors.setOnAction(event -> {
            btnAuthors.getScene().getWindow().hide();
            openNewScene("/_3ButtonsForSelectedRole/InsertUpdateDelete/ChiefEditor/IUD_Authors.fxml", "Authors", "/Views/Assets/Option.png");
        });
        btnManuscripts.setOnAction(event -> {
            btnManuscripts.getScene().getWindow().hide();
            openNewScene("/_3ButtonsForSelectedRole/InsertUpdateDelete/ChiefEditor/IUD_Manuscripts.fxml", "Manuscripts", "/Views/Assets/Option.png");
        });
        btnBooks.setOnAction(event -> {
            btnBooks.getScene().getWindow().hide();
            openNewScene("/_3ButtonsForSelectedRole/InsertUpdateDelete/ChiefEditor/IUD_btnBooks.fxml", "Books", "/Views/Assets/Option.png");
        });
        btnBookEdition.setOnAction(event -> {
            btnBookEdition.getScene().getWindow().hide();
            openNewScene("/_3ButtonsForSelectedRole/InsertUpdateDelete/ChiefEditor/IUD_btnBookEdition.fxml", "Book edition", "/Views/Assets/Option.png");
        });
        btnStages.setOnAction(event -> {
            btnStages.getScene().getWindow().hide();
            openNewScene("/_3ButtonsForSelectedRole/InsertUpdateDelete/ChiefEditor/IUD_btnStages.fxml", "Stages", "/Views/Assets/Option.png");
        });
    }
}

