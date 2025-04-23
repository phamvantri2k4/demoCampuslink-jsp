<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nhập thông tin sinh viên</title>
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background: linear-gradient(135deg, #74ebd5, #acb6e5);
            margin: 0;
            padding: 40px;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .container {
            max-width: 700px;
            width: 100%;
            background: #fff;
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 8px 24px rgba(0, 0, 0, 0.15);
            border: 1px solid #e0e0e0;
        }

        h2 {
            color: #2c3e50;
            text-align: center;
            margin-bottom: 30px;
            font-size: 28px;
            font-weight: 700;
            text-transform: uppercase;
            letter-spacing: 1px;
        }

        form {
            display: flex;
            flex-direction: column;
        }

        label {
            color: #34495e;
            font-size: 16px;
            font-weight: 500;
            margin-bottom: 8px;
        }

        input, select {
            width: 100%;
            padding: 12px;
            margin-bottom: 20px;
            border: 2px solid #dfe6e9;
            border-radius: 8px;
            font-size: 16px;
            transition: border-color 0.3s, box-shadow 0.3s;
            background-color: #f9f9f9;
        }

        input:focus, select:focus {
            border-color: #0984e3;
            box-shadow: 0 0 8px rgba(9, 132, 227, 0.3);
            outline: none;
        }

        input[type="submit"] {
            background: #0984e3;
            color: #fff;
            border: none;
            padding: 14px;
            border-radius: 8px;
            font-size: 18px;
            font-weight: 600;
            cursor: pointer;
            transition: background 0.3s, transform 0.2s;
        }

        input[type="submit"]:hover {
            background: #0652dd;
            transform: translateY(-2px);
        }

        .error {
            color: #e74c3c;
            font-size: 14px;
            font-weight: 600;
            margin: 10px 0;
            text-align: center;
        }

        .success {
            color: #27ae60;
            font-size: 14px;
            font-weight: 600;
            margin: 10px 0;
            text-align: center;
        }

        a {
            display: block;
            text-align: center;
            color: #0984e3;
            font-size: 16px;
            font-weight: 500;
            text-decoration: none;
            margin-top: 20px;
            transition: color 0.3s;
        }

        a:hover {
            color: #0652dd;
            text-decoration: underline;
        }

        .debug {
            color: #7f8c8d;
            font-size: 12px;
            margin-top: 15px;
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Nhập thông tin sinh viên</h2>
    <form action="NhapThongTinServlet" method="post">
        <label for="soCMND">Số CMND:</label>
        <input type="text" id="soCMND" name="soCMND" required>

        <label for="hoTen">Họ Tên:</label>
        <input type="text" id="hoTen" name="hoTen">

        <label for="email">Email:</label>
        <input type="email" id="email" name="email">

        <label for="soDT">Số ĐT:</label>
        <input type="text" id="soDT" name="soDT">

        <label for="diaChi">Địa chỉ:</label>
        <input type="text" id="diaChi" name="diaChi">

        <label for="maTruong">Trường:</label>
        <select id="maTruong" name="maTruong" required>
            <option value="">Chọn trường</option>
            <c:forEach var="truong" items="${truongs}">
                <option value="${truong.maTruong}">${truong.tenTruong}</option>
            </c:forEach>
        </select>

        <label for="maNganh">Ngành:</label>
        <select id="maNganh" name="maNganh" required>
            <option value="">Chọn ngành</option>
            <c:forEach var="nganh" items="${nganhs}">
                <option value="${nganh.maNganh}">${nganh.tenNganh}</option>
            </c:forEach>
        </select>

        <label for="ngayTN">Ngày tốt nghiệp:</label>
        <input type="date" id="ngayTN" name="ngayTN" required>

        <input type="submit" value="Lưu thông tin">
    </form>

    <c:if test="${not empty error}">
        <p class="error">${error}</p>
    </c:if>
    <c:if test="${param.success == '1'}">
        <p class="success">Lưu thông tin thành công!</p>
    </c:if>
    <a href="trangChuTruong.jsp">Quay lại trang chủ</a>
</div>
</body>
</html>