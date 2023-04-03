package _2SelectedRole.Corrector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
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
    public void openNewScene(String window, String title, String image){
        Back.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        try { loader.load(); } catch (IOException e) {e.printStackTrace();}
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        Image Icon = new Image(image);
        stage.getIcons().add(Icon);
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
