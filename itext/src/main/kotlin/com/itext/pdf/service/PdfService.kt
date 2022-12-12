package com.itext.pdf.service

import com.itext.pdf.PdfCommonUtils
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfDocumentInfo
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Div
import com.itextpdf.layout.element.Table

object PdfService {
    @JvmStatic
    fun addMetaData(pdfDoc: PdfDocument) {
        val documentInfo: PdfDocumentInfo = pdfDoc.documentInfo
        documentInfo.author = "Jack"
        documentInfo.addCreationDate()
    }

    @JvmStatic
    fun buildPartA(document: Document, pdfCommonUtils: PdfCommonUtils) {
        val tdCss = """
            border: 1px solid black;
            text-align: left;
            padding: 8px;
        """
        val tableCss = """
            font: 14px verdana;
            border-collapse: collapse;
            background: white;
            width: 100%;
        """
        val html = """
            <table style='${tableCss}'>
                <tr>
                    <td style='${tdCss}' colspan='4'>
                        <b style='margin-right: 10px; display: inline-block;'>A.</b>
                        <b>THÔNG TIN TƯ VẤN VIÊN</b>
                    </td>
                </tr>
                <tr>
                    <td style='${tdCss}' colspan='2'><b>Họ và tên Tư vấn viên:</b>
                        <div style='margin-top: 10px; font-weight: bold;'>PRUDENTIAL VIỆT NAM</div>
                    </td>
                    <td style='${tdCss}'><b>Mã số:</b>
                        <div style='margin-top: 10px; font-weight: bold;'>69101525</div>
                    </td>
                    <td style='${tdCss}'>
                        <b>Điện thoại liên lạc:</b>
                        <div style='margin-top: 10px; font-weight: bold;'>0999999999</div>
                    </td>
                </tr>
            </table>
        """
        val table = pdfCommonUtils.getElementFromHtml(html) as Table
        table.isKeepTogether = true
        document.add(table)

//        val imgHtml = """
//            <img style='width: 50px; height: auto;border: 1px solid black;' src='images/logo.png'/>
//        """
//        document.add(pdfCommonUtils.getElementFromHtml(imgHtml) as Paragraph)
    }

    @JvmStatic
    fun buildCaution(document: Document, pdfCommonUtils: PdfCommonUtils) {
        val css = """
            color: red;
            border: 1px solid black;
            background: white;
            margin: 10 0 0 0;
            font: italic 14px verdana;
            padding: 5px;
        """
        val html = """
            <div style='${css}'>
                <b>LƯU Ý:</b> Việc kê khai đầy đủ và trung thực các thông tin dưới đây sẽ quyết định tính hiệu lực của hợp đồng bảo hiểm và việc chi trả các quyền lợi bảo hiểm sau này của Quý khách.
            </div>
        """

        document.add(pdfCommonUtils.getElementFromHtml(html) as Div)
    }

