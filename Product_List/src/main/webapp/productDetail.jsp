<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>��ǰ �� ������</title>
</head>
<body>
<h1>��ǰ �� ����</h1>
<%
	//String = id ���� ������ ���ڴ�.
	String productIdValue = request.getParameter("productId");
	int productId = Integer.parseInt(productIdValue);
	//DAO �۾�
	Product product = productDAO.get(productId);
%>
<p>��ǰID : <% product.getProductId() %></p>
</body>
</html>