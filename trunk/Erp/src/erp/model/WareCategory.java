package erp.model;

public class WareCategory {

    private int id;
    private String name;
    private boolean hide;
    
    public WareCategory() {
        id = 0;
        name = "";
        hide = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

}
