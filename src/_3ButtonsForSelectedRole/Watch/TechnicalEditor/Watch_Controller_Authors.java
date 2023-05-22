package _3ButtonsForSelectedRole.Watch.TechnicalEditor;
import _1Authorization.DatabaseHandler;
import _3ButtonsForSelectedRole.skeleton.Authors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.*;

import static _1Authorization.Main.openNewScene;

public class Watch_Controller_Authors{
    @FXML  Button Back;
    @FXML  TableView<Authors> tvBooks;
    @FXML  TableColumn<Authors, Integer> col_id;
    @FXML  TableColumn<Authors, String> col_surname, col_name, col_patronymic;
    @FXML
    void initialize(){
        Back.setOnAction(event -> {
            openNewScene("/_2SelectedRole/TechnicalEditor/MainForTechnicalEditor.fxml", "Авторизация/Технический редактор", "/assets/employee.png");
            Back.getScene().getWindow().hide();
        });
        showBooks();
    }
    public ObservableList<Authors> getBooksList(){
        ObservableList<Authors> bookList = FXCollections.observableArrayList();
        Connection conn = DatabaseHandler.getConnection();
        String query = "SELECT * FROM `literary_publishing_house`.authors;";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Authors Authors;
            while (rs.next()){
                Authors = new Authors(
                        rs.getInt("id"),
                        rs.getString("surname"),
                        rs.getString("name"),
                        rs.getString("patronymic")
                );
                bookList.add(Authors);
            }
        }catch (Exception e){
            System.out.println("getBooksList " + e);
        }
        return bookList;
    }
    public void showBooks(){
        ObservableList<Authors> list = getBooksList();
        col_id.setCellValueFactory(new PropertyValueFactory<Authors, Integer>("id"));
        col_surname.setCellValueFactory(new PropertyValueFactory<Authors, String>("surname"));
        col_name.setCellValueFactory(new PropertyValueFactory<Authors, String>("name"));
        col_patronymic.setCellValueFactory(new PropertyValueFactory<Authors, String>("patronymic"));
        tvBooks.setItems(list);
    }
}
