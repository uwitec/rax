package rax.service;

import java.util.List;

import rax.dao.FaqDao;
import rax.model.Faq;

public class FaqService {

    private FaqDao faqDao;

    public FaqService() {
    }

    public Faq getFaqById(int id) {
        return faqDao.read(id);
    }

    public int createFaq(Faq faq) {
        return faqDao.create(faq);
    }

    public boolean updateFaq(Faq faq) {
        faqDao.update(faq);
        return true;
    }

    public boolean deleteFaq(int id) {
        boolean ret = false;
        Faq faq = faqDao.read(id);
        if (faq != null) {
            faqDao.delete(faq);
            ret = true;
        }
        return ret;
    }

    public int getCount(boolean onlyPub) {
        return faqDao.count(onlyPub);
    }

    public List<Faq> listFaqs(int index, int num, boolean onlyPub) {
        return faqDao.list(index, num, onlyPub);
    }

    public List<Faq> listAllFaqs(boolean onlyPub) {
        return faqDao.listAll(onlyPub);
    }

    public void setFaqDao(FaqDao dao) {
        faqDao = dao;
    }
}
