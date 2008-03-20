package rax.service;

import java.util.List;

import rax.dao.ArticleCategoryDao;
import rax.dao.ArticleDao;
import rax.model.ArticleCategory;

public class ArticleCategoryService {

    private ArticleDao articleDao;
    private ArticleCategoryDao categoryDao;

    public ArticleCategoryService() {
    }

    public int createCategory(ArticleCategory category) {
        return categoryDao.create(category);
    }

    public boolean deleteCategory(int id) {
        ArticleCategory category = categoryDao.read(id);

        if (null == category)
            return false;

        List<ArticleCategory> subCategoryList = categoryDao
                .listAllSubCategoryByCategoryId(category.getId());
        subCategoryList.add(category);

        for (ArticleCategory subCategory : subCategoryList) {
            articleDao.deleteByCategoryId(subCategory.getId());
            categoryDao.delete(subCategory);
        }

        return true;
    }

    public boolean updateCategory(int id, ArticleCategory category) {
        categoryDao.update(category);
        return true;
    }

    public int getCount(boolean onlyPub) {
        return categoryDao.count();
    }

    public List<ArticleCategory> listCategorys(int index, int num) {
        return categoryDao.list(index, num);
    }

    public List<ArticleCategory> listCategorysByCategory(
            ArticleCategory category, int index, int num) {
        return categoryDao.listByCategoryId(category.getId(), index, num);
    }

    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    public void setCategoryDao(ArticleCategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

}
