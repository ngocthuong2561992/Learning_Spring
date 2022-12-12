package com.itext.pdf

import com.itextpdf.html2pdf.ConverterProperties
import com.itextpdf.html2pdf.HtmlConverter
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider
import com.itextpdf.layout.font.FontProvider
import org.apache.commons.io.FileUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.core.env.Environment
import org.springframework.core.env.get
import org.springframework.stereotype.Component
import org.springframework.util.ResourceUtils
import java.io.File
import java.util.*

@Component
class PdfCommonUtils {
    @Autowired
    @Qualifier("customConverterProperties")
    lateinit var converter: ConverterProperties

    fun <T> getElementFromHtml(html: String?): T {
        val list = HtmlConverter.convertToElements(html, converter)
        return list[0] as T
    }

    fun getFileBase64FromUrl(url: String): String {
        val inputFile = ResourceUtils.getFile("classpath:$url")

        val fileContent: ByteArray = FileUtils.readFileToByteArray(inputFile)
        return Base64
            .getEncoder()
            .encodeToString(fileContent)
    }
}