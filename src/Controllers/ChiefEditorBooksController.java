package _3ButtonsForSelectedRole.InsertUpdateDelete.ChiefEditor;
import DTO.Books;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.sql.*;

import static Main.executeQuery;
import static Main.openNewScene;

public class IUD_Controller_Books{
    @FXML Button Back, btnInsert, btnUpdate, btnDelete, Print;
    @FXML TableView<Books> tvBooks;
    @FXML TableColumn<Books, Integer> col_id, col_id_author, col_circulation, col_list;
    @FXML TableColumn<Books, String> col_book_name, col_genre;
    @FXML TextField tf_id, tf_author, tf_book_name, tf_genre, tf_circulation, tf_list;
    @FXML void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btnInsert){
            insertRecord();
        } else if(event.getSource() == btnUpdate){
            updateRecord();
        } else if(event.getSource() == btnDelete){
            deleteButton();
        }
    }
    public void handleClick(MouseEvent event) {
        try {
            Books book = tvBooks.getSelectionModel().getSelectedItem();
            tf_id.setText("" + book.getId());
            tf_author.setText("" + book.getId_author());
            tf_book_name.setText(book.getBook_name());
            tf_genre.setText(book.getGenre());
            tf_circulation.setText("" + book.getCirculation());
            tf_list.setText("" + book.getList());
        } catch (Exception e){
            System.out.println("handleClick " + e);
        }
    }
    @FXML
    void initialize(){
        Back.setOnAction(event -> {
            openNewScene("/Views/ChiefEditor.fxml", "Авторизация/Главный редактор", "/assets/Employee.png");
            Back.getScene().getWindow().hide();
        });
        showBooks();
    }
    public ObservableList<Books> getBooksList(){
        ObservableList<Books> bookList = FXCollections.observableArrayList();
        Connection conn = DatabaseHandler.getConnection();
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
        } catch (Exception e){
            System.out.println("getBooksList " + e);
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
    void insertRecord(){
        String query = "INSERT INTO books(id_author,book_name,genre,circulation,list) VALUES (" +
                Integer.parseInt(tf_author.getText()) + "," +
                "'" + tf_book_name.getText() + "'" + "," +
                "'" + tf_genre.getText() + "'" + "," +
                Integer.parseInt(tf_circulation.getText()) + "," +
                Integer.parseInt(tf_list.getText()) + ")";
        executeQuery(query);
        showBooks();
    }
    void updateRecord(){
        try {
            /* UPDATE `literary_publishing_house`.books SET id_author = 1, book_name = 'a', genre = 'a', circulation = 1, list = 1 WHERE id = 1 */
            String query = "UPDATE books SET id_author = " + Integer.parseInt(tf_author.getText()) + "," +
                    "book_name = '" + tf_book_name.getText() + "'," +
                    "genre = '" + tf_genre.getText() + "'," +
                    "circulation = " + Integer.parseInt(tf_circulation.getText()) + "," +
                    "list = " + Integer.parseInt(tf_list.getText()) + " WHERE id = " + Integer.parseInt(tf_id.getText()) + " ";
            executeQuery(query);
            showBooks();
        }catch (NumberFormatException numberFormatException) {
            System.out.print("\nИсключение формата числа: Для входной строки: ' ' ");
        }
    }
    void deleteButton(){
        try {
            String query = "DELETE FROM books WHERE id = " + Integer.parseInt(tf_id.getText()) + " ";
            executeQuery(query);
            showBooks();
        } catch (NumberFormatException e) {
            System.out.println("Не указан id");
        }
    }
}
