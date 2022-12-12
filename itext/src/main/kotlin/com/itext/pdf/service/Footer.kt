package com.itext.pdf.service

import com.itext.pdf.PdfCommonDto
import com.itext.pdf.PdfCommonUtils
import com.itextpdf.kernel.colors.DeviceRgb
import com.itextpdf.kernel.events.Event
import com.itextpdf.kernel.events.IEventHandler
import com.itextpdf.kernel.events.PdfDocumentEvent
import com.itextpdf.kernel.geom.Rectangle
import com.itextpdf.kernel.pdf.PdfPage
import com.itextpdf.kernel.pdf.canvas.PdfCanvas
import com.itextpdf.layout.Canvas
import com.itextpdf.layout.Document
import com.itextpdf.layout.borders.SolidBorder
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.properties.BorderRadius
import com.itextpdf.layout.properties.TextAlignment
import com.itextpdf.layout.properties.VerticalAlignment

class Footer(private val pdfCommonUtils: PdfCommonUtils, dto: PdfCommonDto) : IEventHandler {
    override fun handleEvent(event: Event) {
        val docEvent: PdfDocumentEvent = event as PdfDocumentEvent
        val page: PdfPage = docEvent.getPage()
        val pageSize = page.pageSize
        val canvas = Canvas(PdfCanvas(page), pageSize)
        canvas.showTextAligned(
            "390004598 - 05/11/2020 14:03:41 GMT 07:00",
            pageSize.left + 30,
            pageSize.bottom + 13,
            TextAlignment.LEFT
        )
        val number = """
            <span style='border: 1px solid black; font: bold 20px verdana;
                padding: 0 3 0 3;'>10201141</span>
        """
        canvas.showTextAligned(
            pdfCommonUtils.getElementFromHtml(number) as Paragraph,
            pageSize.right - 66,
            pageSize.bottom + 13,
            TextAlignment.RIGHT
        )
        canvas.close()
    }

    companion object {
        fun addPagingToFooter(doc: Document) {
            val pageSize: Rectangle = doc.pdfDocument.defaultPageSize
            val totalPage = doc.pdfDocument.numberOfPages
            for (i in 1..totalPage) {
                val number = Paragraph("$i/$totalPage")
                number.setBorder(SolidBorder(0.toFloat()))
                number.setBorderRadius(BorderRadius(4.toFloat()))
                //            number.setWidth(10);
                number.setMaxWidth(32f)
                number.setPadding(2f)
                //            number.setPaddingLeft(5);
    //            number.setHorizontalAlignment(HorizontalAlignment.RIGHT);
                number.isKeepTogether = true
                number.setBackgroundColor(DeviceRgb(255, 0, 0))
                number.setFontColor(DeviceRgb.WHITE)
                doc.showTextAligned(
                    number,
                    pageSize.right - 46,
                    pageSize.bottom + 30,
                    i,
                    TextAlignment.CENTER,
                    VerticalAlignment.TOP,
                    0f
                )
            }
        }
    }
}