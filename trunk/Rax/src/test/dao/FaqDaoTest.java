package test.dao;

import java.util.Date;
import java.util.List;

import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import rax.dao.FaqDao;
import rax.model.Faq;

public class FaqDaoTest extends
        AbstractTransactionalDataSourceSpringContextTests {

    private FaqDao dao;

    public void testCount() throws Exception {
        int cnt = dao.count(true);
        assertTrue(cnt >= 0);
        assertTrue(dao.count(false) >= cnt);
    }

    public void testList() throws Exception {
        List<Faq> list;
        int size;
        int cnt = dao.count(false);
        list = dao.list(0, cnt, false);
        assertNotNull(list);
        size = list.size();
        list = dao.list(0, cnt, true);
        assertNotNull(list);
        assertTrue(size >= list.size());
        list = dao.listAll(false);
        size = list.size();
        assertNotNull(list);
        list = dao.listAll(true);
        assertNotNull(list);
        assertTrue(size >= list.size());
    }

    public void testAll() throws Exception {
        int id;
        int cnt;
        int num = 7;
        Faq obj = new Faq();
        cnt = dao.count(false) - dao.count(true);
        for (int i = 1; i <= num; i++) {
            obj.setQuestion("Q" + i);
            obj.setAnswer("A" + i);
            obj.setPubDate(new Date());
            obj.setPub(i > num / 2);
            id = dao.create(obj);
            assertTrue(id > 0);

            obj = dao.read(id);
            assertNotNull(obj);
            assertTrue(obj.getQuestion().equals("Q" + i));
            obj.setPubDate(new Date());
            obj.setAnswer("Answer" + i);
            assertTrue(dao.update(obj) > 0);
            
            obj = dao.read(id);
            assertNotNull(obj);
            assertTrue(obj.getAnswer().equals("Answer" + i));
            
            System.out.println("ID:" + obj.getId());
            System.out.println("Question:" + obj.getQuestion());
            System.out.println("Answer:" + obj.getAnswer());
            System.out.println("PubDate:" + obj.getPubDate());
        }
        assertTrue(dao.count(false) - dao.count(true) == cnt
                + Math.round(num / 2));

        List<Faq> list;

        cnt = dao.count(false);
        list = dao.list(0, cnt / 2, false);
        assertTrue(list.size() == cnt / 2);

        cnt = dao.count(true);
        list = dao.list(0, cnt / 2, true);
        assertTrue(cnt - list.size() > Math.round(num / 2));

        assertTrue(dao.delete(obj) > 0);

        list = dao.listAll(false);
        cnt = list.size();
        assertTrue(cnt > 0);
        list = dao.listAll(true);
        assertTrue(list.size() < cnt);
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
