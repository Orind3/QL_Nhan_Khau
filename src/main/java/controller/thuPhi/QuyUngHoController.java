package controller.thuPhi;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import utility.*;
import entity.UngHoModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.fxml.Initializable;

public class QuyUngHoController implements Initializable {
    @FXML
    private ObservableList<String> list;

    @FXML
    private TableColumn<UngHoModel, Integer> IDColumn;

    @FXML
    private Button btnCapNhat;
    @FXML
    private Pane ungHoPane;
    @FXML
    private Button btnThemQuyMoi;
    @FXML
    private TextField dotTxtField;


    @FXML
    private TableColumn<UngHoModel, String> ghiChuColumn;

    @FXML
    private ListView<String> listView;

    @FXML
    private TableColumn<UngHoModel, Integer> maHoKhauColumn;

    @FXML
    private TableColumn<UngHoModel, Integer> quyColumn;

    @FXML
    private TableColumn<UngHoModel, Integer> soTienDaDongColumn;

    @FXML
    private TableColumn<UngHoModel, Integer> tenChuHoColumn;
    @FXML
    private TableView<UngHoModel> tableView;

    private ObservableList<UngHoModel> listUngHo;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list = FXCollections.observableArrayList();
        list.add("(Tất cả)");
        listUngHo = FXCollections.observableArrayList();
        try {
            Connection con = MysqlConnection.getMysqlConnection();
            String query = "SELECT DISTINCT thu_phi.tenKhoanThu FROM thu_phi";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                list.add(rs.getString("tenKhoanThu"));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    listView.setItems(list);
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        maHoKhauColumn.setCellValueFactory(new PropertyValueFactory<>("maHoKhau"));
        tenChuHoColumn.setCellValueFactory(new PropertyValueFactory<>("tenChuHo"));
        soTienDaDongColumn.setCellValueFactory(new PropertyValueFactory<>("soTienDaDong"));
        quyColumn.setCellValueFactory(new PropertyValueFactory<>("quy"));
        ghiChuColumn.setCellValueFactory(new PropertyValueFactory<>("ghiChu"));
        try {
            Connection con = MysqlConnection.getMysqlConnection();
            String query = "SELECT thu_phi.maKhoanThu,ho_khau.idHoKhau,nhan_khau.hoTen,thu_phi.tenKhoanThu,thu_phi.soTien,thu_phi.ghiChu,thu_phi.dot FROM thu_phi LEFT JOIN ho_khau on ho_khau.idHoKhau = thu_phi.IDHoKhau LEFT JOIN nhan_khau on nhan_khau.idNhanKhau = ho_khau.idChuHo";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                listUngHo.add(new UngHoModel(rs.getInt("maKhoanThu"),rs.getInt("idHoKhau"), rs.getString("hoTen"), rs.getString("soTien"), rs.getString("tenKhoanThu"), rs.getString("ghiChu"),rs.getString("dot"),rs.getInt("idHoKhau")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        tableView.setRowFactory(null);

        tableView.setRowFactory( tv -> {
            TableRow<UngHoModel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    UngHoModel rowData = row.getItem();
                    chinhSuaThongTin(rowData);
                }
            });
            return row ;
        });
        tableView.setItems(listUngHo);
    }
    

