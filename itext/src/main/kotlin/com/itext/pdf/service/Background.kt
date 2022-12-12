package com.itext.pdf.service

import com.itextpdf.kernel.events.PdfDocumentEvent
import com.itextpdf.kernel.pdf.canvas.PdfCanvas
import com.itextpdf.kernel.colors.DeviceRgb
import com.itextpdf.kernel.events.Event
import com.itextpdf.kernel.events.IEventHandler

open class Background : IEventHandler {
    override fun handleEvent(event: Event) {
        val docEvent = event as PdfDocumentEvent
        val page = docEvent.page
        val canvas = PdfCanvas(page)
        val rect = page.pageSize
        canvas.saveState()
            .setFillColor(DeviceRgb(255, 251, 199))
            .rectangle(rect.left.toDouble(), rect.bottom.toDouble(), rect.width.toDouble(), rect.height.toDouble())
            .fillStroke()
            .restoreState()
    }
}