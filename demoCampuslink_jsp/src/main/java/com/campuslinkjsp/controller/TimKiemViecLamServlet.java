package com.campuslinkjsp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.campuslinkjsp.dao.SinhVienDAO;
import com.campuslinkjsp.model.KetQuaTimKiem;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/TimKiemViecLamServlet")
public class TimKiemViecLamServlet extends HttpServlet {
    private SinhVienDAO sinhVienDAO;

    @Override
    public void init() {
        sinhVienDAO = new SinhVienDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String soCMND = request.getParameter("soCMND");
        try {
            List<KetQuaTimKiem> ketQuas = sinhVienDAO.searchViecLam(soCMND);
            request.setAttribute("ketQuas", ketQuas);
            request.getRequestDispatcher("timKiemViecLam.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}