    public void Click(MouseEvent event){
        String tenQuy = listView.getSelectionModel().getSelectedItem();
        ObservableList<UngHoModel> input = FXCollections.observableArrayList();
        if(!tenQuy.equals("(Tất cả)")){
            for(UngHoModel i: listUngHo){
                if(i.getQuy().equals(tenQuy)){
                    input.add(i);
                }
            }
            tableView.setItems(input);
        }
        else{
            tableView.setItems(listUngHo);
        }
        tableView.refresh();
    }
    public void chinhSuaThongTin(UngHoModel model){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/thuPhi/suadoiunghoui.fxml"));
            DialogPane input = loader.load();
            SuaDoiUngHoController suadoicontroller = loader.getController();
            suadoicontroller.getDataFromScene(model.getGhiChu(),String.valueOf(model.getSoTienDaDong()));
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(input);
            Optional<ButtonType> btn = dialog.showAndWait();
            if(btn.get()==ButtonType.APPLY){
                model.setGhiChu(suadoicontroller.getGhiChuTxtField().getText());
                model.setSoTienDaDong(suadoicontroller.getTienTxtField().getText());
                updateToDataBase(model);
                this.tableView.refresh();
            }
            if(btn.get()==ButtonType.CANCEL){

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void capNhat(ActionEvent event){
        String tenQuy = listView.getSelectionModel().getSelectedItem();
        ObservableList<UngHoModel> input = FXCollections.observableArrayList();
        if(!tenQuy.equals("(Tất cả)")){
            for(UngHoModel i: listUngHo){
                if(i.getQuy().equals(tenQuy)&&i.getDot().equals(dotTxtField.getText())||dotTxtField.getText()==null){
                    input.add(i);
                }
            }
            tableView.setItems(input);
        }
        else{
            for(UngHoModel i: listUngHo){
                if(i.getDot().equals(dotTxtField.getText())||dotTxtField.getText().equals("")){
                    input.add(i);
                }
            }
            tableView.setItems(input);
        }
        tableView.refresh();
    }
    public void themQuy(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/thuPhi/themquyunghoui.fxml"));
            DialogPane input = loader.load();
            ThemQuyController controller = loader.getController();
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(input);
            controller.setQuyList(list);
            Optional<ButtonType> bOptional = dialog.showAndWait();
            String tenQuy = controller.getChosen();
            ArrayList<UngHoModel> listHoKhau = taoQuyUngHo(tenQuy);
            if(bOptional.get().equals(ButtonType.OK)){
                if(!tenQuy.equals("")){
                    updateToDataBase(listHoKhau);
                    boolean check = false;
                    for(String i: list){
                        if(i.equals(tenQuy)) check = true;
                    }
                    if(!check){
                        list.add(tenQuy);
                        listView.refresh();
                    }
                }
            }
            if(bOptional.get().equals(ButtonType.CANCEL)){

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<UngHoModel> taoQuyUngHo(String tenQuy){
        String query = "";
        ArrayList<UngHoModel> listHoKhau = new ArrayList<>();
        try {
            Connection con = MysqlConnection.getMysqlConnection();
            query = "SELECT nhan_khau.hoTen,ho_khau.idHoKhau FROM ho_khau LEFT JOIN nhan_khau on nhan_khau.idNhanKhau = ho_khau.idChuHo";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                UngHoModel input = new UngHoModel();
                input.setTenChuHo(rs.getString("hoTen"));
                input.setIDHoKhau(rs.getInt("idHoKhau"));
                listHoKhau.add(input);
            }
            query = "SELECT thu_phi.dot FROM thu_phi WHERE thu_phi.tenKhoanThu LIKE ";
            PreparedStatement ppst = con.prepareStatement(query);
            query += "N'"+tenQuy+"' ORDER BY thu_phi.maKhoanThu DESC LIMIT 1;";
            rs = ppst.executeQuery(query);
            int dot = 0;
            while(rs.next()){
                dot = Integer.parseInt(rs.getString("dot"))+1;
            }
            for(UngHoModel i: listHoKhau){
                i.setDot(String.valueOf(dot));
                i.setQuy(tenQuy);
            }
            con.close();
            return listHoKhau;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHoKhau;
    }
        public void updateToDataBase(ArrayList<UngHoModel> listHoKhau){
           try {
            Connection con = MysqlConnection.getMysqlConnection();
            String query = "INSERT INTO thu_phi (thu_phi.IDHoKhau,thu_phi.tenKhoanThu,thu_phi.soTien,thu_phi.ghiChu,thu_phi.dot,thu_phi.ngayNop) VALUES (?,?,?,?,?,CURRENT_DATE())";
            PreparedStatement ppst = con.prepareStatement(query);
            for(UngHoModel i: listHoKhau){
                listUngHo.add(new UngHoModel(i.getTenChuHo(), "0", i.getQuy(), i.getGhiChu()));
                i.setSoTienDaDong("0");
                i.setGhiChu(null);
                ppst.setInt(1,i.getIDHoKhau());
                ppst.setString(2,i.getQuy());
                ppst.setString(3,i.getSoTienDaDong());
                ppst.setString(4,i.getGhiChu());
                ppst.setString(5,i.getDot());
                ppst.execute();
            }   
            tableView.refresh();
            con.close();
           } catch (Exception e) {
                e.printStackTrace();
           }
        }
        public void updateToDataBase(UngHoModel input){
            try {
                System.out.println("hele");
                Connection con = MysqlConnection.getMysqlConnection();
                String query = "UPDATE thu_phi SET thu_phi.soTien = N'" +input.getSoTienDaDong() +"', thu_phi.ghiChu = N'"+ input.getGhiChu()+ "' WHERE thu_phi.tenKhoanThu = N'" +input.getQuy()+"' and thu_phi.IDHoKhau = "+input.getIDHoKhau()+" and thu_phi.dot = N'"+input.getDot()+"';";
                PreparedStatement st = con.prepareStatement(query);
                // st.setString(1,"N'"+input.getSoTienDaDong()+"'");
                // st.setString(2,"N'"+input.getGhiChu()+"'");
                // st.setString(3,"N'"+input.getQuy()+"'");
                // st.setInt(4,input.getIDHoKhau());
                // st.setString(5,"N'"+input.getDot()+"'");
                // System.out.println(input.getIDHoKhau() + input.getQuy() + input.getDot());
                st.executeUpdate();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public void backMouseClick(MouseEvent mouseEvent) {
            try {
                Pane pane = FXMLLoader.load(getClass().getResource("/view/thuPhi/thuphiui.fxml"));
                ungHoPane.getChildren().clear();
                ungHoPane.getChildren().add(pane);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}
