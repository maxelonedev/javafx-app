package _1Authorization;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;

public class DatabaseHandler {

    /* connect to database */
    public static Connection getConnection() {
        Connection dbConnection = null;
//        String connectionString = "jdbc:mysql://localhost:3306/literary publishing house";
        String connectionString = "jdbc:mysql://localhost:3306/literary_publishing_house";
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection(connectionString, "root", "12345");
//            ClassNotFoundException |
        } catch (SQLException e) {
            System.out.println("getConnection " + e);
        }
        return dbConnection;
    }

    /* get user from database */
    public ResultSet getUser(User user) {
        ResultSet resSet = null;
        String select = "SELECT * FROM users WHERE username = ? AND password = ?";
        try {
            PreparedStatement prSt = getConnection().prepareStatement(select);
            prSt.setString(1, user.getUsername());
            prSt.setString(2, user.getPassword());
            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            System.out.println("getUser " + e);
        }
        return  resSet;
    }
}
