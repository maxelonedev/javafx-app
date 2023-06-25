package Controllers;
import DTO.Books;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

import static Controllers.AuthController.getConnection;
import static Controllers.AuthController.openNewScene;

public class LiteraryEditorBooksController{
    @FXML  Button btnBack;
    @FXML  TableView<Books> tvBooks;
    @FXML  TableColumn<Books, Integer> tcBookId, tcAuthorId, tcCirculation, tcList;
    @FXML  TableColumn<Books, String> tcBookName, tcGenre;
    @FXML
    void initialize(){
        btnBack.setOnAction(event -> {
            btnBack.getScene().getWindow().hide();
            openNewScene("/Views/LiteraryEditor.fxml", "Literary Editor", "/Views/Assets/Employee.png");
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
                        rs.getInt("book_id"),
                        rs.getInt("author_id"),
                        rs.getString("book_name"),
                        rs.getString("genre"),
                        rs.getInt("circulation"),
                        rs.getInt("list")
                );
                bookList.add(books);
            }
        }catch (Exception e){
            System.out.println("getBooksList " + e);
        }
        return bookList;
    }
    public void showBooks(){
        ObservableList<Books> list = getBooksList();
        tcBookId.setCellValueFactory(new PropertyValueFactory<Books, Integer>("book_id"));
        tcAuthorId.setCellValueFactory(new PropertyValueFactory<Books, Integer>("author_id"));
        tcBookName.setCellValueFactory(new PropertyValueFactory<Books, String>("book_name"));
        tcGenre.setCellValueFactory(new PropertyValueFactory<Books, String>("genre"));
        tcCirculation.setCellValueFactory(new PropertyValueFactory<Books, Integer>("circulation"));
        tcList.setCellValueFactory(new PropertyValueFactory<Books, Integer>("list"));
        tvBooks.setItems(list);
    }
}
