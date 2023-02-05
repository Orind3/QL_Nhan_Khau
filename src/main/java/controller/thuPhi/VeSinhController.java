package controller.thuPhi;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import entity.PhiVeSinhModel;
import utility.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;




public class VeSinhController implements Initializable {
    private PhiVeSinhModel onMouse;
    private int count = 1;
    @FXML
    private Button backBtn;

    @FXML
    private Pane phiVeSinhPane;
    @FXML
    private TableColumn<PhiVeSinhModel, String> ghiChu;
    @FXML
    private TableView<PhiVeSinhModel> phiVeSinh;
    @FXML
    private TableColumn<PhiVeSinhModel, Integer> id;

    @FXML
    private TableColumn<PhiVeSinhModel, Integer> maHoKhau;

    @FXML
    private TableColumn<PhiVeSinhModel, Integer> soThanhVien;

    @FXML
    private TableColumn<PhiVeSinhModel, Integer> soTienDaDong;

    @FXML
    private TableColumn<PhiVeSinhModel, Integer> soTienPhaiDong;

    @FXML
    private TableColumn<PhiVeSinhModel, Integer> tenChuHo;
    
    @FXML
    private TextField namTxtField;


    @FXML
    private TextField donGiaTxtField;
    
    private ObservableList<PhiVeSinhModel> phiVeSinhList;
    

    
    @Override

    public void initialize(URL location, ResourceBundle resources) {
        phiVeSinhList = FXCollections.observableArrayList();

        id.setCellValueFactory(new PropertyValueFactory<>("IDPhiVeSinh"));
        ghiChu.setCellValueFactory(new PropertyValueFactory<>("ghiChu"));
        maHoKhau.setCellValueFactory(new PropertyValueFactory<>("ID"));
        soThanhVien.setCellValueFactory(new PropertyValueFactory<>("soThanhVien"));
        soTienDaDong.setCellValueFactory(new PropertyValueFactory<>("soTienDaDong"));
        soTienPhaiDong.setCellValueFactory(new PropertyValueFactory<>("soTienPhaiDong"));
        tenChuHo.setCellValueFactory(new PropertyValueFactory<>("tenChuHo"));

        phiVeSinh.setItems(phiVeSinhList);
    }

