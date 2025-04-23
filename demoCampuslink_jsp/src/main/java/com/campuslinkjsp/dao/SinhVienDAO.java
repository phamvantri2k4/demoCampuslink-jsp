package com.campuslinkjsp.dao;

import com.campuslinkjsp.config.DBConnection;
import com.campuslinkjsp.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SinhVienDAO {
    private static final Logger logger = Logger.getLogger(SinhVienDAO.class.getName());
    private Connection conn;

    public SinhVienDAO() {
        try {
            conn = DBConnection.getConnection();
            logger.info("Kết nối cơ sở dữ liệu thành công.");
        } catch (SQLException e) {
            logger.severe("Lỗi khi kết nối cơ sở dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void addSinhVien(SinhVien sv, TotNghiep tn) throws SQLException {
        if (conn == null) {
            throw new SQLException("Không thể kết nối tới cơ sở dữ liệu!");
        }
        String sqlSinhVien = "INSERT INTO sinhvien (SoCMND, HoTen, Email, SoDT, DiaChi) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sqlSinhVien);
        ps.setString(1, sv.getSoCMND());
        ps.setString(2, sv.getHoTen());
        ps.setString(3, sv.getEmail());
        ps.setString(4, sv.getSoDT());
        ps.setString(5, sv.getDiaChi());
        ps.executeUpdate();

        String sqlTotNghiep = "INSERT INTO tot_nghiep (SoCMND, MaTruong, MaNganh, NgayTN) VALUES (?, ?, ?, ?)";
        ps = conn.prepareStatement(sqlTotNghiep);
        ps.setString(1, tn.getSoCMND());
        ps.setString(2, tn.getMaTruong());
        ps.setString(3, tn.getMaNganh());
        ps.setDate(4, tn.getNgayTN());
        ps.executeUpdate();
    }

    public List<Truong> getAllTruong() throws SQLException {
        List<Truong> truongs = new ArrayList<>();
        if (conn == null) {
            logger.severe("Không thể lấy danh sách trường: Kết nối cơ sở dữ liệu là null!");
            return truongs;
        }
        String sql = "SELECT * FROM truong";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Truong truong = new Truong();
            truong.setMaTruong(rs.getString("MaTruong"));
            truong.setTenTruong(rs.getString("TenTruong"));
            truongs.add(truong);
        }
        logger.info("Lấy danh sách trường thành công. Số lượng: " + truongs.size());
        return truongs;
    }

    public List<Nganh> getAllNganh() throws SQLException {
        List<Nganh> nganhs = new ArrayList<>();
        if (conn == null) {
            logger.severe("Không thể lấy danh sách ngành: Kết nối cơ sở dữ liệu là null!");
            return nganhs;
        }
        String sql = "SELECT * FROM nganh";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Nganh nganh = new Nganh();
            nganh.setMaNganh(rs.getString("MaNganh"));
            nganh.setTenNganh(rs.getString("TenNganh"));
            nganhs.add(nganh);
        }
        logger.info("Lấy danh sách ngành thành công. Số lượng: " + nganhs.size());
        return nganhs;
    }

    public List<SinhVien> searchSinhVien(String soCMND) throws SQLException {
        List<SinhVien> sinhViens = new ArrayList<>();
        if (conn == null) {
            throw new SQLException("Không thể tìm kiếm sinh viên: Kết nối cơ sở dữ liệu là null!");
        }
        String sql = "SELECT * FROM sinhvien WHERE SoCMND LIKE ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, "%" + soCMND + "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            SinhVien sv = new SinhVien();
            sv.setSoCMND(rs.getString("SoCMND"));
            sv.setHoTen(rs.getString("HoTen"));
            sv.setEmail(rs.getString("Email"));
            sv.setSoDT(rs.getString("SoDT"));
            sv.setDiaChi(rs.getString("DiaChi"));
            sinhViens.add(sv);
        }
        return sinhViens;
    }

    public List<KetQuaTimKiem> searchViecLam(String soCMND) throws SQLException {
        List<KetQuaTimKiem> ketQuas = new ArrayList<>();
        if (conn == null) {
            throw new SQLException("Không thể tìm kiếm việc làm: Kết nối cơ sở dữ liệu là null!");
        }
        String sql = "SELECT s.SoCMND, s.HoTen, tn.MaNganh AS MaNganhTotNghiep, tn.MaTruong, cv.MaNganh AS MaNganhCongTy, ct.TenCongTy, cv.ThoiGianLamViec " +
                "FROM sinhvien s " +
                "LEFT JOIN tot_nghiep tn ON s.SoCMND = tn.SoCMND " +
                "LEFT JOIN congviec cv ON s.SoCMND = cv.SoCMND " +
                "LEFT JOIN congty ct ON cv.MaCongTy = ct.MaCongTy " +
                "WHERE s.SoCMND LIKE ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, "%" + soCMND + "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            KetQuaTimKiem kq = new KetQuaTimKiem();
            kq.setSoCMND(rs.getString("SoCMND"));
            kq.setHoTen(rs.getString("HoTen"));
            kq.setMaNganhTotNghiep(rs.getString("MaNganhTotNghiep"));
            kq.setMaTruong(rs.getString("MaTruong"));
            kq.setMaNganhCongTy(rs.getString("MaNganhCongTy"));
            kq.setTenCongTy(rs.getString("TenCongTy"));
            kq.setThoiGianLamViec(rs.getDate("ThoiGianLamViec"));
            ketQuas.add(kq);
        }
        return ketQuas;
    }
}