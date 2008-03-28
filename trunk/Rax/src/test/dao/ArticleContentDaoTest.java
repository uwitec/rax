package test.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import rax.dao.ArticleContentDao;

public class ArticleContentDaoTest extends
        AbstractTransactionalDataSourceSpringContextTests {

    private ArticleContentDao dao;

    public void testAll() throws Exception {
        List<String> list;
        String content;
        int num = 2;
        int pages = 3;
        for (int i = 1; i <= num; i++) {
            list = new ArrayList<String>();
            for (int j = 1; j <= pages; j++) {
                list.add("Content_" + i + "_" + j);
            }
            dao.create(-i, list);
        }

        for (int i = 1; i <= num; i++) {
            for (int j = 1; j <= pages; j++) {
                content = dao.read(-i, j);
                assertNotNull(content);
                assertTrue(content.equals("Content_" + i + "_" + j));
            }
            list = dao.listByArticleId(-i);
            for (int j = 1; j <= pages; j++) {
                list.set(j - 1, "Content-" + i + "-" + j);
            }
            dao.update(-i, list);

            list = dao.listByArticleId(-i);
            assertTrue(list.size() == pages);
            for (int j = 1; j <= pages; j++) {
                content = dao.read(-i, j);
                assertNotNull(content);
                assertTrue(content.equals("Content-" + i + "-" + j));
            }
            assertTrue(dao.countByArticleId(-i) == pages);
        }

        for (int i = 1; i <= num; i++) {
            assertTrue(dao.deleteByArticleId(-i) > 0);
            assertTrue(dao.countByArticleId(-i) == 0);
        }
    }

    public void setDao(ArticleContentDao dao) {
        this.dao = dao;
    }

    protected String[] getConfigLocations() {
        String[] config = new String[] { "applicationContext-dao.xml",
                "applicationContext-test.xml" };
        return config;
    }

}
