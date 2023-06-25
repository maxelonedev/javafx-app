package Controllers;
import DTO.Stages;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.sql.*;

import static Controllers.AuthController.getConnection;
import static Controllers.AuthController.openNewScene;

public class IllustratorStagesController {
    @FXML Button btnBack, btnUpdate;
    @FXML TableView<Stages> tvBooks;
    @FXML TableColumn<Stages, Integer> tcStageId, tcBookId;
    @FXML TableColumn<Stages, String> tcProofreading, tcIllustration, tcLayout, tcSeal;
    @FXML TextField tfStageId, tfBookId;
    @FXML CheckBox illustration;
    @FXML void handleButtonAction(ActionEvent event) throws SQLException {
        if(event.getSource() == btnUpdate){
            updateRecord();
        }
    }
    public void handleClick(MouseEvent event) {
        Stages stage = tvBooks.getSelectionModel().getSelectedItem();
        tfStageId.setText("" + stage.getStage_id());
        tfBookId.setText("" + stage.getBook_id());
        if (stage.getIllustration() == true){
            illustration.setSelected(true);
        } else if (stage.getIllustration() == false){
            illustration.setSelected(false);
        }
    }
    @FXML
    void initialize() throws SQLException {
        btnBack.setOnAction(event -> {
            openNewScene("/Views/Illustrator.fxml", "Illustrator", "/Views/Assets/Employee.png");
            btnBack.getScene().getWindow().hide();
        });
        showBooks();
    }
    public ObservableList<Stages> getBooksList() throws SQLException {
        ObservableList<Stages> bookList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM `literary_publishing_house`.stages;";
        Statement st;
        ResultSet rs;
        st = conn.createStatement();
        rs = st.executeQuery(query);
        Stages Stages;
        while (rs.next()){
            Stages = new Stages(
                    rs.getInt("stage_id"),
                    rs.getInt("book_id"),
                    rs.getBoolean("proofreading"),
                    rs.getBoolean("illustration"),
                    rs.getBoolean("layout"),
                    rs.getBoolean("seal")
            );
            bookList.add(Stages);
        }
        return bookList;
    }
    public void showBooks() throws SQLException {
        ObservableList<Stages> list = getBooksList();
        tcStageId.setCellValueFactory(new PropertyValueFactory<Stages, Integer>("stage_id"));
        tcBookId.setCellValueFactory(new PropertyValueFactory<Stages, Integer>("book_id"));
        tcProofreading.setCellValueFactory(new PropertyValueFactory<Stages, String>("proofreading"));
        tcIllustration.setCellValueFactory(new PropertyValueFactory<Stages, String>("illustration"));
        tcLayout.setCellValueFactory(new PropertyValueFactory<Stages, String>("layout"));
        tcSeal.setCellValueFactory(new PropertyValueFactory<Stages, String>("seal"));
        tvBooks.setItems(list);
    }
     void updateRecord() throws SQLException {
        PreparedStatement pst;
        Connection conn = getConnection();
        String sql = "UPDATE stages SET stage_id=?, book_id=?, illustration=? WHERE stage_id="+tfStageId.getText()+" ";
        pst = conn.prepareStatement(sql);
        pst.setString(1, tfStageId.getText());
        pst.setString(2, tfBookId.getText());
        pst.setBoolean(3, illustration.isSelected());
        pst.execute();
        showBooks();
    }
}
