package erp.action;

import java.util.ArrayList;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.Order;
import com.taobao.api.domain.Trade;
import com.taobao.api.domain.User;
import com.taobao.api.request.TradeFullinfoGetRequest;
import com.taobao.api.request.TradesSoldGetRequest;
import com.taobao.api.request.UserGetRequest;
import com.taobao.api.response.TradeFullinfoGetResponse;
import com.taobao.api.response.TradesSoldGetResponse;
import com.taobao.api.response.UserGetResponse;

import erp.bean.TaobaoBean;

public class TradeImportAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger
			.getLogger(TradeImportAction.class);

	private TaobaoBean taobao;
	private Map session;

	// list()
	ArrayList<Trade> trades;

	// session_callback()
	private String top_appkey;
	private String top_parameters;
	private String top_session;
	private String top_sign;
	
	// input()
	private String tids;

	public String list() throws Exception {
		try {
			TaobaoClient client = new DefaultTaobaoClient(taobao.getRestUrl(),
					taobao.getAppKey(), taobao.getAppSecret());

			// getUser(client, "timonlio");
			printSession();

			TradesSoldGetRequest tradeSoldReq = new TradesSoldGetRequest();
			tradeSoldReq.setStatus("WAIT_SELLER_SEND_GOODS");
			tradeSoldReq.setFields("tid,"
					+ "created,"
					+ "buyer_nick,"
					+ "total_fee,"
					+ "discount_fee," 
					+ "adjust_fee,"
					+ "post_fee,"
					+ "payment,"
					+ "receiver_name,"
					+ "receiver_address,"
					+ "receiver_city,"
					+ "receiver_district,"
					+ "receiver_mobile,"
					+ "receiver_phone,"
					+ "receiver_state,"
					+ "receiver_zip,"
					+ "orders");

			TradesSoldGetResponse tradeSoldResp = client.execute(tradeSoldReq, (String)session.get("sess_top_session"));
			logger.info("totalResults:" + tradeSoldResp.getTotalResults());

			if (tradeSoldResp.getTotalResults() > 0) {
				this.trades = (ArrayList<Trade>) tradeSoldResp.getTrades();
				for (Trade t : trades) {
					logger.info("trade.buyer_nick:" + t.getBuyerNick());
					logger.info("trade.created:" + t.getCreated());
					logger.info("trade.tid:" + t.getTid());
					logger.info("trade.total_fee:" + t.getTotalFee());
					logger.info("trade.discount_fee:" + t.getDiscountFee());
					logger.info("trade.adjust_fee:" + t.getAdjustFee());
					logger.info("trade.post_fee:" + t.getPostFee());
					logger.info("trade.payment:" + t.getPayment());
					
					logger.info("trade.receiver_name:" + t.getReceiverName());
					logger.info("trade.receiver_address:" + t.getReceiverAddress());
					logger.info("trade.receiver_city:" + t.getReceiverCity());
					logger.info("trade.receiver_district:" + t.getReceiverDistrict());
					logger.info("trade.receiver_mobile:" + t.getReceiverMobile());
					logger.info("trade.receiver_phone:" + t.getReceiverPhone());
					logger.info("trade.receiver_state:" + t.getReceiverState());
					logger.info("trade.receiver_zip:" + t.getReceiverZip());

					ArrayList<Order> orders = (ArrayList<Order>) t.getOrders();
					logger.info("trade.orders.length:" + orders.size());

					for (Order o : orders) {
						logger.info("order.title:" + o.getTitle());
						logger.info("order.num:" + o.getNum());
						logger.info("order.price:" + o.getPrice());
						logger.info("order.num_iid:" + o.getNumIid());
						logger.info("order.outer_iid:" + o.getOuterIid());
						logger.info("order.outer_sku_id:" + o.getOuterSkuId());
						logger.info("order.total_fee:" + o.getTotalFee());
						logger.info("order.discount_fee:" + o.getDiscountFee());
						logger.info("order.adjust_fee:" + o.getAdjustFee());
						logger.info("order.payment:" + o.getPayment());
					}
					logger.info("");
				}
			}

			// TradeGetRequest tradeReq = new TradeGetRequest();
			// tradeReq.setFields("");
			// tradeReq.setTid(tid);

		} catch (Exception ex) {
			logger.error(ex.toString());
			this.trades = null;
			return LOGIN;
		}
		return SUCCESS;
	}

	public String input() throws Exception {
		try {
			TradeFullinfoGetRequest tradeReq;
			TradeFullinfoGetResponse tradeResp;
			Trade trade;
			TaobaoClient client = new DefaultTaobaoClient(taobao.getRestUrl(),
					taobao.getAppKey(), taobao.getAppSecret());
			
			String[] tids = this.tids.split(",");
			
			for (String tid : tids) {
				tradeReq = new TradeFullinfoGetRequest();
				tradeReq.setTid(Long.parseLong(tid));
				tradeReq.setFields("tid,"
						+ "created,"
						+ "buyer_nick,"
						+ "total_fee,"
						+ "discount_fee," 
						+ "adjust_fee,"
						+ "post_fee,"
						+ "payment,"
						+ "buyer_message,"
						+ "buyer_memo,"
						+ "seller_memo,"
						+ "receiver_name,"
						+ "receiver_address,"
						+ "receiver_city,"
						+ "receiver_district,"
						+ "receiver_mobile,"
						+ "receiver_phone,"
						+ "receiver_state,"
						+ "receiver_zip,"
						+ "orders");
				
				tradeResp = client.execute(tradeReq, (String)session.get("sess_top_session"));
				trade = tradeResp.getTrade();
				logger.info("trade.created:" + trade.getCreated());
				logger.info("trade.tid:" + trade.getTid());
				logger.info("trade.buyer_nick:" + trade.getBuyerNick());
				logger.info("trade.total_fee:" + trade.getTotalFee());
				logger.info("trade.discount_fee:" + trade.getDiscountFee());
				logger.info("trade.adjust_fee:" + trade.getAdjustFee());
				logger.info("trade.post_fee:" + trade.getPostFee());
				logger.info("trade.payment:" + trade.getPayment());
				logger.info("trade.buyer_message:" + trade.getBuyerMessage());
				logger.info("trade.buyer_memo:" + trade.getBuyerMemo());
				logger.info("trade.seller_memo:" + trade.getSellerMemo());
				logger.info("trade.receiver_name:" + trade.getReceiverName());
				logger.info("trade.receiver_address:" + trade.getReceiverAddress());
				logger.info("trade.receiver_city:" + trade.getReceiverCity());
				logger.info("trade.receiver_district:" + trade.getReceiverDistrict());
				logger.info("trade.receiver_mobile:" + trade.getReceiverMobile());
				logger.info("trade.receiver_phone:" + trade.getReceiverPhone());
				logger.info("trade.receiver_state:" + trade.getReceiverState());
				logger.info("trade.receiver_zip:" + trade.getReceiverZip());
				
				ArrayList<Order> orders = (ArrayList<Order>) trade.getOrders();
				logger.info("trade.orders.length:" + orders.size());
				for (Order o : orders) {
					logger.info("order.title:" + o.getTitle());
					logger.info("order.num:" + o.getNum());
					logger.info("order.price:" + o.getPrice());
					logger.info("order.num_iid:" + o.getNumIid());
					logger.info("order.outer_iid:" + o.getOuterIid());
					logger.info("order.outer_sku_id:" + o.getOuterSkuId());
					logger.info("order.total_fee:" + o.getTotalFee());
					logger.info("order.discount_fee:" + o.getDiscountFee());
					logger.info("order.adjust_fee:" + o.getAdjustFee());
					logger.info("order.payment:" + o.getPayment());
				}
				logger.info("");
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
			return ERROR;
		}
		return SUCCESS;
	}

	public String session_callback() throws Exception {

		logger.info("top_appkey:" + this.top_appkey);
		logger.info("top_parameters:" + this.top_parameters);
		logger.info("top_session:" + this.top_session);
		logger.info("top_sign:" + this.top_sign);

		this.session.put("sess_top_appkey", this.top_appkey);
		this.session.put("sess_top_parameters", this.top_parameters);
		this.session.put("sess_top_session", this.top_session);
		this.session.put("sess_top_sign", this.top_sign);

		return SUCCESS;
	}

	@Override
	public String execute() throws Exception {
		try {
			printSession();
		} catch (Exception ex) {
			logger.error(ex.toString());
			return ERROR;
		}
		return LOGIN;
	}

	private void getUser(TaobaoClient client, String nick) {
		try {
			UserGetRequest userReq = new UserGetRequest();
			userReq.setFields("nick,sex,buyer_credit,seller_credit,created,last_visit,alipay_account,email");
			userReq.setNick(nick);

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
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
	}

	private void printSession() {
		try {
			logger.info("sess_top_appkey:"
					+ this.session.get("sess_top_appkey"));
			logger.info("sess_top_parameters:"
					+ this.session.get("sess_top_parameters"));
			logger.info("sess_top_session:"
					+ this.session.get("sess_top_session"));
			logger.info("sess_top_sign:" + this.session.get("sess_top_sign"));
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
	}

	public static void main(String[] args) throws Exception {
		TradeImportAction action = new TradeImportAction();
		action.list();
	}

	public void setTaobao(TaobaoBean taobao) {
		this.taobao = taobao;
	}

	public TaobaoBean getTaobao() {
		return taobao;
	}

	public void setSession(Map session) {
		this.session = session;
	}

	public Map getSession() {
		return session;
	}

	public ArrayList<Trade> getTrades() {
		return trades;
	}

	public void setTop_appkey(String top_appkey) {
		this.top_appkey = top_appkey;
	}

	public void setTop_parameters(String top_parameters) {
		this.top_parameters = top_parameters;
	}

	public void setTop_session(String top_session) {
		this.top_session = top_session;
	}

	public void setTop_sign(String top_sign) {
		this.top_sign = top_sign;
	}

	public void setTids(String tids) {
		this.tids = tids;
	}

}
