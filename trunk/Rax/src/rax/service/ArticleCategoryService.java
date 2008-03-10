package rax.service;

import java.util.List;

import rax.dao.hibernate.ArticleCategoryDao;
import rax.dao.hibernate.ArticleDao;
import rax.model.ArticleCategory;

public class ArticleCategoryService {

    private ArticleDao articleDao;
    private ArticleCategoryDao categoryDao;

    public ArticleCategoryService() {
        articleDao = new ArticleDao();
        categoryDao = new ArticleCategoryDao();
    }

    public long createCategory(ArticleCategory category) {
        return categoryDao.create(category);
    }

    public boolean deleteCategory(long id) {
        ArticleCategory category = categoryDao.read(id);

        if (null == category)
            return false;

        List<ArticleCategory> subCategoryList = categoryDao
                .listAllSubCategoryByCategoryId(category.getId());
        subCategoryList.add(category);

        boolean bArticleFlag = true;
        boolean bCategoryFlag = true;
        for (ArticleCategory subCategory : subCategoryList) {
            bArticleFlag = bArticleFlag
                    && articleDao.deleteByCategoryId(subCategory.getId());
            bCategoryFlag = bArticleFlag && bCategoryFlag
                    && categoryDao.delete(subCategory);
        }

        return bArticleFlag && bCategoryFlag;
    }

    public boolean updateCategory(long id, ArticleCategory category) {
        return categoryDao.update(category);
    }

    public long getCount(boolean onlyPub) {
        return categoryDao.count();
    }

    public List<ArticleCategory> listCategorys(int index, int num) {
        return categoryDao.list(index, num);
    }

    public List<ArticleCategory> listCategorysByCategory(
            ArticleCategory category, int index, int num) {
        return categoryDao.listByCategoryId(category.getId(), index, num);
    }

}
