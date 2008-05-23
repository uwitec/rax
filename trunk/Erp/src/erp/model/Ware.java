package erp.model;

public class Ware {

    private int id;
    private String name;
    private double cost;
    private double lastCost;
    private double price;
    private String barcode;
    private int number;
    private int status;
    
    public Ware() {
        id = 0;
        name = "";
        cost = 0;
        lastCost = 0;
        price = 0;
        barcode = "";
        number = 0;
        status = 0;
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getLastCost() {
        return lastCost;
    }

    public void setLastCost(double lastCost) {
        this.lastCost = lastCost;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}
