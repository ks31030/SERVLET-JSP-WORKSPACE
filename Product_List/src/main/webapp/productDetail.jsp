<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import ="com.kh.product.Product" %>
<%@ page import ="com.kh.product.ProductDAO" %>
<!DOCTYPE html>
<html>
<head>
    <title>��ǰ �� ����</title>
       <style>

        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
             text-align: center;
        }

        h1 {
            background-color: #333;
            color: #fff;
            padding: 10px;
            text-align: center;
        }

        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #fff;
        }

        table, th, td {
            border: 1px solid #ccc;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #e0e0e0;
        }
    </style>
</head>
<body>
    <h1>��ǰ �� ����</h1>

    <%
        String productIdParam = request.getParameter("productId");
        if (productIdParam != null) {
            int productId = Integer.parseInt(productIdParam);
            ProductDAO productDAO = new ProductDAO();
            Product product = productDAO.getProductId(productId);
            
    %>

    <p>��ǰ ID: <%= product.getProductId() %></p>
    <p>��ǰ��: <%= product.getProductName() %></p>
    <p>ī�װ�: <%= product.getCategory() %></p>
    <p>����: <%= product.getPrice() %></p>
    <p>��� ����: <%= product.getStockQuantity() %></p>

    <%
        } else {
    %>
    <p>��ǰ�� ã�� �� �����ϴ�..</p>
    <%
        }
    %>
</body>
</html>