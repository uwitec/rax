package rax.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import rax.model.Attachment;

public interface AttachmentDao {

    public int create(Attachment newInstance) throws DataAccessException;

    public Attachment read(int id) throws DataAccessException;

    public int update(Attachment transientObject) throws DataAccessException;

    public int delete(Attachment persistentObject) throws DataAccessException;

    public int count() throws DataAccessException;

    public int countByArticleId(int id) throws DataAccessException;

    public List<Attachment> listByArticleId(int id) throws DataAccessException;

}
