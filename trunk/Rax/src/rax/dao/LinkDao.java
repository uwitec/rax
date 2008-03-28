package rax.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import rax.model.Link;

public interface LinkDao {

    public int create(Link newInstance) throws DataAccessException;

    public Link read(int id) throws DataAccessException;

    public int update(Link transientObject) throws DataAccessException;

    public int delete(Link persistentObject) throws DataAccessException;

    public int deleteByCategoryId(int id) throws DataAccessException;

    public int count(boolean bOnlyPub) throws DataAccessException;

    public int countByCategoryId(int id, boolean bOnlyPub)
            throws DataAccessException;

    public List<Link> list(int index, int num, boolean bOnlyPub)
            throws DataAccessException;

    public List<Link> listByCategoryId(int id, int index, int num,
            boolean bOnlyPub) throws DataAccessException;

    public List<Link> listAll(boolean bOnlyPub) throws DataAccessException;

    public List<Link> listAllByCategoryId(int id, boolean bOnlyPub)
            throws DataAccessException;

}
