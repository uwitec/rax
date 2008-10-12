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
import erp.model.SellItem;
import erp.service.SellItemService;
import erp.service.SellService;
import erp.service.WareService;

public class SellItemImportAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger
            .getLogger(SellItemImportAction.class);

    private SellService sellService;
    private SellItemService sellItemService;
    private WareService wareService;

    private int sellId;
    private Sell sell;
    private String sellContent;
    private List<InvoiceItem> itemList;
    private List<SellItem> sellItemList;

    @Override
    public String input() throws Exception {
        sell = sellService.getSellById(sellId);
        if (sell != null) {
            sellItemList = sellItemService.listBySell(sell);
            for (SellItem item : sellItemList) {
                item.setWare(wareService.getWareById(item.getWareId()));
            }
        }
        return SUCCESS;
    }

    @Override
    public String execute() throws Exception {
        try {
            itemList = new ArrayList<InvoiceItem>();
            InvoiceItem item = null;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String numStr = "\\(([0-9]*) 件\\)";
            String dateStr = "([0-9.]+)\\s+([0-9]{4}-[0-9]{1,2}-[0-9]{1,2})";
            String[] details;

            String info = "";
            String[] infos = sellContent.split("\n");
            ArrayList<String> infoArray = new ArrayList<String>();
            for (int i = 0; i < infos.length; i++) {
                if (infos[i].trim().length() > 0) {
                    infoArray.add(infos[i].trim());
                }
            }

            Pattern pattern = Pattern.compile(dateStr);
            Matcher matcher;
            for (int i = 0; i < infoArray.size(); i++) {
                Date date = new Date();
                int num = 1;
                String name = "";
                String byerId = "";
                String byerName = "";
                double price = 0;

                info = infoArray.get(i);
                // logger.info("info[" + i + "]:" + info);

                matcher = pattern.matcher(info);
                if (matcher.find()) {
                    // logger.info("datePattern:");
                    // for (int j = 0; j <= matcher.groupCount(); j++) {
                    // logger.info(matcher.group(j));
                    // }

                    price = Double.parseDouble(matcher.group(1));
                    // logger.info("price:" + String.valueOf(price));

                    date = formatter.parse(matcher.group(2));
                    // logger.info("date:" + formatter.format(date));

                    details = info.split("  ");
                    int pos = details[0].equals("修改") ? i - 1 : i;

                    byerName = infoArray.get(pos - 1);
                    // logger.info("byerName:" + byerName);

                    details = infoArray.get(pos - 2).split("  ");
                    pos = details.length - 1;
                    byerId = details[pos].trim();
                    // logger.info("byerId:" + byerId);

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
                        // logger.info("num:" + num);
                    }

                    item = new InvoiceItem();
                    item.setName(name);
                    item.setDate(date);
                    item.setByerId(byerId);
                    item.setNumber(num);
                    item.setByerName(byerName);
                    item.setPrice(price);

                    itemList.add(item);

                    logger.info("日期:" + formatter.format(date) + " 宝贝名称:"
                            + name + " 数量:" + num + " 价格:" + price + " 买家ID:"
                            + byerId + " 买家姓名:" + byerName);
                }

            }
            if (sellId > 0 && item != null) {
                Sell obj = sellService.getSellById(sellId);
                obj.setCustomerIM(item.getByerId());
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

    public void setSellItemService(SellItemService sellItemService) {
        this.sellItemService = sellItemService;
    }

    public void setWareService(WareService wareService) {
        this.wareService = wareService;
    }

    public Sell getSell() {
        return sell;
    }

    public List<SellItem> getSellItemList() {
        return sellItemList;
    }

}
