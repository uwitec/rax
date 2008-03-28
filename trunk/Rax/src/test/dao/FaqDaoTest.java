package test.dao;

import java.util.Date;
import java.util.List;

import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import rax.dao.FaqDao;
import rax.model.Faq;

public class FaqDaoTest extends
        AbstractTransactionalDataSourceSpringContextTests {

    private FaqDao dao;

    public void testCountFaq() throws Exception {
        int cnt = dao.count(true);
        assertTrue(cnt >= 0);
        assertTrue(dao.count(false) >= cnt);
    }

    public void testCreateFaq() throws Exception {
        int id;
        int cnt;
        int num = 3;
        Faq obj = new Faq();
        cnt = dao.count(false);
        for (int i = 1; i <= num; i++) {
            obj.setQuestion("Q" + i);
            obj.setAnswer("A" + i);
            obj.setPubDate(new Date());
            obj.setPub(i % 2 == 1);
            id = dao.create(obj);
            assertTrue(id > 0);
        }
        assertTrue(dao.count(false) == cnt + num);
    }

    public void testReadFaq() throws Exception {
        Faq obj = new Faq();
        obj = dao.read(1);
        assertNotNull(obj);
    }

    public void testUpdateFaq() throws Exception {
        Faq obj = new Faq();
        obj = dao.read(1);
        obj.setPubDate(new Date());
        assertTrue(dao.update(obj) > 0);
    }

    public void testDeleteFaq() throws Exception {
        Faq obj = dao.read(1);
        assertTrue(dao.delete(obj) > 0);
    }

    public void testList() throws Exception {
        int cnt = dao.count(false);
        List<Faq> list = dao.list(0, cnt / 2, false);
        assertTrue(list.size() == cnt / 2);
        list = dao.list(0, cnt / 2, false);
        assertTrue(cnt - list.size() > 1);
    }

    public void testListAll() throws Exception {
        int size;
        Faq obj = new Faq();
        obj.setQuestion("Q");
        obj.setAnswer("A");
        obj.setPubDate(new Date());
        obj.setPub(false);
        dao.create(obj);
        List<Faq> list = dao.listAll(false);
        size = list.size();
        assertTrue(size > 0);
        list = dao.listAll(true);
        assertTrue(list.size() < size);
    }

    public void setDao(FaqDao dao) {
        this.dao = dao;
    }

    protected String[] getConfigLocations() {
        String[] config = new String[] { "applicationContext-dao.xml",
                "applicationContext-test.xml" };
        return config;
    }

}
