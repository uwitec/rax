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
    public int delete(String persistentObject);

    public int deleteByCategoryId(Long id);

    @Override
    public String read(Long id);

    @Override
    public int update(String transientObject);

}
