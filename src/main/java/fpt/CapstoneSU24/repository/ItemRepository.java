package fpt.CapstoneSU24.repository;

import fpt.CapstoneSU24.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository

public interface ItemRepository extends JpaRepository<Item, Integer> {
    @Override
    Page<Item> findAll(Pageable pageable);
    @Query("SELECT o FROM Item o WHERE o.product.productId = :id")

    List<Item> findAllByProductId(@Param("id")  int id);

    Page<Item> findAllByCurrentOwnerContaining(String currentOwner, Pageable pageable);

    Page<Item> findByCreatedAtBetween(Long startDate, Long endDate, Pageable pageable);

    List<Item> findByCreatedAtBetween(Long startDate, Long endDate);

    @Query("select i from Item i where i.product.productId = :id ")
    List<Item> findByProductId(@Param("id")  Integer id);

    @Query("SELECT i FROM Item i WHERE i.productRecognition = :productRecognition")
    Item findByProductRecognition(@Param("productRecognition") String productRecognition);

    //DEFINED Status của bảng ITEM là 1 là đã có current_owner 0 là chưa có current_owner
    @Modifying
    @Transactional
    @Query("UPDATE Item i SET i.status = 0 , i.currentOwner = :currentOwner WHERE i.itemId = :itemId")
    void updateStatusAndCurrent(@Param("itemId") int itemId, @Param("currentOwner") String currentOwner);


//    @Modifying
//    @Transactional
//    @Query("UPDATE Item i SET i.status = 1 WHERE i.itemId = :itemId")
//    void updateStatus(@Param("itemId") int itemId);

    // Phương thức cập nhật status của item

    @Modifying
    @Transactional
    @Query("UPDATE Item i SET i.status = :status WHERE i.itemId = :itemId")
    void updateItemStatus(@Param("itemId") Long itemId, @Param("status") Integer status);


    @Query("SELECT i FROM Item i WHERE i.currentOwner = :currentOwner")
    List<Item> findByCurrentOwner(@Param("currentOwner") String currentOwner);
}


