package controller.thuPhi;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class SuaDoiUngHoController implements Initializable{
    @FXML
    private TextField ghiChuTxtField;

    @FXML
    private TextField tienTxtField;


    public void getDataFromScene(String ghiChu,String tien){
        this.ghiChuTxtField.setText(ghiChu);
        this.tienTxtField.setText(tien);
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
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
