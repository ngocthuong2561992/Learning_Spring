package com.itext.service

import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream

interface ExportService {
    @Throws(Exception::class)
    fun exportExcel(file: MultipartFile) : ByteArrayInputStream
}
