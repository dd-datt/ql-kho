package com.example.ql_kho.controller;

import com.example.ql_kho.model.Product;
import com.example.ql_kho.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    // Endpoint test JPAQuery: tìm kiếm theo tên hoặc mã SKU
    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam(required = false) String name,
            @RequestParam(required = false) String sku) {
        return productService.searchProducts(name, sku);
    }

    // Endpoint test NativeQuery: lọc theo tồn kho tối thiểu
    @GetMapping("/filter")
    public List<Product> filterProductsByMinStock(@RequestParam int minStock) {
        return productService.filterProductsByMinStock(minStock);
    }
}
