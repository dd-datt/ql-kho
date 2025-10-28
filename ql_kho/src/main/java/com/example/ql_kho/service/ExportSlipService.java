package com.example.ql_kho.service;

import com.example.ql_kho.model.ExportSlip;
import com.example.ql_kho.model.ExportSlipDetail;
import com.example.ql_kho.model.Product;
import com.example.ql_kho.repository.ExportSlipRepository;
import com.example.ql_kho.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ExportSlipService {
    @Autowired
    private ExportSlipRepository exportSlipRepository;
    @Autowired
    private ProductRepository productRepository;

    // Tạo phiếu xuất, đồng thời giảm tồn kho theo từng detail; atomic
    @Transactional
    public ExportSlip createExportSlip(ExportSlip slip) {
        if (slip.getDetails() == null || slip.getDetails().isEmpty()) {
            throw new RuntimeException("Phiếu xuất phải có ít nhất một chi tiết");
        }

        // Validate and adjust stock for each detail
        for (ExportSlipDetail detail : slip.getDetails()) {
            Long productId = detail.getProduct() == null ? null : detail.getProduct().getId();
            if (productId == null) {
                throw new RuntimeException("Chi tiết phiếu xuất thiếu thông tin sản phẩm");
            }
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm id=" + productId));
            if (product.getCurrentStock() < detail.getQuantity()) {
                throw new RuntimeException("Không đủ tồn kho cho sản phẩm id=" + productId);
            }
            product.setCurrentStock(product.getCurrentStock() - detail.getQuantity());
            productRepository.save(product);

            // ensure back-reference so cascade persists correctly
            detail.setExportSlip(slip);
        }

        return exportSlipRepository.save(slip);
    }

    public List<ExportSlip> getAllExportSlips() {
        return exportSlipRepository.findAll();
    }

    public Optional<ExportSlip> getExportSlipById(Long id) {
        return exportSlipRepository.findById(id);
    }
}
