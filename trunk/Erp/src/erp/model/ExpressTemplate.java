package erp.model;

public class ExpressTemplate {

	private String dateFormat;
	private ExpressPos size;
	private ExpressPos date;
	private ExpressPos srcName;
	private ExpressPos srcAddressLB;
	private ExpressPos srcAddressRT;
	private int srcAddressIndent = 0;
	private ExpressPos srcPhone;
	private ExpressPos srcPostCode;
	private ExpressPos dstName;
	private ExpressPos dstAddressLB;
	private ExpressPos dstAddressRT;
	private int dstAddressIndent = 0;
	private ExpressPos dstPhone1;
	private ExpressPos dstPhone2;
	private ExpressPos dstPostCode;

	public String getDateFormat() {
		return dateFormat;
	}

	public int getSrcAddressIndent() {
		return srcAddressIndent;
	}

	public void setSrcAddressIndent(int srcAddressIndent) {
		this.srcAddressIndent = srcAddressIndent;
	}

	public int getDstAddressIndent() {
		return dstAddressIndent;
	}

	public void setDstAddressIndent(int dstAddressIndent) {
		this.dstAddressIndent = dstAddressIndent;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public ExpressPos getSize() {
		return size;
	}

	public void setSize(ExpressPos size) {
		this.size = size;
	}

	public ExpressPos getDate() {
		return date;
	}

	public void setDate(ExpressPos date) {
		this.date = date;
	}

	public ExpressPos getSrcName() {
		return srcName;
	}

	public void setSrcName(ExpressPos srcName) {
		this.srcName = srcName;
	}

	public ExpressPos getSrcPhone() {
		return srcPhone;
	}

	public void setSrcPhone(ExpressPos srcPhone) {
		this.srcPhone = srcPhone;
	}

	public ExpressPos getSrcPostCode() {
		return srcPostCode;
	}

	public void setSrcPostCode(ExpressPos srcPostCode) {
		this.srcPostCode = srcPostCode;
	}

	public ExpressPos getDstName() {
		return dstName;
	}

	public void setDstName(ExpressPos dstName) {
		this.dstName = dstName;
	}

	public ExpressPos getDstPhone1() {
		return dstPhone1;
	}

	public void setDstPhone1(ExpressPos dstPhone1) {
		this.dstPhone1 = dstPhone1;
	}

	public ExpressPos getDstPhone2() {
		return dstPhone2;
	}

	public void setDstPhone2(ExpressPos dstPhone2) {
		this.dstPhone2 = dstPhone2;
	}

	public ExpressPos getDstPostCode() {
		return dstPostCode;
	}

	public void setDstPostCode(ExpressPos dstPostCode) {
		this.dstPostCode = dstPostCode;
	}

	public ExpressPos getSrcAddressLB() {
		return srcAddressLB;
	}

	public void setSrcAddressLB(ExpressPos srcAddressLB) {
		this.srcAddressLB = srcAddressLB;
	}

	public ExpressPos getSrcAddressRT() {
		return srcAddressRT;
	}

	public void setSrcAddressRT(ExpressPos srcAddressRT) {
		this.srcAddressRT = srcAddressRT;
	}

	public ExpressPos getDstAddressLB() {
		return dstAddressLB;
	}

	public void setDstAddressLB(ExpressPos dstAddressLB) {
		this.dstAddressLB = dstAddressLB;
	}

	public ExpressPos getDstAddressRT() {
		return dstAddressRT;
	}

	public void setDstAddressRT(ExpressPos dstAddressRT) {
		this.dstAddressRT = dstAddressRT;
	}

}
