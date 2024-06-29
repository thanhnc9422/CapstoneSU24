package fpt.CapstoneSU24.controller;

import fpt.CapstoneSU24.dto.B02.B02_GetListReport;
import fpt.CapstoneSU24.dto.B02.B02_RequestFilterTable;
import fpt.CapstoneSU24.dto.ReportDetailDto;
import fpt.CapstoneSU24.repository.ReportRepository;
import fpt.CapstoneSU24.service.ReportService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;

@RestController
@RequestMapping("/api/report")
public class ReportController {
    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/getListReports")
    public ResponseEntity<Page<B02_GetListReport>> getListReports(@RequestBody B02_RequestFilterTable requestFilter) {
        return reportService.getListReports(requestFilter);
    }


    @PostMapping("/getReportById")
    public ResponseEntity<?> getReportById(@RequestBody String req)
    {
        return reportService.getReportById(req);

    }


}
