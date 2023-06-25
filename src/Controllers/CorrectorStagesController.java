package _3ButtonsForSelectedRole.Watch.Corrector;
import DTO.Stages;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.sql.*;

import static Main.openNewScene;

public class Update_Controller_Stages{
    @FXML  Button Back, btnUpdate;
    @FXML  TableView<Stages> tvBooks;
    @FXML  TableColumn<Stages, Integer> col_id, col_id_book;
    @FXML  TableColumn<Stages, String> col_proofreading, col_illustration, col_layout, col_seal;
    @FXML  TextField tf_id, tf_id_book;
    @FXML  CheckBox proofreading;
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
            if (stage.getProofreading() == true){
                proofreading.setSelected(true);
            } else if (stage.getProofreading() == false){
                proofreading.setSelected(false);
            }
        } catch (Exception e) {
            System.out.println("handleClick " + e);
        }
    }
    @FXML
    void initialize(){
        Back.setOnAction(event -> {
            openNewScene("/Views/Corrector.fxml", "Авторизация/Корректор", "/assets/Employee.png");
            Back.getScene().getWindow().hide();
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
            e.printStackTrace();
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
            String sql = "UPDATE stages SET id=?,id_book=?,proofreading=? WHERE id="+tf_id.getText()+" ";
            pst = conn.prepareStatement(sql);
            pst.setString(1, tf_id.getText());
            pst.setString(2, tf_id_book.getText());
            if (proofreading.isSelected()){
                pst.setBoolean(3, true);
            }
            else {
                pst.setBoolean(3, false);
            }
            pst.execute();
            showBooks();
        }catch (SQLException e) {
            System.out.println("updateRecord " + e);
        }
    }
}
