package rax.service;

import java.util.List;

import rax.dao.ArticleDao;
import rax.model.Article;
import rax.model.ArticleCategory;

public class ArticleService {

    private ArticleDao articleDao;

    public ArticleService() {
    }

    public Article getArticleById(int id) {
        return articleDao.read(id);
    }

    public int createArticle(Article article) {
        return articleDao.create(article);
    }

    public boolean deleteArticle(int id) {
        Article article = articleDao.read(id);
        if (null != article)
            articleDao.delete(article);
        return true;
    }

    public boolean updateArticle(Article article) {
        articleDao.update(article);
        return true;
    }

    public int getCount(boolean onlyPub) {
        return articleDao.count(onlyPub);
    }

    public int getCountByCategory(ArticleCategory category, boolean onlyPub) {
        return articleDao.countByCategoryId(category.getId(), onlyPub);
    }

    public List<Article> listArticlesByCategory(ArticleCategory category,
            int index, int num, boolean onlyPub) {
        return articleDao.listByCategoryId(category.getId(), index, num,
                onlyPub);
    }

    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

}
