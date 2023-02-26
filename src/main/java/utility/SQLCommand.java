package utility;

public class SQLCommand {
    // nguoi_dung
    public static String NGUOI_DUNG_QUERY_DANG_NHAP = "SELECT * FROM nguoi_dung WHERE taiKhoan = ? AND matKhau = ?";

    // nhan_khau
    public static String NHAN_KHAU_QUERY_TONG_THUONG_TRU = "SELECT COUNT(*) FROM nhan_khau WHERE trangThai = N'Thường trú'";
    public static String NHAN_KHAU_QUERY_TONG_DA_CHUYEN_DI = "SELECT COUNT(*) FROM nhan_khau WHERE trangThai = N'Đã chuyển đi'";
    public static String NHAN_KHAU_QUERY_TONG_DA_MAT = "SELECT COUNT(*) FROM nhan_khau WHERE trangThai = N'Đã mất'";
    public static String NHAN_KHAU_DUOI_18_TUOI = "SELECT idNhanKhau, DATEDIFF(CURRENT_DATE, ngaySinh)/365 as tuoi FROM nhan_khau WHERE DATEDIFF(CURRENT_DATE, ngaySinh)/365 < 18";
    public static String NHAN_KHAU_QUERY_TONG_KHONG_XAC_DINH = "SELECT COUNT(*) FROM nhan_khau WHERE trangThai = N''";
//    public static String NHAN_KHAU_QUERY_TONG_TAM_TRU = "SELECT COUNT(*)\n" +
//            "FROM nhan_khau nk, tam_tru tt\n" +
//            "WHERE nk.idNhanKhau = tt.idNhanKhau\n" +
//            "AND nk.trangThai = N'Tạm trú'\n" +
//            "AND tt.denNgay > GETDATE()";
//    public static String NHAN_KHAU_QUERY_TONG_TAM_VANG = "SELECT COUNT(*)\n" +
//            "FROM nhan_khau nk, tam_vang tt\n" +
//            "WHERE nk.idNhanKhau = tt.idNhanKhau\n" +
//            "AND nk.trangThai = N'Tạm vắng'\n" +
//            "AND tt.denNgay > GETDATE()";    public static String NHAN_KHAU_QUERY_LAY_THONG_TIN = "SELECT * FROM nhan_khau";
    public static String NHAN_KHAU_QUERY_TONG_TAM_TRU = "SELECT COUNT(*)\n" +
            "FROM nhan_khau nk, tam_tru tt\n" +
            "WHERE nk.idNhanKhau = tt.idNhanKhau\n" +
            "AND nk.trangThai = N'Tạm trú'\n" +
            "AND tt.denNgay > NOW()";
    public static String NHAN_KHAU_QUERY_TONG_TAM_VANG = "SELECT COUNT(*)\n" +
            "FROM nhan_khau nk, tam_vang tt\n" +
            "WHERE nk.idNhanKhau = tt.idNhanKhau\n" +
            "AND nk.trangThai = N'Tạm vắng'\n" +
            "AND tt.denNgay > NOW()";    public static String NHAN_KHAU_QUERY_LAY_THONG_TIN = "SELECT * FROM nhan_khau";

    public static String NHAN_KHAU_QUERY_NAM = "SELECT COUNT(*) FROM nhan_khau WHERE gioiTinh = N'Nam'";
    public static String NHAN_KHAU_QUERY_NU = "SELECT COUNT(*) FROM nhan_khau WHERE gioiTinh = N'Nữ'";
    public static String NHAN_KHAU_QUERY_UPDATE =  "UPDATE `nhan_khau` SET " +
            "`hoTen`=?," +
            "`biDanh`=?," +
            "`ngaySinh`=?," +
            "`noiSinh`=?," +
            "`gioiTinh`=?," +
            "`nguyenQuan`=?," +
            "`danToc`=?," +
            "`tonGiao`=?," +
            "`quocTich`=?," +
            "`ngheNghiep`=?," +
            "`noiLamViec`=?," +
            "`cmnd`=?," +
            "`ngayCap`=?," +
            "`chuyenDenNgay`=?," +
            "`noiThuongTruTruoc`=?  WHERE idNhanKhau  = ";
    public static String NHAN_KHAU_QUERY_UPDATE_TRANGTHAI    ="UPDATE `nhan_khau` SET " +

