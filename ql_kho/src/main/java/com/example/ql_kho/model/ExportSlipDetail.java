package com.example.ql_kho.model;

import jakarta.persistence.*;

@Entity
@Table(name = "export_slip_details")
public class ExportSlipDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "export_slip_id", nullable = false)
    private ExportSlip exportSlip;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private int quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ExportSlip getExportSlip() {
        return exportSlip;
    }

    public void setExportSlip(ExportSlip exportSlip) {
        this.exportSlip = exportSlip;
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
}
