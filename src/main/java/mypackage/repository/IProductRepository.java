package mypackage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import mypackage.model.*;
public interface IProductRepository extends JpaRepository<Product, Integer> {

}