            "`trangThai`=?  WHERE idNhanKhau  =";
    public static String NHAN_KHAU_QUERY_INSERT_CHUYENNHANKHAU="INSERT INTO `chuyen_nhan_khau`( `idNhanKhau`, `ngayChuyenDi`, `noiChuyenDen`, `ghiChu`) VALUES (?,?,?,?)";
    public static String NHAN_KHAU_QUERY_HOTEN="SELECT * FROM `nhan_khau` WHERE hoTen like '%";
    public static String NHAN_KHAU_QUERY_CMND="SELECT * FROM `nhan_khau` WHERE cmnd like '%";
    public static String NHAN_KHAU_QUERY_TRANGTHAI="SELECT * FROM `nhan_khau` WHERE trangThai like '%";
    public static String NHAN_KHAU_QUERY_NGAYSINH="SELECT * FROM `nhan_khau` WHERE ngaySinh like '%";
    public static String NHAN_KHAU_QUERY_INSERT_KHAITU="INSERT INTO `khai_tu`(`idNguoiMat`, `idNguoiKhai`, `ngayKhai`, `ngayMat`, `liDoMat`) VALUES (?,?,?,?,?)";
    public static String NHAN_KHAU_QUERY_INSERT_TAMVANG="INSERT INTO `tam_vang`( `idNhanKhau`, `noiTamTru`, `tuNgay`,`denNgay`, `lyDo`) VALUES (?,?,?,?,?)";
    public static String NHAN_KHAU_QUERY_INSERT_TAMTRU="INSERT INTO `tam_tru`( `idNhanKhau`, `noiThuongTru`, `noiTamTru`, `tuNgay`,`denNgay`, `lyDo`) VALUES (?,?,?,?,?,?)";
    public static String NHAN_KHAU_QUERY_INSERT_NHANKHAU="INSERT INTO `nhan_khau`( `hoTen`, `biDanh`, `ngaySinh`, `noiSinh`, `gioiTinh`, `nguyenQuan`, `danToc`, `tonGiao`, `quocTich`, `ngheNghiep`, `noiLamViec`, `cmnd`, `ngayCap`, `chuyenDenNgay`, `noiThuongTruTruoc`, `trangThai`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//    public static String NHAN_KHAU_QUERY_MAM_NON = "SELECT COUNT(*) FROM nhan_khau WHERE YEAR(GETDATE()) - YEAR(ngaySinh) <= 5";
//    public static String NHAN_KHAU_QUERY_CAP_1 = "SELECT COUNT(*) FROM nhan_khau WHERE YEAR(GETDATE()) - YEAR(ngaySinh) >= 6 AND YEAR(GETDATE()) - YEAR(ngaySinh) <= 10";
//    public static String NHAN_KHAU_QUERY_CAP_2 = "SELECT COUNT(*) FROM nhan_khau WHERE YEAR(GETDATE()) - YEAR(ngaySinh) >= 11 AND YEAR(GETDATE()) - YEAR(ngaySinh) <= 14";
//    public static String NHAN_KHAU_QUERY_CAP_3 = "SELECT COUNT(*) FROM nhan_khau WHERE YEAR(GETDATE()) - YEAR(ngaySinh) >= 15 AND YEAR(GETDATE()) - YEAR(ngaySinh) <= 17";
//    public static String NHAN_KHAU_QUERY_DO_TUOI_LAO_DONG = "SELECT COUNT(*) FROM nhan_khau WHERE YEAR(GETDATE()) - YEAR(ngaySinh) >= 18 AND YEAR(GETDATE()) - YEAR(ngaySinh) <= 64";
//    public static String NHAN_KHAU_QUERY_NGHI_HUU = "SELECT COUNT(*) FROM nhan_khau WHERE YEAR(GETDATE()) - YEAR(ngaySinh) >= 65";
    public static String NHAN_KHAU_QUERY_MAM_NON = "SELECT COUNT(*) FROM nhan_khau WHERE YEAR(NOW()) - YEAR(ngaySinh) <= 5";
    public static String NHAN_KHAU_QUERY_CAP_1 = "SELECT COUNT(*) FROM nhan_khau WHERE YEAR(NOW()) - YEAR(ngaySinh) >= 6 AND YEAR(NOW()) - YEAR(ngaySinh) <= 10";
    public static String NHAN_KHAU_QUERY_CAP_2 = "SELECT COUNT(*) FROM nhan_khau WHERE YEAR(NOW()) - YEAR(ngaySinh) >= 11 AND YEAR(NOW()) - YEAR(ngaySinh) <= 14";
    public static String NHAN_KHAU_QUERY_CAP_3 = "SELECT COUNT(*) FROM nhan_khau WHERE YEAR(NOW()) - YEAR(ngaySinh) >= 15 AND YEAR(NOW()) - YEAR(ngaySinh) <= 17";
    public static String NHAN_KHAU_QUERY_DO_TUOI_LAO_DONG = "SELECT COUNT(*) FROM nhan_khau WHERE YEAR(NOW()) - YEAR(ngaySinh) >= 18 AND YEAR(NOW()) - YEAR(ngaySinh) <= 64";
    public static String NHAN_KHAU_QUERY_NGHI_HUU = "SELECT COUNT(*) FROM nhan_khau WHERE YEAR(NOW()) - YEAR(ngaySinh) >= 65";


