package com.henry.expenseTracker.service.impl;

import com.henry.expenseTracker.Dto.response.CategoryResponseDto;
import com.henry.expenseTracker.Dto.response.ExpenseResponseDto;
import com.henry.expenseTracker.Dto.response.ExpirationResponseDto;
import com.henry.expenseTracker.Dto.response.SupplierResponseDto;
import com.henry.expenseTracker.service.abstract_service.ReportService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ExcelService implements ReportService {

    private final ExpenseService expenseService;

    @Override
    public byte[] readFile() {
        try {
            this.createReport();
            var path = Paths.get(REPORTS_PATH, FILE_NAME).toAbsolutePath();
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private void createReport() throws IOException {
        var workBook = new XSSFWorkbook();
        var sheet = workBook.createSheet(SHEET_NAME);
        var sheet2 = workBook.createSheet("Expirations");

        var header = sheet.createRow(0);
        var header2 = sheet2.createRow(0);

        var headerStyle = workBook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.VIOLET.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        var font = workBook.createFont();
        font.setFontName(FONT_TYPE);
        font.setColor(IndexedColors.WHITE.getIndex());
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);

        headerStyle.setFont(font);

        CellStyle styleDate = workBook.createCellStyle();

        CreationHelper createHelper = workBook.getCreationHelper();
        styleDate.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy"));

        CellStyle styleDecimal = workBook.createCellStyle();
        styleDecimal.setDataFormat(createHelper.createDataFormat().getFormat("#.00"));

        var headerCell = header.createCell(0);
        var headerCell2 = header2.createCell(0);
        headerCell2.setCellValue("Expiration");
        headerCell2.setCellStyle(headerStyle);

        headerCell2 = header2.createCell(1);
        headerCell2.setCellValue(COLUMN_EXPENSE_ID);
        headerCell2.setCellStyle(headerStyle);


        headerCell2 = header2.createCell(2);

        headerCell2.setCellValue(COLUMN_AMOUNT);
        headerCell2.setCellStyle(headerStyle);
        sheet2.autoSizeColumn(2);

        headerCell2 = header2.createCell(3);
        headerCell2.setCellValue(COLUMN_EMIT_DATE);
        headerCell2.setCellStyle(headerStyle);
        sheet2.setColumnWidth(3,3000);


        headerCell = header.createCell(1);
        headerCell.setCellValue(COLUMN_CUSTOMER_ID);
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(2);
        headerCell.setCellValue(COLUMN_EXPENSE_ID);
        headerCell.setCellStyle(headerStyle);

        sheet.setColumnWidth(3,8000);
        headerCell = header.createCell(3);
        headerCell.setCellValue(COLUMN_EXPENSE_DESCRIPTION);
        headerCell.setCellStyle(headerStyle);

        sheet.setColumnWidth(4,3000);
        headerCell = header.createCell(4);
        headerCell.setCellValue(COLUMN_EMIT_DATE);
        headerCell.setCellStyle(headerStyle);

        sheet.setColumnWidth(5,3000);
        headerCell = header.createCell(5);
        headerCell.setCellValue(COLUMN_AMOUNT);
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(6);
        headerCell.setCellValue(COLUMN_CATEGORY_ID);
        headerCell.setCellStyle(headerStyle);

        sheet.setColumnWidth(7,5000);
        headerCell = header.createCell(7);
        headerCell.setCellValue(COLUMN_CATEGORY_NAME);
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(8);
        headerCell.setCellValue(COLUMN_SUPPLIER_ID);
        headerCell.setCellStyle(headerStyle);

        sheet.setColumnWidth(9,5000);
        headerCell = header.createCell(9);
        headerCell.setCellValue(COLUMN_SUPPLIER_NAME);
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(10);
        headerCell.setCellValue(COLUMN_EXPIRES);
        headerCell.setCellStyle(headerStyle);

        var style = workBook.createCellStyle();
        style.setWrapText(true);

        var expenses = this.expenseService.findAll();

        var rowPos = 1;
        var rowPos2 = 1;
        for (ExpenseResponseDto expense:expenses) {
            var row = sheet.createRow(rowPos);
            var cell = row.createCell(1);
            cell.setCellValue(expense.getUserId());
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellValue(expense.getId());
            cell.setCellStyle(style);

            cell = row.createCell(3);
            cell.setCellValue(expense.getDescription());
            cell.setCellStyle(style);
            sheet.autoSizeColumn(3);

            cell = row.createCell(4);
            cell.setCellValue(expense.getEmitDate());
            cell.setCellStyle(styleDate);

            cell = row.createCell(5);
            cell.setCellValue(expense.getAmount());
            cell.setCellStyle(styleDecimal);
            sheet.autoSizeColumn(5);

            cell = row.createCell(6);

            CategoryResponseDto category = expense.getCategory();

            cell.setCellValue(category.getId());
            cell.setCellStyle(style);

            cell = row.createCell(7);
            cell.setCellValue(category.getName());
            cell.setCellStyle(style);

            SupplierResponseDto supplier = expense.getSupplier();

            cell = row.createCell(8);
            cell.setCellValue(supplier.getId());
            cell.setCellStyle(style);

            cell = row.createCell(9);
            cell.setCellValue(supplier.getName());
            cell.setCellStyle(style);

            boolean expires = expense.getExpires() == 1;

            cell = row.createCell(10);
            cell.setCellValue(expires? "Y" : "N");
            cell.setCellStyle(style);

            if (expires) {
                List<ExpirationResponseDto> expirations = expense.getExpirations();
                for (ExpirationResponseDto expiration:expirations) {
                    var row2 = sheet2.createRow(rowPos2);
                    var cell2 = row2.createCell(0);
                    cell2.setCellValue(expiration.getId());
                    cell2.setCellStyle(style);

                    cell2 = row2.createCell(1);
                    cell2.setCellValue(expiration.getExpenseId());
                    cell2.setCellStyle(style);

                    cell2 = row2.createCell(2);
                    cell2.setCellValue(expiration.getAmount());
                    cell2.setCellStyle(styleDecimal);

                    cell2 = row2.createCell(3);
                    cell2.setCellValue(expiration.getExpireDate());
                    cell2.setCellStyle(styleDate);

                    rowPos2++;
                }
            }

            rowPos++;
        }


        var report = new File(REPORTS_PATH_WITH_NAME);
        var path = report.getAbsolutePath();
        var fileLocation = path+FILE_TYPE;
        Path filePath = Paths.get(fileLocation);


        boolean isDeleted = Files.deleteIfExists(filePath);

        try (var outputStream = new FileOutputStream(fileLocation)){
            workBook.write(outputStream);
            workBook.close();
        } catch (IOException e){
            log.error("Error trying to save excel report file");
            throw  new IllegalStateException();
        }
    }


    private static final String SHEET_NAME = "Expenses";
    private static final String FONT_TYPE = "Arial";
    private static final String COLUMN_CUSTOMER_ID = "User";
    private static final String COLUMN_CUSTOMER_NAME = "User name";
    private static final String COLUMN_EXPENSE_ID = "Expense";
    private static final String COLUMN_EXPENSE_DESCRIPTION = "Expense description";
    private static final String COLUMN_EMIT_DATE = "Emit Date";
    private static final String COLUMN_AMOUNT = "Amount";
    private static final String COLUMN_CATEGORY_ID = "Category";
    private static final String COLUMN_CATEGORY_NAME = "Category name";
    private static final String COLUMN_SUPPLIER_ID = "Supplier";
    private static final String COLUMN_SUPPLIER_NAME = "Supplier name";
    private static final String COLUMN_EXPIRES = "Expires";
    private static final String REPORTS_PATH_WITH_NAME = "reports/Expenses";
    private static final String REPORTS_PATH = "reports";
    private static final String FILE_TYPE= ".xlsx";
    private static final String FILE_NAME= "Expenses.xlsx";
}
