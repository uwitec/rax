package rax.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import rax.model.Faq;

public interface FaqDao {

    public Long create(Faq newInstance) throws DataAccessException;

    public Faq read(Long id) throws DataAccessException;

    public int update(Faq transientObject) throws DataAccessException;

    public int delete(Faq persistentObject) throws DataAccessException;

    public int count(boolean bOnlyPub) throws DataAccessException;

    public List<Faq> list(int index, int num, boolean bOnlyPub)
            throws DataAccessException;

    public List<Faq> listAll(boolean bOnlyPub) throws DataAccessException;

}
