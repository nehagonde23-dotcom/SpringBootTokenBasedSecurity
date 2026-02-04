package mypackage.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import mypackage.repository.IProductRepository;
import mypackage.model.*;
@Service
public class ProductService {
	
	@Autowired
	IProductRepository prepo;
	
	public Product addproduct(Product p) {
		return prepo.save(p);
	}
	
	
	public List<Product> getallproduct(){
		return prepo.findAll();
	}

}
