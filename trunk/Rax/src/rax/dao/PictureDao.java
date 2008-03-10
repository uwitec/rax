package rax.dao;

import java.util.List;

import rax.model.Picture;

public interface PictureDao extends GenericDao<Picture, Long> {

    @Override
    public long count();

    public long countByArticleId(Long id);

    @Override
    public List<Picture> list(int index, int num);

    @Override
    public List<Picture> listAll();

    public List<Picture> listByArticleId(Long id);

    @Override
    public Long create(Picture newInstance);

    @Override
    public boolean delete(Picture persistentObject);

    @Override
    public Picture read(Long id);

    @Override
    public boolean update(Picture transientObject);

}
