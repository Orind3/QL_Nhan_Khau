package controller.thongKe;

import java.net.URL;
import java.util.ResourceBundle;

import entity.UngHoModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ThongTinChiTietController implements Initializable{
    
    @FXML
    private Label dotLabel;

    @FXML
    private Label idHoKhauLabel;

    @FXML
    private Label maHoKhauLabel;

    @FXML
    private Label maUngHoLabel;

    @FXML
    private Label soTienDaDongLabel;

    @FXML
    private Label tenChuHoLabel;

    @FXML
    private Label tenQuyLabel;

    @FXML
    private Label thoiGianLebel;

    public void setUp(UngHoModel input){
        dotLabel.setText(input.getDot());
        idHoKhauLabel.setText(String.valueOf(input.getIDHoKhau()));
        maHoKhauLabel.setText("NULL");
        maUngHoLabel.setText(String.valueOf(input.getID()));
        soTienDaDongLabel.setText(input.getSoTienDaDong());
        tenChuHoLabel.setText(input.getTenChuHo());
        tenQuyLabel.setText(input.getQuy());
        thoiGianLebel.setText(input.getDate());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
    }
}
