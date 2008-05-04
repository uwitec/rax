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
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import com.opensymphony.xwork2.Action;

import erp.model.Sell;
import erp.service.SellService;

public class ExpressAction implements Action {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(ExpressAction.class);
    private SellService sellService = null;
    private Sell sell;
    private int sellId;
    private String comment;
    private String date;
    Map dateSel;

    public InputStream getInputStream() throws Exception {

        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        Rectangle pageSize = new Rectangle(millimeterToPoint(232),
                millimeterToPoint(127));
        Document doc = new Document();
        doc.setPageSize(pageSize);

        BaseFont bfChinese = BaseFont.createFont("STSong-Light",
                "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        BaseFont bfEnglish = BaseFont.createFont(BaseFont.HELVETICA,
                BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
        Font ef = new Font(bfEnglish, 12, Font.NORMAL);
        Font cf = new Font(bfChinese, 12, Font.NORMAL);

        PdfWriter writer = PdfWriter.getInstance(doc, buf);
        doc.open();

        PdfContentByte cb = writer.getDirectContent();
        cb.beginText();
        cb.setFontAndSize(bfChinese, 12);
        cb.setTextMatrix(10, 10);
        cb.showText("中文内容");
        cb.setTextMatrix(10, 80);
        cb.showText("很长很长\n很长很长\n很长的\n中文内容");
        cb.setTextMatrix(10, 120);
        cb.showText("很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长的中文内容");

        cb.setFontAndSize(bfEnglish, 12);
        cb.setTextMatrix(47, 34);
        cb.showText(date);
        cb.endText();
        
        Phrase p1 = new Phrase(
                "quick brown fox jumps over the lazy dog quick brown fox jumps over the lazy dog quick brown fox jumps over the lazy dog",
                ef);
        Phrase p2 = new Phrase("很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长的中文", cf);

        ColumnText ct = new ColumnText(cb);
        ct.setSimpleColumn(p1, 100, 100, 200, 200, 15, Element.ALIGN_LEFT
                | Element.ALIGN_TOP);
        ct.go();
        ct.clearChunks();
        ct.setSimpleColumn(p2, 200, 200, 300, 300, 15, Element.ALIGN_LEFT
                | Element.ALIGN_TOP);
        ct.go();
        
        doc.close();
        return new ByteArrayInputStream(buf.toByteArray());
    }

    private float centimetreToPoint(float length) {
        return (float) (length / 2.54 * 72);
    }

    private float millimeterToPoint(float length) {
        return centimetreToPoint(length / 10);
    }

    @Override
    public String execute() throws Exception {
        if (sellId > 0 && (comment == null || date == null)) {
            sell = sellService.getSellById(sellId);
            dateSel = new HashMap();
            dateSel.put(0, "今天");
            dateSel.put(1, "明天");
            dateSel.put(2, "后天");
            return INPUT;
        }
        return SUCCESS;
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

}
