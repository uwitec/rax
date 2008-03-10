package rax.dao.hibernate;

import java.util.List;

public class ArticleContentDao implements GenericDao<String, Long> {

    @Override
    public long count() {
        return 0;
    }

    public long countByCategoryId(Long id) {
        return 0;
    }

    @Override
    public List<String> list(int index, int num) {

        return null;
    }
    
    public List<String> listByCategoryId(Long id, int index, int num) {

        return null;
    }
    
    @Override
    public List<String> listAll() {

        return null;
    }

    @Override
    public Long create(String newInstance) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean delete(String persistentObject) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean deleteByCategoryId(Long id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String read(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean update(String transientObject) {
        // TODO Auto-generated method stub
        return false;
    }

}
