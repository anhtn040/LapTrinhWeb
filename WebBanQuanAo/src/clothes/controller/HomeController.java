package clothes.controller;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import clothes.dao.AccountDAO;
import clothes.dao.CategoryDAO;
import clothes.dao.ProductDAO;
import clothes.entity.Account;
import clothes.entity.Category;
import clothes.entity.Product;
import clothes.utils.Constants;

@Controller
public class HomeController { 
	
	@Autowired 
	private ProductDAO productDAO;
	
	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private AccountDAO accountDAO;

	@RequestMapping(value = "home")
	public String index(ModelMap model, HttpSession session) {     
		 Account user = (Account) session.getAttribute("account");
		 int userId = user == null ? -1 : user.getAccountId();
		 
		 List<Product> productMen = productDAO.listProductsInCategory(1); 
		 model.addAttribute("men", productMen); 
		
		 List<Product> productWomen = productDAO.listProductsInCategory(2); 
		 model.addAttribute("women", productWomen);
			
		 List<Category> list = categoryDAO.listCategories();
		 model.addAttribute("category", list);
		 
		 List<Product> productAccessories = productDAO.listProductsInCategory(3); 
		 model.addAttribute("accessories", productAccessories);
		
		 List<Product> hangMoi = productDAO.listProducts("", -1, Constants.FILTER_BY_NEWEST,userId == 1); 
		 List<Product> giamGia = productDAO.listProductsGiamGia();
		 model.addAttribute("hangMoi", hangMoi.subList(0, Math.min(10,hangMoi.size()))); 
		 model.addAttribute("giamGia",giamGia.subList(0, Math.min(7, giamGia.size())));	 
		 return "index";
		}

	@RequestMapping("login")
	public String login(HttpSession session) {
		Account account = accountDAO.getAccount(1);
		session.setAttribute("account", account);
		return "redirect:/home.htm";
	}

	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("account");
		return "redirect:/home.htm";
	}
}
