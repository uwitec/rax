package rax.model;

import java.util.Date;

public class ArticleCategory {

    private int id;
    private int parentId;

    private String name;
    private String summary;

    private int lthread;
    private int rthread;

    private Date createDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getLthread() {
        return lthread;
    }

    public void setLthread(int lthread) {
        this.lthread = lthread;
    }

    public int getRthread() {
        return rthread;
    }

    public void setRthread(int rthread) {
        this.rthread = rthread;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}
