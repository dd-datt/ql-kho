package com.example.ql_kho.controller;

import com.example.ql_kho.model.ExportSlip;
import com.example.ql_kho.service.ExportSlipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/export-slips")
public class ExportSlipController {
    @Autowired
    private ExportSlipService exportSlipService;

    @PostMapping
    public ExportSlip createExportSlip(@RequestBody ExportSlip slip) {
        return exportSlipService.createExportSlip(slip);
    }

    @GetMapping
    public List<ExportSlip> getAllExportSlips() {
        return exportSlipService.getAllExportSlips();
    }

    @GetMapping("/{id}")
    public ExportSlip getExportSlipById(@PathVariable Long id) {
        return exportSlipService.getExportSlipById(id).orElseThrow();
    }
}
