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

    public ArticleCategory getCategoryById(ArticleCategory category) {
        return categoryDao.read(category.getId());
    }

    public int createCategory(ArticleCategory category) {
        ArticleCategory parent = categoryDao.read(category.getParentId());
        int thread = parent.getRthread();
        category.setLthread(thread);
        category.setRthread(thread + 1);
        categoryDao.adjustLthread(thread, 2);
        categoryDao.adjustRthread(thread, 2);
        return categoryDao.create(category);
    }

    public boolean deleteCategory(int id) {
        ArticleCategory category = categoryDao.read(id);
        if (null != category) {
            int thread = category.getLthread();
            int diff = thread - category.getRthread();
            List<ArticleCategory> subCategoryList = categoryDao
                    .listSubCategoryByCategoryId(category.getId());
            subCategoryList.add(category);
            for (ArticleCategory subCategory : subCategoryList) {
                articleDao.deleteByCategoryId(subCategory.getId());
                categoryDao.delete(subCategory);
            }
            categoryDao.adjustLthread(thread, -diff);
            categoryDao.adjustRthread(thread, -diff);
            return true;
        }
        return false;
    }

    public boolean updateCategory(int id, ArticleCategory category) {
        categoryDao.update(category);
        return true;
    }

    public int countChildByCategoryId(int id) {
        return categoryDao.countChildCategoryByCategoryId(id);
    }

    public int countSubCategoryByCategoryId(int id) {
        return categoryDao.countSubCategoryByCategoryId(id);
    }

    public List<ArticleCategory> listAllCategorys() {
        return categoryDao.listAll();
    }

    public List<ArticleCategory> listChildCategorys(ArticleCategory category) {
        return categoryDao.listChildByCategoryId(category.getId());
    }

    public List<ArticleCategory> listSubCategorys(ArticleCategory category) {
        return categoryDao.listSubCategoryByCategoryId(category.getId());
    }

    public List<ArticleCategory> listPathToCategory(ArticleCategory category) {
        return categoryDao.listPathToCategory(category.getLthread(), category
                .getRthread());
    }

    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    public void setCategoryDao(ArticleCategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

}
