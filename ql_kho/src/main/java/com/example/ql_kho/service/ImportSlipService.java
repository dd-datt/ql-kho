package com.example.ql_kho.service;

import com.example.ql_kho.model.ImportSlip;
import com.example.ql_kho.model.ImportSlipDetail;
import com.example.ql_kho.model.Product;
import com.example.ql_kho.repository.ImportSlipRepository;
import com.example.ql_kho.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ImportSlipService {
    @Autowired
    private ImportSlipRepository importSlipRepository;
    @Autowired
    private ProductRepository productRepository;

    // Tạo phiếu nhập, đồng thời tăng tồn kho
    @Transactional
    public ImportSlip createImportSlip(ImportSlip slip) {
        if (slip.getDetails() == null || slip.getDetails().isEmpty()) {
            throw new RuntimeException("Phiếu nhập phải có ít nhất một chi tiết");
        }

        for (ImportSlipDetail detail : slip.getDetails()) {
            Long productId = detail.getProduct() == null ? null : detail.getProduct().getId();
            if (productId == null) {
                throw new RuntimeException("Chi tiết phiếu nhập thiếu thông tin sản phẩm");
            }
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm id=" + productId));
            product.setCurrentStock(product.getCurrentStock() + detail.getQuantity());
            productRepository.save(product);

            // ensure back-reference for cascade
            detail.setImportSlip(slip);
        }

        return importSlipRepository.save(slip);
    }

    public List<ImportSlip> getAllImportSlips() {
        return importSlipRepository.findAll();
    }

    public Optional<ImportSlip> getImportSlipById(Long id) {
        return importSlipRepository.findById(id);
    }
}
