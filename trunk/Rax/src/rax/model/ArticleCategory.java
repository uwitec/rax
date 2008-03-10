package rax.model;

import java.util.Date;

public class ArticleCategory {

    private long id;
    private long parentId;

    private String className;
    private String summary;

    private long lthread;
    private long rthread;

    private Date createDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentCid(long parentId) {
        this.parentId = parentId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public long getLthread() {
        return lthread;
    }

    public void setLthread(long lthread) {
        this.lthread = lthread;
    }

    public long getRthread() {
        return rthread;
    }

    public void setRthread(long rthread) {
        this.rthread = rthread;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}
