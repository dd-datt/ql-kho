package com.example.ql_kho.controller;

import com.example.ql_kho.model.ImportSlip;
import com.example.ql_kho.service.ImportSlipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/import-slips")
public class ImportSlipController {
    @Autowired
    private ImportSlipService importSlipService;

    @PostMapping
    public ImportSlip createImportSlip(@RequestBody ImportSlip slip) {
        return importSlipService.createImportSlip(slip);
    }

    @GetMapping
    public List<ImportSlip> getAllImportSlips() {
        return importSlipService.getAllImportSlips();
    }

    @GetMapping("/{id}")
    public ImportSlip getImportSlipById(@PathVariable Long id) {
        return importSlipService.getImportSlipById(id).orElseThrow();
    }
}
