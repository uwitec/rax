package test.dao;

import java.util.Date;
import java.util.List;

import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import rax.dao.ArticleDao;
import rax.model.Article;

public class ArticleDaoTest extends
        AbstractTransactionalDataSourceSpringContextTests {

    private ArticleDao dao;

    public void testCount() throws Exception {
        int cnt = dao.count(true);
        assertTrue(cnt >= 0);
        assertTrue(dao.count(false) >= cnt);
    }

    public void testAll() throws Exception {
        int id;
        int cnt;
        int num = 7;
        Article obj = new Article();
        cnt = dao.count(false) - dao.count(true);
        for (int i = 1; i <= num; i++) {
            obj.setCategoryId(i % 2 + 1);
            obj.setTitle("Title" + i);
            obj.setSummary("Summary" + i);
            obj.setAuthor("Author" + i);
            obj.setSource("http://source/of/this/article.html");
            obj.setCreateDate(new Date());
            obj.setViews(0);
            obj.setPub(i > num / 2);
            id = dao.create(obj);
            assertTrue(id > 0);

            obj = dao.read(id);
            assertNotNull(obj);
            assertTrue(obj.getTitle().equals("Title" + i));

            obj.setCategoryId(-obj.getCategoryId());
            assertTrue(dao.update(obj) > 0);

            obj = dao.read(id);
            assertNotNull(obj);
            assertTrue(obj.getCategoryId() < 0);

            /*
             * System.out.println(obj.getCategoryId());
             * System.out.println(obj.getTitle());
             * System.out.println(obj.getSummary());
             * System.out.println(obj.getAuthor());
             * System.out.println(obj.getSource());
             * System.out.println(obj.getCreateDate());
             * System.out.println(obj.getViews());
             * System.out.println(obj.isPub()); System.out.println();
             */
        }
        assertTrue(dao.count(false) - dao.count(true) == cnt
                + Math.round(num / 2));

        List<Article> list;
        cnt = dao.countByCategoryId(-1, false);
        list = dao.listByCategoryId(-1, 0, cnt / 2, false);
        assertTrue(list.size() == cnt / 2);

        cnt = dao.countByCategoryId(-1, true);
        list = dao.listByCategoryId(-1, 0, cnt / 2, true);
        assertTrue(cnt > list.size());

        assertTrue(dao.delete(obj) > 0);
        assertTrue(dao.deleteByCategoryId(-1) > 0);

        list = dao.listAllByCategoryId(-2, false);
        cnt = list.size();
        assertTrue(list.size() == Math.round(num / 2));

        list = dao.listAllByCategoryId(-2, true);
        assertTrue(list.size() < cnt);
    }

    public void setDao(ArticleDao dao) {
        this.dao = dao;
    }

    protected String[] getConfigLocations() {
        String[] config = new String[] { "applicationContext-dao.xml",
                "applicationContext-test.xml" };
        return config;
    }

}
