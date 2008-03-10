package rax.dao.hibernate;

import java.util.List;

import rax.model.Article;

public class ArticleDao implements GenericDao<Article, Long> {

    @Override
    public long count() {
        return count(false);
    }
    
    public long count(boolean onlyPub) {
        // TODO Auto-generated method stub
        return 0;
    }

    public long countByCategoryId(Long id) {
        return countByCategoryId(id, false);
    }  
        
    public long countByCategoryId(Long id, boolean onlyPub) {
        // TODO Auto-generated method stub
        return 0;
    }  
    
    @Override
    public List<Article> list(int index, int num) {
        return list(index, num, false);
    }
    
    public List<Article> list(long index, int num, boolean onlyPub) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public List<Article> listAll() {
        return listAll(false);
    }
    
    public List<Article> listAll(boolean onlyPub) {
        // TODO Auto-generated method stub
        return null;
    }
    
    public List<Article> listByCategoryId(Long id, long index, int num) {
        return listByCategoryId(id, index, num, false);
    }
    
    public List<Article> listByCategoryId(Long id, long index, int num, boolean onlyPub) {
        // TODO Auto-generated method stub
        return null;
    }
    
    public List<Article> listAllByCategoryId(Long id) {
        return listAllByCategoryId(id, false);
    }
    
    public List<Article> listAllByCategoryId(Long id, boolean onlyPub) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Long create(Article newInstance) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean delete(Article persistentObject) {
        // TODO Auto-generated method stub
        return false;
    }
    
    public boolean deleteByCategoryId(Long id) {
        // TODO Auto-generated method stub
        return false;
    }
    
    @Override
    public Article read(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean update(Article transientObject) {
        // TODO Auto-generated method stub
        return false;
    }

}
