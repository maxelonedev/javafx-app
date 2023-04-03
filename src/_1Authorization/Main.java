package _1Authorization;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Statement;

import static _1Authorization.DatabaseHandler.getConnection;

public class Main extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        /* Основное окно, окно при запуске приложения - Авторизация. */
        Parent root = FXMLLoader.load(getClass().getResource("/_1Authorization/Authorization.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.setTitle("Авторизация/");

        Image Icon = new Image("/assets/book.png");
        stage.getIcons().add(Icon);

        stage.show();
    }

    /* open new scene, and close previous(in controller-initialize that calling this method) */
    public static void openNewScene(String window, String title, String image) {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(Main.class.getResource(window));

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage s = new Stage();

        Image ic = new Image(image);
        s.getIcons().add(ic);

        s.setTitle(title);
        s.setScene(new Scene(loader.getRoot()));

        s.show();
    }

    /* execute the query usually using for insert and update */
    public static Statement executeQuery(String query) {
        Statement st = null;
        try {
            st = getConnection().createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("executeQuery: " + e);
        }
        return st;
    }

}
