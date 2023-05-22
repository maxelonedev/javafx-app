package _3ButtonsForSelectedRole.InsertUpdateDelete.LeadEditor;
import _1Authorization.DatabaseHandler;
import _3ButtonsForSelectedRole.skeleton.Book_edition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.sql.*;

import static _1Authorization.Main.executeQuery;
import static _1Authorization.Main.openNewScene;

public class IUD_Controller_Book_edition{
    @FXML  Button Back, btnInsert, btnUpdate, btnDelete;
    @FXML  TableView<Book_edition> tvBooks;
    @FXML  TableColumn<Book_edition, Integer> col_id, col_id_book, col_id_author, col_circulation, col_publication_year;
    @FXML  TableColumn<Book_edition, String> col_chief_editor, col_lead_editor, col_corrector, col_book_artist, col_layout;
    @FXML  TableColumn<Book_edition, String> col_date_coc; /* DatePicker! */
    @FXML  TextField tf_id, tf_id_book, tf_id_author, tf_chief_editor, tf_lead_editor,tf_corrector, tf_book_artist, tf_layout,tf_circulation,tf_publication_year;
    @FXML  TextField tf_date_coc; /* DatePicker! */
    @FXML void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btnInsert){ insertRecord(); }
        else if(event.getSource() == btnUpdate){ updateRecord(); }
        else if(event.getSource() == btnDelete){ deleteButton(); }
    }
    public void handleClick(MouseEvent event) {
        try {
            Book_edition bookE = tvBooks.getSelectionModel().getSelectedItem();
            tf_id.setText("" + bookE.getId());
            tf_id_book.setText("" + bookE.getId_book());
            tf_id_author.setText("" + bookE.getId_author());
            tf_chief_editor.setText(bookE.getChief_editor());
            tf_lead_editor.setText(bookE.getLead_editor());
            tf_corrector.setText(bookE.getCorrector());
            tf_book_artist.setText(bookE.getBook_artist());
            tf_layout.setText(bookE.getLayout());
            tf_circulation.setText("" + bookE.getCirculation());
            tf_date_coc.setText(bookE.getDate_coc());
            tf_publication_year.setText("" + bookE.getPublication_year());
        } catch(Exception e) {
            System.out.println("handleClick " + e);
        }
    }
    @FXML
    void initialize() {
        Back.setOnAction(event -> {
            openNewScene("/_2SelectedRole/LeadEditor/MainForLeadEditor.fxml", "Авторизация/Менеджер", "/assets/employee.png");
            Back.getScene().getWindow().hide();
        });
        showBooks();
    }
    public ObservableList<Book_edition> getBooksList() {
        ObservableList<Book_edition> bookList = FXCollections.observableArrayList();
        Connection conn = DatabaseHandler.getConnection();
        String query = "SELECT * FROM `literary_publishing_house`.book_edition;";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Book_edition Book_edition;
            while (rs.next()) {
                Book_edition = new Book_edition(
                        rs.getInt("id"),
                        rs.getInt("id_book"),
                        rs.getInt("id_author"),
                        rs.getString("chief_editor"),
                        rs.getString("lead_editor"),
                        rs.getString("corrector"),
                        rs.getString("book_artist"),
                        rs.getString("layout"),
                        rs.getInt("circulation"),
                        rs.getString("date_coc"),
                        rs.getInt("publication_year"));
                bookList.add(Book_edition);
            }
        } catch (Exception e) {
            System.out.println("getBooksList " + e);
        }
        return bookList;
    }
    public void showBooks() {
        ObservableList<Book_edition> list;
        list = getBooksList();
        col_id.setCellValueFactory(new PropertyValueFactory<Book_edition, Integer>("id"));
        col_id_book.setCellValueFactory(new PropertyValueFactory<Book_edition, Integer>("id_book"));
        col_id_author.setCellValueFactory(new PropertyValueFactory<Book_edition, Integer>("id_author"));
        col_chief_editor.setCellValueFactory(new PropertyValueFactory<Book_edition, String>("chief_editor"));
        col_lead_editor.setCellValueFactory(new PropertyValueFactory<Book_edition, String>("lead_editor"));
        col_corrector.setCellValueFactory(new PropertyValueFactory<Book_edition, String>("corrector"));
        col_book_artist.setCellValueFactory(new PropertyValueFactory<Book_edition, String>("book_artist"));
        col_layout.setCellValueFactory(new PropertyValueFactory<Book_edition, String>("layout"));
        col_circulation.setCellValueFactory(new PropertyValueFactory<Book_edition, Integer>("circulation"));
        col_date_coc.setCellValueFactory(new PropertyValueFactory<Book_edition, String>("date_coc"));
        col_publication_year.setCellValueFactory(new PropertyValueFactory<Book_edition, Integer>("publication_year"));
        tvBooks.setItems(list);
    }
     void insertRecord() {
        String query = "INSERT INTO book_edition VALUES (" + Integer.parseInt(tf_id.getText()) + "," +
                Integer.parseInt(tf_id_book.getText()) + "," +
                Integer.parseInt(tf_id_author.getText()) + "," +
                "'" + tf_chief_editor.getText() + "'" + "," +
                "'" + tf_lead_editor.getText() + "'" + "," +
                "'" + tf_corrector.getText() + "'" + "," +
                "'" + tf_book_artist.getText() + "'" + "," +
                "'" + tf_layout.getText() + "'" + "," +
                Integer.parseInt(tf_circulation.getText()) + "," +
                "'" + tf_date_coc.getText() + "'" + "," +
                Integer.parseInt(tf_publication_year.getText()) + ")";
        executeQuery(query);
        showBooks();
    }
     void updateRecord() {
        try { String query = "UPDATE book_edition SET " +
                "id_book = " + Integer.parseInt(tf_id_book.getText()) + "," +
                "id_author = " + Integer.parseInt(tf_id_author.getText()) + "," +
                "chief_editor = " + "'" + tf_chief_editor.getText() + "'" + "," +
                "lead_editor = " + "'" + tf_lead_editor.getText() + "'" + "," +
                "corrector = " + "'" + tf_corrector.getText() + "'" + "," +
                "book_artist = " + "'" + tf_book_artist.getText() + "'" + "," +
                "layout = " + "'" + tf_layout.getText() + "'" + "," +
                "circulation = " + Integer.parseInt(tf_circulation.getText()) + "," +
                "date_coc = " + "'" + tf_date_coc.getText() + "'" + "," +
                "publication_year = " + Integer.parseInt(tf_publication_year.getText()) + " WHERE id = " + Integer.parseInt(tf_id.getText()) + " ";
            executeQuery(query);
            showBooks();
        } catch (Exception e) {
            System.out.println("updateRecord " + e);
        }
    }
     void deleteButton() {
        try {
            String query = "DELETE FROM book_edition WHERE id = " + Integer.parseInt(tf_id.getText()) + " ";
            executeQuery(query);
            showBooks();
        } catch (NumberFormatException e) {
            System.out.println("Не указан id");
        }
    }
}