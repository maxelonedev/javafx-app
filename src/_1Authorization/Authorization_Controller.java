package _1Authorization;

import animations.Shake;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static _1Authorization.Main.openNewScene;

public class Authorization_Controller {
    @FXML TextField field_login;
    @FXML Button authorizationButton;
    @FXML PasswordField passwordHidden;
    @FXML void initialize() {
        authorizationButton.setOnAction(event -> {
            String loginText = field_login.getText().trim();
            String loginPassword = passwordHidden.getText().trim();
            if(!loginText.equals("") && !loginPassword.equals("")) loginUser(loginText, loginPassword);
            else System.out.print("Логин и/или пароль пустые(ой)");
            String query = "SELECT role FROM users WHERE username = '" + loginText + "' AND password = '" + loginPassword + "' ";
            try {
                Statement st = new DatabaseHandler().getConnection().createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    int role = rs.getInt(1);
                    authorizationButton.getScene().getWindow().hide();
                    if(role == 1) {
                        System.out.println("Добро пожаловать, Главный редактор " + firstUpperCase(loginText) + "!");
                        openNewScene("/_2SelectedRole/ChiefEditor/MainForChiefEditor.fxml", "Авторизация/Главный редактор/", "/assets/employee.png");
                    }
                    else if(role == 2){
                        System.out.println("Добро пожаловать, Менеджер " + firstUpperCase(loginText) + "!");
                        openNewScene("/_2SelectedRole/LeadEditor/MainForLeadEditor.fxml", "Авторизация/Менеджер/", "/assets/employee.png");
                    } else if(role == 3){
                        System.out.println("Добро пожаловать, Литературный редактор " + firstUpperCase(loginText) + "!");
                        openNewScene("/_2SelectedRole/LiteraryEditor/MainForLiteraryEditor.fxml", "Авторизация/Литературный редактор/", "/assets/employee.png");
                    } else if(role == 4){
                        System.out.println("Добро пожаловать, Технический редактор " + firstUpperCase(loginText) + "!");
                        openNewScene("/_2SelectedRole/TechnicalEditor/MainForTechnicalEditor.fxml", "Авторизация/Технический редактор/", "/assets/employee.png");
                    } else if(role == 5){
                        System.out.println("Добро пожаловать, Иллюстратор " + firstUpperCase(loginText) + "!");
                        openNewScene("/_2SelectedRole/BookArtist/MainForBookArtist.fxml", "Авторизация/Иллюстратор/", "/assets/employee.png");
                    } else if(role == 6){
                        System.out.println("Добро пожаловать, Корректор " + firstUpperCase(loginText) + "!");
                        openNewScene("/_2SelectedRole/Corrector/MainForCorrector.fxml", "Авторизация/Корректор/", "/assets/employee.png");
                    } else {
                        System.out.println("Роль не найдена");
                    }
                }
            } catch (SQLException e) {
                System.out.println("initialize controller auth " + e);
            }
        });
    }
    public String firstUpperCase(String word) {
        if(word == null || word.isEmpty())return "";
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
    private void loginUser(String loginText, String loginPassword) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setUsername(loginText);
        user.setPassword(loginPassword);
        ResultSet result = dbHandler.getUser(user);
        int counter = 0;
            try {
                while(result.next()){ counter++; }
            } catch (SQLException e) {
                System.out.println("loginUser " + e);
            }
        if(counter >= 1) {
            System.out.println("Вы успешно зашли в аккаунт!");
        }
        else {
            Shake userLoginAnim = new Shake(field_login);
            Shake userPassAnim = new Shake(passwordHidden);
            userLoginAnim.playAnim();
            userPassAnim.playAnim();
        }
    }
}
