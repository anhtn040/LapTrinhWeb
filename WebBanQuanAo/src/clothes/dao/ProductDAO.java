package clothes.dao;

import java.util.List;

import clothes.entity.Product;

public interface ProductDAO {

	List<Product> listProducts();
	
	List<Product> listProducts(String keyword, int category, int filter, boolean getAll);
	
	List<Product> listProductsInCategory(int category);
	
	Product getProductId(int id);
	
	Product getDiscount(String voucher);
	
	boolean insert(Product productId);
	
	boolean update(Product productId);
	
	boolean delete(Product productId);
	 List<Product> listProductsGiamGia();

	List<Product> filterByKeyword(List<Product> productall, String keyword);
}
