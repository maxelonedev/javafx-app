package Controllers;

import DTO.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class AuthController {
    @FXML TextField fldUsername;
    @FXML PasswordField fldPassword;
    @FXML Button btnAuth;
    @FXML Label lblError;
    @FXML void initialize() {
        btnAuth.setOnAction(event -> {
            String username = fldUsername.getText().trim();
            String password = fldPassword.getText().trim();
            if(username.equals("") && password.equals("")){
                lblError.setText("Username or password is empty!");
            } else {
                try {
                    loginUser(username, password);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            String query = "SELECT role FROM users WHERE username = '" + username + "' AND password = '" + password + "' ";
            try{
                Statement statement = getConnection().createStatement();
                ResultSet result = statement.executeQuery(query);
                while (result.next()) {
                    btnAuth.getScene().getWindow().hide();
                    int role = result.getInt(1);
                    if(role == 1) {
                        openNewScene("/Views/ChiefEditor.fxml", "Chief Editor", "/Views/Assets/Employee.png");
                    }
                    else if(role == 2){
                        openNewScene("/Views/Manager.fxml", "Manager", "/Views/Assets/Employee.png");
                    } else if(role == 3){
                        openNewScene("/Views/LiteraryEditor.fxml", "Literary Editor", "/Views/Assets/Employee.png");
                    } else if(role == 4){
                        openNewScene("/Views/TechnicalEditor.fxml", "Technical Editor", "/Views/Assets/Employee.png");
                    } else if(role == 5){
                        openNewScene("/Views/Illustrator.fxml", "Illustrator", "/Views/Assets/Employee.png");
                    } else if(role == 6){
                        openNewScene("/Views/Corrector.fxml", "Corrector", "/Views/Assets/Employee.png");
                    }
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        });
    }
    private void loginUser(String username, String password) throws SQLException {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        ResultSet result = null;
        PreparedStatement login = getConnection().prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
        login.setString(1, user.getUsername());
        login.setString(2, user.getPassword());
        result = login.executeQuery();
        int counter = 0;
        while(result.next()){
            counter++;
        }
        if(counter == 0) lblError.setText("Username or password is wrong!");
    }
    public static void openNewScene(String window, String title, String image){
        FXMLLoader loader = new FXMLLoader(AuthController.class.getResource(window));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = new Stage();
        stage.getIcons().add(new Image(image));
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }
    public static Connection getConnection(){
        Connection database_connection = null;
        try {
            database_connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/literary_publishing_house", "root", "12345");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return database_connection;
    }
}
