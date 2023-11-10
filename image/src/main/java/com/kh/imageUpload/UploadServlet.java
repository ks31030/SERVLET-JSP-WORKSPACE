package com.kh.imageUpload;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/UploadServlet")
@MultipartConfig
/*
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024,
		//���� �����͸� ��ũ�� ����� �����ϱ� ���� �޸𸮿� �����Ǵ� �ִ� ũ��(1MB)
		maxFileSize = 1024 * 1024 * 5,//���ε��� ������ �ִ� ũ��(5MB)
		maxRequestSize = 1024 * 1024 * 10,//��û �������� �ִ� ũ��(10MB)
		location = "/tmp"//������ ��ũ�� ����� �ӽ� ���͸�
		)
*/
//������ �ø��� ���� ���ϰ��� �����ϴ� ����.
public class UploadServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
		String jdbcUser = "KHCAFE";
		String jdbcPW = "KH1234";
		
		/*
		����ڰ� ��û�� �� �����͸� ó���ϴ� �� ����ϴ� ����.
		jsp title �Ķ���͸� ������ �ͼ� title ���ڿ� ������ ����.
		������ �Էµ� ������ ��Ÿ��.
		String title = request.getParameteru("title");
		*/
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Part imagePart = request.getPart("image");
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			Connection conn = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPW);
			String sql = "INSERT INTO Board(board_id, title, content, image, created_at, author)" + "VALUES(board_sequence.NEXTVAL, ?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, title);
			ps.setString(2, content);
			ps.setBinaryStream(3, imagePart.getInputStream(),(int) imagePart.getSize());
			ps.setTimestamp(4, new Timestamp(new Date().getTime()));
			ps.setString(5, "author name");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("imageList.jsp");
		
	}

}
