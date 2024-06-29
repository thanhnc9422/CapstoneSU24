package fpt.CapstoneSU24.repository;

import fpt.CapstoneSU24.model.Origin;
import fpt.CapstoneSU24.model.Product;
import fpt.CapstoneSU24.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
public interface UserRepository extends JpaRepository<User, Integer> {
    @Override
    List<User> findAll();
    public User findOneByUserId(int id);
    public User findOneByEmail(String email);
    public User findOneByEmailAndPassword(String email, String password);
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.status = :status")
    List<User> findAllByStatus(@Param("status")  int status);

    @Query("SELECT u FROM User u " +
            "WHERE (:email IS NULL OR :email = '' OR u.email = :email) " +
            "AND (:roleId IS NULL OR u.role.roleId = :roleId) " +
            "AND (:status IS NULL OR u.status = :status) " +
            "AND (:dateFrom IS NULL OR u.createAt >= :dateFrom) " +
            "AND (:dateTo IS NULL OR u.createAt <= :dateTo)")
    Page<User> findByFilters(@Param("email") String email,
                             @Param("roleId") Integer roleId,
                             @Param("status") Integer status,
                             @Param("dateFrom") Long dateFrom,
                             @Param("dateTo") Long dateTo,
                             Pageable pageable);

    /*@Query("SELECT u," +
// "COALESCE(c.certificate_id, 0) AS certificateId" +
            " FROM User u" +
// "LEFT JOIN Certificate c ON u.userId = c.manufacturer.userId" +
            "WHERE (:email IS NULL OR :email = '' OR u.email = :email) " +
            "AND (:roleId IS NULL OR u.role.id = :roleId) " +
            "AND (:status IS NULL OR u.status = :status) " +
            "AND (:dateFrom IS NULL OR u.createAt >= :dateFrom) " +
            "AND (:dateTo IS NULL OR u.createAt <= :dateTo)")
    Page<User> findByFilters(@Param("email") String email,
                             @Param("roleId") Integer roleId,
                             @Param("status") Integer status,
                             @Param("dateFrom") Long dateFrom,
                             @Param("dateTo") Long dateTo,
                             Pageable pageable);*/
}