    @JvmStatic
    fun buildPartB(document: Document, pdfCommonUtils: PdfCommonUtils) {
        val mainCss = """
            font: 14px verdana;
            background: white;
            width: 100%;
            line-height: 20px;
            margin-top: 10px;
        """
        val numberingCss = """
            width: 20px;
            display: inline-block;
            float: left;
        """
        val html = """
            <div style="$mainCss">
              <div style="padding: 8px; border: 1px solid black; border-bottom: 0px;">
                <b style="margin-right: 10px; display: inline-block;">B.</b>
                <b>THÔNG TIN NGƯỜI ĐƯỢC BẢO HIỂM (NĐBH) VÀ ĐỒNG THỜI LÀ BÊN MUA BẢO HIỂM</b>
              </div>
              <table style="width: 100%; border-collapse: collapse;">
                <tbody>
                  <tr>
                    <td colspan="2" style="border: 1px solid black; padding: 8px;">
                      <div style="$numberingCss">1.</div>
                      <div style="display: inline-block;">Họ và tên: <br>
                        <b>CHECK DESPATH ADDRESS</b>
                      </div>
                    </td>
                    <td style="border: 1px solid black; padding: 8px;">
                      <div style="$numberingCss">2.</div>
                      <div style="display: inline-block;">Giới tính: <br>
                        <b>Nam</b>
                      </div>
                    </td>
                    <td style="border: 1px solid black; padding: 8px;">
                      <div style="$numberingCss">3.</div>
                      <div style="display: inline-block;">Số CMND/CCCD/Hộ chiếu: <br>
                        <b>766655544345</b>
                      </div>
                    </td>
                  </tr>
                  <tr>
                    <td style="border: 1px solid black; padding: 8px;">
                      <div style="$numberingCss">4.</div>
                      <div style="display: inline-block;">Ngày sinh: <br>
                        <b>01/01/1970</b>
                      </div>
                    </td>
                    <td style="border: 1px solid black; padding: 8px;">
                      <div style="$numberingCss">5.</div>
                      <div style="display: inline-block;">Tình trạng hôn nhân: <br>
                        <b>Độc thân</b>
                      </div>
                    </td>
                    <td style="border: 1px solid black; padding: 8px;">
                      <div style="$numberingCss">6.</div>
                      <div style="display: inline-block;">Quốc gia nơi sinh: <br>
                        <b>VIỆT NAM</b>
                      </div>
                    </td>
                    <td style="border: 1px solid black; padding: 8px;">
                      <div style="$numberingCss">7.</div>
                      <div style="display: inline-block;">Quốc tịch: <br>
                        <b>VIỆT NAM</b>
                      </div>
                    </td>
                  </tr>
                </tbody>
              </table>
              <div style="padding: 8px; border: 1px solid black; border-top: 0px; font-size: 13px;">
                <div>
                  <span style="display: inline-block; width: 20px;">8.</span>Thông tin liên lạc
                </div>
                <table style="width: 100%; margin-left: 20px;">
                  <tbody>
                    <tr>
                      <td>Địa chỉ thường trú: <br>
                        <b>241 Xuân Thủy, P. DỊCH VỌNG HẬU, Q.CẦU GIẤY, HÀ NỘI, VIỆT NAM</b>
                        <br>Email: <b>truong.thanhbinh@prudential.com.vn</b>
                        <br>Di động: <b>0906390361</b>
                      </td>
                      <td>Địa chỉ thường trú: <br>
                        <b>241 Xuân Thủy, P. DỊCH VỌNG HẬU, Q.CẦU GIẤY, HÀ NỘI, VIỆT NAM</b>
                        <br>Email: <b>truong.thanhbinh@prudential.com.vn</b>
                        <br>Di động: <b>0906390361</b>
                      </td>
                    </tr>
                  </tbody>
                </table>
              </div>
              <div style="padding: 8px; border: 1px solid black; border-top: 0px;">
                <div style="$numberingCss">9.</div>
                <div style="display: inline-block;">Nghề nghiệp: <b>Ăn uống - đầu bếp trưởng</b>
                  <br>Nghề nghiệp thuộc nhóm ngành: <b>Khác</b>
                  <br>Hiện tại Quý khách có khai báo thuế tại Hoa kỳ không?: <b>Không</b>
                </div>
              </div>
              <div style="padding: 8px; border: 1px solid black; border-top: 0px;">
                <b>Thông tin sức khỏe người được bảo hiểm (dành cho Quyền lợi nâng cao): CHECK DESPATH ADDRESS</b>
              </div>
              <table style="width: 100%; border-collapse: collapse;">
                <tbody>
                  <tr>
                    <td style="width: 80%; border: 1px solid black ; border-top: 0px; padding: 8px;">
                      <b>CÂU HỎI DÀNH CHO SỐ TIỀN BẢO HIỂM NHỎ HƠN HOẶC BẰNG 500 TRIỆU ĐỒNG</b>
                    </td>
                    <td style="width: 20%; border: 1px solid black; padding: 8px; border-top: 0px; text-align: center;">
                      <b>Xác nhận</b>
                    </td>
                  </tr>
                  <tr>
                    <td style="width: 80%; border: 1px solid black; border-top: 0px; padding: 8 8 8 20;">Tôi xin xác nhận rằng, tôi: <ol style="list-style-type: disc; text-align: justify;">
                        <li>Hoàn toàn khỏe mạnh và không đang điều trị bất cứ bệnh gì và chưa từng nằm viện điều trị quá 2 tuần trong 2 năm vừa qua</li>
                        <li>Không sử dụng các chất kích thích hay ma túy (trừ khi có chỉ định của bác sĩ)</li>
                        <li>Không bị khuyết tật hoặc chấn thương hoặc các bệnh bẩm sinh</li>
                        <li>Chưa từng được chẩn đoán mắc các bệnh: Ung thư, đột quỵ, bệnh tim, bệnh về hệ thần kinh, bệnh về máu, bệnh tiểu đường, bệnh xơ gan, bệnh AIDS, suy thận, suy gan hoặc suy hô hấp.</li>
                      </ol>
                    </td>
                    <td style="width: 20%; border: 1px solid black; border-top: 0px; text-align: center;">
                      <b>Tôi Xác nhận</b>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
        """.trimIndent()
        document.add(pdfCommonUtils.getElementFromHtml(html) as Div)
    }

}