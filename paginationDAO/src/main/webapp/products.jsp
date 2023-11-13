<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import="java.util.List" %>
<%@ page import="paginationDAO.ProductsDAO" %>
<%@ page import="paginationDAO.Products" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>��ǰ���</title>
</head>
<body>

	<%
		int pageNumber = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
    	int pageSize = 2;
    	//int pageNumber = Integer.parseInt(request.getParameter("page"));
		
		
		ProductsDAO productsDAO = new ProductsDAO();
        List<Products> productList = productsDAO.getAllProducts(pageNumber, pageSize);
	
	%>
	<table border = "1">
		<tr>
			<th>��ǰ��</th>
			<th>��ǰ�̸�</th>
			
		</tr>
	<%
		for(Products p : pl){
	%>
	<tr>
		<td> <%= p.getProductId() %></td>
		<td> <%= p.getProductName() %></td>
	</tr>
	
	</table>
	<%
		//1. ���������̼� ��ũ�� �������ٰ��̰�, ��ũ�� page ���� ���� �ٸ��� ���� ��
        int totalProducts = productsDAO.getTotalProducts(); //��ü ��ǰ ��������
        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

        for (int i = 1; i <= totalPages; i++) {
    %>
            <a href="<%= request.getRequestURI() %>?page=<%= i %>"><%= i %></a>
    <%
        }
    %>
</body>
</html>