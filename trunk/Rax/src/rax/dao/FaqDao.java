package rax.dao;

import java.util.List;

import rax.model.Faq;

public interface FaqDao extends GenericDao<Faq, Long> {

    @Override
    public long count();

    public long count(boolean bOnlyPub);

    @Override
    public List<Faq> list(int index, int num);

    public List<Faq> list(int index, int num, boolean bOnlyPub);

    @Override
    public List<Faq> listAll();

    public List<Faq> listAll(boolean bOnlyPub);

    @Override
    public Long create(Faq newInstance);

    @Override
    public boolean delete(Faq persistentObject);

    @Override
    public Faq read(Long id);

    @Override
    public boolean update(Faq transientObject);

}
