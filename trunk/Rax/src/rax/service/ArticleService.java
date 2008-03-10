package rax.service;

import java.util.List;

import rax.dao.hibernate.ArticleDao;
import rax.model.Article;
import rax.model.ArticleCategory;

public class ArticleService {

    private ArticleDao articleDao;

    public ArticleService() {
        articleDao = new ArticleDao();
    }

    public long createArticle(Article article) {
        return articleDao.create(article);
    }

    public boolean deleteArticle(long id) {
        Article article = articleDao.read(id);
        return (null == article) ? false : articleDao.delete(article);
    }

    public boolean updateArticle(long id, Article article) {
        return articleDao.update(article);
    }

    public long getCount(boolean onlyPub) {
        return articleDao.count(onlyPub);
    }

    public long getCountByCategory(ArticleCategory category, boolean onlyPub) {
        return articleDao.countByCategoryId(category.getId(), onlyPub);
    }

    public List<Article> listArticlesByCategory(ArticleCategory category,
            long index, int num, boolean onlyPub) {
        return articleDao.listByCategoryId(category.getId(), index, num,
                onlyPub);
    }

}
