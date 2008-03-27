package rax.service;

import java.util.List;

import rax.dao.PictureDao;
import rax.model.Article;
import rax.model.Picture;

public class PictureService {

    private PictureDao pictureDao;

    public PictureService() {
    }

    public Picture getPictureById(int id) {
        return pictureDao.read(id);
    }

    public int createPicture(Picture picture) {
        return pictureDao.create(picture);
    }

    public boolean deletePicture(int id) {
        boolean ret = false;
        Picture picture = pictureDao.read(id);
        if (null != picture) {
            pictureDao.delete(picture);
            ret = true;
        }
        return ret;
    }

    public boolean updatePicture(int id, Picture picture) {
        pictureDao.update(picture);
        return true;
    }

    public int getCount() {
        return pictureDao.count();
    }

    public List<Picture> listPicturesByArticle(Article article) {
        return pictureDao.listByArticleId(article.getId());
    }

    public void setPictureDao(PictureDao dao) {
        pictureDao = dao;
    }

}
