package rax.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import rax.model.Link;

public interface LinkDao {

    public Long create(Link newInstance) throws DataAccessException;

    public Link read(Long id) throws DataAccessException;

    public int update(Link transientObject) throws DataAccessException;

    public int delete(Link persistentObject) throws DataAccessException;

    public int deleteByCategoryId(Long id) throws DataAccessException;

    public int count() throws DataAccessException;

    public int countByCategoryId(Long id, boolean bOnlyPub)
            throws DataAccessException;

    public List<Link> list(int index, int num, boolean bOnlyPub)
            throws DataAccessException;

    public List<Link> listByCategoryId(Long id, int index, int num,
            boolean bOnlyPub) throws DataAccessException;

    public List<Link> listAll(boolean bOnlyPub) throws DataAccessException;

    public List<Link> listAllByCategoryId(long id, boolean bOnlyPub)
            throws DataAccessException;

}
