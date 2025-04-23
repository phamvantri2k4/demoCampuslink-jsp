package com.campuslinkjsp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.campuslinkjsp.dao.SinhVienDAO;
import com.campuslinkjsp.model.SinhVien;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/TimKiemCoBanServlet")
public class TimKiemCoBanServlet extends HttpServlet {
    private SinhVienDAO sinhVienDAO;

    @Override
    public void init() {
        sinhVienDAO = new SinhVienDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String soCMND = request.getParameter("soCMND");
        try {
            List<SinhVien> sinhViens = sinhVienDAO.searchSinhVien(soCMND);
            request.setAttribute("sinhViens", sinhViens);
            request.getRequestDispatcher("timKiemCoBan.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}