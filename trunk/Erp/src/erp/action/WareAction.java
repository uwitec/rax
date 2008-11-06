package erp.action;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

import erp.model.Ware;
import erp.model.WareCategory;
import erp.service.KeywordService;
import erp.service.WareCategoryService;
import erp.service.WareService;
import erp.util.Pager;

public class WareAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(WareAction.class);

    private WareService wareService = null;
    private WareCategoryService wareCategoryService = null;
    private KeywordService keywordService = null;

    private int id;
    private int categoryId;
    private String name;
    private String cost;
    private String price;
    private String barcode;
    private String number;
    private int status = 0;

    private String tokenize;

    private List<Ware> wareList;
    private Map<Integer, String> categoryMap;
    private List<WareCategory> categoryList;
    private Pager pager;

    public String listAll() throws Exception {
        pager.setPerPage(24);
        pager.setAction("ware_list_all.action");
        pager.setTotalItems(wareService.getCount(status));
        pager.generatePageData();
        wareList = wareService.list(status, pager.getOffsetItems(), pager
                .getPerPage());
        return SUCCESS;
    }

    public String listByCategory() throws Exception {
        WareCategory category;
        categoryList = wareCategoryService.list();
        category = new WareCategory();
        category.setName("无分组");
        categoryList.add(category);
        category = wareCategoryService.getWareCategoryById(categoryId);
        if (category != null) {
            wareList = wareService.listByCategory(category, status);
        } else {
            wareList = wareService.listByCategory(new WareCategory(), status);
        }
        return SUCCESS;
    }

    public String listHided() throws Exception {
        pager.setTotalItems(wareService.getCount(status));
        wareList = wareService.list(status, 0, pager.getTotalItems());
        return SUCCESS;
    }

    public String get() throws Exception {
        try {
            DecimalFormat f = new DecimalFormat("###0.00");
            Ware w = wareService.getWareById(id);
            categoryMap = wareCategoryService.getMap();
            if (w != null) {
                categoryId = w.getCategoryId();
                name = w.getName();
                barcode = w.getBarcode();
                cost = f.format(w.getCost());
                price = f.format(w.getPrice());
                number = String.valueOf(w.getNumber());
                status = w.getStatus();
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
            return ERROR;
        }
        return SUCCESS;
    }

    public String delete() throws Exception {
        try {
            Ware ware = wareService.getWareById(id);
            ware.setStatus(1);
            wareService.updateWare(ware);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return ERROR;
        }
        return SUCCESS;
    }

    public String parseToken() throws Exception {
        List<String> tokenList = keywordService.parseToken(name);
        StringBuffer tokenBuf = new StringBuffer();
        for (String token : tokenList) {
            tokenBuf.append(token);
            tokenBuf.append(" ");
        }
        tokenize = tokenBuf.toString().trim();
        return SUCCESS;
    }

    @Override
    public String execute() throws Exception {
        try {
            Ware obj = (id > 0) ? wareService.getWareById(id) : new Ware();
            obj.setCategoryId(categoryId);
            obj.setName(name.trim());
            obj.setBarcode(barcode.trim());
            obj.setStatus(status);
            if (cost.isEmpty() == false) {
                obj.setCost(Double.parseDouble(cost));
            }
            if (number.isEmpty() == false) {
                obj.setNumber(Integer.parseInt(number));
            }
            if (price.isEmpty() == false) {
                obj.setPrice(Double.parseDouble(price));
            }

            if (id > 0) {
                wareService.updateWare(obj);
            } else {
                id = wareService.createWare(obj);
            }
            if (tokenize != null && tokenize.isEmpty() == false) {
                keywordService.saveTokens(tokenize);
                wareService.updateFullTextIndex(obj, tokenize);
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
            return ERROR;
        }
        return SUCCESS;
    }

    public void setWareService(WareService service) {
        wareService = service;
    }

    public void setWareCategoryService(WareCategoryService wareCategoryService) {
        this.wareCategoryService = wareCategoryService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Ware> getWareList() {
        return wareList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public WareService getWareService() {
        return wareService;
    }

    public void setWareList(List<Ware> wareList) {
        this.wareList = wareList;
    }

    public Map<Integer, String> getCategoryMap() {
        return categoryMap;
    }

    public List<WareCategory> getCategoryList() {
        return categoryList;
    }

    public Pager getPager() {
        return pager;
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }

    public int getCurrentPage() {
        return pager.getCurrentPage();
    }

    public void setCurrentPage(int page) {
        pager.setCurrentPage(page);
    }

    public void setKeywordService(KeywordService keywordService) {
        this.keywordService = keywordService;
    }

    public String getTokenize() {
        return tokenize;
    }

    public void setTokenize(String tokenize) {
        this.tokenize = tokenize;
    }

}
