package com.itext.service.impl

import com.itext.dto.EmployeeInfo
import com.itext.service.ExportService
import org.apache.poi.hssf.usermodel.HSSFDataFormat
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFCellStyle
import org.apache.poi.xssf.usermodel.XSSFFont
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.*

@Service
class ExportServiceImpl : ExportService {
    override fun exportExcel(file: MultipartFile): ByteArrayInputStream {
        val workbook = XSSFWorkbook()
        val outputStream = ByteArrayOutputStream()
        val sheet = workbook.createSheet("Proposal")

        val data: List<EmployeeInfo> = ArrayList<EmployeeInfo>()

    //  header
        val colNums = createHeader(sheet, workbook, 2)

//      data
        pushExcelData(sheet, workbook, data, 3, colNums)
        workbook.write(outputStream)
        return ByteArrayInputStream(outputStream.toByteArray())
    }

    private fun createHeader(sheet: Sheet, workbook: Workbook, currentRow: Int): Int {
        val font = workbook.createFont() as XSSFFont
        font.bold = true
        font.color = IndexedColors.BLACK.getIndex()
        val style = workbook.createCellStyle() as XSSFCellStyle
        style.setFont(font)
        style.alignment = HorizontalAlignment.CENTER
        style.verticalAlignment = VerticalAlignment.CENTER
        style.borderLeft = BorderStyle.THIN
        style.borderRight = BorderStyle.THIN
        style.borderBottom = BorderStyle.THIN
        style.borderTop = BorderStyle.THIN
        style.wrapText = true
        style.fillBackgroundColor = IndexedColors.YELLOW.index
        style.fillForegroundColor = IndexedColors.YELLOW.index
        style.fillPattern = FillPatternType.SOLID_FOREGROUND
        val cols = arrayOf(
            "Số hợp đồng",
            "Số hồ sơ",
            "Ngày bắt đầu"
        )
        val widths = intArrayOf(20, 20, 20)
        for (i in widths.indices) {
            sheet.setColumnWidth(i, widths[i] * 256)
        }
        val row = sheet.createRow(currentRow)
        row.height = 500.toShort()
        var cell: Cell
        for (i in cols.indices) {
            cell = row.createCell(i)
            cell.setCellValue(cols[i])
            cell.cellStyle = style
        }
        return widths.size
    }

    private fun pushExcelData(
        sheet: Sheet,
        workbook: Workbook,
        data: List<EmployeeInfo>,
        currentRow: Int,
        colNums: Int
    ) {
        val styleNormal = getCellStyle(workbook, ExcelFormat.MORMAL)
        var row: Row
        var cell: Cell
        for (rowIndex in data.indices) {
            row = sheet.createRow(currentRow + rowIndex)
            for (colIndex in 0 until colNums) {
                cell = row.createCell(colIndex)
                setCellValue(cell, styleNormal, colIndex, data[rowIndex])
            }
        }
    }

    private fun setCellValue(cell: Cell, style: XSSFCellStyle, colIndex: Int, info: EmployeeInfo) {
        cell.cellStyle = style
        when (colIndex) {
            0 -> cell.setCellValue(info.getEmpNo())
            1 -> cell.setCellValue(info.getFullName())
            2 -> cell.setCellValue(info.getSalary().toDouble())
        }
    }

    private fun getCellStyle(workbook: Workbook, format: Enum<*>): XSSFCellStyle {
        val font = workbook.createFont() as XSSFFont
        font.color = IndexedColors.BLACK.getIndex()
        val style = workbook.createCellStyle() as XSSFCellStyle
        style.setFont(font)
        style.alignment = HorizontalAlignment.CENTER
        style.verticalAlignment = VerticalAlignment.CENTER
        style.borderLeft = BorderStyle.THIN
        style.borderRight = BorderStyle.THIN
        style.borderBottom = BorderStyle.THIN
        style.borderTop = BorderStyle.THIN
        style.wrapText = true
        if (format === ExcelFormat.PERCENT) {
            style.dataFormat = HSSFDataFormat.getBuiltinFormat("0%") //0.00%
        }
        if (format === ExcelFormat.MONEY) {
            style.dataFormat = HSSFDataFormat.getBuiltinFormat("#,##0") // \"$\"#,##0_);(\"$\"#,##0)
        }
        return style
    }

    internal enum class ExcelFormat {
        PERCENT, MONEY, MORMAL
    }
}