package rax.service;

import java.util.List;

import rax.dao.PictureDao;
import rax.model.Article;
import rax.model.Picture;

public class PictureService {

    private PictureDao pictureDao;

    public PictureService() {
    }

    public long createPicture(Picture picture) {
        return pictureDao.create(picture);
    }

    public boolean deletePicture(long id) {
        boolean ret = false;
        Picture picture = pictureDao.read(id);
        if (null != picture) {
            pictureDao.delete(picture);
            ret = true;
        }
        return ret;
    }

    public boolean updatePicture(long id, Picture picture) {
        pictureDao.update(picture);
        return true;
    }

    public long getCount() {
        return pictureDao.count();
    }

    public List<Picture> listPicturesByArticle(Article article) {
        return pictureDao.listByArticleId(article.getId());
    }

    public void setPictureDao(PictureDao dao) {
        pictureDao = dao;
    }

}
