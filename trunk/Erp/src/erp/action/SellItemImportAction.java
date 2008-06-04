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

public class SellItemImportAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger
            .getLogger(SellItemImportAction.class);

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
            InvoiceItem item;

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String numStr = "([0-9]*)件";
            String dateStr = "^[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}$";
            String[] details;
            String[] infos = sellContent.split("定位");
            for (String info : infos) {

                Date date = new Date();
                int num = 0;
                String name = "";
                String byerId = "";
                String byerName = "";
                double price = 0;

                details = info.trim().split(" ");
                if (details.length < 15) {
                    continue;
                }
                
                for (int i = 0; i < details.length; i++) {
                    if (details[i].matches(dateStr)) {
                        date = formatter.parse(details[i]);
                        //logger.info("date:" + formatter.format(date));
                        
                        int pos = i;
                        num = 1;
                        Pattern numPattern = Pattern.compile(numStr);
                        Matcher numMatcher = numPattern.matcher(details[i - 1]);
                        if (numMatcher.find()) {
                            num = Integer.parseInt(numMatcher.group(numMatcher
                                    .groupCount()));
                            pos = i - 1;
                            //logger.info("num:" + num + " pos:" + pos);
                        }

                        StringBuffer nameBuffer = new StringBuffer();
                        for (int j = 0; j < pos; j++) {
                            if (details[j].equals("\n") || details[j].equals("\r\n")) {
                                nameBuffer = new StringBuffer();
                                continue;
                            }
                            nameBuffer.append(details[j]);
                            nameBuffer.append(" ");
                        }
                        name = nameBuffer.toString().trim();
                        //logger.info("name:" + name);
                        
                        String[] byers = details[i + 2].trim().split("\n");
                        byerId = byers[0].trim();
                        byerName = byers[1].trim();
                        //logger.info("byerId:" + byerId);
                        //logger.info("byerName:" + byerName);

                        String[] prices = details[i + 4].trim().split("\n");
                        price = Double.parseDouble(prices[0]);
                        //logger.info("price:" + price);
                        
                        break;
                    }
                }

                item = new InvoiceItem();
                item.setName(name);
                item.setDate(date);
                item.setByerId(byerId);
                item.setByerName(byerName);
                item.setNumber(num);
                item.setPrice(price);

                itemList.add(item);

                logger.info("日期:" + formatter.format(date) + " 宝贝名称:" + name + " 数量:"
                        + num + " 买家ID:" + byerId + " 买家姓名:" + byerName
                        + " 价格:" + price);
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
