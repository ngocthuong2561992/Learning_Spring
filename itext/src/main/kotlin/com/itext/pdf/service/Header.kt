package com.itext.pdf.service

import com.itext.pdf.PdfCommonDto
import com.itext.pdf.PdfCommonUtils
import com.itextpdf.kernel.events.Event
import com.itextpdf.kernel.events.IEventHandler
import com.itextpdf.kernel.events.PdfDocumentEvent
import com.itextpdf.kernel.geom.Rectangle
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfPage
import com.itextpdf.kernel.pdf.canvas.PdfCanvas
import com.itextpdf.layout.Canvas
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.properties.TextAlignment

class Header(private val pdfCommonUtils: PdfCommonUtils, dto: PdfCommonDto) : IEventHandler {
    override fun handleEvent(event: Event) {
        val docEvent: PdfDocumentEvent = event as PdfDocumentEvent
        val pdf: PdfDocument = docEvent.getDocument()
        val page: PdfPage = docEvent.getPage()
        val pageSize = page.pageSize
        val pdfCanvas = PdfCanvas(page.lastContentStream, page.resources, pdf)
        val canvas = Canvas(pdfCanvas, pageSize)
        if(page.document.numberOfPages == 1) {
            buildBigHeader(canvas, pageSize)
        }else {
            buildSmallHeader(canvas, pageSize)
        }
        canvas.close()
    }

    private fun buildBigHeader(canvas: Canvas, pageSize: Rectangle) {
        val imgHtml = """
            <img style='width: 100px; height: auto;' src='images/logo.png'/>
		"""
        canvas.showTextAligned(pdfCommonUtils.getElementFromHtml(imgHtml) as Paragraph,
            pageSize.left + 30, pageSize.top - 60, TextAlignment.LEFT
        )
    }

    private fun buildSmallHeader(canvas: Canvas, pageSize: Rectangle) {
//        val base64Image = pdfCommonUtils.getFileBase64FromUrl("static/images/logo.png")
//        src='data:image/png;base64,${base64Image}'
//        src='images/logo.png'
        val imgHtml = """
            <img style='width: 70px; height: auto;' src='images/logo.png'/>
		"""
        canvas.showTextAligned(pdfCommonUtils.getElementFromHtml(imgHtml) as Paragraph,
            pageSize.left + 30, pageSize.top - 40, TextAlignment.LEFT
        )
    }

}