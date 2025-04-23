<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang chủ - Quản lý việc làm sinh viên</title>
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background: linear-gradient(135deg, #74ebd5, #acb6e5);
            margin: 0;
            padding: 20px;
            display: flex;
            justify-content: center;
            min-height: 100vh;
        }
        .container {
            max-width: 600px;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.15);
            text-align: center;
        }
        .header {
            margin-bottom: 30px;
        }
        h1 {
            color: #2c3e50;
            font-size: 28px;
            font-weight: 700;
            margin-bottom: 10px;
        }
        p {
            color: #34495e;
            font-size: 16px;
            margin-bottom: 0;
        }
        .btn-custom {
            display: inline-block;
            background: #0984e3;
            color: #fff;
            padding: 12px 20px;
            margin: 10px;
            border-radius: 6px;
            text-decoration: none;
            font-size: 16px;
            font-weight: 500;
            transition: background 0.3s;
        }
        .btn-custom:hover {
            background: #0652dd;
            text-decoration: none;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <h1>Quản lý việc làm sinh viên</h1>
        <p>Chào mừng bạn đến với hệ thống quản lý thông tin việc làm sinh viên.</p>
    </div>
    <div>
        <a href="nhapThongTin.jsp" class="btn-custom">Nhập thông tin sinh viên</a>
        <a href="timKiemCoBan.jsp" class="btn-custom">Tìm kiếm thông tin cơ bản</a>
        <a href="timKiemViecLam.jsp" class="btn-custom">Tìm kiếm tốt nghiệp & việc làm</a>
    </div>
</div>
</body>
</html>