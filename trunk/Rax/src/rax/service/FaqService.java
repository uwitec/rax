package rax.service;

import java.util.List;

import rax.dao.FaqDao;
import rax.model.Faq;

public class FaqService {

    private FaqDao faqDao;

    public FaqService() {
    }

    public void setFaqDao(FaqDao dao) {
        faqDao = dao;
    }

    public long createFaq(Faq faq) {
        return faqDao.create(faq);
    }

    public boolean deleteFaq(long id) {

        boolean ret = false;
        Faq faq = faqDao.read(id);
        if (faq != null) {
            faqDao.delete(faq);
            ret = true;
        }

        return ret;
    }

    public boolean updateFaq(long id, Faq faq) {
        faqDao.update(faq);
        return true;
    }

    public long getCount(boolean onlyPub) {
        return faqDao.count();
    }

    public List<Faq> listFaqs(int index, int num, boolean onlyPub) {
        return faqDao.list(index, num, onlyPub);
    }

    public List<Faq> listAllFaqs(boolean onlyPub) {
        return faqDao.listAll(onlyPub);
    }

}
