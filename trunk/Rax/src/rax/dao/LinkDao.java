package rax.dao;

import java.util.List;

import rax.model.Link;

public interface LinkDao extends GenericDao<Link, Long> {

    public long count();

    public long countByCategoryId(Long id);

    public long countByCategoryId(Long id, boolean bOnlyPub);

    @Override
    public List<Link> list(int index, int num);

    public List<Link> list(int index, int num, boolean bOnlyPub);

    public List<Link> listByCategoryId(Long id, int index, int num);

    public List<Link> listByCategoryId(Long id, int index, int num,
            boolean bOnlyPub);

    @Override
    public List<Link> listAll();

    public List<Link> listAll(boolean bOnlyPub);

    public List<Link> listAllByCategoryId(long id);

    public List<Link> listAllByCategoryId(long id, boolean bOnlyPub);

    @Override
    public Long create(Link newInstance);

    @Override
    public int delete(Link persistentObject);

    public int deleteByCategoryId(Long id);

    @Override
    public Link read(Long id);

    @Override
    public int update(Link transientObject);

}
