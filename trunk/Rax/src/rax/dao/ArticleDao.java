package rax.dao;

import java.util.List;

import rax.model.Article;

public interface ArticleDao extends GenericDao<Article, Long> {

    @Override
    public long count();

    public long count(boolean onlyPub);

    public long countByCategoryId(Long id);

    public long countByCategoryId(Long id, boolean onlyPub);

    @Override
    public List<Article> list(int index, int num);

    public List<Article> list(long index, int num, boolean onlyPub);

    @Override
    public List<Article> listAll();

    public List<Article> listAll(boolean onlyPub);

    public List<Article> listByCategoryId(Long id, long index, int num);

    public List<Article> listByCategoryId(Long id, long index, int num,
            boolean onlyPub);

    public List<Article> listAllByCategoryId(Long id);

    public List<Article> listAllByCategoryId(Long id, boolean onlyPub);

    @Override
    public Long create(Article newInstance);

    @Override
    public int delete(Article persistentObject);

    public int deleteByCategoryId(Long id);

    @Override
    public Article read(Long id);

    @Override
    public int update(Article transientObject);

}
