package entity;

public class PhiVeSinhModel {
    private int IDPhiVeSinh;
    private int ID;
    private String tenChuHo;
    private int soThanhVien;
    private int soTienPhaiDong;
    private int soTienDaDong;
    private String ghiChu;
    private int donGia;

    
    public PhiVeSinhModel(int iD, String tenChuHo, int soThanhVien, int donGia, int soTienDaDong,
            String ghiChu) {
        ID = iD;
        this.tenChuHo = tenChuHo;
        this.soThanhVien = soThanhVien;
        this.soTienPhaiDong = soThanhVien*donGia*12;
        this.soTienDaDong = soTienDaDong;
        this.ghiChu = ghiChu;
        this.donGia = donGia;
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
    public int getSoThanhVien() {
        return soThanhVien;
    }
    public void setSoThanhVien(int soThanhVien) {
        this.soThanhVien = soThanhVien;
    }
    public int getSoTienPhaiDong() {
        return soTienPhaiDong;
    }
    public void setSoTienPhaiDong(int soTienPhaiDong) {
        this.soTienPhaiDong = soTienPhaiDong;
    }
    public void setSoTienPhaiDong() {
        this.soTienPhaiDong = soThanhVien*donGia;
    }
    public int getSoTienDaDong() {
        return soTienDaDong;
    }
    public void setSoTienDaDong(int soTienDaDong) {
        this.soTienDaDong = soTienDaDong;
    }
    public String getGhiChu() {
        return ghiChu;
    }
    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    public int getDonGia() {
        return donGia;
    }
    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public int getIDPhiVeSinh() {
        return IDPhiVeSinh;
    }


    public void setIDPhiVeSinh(int iDPhiVeSinh) {
        IDPhiVeSinh = iDPhiVeSinh;
    }
    
    
}
