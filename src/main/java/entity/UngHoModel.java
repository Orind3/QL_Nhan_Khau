package entity;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class UngHoModel {
    
    private int ID;
    private int maHoKhau;
    private int IDHoKhau;
    private String tenChuHo;
    private String soTienDaDong;
    private String quy;
    private String ghiChu;
    private String dot;
    private String date;

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getDot() {
        return dot;
    }
    public void setDot(String dot) {
        this.dot = dot;
    }
    public UngHoModel(){

    }
    
    public UngHoModel(int iD, String tenChuHo, String soTienDaDong, String quy, String ghiChu) {
        ID = iD;
        this.tenChuHo = tenChuHo;
        this.soTienDaDong = soTienDaDong;
        this.quy = quy;
        this.ghiChu = ghiChu;
    }

    public UngHoModel(String tenChuHo, String soTienDaDong, String quy, String ghiChu) {
        this.tenChuHo = tenChuHo;
        this.soTienDaDong = soTienDaDong;
        this.quy = quy;
        this.ghiChu = ghiChu;
    }

    public UngHoModel(int ID,int IDHoKhau, String tenChuHo, String soTienDaDong, String quy, String ghiChu,String dot,Date date) {
        this.ID = ID;
        this.IDHoKhau = IDHoKhau;
        this.tenChuHo = tenChuHo;
        this.soTienDaDong = soTienDaDong;
        this.quy = quy;
        this.ghiChu = ghiChu;
        this.dot = dot;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
        this.date = dateFormat.format(date);
    }
    public UngHoModel(int ID,int IDHoKhau, String tenChuHo, String soTienDaDong, String quy, String ghiChu,String dot,int maHoKhau) {
        this.ID = ID;
        this.IDHoKhau = IDHoKhau;
        this.tenChuHo = tenChuHo;
        this.soTienDaDong = soTienDaDong;
        this.quy = quy;
        this.ghiChu = ghiChu;
        this.dot = dot;
        this.maHoKhau = maHoKhau;
    }

    
    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
    }
    public String getTenChuHo() {
        return tenChuHo;
    }
    public void setTenChuHo(String tenChuHo) {
        this.tenChuHo = tenChuHo;
    }
    public String getSoTienDaDong() {
        return soTienDaDong;
    }
    public void setSoTienDaDong(String soTienDaDong) {
        this.soTienDaDong = soTienDaDong;
    }
    public String getQuy() {
        return quy;
    }
    public void setQuy(String quy) {
        this.quy = quy;
    }
    public String getGhiChu() {
        return ghiChu;
    }
    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    public int getIDHoKhau() {
        return IDHoKhau;
    }
    public void setIDHoKhau(int iDHoKhau) {
        IDHoKhau = iDHoKhau;
    }
    public int getMaHoKhau() {
        return maHoKhau;
    }
    public void setMaHoKhau(int maHoKhau) {
        this.maHoKhau = maHoKhau;
    }

    
}
