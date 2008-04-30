package rax.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import rax.dao.AttachmentDao;
import rax.model.Attachment;

public class AttachmentDaoImpl extends SqlMapClientDaoSupport implements
        AttachmentDao {

    @Override
    public int create(Attachment newInstance) throws DataAccessException {
        newInstance.setId((Integer) getSqlMapClientTemplate().insert(
                "createAttachment", newInstance));
        return newInstance.getId();
    }

    @Override
    public Attachment read(int id) throws DataAccessException {
        return (Attachment) getSqlMapClientTemplate().queryForObject(
                "readAttachment", id);
    }

    @Override
    public int update(Attachment transientObject) throws DataAccessException {
        return getSqlMapClientTemplate().update("updateAttachment",
                transientObject);
    }

    @Override
    public int delete(Attachment persistentObject) throws DataAccessException {
        return getSqlMapClientTemplate().delete("deleteAttachment",
                persistentObject.getId());
    }

    @Override
    public int count() throws DataAccessException {
        return (Integer) getSqlMapClientTemplate().queryForObject(
                "countAttachment");
    }

    @Override
    public int countByArticleId(int id) throws DataAccessException {
        return (Integer) getSqlMapClientTemplate().queryForObject(
                "countAttachmentByArticleId", id);
    }

    @Override
    public List<Attachment> listByArticleId(int id) throws DataAccessException {
        return getSqlMapClientTemplate().queryForList(
                "listAttachmentByArticleId", id);
    }
}
