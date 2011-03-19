package erp.action;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.Trade;
import com.taobao.api.domain.User;
import com.taobao.api.request.TradesSoldGetRequest;
import com.taobao.api.request.UserGetRequest;
import com.taobao.api.response.TradesSoldGetResponse;
import com.taobao.api.response.UserGetResponse;

import erp.bean.TaobaoBean;

public class TradeImportAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger
			.getLogger(TradeImportAction.class);

	private TaobaoBean taobao;
	
	// session_callback()
	private String top_appkey;
	private String top_parameters;
	private String top_session;
	private String top_sign;

	public String list() throws Exception {
		try {
			TaobaoClient client = new DefaultTaobaoClient(taobao.getRestUrl(),
					taobao.getAppKey(), taobao.getAppSecret());
			
			UserGetRequest userReq = new UserGetRequest();
			userReq.setFields("nick,sex,buyer_credit,seller_credit,created,last_visit,alipay_account,email");
			userReq.setNick("timonlio");
			UserGetResponse userResp = client.execute(userReq);
			User usr = userResp.getUser();
			logger.info("result:" + userResp.getBody());
			logger.info("nick:" + usr.getNick());
			logger.info("buyer_credit:" + usr.getBuyerCredit());
			logger.info("seller_credit:" + usr.getSellerCredit());
			logger.info("created:" + usr.getCreated());
			logger.info("last_visit:" + usr.getLastVisit());
			logger.info("alipay_account:" + usr.getAlipayAccount());
			logger.info("email:" + usr.getEmail());

			TradesSoldGetRequest tradeSoldReq = new TradesSoldGetRequest();
			tradeSoldReq.setFields("buyer_nick,title,created,price,num,tid,payment,num_iid,discount_fee,adjust_fee,status,total_fee,post_fee,orders,");
			TradesSoldGetResponse tradeSoldResp = client.execute(tradeSoldReq , top_session);
			ArrayList<Trade> trades = (ArrayList<Trade>) tradeSoldResp.getTrades();

			logger.info("totalResults:" + tradeSoldResp.getTotalResults());
			
			for (Trade t : trades) {
				logger.info("trades.title:" + t.getTitle());
				logger.info("trades.buyer_nick:" + t.getBuyerNick());
				logger.info("trades.created:" + t.getCreated());
				logger.info("trades.price:" + t.getPrice());
				logger.info("trades.num:" + t.getNum());
				logger.info("trades.tid:" + t.getTid());
				logger.info("trades.payment:" + t.getPayment());
				logger.info("trades.num_iid:" + t.getNumIid());
				logger.info("trades.discount_fee:" + t.getDiscountFee());
				logger.info("trades.adjust_fee:" + t.getAdjustFee());
				logger.info("trades.status:" + t.getStatus());
				logger.info("trades.total_fee:" + t.getTotalFee());
				logger.info("trades.post_fee:" + t.getPostFee());
			}
			
			// TradeGetRequest tradeReq = new TradeGetRequest();
			// tradeReq.setFields("");
			// tradeReq.setTid(tid);

		} catch (Exception ex) {
			logger.error(ex.toString());
			return LOGIN;
		}
		return SUCCESS;
	}

	public String input() throws Exception {
		try {
			logger.info("appkey:" + taobao.getAppKey());
			logger.info("appSecret:" + taobao.getAppSecret());
			logger.info("restUrl:" + taobao.getRestUrl());
		} catch (Exception ex) {
			logger.error(ex.toString());
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String session_callback() throws Exception {
		return SUCCESS;
	}

	@Override
	public String execute() throws Exception {
		try {

		} catch (Exception ex) {
			logger.error(ex.toString());
			return ERROR;
		}
		return SUCCESS;
	}

	public static void main(String[] args) throws Exception {
		TradeImportAction action = new TradeImportAction();
		action.list();
	}

	public TaobaoBean getTaobao() {
		return taobao;
	}

	public void setTaobao(TaobaoBean taobao) {
		this.taobao = taobao;
	}

	public String getTop_appkey() {
		return top_appkey;
	}

	public void setTop_appkey(String top_appkey) {
		this.top_appkey = top_appkey;
	}

	public String getTop_parameters() {
		return top_parameters;
	}

	public void setTop_parameters(String top_parameters) {
		this.top_parameters = top_parameters;
	}

	public String getTop_session() {
		return top_session;
	}

	public void setTop_session(String top_session) {
		this.top_session = top_session;
	}

	public String getTop_sign() {
		return top_sign;
	}

	public void setTop_sign(String top_sign) {
		this.top_sign = top_sign;
	}

}
