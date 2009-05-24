package erp.action;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

import erp.model.Ware;
import erp.model.WareCategory;
import erp.service.KeywordService;
import erp.service.WareCategoryService;
import erp.service.WareService;

public class WareAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(WareAction.class);

	private WareService wareService = null;
	private WareCategoryService wareCategoryService = null;
	private KeywordService keywordService = null;

	private int id;
	private int categoryId = 0;
	private Ware ware;
	private int status = 0;
	private String tokenize;
	private String keyword;

	private List<Ware> wareList;
	private Map<Integer, String> categoryMap;
	private List<WareCategory> categoryList;

	public String listByCategory() throws Exception {
		categoryList = wareCategoryService.list();
		wareList = wareService.listByCategoryId(categoryId, status);
		return SUCCESS;
	}

	public String listHided() throws Exception {
		int num = wareService.getCount(status);
		wareList = wareService.list(status, 0, num);
		return SUCCESS;
	}

	public String get() throws Exception {
		try {
			ware = wareService.getWareById(id);
			categoryMap = wareCategoryService.getMap();
		} catch (Exception ex) {
			logger.error(ex.toString());
			return ERROR;
		}
		return SUCCESS;
	}

	public String delete() throws Exception {
		try {
			// Just hide it, sell history was using them
			Ware obj = wareService.getWareById(id);
			obj.setStatus(1);
			wareService.updateWare(obj);
		} catch (Exception ex) {
			logger.error(ex.toString());
			return ERROR;
		}
		return SUCCESS;
	}

	public String updateAlarm() throws Exception {
		try {
			Ware obj = wareService.getWareById(id);
			if (obj != null) {
				obj.setNumberAlarmEnable(0);
				wareService.updateWare(obj);
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
			return ERROR;
		}
		return SUCCESS;
	}

	@Override
	public String execute() throws Exception {
		try {
			Ware obj = (id > 0) ? wareService.getWareById(id) : new Ware();
			obj.setCategoryId(categoryId);
			obj.setName(ware.getName().trim());
			obj.setBarcode(ware.getBarcode().trim());
			obj.setCost(ware.getCost());
			obj.setNumber(ware.getNumber());
			obj.setLastPrice(ware.getLastPrice());
			obj.setNumberAlarm(ware.getNumberAlarm());
			obj.setNumberAlarmEnable(ware.getNumberAlarmEnable());
			obj.setId(id);
			// Do not set obj.Status

			if (id > 0) wareService.updateWare(obj);
			else id = wareService.createWare(obj);
			
			if (tokenize != null && tokenize.isEmpty() == false) {
				keywordService.saveTokens(tokenize);
				wareService.updateFullTextIndex(obj, tokenize);
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
			return ERROR;
		}
		return (status > 0) ? "success_hide" : SUCCESS;
	}

    public String keywordSearch() throws Exception {
        if ((keyword != null) && keyword.isEmpty() == false) {
            logger.debug("findByKeywords:" + keyword);
            wareList = wareService.findByKeywords(keyword);
        }
        return SUCCESS;
    }
    
    public String fullTextSearch() throws Exception {
    	// AJAX
        logger.debug("fullTextSearch:" + keyword);
        List<String> tokenList = keywordService.parseToken(keyword);
        StringBuffer tokenBuf = new StringBuffer();
        boolean skipFirstToken = true;
        for (String token : tokenList) {
            if (skipFirstToken == false) {
                tokenBuf.append("|");
            }
            tokenBuf.append(token);
            skipFirstToken = false;
        }
        wareList = wareService.fullTextSearch(tokenBuf.toString().trim(), 0);
        return SUCCESS;
    }
	
	public void setWareService(WareService service) {
		wareService = service;
	}

	public void setWareCategoryService(WareCategoryService wareCategoryService) {
		this.wareCategoryService = wareCategoryService;
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

	public void setKeywordService(KeywordService keywordService) {
		this.keywordService = keywordService;
	}

	public String getTokenize() {
		return tokenize;
	}

	public void setTokenize(String tokenize) {
		this.tokenize = tokenize;
	}

	public void setWare(Ware ware) {
		this.ware = ware;
	}

	public Ware getWare() {
		return ware;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}
