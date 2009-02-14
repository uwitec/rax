package erp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import erp.dao.ExpressDao;
import erp.model.Express;

public class ExpressService {

	private ExpressDao expressDao;

	public Map<Integer, String> getExpressSel() {
		Map<Integer, String> mapEx = new HashMap<Integer, String>(16);
		List<Express> lstEx = expressDao.list();
		for (Express ex : lstEx) {
			mapEx.put(ex.getId(), ex.getExName());
		}
		return mapEx;
	}

	public Express getExpress(int expressId) {
		return expressDao.read(expressId);
	}

	public ExpressDao getExpressDao() {
		return expressDao;
	}

	public void setExpressDao(ExpressDao expressDao) {
		this.expressDao = expressDao;
	}
}
