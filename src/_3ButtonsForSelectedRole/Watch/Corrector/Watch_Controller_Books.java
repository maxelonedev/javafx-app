package _3ButtonsForSelectedRole.Watch.Corrector;
import _3ButtonsForSelectedRole.skeleton.Books;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

import static _1Authorization.DatabaseHandler.getConnection;
import static _1Authorization.Main.openNewScene;

public class Watch_Controller_Books{
    @FXML
     Button Back;
    @FXML
     TableView<Books> tvBooks;
    @FXML
     TableColumn<Books, Integer> col_id;
    @FXML
     TableColumn<Books, Integer> col_id_author;
    @FXML
     TableColumn<Books, String> col_book_name;
    @FXML
     TableColumn<Books, String> col_genre;
    @FXML
     TableColumn<Books, Integer> col_circulation;
    @FXML
     TableColumn<Books, Integer> col_list;
    @FXML
    void initialize(){
        Back.setOnAction(event -> {
            openNewScene("/_2SelectedRole/Corrector/MainForCorrector.fxml", "Авторизация/Корректор", "/assets/employee.png");
        });
        showBooks();
    }
    public ObservableList<Books> getBooksList(){
        ObservableList<Books> bookList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM `literary_publishing_house`.books;";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Books books;
            while (rs.next()){
                books = new Books(
                        rs.getInt("id"),
                        rs.getInt("id_author"),
                        rs.getString("book_name"),
                        rs.getString("genre"),
                        rs.getInt("circulation"),
                        rs.getInt("list")
                );
                bookList.add(books);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return bookList;
    }
    public void showBooks(){
        ObservableList<Books> list = getBooksList();
        col_id.setCellValueFactory(new PropertyValueFactory<Books, Integer>("id"));
        col_id_author.setCellValueFactory(new PropertyValueFactory<Books, Integer>("id_author"));
        col_book_name.setCellValueFactory(new PropertyValueFactory<Books, String>("book_name"));
        col_genre.setCellValueFactory(new PropertyValueFactory<Books, String>("genre"));
        col_circulation.setCellValueFactory(new PropertyValueFactory<Books, Integer>("circulation"));
        col_list.setCellValueFactory(new PropertyValueFactory<Books, Integer>("list"));
        tvBooks.setItems(list);
    }
}
