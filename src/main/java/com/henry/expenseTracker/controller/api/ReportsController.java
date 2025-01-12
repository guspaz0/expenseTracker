package com.henry.expenseTracker.controller.api;

import com.henry.expenseTracker.service.abstract_service.ReportService;
import com.henry.expenseTracker.service.impl.ExcelService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Source;
import java.time.LocalDateTime;

@Tag(name="Reports")
@RestController
@RequestMapping("/report")
@AllArgsConstructor
public class ReportsController {

    private final ExcelService reportService;

    private static final MediaType FORCE_DOWNLOAD = new MediaType("application","force-download");
    private static final String FORCE_DOWNLOAD_HEADER_VALUE = "attachment; filename=Expenses-%s.xlsx";

    @GetMapping
    public ResponseEntity<Resource> get(){
        var headers = new HttpHeaders();
        headers.setContentType(FORCE_DOWNLOAD);
        headers.set(HttpHeaders.CONTENT_DISPOSITION,
                String.format(FORCE_DOWNLOAD_HEADER_VALUE, LocalDateTime.now().toString().replace(":","-").split("\\.")[0]));
        var fileInBytes = this.reportService.readFile();
        ByteArrayResource response = new ByteArrayResource(fileInBytes);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(fileInBytes.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(response);
    }


}
