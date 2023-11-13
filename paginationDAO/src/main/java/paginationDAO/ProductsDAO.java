package paginationDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductsDAO {
	private static final String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String jdbcUsername = "khmember";
	private static final String jdbcPassword = "kh1234";
	
	//1. ��� ��ǰ�� ������ ���� �޼���
	//������ ��ȣ�� ������ ũ�⸦ �޾ƿͼ� ����Ʈ�� �߰��ϴ� �޼ҵ�
	
	public List<Products> getAllProducts(int pageNumber, int pageSize){
    	List<Products> productList = new ArrayList<>();
        int start = PaginationUtil.paginationStart(pageNumber, pageSize);
        int end = PaginationUtil.paginationEnd(pageNumber, pageSize);
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			 Connection conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
	            String sql = "SELECT * FROM (SELECT p.*, ROWNUM AS rnum FROM (SELECT * FROM products ORDER BY product_id) p WHERE ROWNUM <= ?) WHERE rnum >= ?";
	           /*
			 * String sql = "SELCT * FROM products " //products ���̺���
						+ "ORDER BY product_id "  //product_id �������� ������ �� ��
						// ������ OFFSET FETCH NEXT�������� ������ ��
						+ "OFFSET ? " // OFFSET : �������⸦ ������ ���� ��ġ ��Ÿ��
						+ "ROWS FETCH NEXT? " //FETCH NEXT : ������ ������ ���� �� ����
						+ "ROWS ONLY";
			*/
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ps.setInt(1, end);
	         ps.setInt(2, start);

			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Products product = new Products();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setCategory(rs.getString("category"));
                product.setPrice(rs.getDouble("price"));
                product.setStockQuantity(rs.getInt("stock_quantity"));
                productList.add(product);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return productList;
	}

	
	//2. ��ü ��ǰ ���� ������ ���� �޼���
	public int getTotalProducts() {
		int totalProducts = 0;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			
			String sql= "SELECT COUNT(*) AS total FROM products";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				totalProducts = rs.getInt("total");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return totalProducts;
	}
}