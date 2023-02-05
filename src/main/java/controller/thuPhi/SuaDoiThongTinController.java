package controller.thuPhi;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SuaDoiThongTinController implements Initializable{


    @FXML
    private TextField ghiChuTxtField;

    @FXML
    private TextField tienTxtField;

    


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
        
    }


    public void setTienAndGhiChu(int tien,String ghichu){
        this.ghiChuTxtField.setText(ghichu);
        this.tienTxtField.setText(String.valueOf(tien));
    }


    public TextField getGhiChuTxtField() {
        return ghiChuTxtField;
    }


    public void setGhiChuTxtField(TextField ghiChuTxtField) {
        this.ghiChuTxtField = ghiChuTxtField;
    }


    public TextField getTienTxtField() {
        return tienTxtField;
    }


    public void setTienTxtField(TextField tienTxtField) {
        this.tienTxtField = tienTxtField;
    }
}
    
