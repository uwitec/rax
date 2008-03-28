package test.dao;

import java.util.List;

import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import rax.dao.LinkDao;
import rax.model.Link;

public class LinkDaoTest extends
        AbstractTransactionalDataSourceSpringContextTests {

    private LinkDao dao;

    public void testCountFaq() throws Exception {
        int cnt = dao.count(true);
        assertTrue(cnt >= 0);
        assertTrue(dao.count(false) >= cnt);
    }

    public void testList() throws Exception {
        List<Link> list;
        int size;
        int cnt = dao.count(false);
        list = dao.list(0, cnt, false);
        size = list.size();
        assertNotNull(list);
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
        Link obj = new Link();
        cnt = dao.count(false) - dao.count(true);
        for (int i = 1; i <= num; i++) {
            obj.setTitle("Title" + i);
            obj.setSummary("Summary" + i);
            obj.setAddress("Address" + i);
            obj.setCategoryId(i % 2 + 1);
            obj.setPub(i > (num / 2));
            id = dao.create(obj);
            assertTrue(id > 0);
            
            obj = dao.read(id);
            obj.setCategoryId(-obj.getCategoryId());
            assertTrue(dao.update(obj) > 0);
        }
        assertTrue(dao.count(false) - dao.count(true) == cnt
                + Math.round(num / 2));

        List<Link> list;
        cnt = dao.countByCategoryId(-1, false);
        list = dao.listByCategoryId(-1, 0, cnt / 2, false);
        assertTrue(list.size() == cnt / 2);
        cnt = dao.countByCategoryId(-1, true);
        list = dao.listByCategoryId(-1, 0, cnt / 2, true);
        assertTrue(list.size() == cnt / 2);

        cnt = dao.count(true);
        list = dao.list(0, cnt / 2, true);
        assertTrue(cnt - list.size() > Math.round(num / 2));

        assertTrue(dao.delete(obj) > 0);
        assertTrue(dao.deleteByCategoryId(-1) > 0);
        assertTrue(dao.countByCategoryId(-1, false) == 0);

        list = dao.listAllByCategoryId(-2, true);
        cnt = list.size();
        list = dao.listAllByCategoryId(-2, false);
        assertTrue(list.size() > cnt);
        assertTrue(dao.deleteByCategoryId(-2) > 0);
        list = dao.listAllByCategoryId(-2, false);
        assertTrue(list.size() == 0);
    }

    public void setDao(LinkDao dao) {
        this.dao = dao;
    }

    protected String[] getConfigLocations() {
        String[] config = new String[] { "applicationContext-dao.xml",
                "applicationContext-test.xml" };
        return config;
    }
}
