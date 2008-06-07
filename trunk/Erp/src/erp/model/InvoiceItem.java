package erp.model;

import java.util.Date;

public class InvoiceItem {

    private int number;
    private Date date;
    private String name;
    private String byerId;
    private String byerName;
     
    public InvoiceItem() {
        number = 0;
        date = new Date();
        name = "";
        byerId = "";
        byerName = "";
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getByerId() {
        return byerId;
    }

    public void setByerId(String byerId) {
        this.byerId = byerId;
    }

    public String getByerName() {
        return byerName;
    }

    public void setByerName(String byerName) {
        this.byerName = byerName;
    }

}
