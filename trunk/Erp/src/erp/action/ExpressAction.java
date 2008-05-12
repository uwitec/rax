package erp.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import com.opensymphony.xwork2.Action;

import erp.model.ExpressPos;
import erp.model.ExpressTemplate;
import erp.model.Sell;
import erp.service.ExpressService;
import erp.service.SellService;

public class ExpressAction implements Action {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(ExpressAction.class);
    private SellService sellService = null;
    private ExpressService expressService = null;
    private Sell sell;
    private int sellId;
    private String date;
    private String sender;
    private String senderPhone;
    private String senderAddress;
    private String senderPostCode;
    Map dateSel;
    Map expressSel;

    public InputStream getInputStream() throws Exception {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        try {
            ExpressPos pos;
            ExpressTemplate tpl = expressService
                    .getExpress(sell.getExpressId());
            BaseFont bfChinese = BaseFont.createFont("STSong-Light",
                    "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            BaseFont bfEnglish = BaseFont.createFont(BaseFont.HELVETICA,
                    BaseFont.CP1252, BaseFont.NOT_EMBEDDED);

            Document doc = new Document();
            doc.setPageSize(tpl.getSize().toRectangle());

            PdfWriter writer = PdfWriter.getInstance(doc, buf);
            doc.open();

            PdfContentByte cb = writer.getDirectContent();
            cb.beginText();

            // 英文字体
            cb.setFontAndSize(bfEnglish, 10);

            // 发件日期
            pos = tpl.getDate();
            if (pos != null) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date d = df.parse(date);
                df = new SimpleDateFormat(tpl.getDateFormat());
                cb.setTextMatrix(pos.X, pos.Y);
                cb.showText(df.format(d));
            }

            // 发件人电话
            pos = tpl.getSrcPhone();
            cb.setTextMatrix(pos.X, pos.Y);
            cb.showText(senderPhone);

            // 发件人邮编
            pos = tpl.getSrcPostCode();
            if (pos != null) {
                cb.setTextMatrix(pos.X, pos.Y);
                cb.showText(senderPostCode);
            }

            // 收件人电话1
            pos = tpl.getDstPhone1();
            cb.setTextMatrix(pos.X, pos.Y);
            cb.showText(sell.getCustomerPhone1());

            // 收件人电话2
            pos = tpl.getDstPhone2();
            cb.setTextMatrix(pos.X, pos.Y);
            cb.showText(sell.getCustomerPhone2());

            // 收件人邮编
            pos = tpl.getDstPostCode();
            if (pos != null) {
                cb.setTextMatrix(pos.X, pos.Y);
                cb.showText(sell.getCustomerPostCode());
            }

            // 发件人姓名
            cb.setFontAndSize(bfChinese, 11);
            pos = tpl.getSrcName();
            cb.setTextMatrix(pos.X, pos.Y);
            cb.showText(sender);

            // 收件人姓名
            cb.setFontAndSize(bfChinese, 12);
            pos = tpl.getDstName();
            cb.setTextMatrix(pos.X, pos.Y);
            cb.showText(sell.getCustomerName());

            cb.endText();

            // 收发件人地址
            StringBuffer recvAddress = new StringBuffer();
            recvAddress.append(sell.getCustomerAddress());
            if (false == sell.getCommentExpress().isEmpty()) {
                recvAddress.append("\n");
                recvAddress.append(sell.getCommentExpress());
            }
            Font cf = new Font(bfChinese, 11, Font.NORMAL);
            Phrase srcAddress = new Phrase(senderAddress, cf);
            Phrase dstAddress = new Phrase(recvAddress.toString(), cf);
            ExpressPos posLB;
            ExpressPos posRT;

            // 发件人地址
            ColumnText ct = new ColumnText(cb);
            posLB = tpl.getSrcAddressLB();
            posRT = tpl.getSrcAddressRT();
            logger.info("SrcAddr:" + senderAddress);
            logger.info(posLB.X + "," + posLB.Y);
            logger.info(posRT.X + "," + posRT.Y);
            ct.setSimpleColumn(srcAddress, posLB.X, posLB.Y, posRT.X, posRT.Y,
                    15, Element.ALIGN_LEFT | Element.ALIGN_TOP);
            ct.go();

            // 收件人地址
            ct = new ColumnText(cb);
            posLB = tpl.getDstAddressLB();
            posRT = tpl.getDstAddressRT();
            logger.info("DstAddr:" + recvAddress.toString());
            logger.info(posLB.X + "," + posLB.Y);
            logger.info(posRT.X + "," + posRT.Y);
            ct.setSimpleColumn(dstAddress, posLB.X, posLB.Y, posRT.X, posRT.Y,
                    15, Element.ALIGN_LEFT | Element.ALIGN_TOP);
            ct.go();

            doc.close();
        } catch (Exception ex) {
            logger.error(ex.toString());
        }
        return new ByteArrayInputStream(buf.toByteArray());
    }

    public String input() throws Exception {
        if (sellId > 0) {
            sell = sellService.getSellById(sellId);
            expressSel = expressService.getExpressSel();
            logger.info("sell.expressId:" + sell.getExpressId());
            dateSel = new HashMap();
            dateSel.put(0, "今天");
            dateSel.put(1, "明天");
            dateSel.put(2, "后天");
            sender = "李立林";
            senderAddress = "浙江省杭州市滨江区\n请确认商品无缺损后签收\n签收后申述缺损,恕我们无法负责";
            senderPhone = "0571-85790698";
            senderPostCode = "310053";
            return SUCCESS;
        }
        return ERROR;
    }

    @Override
    public String execute() throws Exception {
        if (sellId > 0) {
            Sell s = sellService.getSellById(sellId);
            if (false == sell.getCommentExpress().equals(s.getCommentExpress())
                    || false == sell.getCustomerName().equals(
                            s.getCustomerName())
                    || false == sell.getCustomerAddress().equals(
                            s.getCustomerAddress())
                    || false == sell.getCustomerPhone1().equals(
                            s.getCustomerPhone1())
                    || false == sell.getCustomerPhone2().equals(
                            s.getCustomerPhone2())
                    || false == sell.getCustomerPostCode().equals(
                            s.getCustomerPostCode())
                    || sell.getExpressId() != s.getExpressId()) {
                s.setCommentExpress(sell.getCommentExpress());
                s.setCustomerName(sell.getCustomerName());
                s.setCustomerAddress(sell.getCustomerAddress());
                s.setCustomerPhone1(sell.getCustomerPhone1());
                s.setCustomerPhone2(sell.getCustomerPhone2());
                s.setCustomerPostCode(sell.getCustomerPostCode());
                s.setExpressId(sell.getExpressId());
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                s.setSendDate(df.parse(date));
                sellService.updateSell(s);
            }
            sell = s;
            return SUCCESS;
        }
        return ERROR;
    }

    public static void main(String[] args) throws Exception {
        // OutputStream os = new FileBufferWriter("demo.pdf");
        // os.write(getInputStream().;
    }

    public void setSellService(SellService sellService) {
        this.sellService = sellService;
    }

    public int getSellId() {
        return sellId;
    }

    public SellService getSellService() {
        return sellService;
    }

    public void setSell(Sell sell) {
        this.sell = sell;
    }

    public void setDateSel(Map dateSel) {
        this.dateSel = dateSel;
    }

    public void setSellId(int sellId) {
        this.sellId = sellId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Sell getSell() {
        return sell;
    }

    public Map getDateSel() {
        return dateSel;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public String getSenderPostCode() {
        return senderPostCode;
    }

    public void setSenderPostCode(String senderPostCode) {
        this.senderPostCode = senderPostCode;
    }

    public ExpressService getExpressService() {
        return expressService;
    }

    public void setExpressService(ExpressService expressService) {
        this.expressService = expressService;
    }

    public Map getExpressSel() {
        return expressSel;
    }

    public void setExpressSel(Map expressSel) {
        this.expressSel = expressSel;
    }

}
