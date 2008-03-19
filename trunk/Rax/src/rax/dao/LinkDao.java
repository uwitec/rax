package rax.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import rax.model.Link;

public interface LinkDao {

    public Integer create(Link newInstance) throws DataAccessException;

    public Link read(Integer id) throws DataAccessException;

    public int update(Link transientObject) throws DataAccessException;

    public int delete(Link persistentObject) throws DataAccessException;

    public int deleteByCategoryId(Integer id) throws DataAccessException;

    public int count() throws DataAccessException;

    public int countByCategoryId(Integer id, boolean bOnlyPub)
            throws DataAccessException;

    public List<Link> list(int index, int num, boolean bOnlyPub)
            throws DataAccessException;

    public List<Link> listByCategoryId(Integer id, int index, int num,
            boolean bOnlyPub) throws DataAccessException;

    public List<Link> listAll(boolean bOnlyPub) throws DataAccessException;

    public List<Link> listAllByCategoryId(Integer id, boolean bOnlyPub)
            throws DataAccessException;

}
