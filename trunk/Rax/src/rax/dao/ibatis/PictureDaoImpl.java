package rax.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import rax.dao.PictureDao;
import rax.model.Picture;

public class PictureDaoImpl extends SqlMapClientDaoSupport implements
        PictureDao {

    @Override
    public int create(Picture newInstance) throws DataAccessException {
        newInstance.setId((Integer) getSqlMapClientTemplate().insert("createPicture",
                newInstance));
        return newInstance.getId();
   }

    @Override
    public Picture read(int id) throws DataAccessException {
        return (Picture) getSqlMapClientTemplate().queryForObject(
                "readPicture", id);
    }

    @Override
    public int update(Picture transientObject) throws DataAccessException {
        return getSqlMapClientTemplate().update("updatePicture",
                transientObject);
    }

    @Override
    public int delete(Picture persistentObject) throws DataAccessException {
        return getSqlMapClientTemplate().delete("deletePicture",
                persistentObject.getId());
    }

    @Override
    public int count() throws DataAccessException {
        return (Integer) getSqlMapClientTemplate().queryForObject(
                "countPicture");
    }

    @Override
    public int countByArticleId(int id) throws DataAccessException {
        return (Integer) getSqlMapClientTemplate().queryForObject(
                "countPictureByArticleId", id);
    }

    @Override
    public List<Picture> listByArticleId(int id) throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("listPictureByArticleId",
                id);
    }

}
