package erp.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
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
    private String comment;
    private String date;
    private String sender;
    private String senderPhone;
    private String senderAddress;
    private String senderPostCode;
    Map dateSel;

    public InputStream getInputStream() throws Exception {
        ExpressPos pos;
        ExpressTemplate tpl = ExpressService.getExpress(sell.getExpressId());
        BaseFont bfChinese = BaseFont.createFont("STSong-Light",
                "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        BaseFont bfEnglish = BaseFont.createFont(BaseFont.HELVETICA,
                BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
        Font cf = new Font(bfChinese, 12, Font.NORMAL);

        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        Document doc = new Document();
        doc.setPageSize(tpl.getSize().toRectangle());

        PdfWriter writer = PdfWriter.getInstance(doc, buf);
        doc.open();

        PdfContentByte cb = writer.getDirectContent();
        cb.beginText();

        // 英文字体
        cb.setFontAndSize(bfEnglish, 12);

        // 发件日期
        pos = tpl.getDate();
        cb.setTextMatrix(pos.X, pos.Y);
        cb.showText(date);

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

        // 中文字体
        cb.setFontAndSize(bfChinese, 12);

        // 发件人姓名
        pos = tpl.getSrcName();
        cb.setTextMatrix(pos.X, pos.Y);
        cb.showText(sender);

        // 收件人姓名
        pos = tpl.getDstName();
        cb.setTextMatrix(pos.X, pos.Y);
        cb.showText(sell.getCustomerName());

        cb.endText();

        // 收发件人地址
        Phrase srcAddress = new Phrase(senderAddress, cf);
        Phrase dstAddress = new Phrase(sell.getCustomerAddress(), cf);

        // 发件人地址
        ColumnText ct = new ColumnText(cb);
        ct.setSimpleColumn(srcAddress, 100, 100, 200, 200, 15,
                Element.ALIGN_LEFT | Element.ALIGN_TOP);
        ct.go();
        ct.clearChunks();

        // 收件人地址
        ct.setSimpleColumn(dstAddress, 200, 200, 300, 300, 15,
                Element.ALIGN_LEFT | Element.ALIGN_TOP);
        ct.go();

        doc.close();
        return new ByteArrayInputStream(buf.toByteArray());
    }

    /*
     * private float centimetreToPoint(float length) { return (float) (length /
     * 2.54 * 72); }
     * 
     * private float millimeterToPoint(float length) { return
     * centimetreToPoint(length / 10); }
     */

    @Override
    public String execute() throws Exception {
        if (sellId > 0 && (comment == null || date == null || sender == null)) {
            sell = sellService.getSellById(sellId);
            dateSel = new HashMap();
            dateSel.put(0, "今天");
            dateSel.put(1, "明天");
            dateSel.put(2, "后天");
            sender = "李立林";
            senderAddress = "浙江省杭州市滨江区\n请确认商品无缺损后签收\n签收后申述缺损,恕我们无法承担责任";
            senderPhone = "0571-85790698";
            senderPostCode = "310053";
            return INPUT;
        }
        return SUCCESS;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
}
