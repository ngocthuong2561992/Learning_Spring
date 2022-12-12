package com.itext.pdf

import com.itext.pdf.service.Background
import com.itext.pdf.service.Footer
import com.itext.pdf.service.Header
import com.itext.pdf.service.PdfService
import com.itextpdf.kernel.events.PdfDocumentEvent
import com.itextpdf.kernel.geom.PageSize
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.AreaBreak
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.properties.AreaBreakType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

@Component
class PDFGenerator {
    @Autowired
    lateinit var pdfCommonUtils: PdfCommonUtils

    fun getPdfReport(): ByteArrayInputStream {
        val outputStream = ByteArrayOutputStream()
        val headerDto = PdfCommonDto()
        val pdfDoc = PdfDocument(PdfWriter(outputStream))
        PdfService.addMetaData(pdfDoc)
        pdfDoc.addEventHandler(PdfDocumentEvent.START_PAGE, Background())
        pdfDoc.addEventHandler(PdfDocumentEvent.START_PAGE, Header(pdfCommonUtils, headerDto))
        pdfDoc.addEventHandler(PdfDocumentEvent.END_PAGE, Footer(pdfCommonUtils, headerDto))
        val document = Document(pdfDoc, PageSize(PageSize.A4.width, PageSize.A4.height), false)
        try {
            document.setMargins(40f, 30f, 40f, 30f)
            document.add(Paragraph("\n\n\n"))
            for (i in 0..10) {
                PdfService.buildPartA(document, pdfCommonUtils)
//                PdfService.buildPartB(document, pdfCommonUtils)
//                PdfService.buildCaution(document, pdfCommonUtils)
//                document.add(AreaBreak(AreaBreakType.NEXT_PAGE))
            }
            Footer.addPagingToFooter(document)
        } catch (e: Exception) {
            throw e
        } finally {
            document.close()
            outputStream.close()
        }
        return ByteArrayInputStream(outputStream.toByteArray())
    }
}