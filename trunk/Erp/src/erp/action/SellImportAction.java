package erp.action;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

import erp.model.Sell;
import erp.service.ExpressService;
import erp.service.SellService;

public class SellImportAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger
			.getLogger(SellImportAction.class);

	private SellService sellService = null;
	private ExpressService expressService = null;

	private int sellId;
	private String content;
	private String fee;
	private String feeReal;
	private int expressId;
	private String commentExpress;
	private String commentInvoice;
	private String sender;
	private String date;

	List<String> dateSel;
	Map<Integer, String> expressSel;

	public String input() throws Exception {
		try {
			Date d = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime(d);

			if (c.get(Calendar.HOUR_OF_DAY) > 18) {
				c.setTimeInMillis(d.getTime() + 86400000);
				d = c.getTime();
			}
			date = df.format(d);

			Date d1 = new Date();
			c.setTimeInMillis(d1.getTime() + 86400000);
			Date d2 = c.getTime();
			c.setTimeInMillis(d2.getTime() + 86400000);
			Date d3 = c.getTime();

			dateSel = new ArrayList<String>();
			dateSel.add(df.format(d1));
			dateSel.add(df.format(d2));
			dateSel.add(df.format(d3));

			fee = "5";
			feeReal = "4";
			expressId = 0;
			expressSel = expressService.getExpressSel();
		} catch (Exception ex) {
			logger.error(ex.toString());
			return ERROR;
		}
		return SUCCESS;
	}

	@Override
	public String execute() throws Exception {
		try {
			Sell obj = (sellId > 0) ? sellService.getSellById(sellId)
					: new Sell();

			String[] info = content.split("\n");
			for (int i = 0; i < info.length; i++) {
				logger.info("info[" + i + "]:" + info[i]);
			}

			obj.setCustomerName(info[0].substring(info[0].indexOf("：") + 1).trim());
			obj.setCustomerAddress(info[1].substring(info[1].indexOf("：") + 1).trim());
			obj.setCustomerPostCode(info[2].substring(info[2].indexOf("：") + 1).trim());
			String[] phoneList = info[3].substring(info[3].indexOf("：") + 1).trim()
					.split(" ", 2);
			
			obj.setCustomerPhone1(phoneList[0].trim());
			obj.setCustomerPhone2(phoneList.length > 1 ? phoneList[1].trim() : "");

			obj.setExpressId(expressId);
			obj.setCommentExpress(commentExpress);
			obj.setCommentInvoice(commentInvoice);
			obj.setSender(sender);

			if (fee != null && fee.isEmpty() == false) {
				obj.setFee(Double.parseDouble(fee));
			}
			if (feeReal != null && feeReal.isEmpty() == false) {
				obj.setFeeReal(Double.parseDouble(feeReal));
			}

			if (date != null) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				obj.setSendDate(df.parse(date));
			}

			logger.info("Name:" + obj.getCustomerName());
			logger.info("Phone1:" + obj.getCustomerPhone1());
			logger.info("Phone2:" + obj.getCustomerPhone2());
			logger.info("Address:" + obj.getCustomerAddress());
			logger.info("PostCode:" + obj.getCustomerPostCode());

			if (sellId > 0) {
				sellService.updateSell(obj);
			} else if (sellId == 0) {
				sellId = sellService.createSell(obj);
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
			return ERROR;
		}
		return SUCCESS;
	}

	public static void main(String[] args) throws Exception {
		char[] cbuf = new char[1024];
		StringBuffer buf = new StringBuffer();
		InputStreamReader is = new InputStreamReader(new FileInputStream(
				"D:/address.txt"));
		int size;
		while ((size = is.read(cbuf)) != -1) {
			buf.append(cbuf, 0, size);
		}

		SellImportAction action = new SellImportAction();
		action.content = buf.toString();
		action.sellId = -1;
		action.execute();
	}

	public SellService getSellService() {
		return sellService;
	}

	public void setSellService(SellService sellService) {
		this.sellService = sellService;
	}

	public int getSellId() {
		return sellId;
	}

	public void setSellId(int sellId) {
		this.sellId = sellId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getFeeReal() {
		return feeReal;
	}

	public void setFeeReal(String feeReal) {
		this.feeReal = feeReal;
	}

	public int getExpressId() {
		return expressId;
	}

	public void setExpressId(int expressId) {
		this.expressId = expressId;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public Map<Integer, String> getExpressSel() {
		return expressSel;
	}

	public ExpressService getExpressService() {
		return expressService;
	}

	public void setExpressService(ExpressService expressService) {
		this.expressService = expressService;
	}

	public String getCommentExpress() {
		return commentExpress;
	}

	public void setCommentExpress(String commentExpress) {
		this.commentExpress = commentExpress;
	}

	public String getCommentInvoice() {
		return commentInvoice;
	}

	public void setCommentInvoice(String commentInvoice) {
		this.commentInvoice = commentInvoice;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<String> getDateSel() {
		return dateSel;
	}

	public void setDateSel(List<String> dateSel) {
		this.dateSel = dateSel;
	}
}
