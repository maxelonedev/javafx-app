package _3ButtonsForSelectedRole.InsertUpdateDelete.ChiefEditor;
import DTO.Stages;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.sql.*;

import static Main.executeQuery;
import static Main.openNewScene;

public class IUD_Controller_Stages{
    @FXML Button Back, btnInsert, btnUpdate, btnDelete;
    @FXML TableView<Stages> tvBooks;
    @FXML TableColumn<Stages, Integer> col_id, col_id_book;
    @FXML TableColumn<Stages, Boolean> col_proofreading;
    @FXML TableColumn<Stages, Boolean> col_illustration;
    @FXML TableColumn<Stages, Boolean> col_layout;
    @FXML TableColumn<Stages, Boolean> col_seal;
    @FXML TextField tf_id, tf_id_book;
    @FXML CheckBox proofreading, illustration, layout, seal;
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
            Stages stage = tvBooks.getSelectionModel().getSelectedItem();
            tf_id.setText("" + stage.getId());
            tf_id_book.setText("" + stage.getId_book());
            if (stage.getProofreading() == true){
                proofreading.setSelected(true);
            } else if (stage.getProofreading() == false){
                proofreading.setSelected(false);
            }

            if (stage.getIllustration() == true){
                illustration.setSelected(true);
            } else if (stage.getIllustration() == false){
                illustration.setSelected(false);
            }

            if (stage.getLayout() == true){
                layout.setSelected(true);
            } else if (stage.getLayout() == false){
                layout.setSelected(false);
            }

            if (stage.getSeal() == true){
                seal.setSelected(true);
            } else if (stage.getSeal() == false){
                seal.setSelected(false);
            }
        } catch (Exception e) {
            System.out.println("handleClick " + e);
        }
    }
    @FXML void initialize(){
        Back.setOnAction(event -> {
            openNewScene("/Views/ChiefEditor.fxml", "Авторизация/Главный редактор", "/assets/Employee.png");
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
        } catch (SQLException e){
            System.out.println(" getBooksList" + e);
        }
        return bookList;
    }
    public void showBooks(){
        ObservableList<Stages> list = getBooksList();
        col_id.setCellValueFactory(new PropertyValueFactory<Stages, Integer>("id"));
        col_id_book.setCellValueFactory(new PropertyValueFactory<Stages, Integer>("id_book"));
        col_proofreading.setCellValueFactory(new PropertyValueFactory<Stages, Boolean>("proofreading"));
        col_illustration.setCellValueFactory(new PropertyValueFactory<Stages, Boolean>("illustration"));
        col_layout.setCellValueFactory(new PropertyValueFactory<Stages, Boolean>("layout"));
        col_seal.setCellValueFactory(new PropertyValueFactory<Stages, Boolean>("seal"));
        tvBooks.setItems(list);
    }
    void insertRecord(){
        PreparedStatement pst;
        Connection conn = DatabaseHandler.getConnection();
        try {
            String sql = "INSERT INTO stages(id_book,proofreading,illustration,layout,seal) VALUES (?,?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, tf_id_book.getText());
            if (proofreading.isSelected()){
                pst.setBoolean(2, true);
            } else {
                pst.setBoolean(2, false);
            }
            if (illustration.isSelected()){
                pst.setBoolean(3, true);
            } else {
                pst.setBoolean(3, false);
            }
            if (layout.isSelected()){
                pst.setBoolean(4, true);
            } else {
                pst.setBoolean(4, false);
            }
            if (seal.isSelected()){
                pst.setBoolean(5, true);
            } else {
                pst.setBoolean(5, false);
            }
            pst.execute();
        } catch (SQLException e) {
            System.out.println("insertRecord " + e);
        }
        showBooks();
    }
    void updateRecord(){
        PreparedStatement pst;
        Connection conn = DatabaseHandler.getConnection();
        try {
            String sql = "UPDATE stages SET id=?,id_book=?,proofreading=?,illustration=?,layout=?,seal=? WHERE id="+tf_id.getText()+" ";
            pst = conn.prepareStatement(sql);
            pst.setString(1, tf_id.getText());
            pst.setString(2, tf_id_book.getText());
            if (proofreading.isSelected()){
                pst.setBoolean(3, true);
            } else {
                pst.setBoolean(3, false);
            }
            if (illustration.isSelected()){
                pst.setBoolean(4, true);
            } else {
                pst.setBoolean(4, false);
            }
            if (layout.isSelected()){
                pst.setBoolean(5, true);
            } else {
                pst.setBoolean(5, false);
            }
            if (seal.isSelected()){
                pst.setBoolean(6, true);
            } else {
                pst.setBoolean(6, false);
            }
            pst.execute();
            showBooks();
        } catch (SQLException e) {
            System.out.println("updateRecord " + e);
        }
    }
    void deleteButton(){
        try {
            String query = "DELETE FROM stages WHERE id = " + Integer.parseInt(tf_id.getText()) + " ";
            executeQuery(query);
            showBooks();
        } catch (NumberFormatException e) {
            System.out.println("Не указан id");
        }
    }
}
