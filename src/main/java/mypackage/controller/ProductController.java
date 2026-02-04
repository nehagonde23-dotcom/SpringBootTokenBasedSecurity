package mypackage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import mypackage.services.ProductService;
import mypackage.model.*;

@RestController
@CrossOrigin(origins = "*",exposedHeaders ="Authorization",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.OPTIONS},allowedHeaders = "*") 

public class ProductController {
	
	@Autowired
	ProductService pservice;
	
	@PostMapping("/api/product")
	public Product Addproduct( @RequestBody Product p) {
		pservice.addproduct(p);
		return p;
		
	}
	
	@GetMapping("/api/getproducts")
	public List<Product> getall(){
		 return pservice.getallproduct();
	}
	
	

}
