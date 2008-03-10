package rax.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import rax.model.Link;
import rax.model.LinkCategory;

import com.opensymphony.xwork2.ActionSupport;

public class ListLinkActionTest extends ActionSupport {
    
    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(ListLinkAction.class);
    
    private List<Link> links;
    private List<LinkCategory> categorys;

    private int linkNum = 0;
    private int categoryId = 0;
    private LinkCategory category = null;

    public ListLinkActionTest() {

        categorys = new ArrayList<LinkCategory>();
        links = new ArrayList<Link>();

        LinkCategory category = null;

        for (int i = 1; i <= 3; i++) {
            category = new LinkCategory();
            category.setId(i);
            category.setTitle("Title" + String.valueOf(i));
            category.setSummary("Summary" + String.valueOf(i));
            categorys.add(category);
        }

    }

    @Override
    public String execute() throws Exception {

        LinkCategory c = null;
        for (Iterator<LinkCategory> it = categorys.iterator(); it.hasNext();) {
            c = it.next();
            if (c.getId() == categoryId) {
                category = c;
                break;
            }
        }

        try {
            Link link = null;
            for (int i = 1; i <= 5; i++) {
                link = new Link();
                link.setId(category.getId() * 10 +  i);
                link.setAddress("address/" + String.valueOf(i));
                link.setCategoryId(category.getId());
                link.setPub(i % 2 == 1);
                link.setSummary("Summary" + String.valueOf(i));
                link.setTitle("Title" + String.valueOf(i));
                links.add(link);
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
        }

        linkNum = links.size();

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
