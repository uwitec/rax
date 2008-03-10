package rax.action;

import java.util.List;

import org.apache.log4j.Logger;

import rax.model.Link;
import rax.model.LinkCategory;
import rax.service.LinkCategoryService;
import rax.service.LinkService;

import com.opensymphony.xwork2.ActionSupport;

public class ListLinkAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(ListLinkAction.class);

    private List<Link> links;
    private List<LinkCategory> categorys;

    private int linkNum = 0;
    private int categoryId = 0;
    private LinkCategory category = null;

    @Override
    public String execute() throws Exception {

        LinkCategoryService categoryService = new LinkCategoryService();
        LinkService linkService = new LinkService();

        categorys = categoryService.listAllCategorys();
        category = categoryService.getCategoryById(categoryId);
        try {
            
            logger.trace(String.valueOf(category.getId()));
            logger.trace(category.getTitle());
            logger.trace(category.getSummary());
            
            linkNum = (int) linkService.getCountByCategory(category, false);
            links = linkService
                    .listLinksByCategory(category, 0, linkNum, false);
        } catch (Exception ex) {
            logger.error(ex.toString());
        }

        return SUCCESS;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getLinkNum() {
        return linkNum;
    }

    public void setLinkNum(int linkNum) {
        this.linkNum = linkNum;
    }

    public List<Link> getLinks() {
        return links;
    }

    public List<LinkCategory> getCategorys() {
        return categorys;
    }

    public LinkCategory getCategory() {
        return category;
    }

}
