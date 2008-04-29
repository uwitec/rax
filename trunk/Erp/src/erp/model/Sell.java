package erp.model;

import java.util.Date;

public class Sell {

    private int id;
    private String customerName;
    private String customerAddress;
    private String customerPhone1;
    private String customerPhone2;
    private String customerPostCode;
    private String customerWangwang;
    private double fee;
    private double feeReal;
    private Date createDate;
    private boolean print;
    private int expressId;
    private String expressBarcode;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhone1() {
        return customerPhone1;
    }

    public void setCustomerPhone1(String customerPhone1) {
        this.customerPhone1 = customerPhone1;
    }

    public String getCustomerPhone2() {
        return customerPhone2;
    }

    public void setCustomerPhone2(String customerPhone2) {
        this.customerPhone2 = customerPhone2;
    }

    public String getCustomerPostCode() {
        return customerPostCode;
    }

    public void setCustomerPostCode(String customerPostCode) {
        this.customerPostCode = customerPostCode;
    }

    public String getCustomerWangwang() {
        return customerWangwang;
    }

    public void setCustomerWangwang(String customerWangwang) {
        this.customerWangwang = customerWangwang;
    }

    public int getExpressId() {
        return expressId;
    }

    public void setExpressId(int expressId) {
        this.expressId = expressId;
    }

    public String getExpressBarcode() {
        return expressBarcode;
    }

    public void setExpressBarcode(String expressBarcode) {
        this.expressBarcode = expressBarcode;
    }

    public double getFeeReal() {
        return feeReal;
    }

    public void setFeeReal(double feeReal) {
        this.feeReal = feeReal;
    }

    public boolean isPrint() {
        return print;
    }

    public void setPrint(boolean print) {
        this.print = print;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
