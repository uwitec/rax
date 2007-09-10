package sid.service;

import java.util.List;

import sid.model.*;
import sid.persistence.*;
import sid.persistence.dao.*;


public class LinkCategoryService {

	private LinkDao linkDao;
	private LinkCategoryDao categoryDao;

	public LinkCategoryService() {
		linkDao = new LinkDao();
		categoryDao = new LinkCategoryDao();
	}

	public LinkCategory getCategoryById(long id) {
		return categoryDao.read(id);
	}

	public long createCategory(LinkCategory category) {
		return categoryDao.create(category);
	}

	public boolean deleteCategory(long id) {
		LinkCategory category = categoryDao.read(id);
		boolean bFlag = linkDao.deleteByCategoryId(category.getId());
		return (null != category && bFlag) ? categoryDao.delete(category)
				: false;
	}

	public boolean updateCategory(long id, LinkCategory category) {
		return categoryDao.update(category);
	}

	public long getCount(boolean onlyPub) {
		return categoryDao.count();
	}

	public List<LinkCategory> listAllCategorys() {
		return categoryDao.listAll();
	}

}
