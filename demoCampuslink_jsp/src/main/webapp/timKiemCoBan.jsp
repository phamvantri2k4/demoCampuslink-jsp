<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Tìm kiếm sinh viên</title>
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
        }
        h2 {
            color: #2c3e50;
            text-align: center;
            font-size: 24px;
            margin-bottom: 20px;
        }
        form {
            display: flex;
            justify-content: center;
            margin-bottom: 20px;
        }
        input[type="text"] {
            padding: 10px;
            width: 200px;
            border: 2px solid #dfe6e9;
            border-radius: 6px;
            font-size: 14px;
            background: #f9f9f9;
        }
        input[type="text"]:focus {
            border-color: #0984e3;
            outline: none;
        }
        input[type="submit"] {
            background: #0984e3;
            color: #fff;
            border: none;
            padding: 10px 15px;
            border-radius: 6px;
            margin-left: 10px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background: #0652dd;
        }
        h3 {
            color: #2c3e50;
            font-size: 18px;
            text-align: center;
            margin-bottom: 15px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            background: #fff;
        }
        th, td {
            padding: 12px;
            border: 1px solid #dfe6e9;
            text-align: left;
            font-size: 14px;
        }
        th {
            background: #0984e3;
            color: #fff;
        }
        tr:nth-child(even) {
            background: #f9f9f9;
        }
        tr:hover {
            background: #e6f3ff;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Tìm kiếm sinh viên</h2>
    <form action="TimKiemCoBanServlet" method="get">
        <input type="text" name="soCMND" placeholder="Số CMND">
        <input type="submit" value="Tìm">
    </form>
    <c:if test="${not empty sinhViens}">
        <h3>Kết quả</h3>
        <table>
            <tr>
                <th>Số CMND</th>
                <th>Họ Tên</th>
                <th>Email</th>
                <th>Số ĐT</th>
                <th>Địa chỉ</th>
            </tr>
            <c:forEach var="sv" items="${sinhViens}">
                <tr>
                    <td>${sv.soCMND}</td>
                    <td>${sv.hoTen}</td>
                    <td>${sv.email}</td>
                    <td>${sv.soDT}</td>
                    <td>${sv.diaChi}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
</body>
</html>