    public ArrayList<PhiVeSinhModel> getThongTinVeSinh(String nam){
        ArrayList<PhiVeSinhModel> result = new ArrayList<>();
        String query = "SELECT nhan_khau.hoTen,ho_khau.idHoKhau,COUNT(ho_khau_nhan_khau.idNhanKhau) as So_Luong_Thanh_Vien FROM ho_khau RIGHT JOIN ho_khau_nhan_khau on (ho_khau_nhan_khau.idHoKhau = ho_khau.idHoKhau) LEFT JOIN nhan_khau on (ho_khau.idChuHo = nhan_khau.idNhanKhau) GROUP BY ho_khau.idHoKhau";
        try {
            Connection con = MysqlConnection.getMysqlConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                PhiVeSinhModel input = new PhiVeSinhModel(rs.getInt("idHoKhau"), rs.getString("hoTen"), rs.getInt("So_Luong_Thanh_Vien"),6000, 0, null);
                result.add(input);
            }
            query = "SELECT thu_phi.maKhoanThu,thu_phi.tenKhoanThu,thu_phi.soTien,ho_khau.idHoKhau,thu_phi.ghiChu FROM thu_phi LEFT JOIN ho_khau on thu_phi.IDHoKhau = ho_khau.idHoKhau where thu_phi.tenKhoanThu like N'Phí vệ sinh' and year(thu_phi.ngayNop) = "+nam;
            st = con.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                for(PhiVeSinhModel i: result){
                    if(rs.getInt("idHoKhau")==i.getID()){
                        i.setSoTienDaDong(Integer.parseInt(rs.getString("soTien")));
                        i.setGhiChu(rs.getString("ghiChu"));
                        i.setIDPhiVeSinh(rs.getInt("maKhoanThu"));
                    }
                }
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    

    public void doubleClick(MouseEvent event){
        if(onMouse == phiVeSinh.getSelectionModel().getSelectedItem()){
            count++;
        }
        else{
            count = 1;
        }
        onMouse = phiVeSinh.getSelectionModel().getSelectedItem();
        if(count>=2){
            count = 0;
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/thuPhi/suadoithongtinui.fxml"));
                DialogPane input = loader.load();
                SuaDoiThongTinController suadoicontroller = loader.getController();
                suadoicontroller.setTienAndGhiChu(onMouse.getSoTienDaDong(), onMouse.getGhiChu());
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setDialogPane(input);
                Optional<ButtonType> bOptional = dialog.showAndWait();
                onMouse = phiVeSinh.getSelectionModel().getSelectedItem();
                if(bOptional.get() == ButtonType.APPLY){
                    int tienbf = Integer.parseInt(suadoicontroller.getTienTxtField().getText());
                    String ghiChuBf = suadoicontroller.getGhiChuTxtField().getText();
                    if(onMouse.getSoTienDaDong()==tienbf){
                        Alert info = new Alert(AlertType.INFORMATION);
                            info.setTitle("Lưu ý");
                            info.setContentText("Thông tin chưa thay đổi vui lòng nhập lại!");
                            info.showAndWait();
                    }
                    else{
                        onMouse.setSoTienDaDong(tienbf);
                        onMouse.setGhiChu(ghiChuBf);
                        Confirm();
                    }
                }
                if(bOptional.get()==ButtonType.CANCEL){

                }
                phiVeSinh.refresh();
            } catch (Exception e) {
                e.printStackTrace();
            }   
        }

}
    public void updateToDataBase(PhiVeSinhModel valueupdate){
        String query = "INSERT into thu_phi (thu_phi.IDHoKhau,thu_phi.tenKhoanThu,thu_phi.soTien,thu_phi.ngayNop,thu_phi.ghiChu,thu_phi.dot) VALUES (?,?,?,CURRENT_DATE(),?,Year(CURRENT_DATE));";
        try {
            Connection con = MysqlConnection.getMysqlConnection();
            PreparedStatement ppst = con.prepareStatement(query);
            ppst.setInt(1,valueupdate.getID());
            ppst.setString(2,"Phí vệ sinh");
            ppst.setString(3,String.valueOf(valueupdate.getSoTienDaDong()));
            ppst.setString(4,valueupdate.getGhiChu());
            ppst.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
}
    public void nhapNamAction(KeyEvent event){
        if(event.getCode().equals(KeyCode.ENTER)){
            phiVeSinhList.clear();
            for(PhiVeSinhModel i: getThongTinVeSinh(this.namTxtField.getText())){
                phiVeSinhList.add(i);
            }
        }
    }

    public void btnBackClick(ActionEvent event){
        //Donothing
    }

    public void Confirm(){
        Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Lưu ý");
            alert.setContentText("Bạn có muốn cập nhật thông tin vào cơ sở dữ liệu?");
            Optional<ButtonType> btn = alert.showAndWait();
            if(btn.get().equals(ButtonType.OK)){
                updateToDataBase(onMouse);
            }
            else{
                
            }
}
    public void nhapDonGia(KeyEvent event){
        if(event.getCode().equals(KeyCode.ENTER)){
            System.out.println("Here");
            int donGia =  Integer.parseInt(donGiaTxtField.getText());
            for(PhiVeSinhModel i: phiVeSinhList){
                i.setDonGia(donGia);
                i.setSoTienPhaiDong();
            }
            phiVeSinh.refresh();
        }
    }
    public void backClick(MouseEvent mouseEvent) {
        try {
            Pane pane = FXMLLoader.load(getClass().getResource("/view/thuPhi/thuphiui.fxml"));
            phiVeSinhPane.getChildren().clear();
            phiVeSinhPane.getChildren().add(pane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}