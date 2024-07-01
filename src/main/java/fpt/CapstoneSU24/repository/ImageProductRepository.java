package fpt.CapstoneSU24.repository;

import fpt.CapstoneSU24.model.ImageProduct;
import fpt.CapstoneSU24.model.Item;
import fpt.CapstoneSU24.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageProductRepository extends JpaRepository<ImageProduct, Integer> {

    @Query("SELECT i FROM ImageProduct i WHERE i.product.productId = :productId")
    ImageProduct findByproductId(@Param("productId") int productId);
    @Query("SELECT o FROM ImageProduct o WHERE o.product.productId = :id")
    List<ImageProduct> findAllByProductId(@Param("id")  int id);
    ImageProduct findAllByFilePath(String filePath);
    @Modifying
    @Transactional
    @Query("DELETE FROM ImageProduct i WHERE i.product.productId = :id AND i.filePath LIKE 'avatar%'")
    void deleteImageProductWithFilePathStartingWithAvatar(@Param("id") int id);
    @Modifying
    @Transactional
    @Query("DELETE FROM ImageProduct i WHERE i.product.productId = :id AND i.filePath NOT LIKE 'avatar%'")
    void deleteImageProductWithFilePathNotStartingWithAvatar(@Param("id") int id);
    @Modifying
    @Transactional
    @Query("DELETE FROM ImageProduct i WHERE i.product.productId = :productId")
    void deleteByProductId(@Param("productId") int productId);
}