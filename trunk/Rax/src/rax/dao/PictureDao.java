package rax.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import rax.model.Picture;

public interface PictureDao {

    public Long create(Picture newInstance) throws DataAccessException;

    public Picture read(Long id) throws DataAccessException;

    public int update(Picture transientObject) throws DataAccessException;

    public int delete(Picture persistentObject) throws DataAccessException;

    public int count() throws DataAccessException;

    public int countByArticleId(Long id) throws DataAccessException;

    public List<Picture> listByArticleId(Long id) throws DataAccessException;

}
