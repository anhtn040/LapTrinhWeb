package clothes.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import clothes.dao.CategoryDAO;
import clothes.dao.ProductDAO;
import clothes.entity.Account;
import clothes.entity.Category;
import clothes.entity.Product;
import clothes.utils.Constants;

@Controller
public class CategoryController {
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired 
	private ProductDAO productDAO;

	@RequestMapping(value="/category/index")
	public String index(ModelMap model, HttpSession session) {
		
		Account user = (Account) session.getAttribute("account");
		 int userId = user == null ? -1 : user.getAccountId();
		 
		List<Product> listProduct = productDAO.listProducts();
		model.addAttribute("listProduct", listProduct);
	 
		List<Category> list = categoryDAO.listCategories();
		model.addAttribute("category", list);
		
		 List<Product> productMen = productDAO.listProductsInCategory(1); 
		 model.addAttribute("men", productMen); 
		
		 List<Product> productWomen = productDAO.listProductsInCategory(2); 
		 model.addAttribute("women", productWomen);
		 
		 List<Product> productAccessories = productDAO.listProductsInCategory(3); 
		 model.addAttribute("accessories", productAccessories) ;
		
		 List<Product> hangMoi = productDAO.listProducts("", -1, Constants.FILTER_BY_NEWEST,userId == 1); 
		 List<Product> banChay = productDAO.listProducts("", -1, Constants.FILTER_BY_POPULAR, userId == 1); 
		 
		 model.addAttribute("hangMoi", hangMoi.subList(0, Math.min(10,hangMoi.size()))); 
		 model.addAttribute("banChay",banChay.subList(0, Math.min(7, banChay.size())));
		return "category/index";
	}
}
