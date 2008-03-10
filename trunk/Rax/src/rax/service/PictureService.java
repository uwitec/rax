package rax.service;

import java.util.List;

import rax.dao.hibernate.PictureDao;
import rax.model.Article;
import rax.model.Picture;

public class PictureService {

    private PictureDao pictureDao;

    public PictureService() {
        pictureDao = new PictureDao();
    }

    public long createfaq(Picture picture) {
        return pictureDao.create(picture);
    }

    public boolean deleteFaq(long id) {
        Picture picture = pictureDao.read(id);
        return (null == picture) ? false : pictureDao.delete(picture);
    }

    public boolean updateFaq(long id, Picture picture) {
        return pictureDao.update(picture);
    }

    public long getCount() {
        return pictureDao.count();
    }

    public List<Picture> listPicturesByArticle(Article article) {
        return pictureDao.listByArticleId(article.getId());
    }

}
