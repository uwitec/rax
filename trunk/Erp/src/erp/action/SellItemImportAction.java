package erp.action;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

import erp.model.InvoiceItem;
import erp.model.Sell;
import erp.service.SellService;

public class SellItemImportAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger
            .getLogger(SellItemImportAction.class);

    private SellService sellService;
    
    private int sellId;
    private String sellContent;
    private List<InvoiceItem> itemList;

    @Override
    public String input() throws Exception {
        return SUCCESS;
    }

    @Override
    public String execute() throws Exception {
        try {
            itemList = new ArrayList<InvoiceItem>();
            InvoiceItem item = null;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            String numStr = "\\(([0-9]*) 件\\)";
            String dateStr = "[0-9]{4}/[0-9]{1,2}/[0-9]{1,2}";
            String[] details;

            String info = "";
            String[] infos = sellContent.split("\n");
            Pattern pattern = Pattern.compile(dateStr);
            Matcher matcher;
            for (int i = 0; i < infos.length; i++) {

                Date date = new Date();
                int num = 1;
                String name = "";
                String byerId = "";
                String byerName = "";

                info = infos[i].trim();
                // logger.info("info:" + info);
                matcher = pattern.matcher(info);
                if (matcher.find()) {
                    details = info.split(" ");
                    date = formatter.parse(details[0]);
                    // logger.info("date:" + formatter.format(date));

                    info = infos[i - 3].trim();
                    details = info.split(" ");
                    int pos = details.length - 1;
                    byerId = details[pos].trim();
                    byerName = infos[i - 2].trim();
                    // logger.info("byerId:" + byerId + " byerName:" +
                    // byerName);

                    StringBuffer nameBuffer = new StringBuffer();
                    for (int j = 0; j < pos; j++) {
                        nameBuffer.append(details[j]);
                        nameBuffer.append(" ");
                    }
                    name = nameBuffer.toString().trim();
                    // logger.info("name:" + name);

                    Pattern numPattern = Pattern.compile(numStr);
                    Matcher numMatcher = numPattern.matcher(name);
                    if (numMatcher.find()) {
                        name = name.replace(numMatcher.group(0), "").trim();
                        num = Integer.parseInt(numMatcher.group(1));
                        // logger.info("num:" + num );
                    }

                    item = new InvoiceItem();
                    item.setName(name);
                    item.setDate(date);
                    item.setByerId(byerId);
                    item.setNumber(num);
                    item.setByerName(byerName);

                    itemList.add(item);

                    logger.info("日期:" + formatter.format(date) + " 宝贝名称:"
                            + name + " 数量:" + num + " 买家ID:" + byerId
                            + " 买家姓名:" + byerName);
                }
            }
            if (sellId > 0 && item != null) {
                Sell obj = sellService.getSellById(sellId);
                obj.setCustomerWangwang(item.getByerId());
                sellService.updateSell(obj);
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
        }

        return SUCCESS;

    }

    public static void main(String[] args) throws Exception {
        char[] cbuf = new char[1024];
        StringBuffer buf = new StringBuffer();
        InputStreamReader is = new InputStreamReader(new FileInputStream(
                "D:/content.txt"));
        int size;
        while ((size = is.read(cbuf)) != -1) {
            buf.append(cbuf, 0, size);
        }

        SellItemImportAction action = new SellItemImportAction();
        action.sellContent = buf.toString();
        action.execute();
    }
    
    public void setSellService(SellService sellService) {
        this.sellService = sellService;
    }
    
    public String getSellContent() {
        return sellContent;
    }

    public void setSellContent(String sellContent) {
        this.sellContent = sellContent;
    }

    public List<InvoiceItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<InvoiceItem> itemList) {
        this.itemList = itemList;
    }

    public int getSellId() {
        return sellId;
    }

    public void setSellId(int sellId) {
        this.sellId = sellId;
    }

}