    // ho_khau
    public static String HO_KHAU_QUERY_TONG_THUONG_TRU = "SELECT COUNT(*) FROM ho_khau WHERE trangThai = N'Thường trú'";
    public static String HO_KHAU_QUERY_TONG_DA_CHUYEN_DI = "SELECT COUNT(*) FROM ho_khau WHERE trangThai = N'Đã chuyển đi'";
    public static String HO_KHAU_QUERY_LAY_THONG_TIN = "SELECT * FROM ho_khau";
//    public static String HO_KHAU_QUERY_SO_LUONG_THANH_VIEN = "SELECT soLuong, COUNT(idHoKhau) AS soHoKhau\n" +
//            "FROM (SELECT hk.idHoKhau, ISNULL(COUNT(hknk.idNhanKhau), 0) + 1 AS soLuong\n" +
//            "\t\tFROM ho_khau hk\n" +
//            "\t\tLEFT JOIN ho_khau_nhan_khau hknk ON hk.idHoKhau = hknk.idHoKhau\n" +
//            "\t\tGROUP BY(hk.idHoKhau)) temp\n" +
//            "GROUP BY soLuong";
    public static String HO_KHAU_QUERY_SO_LUONG_THANH_VIEN = "SELECT soLuong, COUNT(idHoKhau) AS soHoKhau\n" +
            "FROM (SELECT hk.idHoKhau, IFNULL(COUNT(hknk.idNhanKhau), 0) + 1 AS soLuong\n" +
            "      FROM ho_khau hk\n" +
            "               LEFT JOIN ho_khau_nhan_khau hknk ON hk.idHoKhau = hknk.idHoKhau\n" +
            "      GROUP BY(hk.idHoKhau)) temp\n" +
            "GROUP BY soLuong";

