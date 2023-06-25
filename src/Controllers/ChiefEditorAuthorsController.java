package Controllers;
import DTO.Authors;
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

import static Controllers.AuthController.getConnection;
import static Controllers.AuthController.openNewScene;

public class ChiefEditorAuthorsController{

    @FXML Button btnBack, btnInsert, btnUpdate, btnDelete;
    @FXML TableView<Authors> tvBooks;
    @FXML TableColumn<Authors, Integer> tcId;
    @FXML TableColumn<Authors, String> tcSurname, tcName, tcPatronymic;
    @FXML TextField tfAuthorId, tfSurname, tfName, tfPatronymic;

    @FXML void handleButtonAction(ActionEvent event) throws SQLException {
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
            tfAuthorId.setText("" + book.getId());
            tfSurname.setText(book.getSurname());
            tfName.setText(book.getName());
            tfPatronymic.setText(book.getPatronymic());
        }
        catch (Exception e) {
            System.out.println("handleClick " + e);
        }
    }

    @FXML
    void initialize() throws SQLException {
        btnBack.setOnAction(event -> {
            btnBack.getScene().getWindow().hide();
            openNewScene("/Views/ChiefEditor.fxml", "Chief Editor", "/Views/Assets/Employee.png");
        });
        showBooks();
    }

    public ObservableList<Authors> getBooksList() throws SQLException {
        ObservableList<Authors> bookList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM `literary_publishing_house`.authors;";
        Statement st;
        ResultSet rs;
        st = conn.createStatement();
        rs = st.executeQuery(query);
        Authors Authors;
        while (rs.next()){
            Authors = new Authors(
                    rs.getInt("author_id"),
                    rs.getString("surname"),
                    rs.getString("name"),
                    rs.getString("patronymic")
            );
            bookList.add(Authors);
        }
        return bookList;
    }

    public void showBooks() throws SQLException {
        ObservableList<Authors> list = getBooksList();
        tcId.setCellValueFactory(new PropertyValueFactory<Authors, Integer>("author_id"));
        tcSurname.setCellValueFactory(new PropertyValueFactory<Authors, String>("surname"));
        tcName.setCellValueFactory(new PropertyValueFactory<Authors, String>("name"));
        tcPatronymic.setCellValueFactory(new PropertyValueFactory<Authors, String>("patronymic"));
        tvBooks.setItems(list);
    }

    void insertRecord() throws SQLException {
        String query = "INSERT INTO authors VALUES (" +
                Integer.parseInt(tfAuthorId.getText()) + "," +
                "'" + tfSurname.getText() + "'" + "," +
                "'" + tfName.getText() + "'" + "," +
                "'" + tfPatronymic.getText() + "'" +
                ")";
        executeQuery(query);
        showBooks();
    }

    void updateRecord() throws SQLException {
        String query = "UPDATE authors SET surname = '" + tfSurname.getText() + "'," +
                "name = '" + tfName.getText() + "'," +
                "patronymic = '" + tfPatronymic.getText() + "'" +
                "WHERE author_id = " + Integer.parseInt(tfAuthorId.getText()) + " ";
        executeQuery(query);
        showBooks();
    }

    void deleteButton() throws SQLException {
        String query = "DELETE FROM authors WHERE author_id = " + Integer.parseInt(tfAuthorId.getText()) + " ";
        executeQuery(query);
        showBooks();
    }

    public static Statement executeQuery(String query) throws SQLException {
        Statement execute = null;
        execute = getConnection().createStatement();
        execute.executeUpdate(query);
        return execute;
    }
}
