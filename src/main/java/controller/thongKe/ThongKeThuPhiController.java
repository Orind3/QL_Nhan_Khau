package controller.thongKe;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.Vector;
import utility.*;
import entity.UngHoModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ThongKeThuPhiController implements Initializable {

    private ObservableList<String> listDot;
    private ObservableList<String> listHoKhau;
    private ObservableList<String> listQuy;
    private String dot,hoKhau,quy;
    private int tongtien = 0;
    private int soHoKhau = 0;
    
    @FXML
    private ChoiceBox<String> dotChoiceBox;

    @FXML
    private ChoiceBox<String> hoKhauChoiceBox;

    @FXML
    private ChoiceBox<String> quyChoiceBox;

    @FXML
    private Label soHoLabel;

    @FXML
    private Label soTienLabel;

    @FXML
    private TableView<UngHoModel> thongKeTableView;
    @FXML
    private TableColumn<UngHoModel, String> dotColumn;
    @FXML
    private TableColumn<UngHoModel, String> maHoKhauColumn;
    @FXML
    private TableColumn<UngHoModel, String> soTienColumn;
    @FXML
    private TableColumn<UngHoModel, String> tenChuHoColumn;
    @FXML
    private TableColumn<UngHoModel, String> tenQuyColumn;
    @FXML
    private Pane thongKePane;

    @FXML
    private TableColumn<UngHoModel, String> thoiGianColumn;

    ObservableList<UngHoModel> ungHoList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ungHoList = FXCollections.observableArrayList();
        listDot = FXCollections.observableArrayList("Tất cả");
        listHoKhau = FXCollections.observableArrayList("Tất cả");
        listQuy = FXCollections.observableArrayList("Tất cả");
        dot = "Tất cả";
        quy = "Tất cả";
        hoKhau = "Tất cả";

        dotColumn.setCellValueFactory(new PropertyValueFactory<>("dot"));
        tenChuHoColumn.setCellValueFactory(new PropertyValueFactory<>("tenChuHo"));
        maHoKhauColumn.setCellValueFactory(new PropertyValueFactory<>("maHoKhau"));
        soTienColumn.setCellValueFactory(new PropertyValueFactory<>("soTienDaDong"));
        tenQuyColumn.setCellValueFactory(new PropertyValueFactory<>("quy"));
        thoiGianColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        thongKeTableView.setRowFactory( tv -> {
            TableRow<UngHoModel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    UngHoModel rowData = row.getItem();
                    thongTinChiTiet(rowData);
                }
            });
            return row ;
        });

        quyChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                quy = listQuy.get((Integer) arg2);
                capNhat();
            }
            });
        dotChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                dot = listDot.get((Integer) arg2);
                capNhat();
            }
            });
        hoKhauChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                hoKhau = listHoKhau.get((Integer) arg2);
                capNhat();
            }
            });

        try {
            Connection con = MysqlConnection.getMysqlConnection();
            String query = "SELECT DISTINCT thu_phi.dot FROM thu_phi";
            Statement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                listDot.add(rs.getString("dot"));
            }
            query = "SELECT DISTINCT nhan_khau.hoTen FROM thu_phi LEFT JOIN ho_khau on thu_phi.IDHoKhau = ho_khau.idHoKhau LEFT JOIN nhan_khau on ho_khau.idChuHo = nhan_khau.idNhanKhau";
            st = con.prepareStatement(query);
            rs = st.executeQuery(query);
            while(rs.next()){
                listHoKhau.add(rs.getString("hoTen"));
            }
            query = "SELECT DISTINCT thu_phi.tenKhoanThu FROM thu_phi";
            st = con.prepareStatement(query);
            rs = st.executeQuery(query);
            while(rs.next()){
                listQuy.add(rs.getString("tenKhoanThu"));
            }
            dotChoiceBox.setItems(listDot);
            quyChoiceBox.setItems(listQuy);
            hoKhauChoiceBox.setItems(listHoKhau);
            query = "SELECT thu_phi.maKhoanThu,thu_phi.IDHoKhau,ho_khau.idHoKhau,nhan_khau.hoTen,thu_phi.soTien,thu_phi.tenKhoanThu,thu_phi.ghiChu,thu_phi.dot,thu_phi.ngayNop FROM thu_phi LEFT JOIN ho_khau ON thu_phi.IDHoKhau = ho_khau.idHoKhau LEFT JOIN nhan_khau on nhan_khau.idNhanKhau = ho_khau.idChuHo";
            st = con.prepareStatement(query);
            rs = st.executeQuery(query);
            while(rs.next()){
                ungHoList.add(new UngHoModel(rs.getInt("maKhoanthu"),rs.getInt("IDHoKhau"),rs.getString("hoTen"),rs.getString("soTien"),rs.getString("tenKhoanthu"),rs.getString("ghiChu"),rs.getString("dot"),rs.getDate("ngayNop")));
            }
            this.soHoKhau = listHoKhau.size()-1;
            this.soHoLabel.setText(String.valueOf(soHoKhau));
            for(UngHoModel i: ungHoList){
                tongtien += Integer.parseInt(i.getSoTienDaDong());
                this.soTienLabel.setText(String.valueOf(tongtien));
            }
            thongKeTableView.setItems(ungHoList);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public void capNhat(){
        ObservableList<UngHoModel> listUpdate = FXCollections.observableArrayList();

        for(UngHoModel i: ungHoList){
            listUpdate.add(i);
        }

        if(!dot.equals("Tất cả")){
            int index = 0;
            while(index<listUpdate.size()){
                if(!listUpdate.get(index).getDot().equals(dot)){
                    listUpdate.remove(index);
                }
                else{
                    index++;
                }
            }
        }
        if(!quy.equals("Tất cả")){
            int index = 0;
            while(index<listUpdate.size()){
                if(!listUpdate.get(index).getQuy().equals(quy)){
                    listUpdate.remove(index);
                }
                else{
                    index++;
                }
            }
        }
        if(!hoKhau.equals("Tất cả")){
            int index = 0;
            while(index<listUpdate.size()){
                if(!listUpdate.get(index).getTenChuHo().equals(hoKhau)){
                    listUpdate.remove(index);
                }
                else{
                    index++;
                }
            }
        }
        Vector<Integer> soHoKhau = new Vector<Integer>();
        boolean check = true;
        for(UngHoModel i: listUpdate){
            tongtien += Integer.parseInt(i.getSoTienDaDong());
            this.soTienLabel.setText(String.valueOf(tongtien));
            for(Integer j: soHoKhau){
                if(j.intValue()==i.getIDHoKhau()){
                    check = false;
                    continue;
                }
            }
            if(check){
                soHoKhau.add(i.getIDHoKhau());
            }
        }
        this.soHoLabel.setText(String.valueOf(soHoKhau.size()));
        thongKeTableView.setItems(listUpdate);
        thongKeTableView.refresh();
    }
    public void thongTinChiTiet(UngHoModel input){
        try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/thongKe/thongtinchitietui.fxml"));
                DialogPane load = loader.load();
                ThongTinChiTietController thongtincontroller = loader.getController();

                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setDialogPane(load);
                thongtincontroller.setUp(input);
                dialog.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void backThongKeClick(MouseEvent mouseEvent) {
        try {
            Pane pane = FXMLLoader.load(getClass().getResource("/view/thongKe/thongKe.fxml"));
            thongKePane.getChildren().clear();
            thongKePane.getChildren().add(pane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
