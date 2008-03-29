package test.dao;

import java.util.List;

import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import rax.dao.LinkCategoryDao;
import rax.model.LinkCategory;

public class LinkCategoryDaoTest extends
        AbstractTransactionalDataSourceSpringContextTests {

    private LinkCategoryDao dao;

    public void testCount() throws Exception {
        assertTrue(dao.count() >= 0);
    }

    public void testList() throws Exception {
        List<LinkCategory> list;
        int cnt = dao.count();

        list = dao.list(0, cnt);
        assertNotNull(list);
        assertTrue(list.size() == cnt);

        list = dao.list(0, cnt / 2);
        assertNotNull(list);
        assertTrue(list.size() == cnt / 2);

        list = dao.listAll();
        assertNotNull(list);
        assertTrue(list.size() == cnt);
    }

    public void testAll() throws Exception {
        int id;
        int cnt;
        int num = 7;
        LinkCategory obj = new LinkCategory();
        cnt = dao.count();
        for (int i = 1; i <= num; i++) {
            obj.setTitle("Title" + i);
            obj.setSummary("Summary" + i);
            id = dao.create(obj);
            assertTrue(id > 0);

            obj = dao.read(id);
            assertNotNull(obj);
            assertTrue(obj.getTitle().equals("Title" + i));
            obj.setTitle("Title-" + i);
            assertTrue(dao.update(obj) > 0);

            obj = dao.read(id);
            assertNotNull(obj);
            assertTrue(obj.getTitle().equals("Title-" + i));
        }
        assertTrue(dao.count() == cnt + num);

        List<LinkCategory> list;

        cnt = dao.count();
        list = dao.list(0, cnt / 2);
        assertTrue(list.size() == cnt / 2);

        list = dao.listAll();
        cnt = list.size();
        assertTrue(cnt > 0);

        assertTrue(dao.delete(obj) > 0);
    }

    public void setDao(LinkCategoryDao dao) {
        this.dao = dao;
    }

    protected String[] getConfigLocations() {
        String[] config = new String[] { "applicationContext-dao.xml",
                "applicationContext-test.xml" };
        return config;
    }

}
