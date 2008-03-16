package rax.dao;

import java.util.List;

import rax.model.Faq;

public interface FaqDao extends GenericDao<Faq, Long> {

    @Override
    public int count();

    public int count(boolean bOnlyPub);

    @Override
    public List<Faq> list(int index, int num);

    public List<Faq> list(int index, int num, boolean bOnlyPub);

    @Override
    public List<Faq> listAll();

    public List<Faq> listAll(boolean bOnlyPub);

    @Override
    public Long create(Faq newInstance);

    @Override
    public int delete(Faq persistentObject);

    @Override
    public Faq read(Long id);

    @Override
    public int update(Faq transientObject);

}
