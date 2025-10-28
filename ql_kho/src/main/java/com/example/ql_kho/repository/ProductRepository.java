package com.example.ql_kho.repository;

import com.example.ql_kho.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // JPAQuery (JPQL): Tìm kiếm sản phẩm theo tên hoặc mã SKU
    @Query("SELECT p FROM Product p WHERE (:name IS NULL OR p.name LIKE %:name%) AND (:sku IS NULL OR p.sku = :sku)")
    List<Product> searchByNameOrSku(@Param("name") String name, @Param("sku") String sku);

    // NativeQuery: Lọc sản phẩm theo tồn kho lớn hơn hoặc bằng một giá trị
    @Query(value = "SELECT * FROM products WHERE current_stock >= :minStock", nativeQuery = true)
    List<Product> filterByMinStock(@Param("minStock") int minStock);
}
