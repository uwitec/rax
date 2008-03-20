package rax.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import rax.model.LinkCategory;

public interface LinkCategoryDao {

    public int create(LinkCategory newInstance) throws DataAccessException;

    public LinkCategory read(int id) throws DataAccessException;

    public int update(LinkCategory transientObject) throws DataAccessException;

    public int delete(LinkCategory persistentObject) throws DataAccessException;

    public int count() throws DataAccessException;

    public List<LinkCategory> list(int index, int num)
            throws DataAccessException;

    public List<LinkCategory> listAll() throws DataAccessException;

}
