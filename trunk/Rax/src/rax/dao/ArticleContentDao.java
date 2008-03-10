package rax.dao;

import java.util.List;

public interface ArticleContentDao extends GenericDao<String, Long> {

    @Override
    public long count();

    public long countByCategoryId(Long id);

    @Override
    public List<String> list(int index, int num);

    public List<String> listByCategoryId(Long id, int index, int num);

    @Override
    public List<String> listAll();

    @Override
    public Long create(String newInstance);

    @Override
    public boolean delete(String persistentObject);

    public boolean deleteByCategoryId(Long id);

    @Override
    public String read(Long id);

    @Override
    public boolean update(String transientObject);

}
