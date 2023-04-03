package _3ButtonsForSelectedRole.InsertUpdateDelete.LeadEditor;
import _1Authorization.DatabaseHandler;
import _3ButtonsForSelectedRole.skeleton.Authors;
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

import static _1Authorization.Main.executeQuery;
import static _1Authorization.Main.openNewScene;

public class IUD_Controller_Authors{
    @FXML Button Back, btnInsert, btnUpdate, btnDelete;
    @FXML TableView<Authors> tvBooks;
    @FXML TableColumn<Authors, Integer> col_id;
    @FXML TableColumn<Authors, String> col_surname,col_name,col_patronymic;
    @FXML TextField tf_id, tf_surname, tf_name, tf_patronymic;
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
            Authors book = tvBooks.getSelectionModel().getSelectedItem();
            tf_id.setText("" + book.getId());
            tf_surname.setText(book.getSurname());
            tf_name.setText(book.getName());
            tf_patronymic.setText(book.getPatronymic());
        } catch (Exception e) {
            System.out.println("handleClick " + e);
        }
    }
    @FXML
    void initialize(){
        Back.setOnAction(event -> {
            Back.getScene().getWindow().hide();
            openNewScene("/_2SelectedRole/LeadEditor/MainForLeadEditor.fxml", "Авторизация/Менеджер", "/assets/employee.png");
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
        } catch (Exception e){
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
    void insertRecord(){
        String query = "INSERT INTO authors VALUES (" +
                Integer.parseInt(tf_id.getText()) + "," +
                "'" + tf_surname.getText() + "'" + "," +
                "'" + tf_name.getText() + "'" + "," +
                "'" + tf_patronymic.getText() + "'" +
                ")";
        executeQuery(query);
        showBooks();
    }
    void updateRecord(){
        try {
            /* UPDATE `literary_publishing_house`.authors SET id_author = 1, book_name = 'a', genre = 'a', circulation = 1, list = 1 WHERE id = 1 */
            String query = "UPDATE authors SET surname = '" + tf_surname.getText() + "'," +
                    "name = '" + tf_name.getText() + "'," +
                    "patronymic = '" + tf_patronymic.getText() + "'" +
                    "WHERE id = " + Integer.parseInt(tf_id.getText()) + " ";
            executeQuery(query);
            showBooks();
        } catch (NumberFormatException numberFormatException) {
            System.out.print("\nИсключение формата числа: Для входной строки: ' ' ");
        }
    }
    void deleteButton(){
        try {
            String query = "DELETE FROM authors WHERE id = " + Integer.parseInt(tf_id.getText()) + " ";
            executeQuery(query);
            showBooks();
        } catch (NumberFormatException e) {
            System.out.println("Не указан id");
        }
    }
}
