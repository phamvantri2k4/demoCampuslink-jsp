package com.campuslinkjsp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.campuslinkjsp.dao.SinhVienDAO;
import com.campuslinkjsp.model.SinhVien;
import com.campuslinkjsp.model.TotNghiep;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

@WebServlet("/NhapThongTinServlet")
public class NhapThongTinServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(NhapThongTinServlet.class.getName());
    private SinhVienDAO sinhVienDAO;

    @Override
    public void init() {
        sinhVienDAO = new SinhVienDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Lấy danh sách trường và ngành từ cơ sở dữ liệu
            var truongs = sinhVienDAO.getAllTruong();
            var nganhs = sinhVienDAO.getAllNganh();

            // Ghi log để kiểm tra
            logger.info("Số lượng trường: " + (truongs != null ? truongs.size() : "null"));
            logger.info("Số lượng ngành: " + (nganhs != null ? nganhs.size() : "null"));

            request.setAttribute("truongs", truongs);
            request.setAttribute("nganhs", nganhs);
            request.getRequestDispatcher("nhapThongTin.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.severe("Lỗi khi lấy danh sách trường hoặc ngành: " + e.getMessage());
            request.setAttribute("error", "Lỗi khi lấy danh sách trường hoặc ngành: " + e.getMessage());
            request.getRequestDispatcher("nhapThongTin.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String soCMND = request.getParameter("soCMND");
        String hoTen = request.getParameter("hoTen");
        String email = request.getParameter("email");
        String soDT = request.getParameter("soDT");
        String diaChi = request.getParameter("diaChi");
        String maTruong = request.getParameter("maTruong");
        String maNganh = request.getParameter("maNganh");
        String ngayTNStr = request.getParameter("ngayTN");

        // Kiểm tra bắt buộc nhập
        if (soCMND == null || soCMND.isEmpty() || maTruong == null || maTruong.isEmpty() || maNganh == null || maNganh.isEmpty()) {
            request.setAttribute("error", "Vui lòng nhập đầy đủ các trường bắt buộc!");
            doGet(request, response);
            return;
        }

        // Kiểm tra ngày tốt nghiệp hợp lệ
        Date ngayTN = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            java.util.Date utilDate = sdf.parse(ngayTNStr);
            ngayTN = new Date(utilDate.getTime());
        } catch (ParseException e) {
            request.setAttribute("error", "Ngày tốt nghiệp không hợp lệ!");
            doGet(request, response);
            return;
        }

        // Lưu thông tin vào CSDL
        try {
            SinhVien sv = new SinhVien();
            sv.setSoCMND(soCMND);
            sv.setHoTen(hoTen);
            sv.setEmail(email);
            sv.setSoDT(soDT);
            sv.setDiaChi(diaChi);

            TotNghiep tn = new TotNghiep();
            tn.setSoCMND(soCMND);
            tn.setMaTruong(maTruong);
            tn.setMaNganh(maNganh);
            tn.setNgayTN(ngayTN);

            sinhVienDAO.addSinhVien(sv, tn);
            response.sendRedirect("nhapThongTin.jsp?success=1");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.severe("Lỗi khi lưu dữ liệu: " + e.getMessage());
            request.setAttribute("error", "Lỗi khi lưu dữ liệu: " + e.getMessage());
            doGet(request, response);
        }
    }
}