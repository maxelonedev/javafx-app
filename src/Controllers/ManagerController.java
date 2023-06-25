package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import static Controllers.AuthController.openNewScene;

public class ManagerController {
    @FXML Button btnBack, btnAuthors, btnManuscripts, btnBooks, btnBookEdition, btnStages;

    @FXML void initialize(){
        btnBack.setOnAction(event -> {
            openNewScene("/Views/Auth.fxml", "Auth", "/Views/Assets/Book.png");
            btnBack.getScene().getWindow().hide();
        });
        // !
        btnAuthors.setOnAction(event -> {
            btnAuthors.getScene().getWindow().hide();
            openNewScene("/_3ButtonsForSelectedRole/InsertUpdateDelete/ChiefEditor/IUD_Authors.fxml", "Literary Editor", "/Views/Assets/Option.png");
        });
        btnManuscripts.setOnAction(event -> {
            openNewScene("/_3ButtonsForSelectedRole/InsertUpdateDelete/ChiefEditor/IUD_Manuscripts.fxml",
                    "Главный редактор/Рукописи", "/assets/Option.png");
            btnManuscripts.getScene().getWindow().hide();
        });
        btnBooks.setOnAction(event -> {
            openNewScene("/_3ButtonsForSelectedRole/InsertUpdateDelete/ChiefEditor/IUD_btnBooks.fxml",
                    "Главный редактор/Книги", "/assets/Option.png");
            btnBooks.getScene().getWindow().hide();
        });
        btnBookEdition.setOnAction(event -> {
            openNewScene("/_3ButtonsForSelectedRole/InsertUpdateDelete/ChiefEditor/IUD_btnBookEdition.fxml",
                    "Главный редактор/Издание книги", "/assets/Option.png");
            btnBookEdition.getScene().getWindow().hide();
        });
        btnStages.setOnAction(event -> {
            openNewScene("/_3ButtonsForSelectedRole/InsertUpdateDelete/ChiefEditor/IUD_btnStages.fxml",
                    "Главный редактор/Этапы", "/assets/Option.png");
            btnStages.getScene().getWindow().hide();
        });
    }
}