    public static String HO_KHAU_QUERY_DELETE_HK = "DELETE FROM `ho_khau` WHERE idHoKhau = ?";
    public static String HO_KHAU_QUERY_UPDATE_NK_AFTER_DELETE = "UPDATE `nhan_khau` SET trangThai = ? WHERE idNhanKhau IN (SELECT idNhanKhau FROM `ho_khau_nhan_khau` WHERE idHoKhau = ?)";
    public static String HO_KHAU_QUERY_UPDATE_CH_AFTER_DELETE = "UPDATE `nhan_khau` SET trangThai = ? WHERE idNhanKhau IN (SELECT idChuHo FROM `ho_khau` WHERE idHoKhau = ?)";
    public static String HO_KHAU_QUERY_LOADDATAHOKHAUCONTROLLER = "SELECT hk.*, nk.hoTen FROM `ho_khau` hk, `nhan_khau` nk WHERE hk.idChuHo = nk.idNhanKhau";
    public static String HO_KHAU_QUERY_CHECK_CHU_HO = "SELECT * FROM `ho_khau`";
    public static String HO_KHAU_QUERY_XAC_NHAN_BUTTON = "INSERT INTO ho_khau(idChuHo, tinhThanhPho, quanHuyen, phuongXa, diaChi, ngayTao, trangThai) VALUES (?,?,?,?,?,?,?)";
    public static String HO_KHAU_QUERY_IDHOKHAU_MOI_NHAT = "SELECT idHoKhau FROM `ho_khau` ORDER BY idHoKhau DESC";
    public static String HO_KHAU_QUERY_UPDATE_NK_AFTER_ADD = "UPDATE `nhan_khau` SET trangThai = ? WHERE idNhanKhau IN (SELECT idNhanKhau FROM `ho_khau_nhan_khau` WHERE idHoKhau = ?)";
    public static String HO_KHAU_QUERY_UPDATE_CH_AFTER_ADD = "UPDATE `nhan_khau` SET trangThai = ? WHERE idNhanKhau IN (SELECT idChuHo FROM `ho_khau` WHERE idHoKhau = ?)";
    public static String HO_KHAU_QUERY_LOADDATATHEMHOKHAUCONTROLLER = "SELECT * FROM `nhan_khau`";
    public static String HO_KHAU_QUERY_HOTEN_CHU_HO = "SELECT nk.hoTen FROM `ho_khau` hk, `nhan_khau` nk WHERE hk.idChuho = nk.idNhanKhau and hk.idHoKhau = ?";
    public static String HO_KHAU_QUERY_LOADDATAXEMHOKHAUCONTROLLER = "SELECT hknk.idHoKhau, hknk.idNhanKhau, hknk.quanHeChuHo, nk.hoTen, nk.ngaySinh, nk.cmnd FROM `ho_khau_nhan_khau` hknk, `nhan_khau` nk WHERE hknk.idNhanKhau = nk.idNhanKhau and hknk.idHoKhau = ?";
    public static String HO_KHAU_QUERY_CHANGE_INF_HK = "UPDATE `ho_khau` SET idChuHo = ? WHERE idHoKhau = ?";
    public static String HO_KHAU_QUERY_UPDATE_NK_BEFORE_DELETE = "UPDATE `nhan_khau` SET trangThai = ? WHERE idNhanKhau IN (SELECT idNhanKhau FROM `ho_khau_nhan_khau` WHERE idHoKhau = ?)";
    public static String HO_KHAU_QUERY_UPDATE_NK_AFTER_CHANGE = "UPDATE `nhan_khau` SET trangThai = ? WHERE idNhanKhau IN (SELECT idNhanKhau FROM `ho_khau_nhan_khau` WHERE idHoKhau = ?)";
    public static String HO_KHAU_QUERY_UPDATE_CH_BEFORE_DELETE = "UPDATE `nhan_khau` SET trangThai = ? WHERE idNhanKhau IN (SELECT idChuHo FROM `ho_khau` WHERE idHoKhau = ?)";
    public static String HO_KHAU_QUERY_UPDATE_CH_AFTER_CHANGE = "UPDATE `nhan_khau` SET trangThai = ? WHERE idNhanKhau IN (SELECT idChuHo FROM `ho_khau` WHERE idHoKhau = ?)";
    public static String HO_KHAU_QUERY_CHECK_NHAN_KHAU_EXIST_HK = "SELECT * FROM `ho_khau` WHERE idHoKhau != ?";
    public static String HO_KHAU_QUERY_LOADDATASUAHKCONTROLLER = "SELECT hknk.idHoKhau, hknk.idNhanKhau, hknk.quanHeChuHo, nk.cmnd, nk.hoTen, nk.ngaySinh FROM `ho_khau_nhan_khau` hknk, `nhan_khau` nk WHERE hknk.idNhanKhau = nk.idNhanKhau and hknk.idHoKhau = ?";
    public static String HO_KHAU_QUERY_LOADNKSUAHKCONTROLLER = "SELECT * FROM `nhan_khau`";
    public static String HO_KHAU_QUERY_HOTEN_CHU_HO_CHANGE_INT = "SELECT nk.hoTen, nk.idNhanKhau FROM `ho_khau` hk, `nhan_khau` nk WHERE hk.idChuho = nk.idNhanKhau and hk.idHoKhau = ?";
    public static String HO_KHAU_QUERY_HOTEN_CHU_HO_CHANGE_STRING = "SELECT nk.hoTen, nk.idNhanKhau FROM `ho_khau` hk, `nhan_khau` nk WHERE hk.idChuho = nk.idNhanKhau and hk.idHoKhau = ?";
    public static String HO_KHAU_QUERY_CHECK_NHAN_KHAU_EXIST_NK = "SELECT * FROM `ho_khau_nhan_khau`";
    public static String HO_KHAU_QUERY_THEMNHANKHAU = "INSERT INTO `ho_khau_nhan_khau` VALUES (?,?,?)";
    public static String HO_KHAU_QUERY_CHANGE_INF_HKNK = "INSERT INTO `ho_khau_nhan_khau` VALUES (?,?,?)";
    public static String HO_KHAU_QUERY_CHECK_NHAN_KHAU_EXIST_NK_1 = "SELECT * FROM `ho_khau_nhan_khau` WHERE idHoKhau != ?";
    public static String HO_KHAU_QUERY_CLEAR_HKNK = "DELETE FROM `ho_khau_nhan_khau` WHERE idHoKhau = ?";
    public static String HO_KHAU_QUERY_UPDATE_NK_HK_HIEN_TAI = "UPDATE `nhan_khau` SET trangThai = ? WHERE idNhanKhau IN (SELECT idNhanKhau FROM `ho_khau_nhan_khau` WHERE idHoKhau = ?)";
    public static String HO_KHAU_QUERY_UPDATE_CH_HK_HIEN_TAI = "UPDATE `nhan_khau` SET trangThai = ? WHERE idNhanKhau IN (SELECT idChuHo FROM `ho_khau` WHERE idHoKhau = ?)";
    public static String HO_KHAU_QUERY_UPDATE_ID_CH_HIEN_TAI = "UPDATE `ho_khau` SET idChuHo = ? WHERE idHoKhau = ?";
    public static String HO_KHAU_QUERY_DELETE_ALL_NK_FROM_HK_HIEN_TAI = "DELETE FROM `ho_khau_nhan_khau` WHERE idHoKhau = ?";
    public static String HO_KHAU_QUERY_INSERT_ALL_NK_TO_HK_HIEN_TAI = "INSERT INTO `ho_khau_nhan_khau` VALUES (?,?,?)";
    public static String HO_KHAU_QUERY_UPDATE_ALL_NK_FROM_HK_HIEN_TAI = "UPDATE `nhan_khau` SET trangThai = ? WHERE idNhanKhau IN (SELECT idNhanKhau FROM `ho_khau_nhan_khau` WHERE idHoKhau = ?)";
    public static String HO_KHAU_QUERY_UPDATE_CH_FROM_HK_HIEN_TAI = "UPDATE `nhan_khau` SET trangThai = ? WHERE idNhanKhau IN (SELECT idChuHo FROM `ho_khau` WHERE idHoKhau = ?)";
    public static String HO_KHAU_QUERY_CREATE_NEW_HK = "INSERT INTO ho_khau(idChuHo, tinhThanhPho, quanHuyen, phuongXa, diaChi, ngayTao, trangThai) VALUES (?,?,?,?,?,?,?)";
    public static String HO_KHAU_QUERY_ID_NEW_HOKHAU = "SELECT idHoKhau FROM `ho_khau` ORDER BY idHoKhau DESC";
    public static String HO_KHAU_QUERY_INSERT_ALL_NK_TO_HK_MOI = "INSERT INTO `ho_khau_nhan_khau` VALUES (?,?,?)";
    public static String HO_KHAU_QUERY_UPDATE_NEW_CH = "UPDATE `nhan_khau` SET trangThai = ? WHERE idNhanKhau IN (SELECT idChuHo FROM `ho_khau` WHERE idHoKhau = ?)";
    public static String HO_KHAU_QUERY_UPDATE_NEW_NK = "UPDATE `nhan_khau` SET trangThai = ? WHERE idNhanKhau IN (SELECT idNhanKhau FROM `ho_khau_nhan_khau` WHERE idHoKhau = ?)";
    public static String HO_KHAU_QUERY_LOADDATANKTACHHKCONTROLLER = "SELECT a.idNhanKhau,a.quanHeChuHo,b.hoTen,b.ngaySinh FROM `ho_khau_nhan_khau` a, `nhan_khau` b WHERE a.idNhanKhau = b.idNhanKhau and a.idHoKhau = ?";
    public static String HO_KHAU_QUERY_LOADDATACHTACHHKCONTROLLER = "SELECT b.idNhanKhau,b.hoTen,b.ngaySinh FROM `ho_khau` a, `nhan_khau` b WHERE a.idChuHo = b.idNhanKhau and a.idHoKhau = ?";
    public static String HO_KHAU_QUERY_CHUYEN_HO_KHAU = "INSERT INTO `chuyen_ho_khau`(idHoKhau, ngayChuyenDi, noiChuyenDen, ghiChu) VALUES (?,?,?,?)";
    public static String HO_KHAU_QUERY_UPDATE_TRANGTHAI_HOKHAU = "UPDATE `ho_khau` SET diaChi = ?, tinhThanhPho = ?, quanHuyen = ?, phuongXa = ?,trangThai = ? WHERE idHoKhau = ? ";
    public static String HO_KHAU_QUERY_UPDATE_TRANGTHAI_NHANKHAU = "UPDATE `nhan_khau` SET trangThai = ? WHERE idNhanKhau IN (SELECT idNhanKhau FROM `ho_khau_nhan_khau` WHERE idHoKhau = ?) ";
    public static String HO_KHAU_QUERY_UPDATE_TRANGTHAI_CHUHO = "UPDATE `nhan_khau` SET trangThai = ? WHERE idNhanKhau IN (SELECT idChuho FROM `ho_khau` WHERE idHoKhau = ?) ";
    public static String HO_KHAU_QUERY_LOADDATA = "SELECT * FROM `chuyen_ho_khau` WHERE idHoKhau = ?";
}
