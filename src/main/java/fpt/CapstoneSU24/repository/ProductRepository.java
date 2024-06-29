package fpt.CapstoneSU24.repository;

import fpt.CapstoneSU24.model.Item;
import fpt.CapstoneSU24.model.Origin;
import fpt.CapstoneSU24.model.Product;
import fpt.CapstoneSU24.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer>  {
    public Product findOneByProductId(int id);
    @Query("SELECT o FROM Product o WHERE o.manufacturer.userId = :id")
    List<Product> findAllByManufacturerId(@Param("id")  int id);
    Page<Product> findAllByProductNameContaining(String productName, Pageable pageable);
    Page<Product> findByManufacturerAndCreateAtBetween(User manufacturer, Long startDate, Long endDate, Pageable pageable);
    Page<Product> findByManufacturerAndProductNameContaining(User manufacturer, String productName, Pageable pageable);
    @Modifying
    @Transactional
    void deleteOneByProductId(int productId);
}
