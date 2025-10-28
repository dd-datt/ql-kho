package com.example.ql_kho.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "import_slip_details")
public class ImportSlipDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "import_slip_id", nullable = false)
    private ImportSlip importSlip;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "import_price", precision = 15, scale = 2, nullable = false)
    private BigDecimal importPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ImportSlip getImportSlip() {
        return importSlip;
    }

    public void setImportSlip(ImportSlip importSlip) {
        this.importSlip = importSlip;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(BigDecimal importPrice) {
        this.importPrice = importPrice;
    }
}
