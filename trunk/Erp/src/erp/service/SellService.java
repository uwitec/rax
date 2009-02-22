package erp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import erp.dao.SellDao;
import erp.model.Sell;

public class SellService {

	private SellDao sellDao;
	private Map<Integer, String> IMTypeSel;

	public SellService() {
		IMTypeSel = new HashMap<Integer, String>(8);
		IMTypeSel.put(0, "旺旺");
		IMTypeSel.put(1, "QQ");
		IMTypeSel.put(2, "易趣通");
		IMTypeSel.put(3, "MSN");
		IMTypeSel.put(-1, "其他");
	}

	public Map<Integer, String> getIMTypeSel() {
		return IMTypeSel;
	}

	public Sell getSellById(int id) {
		return sellDao.read(id);
	}

	public int createSell(Sell obj) {
		return sellDao.create(obj);
	}

	public boolean deleteSell(Sell obj) {
		boolean ret = false;
		if (null != obj) {
			sellDao.delete(obj);
			ret = true;
		}
		return ret;
	}

	public boolean deleteSellById(int id) {
		boolean ret = false;
		Sell obj = sellDao.read(id);
		if (null != obj) {
			sellDao.delete(obj);
			ret = true;
		}
		return ret;
	}

	public boolean updateSell(Sell obj) {
		sellDao.update(obj);
		return true;
	}

	public int getCount(int status) {
		return sellDao.count(status);
	}

	public List<Sell> list(int index, int num, int status) {
		return sellDao.list(index, num, status);
	}

	public List<Sell> findByKeyword(String keyword) {
		return sellDao.findByKeyword(keyword.toLowerCase());
	}

	public void setSellDao(SellDao dao) {
		sellDao = dao;
	}

}
