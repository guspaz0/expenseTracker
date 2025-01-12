package com.henry.expenseTracker.controller.views;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="Report")
@RestController
@AllArgsConstructor
@RequestMapping("/report")
public class ReportController {

}
