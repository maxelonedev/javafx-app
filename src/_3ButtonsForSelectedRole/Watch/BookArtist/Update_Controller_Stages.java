package _3ButtonsForSelectedRole.Watch.BookArtist;
import _1Authorization.DatabaseHandler;
import _3ButtonsForSelectedRole.skeleton.Stages;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
public class Update_Controller_Stages{
    @FXML  Button Back, btnUpdate;
    @FXML  TableView<Stages> tvBooks;
    @FXML  TableColumn<Stages, Integer> col_id, col_id_book;
    @FXML  TableColumn<Stages, String> col_proofreading, col_illustration, col_layout, col_seal;
    @FXML  TextField tf_id, tf_id_book;
    @FXML  CheckBox illustration;
    @FXML void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btnUpdate){
            updateRecord();
        }
    }
    public void handleClick(MouseEvent event) {
        try {
            Stages stage = tvBooks.getSelectionModel().getSelectedItem();
            tf_id.setText("" + stage.getId());
            tf_id_book.setText("" + stage.getId_book());
            if (stage.getIllustration() == true){
                illustration.setSelected(true);
            } else if (stage.getIllustration() == false){
                illustration.setSelected(false);
            }
        }catch (Exception e){
            System.out.println("handleClick " + e);
        }
    }
    @FXML
    void initialize(){
        Back.setOnAction(event -> {
            Back.getScene().getWindow().hide();
            new DatabaseHandler.openNewScene("/_2SelectedRole/BookArtist/MainForBookArtist.fxml", "Авторизация/Иллюстратор", "/assets/employee.png");
        });
        showBooks();
    }
    public ObservableList<Stages> getBooksList(){
        ObservableList<Stages> bookList = FXCollections.observableArrayList();
        Connection conn = DatabaseHandler.getConnection();
        String query = "SELECT * FROM `literary_publishing_house`.stages;";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Stages Stages;
            while (rs.next()){
                Stages = new Stages(
                        rs.getInt("id"),
                        rs.getInt("id_book"),
                        rs.getBoolean("proofreading"),
                        rs.getBoolean("illustration"),
                        rs.getBoolean("layout"),
                        rs.getBoolean("seal")
                );
                bookList.add(Stages);
            }
        }catch (Exception e){
            System.out.println("getBooksList " + e);
        }
        return bookList;
    }
    public void showBooks(){
        ObservableList<Stages> list = getBooksList();
        col_id.setCellValueFactory(new PropertyValueFactory<Stages, Integer>("id"));
        col_id_book.setCellValueFactory(new PropertyValueFactory<Stages, Integer>("id_book"));
        col_proofreading.setCellValueFactory(new PropertyValueFactory<Stages, String>("proofreading"));
        col_illustration.setCellValueFactory(new PropertyValueFactory<Stages, String>("illustration"));
        col_layout.setCellValueFactory(new PropertyValueFactory<Stages, String>("layout"));
        col_seal.setCellValueFactory(new PropertyValueFactory<Stages, String>("seal"));
        tvBooks.setItems(list);
    }
     void updateRecord(){
        PreparedStatement pst;
        Connection conn = DatabaseHandler.getConnection();
        try {
            String sql = "UPDATE stages SET id=?,id_book=?,illustration=? WHERE id="+tf_id.getText()+" ";
            pst = conn.prepareStatement(sql);
            pst.setString(1, tf_id.getText());
            pst.setString(2, tf_id_book.getText());
            pst.setBoolean(3, illustration.isSelected());
            pst.execute();
            showBooks();
        } catch (SQLException e) {
            System.out.println("updateRecord " + e);
        }
    }
}
