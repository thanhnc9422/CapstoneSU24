package fpt.CapstoneSU24.repository;

import fpt.CapstoneSU24.dto.ItemLogDTO;
import fpt.CapstoneSU24.model.Item;
import fpt.CapstoneSU24.model.ItemLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ItemLogRepository extends JpaRepository<ItemLog, Integer> {
    @Override
    List<ItemLog> findAll();
    @Query("SELECT il " +
            "FROM ItemLog il " +
            "LEFT JOIN Party p ON p.partyId = il.party.partyId " +
            "LEFT JOIN Location l ON il.location.locationId = l.locationId " +
            "WHERE il.itemLogId = :itemLogId")
    ItemLog getItemLogsById(@Param("itemLogId") Integer itemLogId);



    @Query("SELECT il " +
            "FROM ItemLog il " +
            "LEFT JOIN Item i ON il.item.itemId = i.itemId " +
            "LEFT JOIN Origin o ON i.origin.originId = o.originId " +
            "LEFT JOIN Product p ON i.product.productId = p.productId " +
            "LEFT JOIN ImageProduct ipr ON p.productId = ipr.product.productId " +
            "LEFT JOIN Category c ON p.category.categoryId = c.categoryId " +
            "LEFT JOIN Location l ON o.location.locationId = l.locationId " +
            "WHERE il.itemLogId = :itemLogId")
    ItemLog getItemLogs(@Param("itemLogId") int itemLogId);

    @Query("SELECT il FROM ItemLog il LEFT JOIN  il.location loc LEFT JOIN il.item i WHERE i.itemId = :itemId ORDER BY il.itemLogId")
    List<ItemLog> getItemLogsByItemId(@Param("itemId") Integer itemId);

    @Query("SELECT il FROM ItemLog il LEFT JOIN il.item i WHERE i.itemId = :itemId AND il.point IS NOT NULL")
    List<ItemLog> getPointItemId(@Param("itemId") Integer itemId);



    @Modifying
    @Transactional
    @Query("UPDATE ItemLog i SET i.authorized.authorized_id = :authorizedId  WHERE i.itemLogId = :itemLogId")
    void updateAuthorized(@Param("authorizedId") int authorizedId, @Param("itemLogId") int itemLogId);

    @Modifying
    @Transactional
    @Query("UPDATE ItemLog i SET i.status = :status  WHERE i.itemLogId = :itemLogId")
    void updateStatus(@Param("status") int status, @Param("itemLogId") int itemLogId);



    //   List<Item> findAllById(int itemId);
//    @Query("SELECT o FROM Origin o WHERE o.Product.productId = :id")
//    List<Origin> findAllByProductId(@Param("id")  int id);

}