package rax.service;

import java.util.List;

import rax.dao.hibernate.AttachmentDao;
import rax.model.Article;
import rax.model.Attachment;

public class AttachmentService {

    private AttachmentDao attachmentDao;

    public AttachmentService() {
        attachmentDao = new AttachmentDao();
    }

    public long createAttachment(Attachment attachment) {
        return attachmentDao.create(attachment);
    }

    public boolean deleteAttachment(long id) {
        Attachment attachment = attachmentDao.read(id);
        return (null == attachment) ? false : attachmentDao.delete(attachment);
    }

    public boolean updateAttachment(long id, Attachment attachment) {
        return attachmentDao.update(attachment);
    }

    public long getCount() {
        return attachmentDao.count();
    }

    public List<Attachment> listAttachmentsByArticle(Article article) {
        return attachmentDao.listByArticleId(article.getId());
    }

}
