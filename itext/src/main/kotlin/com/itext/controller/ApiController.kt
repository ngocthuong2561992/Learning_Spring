package com.itext.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.itext.pdf.PDFGenerator
import com.itext.service.ExportService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.core.io.InputStreamResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream
import java.lang.Exception

@RestControllerAdvice
@RequestMapping(value = ["/api"])
class ApiController {
    @Autowired
    lateinit var exportService: ExportService

    @Autowired
    lateinit var pDFGenerator: PDFGenerator

    @Autowired
    @Qualifier("customObjectMapper")
    lateinit var mapper: ObjectMapper

    private val logger = LoggerFactory.getLogger(ApiController::class.java)

    @GetMapping(value = ["/log"])
    fun log(@RequestParam("status") status: String) {
        if(status == "success") {
            logger.info("success")
        }else {
            val a = 1/0
        }
    }

    @GetMapping(value = ["/exportExcel"])
    @Throws(Exception::class)
    fun exportExcel(@RequestParam("file") file: MultipartFile): ResponseEntity<Resource?>? {
        val fileExport = InputStreamResource(exportService.exportExcel(file))
        logger.info("exportExcel")
        return ResponseEntity
            .ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=tutorials.xlsx")
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            //.contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
            .body(fileExport)
    }

    @GetMapping(value = ["/exportPdf"], produces = [MediaType.APPLICATION_PDF_VALUE])
    fun exportPdf(): ResponseEntity<InputStreamResource?>? {
        logger.info("aaaa")
        val bis: ByteArrayInputStream = pDFGenerator.getPdfReport()
        val headers = HttpHeaders()
        headers.add("Content-Disposition", "inline; filename=customers.pdf")
        return ResponseEntity
            .ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_PDF)
            .body(InputStreamResource(bis))
    }
}