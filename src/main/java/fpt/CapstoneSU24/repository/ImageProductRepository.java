package fpt.CapstoneSU24.repository;

import fpt.CapstoneSU24.model.ImageProduct;
import fpt.CapstoneSU24.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageProductRepository extends JpaRepository<ImageProduct, Integer> {

    @Query("SELECT i FROM ImageProduct i WHERE i.product.productId = :productId")
    ImageProduct findByproductId(@Param("productId") int productId);
}