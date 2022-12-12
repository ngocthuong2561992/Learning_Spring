//package com.itext.pdf.service;
//
//import com.demo.pdf.PdfCommonUtils;
//import com.itextpdf.html2pdf.ConverterProperties;
//import com.itextpdf.kernel.pdf.PdfDocument;
//import com.itextpdf.kernel.pdf.PdfDocumentInfo;
//import com.itextpdf.layout.Document;
//import com.itextpdf.layout.element.Div;
//import com.itextpdf.layout.element.Table;
//
//public class PdfServiceOld {
//    public static void addMetaData(PdfDocument pdfDoc) {
//        PdfDocumentInfo documentInfo = pdfDoc.getDocumentInfo();
//        documentInfo.setAuthor("Jack");
//        documentInfo.addCreationDate();
//    }
//
//    public static void buildPart_A(Document document, ConverterProperties converterFont) {
//        String tdCss = "border: 1px solid black;"
//                + "text-align: left;"
//                + "padding: 8px;";
//        String tableCss = "font: 14px verdana;"
//                + 	"border-collapse: collapse;"
//                +	"background: white;"
//                + 	"width: 100%;";
//        String html = ""
//                + "<table style='" + tableCss + "'>"
//                +	 "<tr>"
//                +	     "<td style='" + tdCss + "' colspan='4'>"
//                + 			"<b style='margin-right: 10px; display: inline-block;'>A.</b>"
//                +            "<b>THÔNG TIN TƯ VẤN VIÊN</b>"
//                +        "</td>"
//                +    "</tr>"
//                +    "<tr>"
//                +        "<td style='" + tdCss + "' colspan='2'>Họ và tên Tư vấn viên:"
//                +            "<div style='margin-top: 10px; font-weight: bold;'>PRUDENTIAL VIỆT NAM</div>"
//                +        "</td>"
//                +        "<td style='" + tdCss + "'>Mã số:"
//                +            "<div style='margin-top: 10px; font-weight: bold;'>69101525</div>"
//                +        "</td>"
//                +        "<td style='" + tdCss + "'>Điện thoại liên lạc:"
//                +            "<div style='margin-top: 10px; font-weight: bold;'>0999999999</div>"
//                +        "</td>"
//                +    "</tr>"
//                + "</table>";
//        document.add((Table) PdfCommonUtils.getElementFromHtml(html, converterFont));
//
////		String imgHtml = ""
////				+ "<div style='padding: 20px;'>"
////    			+ 	"<img style='display: block; width: 100px; height: auto; margin: auto;' "
////        		+ 	"src='data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO9TXL0Y4OHwAAAABJRU5ErkJggg=='"
////        		+ 	"/>"
////        		+ "</div>";
////		document.add((Div) PdfCommonUtils.getElementFromHtml(imgHtml, converterFont));
//    }
//    public static void buildCaution(Document document, ConverterProperties converterFont) {
//        String css = ""
//                + "color: red;"
//                + "border: 1px solid black;"
//                + "background: white;"
//                + "margin: 10 0 0 0;"
//                + "font: italic 14px verdana;"
//                + "padding: 5px;";
//        String html = "<div style='" + css + "'>"
//                +	"<b>LƯU Ý:</b> Việc kê khai đầy đủ và trung thực các thông tin dưới đây sẽ quyết định tính hiệu lực của hợp đồng bảo hiểm và việc chi trả các quyền lợi bảo hiểm sau này của Quý khách."
////					+ 	"<img style='width: 140px; height: auto;' src='src/main/resources/static/images/Prudential_1.png'/>"
//                + "</div>";
//        document.add((Div) PdfCommonUtils.getElementFromHtml(html, converterFont));
//    }
//
//    public static void buildPart_B(Document document, ConverterProperties converterFont) {
//        String mainCss = ""
//                + "font: 14px verdana;"
//                + "background: white;"
//                + "width: 100%;"
//                + "line-height: 20px;"
//                + "margin-top: 10px;";
//        String numberingCss = "width: 20px; display: inline-block; float: left;";
//        String html = ""
//                + "<div style='" + mainCss + "'>"
//                +	"<div style='padding: 8px; border: 1px solid black; border-bottom: 0px;'>"
//                + 		"<b style='margin-right: 10px; display: inline-block;'>B.</b>"
//                +		"<b>THÔNG TIN NGƯỜI ĐƯỢC BẢO HIỂM (NĐBH) VÀ ĐỒNG THỜI LÀ BÊN MUA BẢO HIỂM</b>"
//                +	"</div>"
//                +	"<table style='width: 100%; border-collapse: collapse;'>"
//                +		"<tr>"
//                +	    	"<td colspan=2 style='border: 1px solid black; padding: 8px;'>"
//                +	    		"<div style='" + numberingCss+ "'>1.</div>"
//                +	    		"<div style='display: inline-block;'>Họ và tên:<br>"
//                +	    			"<b>CHECK DESPATH ADDRESS</b>"
//                +    			"</div>"
//                +    		"</td>"
//                +	    	"<td style='border: 1px solid black; padding: 8px;'>"
//                +	    		"<div style='" + numberingCss+ "'>2.</div>"
//                +	    		"<div style='display: inline-block;'>Giới tính:<br>"
//                +	    			"<b>Nam</b>"
//                +    			"</div>"
//                +	    	"</td>"
//                +	    	"<td style='border: 1px solid black; padding: 8px;'>"
//                +	    		"<div style='" + numberingCss+ "'>3.</div>"
//                +	    		"<div style='display: inline-block;'>Số CMND/CCCD/Hộ chiếu:<br>"
//                +	    			"<b>766655544345</b>"
//                +    			"</div>"
//                +	    	"</td>"
//                +	  	"</tr>"
//                +	  	"<tr>"
//                +	    	"<td style='border: 1px solid black; padding: 8px;'>"
//                +	    		"<div style='" + numberingCss+ "'>4.</div>"
//                +	    		"<div style='display: inline-block;'>Ngày sinh:<br>"
//                +	    			"<b>01/01/1970</b>"
//                +    			"</div>"
//                +	    	"</td>"
//                +	    	"<td style='border: 1px solid black; padding: 8px;'>"
//                +	    		"<div style='" + numberingCss+ "'>5.</div>"
//                +	    		"<div style='display: inline-block;'>Tình trạng hôn nhân:<br>"
//                +	    			"<b>Độc thân</b>"
//                +    			"</div>"
//                +	    	"</td>"
//                +	    	"<td style='border: 1px solid black; padding: 8px;'>"
//                +	    		"<div style='" + numberingCss+ "'>6.</div>"
//                +	    		"<div style='display: inline-block;'>Quốc gia nơi sinh:<br>"
//                +	    			"<b>VIỆT NAM</b>"
//                +    			"</div>"
//                +	    	"</td>"
//                +	    	"<td style='border: 1px solid black; padding: 8px;'>"
//                +	    		"<div style='" + numberingCss+ "'>7.</div>"
//                +	    		"<div style='display: inline-block;'>Quốc tịch:<br>"
//                +	    			"<b>VIỆT NAM</b>"
//                +    			"</div>"
//                +	    	"</td>"
//                +	  	"</tr>"
//                +	"</table>"
//                +	"<div style='padding: 8px; border: 1px solid black; border-top: 0px; font-size: 13px;'>"
//                +		"<div><span style='display: inline-block; width: 20px;'>8.</span>Thông tin liên lạc</div>"
//                +		"<table style='width: 100%; margin-left: 20px;'>"
//                +			"<tr>"
//                + 				"<td>"
//                +					"Địa chỉ thường trú:<br>"
//                +					"<b>241 Xuân Thủy, P. DỊCH VỌNG HẬU, Q.CẦU GIẤY, HÀ NỘI, VIỆT NAM</b><br>"
//                +					"Email: <b>truong.thanhbinh@prudential.com.vn</b><br>"
//                +					"Di động: <b>0906390361</b>"
//                + 				"</td>"
//                + 				"<td>"
//                +					"Địa chỉ thường trú:<br>"
//                +					"<b>241 Xuân Thủy, P. DỊCH VỌNG HẬU, Q.CẦU GIẤY, HÀ NỘI, VIỆT NAM</b><br>"
//                +					"Email: <b>truong.thanhbinh@prudential.com.vn</b><br>"
//                +					"Di động: <b>0906390361</b>"
//                + 				"</td>"
//                +			"</tr>"
//                +		"</table>"
//                +	"</div>"
//                +	"<div style='padding: 8px; border: 1px solid black; border-top: 0px;'>"
//                +		"<div style='" + numberingCss+ "'>9.</div>"
//                +		"<div style='display: inline-block;'>"
//                +			"Nghề nghiệp: <b>Ăn uống - đầu bếp trưởng</b><br>"
//                +			"Nghề nghiệp thuộc nhóm ngành: <b>Khác</b><br>"
//                +			"Hiện tại Quý khách có khai báo thuế tại Hoa kỳ không?: <b>Không</b>"
//                +		"</div>"
//                +	"</div>"
//                +	"<div style='padding: 8px; border: 1px solid black; border-top: 0px;'>"
//                +		"<b>Thông tin sức khỏe người được bảo hiểm (dành cho Quyền lợi nâng cao): CHECK DESPATH ADDRESS</b>"
//                +	"</div>"
//                +	"<table style='width: 100%; border-collapse: collapse;'>"
//                +		"<tr>"
//                +			"<td style='width: 80%; border: 1px solid black ; border-top: 0px; padding: 8px;'>"
//                +				"<b>CÂU HỎI DÀNH CHO SỐ TIỀN BẢO HIỂM NHỎ HƠN HOẶC BẰNG 500 TRIỆU ĐỒNG</b>"
//                +			"</td>"
//                +			"<td style='width: 20%; border: 1px solid black; padding: 8px; border-top: 0px; text-align: center;'>"
//                +				"<b>Xác nhận</b>"
//                +			"</td>"
//                +		"</tr>"
//                +		"<tr>"
//                +			"<td style='width: 80%; border: 1px solid black; border-top: 0px; padding: 8 8 8 20;'>"
//                +				"Tôi xin xác nhận rằng, tôi:"
//                +  				"<ol style='list-style-type: disc; text-align: justify;'>"
//                +				  	"<li>Hoàn toàn khỏe mạnh và không đang điều trị bất cứ bệnh gì và chưa từng nằm viện điều trị quá 2 tuần trong 2 năm vừa qua</li>"
//                +				  	"<li>Không sử dụng các chất kích thích hay ma túy (trừ khi có chỉ định của bác sĩ)</li>"
//                +				  	"<li>Không bị khuyết tật hoặc chấn thương hoặc các bệnh bẩm sinh</li>"
//                +				  	"<li>Chưa từng được chẩn đoán mắc các bệnh: Ung thư, đột quỵ, bệnh tim, bệnh về hệ thần kinh, bệnh về máu, bệnh tiểu đường, bệnh xơ gan, bệnh AIDS, suy thận, suy gan hoặc suy hô hấp.</li>"
//                +				"</ol>"
//                +			"</td>"
//                +			"<td style='width: 20%; border: 1px solid black; border-top: 0px; text-align: center;'>"
//                +				"<b>Tôi Xác nhận</b>"
//                +			"</td>"
//                +		"</tr>"
//                +	"</table>"
//                + "</div>";
//        document.add((Div) PdfCommonUtils.getElementFromHtml(html, converterFont));
//    }
//    public static void buildPart_C(Document document, ConverterProperties converterFont) {
//        String mainCss = ""
//                + "font: 14px verdana;"
//                + "border-collapse: collapse;"
//                + "background: white;"
//                + "width: 100%;"
//                + "line-height: 20px;"
//                + "margin-top: 10px;";
//        String html = ""
//                + "<table style='" + mainCss + "'>"
//                +	"<tr>"
//                +    	"<td colspan=3 style='border: 1px solid black; padding: 5px 8px; line-height: 20px;'>"
//                +    		"<b style='margin-right: 10px; float: left;'>C.</b>"
//                +    		"<b style='display: inline-block;'>THÔNG TIN SẢN PHẨM BẢO HIỂM VÀ PHÍ BẢO HIỂM<br>NGƯỜI ĐƯỢC BẢO HIỂM: CHECK DESPATH ADDRESS</b>"
//                +		"</td>"
//                +  	"</tr>"
//                +  	"<tr>"
//                +	  	"<th style='border: 1px solid black; padding: 8px;'>Sản phẩm bảo hiểm</th>"
//                +	  	"<th style='border: 1px solid black; padding: 8px;'>Số tiền bảo hiểm(VNĐ)<sup>(*)</sup></th>"
//                +	  	"<th style='border: 1px solid black; padding: 8px;'>Thời hạn hợp đồng (tháng)</th>"
//                +  	"</tr>"
//                +  	"<tr>"
//                +	  	"<td style='border: 1px solid black; padding: 8px;'>PRU-AN TÂM BẢO TÍN (kế hoạch A)</td>"
//                +	  	"<td style='border: 1px solid black; padding: 8px; text-align: right;'>500.000.000</td>"
//                +	  	"<td style='border: 1px solid black; padding: 8px; text-align: right;'>36</td>"
//                +  	"</tr>"
//                +  	"<tr>"
//                +	  	"<td colspan=3 style='border: 1px solid black; padding: 8px;'>"
//                +	  		"<b>Quyền lợi bảo hiểm</b>"
//                +			"<div style='margin: 5px 0px 15px 0px;'>Quyền lợi nâng cao - Tử vong hoặc Thương tật toàn bộ và vĩnh viễn</div>"
//                +			"<b>Lựa chọn đóng phí <sup>(**)</sup></b>"
//                +	  	"</td>"
//                +  	"</tr>"
//                +  	"<tr>"
//                +	  	"<td style='border: 1px solid black; padding: 8px;'>Một lần</td>"
//                +	  	"<td colspan=2 style='border: 1px solid black; padding: 8px;'>Phí bảo hiểm: 5.775.000 VNĐ</td>"
//                +  	"</tr>"
//                +  	"<tr>"
//                +	  	"<td colspan=3 style='border: 1px solid black; padding: 8px; line-height: 20px;'>"
//                +	  		"<b>Thông tin hợp đồng tín dụng:</b><br>"
//                +			"Loại hình tín dụng"
//                +	  	"</td>"
//                +  	"</tr>"
//                +  	"<tr>"
//                +	  	"<td style='border: 1px solid black; padding: 8px;'><b>Vay tín chấp</b></td>"
//                +	  	"<td style='border: 1px solid black; padding: 8px;'>"
//                +	  		"Giá trị khoản vay:<br>500.000.000 VNĐ"
//                +	  	"</td>"
//                +	  	"<td style='border: 1px solid black; padding: 8px;'>Lãi suất: 12 %</td>"
//                +  	"</tr>"
//                +  	"<tr>"
//                +	  	"<td colspan=3 style='border: 1px solid black; padding: 8px;'>"
//                +	  		"(*) Đây là Số tiền bảo hiểm khách hàng lựa chọn tại thời điểm tham gia. Số tiền bảo hiểm cụ thể tại thời điểm xảy ra sự kiện bảo hiểm được quy định tại Quy tắc, Điều khoản của sản phẩm và/hoặc trên Bảng minh họa quyền lợi sản phẩm."
//                +	  		"<br>(**) Trường hợp lựa chọn đóng phí định kỳ thì Thời hạn hợp đồng của sản phẩm bảo hiểm là 1 năm (nhưng không được vượt quá thời hạn còn lại của hợp đồng tín dụng) và được tự động gia hạn hàng năm. Tỷ lệ phí bảo hiểm định kỳ có thể thay đổi vào ngày gia hạn kế tiếp của Thời hạn hợp đồng dựa theo nguyên tắc định phí đã được Bộ Tài chính phê chuẩn. Prudential có nghĩa vụ thông báo bằng văn bản cho Bên mua bảo hiểm ít nhất 30 ngày trước ngày áp dụng Tỷ lệ phí bảo hiểm mới."
//                +	  	"</td>"
//                +  	"</tr>"
//                +  	"<tr>"
//                +	  	"<td colspan=3 style='border: 1px solid black; padding: 8px;'>"
//                +	  		"<b>Nhu cầu bảo hiểm và khả năng tài chính:</b><br>"
//                +	  		"<input type='checkbox' checked style='margin: 0px 5px 0px 0px;'>"
//                +	  		"Tôi xác nhận sản phẩm bảo hiểm trên phù hợp với nhu cầu hiện tại về bảo vệ an toàn tài chính cho gia đình trước rủi ro và mức phí bảo hiểm phù hợp với khả năng tài chính của tôi."
//                +	  	"</td>"
//                +  	"</tr>"
//                + "</table>";
//        Table table = (Table) PdfCommonUtils.getElementFromHtml(html, converterFont);
////		table.setKeepTogether(true);
//        document.add(table);
//    }
//    public static void buildPart_D(Document document, ConverterProperties converterFont) {
//        String mainCss = ""
//                + "font: 14px verdana;"
//                + "background: white;"
//                + "width: 100%;"
//                + "line-height: 20px;"
//                + "margin-top: 10px;"
//                + "border: 1px solid black;"
//                + "box-sizing: border-box;";
//        String html = ""
//                +   "<div style='" + mainCss + "'>"
//                +		"<div style='border-bottom: 1px solid black; padding: 8px;'>"
//                +			"<b style='margin-right: 10px; float: left;'>D.</b>"
//                +			"<b style='display: inline-block;'> CHI TIẾT NGƯỜI THỤ HƯỞNG QUYỀN LỢI BẢO HIỂM</b>"
//                +		"</div>"
//                +		"<div style='padding: 8px;'>"
//                +			"(Hiện tại Công ty chưa nhận được thông tin người thụ hưởng quyền lợi bảo hiểm từ Quý khách)"
//                +		"</div>"
//                +	"</div>";
//        document.add((Div) PdfCommonUtils.getElementFromHtml(html, converterFont));
//    }
//    public static void buildPart_E(Document document, ConverterProperties converterFont) {
//        String mainCss = ""
//                + "font: 14px verdana;"
//                + "background: white;"
//                + "width: 100%;"
//                + "line-height: 20px;"
//                + "margin-top: 10px;"
//                + "border: 1px solid black;"
//                + "text-align: justify;"
//                + "box-sizing: border-box;";
//        String html = ""
//                + "<div style='" + mainCss + "'>"
//                +		"<div style='border-bottom: 1px solid black; padding: 8px;'>"
//                +			"<b style='margin-right: 10px; float: left;'>E.</b>"
//                +			"<b style='display: inline-block;'>CAM KẾT CỦA NĐBH</b>"
//                +		"</div>"
//                +		"<div style='padding: 8px;'>"
//                +			"Tôi cam kết và đồng ý:"
//                +			"<ol>"
//                +				"<li>Đã kê khai đầy đủ, đúng sự thật về các thông tin và số liệu trong hồ sơ yêu cầu bảo hiểm (“HSYCBH”) này và những thông tin khác theo yêu cầu của Prudential; nếu các thông tin và số liệu này được cố tình kê khai không trung thực hoặc không đầy đủ, Prudential sẽ không có nghĩa vụ giải quyết bất cứ quyền lợi bảo hiểm nào;</li>"
//                +				"<li style='margin-top: 5px;'>Nếu không có sự chấp thuận trước bằng văn bản, Prudential không được phép tiết lộ hoặc sử dụng các thông tin và số liệu do chúng tôi cung cấp trong HSYCBH hoặc liên quan đến hợp đồng bảo hiểm cho bất cứ bên thứ ba nào khác ngoại trừ các trường hợp sau đây: thu thập, sử dụng, chuyển giao theo yêu cầu của cơ quan nhà nước có thẩm quyền hoặc cho mục đích thẩm định, tính toán phí bảo hiểm, phát hành hợp đồng bảo hiểm, thu phí bảo hiểm, tái bảo hiểm, trích lập dự phòng nghiệp vụ, giải quyết chi trả quyền lợi bảo hiểm, nghiên cứu thiết kế sản phẩm, phòng chống trục lợi bảo hiểm, nghiên cứu, đánh giá tình hình tài chính, khả năng thanh toán, mức độ đầy đủ vốn, yêu cầu vốn, lưu trữ, xử lý và quản trị cơ sở dữ liệu;</li>"
//                +				"<li style='margin-top: 5px;'>Cho Prudential thu thập những thông tin, tài liệu về sức khỏe, điều kiện y tế ở bất kỳ thời gian nào, từ bất cứ cơ sở y tế, bệnh viện hoặc bác sĩ nào đã khám, chẩn đoán và/ hoặc điều trị cho tôi, hoặc những thông tin có liên quan đến tôi từ bất cứ cơ quan chính quyền, Công ty bảo hiểm, tổ chức hoặc cá nhân nào khác mà không cần phải được tôi cho phép bằng một văn bản khác; và các cơ sở y tế, bệnh viện, bác sĩ, Công ty bảo hiểm khác, tổ chức, cá nhân, khi nhận được bản sao HSYCBH này, được phép cung cấp cho Công ty những thông tin nói trên;</li>"
//                +				"<li style='margin-top: 5px;'>Đã và chỉ cung cấp cho đại lý của Công ty những thông tin như đã nêu trong HSYCBH này trong quá trình được tư vấn, trả lời các câu hỏi theo mẫu và hoàn tất HSYCBH; đã lập HSYCBH này một cách độc lập; và Đại lý của Prudential đã không cung cấp bất cứ thông tin nào gây bất lợi cho Prudential hoặc tạo thuận lợi hơn cho tôi khi Công ty thẩm định hồ sơ này;</li>"
//                +				"<li style='margin-top: 5px;'>Toàn bộ quá trình tư vấn bảo hiểm, lập và ký HSYCBH này được thực hiện tại Việt Nam và trong thời gian tôi đang lưu trú tại Việt Nam;</li>"
//                +				"<li style='margin-top: 5px;'>Cập nhật kip thời cho Prudential bất kỳ thay đổi nào về các thông tin cá nhân đã cung cấp cho Prudential, bao gồm cả thư điện tử, điện thoại và địa chỉ liên lạc; và cung cấp cho Prudential bất kỳ thông tin bổ sung nào khi Prudential có yêu cầu, ví dụ như các tờ khai thuế; và cho phép Prudential cung cấp cho cơ quan thuế Hoa kỳ (i) thông tin cá nhân của tôi như đã cung cấp trong hợp đồng bảo hiểm hoặc được cung cấp sau đó cho Prudential , bao gồm cả thông tin về khai thuế, và (ii) thông tin về hợp đồng bảo hiểm, bao gồm số hợp đồng bảo hiểm, giá trị tài khoản hoặc thông tin về hợp đồng và/hoặc giá trị tiền mặt của hợp đồng bảo hiểm, nếu có, tại bất kỳ thời điểm nào trong thời hạn của hợp đồng bảo hiểm này, khi tôi là người chịu thuế hoặc trở thành người chịu thuế tại Hoa kỳ như được định nghĩa theo luật thuế Hoa kỳ;</li>"
//                +				"<li style='margin-top: 5px;'>Tổ chức tài chính nêu dưới đây được ủy quyền thay mặt tôi trực tiếp giao dịch với Prudential về việc đóng phí bảo hiểm, yêu cầu giải quyết quyền lợi bảo hiểm, nhận hoàn phí bảo hiểm, nhận quyền lợi bảo hiểm, và/hoặc nhận/ thực hiện (các) khoản thanh toán, quyền lợi, nghĩa vụ, thông tin liên quan đến Hợp đồng bảo hiểm như được quy định trong Quy tắc và Điều khoản sản phẩm bảo hiểm.</li>"
//                +			"</ol>"
//                +			"<div style='width: 200px; margin-left: 40px; text-align: center;'>"
//                +				"<b>Xác nhận của NĐBH</b><br>"
//                +				"(Chữ ký điện tử)"
//                +			"</div>"
//                +			"<table style='border-collapse: collapse;'>"
//                +				"<tr>"
//                +					"<td style='border: 1px solid black; padding: 8px; width: 270px; vertical-align: top;'>"
//                +						"<img src='src/main/resources/static/images/sign.png' style='width: 100%; height: auto;'/>"
//                +					"</td>"
//                +					"<td style='border: 1px solid black; padding: 8px;'>"
//                +						"Tôi xác nhận rằng tôi đã được Đại lý bảo hiểm/Đại diện bán hàng phân tích nhu cầu, giải thích và tôi hiểu rõ cũng như hoàn toàn đồng ý với nội dung HSYCBH, Tài liệu minh họa bán hàng và Quy tắc và Điều khoản sản phẩm bảo hiểm, bao gồm điều khoản loại trừ trách nhiệm bảo hiểm, là bộ phận hợp thành Bộ Hợp đồng bảo hiểm."
//                +					"</td>"
//                +				"</tr>"
//                +			"</table>"
//                +		"</div>"
//                +	"</div>";
//        document.add((Div) PdfCommonUtils.getElementFromHtml(html, converterFont));
//    }
//
//}
