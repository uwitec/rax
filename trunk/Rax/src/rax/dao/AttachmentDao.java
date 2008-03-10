package rax.dao;

import java.util.List;

import rax.model.Attachment;

public interface AttachmentDao extends GenericDao<Attachment, Long> {

    @Override
    public long count();

    public long countByArticleId(Long id);

    @Override
    public List<Attachment> list(int index, int num);

    @Override
    public List<Attachment> listAll();

    public List<Attachment> listByArticleId(Long id);

    @Override
    public Long create(Attachment newInstance);

    @Override
    public boolean delete(Attachment persistentObject);

    @Override
    public Attachment read(Long id);

    @Override
    public boolean update(Attachment transientObject);

}
